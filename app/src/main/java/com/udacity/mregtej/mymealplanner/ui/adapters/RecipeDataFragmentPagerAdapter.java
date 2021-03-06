package com.udacity.mregtej.mymealplanner.ui.adapters;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.udacity.mregtej.mymealplanner.R;
import com.udacity.mregtej.mymealplanner.datamodel.Recipe;
import com.udacity.mregtej.mymealplanner.datamodel.RecipeIngredient;
import com.udacity.mregtej.mymealplanner.datamodel.RecipeNutritionalFact;
import com.udacity.mregtej.mymealplanner.datamodel.RecipeStep;
import com.udacity.mregtej.mymealplanner.ui.RecipeIngredientsFragment;
import com.udacity.mregtej.mymealplanner.ui.RecipeNutritionalFactsFragment;
import com.udacity.mregtej.mymealplanner.ui.RecipeStepsFragment;

import java.util.ArrayList;

public class RecipeDataFragmentPagerAdapter extends FragmentPagerAdapter {

    private static final String POSITION_KEY = "recipe-data-fragment-pager-position";

    /** Key for storing the recipe in savedInstanceState */
    private static final String RECIPE_KEY = "recipe";

    /** Key for passing the recipe steps to RecipeStepsFragment */
    private static final String RECIPE_STEP_LIST_KEY = "recipe-step-list";

    /** Key for passing the recipe servings to RecipeIngredientsFragment */
    private static final String RECIPE_SERVINGS_KEY = "recipe-servings";

    /** Key for passing the recipe ingredients to RecipeIngredientsFragment */
    private static final String RECIPE_INGREDIENTS_LIST_KEY = "recipe-ingredient-list";

    /** Key for passing the recipe nutritional facts to RecipeIngredientsFragment */
    private static final String RECIPE_NUTRITIONAL_FACTS_LIST_KEY = "recipe-nutritional-fact-list";

    private Recipe mRecipe;
    private static final int mNumScreenDataTitles = 3;
    private Context mContext;

    public RecipeDataFragmentPagerAdapter(FragmentManager fm, Context context, Recipe recipe) {
        super(fm);
        mContext = context;
        mRecipe = recipe;
    }

    @Override
    public int getCount() {
        return mNumScreenDataTitles;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment recipeDataFragment = null;
        Bundle args = new Bundle();
        switch (position) {
            case 0:
                recipeDataFragment = new RecipeIngredientsFragment();
                args.putParcelableArrayList(RECIPE_INGREDIENTS_LIST_KEY,
                        new ArrayList<RecipeIngredient>(mRecipe.getIngredients()));
                args.putInt(RECIPE_SERVINGS_KEY, mRecipe.getServings());
                break;
            case 1:
                recipeDataFragment = new RecipeNutritionalFactsFragment();
                args.putParcelableArrayList(RECIPE_NUTRITIONAL_FACTS_LIST_KEY,
                        new ArrayList<RecipeNutritionalFact>(mRecipe.getNutritionalFacts()));
                break;
            case 2:
            default:
                recipeDataFragment = new RecipeStepsFragment();
                args.putParcelableArrayList(RECIPE_STEP_LIST_KEY,
                        new ArrayList<RecipeStep>(mRecipe.getRecipeSteps()));
                break;
        }
        args.putInt(POSITION_KEY, position + 1);
        if(recipeDataFragment != null) {
            recipeDataFragment.setArguments(args);
        }
        return recipeDataFragment;
    }

    @Override public CharSequence getPageTitle (int position){
        return mContext.getResources().getStringArray(R.array.recipe_data_tab_titles)[position];
    }

}
