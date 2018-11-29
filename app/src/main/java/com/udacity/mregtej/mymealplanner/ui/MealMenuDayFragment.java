package com.udacity.mregtej.mymealplanner.ui;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udacity.mregtej.mymealplanner.R;
import com.udacity.mregtej.mymealplanner.datamodel.Meal;
import com.udacity.mregtej.mymealplanner.datamodel.MealDay;
import com.udacity.mregtej.mymealplanner.datamodel.Recipe;
import com.udacity.mregtej.mymealplanner.global.MyMealPlannerGlobals;
import com.udacity.mregtej.mymealplanner.ui.adapters.MealMenuDayMealtimeAdapter;
import com.udacity.mregtej.mymealplanner.viewmodel.RecipeViewModel;

import java.util.LinkedHashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MealMenuDayFragment extends Fragment
        implements MealMenuDayMealtimeAdapter.MealMenuDayMealtimeClickListener {

    public static final String POSITION_KEY = "meal-day-fragment-position";

    public static final String MEAL_DAY_KEY = "meal-day";

    public static final String RECIPE_KEY = "recipe";

    /** Key for storing the list state in savedInstanceState */
    private static final String MEAL_DAY_LIST_STATE_KEY = "meal-day-list-state";

    /** Key for storing the meal-days to buy in savedInstanceState */
    private static final String MEAL_DAY_LIST_KEY = "meal-day-list";


    @BindView(R.id.cl_meal_menu_day)
    ConstraintLayout clMealMenuDay;
    @BindView(R.id.fab_meal_menu_all_schedule)
    FloatingActionButton fabMealMenuAllSchedule;
    @BindView(R.id.rv_meal_menu_day_data)
    RecyclerView rvMealMenuDayData;
    @BindView(R.id.fab_meal_menu_day_schedule)
    FloatingActionButton fabMealMenuDaySchedule;

    Unbinder unbinder;
    Context mContext;
    ActionBar actionBar;
    private MealDay mMealDay;

    private static MealMenuDayFragmentListener mMealMenuDayFragmentListener;


    /**
     * ViewModel instance
     */
    private RecipeViewModel mRecipeViewModel;

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

    public static Fragment newInstance(Context context, MealMenuDayFragmentListener listener) {
        mMealMenuDayFragmentListener = listener;
        return Fragment.instantiate(context, MealMenuDayFragment.class.getName());
    }

    public MealMenuDayFragment() {
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

            LinkedHashMap<String,Recipe> mealDayList =
                    (LinkedHashMap<String,Recipe>) savedInstanceState.getSerializable(MEAL_DAY_LIST_KEY);
            mMealPlannerMealtimeAdapter = new MealMenuDayMealtimeAdapter(mealDayList, this);
            rvMealMenuDayData.setAdapter(mMealPlannerMealtimeAdapter);
            mMealPlannerMealtimeAdapter.notifyDataSetChanged();

        } else {

            mMealDay = getArguments().getParcelable(MEAL_DAY_KEY);

            // Subscribe MealMenuDayFragment to receive notifications from RecipeViewModel
            registerToRecipeViewModel();

            // Get recipes
            getRecipes();

        }

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void setRecyclerViewLayoutManager(View rootView) {
        switch (this.getResources().getConfiguration().orientation) {
            case MyMealPlannerGlobals.LANDSCAPE_VIEW: // Landscape Mode
                mMealPlannerMealtimeLayoutManager = new GridLayoutManager(rootView.getContext(), MyMealPlannerGlobals.MEAL_MENU_DAY_RECIPE_GV_LAND_COLUMN_NUMB);
                break;
            case MyMealPlannerGlobals.PORTRAIT_VIEW: // Portrait Mode
            default:
                mMealPlannerMealtimeLayoutManager = new GridLayoutManager(rootView.getContext(), MyMealPlannerGlobals.MEAL_MENU_DAY_RECIPE_GV_PORT_COLUMN_NUMB);
                break;
        }
        rvMealMenuDayData.setLayoutManager(mMealPlannerMealtimeLayoutManager);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mListStateMealPlannerMealtime = mMealPlannerMealtimeLayoutManager.onSaveInstanceState();
        outState.putParcelable(MEAL_DAY_LIST_STATE_KEY, mListStateMealPlannerMealtime);
        outState.putSerializable(MEAL_DAY_LIST_KEY, mMealPlannerMealtimeAdapter.getmMealList());

    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            mListStateMealPlannerMealtime = savedInstanceState.getParcelable(MEAL_DAY_LIST_STATE_KEY);
        }
    }

    @Override
    public void onMealRecipeClick(Recipe recipe) {
        Intent intent = new Intent(mContext, RecipeActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(RECIPE_KEY, recipe);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * Registers the View into Recipe ViewModel to receive updated recipes (Observer pattern)
     */
    private void registerToRecipeViewModel() {
        // Create RecipeViewModel Factory for param injection
        RecipeViewModel.Factory recipeFactory = new RecipeViewModel.Factory(getActivity().getApplication());
        // Get instance of RecipeViewModel
        mRecipeViewModel = ViewModelProviders.of(this, recipeFactory).get(RecipeViewModel.class);
    }

    private void setRecipes() {
        mRecipeViewModel.setRecipes();
    }

    private void getRecipes() {
        mRecipeViewModel.getRecipes().observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(@Nullable List<Recipe> recipes) {
                if (recipes == null || recipes.isEmpty()) {
                    return;
                } else {
                    populateMealMenuDayMealtimeAdapter(recipes);
                }
            }
        });
    }

    /**
     * Populate ArrayAdapters's list of recipes
     *
     * @param recipes Retrieved list of recipes from Realtime Database
     */
    private void populateMealMenuDayMealtimeAdapter(List<Recipe> recipes) {

        // TODO Min API: 19 - Look for alternatives
        LinkedHashMap<String,Recipe> mealRecipeList = new LinkedHashMap<>();
        if(recipes != null) {
            for (Recipe recipe : recipes) {
                if(mMealDay != null) {
                    for (Meal meal : mMealDay.getMeals()) {
                        if (meal.getRecipeId().equals(recipe.getId())) {
                            mealRecipeList.put(meal.getMealTime(), recipe);
                        }
                    }
                }
            }
        }

        // Set Adapter and notifyDataSetChanged
        mMealPlannerMealtimeAdapter = new MealMenuDayMealtimeAdapter(mealRecipeList, this);
        rvMealMenuDayData.setAdapter(mMealPlannerMealtimeAdapter);
        mMealPlannerMealtimeAdapter.notifyDataSetChanged();

    }

    @OnClick({R.id.fab_meal_menu_day_schedule, R.id.fab_meal_menu_all_schedule})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fab_meal_menu_day_schedule:
                showAddMealDayToCalendarDialog();
                break;
            case R.id.fab_meal_menu_all_schedule:
                if(mMealMenuDayFragmentListener != null) {
                    mMealMenuDayFragmentListener.onAddMealPlanToCalendarClick();
                }
                break;
        }
    }

    private void showAddMealDayToCalendarDialog() {
        // DialogFragment.show() will take care of adding the fragment
        // in a transaction.  We also want to remove any currently showing
        // dialog, so make our own transaction and take care of that here.
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        // Create and show the dialog.
        DialogFragment newFragment = AddMealDayToCalendarDialogFragment.newInstance();
        Bundle bundle = new Bundle();
        bundle.putSerializable(MEAL_DAY_LIST_KEY, mMealPlannerMealtimeAdapter.getmMealList());
        newFragment.setArguments(bundle);
        newFragment.show(ft, "dialog");
    }

    //--------------------------------------------------------------------------------|
    //                        Fragment--> Fragment Pager Comm                         |
    //--------------------------------------------------------------------------------|

    public interface MealMenuDayFragmentListener {
        public void onAddMealPlanToCalendarClick();
    }

}
