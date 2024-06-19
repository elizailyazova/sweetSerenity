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
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.widget.Toast;
import com.example.sweetsshop.databinding.DItemCardBinding;
import com.example.sweetsshop.models.ModelM;

import java.util.ArrayList;


public class DescriptionFragment extends Fragment {

    private FragmentDescriptionBinding binding;
    NavController navController;
    DescAdapter adapter;
    ArrayList<ModelM> d_list = new ArrayList<>();


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDescriptionBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        if (getArguments() != null) {
            d_list = getArguments().getParcelableArrayList("see_more");
            Log.e("TAG", "DATA GETTING!!!");
        } else {
            Toast.makeText(requireActivity(), "No data passed", Toast.LENGTH_SHORT).show();
        }

        adapter = new DescAdapter(requireContext(), d_list);
        binding.rvDetailsCatalog.setAdapter(adapter);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btnBack.setOnClickListener(v-> {
            navController = Navigation.findNavController(requireActivity(),
                    R.id.nav_host);
            navController.navigate(R.id.action_navigation_description_to_navigation_home);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}