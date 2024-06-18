package com.example.sweetsshop.ui.category;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.sweetsshop.databinding.FragmentCategoryBinding;
import android.util.Log;
import com.example.sweetsshop.R;
import com.example.sweetsshop.models.ModelM;
import com.example.sweetsshop.remote_data.RetrofitClient;
import com.example.sweetsshop.ui.home.CategoryAdapter;
import com.example.sweetsshop.ui.home.JemAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryFragment extends Fragment {

    private FragmentCategoryBinding binding;
    private JemAdapter adapter;
    private NavController navController;
    private int categoryId;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCategoryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        if (getArguments() != null) {
            categoryId = getArguments().getInt("categoryId");
        }

        loadDessertsByCategory(categoryId);
        return root;
    }

    private void loadDessertsByCategory(int categoryId) {
        Call<List<ModelM>> apiCall = RetrofitClient.getInstance().getApi().getDessertByCategory(categoryId);
        apiCall.enqueue(new Callback<List<ModelM>>() {
            @Override
            public void onResponse(Call<List<ModelM>> call, Response<List<ModelM>> response) {
                if (response.body() != null && !response.body().isEmpty()) {
                    adapter = new JemAdapter();
                    binding.rvCategoryDesserts.setAdapter(adapter);
                    adapter.setList(response.body());
                } else {
                    Toast.makeText(requireActivity(), "No data for this category", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ModelM>> call, Throwable throwable) {
                Log.e("TAG", "No data: " + throwable.getLocalizedMessage());
                Toast.makeText(requireActivity(), "No data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btnBack.setOnClickListener(v -> {
            navController = Navigation.findNavController(requireActivity(), R.id.nav_host);
            navController.navigate(R.id.action_categoryFragment_to_navigation_home);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
