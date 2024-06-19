package com.example.sweetsshop.ui.home;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sweetsshop.R;
import com.example.sweetsshop.databinding.ItemProductBinding;
import com.example.sweetsshop.models.ModelM;
import com.example.sweetsshop.models.Order;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private Context context;
    private List<ModelM> list;
    private ArrayList<Order> selected_BasketList = new ArrayList<>();
    private NavController navController;

    public SearchAdapter(Context context, List<ModelM> list) {
        this.context = context;
        this.list = list;
    }

    public ArrayList<Order> getSelected_BasketList() {
        return selected_BasketList;
    }

    public void setList(List<ModelM> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void updateList(List<ModelM> newList) {
        list.clear();
        list.addAll(newList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemProductBinding binding = ItemProductBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ItemProductBinding binding;

        public ViewHolder(@NonNull ItemProductBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(ModelM modelM) {
            binding.nameProductCard.setText(modelM.getModelName());
            binding.priceCard.setText(String.valueOf(modelM.getModelPrice()));
            binding.descriptionCard.setText(modelM.getModelDescription());
            Picasso.get().load(modelM.getModelImage()).into(binding.imageCard);

            binding.btnZoom.setOnClickListener(v -> {
                ArrayList<ModelM> desc_list = new ArrayList<>();
                desc_list.add(modelM);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("see_more", desc_list);
                navController = Navigation.findNavController((Activity) context, R.id.nav_host);
                navController.navigate(R.id.navigation_description, bundle);
            });

        }
    }
}
