package com.example.sweetsshop.ui.notifications;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.sweetsshop.databinding.FragmentDescriptionBinding;

import com.example.sweetsshop.R;

import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.widget.Toast;
import com.example.sweetsshop.models.ModelM;

import java.util.ArrayList;

public class DescriptionFragment extends Fragment {

    private FragmentDescriptionBinding binding;
    NavController navController;
    DescAdapter adapter;
    ArrayList<ModelM> d_list;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDescriptionBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Bundle arguments = getArguments();

        if (arguments != null) {
            d_list = arguments.getParcelableArrayList("see_more");
            if (d_list == null || d_list.isEmpty()) {
                Log.e("TAG", "d_list is empty or null");
            } else {
                Log.e("TAG", "DATA GETTING!!!");
                adapter = new DescAdapter(requireContext(), d_list);
                binding.rvDetailsCatalog.setAdapter(adapter);
            }
        } else {
            Log.e("TAG", "Bundle arguments are null");
        }
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btnBack.setOnClickListener(v -> {
            navController = Navigation.findNavController(requireActivity(), R.id.nav_host);
            navController.navigate(R.id.action_navigation_description_to_navigation_home);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
