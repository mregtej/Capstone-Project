package com.udacity.mregtej.mymealplanner.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.database.Cursor;

import com.udacity.mregtej.mymealplanner.datamodel.PlannedMeal;
import com.udacity.mregtej.mymealplanner.provider.MyMealPlannerDBContract;

import java.util.List;

@Dao
public interface PlannedMealDao {
    @Query("SELECT * FROM " + MyMealPlannerDBContract.PlannedMealsEntry.TABLE_NAME)
    List<PlannedMeal> getPlannedMeals();

    @Query("SELECT * FROM " + MyMealPlannerDBContract.PlannedMealsEntry.TABLE_NAME)
    Cursor getPlannedMealsViaCP();

    @Query("SELECT * FROM " + MyMealPlannerDBContract.PlannedMealsEntry.TABLE_NAME +
            " WHERE " + MyMealPlannerDBContract.PlannedMealsEntry.COLUMN_DAY_OF_MONTH + " = :dayOfMonth"
            + " AND " + MyMealPlannerDBContract.PlannedMealsEntry.COLUMN_MONTH + " = :month"
            + " AND " + MyMealPlannerDBContract.PlannedMealsEntry.COLUMN_YEAR + " = :year")
    LiveData<List<PlannedMeal>> findPlannedMealsByDate(int year, int month, int dayOfMonth);

    @Query("SELECT * FROM " + MyMealPlannerDBContract.PlannedMealsEntry.TABLE_NAME +
            " WHERE " + MyMealPlannerDBContract.PlannedMealsEntry.COLUMN_DAY_OF_MONTH + " = :dayOfMonth"
            + " AND " + MyMealPlannerDBContract.PlannedMealsEntry.COLUMN_MONTH + " = :month"
            + " AND " + MyMealPlannerDBContract.PlannedMealsEntry.COLUMN_YEAR + " = :year")
    Cursor findPlannedMealsByDateViaCP(int year, int month, int dayOfMonth);

    @Query("SELECT * FROM " + MyMealPlannerDBContract.PlannedMealsEntry.TABLE_NAME +
            " WHERE " + MyMealPlannerDBContract.PlannedMealsEntry.COLUMN_DAY_OF_MONTH + " = :dayOfMonth"
            + " AND " + MyMealPlannerDBContract.PlannedMealsEntry.COLUMN_MONTH + " = :month"
            + " AND " + MyMealPlannerDBContract.PlannedMealsEntry.COLUMN_YEAR + " = :year"
            + " AND " + MyMealPlannerDBContract.PlannedMealsEntry.COLUMN_MEALTIME + " = :mealtime")
    LiveData<List<PlannedMeal>> findPlannedMealsByDateAndMealtime(int year, int month, int dayOfMonth,
                                                                  String mealtime);

    @Query("SELECT * FROM " + MyMealPlannerDBContract.PlannedMealsEntry.TABLE_NAME +
            " WHERE " + MyMealPlannerDBContract.PlannedMealsEntry.COLUMN_DAY_OF_MONTH + " = :dayOfMonth"
            + " AND " + MyMealPlannerDBContract.PlannedMealsEntry.COLUMN_MONTH + " = :month"
            + " AND " + MyMealPlannerDBContract.PlannedMealsEntry.COLUMN_YEAR + " = :year"
            + " AND " + MyMealPlannerDBContract.PlannedMealsEntry.COLUMN_MEALTIME + " = :mealtime")
    Cursor findPlannedMealsByDateAndMealtimeViaCP(int year, int month, int dayOfMonth,
                                                  String mealtime);

    @Query("SELECT COUNT(*) FROM " + MyMealPlannerDBContract.PlannedMealsEntry.TABLE_NAME)
    int countPlannedMeals();

    @Update
    int updatePlannedMeal(PlannedMeal plannedMeal);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertPlannedMeal(PlannedMeal plannedMeal);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertPlannedMeals(List<PlannedMeal> plannedMeals);

    @Delete
    void deletePlannedMeal(PlannedMeal plannedMeal);

    @Query("DELETE FROM " + MyMealPlannerDBContract.PlannedMealsEntry.TABLE_NAME +
            " WHERE " + MyMealPlannerDBContract.PlannedMealsEntry.COLUMN_DAY_OF_MONTH + " = :dayOfMonth"
            + " AND " + MyMealPlannerDBContract.PlannedMealsEntry.COLUMN_MONTH + " = :month"
            + " AND " + MyMealPlannerDBContract.PlannedMealsEntry.COLUMN_YEAR + " = :year")
    int deletePlannedMealsByDate(int year, int month, int dayOfMonth);

    @Query("DELETE FROM " + MyMealPlannerDBContract.PlannedMealsEntry.TABLE_NAME +
            " WHERE " + MyMealPlannerDBContract.PlannedMealsEntry.COLUMN_DAY_OF_MONTH + " = :dayOfMonth"
            + " AND " + MyMealPlannerDBContract.PlannedMealsEntry.COLUMN_MONTH + " = :month"
            + " AND " + MyMealPlannerDBContract.PlannedMealsEntry.COLUMN_YEAR + " = :year"
            + " AND " + MyMealPlannerDBContract.PlannedMealsEntry.COLUMN_MEALTIME + " = :mealtime")
    int deletePlannedMealsByDateAndMealtime(int year, int month, int dayOfMonth, String mealtime);

}
