package com.udacity.mregtej.mymealplanner.ui;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.udacity.mregtej.mymealplanner.R;
import com.udacity.mregtej.mymealplanner.datamodel.MealPlan;
import com.udacity.mregtej.mymealplanner.ui.adapters.MealPlansPreviewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class MealPlansFragment extends Fragment implements MealPlansPreviewAdapter.MealPlansPreviewClickListener {

    /**
     * Key for storing the list state in savedInstanceState
     */
    private static final String FAVOURITE_MEAL_PLANS_LIST_STATE_KEY = "favourite-meal-plan-list-state";
    /**
     * Key for storing the list state in savedInstanceState
     */
    private static final String DIET_MEAL_PLANS_LIST_STATE_KEY = "diet-meal-plan-list-state";
    /**
     * Key for storing the list state in savedInstanceState
     */
    private static final String VEGGIE_MEAL_PLANS_LIST_STATE_KEY = "veggie-meal-plan-list-state";

    /**
     * Key for storing the favourite meal plans in savedInstanceState
     */
    private static final String FAVOURITE_MEAL_PLANS_LIST_KEY = "favourite-meal-plan-list";
    /**
     * Key for storing the diet meal plans in savedInstanceState
     */
    private static final String DIET_MEAL_PLANS_LIST_KEY = "diet-meal-plan-list";
    /**
     * Key for storing the veggies meal plans in savedInstanceState
     */
    private static final String VEGGIE_MEAL_PLANS_LIST_KEY = "veggie-meal-plan-list";

    @BindView(R.id.bt_show_more_favourite_meals)
    Button btShowMoreFavouriteMeals;
    @BindView(R.id.bt_show_more_diet_meals)
    Button btShowMoreDietMeals;
    @BindView(R.id.bt_show_more_veggie_meals)
    Button btShowMoreVeggieMeals;

    RecyclerView rvFavouriteMealCards;
    RecyclerView rvDietMealCards;
    RecyclerView rvVeggieMealCards;
    Unbinder unbinder;
    ActionBar actionBar;

    private OnMealPlansFragmentInteractionListener mListener;

    /**
     * RecyclerView LayoutManager instance
     */
    private RecyclerView.LayoutManager mFavouriteMealPlanLayoutManager;
    /**
     * Favourite Meal Plans Custom ArrayAdapter
     */
    private MealPlansPreviewAdapter mFavouriteMealPlanListAdapter;
    /**
     * List state stored in savedInstanceState
     */
    private Parcelable mListStateFavouriteMealPlan;
    /**
     * RecyclerView LayoutManager instance
     */
    private RecyclerView.LayoutManager mDietMealPlanLayoutManager;
    /**
     * Diet Meal Plans Custom ArrayAdapter
     */
    private MealPlansPreviewAdapter mDietMealPlanListAdapter;
    /**
     * List state stored in savedInstanceState
     */
    private Parcelable mListStateDietMealPlan;
    /**
     * RecyclerView LayoutManager instance
     */
    private RecyclerView.LayoutManager mVeggieMealPlanLayoutManager;
    /**
     * Veggie Meal Plans Custom ArrayAdapter
     */
    private MealPlansPreviewAdapter mVeggieMealPlanListAdapter;
    /**
     * List state stored in savedInstanceState
     */
    private Parcelable mListStateVeggieMealPlan;

    public MealPlansFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnMealPlansFragmentInteractionListener) {
            mListener = (OnMealPlansFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnMealPlansFragmentInteractionListener");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public interface OnMealPlansFragmentInteractionListener {
        void onMealPlanPreviewClick(MealPlan mealPlan);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Set ActionBar Title
        setActionBarTitle(getString(R.string.meal_plans_screen_title));

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_meal_plans, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        // Inflate RecyclerViews
        rvFavouriteMealCards = (RecyclerView) rootView.findViewById(R.id.rv_favourite_meal_cards);
        rvDietMealCards = (RecyclerView) rootView.findViewById(R.id.rv_diet_meal_cards);
        rvVeggieMealCards = (RecyclerView) rootView.findViewById(R.id.rv_veggie_meal_cards);

        // Load & set GridLayout
        rvFavouriteMealCards.setHasFixedSize(true);
        rvDietMealCards.setHasFixedSize(true);
        rvVeggieMealCards.setHasFixedSize(true);
        setRecyclerViewLayoutManager(rootView);

        if (savedInstanceState != null) {

            List<MealPlan> favouriteMealPlans = savedInstanceState.
                    getParcelableArrayList(FAVOURITE_MEAL_PLANS_LIST_KEY);
            mFavouriteMealPlanListAdapter = new MealPlansPreviewAdapter(favouriteMealPlans, this);
            rvFavouriteMealCards.setAdapter(mFavouriteMealPlanListAdapter);
            mFavouriteMealPlanListAdapter.notifyDataSetChanged();

            List<MealPlan> dietMealPlans = savedInstanceState.
                    getParcelableArrayList(DIET_MEAL_PLANS_LIST_KEY);
            mDietMealPlanListAdapter = new MealPlansPreviewAdapter(dietMealPlans, this);
            rvDietMealCards.setAdapter(mDietMealPlanListAdapter);
            mDietMealPlanListAdapter.notifyDataSetChanged();

            List<MealPlan> veggieMealPlans = savedInstanceState.
                    getParcelableArrayList(VEGGIE_MEAL_PLANS_LIST_KEY);
            mVeggieMealPlanListAdapter = new MealPlansPreviewAdapter(veggieMealPlans, this);
            rvVeggieMealCards.setAdapter(mVeggieMealPlanListAdapter);
            mVeggieMealPlanListAdapter.notifyDataSetChanged();

        } else {

            ArrayList<MealPlan> favouriteMealPlans = new ArrayList<>();
            // TODO Fill in
            favouriteMealPlans.add(null);
            favouriteMealPlans.add(null);
            favouriteMealPlans.add(null);
            favouriteMealPlans.add(null);
            favouriteMealPlans.add(null);
            favouriteMealPlans.add(null);
            mFavouriteMealPlanListAdapter = new MealPlansPreviewAdapter(favouriteMealPlans, this);

            ArrayList<MealPlan> dietMealPlans = new ArrayList<>();
            // TODO Fill in
            dietMealPlans.add(null);
            dietMealPlans.add(null);
            dietMealPlans.add(null);
            dietMealPlans.add(null);
            dietMealPlans.add(null);
            dietMealPlans.add(null);
            mDietMealPlanListAdapter = new MealPlansPreviewAdapter(dietMealPlans, this);

            ArrayList<MealPlan> veggieMealPlans = new ArrayList<>();
            // TODO Fill in
            veggieMealPlans.add(null);
            veggieMealPlans.add(null);
            veggieMealPlans.add(null);
            veggieMealPlans.add(null);
            veggieMealPlans.add(null);
            veggieMealPlans.add(null);
            mVeggieMealPlanListAdapter = new MealPlansPreviewAdapter(veggieMealPlans, this);

            // Set Adapter and notifyDataSetChanged
            rvFavouriteMealCards.setAdapter(mFavouriteMealPlanListAdapter);
            mFavouriteMealPlanListAdapter.notifyDataSetChanged();
            rvDietMealCards.setAdapter(mDietMealPlanListAdapter);
            mDietMealPlanListAdapter.notifyDataSetChanged();
            rvVeggieMealCards.setAdapter(mVeggieMealPlanListAdapter);
            mVeggieMealPlanListAdapter.notifyDataSetChanged();

        }

        return rootView;

    }

    private void setRecyclerViewLayoutManager(View rootView) {
        mFavouriteMealPlanLayoutManager = new GridLayoutManager(rootView.getContext(),
                1, GridLayoutManager.HORIZONTAL, false);
        rvFavouriteMealCards.setLayoutManager(mFavouriteMealPlanLayoutManager);
        mDietMealPlanLayoutManager = new GridLayoutManager(rootView.getContext(),
                1, GridLayoutManager.HORIZONTAL, false);
        rvDietMealCards.setLayoutManager(mDietMealPlanLayoutManager);
        mVeggieMealPlanLayoutManager = new GridLayoutManager(rootView.getContext(),
                1, GridLayoutManager.HORIZONTAL, false);
        rvVeggieMealCards.setLayoutManager(mVeggieMealPlanLayoutManager);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        mListStateFavouriteMealPlan = mFavouriteMealPlanLayoutManager.onSaveInstanceState();
        outState.putParcelable(FAVOURITE_MEAL_PLANS_LIST_STATE_KEY, mListStateFavouriteMealPlan);
        outState.putParcelableArrayList(FAVOURITE_MEAL_PLANS_LIST_KEY,
                (ArrayList<MealPlan>) mFavouriteMealPlanListAdapter.getmMealPlanList());

        mListStateDietMealPlan = mDietMealPlanLayoutManager.onSaveInstanceState();
        outState.putParcelable(DIET_MEAL_PLANS_LIST_STATE_KEY, mListStateDietMealPlan);
        outState.putParcelableArrayList(DIET_MEAL_PLANS_LIST_KEY,
                (ArrayList<MealPlan>) mDietMealPlanListAdapter.getmMealPlanList());

        mListStateVeggieMealPlan = mVeggieMealPlanLayoutManager.onSaveInstanceState();
        outState.putParcelable(VEGGIE_MEAL_PLANS_LIST_STATE_KEY, mListStateVeggieMealPlan);
        outState.putParcelableArrayList(VEGGIE_MEAL_PLANS_LIST_KEY,
                (ArrayList<MealPlan>) mVeggieMealPlanListAdapter.getmMealPlanList());
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            mListStateFavouriteMealPlan = savedInstanceState
                    .getParcelable(FAVOURITE_MEAL_PLANS_LIST_STATE_KEY);
            mListStateDietMealPlan = savedInstanceState
                    .getParcelable(DIET_MEAL_PLANS_LIST_STATE_KEY);
            mListStateVeggieMealPlan = savedInstanceState
                    .getParcelable(VEGGIE_MEAL_PLANS_LIST_STATE_KEY);
        }
    }

    @Override
    public void onMealPlansPreviewClickListenerClick(MealPlan mealPlan) {
        if (mListener != null) {
            mListener.onMealPlanPreviewClick(mealPlan);
        }
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

}

