package com.example.sweetsshop.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.sweetsshop.R;
import com.example.sweetsshop.databinding.FragmentHomeBinding;
import com.example.sweetsshop.models.Category;
import com.example.sweetsshop.models.ModelM;
import com.example.sweetsshop.remote_data.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    JemAdapter adapter;
    CategoryAdapter categoryAdapter;
    SearchAdapter searchAdapter;
    NavController navController;
    SharedPreferences preferences;
    List<Category> list_category = new ArrayList<>();
    String emailuserIdentify;

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

        Call<List<ModelM>> apiCall = RetrofitClient.getInstance().getApi().getStoreDesserts();
        apiCall.enqueue(new Callback<List<ModelM>>() {
            @Override
            public void onResponse(Call<List<ModelM>> call, Response<List<ModelM>> response) {
                if (!isAdded() || binding == null) return;
                if (response.body() != null) {
                    if (binding.progressBar != null) {
                        binding.progressBar.setVisibility(View.INVISIBLE);
                        adapter = new JemAdapter(requireActivity(), new ArrayList<>());
                        binding.rvCatalogM.setAdapter(adapter);
                    }
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
        Call<List<Category>> apiCalling = RetrofitClient.getInstance().getApi().getCategories();
        apiCalling.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (!isAdded() || binding == null) return;
                if (response.body() != null) {
                    if (binding.progressBar != null) {
                        binding.progressBar.setVisibility(View.INVISIBLE);
                    }
                    categoryAdapter = new CategoryAdapter();
                    binding.rvCatalogCategory.setAdapter(categoryAdapter);
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


        searchAdapter = new SearchAdapter(requireActivity(), new ArrayList<>());
        binding.rvSearchResults.setAdapter(searchAdapter);
        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Call<List<ModelM>> searchCall = RetrofitClient.getInstance().getApi().searchDesserts(query);
                searchCall.enqueue(new Callback<List<ModelM>>() {
                    @Override
                    public void onResponse(Call<List<ModelM>> call, Response<List<ModelM>> response) {
                        if (!isAdded() || binding == null) return;
                        if (response.isSuccessful()) {
                            List<ModelM> searchResults = response.body();
                            if (searchResults != null && !searchResults.isEmpty()) {
                                binding.searchResultsLabel.setVisibility(View.VISIBLE);
                                binding.rvSearchResults.setVisibility(View.VISIBLE);
                                searchAdapter.setList(searchResults);
                            } else {
                                binding.searchResultsLabel.setVisibility(View.GONE);
                                binding.rvSearchResults.setVisibility(View.GONE);
                                Toast.makeText(requireActivity(), "No search results", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Log.e("TAG", "Search request failed with code: " + response.code());
                            Toast.makeText(requireActivity(), "Search request failed", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<ModelM>> call, Throwable t) {
                        Log.e("SearchFragment", "Error searching desserts: " + t.getMessage());
                        Toast.makeText(requireContext(), "Failed to search desserts", Toast.LENGTH_SHORT).show();
                    }
                });
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return root;
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
