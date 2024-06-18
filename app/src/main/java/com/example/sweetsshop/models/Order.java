package com.example.sweetsshop.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Order implements Parcelable {

    @SerializedName("dessert")
    @Expose
    private int dessertId;

    @SerializedName("quantity")
    @Expose
    private int quantity;

    @SerializedName("branch")
    @Expose
    private int branchId;

    @SerializedName("user")
    @Expose
    private int userId;


    public Order(int dessertId, int quantity, int branchId, int userId) {
        this.dessertId = dessertId;
        this.quantity = quantity;
        this.branchId = branchId;
        this.userId = userId;
    }

    protected Order(Parcel in) {
        dessertId = in.readInt();
        quantity = in.readInt();
        branchId = in.readInt();
        userId = in.readInt();
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(dessertId);
        dest.writeInt(quantity);
        dest.writeInt(branchId);
        dest.writeInt(userId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };

    public int getDessertId() {
        return dessertId;
    }

    public void setDessertId(int dessertId) {
        this.dessertId = dessertId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
