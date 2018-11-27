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
import com.udacity.mregtej.mymealplanner.datamodel.Recipe;
import com.udacity.mregtej.mymealplanner.provider.MyMealPlannerDBContract;

import java.util.List;

@Dao
public interface PlannedRecipeDao {

    @Query("SELECT * FROM " + MyMealPlannerDBContract.RecipeEntry.TABLE_NAME)
    LiveData<List<Recipe>> getPlannedRecipes();

    @Query("SELECT * FROM " + MyMealPlannerDBContract.RecipeEntry.TABLE_NAME)
    Cursor getPlannedRecipesViaCP();

    @Query("SELECT * FROM " + MyMealPlannerDBContract.RecipeEntry.TABLE_NAME +
            " WHERE " + MyMealPlannerDBContract.RecipeEntry.COLUMN_ID + " = :id")
    Cursor findPlannedRecipeByIdViaCP(long id);

    @Query("SELECT COUNT(*) FROM " + MyMealPlannerDBContract.RecipeEntry.TABLE_NAME)
    int countPlannedRecipes();

    @Update
    int updatePlannedRecipe(Recipe recipe);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertPlannedRecipe(Recipe recipe);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertPlannedRecipes(List<Recipe> recipes);

    @Delete
    void deletePlannedRecipe(Recipe recipe);

    @Query("DELETE FROM " + MyMealPlannerDBContract.RecipeEntry.TABLE_NAME +
            " WHERE " + MyMealPlannerDBContract.RecipeEntry.COLUMN_ID + " = :id")
    int deletePlannedRecipeById(long id);

}
