package com.udacity.mregtej.mymealplanner.ui;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.udacity.mregtej.mymealplanner.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MealMenuDayFragment extends Fragment {

    public static final String POSITION_KEY = "meal-menu-day-fragment-position";

    @BindView(R.id.iv_meal_menu_day_breakfast_update)
    ImageView ivMealMenuDayBreakfastUpdate;
    @BindView(R.id.iv_meal_menu_day_breakfast_recipe_photo)
    ImageView ivMealMenuDayBreakfastRecipePhoto;
    @BindView(R.id.tv_meal_menu_day_breakfast_recipe_name)
    TextView tvMealMenuDayBreakfastRecipeName;
    @BindView(R.id.iv_meal_menu_day_brunch_update)
    ImageView ivMealMenuDayBrunchUpdate;
    @BindView(R.id.iv_meal_menu_day_brunch_recipe_photo)
    ImageView ivMealMenuDayBrunchRecipePhoto;
    @BindView(R.id.tv_meal_menu_day_brunch_recipe_name)
    TextView tvMealMenuDayBrunchRecipeName;
    @BindView(R.id.iv_meal_menu_day_lunch_update)
    ImageView ivMealMenuDayLunchUpdate;
    @BindView(R.id.iv_meal_menu_day_lunch_recipe_photo)
    ImageView ivMealMenuDayLunchRecipePhoto;
    @BindView(R.id.tv_meal_menu_day_lunch_recipe_name)
    TextView tvMealMenuDayLunchRecipeName;
    @BindView(R.id.iv_meal_menu_day_dinner_update)
    ImageView ivMealMenuDayDinnerUpdate;
    @BindView(R.id.iv_meal_menu_day_dinner_recipe_photo)
    ImageView ivMealMenuDayDinnerRecipePhoto;
    @BindView(R.id.tv_meal_menu_day_dinner_recipe_name)
    TextView tvMealMenuDayDinnerRecipeName;
    @BindView(R.id.fab_meal_menu_day_schedule)
    FloatingActionButton fabMealMenuDaySchedule;

    private Context mContext;
    Unbinder unbinder;

    public MealMenuDayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_meal_menu_day, container, false);
        unbinder = ButterKnife.bind(this, view);

        mContext = getActivity();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.iv_meal_menu_day_breakfast_update,
            R.id.iv_meal_menu_day_breakfast_recipe_photo,
            R.id.iv_meal_menu_day_brunch_update,
            R.id.iv_meal_menu_day_brunch_recipe_photo,
            R.id.iv_meal_menu_day_lunch_update,
            R.id.iv_meal_menu_day_lunch_recipe_photo,
            R.id.iv_meal_menu_day_dinner_update,
            R.id.iv_meal_menu_day_dinner_recipe_photo,
            R.id.fab_meal_menu_day_schedule})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_meal_menu_day_breakfast_update:
                break;
            case R.id.iv_meal_menu_day_breakfast_recipe_photo:
                Intent intent = new Intent(mContext, RecipeActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_meal_menu_day_brunch_update:
                break;
            case R.id.iv_meal_menu_day_brunch_recipe_photo:
                break;
            case R.id.iv_meal_menu_day_lunch_update:
                break;
            case R.id.iv_meal_menu_day_lunch_recipe_photo:
                break;
            case R.id.iv_meal_menu_day_dinner_update:
                break;
            case R.id.iv_meal_menu_day_dinner_recipe_photo:
                break;
            case R.id.fab_meal_menu_day_schedule:
                break;
        }
    }
}
