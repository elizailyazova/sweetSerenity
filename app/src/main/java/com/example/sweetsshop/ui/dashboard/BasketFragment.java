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
    private ArrayList<ModelM> basket_products;
    JemAdapter adapter;
    NavController navController;
    Double total_sum = 1.0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentBasketBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        if (getArguments() != null) {
            basket_products = new ArrayList<>();
            basket_products = getArguments().getParcelableArrayList("keysss_basket");
        }
        if (basket_products != null) {
            adapter = new JemAdapter(requireActivity(), basket_products);
            binding.rvBacket.setAdapter(adapter);
        }

        try {
            for (int i = 0; i <basket_products.size(); i++) {
                total_sum += basket_products.get(i).getModelPrice();
            }
            binding.basketTotalCount.setText(String.valueOf(total_sum - 1.0));
        } catch (NullPointerException ex) {
            binding.basketTotalCount.setText("0.00");
            Log.e("TAG", "error" + ex.getLocalizedMessage());
        }

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnBack.setOnClickListener(v -> {
            navController = Navigation.findNavController(requireActivity(), R.id.nav_host);
            navController.navigate(R.id.action_navigation_basket_to_navigation_home);
        });

        binding.btnPay.setOnClickListener(v1 -> {
            total_sum = 0.0;
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