package com.example.sweetsshop.ui.home;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
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

public class JemAdapter extends RecyclerView.Adapter<JemAdapter.ViewHolder> {

    ItemProductBinding binding;
    Context context;
    List<ModelM> list;
    ArrayList<Order> selected_BasketList = new ArrayList<>();
    NavController navController;

    public JemAdapter() {
    }

    public JemAdapter(Context context, List<ModelM> list) {
        this.context = context;
        this.list = list;
    }

    public ArrayList<Order> getSelected_BasketList() {
        return selected_BasketList;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setList(List<ModelM> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemProductBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemProductBinding binding;

        public ViewHolder(@NonNull ItemProductBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }

        public void onBind(ModelM modelM) {
            binding.nameProductCard.setText(modelM.getModelName());
            binding.priceCard.setText(String.valueOf(modelM.getModelPrice()));
            binding.descriptionCard.setText(modelM.getModelDescription());
            Picasso.get().load(modelM.getModelImage()).into(binding.imageCard);
            binding.btnZoom.setOnClickListener(v -> {
                Bundle bundle = new Bundle();
                ArrayList<ModelM> desc_list = new ArrayList<>();
                desc_list.add(modelM);
                bundle.putParcelableArrayList("see_more", desc_list);
                navController = Navigation.findNavController((Activity) itemView.getContext(), R.id.nav_host);
                navController.navigate(R.id.navigation_description, bundle);
                Log.e("TAG", "pass data to description ! ! ! ");
            });

            itemView.setOnClickListener(v1 -> {
                Order order = new Order(modelM.getModelId(), 1, 1, 1);
                if (binding.tovarFavoriteCheck.getVisibility() == View.INVISIBLE) {
                    binding.tovarFavoriteCheck.setVisibility(View.VISIBLE);
                    selected_BasketList.add(order);
                } else {
                    binding.tovarFavoriteCheck.setVisibility(View.INVISIBLE);
                    selected_BasketList.remove(order);
                }
            });
        }
    }
}
