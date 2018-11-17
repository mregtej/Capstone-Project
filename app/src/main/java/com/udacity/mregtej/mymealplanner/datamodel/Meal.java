package com.udacity.mregtej.mymealplanner.datamodel;

import android.os.Parcel;
import android.os.Parcelable;

public class Meal implements Parcelable {

    private String mealTime;
    private String recipeId;

    public Meal(String mealTime, String recipeID) {
        this.mealTime = mealTime;
        this.recipeId = recipeID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mealTime);
        dest.writeString(this.recipeId);
    }

    public Meal() {
    }

    protected Meal(Parcel in) {
        this.mealTime = in.readString();
        this.recipeId = in.readString();
    }

    public static final Creator<Meal> CREATOR = new Creator<Meal>() {
        @Override
        public Meal createFromParcel(Parcel source) {
            return new Meal(source);
        }

        @Override
        public Meal[] newArray(int size) {
            return new Meal[size];
        }
    };

    public String getMealTime() {
        return mealTime;
    }

    public void setMealTime(String mealTime) {
        this.mealTime = mealTime;
    }

    public String getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(String recipeId) {
        this.recipeId = recipeId;
    }

}
