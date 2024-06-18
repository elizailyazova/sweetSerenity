package com.example.sweetsshop.ui.dashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sweetsshop.R;
import com.example.sweetsshop.databinding.ItemOrderBinding;
import com.example.sweetsshop.models.ModelM;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BasketAdapter extends RecyclerView.Adapter<BasketAdapter.ViewHolder> {

    ItemOrderBinding binding;
    Context context;
    ArrayList<ModelM> nn_list;

    public BasketAdapter(Context context, ArrayList<ModelM> nn_list) {
        this.context = context;
        this.nn_list = nn_list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemOrderBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(nn_list.get(position));
    }

    @Override
    public int getItemCount() {
        return nn_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemOrderBinding binding;

        public ViewHolder(@NonNull ItemOrderBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }

        public void onBind(ModelM modelM) {
            binding.nameProductCard.setText(modelM.getModelName());
            binding.priceCard.setText(String.valueOf(modelM.getModelPrice()));
            Picasso.get().load(modelM.getModelImage())
                    .placeholder(R.drawable.place_holder_my).into(binding.imageCard);
        }
    }
}
