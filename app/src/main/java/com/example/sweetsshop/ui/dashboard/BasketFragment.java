package com.example.sweetsshop.ui.dashboard;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import com.example.sweetsshop.R;
import com.example.sweetsshop.databinding.FragmentBasketBinding;
import com.example.sweetsshop.models.ModelM;
import com.example.sweetsshop.models.Order;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.sweetsshop.remote_data.RetrofitClient;
import com.example.sweetsshop.ui.home.JemAdapter;


public class BasketFragment extends Fragment {

    private FragmentBasketBinding binding;
    private ArrayList<Order> orderList;
    private BasketAdapter adapter;
    private NavController navController;
    private Double totalSum = 0.0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentBasketBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        if (getArguments() != null) {
            orderList = getArguments().getParcelableArrayList("keysss_basket");
        }

        if (orderList != null) {
            adapter = new BasketAdapter(requireActivity(), orderList, this::updateTotalPrice);
            binding.rvBacket.setAdapter(adapter);
            updateTotalPrice();
        } else {
            orderList = new ArrayList<>();
        }

        return root;
    }

    private void updateTotalPrice() {
        totalSum = 0.0;
        for (Order order : orderList) {
            if (order.getDessert() != null) {
                totalSum += order.getQuantity() * order.getDessert().getModelPrice();
            }
        }
        binding.basketTotalCount.setText(String.valueOf(totalSum));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnBack.setOnClickListener(v -> {
            navController = Navigation.findNavController(requireActivity(), R.id.nav_host);
            navController.navigate(R.id.action_navigation_basket_to_navigation_home);
        });

        binding.btnPay.setOnClickListener(v1 -> {
            totalSum = 0.0;
            navController = Navigation.findNavController(requireActivity(), R.id.nav_host);
            navController.navigate(R.id.navigation_payment);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
