package com.udacity.mregtej.mymealplanner.ui.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.udacity.mregtej.mymealplanner.ui.MealMenuDayFragment;

public class MealMenuFragmentPagerAdapter extends FragmentPagerAdapter {
    private int mNumDays;
    private Context mContext;

    public MealMenuFragmentPagerAdapter(FragmentManager fm, Context context, int number_of_days) {
        super(fm);
        mContext = context;
        mNumDays = number_of_days;
    }

    @Override
    public int getCount() {
        return mNumDays;
    }

    @Override
    public Fragment getItem(int position) {
        MealMenuDayFragment mealMenuDayFragment = new MealMenuDayFragment();
        Bundle args = new Bundle();
        args.putInt(MealMenuDayFragment.POSITION_KEY, position + 1);
        mealMenuDayFragment.setArguments(args);
        return mealMenuDayFragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Day " + (position + 1);
    }
}
