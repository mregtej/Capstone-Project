package com.udacity.mregtej.mymealplanner.datamodel;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class MealDay implements Parcelable {

    private long id;
    private ArrayList<Meal> meals;

    public MealDay() { }

    public MealDay(long id, ArrayList<Meal> meals) {
        this.id = id;
        this.meals = meals;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ArrayList<Meal> getMeals() {
        return meals;
    }

    public void setMeals(ArrayList<Meal> meals) {
        this.meals = meals;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeTypedList(this.meals);
    }

    protected MealDay(Parcel in) {
        this.id = in.readLong();
        this.meals = in.createTypedArrayList(Meal.CREATOR);
    }

    public static final Creator<MealDay> CREATOR = new Creator<MealDay>() {
        @Override
        public MealDay createFromParcel(Parcel source) {
            return new MealDay(source);
        }

        @Override
        public MealDay[] newArray(int size) {
            return new MealDay[size];
        }
    };

}
