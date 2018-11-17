package com.udacity.mregtej.mymealplanner.datamodel;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.udacity.mregtej.mymealplanner.database.PlannedRecipeConverters;
import com.udacity.mregtej.mymealplanner.provider.MyMealPlannerDBContract;

import java.util.List;

@Entity(tableName = MyMealPlannerDBContract.RecipeEntry.TABLE_NAME)
public class Recipe implements Parcelable {

    @PrimaryKey(autoGenerate = false)
    @NonNull
    @ColumnInfo(name = MyMealPlannerDBContract.RecipeEntry.COLUMN_ID)
    private String id;
    @ColumnInfo(name = MyMealPlannerDBContract.RecipeEntry.COLUMN_CATEGORY)
    private String category;
    @ColumnInfo(name = MyMealPlannerDBContract.RecipeEntry.COLUMN_IMAGE_URL)
    private String imageUrl;
    @NonNull
    @ColumnInfo(name = MyMealPlannerDBContract.RecipeEntry.COLUMN_TITLE)
    private String title;
    @NonNull
    @ColumnInfo(name = MyMealPlannerDBContract.RecipeEntry.COLUMN_SERVINGS)
    private int servings;
    @ColumnInfo(name = MyMealPlannerDBContract.RecipeEntry.COLUMN_AUTHOR)
    private String author;
    @NonNull
    @ColumnInfo(name = MyMealPlannerDBContract.RecipeEntry.COLUMN_COOKING_TIME)
    private String cookingTime;
    @TypeConverters(PlannedRecipeConverters.class)
    @NonNull
    @ColumnInfo(name = MyMealPlannerDBContract.RecipeEntry.COLUMN_INGREDIENTS)
    private List<RecipeIngredient> ingredients;
    @TypeConverters(PlannedRecipeConverters.class)
    @NonNull
    @ColumnInfo(name = MyMealPlannerDBContract.RecipeEntry.COLUMN_NUTRITIONAL_FACTS)
    private List<RecipeNutritionalFact> nutritionalFacts;
    @TypeConverters(PlannedRecipeConverters.class)
    @NonNull
    @ColumnInfo(name = MyMealPlannerDBContract.RecipeEntry.COLUMN_RECIPE_STEPS)
    private List<RecipeStep> recipeSteps;

    public Recipe() { }

    @Ignore
    public Recipe(String id, String category, String imageUrl, String title, int servings,
                  String author, String cookingTime, List<RecipeIngredient> ingredients,
                  List<RecipeNutritionalFact> nutritionalFacts, List<RecipeStep> recipeSteps) {
        this.id = id;
        this.category = category;
        this.imageUrl = imageUrl;
        this.title = title;
        this.servings = servings;
        this.author = author;
        this.cookingTime = cookingTime;
        this.ingredients = ingredients;
        this.nutritionalFacts = nutritionalFacts;
        this.recipeSteps = recipeSteps;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.category);
        dest.writeString(this.imageUrl);
        dest.writeString(this.title);
        dest.writeInt(this.servings);
        dest.writeString(this.author);
        dest.writeString(this.cookingTime);
        dest.writeList(this.ingredients);
        dest.writeList(this.nutritionalFacts);
        dest.writeList(this.recipeSteps);
    }

    protected Recipe(Parcel in) {
        this.id = in.readString();
        this.category = in.readString();
        this.imageUrl = in.readString();
        this.title = in.readString();
        this.servings = in.readInt();
        this.author = in.readString();
        this.cookingTime = in.readString();
        this.ingredients = in.readArrayList(RecipeIngredient.class.getClassLoader());
        this.nutritionalFacts = in.readArrayList(RecipeNutritionalFact.class.getClassLoader());
        this.recipeSteps = in.readArrayList(RecipeStep.class.getClassLoader());
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(String cookingTime) {
        this.cookingTime = cookingTime;
    }

    public List<RecipeIngredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<RecipeIngredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<RecipeNutritionalFact> getNutritionalFacts() {
        return nutritionalFacts;
    }

    public void setNutritionalFacts(List<RecipeNutritionalFact> nutritionalFacts) {
        this.nutritionalFacts = nutritionalFacts;
    }

    @NonNull
    public List<RecipeStep> getRecipeSteps() {
        return recipeSteps;
    }

    public void setRecipeSteps(@NonNull List<RecipeStep> recipeSteps) {
        this.recipeSteps = recipeSteps;
    }

}

