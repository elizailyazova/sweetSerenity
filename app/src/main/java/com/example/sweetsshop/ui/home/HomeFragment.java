package com.example.sweetsshop.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.airbnb.lottie.LottieAnimationView;
import com.example.sweetsshop.R;
import com.example.sweetsshop.databinding.FragmentHomeBinding;
import com.example.sweetsshop.models.Category;
import com.example.sweetsshop.models.ModelM;
import com.example.sweetsshop.remote_data.RetrofitClient;
import com.example.sweetsshop.ui.category.CategoryViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    JemAdapter adapter;
    CategoryAdapter categoryAdapter;
    NavController navController;
    SharedPreferences preferences;
    List<Category> list_category = new ArrayList<>();
    String emailuserIdentify;
    private CategoryViewModel categoryViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        preferences = getActivity().getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        if (getArguments() != null) {
            emailuserIdentify = getArguments().getString("identify");
        }
        if (preferences.getBoolean("loggedin", true)) {
            binding.textViewIdentify.setVisibility(View.VISIBLE);
            binding.textViewIdentify.setText(emailuserIdentify);
        }
        categoryAdapter = new CategoryAdapter();
        categoryAdapter.setMain_list(list_category);
        binding.rvCatalogCategory.setAdapter(categoryAdapter);
        loadCategories();
        setupRecyclerView();
        fetchDesserts();
        setupCategoryViewModel();

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("HomeFragment", "onResume() called");
        loadCategories();
    }

    private void setupRecyclerView() {
        categoryAdapter = new CategoryAdapter();
        binding.rvCatalogCategory.setAdapter(categoryAdapter);
    }

    private void fetchDesserts() {
        Call<List<ModelM>> apiCall = RetrofitClient.getInstance().getApi().getStoreDesserts();
        apiCall.enqueue(new Callback<List<ModelM>>() {
            @Override
            public void onResponse(Call<List<ModelM>> call, Response<List<ModelM>> response) {
                if (!isAdded() || binding == null) return;
                if (response.body() != null) {
                    if (binding.progressBar != null) {
                        binding.progressBar.setVisibility(View.INVISIBLE);
                    }
                    adapter = new JemAdapter();
                    binding.rvCatalogM.setAdapter(adapter);
                    adapter.setList(response.body());
                } else {
                    Toast.makeText(requireActivity(), "NO data from sweets shop", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ModelM>> call, Throwable throwable) {
                if (!isAdded() || binding == null) return;
                Log.e("TAG", "NO DATA " + throwable.getLocalizedMessage());
                Toast.makeText(requireActivity(), "NO DATA", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupCategoryViewModel() {
        categoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
        categoryViewModel.getCategoryListLiveData().observe(getViewLifecycleOwner(), new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                if (!isAdded() || binding == null) return;
                if (categories != null) {
                    categoryAdapter.setMain_list(categories);
                } else {
                    Toast.makeText(requireContext(), "Failed to load categories", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void loadCategories() {
        Call<List<Category>> apiCall = RetrofitClient.getInstance().getApi().getCategories();
        apiCall.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (!isAdded() || binding == null) return;
                if (response.body() != null) {
                    if (binding.progressBar != null) {
                        binding.progressBar.setVisibility(View.INVISIBLE);
                    }
                    categoryAdapter.setMain_list(response.body());
                } else {
                    Toast.makeText(requireActivity(), "NO data from categories", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable throwable) {
                if (!isAdded() || binding == null) return;
                Log.e("TAG", "NO DATA " + throwable.getLocalizedMessage());
                Toast.makeText(requireActivity(), "NO DATA", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.basketBtn.setOnClickListener(v -> {
            PopupMenu popup = new PopupMenu(requireActivity(), binding.basketBtn);
            popup.getMenuInflater().inflate(R.menu.action_menu, popup.getMenu());
            popup.setOnMenuItemClickListener(item -> {
                switch (item.getTitle().toString()) {
                    case "Добавить в корзину":
                        navController = Navigation.findNavController(requireActivity(), R.id.nav_host);
                        Bundle bundle = new Bundle();
                        bundle.putParcelableArrayList("keysss_basket", adapter.getSelected_BasketList());
                        navController.navigate(R.id.navigation_basket, bundle);
                        break;
                    case "Пометить":
                        Toast.makeText(requireActivity(), "Marked", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(requireActivity(), "default", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            });
            popup.show();
        });
        binding.signInReg.setOnClickListener(v1 -> {
            navController = Navigation.findNavController(requireActivity(), R.id.nav_host);
            navController.navigate(R.id.action_navigation_home_to_navigation_registr);
        });
        binding.feedbackBtn.setOnClickListener(v1 -> {
            navController = Navigation.findNavController(requireActivity(), R.id.nav_host);
            navController.navigate(R.id.action_navigation_home_to_feedbackFragment);
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
