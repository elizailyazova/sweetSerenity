package com.example.sweetsshop.remote_data;

import com.example.sweetsshop.models.*;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface API {

    @GET("dessert/all")
    Call<List<ModelM>> getStoreProducts();

    @POST("dessert/create/")
    Call<ModelM> createNewProduct();

    @GET("dessert/{id}/")
    Call<ModelM> getProductById();

    @PUT("dessert/{id}/")
    Call<ModelM> updateProductById();

    @DELETE("dessert/{id}/")
    Call<ModelM> deleteProductById();

    @PATCH("dessert/{id}/")
    Call<ModelM> changeProductById();

    @POST("user/register")
    Call<User> registrationNewUser(@Body User user);

    @POST("user/login")
    Call<LoginResponse> checkLoginUser(@Body CurrentUser currentUser);

    @POST("user/logout")
    Call<User> makeLogOutUser();

    @POST("order/create/")
    Call<Order> createNewOrder(@Body Order order);

    @GET("order/all")
    Call<List<Order>> getAllOrders();

    @GET("branch/all")
    Call<List<String>> getBranches();

    @GET("category/all")
    Call<List<Category>> getCategories();

    @GET("category/dessert/{category}")
    Call<List<ModelM>> getDessertByCategory(@Path("category") int categoryId);

}
