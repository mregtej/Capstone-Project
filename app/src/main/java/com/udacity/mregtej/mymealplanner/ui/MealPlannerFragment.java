package com.udacity.mregtej.mymealplanner.ui;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import com.udacity.mregtej.mymealplanner.R;
import com.udacity.mregtej.mymealplanner.datamodel.PlannedMeal;
import com.udacity.mregtej.mymealplanner.datamodel.Recipe;
import com.udacity.mregtej.mymealplanner.ui.adapters.MealMenuDayMealtimeAdapter;
import com.udacity.mregtej.mymealplanner.ui.adapters.MealPlannerMealtimeAdapter;
import com.udacity.mregtej.mymealplanner.ui.utils.DateUtils;
import com.udacity.mregtej.mymealplanner.viewmodel.PlannedMealViewModel;
import com.udacity.mregtej.mymealplanner.viewmodel.PlannedRecipeViewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MealPlannerFragment extends Fragment
        implements MealPlannerMealtimeAdapter.MealPlannerMealtimeClickListener {

    /** Key for storing the list state in savedInstanceState */
    private static final String MEAL_DAY_LIST_STATE_KEY = "meal-day-list-state";

    /** Key for storing the meal-days to buy in savedInstanceState */
    private static final String MEAL_DAY_LIST_KEY = "meal-day-list";

    private static final String YEAR_KEY = "year";
    private static final String MONTH_KEY = "month";
    private static final String DAY_KEY = "day";

    @BindView(R.id.cv_meal_planner_calendar)
    CalendarView cvMealPlannerCalendarView;
    @BindView(R.id.tv_meal_planner_date)
    TextView tvMealPlannerDate;
    @BindView(R.id.rv_cv_meal_planner_mealtime_data)
    RecyclerView rvCvMealPlannerMealtimeData;

    Unbinder unbinder;
    Context mContext;
    ActionBar actionBar;

    private PlannedMealViewModel mPlannedMealViewModel;
    private PlannedRecipeViewModel mPlannedRecipeViewModel;

    private HashMap<String,Recipe> mMealDayList;
    private List<PlannedMeal> mPlannedMealList;
    private List<Recipe> mPlannedRecipeList;

    private int mYear;
    private int mMonth;
    private int mDayOfMonth;

    private boolean hasPlannedMealsRetrieved = false;
    private boolean hasPlannedRecipesRetrieved = false;

    /**
     * RecyclerView LayoutManager instance
     */
    private RecyclerView.LayoutManager mMealPlannerMealtimeLayoutManager;
    /**
     * Meal-day Recipes Custom ArrayAdapter
     */
    private MealPlannerMealtimeAdapter mMealPlannerMealtimeAdapter;
    /**
     * List state stored in savedInstanceState
     */
    private Parcelable mListStateMealPlannerMealtime;

    public MealPlannerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();

        // Set ActionBar Title
        setActionBarTitle(getString(R.string.meal_planner_screen_title));

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_meal_planner, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        // Set Calendar
        setCalendar(rootView);

        // Load & set GridLayout
        rvCvMealPlannerMealtimeData.setHasFixedSize(true);
        setRecyclerViewLayoutManager(rootView);

        // Subscribe MealPlansFragment to receive notifications from PlannedMealViewModel
        registerToPlannedMealViewModel();

        // Subscribe MealPlansFragment to receive notifications from PlannedRecipesViewModel
        registerToPlannedRecipeViewModel();

        // Enable notifications for receiving planned meals from PlannedMealViewModel
        enableNotificationsFromPlannedMealViewModel();

        // Enable notifications for receiving planned recipes from PlannedRecipesViewModel
        enableNotificationsFromPlannedRecipeViewModel();

        if (savedInstanceState != null) {

            mYear = savedInstanceState.getInt(YEAR_KEY);
            mMonth = savedInstanceState.getInt(MONTH_KEY);
            mDayOfMonth = savedInstanceState.getInt(DAY_KEY);

            LinkedHashMap<String,Recipe> mealDayList =
                    (LinkedHashMap<String,Recipe>) savedInstanceState.getSerializable(MEAL_DAY_LIST_KEY);
            mMealPlannerMealtimeAdapter = new MealPlannerMealtimeAdapter(mealDayList, this);
            rvCvMealPlannerMealtimeData.setAdapter(mMealPlannerMealtimeAdapter);
            mMealPlannerMealtimeAdapter.notifyDataSetChanged();

            // Update Date on Calendar View
            updateCalendarView();

        } else {

            // Set Current Date on Calendar View
            setCurrentDateOnCalendarView();

        }

        // Set-Up Calendar listeners
        setCalendarViewListeners();

        // Return fragment view
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    private void setActionBarTitle(String title) {
        actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(title);
            if (!actionBar.isShowing()) {
                actionBar.show();
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    // TODO Implement Calendar
    private void setCalendar(View view) {

    }

    private void setRecyclerViewLayoutManager(View rootView) {
        mMealPlannerMealtimeLayoutManager = new GridLayoutManager(rootView.getContext(),
                1, GridLayoutManager.VERTICAL, false);
        rvCvMealPlannerMealtimeData.setLayoutManager(mMealPlannerMealtimeLayoutManager);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(YEAR_KEY, mYear);
        outState.putInt(MONTH_KEY, mMonth);
        outState.putInt(DAY_KEY, mDayOfMonth);
        mListStateMealPlannerMealtime = mMealPlannerMealtimeLayoutManager.onSaveInstanceState();
        outState.putParcelable(MEAL_DAY_LIST_STATE_KEY, mListStateMealPlannerMealtime);
        outState.putSerializable(MEAL_DAY_LIST_KEY, mMealPlannerMealtimeAdapter.
                getmMealDayRecipeList());
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            mListStateMealPlannerMealtime = savedInstanceState
                    .getParcelable(MEAL_DAY_LIST_STATE_KEY);
        }
    }

    @Override
    public void onMealPlannerMealtimeClick(int position) {

    }

    private void setCurrentDateOnCalendarView() {
        // Get current date
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(calendar.getTime());
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        // Update text-string date
        tvMealPlannerDate.setText(DateUtils.formatDate(mYear,mMonth,mDayOfMonth,mContext));
    }

    private void setCalendarViewListeners() {
        // Set OnDateChangeListener (user chooses a different date)
        cvMealPlannerCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month,
                                            int dayOfMonth) {
                // Store date
                mYear = year;
                mMonth = month;
                mDayOfMonth = dayOfMonth;
                // Show meal plan for that day (in case it has already been planned)
                showMealPlan();
                // Update text-string date
                tvMealPlannerDate.setText(DateUtils.formatDate(mYear,mMonth,mDayOfMonth,mContext));
            }
        });
    }

    private void updateCalendarView() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(mYear, mMonth, mDayOfMonth, 0, 0);
        cvMealPlannerCalendarView.setDate(calendar.getTimeInMillis());
    }

    private void showMealPlan() {

        // TODO Min API: 19 - Look for alternatives
        LinkedHashMap<String,Recipe> mealRecipeList = new LinkedHashMap<>();

        // Add planned meals for the choosen day
        if(mPlannedMealList != null && !mPlannedMealList.isEmpty()) {
            for(PlannedMeal plannedMeal : mPlannedMealList) {
                if (plannedMeal.getDayOfMonth() == mDayOfMonth
                        && plannedMeal.getMonth() == mMonth
                        && plannedMeal.getYear() == mYear) {
                    // Look for planned recipe using recipeId
                    if(mPlannedRecipeList != null && !mPlannedRecipeList.isEmpty()) {
                        for (Recipe plannedRecipe : mPlannedRecipeList) {
                            if (plannedMeal.getRecipeID().equals(plannedRecipe.getId())) {
                                // Add planned recipe for the choosen day and mealtime
                                mealRecipeList.put(plannedMeal.getMealTime(), plannedRecipe);
                                continue;
                            }
                        }
                    }
                }
            }
        }

        // Populate Mealtime Plan Adapter
        if(mealRecipeList.size() == 0) { return; }
        mMealPlannerMealtimeAdapter = new MealPlannerMealtimeAdapter(mealRecipeList, this);
        rvCvMealPlannerMealtimeData.setAdapter(mMealPlannerMealtimeAdapter);
        mMealPlannerMealtimeAdapter.notifyDataSetChanged();

    }

    //--------------------------------------------------------------------------------|
    //                               DataModel Methods                                |
    //--------------------------------------------------------------------------------|

    /**
     * Registers the View into PlannedMeal ViewModel to receive/update planned meals
     */
    private void registerToPlannedMealViewModel() {
        // Create RecipeViewModel Factory for param injection
        PlannedMealViewModel.Factory plannedMealFactory = new PlannedMealViewModel.Factory(
                getActivity().getApplication());
        // Get instance of RecipeViewModel
        mPlannedMealViewModel = ViewModelProviders.of(this, plannedMealFactory)
                .get(PlannedMealViewModel.class);
    }

    /**
     * Registers the View into PlannedRecipe ViewModel to receive/update planned recipes
     */
    private void registerToPlannedRecipeViewModel() {
        // Create RecipeViewModel Factory for param injection
        PlannedRecipeViewModel.Factory plannedRecipeFactory = new PlannedRecipeViewModel.Factory(
                getActivity().getApplication());
        // Get instance of RecipeViewModel
        mPlannedRecipeViewModel = ViewModelProviders.of(this, plannedRecipeFactory)
                .get(PlannedRecipeViewModel.class);
    }

    private void enableNotificationsFromPlannedMealViewModel() {
        mPlannedMealViewModel.getPlannedMeals().observe(this, new Observer<List<PlannedMeal>>() {
            @Override
            public void onChanged(@Nullable List<PlannedMeal> plannedMeals) {
                if(plannedMeals == null || plannedMeals.isEmpty()) {
                    return;
                } else {
                    mPlannedMealList = plannedMeals;
                }
                hasPlannedMealsRetrieved = true;
                if(hasPlannedMealsRetrieved && hasPlannedRecipesRetrieved) {
                    showMealPlan();
                }
            }
        });
    }

    private void enableNotificationsFromPlannedRecipeViewModel() {
        mPlannedRecipeViewModel.getPlannedRecipes().observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(@Nullable List<Recipe> plannedRecipes) {
                if(plannedRecipes == null || plannedRecipes.isEmpty()) {
                    return;
                } else {
                    mPlannedRecipeList = plannedRecipes;
                }
                hasPlannedRecipesRetrieved = true;
                if(hasPlannedMealsRetrieved && hasPlannedRecipesRetrieved) {
                    showMealPlan();
                }
            }
        });
    }

}
