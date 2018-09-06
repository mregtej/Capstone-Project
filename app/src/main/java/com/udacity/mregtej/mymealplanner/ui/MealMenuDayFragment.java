package com.udacity.mregtej.mymealplanner.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udacity.mregtej.mymealplanner.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MealMenuDayFragment extends Fragment {

    public static final String POSITION_KEY = "meal-menu-day-fragment-position";

    public MealMenuDayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_meal_menu_day, container, false);
    }

}
