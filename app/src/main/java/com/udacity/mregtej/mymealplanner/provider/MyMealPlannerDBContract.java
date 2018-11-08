package com.udacity.mregtej.mymealplanner.provider;

import android.provider.BaseColumns;

public class MyMealPlannerDBContract {

    /* RecipeEntry is an inner class that defines the contents of the Recipe table */
    public static final class RecipeEntry implements BaseColumns {

        /** The name of the Recipe table. */
        public static final String TABLE_NAME = "recipes";

        /** The name of the recipe ID column. */
        public static final String COLUMN_ID = "id";

        /** The name of the recipe category column. */
        public static final String COLUMN_CATEGORY = "category";

        /** The name of the recipe image url column. */
        public static final String COLUMN_IMAGE_URL = "imageUrl";

        /** The name of the recipe steps column. */
        public static final String COLUMN_TITLE = "title";

        /** The name of the recipe servings column. */
        public static final String COLUMN_SERVINGS = "servings";

        /** The name of the recipe author column. */
        public static final String COLUMN_AUTHOR = "author";

        /** The name of the recipe cooking time column. */
        public static final String COLUMN_COOKING_TIME = "cookingTime";

        /** The name of the recipe ingredients column. */
        public static final String COLUMN_INGREDIENTS = "ingredients";

        /** The name of the recipe nutritional facts column. */
        public static final String COLUMN_NUTRITIONAL_FACTS = "nutritionalFacts";

        /** The name of the recipe steps column. */
        public static final String COLUMN_RECIPE_STEPS = "recipeSteps";

    }

    /* RecipeEntry is an inner class that defines the contents of the Planned Meals table */
    public static final class PlannedMealsEntry implements BaseColumns {

        /** The name of the Planned Meals table. */
        public static final String TABLE_NAME = "plannedMeals";

        /** The name of the planned meal entry (id) column. */
        public static final String COLUMN_ID = "id";

        /** The name of the planned meal's day of month column. */
        public static final String COLUMN_DAY_OF_MONTH = "dayOfMonth";

        /** The name of the planned meal's month column. */
        public static final String COLUMN_MONTH = "month";

        /** The name of the planned meal's year column. */
        public static final String COLUMN_YEAR = "year";

        /** The name of the planned meal's mealtime column. */
        public static final String COLUMN_MEALTIME = "mealtime";

        /** The name of the planned meal's mealtime column. */
        public static final String COLUMN_RECIPE_ID = "recipeId";

    }

}
