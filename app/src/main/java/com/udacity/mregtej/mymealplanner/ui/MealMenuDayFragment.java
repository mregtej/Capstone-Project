package com.udacity.mregtej.mymealplanner.ui;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.udacity.mregtej.mymealplanner.R;
import com.udacity.mregtej.mymealplanner.datamodel.Recipe;
import com.udacity.mregtej.mymealplanner.global.MyMealPlannerGlobals;
import com.udacity.mregtej.mymealplanner.ui.adapters.MealMenuDayMealtimeAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MealMenuDayFragment extends Fragment implements
        MealMenuDayMealtimeAdapter.MealMenuDayMealtimeClickListener {

    public static final String POSITION_KEY = "meal-menu-day-fragment-position";

    /* Key for storing the list state in savedInstanceState */
    private static final String MEAL_MENU_DAY_RECIPE_LIST_STATE_KEY = "meal-menu-day-recipe-list-state";

    /**
     * Key for storing the meal-day recipes to buy in savedInstanceState
     */
    private static final String MEAL_MENU_DAY_RECIPE_LIST_KEY = "meal-menu-day-recipe-list";

    Unbinder unbinder;
    Context mContext;
    ActionBar actionBar;

    @BindView(R.id.rv_meal_menu_day_data)
    RecyclerView rvMealMenuDayData;
    @BindView(R.id.fab_meal_menu_day_schedule)
    FloatingActionButton fabMealMenuDaySchedule;

    /**
     * RecyclerView LayoutManager instance
     */
    private RecyclerView.LayoutManager mMealPlannerMealtimeLayoutManager;
    /**
     * Meal-day Recipes Custom ArrayAdapter
     */
    private MealMenuDayMealtimeAdapter mMealPlannerMealtimeAdapter;
    /**
     * List state stored in savedInstanceState
     */
    private Parcelable mListStateMealPlannerMealtime;


    public MealMenuDayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_meal_menu_day, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        mContext = getActivity();

        // Load & set GridLayout
        rvMealMenuDayData.setHasFixedSize(true);
        setRecyclerViewLayoutManager(rootView);

        if (savedInstanceState != null) {

            List<Recipe> mealDayRecipes = savedInstanceState.
                    getParcelableArrayList(MEAL_MENU_DAY_RECIPE_LIST_KEY);
            mMealPlannerMealtimeAdapter = new MealMenuDayMealtimeAdapter(mealDayRecipes, this);
            rvMealMenuDayData.setAdapter(mMealPlannerMealtimeAdapter);
            mMealPlannerMealtimeAdapter.notifyDataSetChanged();

        } else {

            ArrayList<Recipe> mealDayRecipes = new ArrayList<>();
            // TODO Fill in
            mealDayRecipes.add(null);
            mealDayRecipes.add(null);
            mealDayRecipes.add(null);
            mealDayRecipes.add(null);
            mMealPlannerMealtimeAdapter = new MealMenuDayMealtimeAdapter(mealDayRecipes, this);
            // Set Adapter and notifyDataSetChanged
            rvMealMenuDayData.setAdapter(mMealPlannerMealtimeAdapter);
            mMealPlannerMealtimeAdapter.notifyDataSetChanged();

        }

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.fab_meal_menu_day_schedule})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fab_meal_menu_day_schedule:
                break;
        }
    }

    private void setRecyclerViewLayoutManager(View rootView) {
        switch (this.getResources().getConfiguration().orientation) {
            case MyMealPlannerGlobals.LANDSCAPE_VIEW: // Landscape Mode
                mMealPlannerMealtimeLayoutManager = new GridLayoutManager(rootView.getContext(),
                        MyMealPlannerGlobals.MEAL_MENU_DAY_RECIPE_GV_LAND_COLUMN_NUMB);
                break;
            case MyMealPlannerGlobals.PORTRAIT_VIEW: // Portrait Mode
            default:
                mMealPlannerMealtimeLayoutManager = new GridLayoutManager(rootView.getContext(),
                        MyMealPlannerGlobals.MEAL_MENU_DAY_RECIPE_GV_PORT_COLUMN_NUMB);
                break;
        }
        rvMealMenuDayData.setLayoutManager(mMealPlannerMealtimeLayoutManager);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        mListStateMealPlannerMealtime = mMealPlannerMealtimeLayoutManager.onSaveInstanceState();
        outState.putParcelable(MEAL_MENU_DAY_RECIPE_LIST_KEY, mListStateMealPlannerMealtime);
        outState.putParcelableArrayList(MEAL_MENU_DAY_RECIPE_LIST_KEY, (ArrayList<Recipe>)
                mMealPlannerMealtimeAdapter.getmMealDayRecipeList());

    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            mListStateMealPlannerMealtime = savedInstanceState.getParcelable(MEAL_MENU_DAY_RECIPE_LIST_STATE_KEY);
        }
    }

    @Override
    public void onMealMenuDayMealtimeClick(int position) {
        Intent intent = new Intent(mContext, RecipeActivity.class);
        // TODO Add extras
        startActivity(intent);
    }

}
