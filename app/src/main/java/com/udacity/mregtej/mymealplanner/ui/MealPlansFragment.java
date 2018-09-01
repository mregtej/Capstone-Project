package com.udacity.mregtej.mymealplanner.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.udacity.mregtej.mymealplanner.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MealPlansFragment extends Fragment {

    @BindView(R.id.bt_show_more_favourite_meals)
    Button btShowMoreFavouriteMeals;
    @BindView(R.id.bt_show_more_diet_meals)
    Button btShowMoreDietMeals;
    @BindView(R.id.bt_show_more_veggie_meals)
    Button btShowMoreVeggieMeals;
    Unbinder unbinder;
    ActionBar actionBar;

    public MealPlansFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Set ActionBar Title
        setActionBarTitle(getString(R.string.meal_plans_screen_title));
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_meal_plans, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void setActionBarTitle(String title) {
        actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        if(actionBar != null) {
            actionBar.setTitle(title);
        }
    }
}
