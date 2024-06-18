package com.example.sweetsshop.ui.category;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sweetsshop.models.Category;
import com.example.sweetsshop.repositories.CategoryRepository;

import java.util.List;

public class CategoryViewModel extends ViewModel {
    private CategoryRepository categoryRepository;
    private LiveData<List<Category>> categoryListLiveData;

    public CategoryViewModel() {
        categoryRepository = new CategoryRepository();
        categoryListLiveData = categoryRepository.getCategories();
    }

    public LiveData<List<Category>> getCategoryListLiveData() {
        return categoryListLiveData;
    }
}
