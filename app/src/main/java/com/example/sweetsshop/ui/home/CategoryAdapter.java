package com.example.sweetsshop.ui.home;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sweetsshop.R;
import com.example.sweetsshop.databinding.ItemCategoryBinding;
import com.example.sweetsshop.models.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    ItemCategoryBinding binding;
    List<Category> main_list = new ArrayList<>();
    NavController navController;

    public void setMain_list(List<Category> main_list) {
        this.main_list = main_list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemCategoryBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(main_list.get(position));

    }

    @Override
    public int getItemCount() {
        return main_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemCategoryBinding binding;
        public ViewHolder(@NonNull ItemCategoryBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        public void onBind(Category category) {
            binding.titleCategory.setText(category.getCategoryName());
            Glide.with(itemView.getContext()).load(category.getCategoryImage()).into(binding.imageCategory);

            itemView.setOnClickListener(v -> {
                navController = Navigation.findNavController((Activity) itemView.getContext(), R.id.nav_host);
                Bundle bundle = new Bundle();
                bundle.putInt("categoryId", category.getId());
                navController.navigate(R.id.action_navigation_home_to_categoryFragment, bundle);
            });
        }

    }
}
