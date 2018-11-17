package com.udacity.mregtej.mymealplanner.database;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.udacity.mregtej.mymealplanner.datamodel.RecipeIngredient;
import com.udacity.mregtej.mymealplanner.datamodel.RecipeNutritionalFact;
import com.udacity.mregtej.mymealplanner.datamodel.RecipeStep;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class PlannedRecipeConverters {

    static Gson gson = new Gson();

    @TypeConverter
    public static List<RecipeIngredient> stringToIngredientList(String ingredients) {
        if(ingredients == null) {
            return Collections.emptyList();
        }
        Type listType = new TypeToken<List<RecipeIngredient>>() {}.getType();
        return gson.fromJson(ingredients, listType);
    }

    @TypeConverter
    public static String ingredientListToString(List<RecipeIngredient> ingredients) {
        return gson.toJson(ingredients);
    }

    @TypeConverter
    public static List<RecipeNutritionalFact> stringToNutritionalFactList(String nutritionalFacts) {
        if(nutritionalFacts == null) {
            return Collections.emptyList();
        }
        Type listType = new TypeToken<List<RecipeNutritionalFact>>() {}.getType();
        return gson.fromJson(nutritionalFacts, listType);
    }

    @TypeConverter
    public static String nutritionalFactListToString(List<RecipeNutritionalFact> nutritionalFacts) {
        return gson.toJson(nutritionalFacts);
    }

    @TypeConverter
    public static List<RecipeStep> stringToStepList(String steps) {
        if(steps == null) {
            return Collections.emptyList();
        }
        Type listType = new TypeToken<List<RecipeStep>>() {}.getType();
        return gson.fromJson(steps, listType);
    }

    @TypeConverter
    public static String stepListToString(List<RecipeStep> steps) {
        return gson.toJson(steps);
    }

}
