package com.udacity.mregtej.mymealplanner.ui.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;

import com.udacity.mregtej.mymealplanner.datamodel.Menu;
import com.udacity.mregtej.mymealplanner.ui.AddMealPlanToCalendarDialogFragment;
import com.udacity.mregtej.mymealplanner.ui.MealMenuDayFragment;

public class MealMenuFragmentPagerAdapter extends FragmentPagerAdapter
        implements MealMenuDayFragment.MealMenuDayFragmentListener {

    /** Key for passing the Meal Menu in the bundle */
    private static final String MEAL_MENU_KEY = "meal-menu";

    private FragmentManager mFragmentManager;
    private Menu mMenu;
    private int mNumDays;
    private Context mContext;

    public MealMenuFragmentPagerAdapter(FragmentManager fm, Context context, Menu menu) {
        super(fm);
        mFragmentManager = fm;
        mContext = context;
        mMenu = menu;
        mNumDays = menu.getMealDays().size();
    }

    @Override
    public int getCount() {
        return mNumDays;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment mealMenuDayFragment = MealMenuDayFragment.newInstance(mContext,this);
        Bundle args = new Bundle();
        args.putInt(MealMenuDayFragment.POSITION_KEY, position + 1);
        args.putParcelable(MealMenuDayFragment.MEAL_DAY_KEY, mMenu.getMealDays().get(position));
        mealMenuDayFragment.setArguments(args);
        return mealMenuDayFragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Day " + (position + 1);
    }

    @Override
    public void onAddMealPlanToCalendarClick() {

        // DialogFragment.show() will take care of adding the fragment
        // in a transaction.  We also want to remove any currently showing
        // dialog, so make our own transaction and take care of that here.
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        Fragment prev = mFragmentManager.findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        // Create and show the dialog.
        DialogFragment newFragment = AddMealPlanToCalendarDialogFragment.newInstance();
        Bundle bundle = new Bundle();
        bundle.putParcelable(MEAL_MENU_KEY, mMenu);
        newFragment.setArguments(bundle);
        ft.add(newFragment,"dialog");
        // TODO solve cannot perform this action after onSaveInstanceState
        ft.commitAllowingStateLoss();
    }

}
