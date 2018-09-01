package com.udacity.mregtej.mymealplanner.ui;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import com.udacity.mregtej.mymealplanner.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MealPlannerFragment extends Fragment {

    @BindView(R.id.cv_meal_planner_calendar)
    CalendarView cvMealPlannerCalendarView;
    @BindView(R.id.tv_meal_planner_date)
    TextView tvMealPlannerDate;
    @BindView(R.id.tv_meal_planner_breakfast_meal)
    TextView tvMealPlannerBreakfastMeal;
    @BindView(R.id.bt_meal_planner_breakfast_meal_update)
    Button btMealPlannerBreakfastMealUpdate;
    @BindView(R.id.tv_meal_planner_brunch_meal)
    TextView tvMealPlannerBrunchMeal;
    @BindView(R.id.bt_meal_planner_brunch_meal_update)
    Button btMealPlannerBrunchMealUpdate;
    @BindView(R.id.tv_meal_planner_lunch_meal)
    TextView tvMealPlannerLunchMeal;
    @BindView(R.id.bt_meal_planner_lunch_meal_update)
    Button btMealPlannerLunchMealUpdate;
    @BindView(R.id.tv_meal_planner_dinner_meal)
    TextView tvMealPlannerDinnerMeal;
    @BindView(R.id.bt_meal_planner_dinner_meal_update)
    Button btMealPlannerDinnerMealUpdate;

    Unbinder unbinder;
    Context mContext;
    ActionBar actionBar;

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
        View view = inflater.inflate(R.layout.fragment_meal_planner, container, false);
        unbinder = ButterKnife.bind(this, view);
        // Set Calendar
        setCalendar(view);
        // Return fragment view
        return view;
    }


    private void setActionBarTitle(String title) {
        actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(title);
            if(!actionBar.isShowing()) {
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

}
