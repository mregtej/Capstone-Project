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

}
