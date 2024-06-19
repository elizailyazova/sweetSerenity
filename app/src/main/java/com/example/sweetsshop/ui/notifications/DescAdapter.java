package com.example.sweetsshop.ui.notifications;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sweetsshop.databinding.DItemCardBinding;
import com.example.sweetsshop.models.ModelM;

import java.util.ArrayList;
import java.util.List;

public class DescAdapter extends RecyclerView.Adapter<DescAdapter.ViewHolder> {

    DItemCardBinding binding;
    Context context;
    List<ModelM> listD;

    public DescAdapter(Context context, List<ModelM> listD) {
        this.context = context;
        this.listD = listD;
    }
    public void setList(List<ModelM> list) {
        this.listD = list != null ? list : new ArrayList<>();
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = DItemCardBinding
                .inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(listD.get(position));
    }


    @Override
    public int getItemCount() {
        return listD != null ? listD.size() : 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull DItemCardBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        public void onBind(ModelM modelM) {
            binding.nameCard.setText(modelM.getModelName());
            Glide.with(itemView.getContext()).load(modelM.getModelImage()).into(binding.imageCard);
            binding.priceCard.setText(String.valueOf(modelM.getModelPrice()));
            binding.descriptionCard.setText(modelM.getModelDescription());
            binding.formula.setText(modelM.getModelFormula());
            binding.nutrition.setText(modelM.getModelNutrition());
            binding.calories.setText(modelM.getModelCalories());
            binding.freshnessDate.setText(modelM.getModelFreshnessDate());
        }
    }
}
