package com.example.sweetsshop.models;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Map implements Parcelable {
    long id;

    String title;
    String description;
    String distance;
    int imageInt;
    byte[] image;


    public Map(long id, String title, String description, String distance, byte[] image) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.distance = distance;
        this.image = image;
    }

    public Map(String title, String description, String distance, int imageInt) {
        this.title = title;
        this.description = description;
        this.distance = distance;
        this.imageInt = imageInt;
    }

    protected Map(Parcel in) {
        id = in.readLong();
        title = in.readString();
        description = in.readString();
        distance = in.readString();
        imageInt = in.readInt();
    }


    public static final Creator<Map> CREATOR = new Creator<Map>() {
        @Override
        public Map createFromParcel(Parcel in) {
            return new Map(in);
        }

        @Override
        public Map[] newArray(int size) {
            return new Map[size];
        }
    };



    @Override
    public int describeContents() {return 0;}

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeInt(imageInt);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public int getImageInt() {
        return imageInt;
    }

    public void setImageInt(int imageInt) {
        this.imageInt = imageInt;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }



}

