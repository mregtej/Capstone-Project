package com.udacity.mregtej.mymealplanner.datamodel;

import android.os.Parcel;
import android.os.Parcelable;

public class Recipe implements Parcelable {

    public Recipe(Parcel in) {}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel source) {
            return new Recipe(source);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

}

