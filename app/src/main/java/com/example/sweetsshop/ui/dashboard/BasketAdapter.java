package com.example.sweetsshop.ui.dashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sweetsshop.R;
import com.example.sweetsshop.databinding.ItemOrderBinding;
import com.example.sweetsshop.models.ModelM;
import com.example.sweetsshop.models.Order;
import com.example.sweetsshop.remote_data.RetrofitClient;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BasketAdapter extends RecyclerView.Adapter<BasketAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Order> orderList;
    private OnOrderChangeListener onOrderChangeListener;

    public BasketAdapter(Context context, ArrayList<Order> orderList, OnOrderChangeListener onOrderChangeListener) {
        this.context = context;
        this.orderList = orderList;
        this.onOrderChangeListener = onOrderChangeListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemOrderBinding binding = ItemOrderBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(orderList.get(position));
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemOrderBinding binding;

        public ViewHolder(@NonNull ItemOrderBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }

        public void onBind(Order order) {
            if (order.getDessert() == null) {
                Call<ModelM> apiCall = RetrofitClient.getInstance().getApi().getDessertById(order.getDessertId());
                apiCall.enqueue(new Callback<ModelM>() {
                    @Override
                    public void onResponse(Call<ModelM> call, Response<ModelM> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            order.setDessert(response.body());
                            bindData(order);
                            onOrderChangeListener.onOrderChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<ModelM> call, Throwable t) {
                    }
                });
            } else {
                bindData(order);
            }
        }
        private void bindData(Order order) {
            ModelM dessert = order.getDessert();
            if (dessert != null) {
                binding.nameProductCard.setText(dessert.getModelName());
                binding.priceCard.setText(String.valueOf(dessert.getModelPrice()));
                binding.tvQuantity.setText(String.valueOf(order.getQuantity()));

                Picasso.get().load(dessert.getModelImage())
                        .placeholder(R.drawable.place_holder).into(binding.imageCard);

                binding.btnIncrease.setOnClickListener(v -> {
                    int currentQuantity = order.getQuantity();
                    order.setQuantity(currentQuantity + 1);
                    binding.tvQuantity.setText(String.valueOf(order.getQuantity()));
                    onOrderChangeListener.onOrderChanged();
                });

                binding.btnDecrease.setOnClickListener(v -> {
                    int currentQuantity = order.getQuantity();
                    if (currentQuantity > 1) {
                        order.setQuantity(currentQuantity - 1);
                        binding.tvQuantity.setText(String.valueOf(order.getQuantity()));
                        onOrderChangeListener.onOrderChanged();
                    }
                });
            }
        }
    }

    public interface OnOrderChangeListener {
        void onOrderChanged();
    }
}
