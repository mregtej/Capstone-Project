package com.udacity.mregtej.mymealplanner.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udacity.mregtej.mymealplanner.R;
import com.udacity.mregtej.mymealplanner.ui.adapters.MealMenuFragmentPagerAdapter;
import com.udacity.mregtej.mymealplanner.ui.adapters.RecipeDataFragmentPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeDataFragment extends Fragment {

    public static final String POSITION_KEY = "recipe-data-fragment-position";

    @BindView(R.id.pts_recipe_data_pager_title_strip)
    PagerTitleStrip ptsRecipeDataPagerTitleStrip;
    @BindView(R.id.vp_recipe_data_pager)
    ViewPager vpRecipeDataPager;

    Unbinder unbinder;

    public RecipeDataFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_recipe_data, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        // Set Adapter to ViewPager
        vpRecipeDataPager.setAdapter(new RecipeDataFragmentPagerAdapter(getChildFragmentManager(),
                getActivity()));
        // Attach the page change listener inside the activity
        vpRecipeDataPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            // This method will be invoked when a new page becomes selected.
            @Override
            public void onPageSelected(int position) {
                // Code goes here
            }

            // This method will be invoked when the current page is scrolled
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // Code goes here
            }

            // Called when the scroll state changes:
            // SCROLL_STATE_IDLE, SCROLL_STATE_DRAGGING, SCROLL_STATE_SETTLING
            @Override
            public void onPageScrollStateChanged(int state) {
                // Code goes here
            }
        });

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
