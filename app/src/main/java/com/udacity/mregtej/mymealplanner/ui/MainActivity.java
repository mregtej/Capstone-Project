package com.udacity.mregtej.mymealplanner.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.udacity.mregtej.mymealplanner.R;
import com.udacity.mregtej.mymealplanner.ui.utils.BottomNavigationViewHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final String FRAGMENT_NAME_SAVE_INSTANCE_KEY = "fragment-name";

    @BindView(R.id.fl_meal_screen_fragment_container)
    FrameLayout flMealScreenFragmentContainer;
    @BindView(R.id.bnv_app_navigation)
    BottomNavigationView bnvAppNavigation;
    private String sFragmentName;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.nav_meal_plans:
                    sFragmentName = getString(R.string.meal_plans_screen_title);
                    getSupportFragmentManager().
                            beginTransaction()
                            .replace(R.id.fl_meal_screen_fragment_container, new MealPlansFragment(),
                                    sFragmentName)
                            .commit();
                    return true;
                case R.id.nav_meal_planner:
                    sFragmentName = getString(R.string.meal_planner_screen_title);
                    getSupportFragmentManager().
                            beginTransaction()
                            .replace(R.id.fl_meal_screen_fragment_container, new MealPlannerFragment(),
                                    sFragmentName)
                            .commit();
                    return true;
                case R.id.nav_shopping_list:
                    return true;
                case R.id.nav_recipe_list:
                    return true;
                case R.id.nav_user_profile:
                    sFragmentName = getString(R.string.my_account_screen_title);
                    getSupportFragmentManager().
                            beginTransaction()
                            .replace(R.id.fl_meal_screen_fragment_container, new MyAccountFragment(),
                                    sFragmentName)
                            .commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // Build Bottom App Navigation Bar
        bnvAppNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BottomNavigationViewHelper.removeShiftMode(bnvAppNavigation);

        if (savedInstanceState == null) {
            // Load MealPlansFragment by default
            sFragmentName = getString(R.string.meal_plans_screen_title);
            getSupportFragmentManager().
                    beginTransaction()
                    .replace(R.id.fl_meal_screen_fragment_container, new MealPlansFragment(),
                            sFragmentName)
                    .commit();
        } else {
            sFragmentName = savedInstanceState.getString(FRAGMENT_NAME_SAVE_INSTANCE_KEY);
            getSupportFragmentManager().findFragmentByTag(sFragmentName);
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(FRAGMENT_NAME_SAVE_INSTANCE_KEY, sFragmentName);
        super.onSaveInstanceState(outState);
    }

}
