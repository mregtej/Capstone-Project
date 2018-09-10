package com.udacity.mregtej.mymealplanner.ui.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.udacity.mregtej.mymealplanner.R;
import com.udacity.mregtej.mymealplanner.ui.MealMenuDayFragment;
import com.udacity.mregtej.mymealplanner.ui.RecipeNutritionalFactsFragment;

public class RecipeDataFragmentPagerAdapter extends FragmentPagerAdapter {

    private static final int mNumScreenDataTitles = 3;
    private Context mContext;

    public RecipeDataFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    @Override
    public int getCount() {
        return mNumScreenDataTitles;
    }

    @Override
    public Fragment getItem(int position) {
        RecipeNutritionalFactsFragment recipeDataFragment = new RecipeNutritionalFactsFragment();
        Bundle args = new Bundle();
        args.putInt(MealMenuDayFragment.POSITION_KEY, position + 1);
        recipeDataFragment.setArguments(args);
        return recipeDataFragment;
    }

    @Override public CharSequence getPageTitle (int position){
        return mContext.getResources().getStringArray(R.array.recipe_data_tab_titles)[position];
    }

}