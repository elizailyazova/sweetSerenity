package com.example.sweetsshop.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelM implements Parcelable {

    @SerializedName("id")
    @Expose
    private int modelId;

    @SerializedName("name")
    @Expose
    private String modelName;

    @SerializedName("category")
    @Expose
    private int category;

    @SerializedName("price")
    @Expose
    private Double modelPrice;

    @SerializedName("image")
    @Expose
    private String modelImage;

    @SerializedName("description")
    @Expose
    private String modelDescription;

    @SerializedName("formula")
    @Expose
    private String modelFormula;

    @SerializedName("nutrition")
    @Expose
    private String modelNutrition;

    @SerializedName("calories")
    @Expose
    private String modelCalories;

    @SerializedName("freshness_date")
    @Expose
    private String modelFreshnessDate;

    public ModelM(int modelId, String modelName, int category,
                  Double modelPrice, String modelImage, String modelDescription,
                  String modelFormula, String modelNutrition, String modelCalories,
                  String modelFreshnessDate) {
        this.modelId = modelId;
        this.modelName = modelName;
        this.category = category;
        this.modelPrice = modelPrice;
        this.modelImage = modelImage;
        this.modelDescription = modelDescription;
        this.modelFormula = modelFormula;
        this.modelNutrition = modelNutrition;
        this.modelCalories = modelCalories;
        this.modelFreshnessDate = modelFreshnessDate;
    }

    protected ModelM(Parcel in) {
        modelId = in.readInt();
        modelName = in.readString();
        category = in.readInt();
        if (in.readByte() == 0) {
            modelPrice = null;
        } else {
            modelPrice = in.readDouble();
        }
        modelImage = in.readString();
        modelDescription = in.readString();
        modelFormula = in.readString();
        modelNutrition = in.readString();
        modelCalories = in.readString();
        modelFreshnessDate = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(modelId);
        dest.writeString(modelName);
        dest.writeInt(category);
        if (modelPrice == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(modelPrice);
        }
        dest.writeString(modelImage);
        dest.writeString(modelDescription);
        dest.writeString(modelFormula);
        dest.writeString(modelNutrition);
        dest.writeString(modelCalories);
        dest.writeString(modelFreshnessDate);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ModelM> CREATOR = new Creator<ModelM>() {
        @Override
        public ModelM createFromParcel(Parcel in) {
            return new ModelM(in);
        }

        @Override
        public ModelM[] newArray(int size) {
            return new ModelM[size];
        }
    };


    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }


    public Double getModelPrice() {
        return modelPrice;
    }

    public void setModelPrice(Double modelPrice) {
        this.modelPrice = modelPrice;
    }

    public String getModelImage() {
        return modelImage;
    }

    public void setModelImage(String modelImage) {
        this.modelImage = modelImage;
    }

    public String getModelDescription() {
        return modelDescription;
    }

    public void setModelDescription(String modelDescription) {
        this.modelDescription = modelDescription;
    }

    public String getModelFormula() {
        return modelFormula;
    }

    public void setModelFormula(String modelFormula) {
        this.modelFormula = modelFormula;
    }

    public String getModelNutrition() {
        return modelNutrition;
    }

    public void setModelNutrition(String modelNutrition) {
        this.modelNutrition = modelNutrition;
    }

    public String getModelCalories() {
        return modelCalories;
    }

    public void setModelCalories(String modelCalories) {
        this.modelCalories = modelCalories;
    }

    public String getModelFreshnessDate() {
        return modelFreshnessDate;
    }

    public void setModelFreshnessDate(String modelFreshnessDate) {
        this.modelFreshnessDate = modelFreshnessDate;
    }


}
