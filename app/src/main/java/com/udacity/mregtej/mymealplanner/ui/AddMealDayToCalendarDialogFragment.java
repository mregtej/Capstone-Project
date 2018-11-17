package com.udacity.mregtej.mymealplanner.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import com.udacity.mregtej.mymealplanner.R;
import com.udacity.mregtej.mymealplanner.datamodel.PlannedMeal;
import com.udacity.mregtej.mymealplanner.datamodel.Recipe;
import com.udacity.mregtej.mymealplanner.ui.utils.CalendarViewScrollable;
import com.udacity.mregtej.mymealplanner.viewmodel.PlannedMealViewModel;
import com.udacity.mregtej.mymealplanner.viewmodel.PlannedRecipeViewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class AddMealDayToCalendarDialogFragment extends DialogFragment {

    /** Key for storing the meal-days to buy in savedInstanceState */
    private static final String MEAL_DAY_LIST_KEY = "meal-day-list";

    private static final String YEAR_KEY = "year";
    private static final String MONTH_KEY = "month";
    private static final String DAY_KEY = "day";

    @BindView(R.id.cv_mealday_calendar)
    CalendarViewScrollable cvMealDayCalendar;
    @BindView(R.id.bt_mealday_cancel)
    Button btMealdayCancel;
    @BindView(R.id.bt_mealday_view)
    Button btMealdayView;
    @BindView(R.id.bt_mealday_plan)
    Button btMealdayPlan;

    private PlannedMealViewModel mPlannedMealViewModel;
    private PlannedRecipeViewModel mPlannedRecipeViewModel;

    private Toast mToast;
    private Context mContext;
    Unbinder unbinder;

    private HashMap<String,Recipe> mMealDayList;
    private int mYear;
    private int mMonth;
    private int mDayOfMonth;

    static AddMealDayToCalendarDialogFragment newInstance() {
        return new AddMealDayToCalendarDialogFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_dialog_add_mealday_to_calendar, container,
                false);
        getDialog().requestWindowFeature(STYLE_NO_TITLE);
        setCancelable(false);
        unbinder = ButterKnife.bind(this, rootView);
        mContext = getContext();

        if (savedInstanceState != null) {

            mMealDayList = (HashMap<String,Recipe>)savedInstanceState.getSerializable(MEAL_DAY_LIST_KEY);
            mYear = savedInstanceState.getInt(YEAR_KEY);
            mMonth = savedInstanceState.getInt(MONTH_KEY);
            mDayOfMonth = savedInstanceState.getInt(DAY_KEY);

            // Update Date on Calendar View
            updateCalendarView();

        } else {

            /** Retrieve the meal-day recipes + mealtimes */
            mMealDayList = (HashMap<String,Recipe>)getArguments().getSerializable(MEAL_DAY_LIST_KEY);

            // Subscribe MealPlansFragment to receive notifications from PlannedMealViewModel
            registerToPlannedMealViewModel();

            // Subscribe MealPlansFragment to receive notifications from PlannedRecipesViewModel
            registerToPlannedRecipeViewModel();

            // Set Current Date on Calendar View
            setCurrentDateOnCalendarView();

            // Show Initial instructions
            showInitialInstructions();

        }

        // Set-Up Calendar listeners
        setCalendarViewListeners();

        return rootView;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(YEAR_KEY, mYear);
        outState.putInt(MONTH_KEY, mMonth);
        outState.putInt(DAY_KEY, mDayOfMonth);
        outState.putSerializable(MEAL_DAY_LIST_KEY, mMealDayList);
    }

    private void showInitialInstructions() {
        if (mToast != null) {
            mToast.cancel();
        }
        mToast = Toast.makeText(mContext, mContext.getString(R.string.add_mealday_to_calendar_choose_day_instructions), Toast.LENGTH_LONG);
        mToast.show();
    }

    private void setCurrentDateOnCalendarView() {
        // Get current date
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(calendar.getTime());
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
    }

    private void setCalendarViewListeners() {
        // Set OnDateChangeListener (user chooses a different date)
        cvMealDayCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month,
                                            int dayOfMonth) {
                // Store date
                mYear = year;
                mMonth = month;
                mDayOfMonth = dayOfMonth;
            }
        });
    }



    private void updateCalendarView() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(mYear, mMonth, mDayOfMonth, 0, 0);
        cvMealDayCalendar.setDate(calendar.getTimeInMillis());
    }

    @OnClick({R.id.bt_mealday_cancel, R.id.bt_mealday_view, R.id.bt_mealday_plan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_mealday_cancel:
                dismiss();
                break;
            case R.id.bt_mealday_view:
                showMealDayCalendarDialogFragment();
                break;
            case R.id.bt_mealday_plan:
                // TODO Store MealDay on local DB
                storeMealDayOnDB();

        }
    }

    private void showMealDayCalendarDialogFragment() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.addToBackStack(null);
        DialogFragment newFragment = MealDayCalendarDialogFragment.newInstance(mYear, mMonth,
                mDayOfMonth);
        newFragment.show(ft, "dialog");
    }

    private void storeMealDayOnDB() {
        ArrayList<PlannedMeal> plannedMeals = new ArrayList<>();
        ArrayList<Recipe> plannedRecipes = new ArrayList<>();

        // Retrieve meals and recipes and prepare the DB insertion
        Set<Map.Entry<String, Recipe>> mealTimeSet = mMealDayList.entrySet();
        for(Iterator<Map.Entry<String, Recipe>> iter = mealTimeSet.iterator(); iter.hasNext(); ) {
            Map.Entry<String, Recipe> mealTimeElement = iter.next();
            plannedMeals.add(new PlannedMeal(
                    mDayOfMonth,
                    mMonth,
                    mYear,
                    mealTimeElement.getKey(),
                    mealTimeElement.getValue().getId()));
            plannedRecipes.add(mealTimeElement.getValue());
        }

        // Insert planned recipes
        insertPlannedRecipes(plannedRecipes);
        // Insert planned meals (foreign keys previously stored)
        insertPlannedMeals(plannedMeals);
        // Close dialog
        dismiss();
        // Notify user about the data insertion
        if (mToast != null) { mToast.cancel(); }
        mToast = Toast.makeText(mContext, mContext.getString(
                R.string.add_mealday_to_calendar_data_successful_inserted),
                Toast.LENGTH_SHORT);
        mToast.show();

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

    private void insertPlannedMeals(List<PlannedMeal> plannedMeals) {
        mPlannedMealViewModel.insertPlannedMeals(plannedMeals);
    }

    private void insertPlannedRecipes(List<Recipe> plannedRecipes) {
        mPlannedRecipeViewModel.insertPlannedRecipes(plannedRecipes);
    }

}
