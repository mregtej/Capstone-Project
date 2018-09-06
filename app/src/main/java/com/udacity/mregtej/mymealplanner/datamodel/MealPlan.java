package com.udacity.mregtej.mymealplanner.datamodel;

import android.os.Parcel;
import android.os.Parcelable;

public class MealPlan implements Parcelable {

    public MealPlan(Parcel in) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    public static final Creator<MealPlan> CREATOR = new Creator<MealPlan>() {
        @Override
        public MealPlan createFromParcel(Parcel source) {
            return new MealPlan(source);
        }

        @Override
        public MealPlan[] newArray(int size) {
            return new MealPlan[size];
        }
    };

}

