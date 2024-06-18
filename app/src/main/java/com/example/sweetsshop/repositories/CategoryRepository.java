package com.example.sweetsshop.repositories;


import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sweetsshop.models.Category;
import com.example.sweetsshop.models.ModelM;
import com.example.sweetsshop.remote_data.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryRepository {
    private MutableLiveData<List<Category>> categoryData = new MutableLiveData<>();

    public LiveData<List<Category>> getCategories() {
        Call<List<Category>> apiCall = RetrofitClient.getInstance().getApi().getCategories();
        apiCall.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    categoryData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Log.e("CategoryRepository", "Failed to fetch categories: " + t.getMessage());
                categoryData.postValue(null);
            }
        });
        return categoryData;
    }
}
