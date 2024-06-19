package com.example.sweetsshop.ui.payment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.airbnb.lottie.LottieAnimationView;
import com.example.sweetsshop.R;
import com.example.sweetsshop.databinding.FragmentPaymentBinding;
import com.example.sweetsshop.models.Order;
import com.example.sweetsshop.remote_data.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PaymentFragment extends Fragment {
    FragmentPaymentBinding binding;
    NavController navController;
    LottieAnimationView lotty;


    public PaymentFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPaymentBinding.
                inflate(inflater, container, false);
        View root = binding.getRoot();
        lotty = binding.lotty;
        lotty.setAnimation(R.raw.lotty);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.cardVisa.setOnClickListener(v->{
            Intent myIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://visa.com/"));
            startActivity(myIntent);
        });

        binding.cardPaypal.setOnClickListener(v1->{
            Intent myIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://paypal.com/"));
            startActivity(myIntent);
        });

        binding.cardMBank.setOnClickListener(v2 -> {
            Intent myIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=com.maanavan.mb_kyrgyzstan&hl=ru&pli=1"));
            startActivity(myIntent);
        });

        binding.cardODengi.setOnClickListener(v4 -> {
            Intent myIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=kg.o.nurtelecom&hl=ru"));
            startActivity(myIntent);
        });

        binding.btnBack.setOnClickListener(v5-> {
            navController = Navigation.findNavController(requireActivity(),
                    R.id.nav_host);
            navController.navigate(R.id.action_navigation_payment_to_navigation_home);
        });
        binding.btnPay.setOnClickListener(v5-> {
            navController = Navigation.findNavController(requireActivity(),
                    R.id.nav_host);
            navController.navigate(R.id.action_navigation_payment_to_successFragment);
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}