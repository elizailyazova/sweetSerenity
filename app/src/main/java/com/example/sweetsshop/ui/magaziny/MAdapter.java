package com.example.sweetsshop.ui.magaziny;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sweetsshop.databinding.ItemMapBinding;
import com.example.sweetsshop.models.Map;

import java.util.ArrayList;
import java.util.List;

public class MAdapter extends RecyclerView.Adapter<MAdapter.ViewHolder> {
    ItemMapBinding binding;
    List<Map> list_m = new ArrayList<>();
    public void setList_m(List<Map> list_m) {
        this.list_m = list_m;
    }

    @NonNull
    @Override
    public MAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemMapBinding
                .inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding);
    }
    @Override
    public void onBindViewHolder(@NonNull MAdapter.ViewHolder holder, int position) {
        holder.onBind(list_m.get(position));
    }

    @Override
    public int getItemCount() {
        return list_m.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        ItemMapBinding binding;

        public ViewHolder(@NonNull ItemMapBinding itemView) {
            super(itemView.getRoot());
            binding= itemView;
        }

        public void onBind(Map Map) {

            binding.title.setText(Map.getTitle());
            binding.description.setText(Map.getDescription());
            binding.distance.setText(Map.getDistance());
            binding.image.setImageResource(Map.getImageInt());

        }
    }
}
