package com.udacity.mregtej.mymealplanner.datamodel;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.udacity.mregtej.mymealplanner.provider.MyMealPlannerDBContract;

@Entity(tableName = MyMealPlannerDBContract.PlannedMealsEntry.TABLE_NAME,
        foreignKeys = @ForeignKey(entity = Recipe.class,
                parentColumns = MyMealPlannerDBContract.RecipeEntry.COLUMN_ID,
                childColumns = MyMealPlannerDBContract.PlannedMealsEntry.COLUMN_RECIPE_ID,
                onDelete = ForeignKey.CASCADE))
public class PlannedMeal implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = MyMealPlannerDBContract.PlannedMealsEntry.COLUMN_ID)
    private long id;
    @NonNull
    @ColumnInfo(name = MyMealPlannerDBContract.PlannedMealsEntry.COLUMN_DAY_OF_MONTH)
    private int dayOfMonth;
    @NonNull
    @ColumnInfo(name = MyMealPlannerDBContract.PlannedMealsEntry.COLUMN_MONTH)
    private int month;
    @NonNull
    @ColumnInfo(name = MyMealPlannerDBContract.PlannedMealsEntry.COLUMN_YEAR)
    private int year;
    @NonNull
    @ColumnInfo(name = MyMealPlannerDBContract.PlannedMealsEntry.COLUMN_MEALTIME)
    private String mealTime;
    @NonNull
    @ColumnInfo(name = MyMealPlannerDBContract.PlannedMealsEntry.COLUMN_RECIPE_ID)
    private String recipeID;

    public PlannedMeal() { }

    @Ignore
    public PlannedMeal(@NonNull int dayOfMonth, @NonNull int month,
                       @NonNull int year, @NonNull String mealTime, @NonNull String recipeID) {
        this.dayOfMonth = dayOfMonth;
        this.month = month;
        this.year = year;
        this.mealTime = mealTime;
        this.recipeID = recipeID;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeInt(this.dayOfMonth);
        dest.writeInt(this.month);
        dest.writeInt(this.year);
        dest.writeString(this.mealTime);
        dest.writeString(this.recipeID);
    }

    protected PlannedMeal(Parcel in) {
        this.id = in.readLong();
        this.dayOfMonth = in.readInt();
        this.month = in.readInt();
        this.year = in.readInt();
        this.mealTime = in.readString();
        this.recipeID = in.readString();
    }

    public static final Creator<PlannedMeal> CREATOR = new Creator<PlannedMeal>() {
        @Override
        public PlannedMeal createFromParcel(Parcel source) {
            return new PlannedMeal(source);
        }

        @Override
        public PlannedMeal[] newArray(int size) {
            return new PlannedMeal[size];
        }
    };

    @NonNull
    public long getId() {
        return id;
    }

    public void setId(@NonNull long id) {
        this.id = id;
    }

    @NonNull
    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(@NonNull int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    @NonNull
    public int getMonth() {
        return month;
    }

    public void setMonth(@NonNull int month) {
        this.month = month;
    }

    @NonNull
    public int getYear() {
        return year;
    }

    public void setYear(@NonNull int year) {
        this.year = year;
    }

    @NonNull
    public String getMealTime() {
        return mealTime;
    }

    public void setMealTime(@NonNull String mealTime) {
        this.mealTime = mealTime;
    }

    @NonNull
    public String getRecipeID() {
        return recipeID;
    }

    public void setRecipeID(@NonNull String recipeID) {
        this.recipeID = recipeID;
    }

}
