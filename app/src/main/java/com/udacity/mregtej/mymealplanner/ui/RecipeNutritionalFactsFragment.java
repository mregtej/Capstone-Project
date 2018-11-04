package com.udacity.mregtej.mymealplanner.ui;


import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.udacity.mregtej.mymealplanner.R;
import com.udacity.mregtej.mymealplanner.datamodel.RecipeNutritionalFact;
import com.udacity.mregtej.mymealplanner.ui.utils.NumberUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeNutritionalFactsFragment extends Fragment {

    //--------------------------------------------------------------------------------|
    //                                 Constants                                      |
    //--------------------------------------------------------------------------------|

    /** Key for passing the recipe nutritional facts to RecipeIngredientsFragment */
    private static final String RECIPE_NUTRITIONAL_FACTS_LIST_KEY = "recipe-nutritional-fact-list";

    /** Recommended calories intake per day (in kcal)*/
    private static final int RECOMMENDED_CALORIES_PER_DAY = 2000;
    /** Recommended fats intake per day (in g)*/
    private static final int RECOMMENDED_FAT_PER_DAY = 65;
    /** Recommended cholesterol intake per day (in mg)*/
    private static final int RECOMMENDED_CHOLESTEROL_PER_DAY = 300;
    /** Recommended sodium intake per day (in mg)*/
    private static final int RECOMMENDED_SODIUM_PER_DAY = 2400;
    /** Recommended carbs intake per day (in g)*/
    private static final int RECOMMENDED_CARBS_PER_DAY = 300;
    /** Recommended proteins intake per day (in g)*/
    private static final int RECOMMENDED_PROTEIN_PER_DAY = 50;


    //--------------------------------------------------------------------------------|
    //                                 Parameters                                     |
    //--------------------------------------------------------------------------------|

    @BindView(R.id.v_recipe_screen_nutritional_facts_calories_daily_bar)
    View vRecipeScreenNutritionalFactsCaloriesDailyBar;
    @BindView(R.id.tv_recipe_screen_nutritional_facts_calories_value)
    TextView tvRecipeScreenNutritionalFactsCaloriesValue;
    @BindView(R.id.tv_recipe_screen_nutritional_facts_calories_units)
    TextView tvRecipeScreenNutritionalFactsCaloriesUnits;
    @BindView(R.id.tv_recipe_screen_nutritional_facts_calories_pro_day)
    TextView tvRecipeScreenNutritionalFactsCaloriesProDay;
    @BindView(R.id.cl_recipe_screen_nutritional_facts_calories)
    ConstraintLayout clRecipeScreenNutritionalFactsCalories;
    @BindView(R.id.v_recipe_screen_nutritional_facts_fat_daily_bar)
    View vRecipeScreenNutritionalFactsFatDailyBar;
    @BindView(R.id.tv_recipe_screen_nutritional_facts_fat_value)
    TextView tvRecipeScreenNutritionalFactsFatValue;
    @BindView(R.id.tv_recipe_screen_nutritional_facts_fat_units)
    TextView tvRecipeScreenNutritionalFactsFatUnits;
    @BindView(R.id.tv_recipe_screen_nutritional_facts_fat_pro_day)
    TextView tvRecipeScreenNutritionalFactsFatProDay;
    @BindView(R.id.cl_recipe_screen_nutritional_facts_fat)
    ConstraintLayout clRecipeScreenNutritionalFactsFat;
    @BindView(R.id.v_recipe_screen_nutritional_facts_carbs_daily_bar)
    View vRecipeScreenNutritionalFactsCarbsDailyBar;
    @BindView(R.id.tv_recipe_screen_nutritional_facts_carbs_value)
    TextView tvRecipeScreenNutritionalFactsCarbsValue;
    @BindView(R.id.tv_recipe_screen_nutritional_facts_carbs_units)
    TextView tvRecipeScreenNutritionalFactsCarbsUnits;
    @BindView(R.id.tv_recipe_screen_nutritional_facts_carbs_pro_day)
    TextView tvRecipeScreenNutritionalFactsCarbsProDay;
    @BindView(R.id.cl_recipe_screen_nutritional_facts_carbs)
    ConstraintLayout clRecipeScreenNutritionalFactsCarbs;
    @BindView(R.id.v_recipe_screen_nutritional_facts_cholesterol_daily_bar)
    View vRecipeScreenNutritionalFactsCholesterolDailyBar;
    @BindView(R.id.tv_recipe_screen_nutritional_facts_cholesterol_value)
    TextView tvRecipeScreenNutritionalFactsCholesterolValue;
    @BindView(R.id.tv_recipe_screen_nutritional_facts_cholesterol_units)
    TextView tvRecipeScreenNutritionalFactsCholesterolUnits;
    @BindView(R.id.tv_recipe_screen_nutritional_facts_cholesterol_pro_day)
    TextView tvRecipeScreenNutritionalFactsCholesterolProDay;
    @BindView(R.id.cl_recipe_screen_nutritional_facts_cholesterol)
    ConstraintLayout clRecipeScreenNutritionalFactsCholesterol;
    @BindView(R.id.v_recipe_screen_nutritional_facts_protein_daily_bar)
    View vRecipeScreenNutritionalFactsProteinDailyBar;
    @BindView(R.id.tv_recipe_screen_nutritional_facts_protein_value)
    TextView tvRecipeScreenNutritionalFactsProteinValue;
    @BindView(R.id.tv_recipe_screen_nutritional_facts_protein_units)
    TextView tvRecipeScreenNutritionalFactsProteinUnits;
    @BindView(R.id.tv_recipe_screen_nutritional_facts_protein_pro_day)
    TextView tvRecipeScreenNutritionalFactsProteinProDay;
    @BindView(R.id.cl_recipe_screen_nutritional_facts_protein)
    ConstraintLayout clRecipeScreenNutritionalFactsProtein;
    @BindView(R.id.v_recipe_screen_nutritional_facts_sodium_daily_bar)
    View vRecipeScreenNutritionalFactsSodiumDailyBar;
    @BindView(R.id.tv_recipe_screen_nutritional_facts_sodium_value)
    TextView tvRecipeScreenNutritionalFactsSodiumValue;
    @BindView(R.id.tv_recipe_screen_nutritional_facts_sodium_units)
    TextView tvRecipeScreenNutritionalFactsSodiumUnits;
    @BindView(R.id.tv_recipe_screen_nutritional_facts_sodium_pro_day)
    TextView tvRecipeScreenNutritionalFactsSodiumProDay;
    @BindView(R.id.cl_recipe_screen_nutritional_facts_sodium)
    ConstraintLayout clRecipeScreenNutritionalFactsSodium;

    /** List of recipe nutritional facts retrived from Realtime Database */
    private ArrayList<RecipeNutritionalFact> mRecipeNutritionalFacts;
    /** Activity Context*/
    private Context mContext;
    /** ButterKnife un-binder */
    Unbinder unbinder;


    //--------------------------------------------------------------------------------|
    //                                 Constructors                                   |
    //--------------------------------------------------------------------------------|

    public RecipeNutritionalFactsFragment() {
        // Required empty public constructor
    }


    //--------------------------------------------------------------------------------|
    //                                 Override Methods                               |
    //--------------------------------------------------------------------------------|

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_recipe_nutritional_facts, container,
                false);
        unbinder = ButterKnife.bind(this, rootView);
        mContext = getActivity();

        if (savedInstanceState != null) {
            // Retrieve recipe nutritional facts from savedInstanceState
            mRecipeNutritionalFacts = savedInstanceState.
                    getParcelableArrayList(RECIPE_NUTRITIONAL_FACTS_LIST_KEY);
        } else {
            // Retrieve recipe nutritional facts from Activity
            mRecipeNutritionalFacts = getArguments().
                    getParcelableArrayList(RECIPE_NUTRITIONAL_FACTS_LIST_KEY);
        }

        // Populate UI views
        populateUIViews();

        // Return rootView
        return rootView;

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(RECIPE_NUTRITIONAL_FACTS_LIST_KEY, mRecipeNutritionalFacts);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    //--------------------------------------------------------------------------------|
    //                                 Private Methods                                |
    //--------------------------------------------------------------------------------|

    /**
     * Populate UI View elements (nutritional facts)
     */
    private void populateUIViews() {

        if(mRecipeNutritionalFacts != null) {

            for(RecipeNutritionalFact recipeNutritionalFact : mRecipeNutritionalFacts) {

                String nutritionalFactTitle = recipeNutritionalFact.getTitle();

                if (nutritionalFactTitle.equals(mContext.getString(
                        R.string.recipe_nutritional_facts_calories_key))) {

                    tvRecipeScreenNutritionalFactsCaloriesValue.setText(
                            String.valueOf(recipeNutritionalFact.getQuantity()));
                    tvRecipeScreenNutritionalFactsCaloriesUnits.setText(
                            recipeNutritionalFact.getUnits());

                } else if (nutritionalFactTitle.equals(mContext.getString(
                        R.string.recipe_nutritional_facts_fat_key))) {

                    tvRecipeScreenNutritionalFactsFatValue.setText(
                            String.valueOf(recipeNutritionalFact.getQuantity()));
                    tvRecipeScreenNutritionalFactsFatUnits.setText(
                            recipeNutritionalFact.getUnits());

                } else if (nutritionalFactTitle.equals(mContext.getString(
                        R.string.recipe_nutritional_facts_cholesterol_key))) {

                    tvRecipeScreenNutritionalFactsCholesterolValue.setText(
                            String.valueOf(recipeNutritionalFact.getQuantity()));
                    tvRecipeScreenNutritionalFactsCholesterolUnits.setText(
                            recipeNutritionalFact.getUnits());

                } else if (nutritionalFactTitle.equals(mContext.getString(
                        R.string.recipe_nutritional_facts_carbs_key))) {

                    tvRecipeScreenNutritionalFactsCarbsValue.setText(
                            String.valueOf(recipeNutritionalFact.getQuantity()));
                    tvRecipeScreenNutritionalFactsCarbsUnits.setText(
                            recipeNutritionalFact.getUnits());

                } else if (nutritionalFactTitle.equals(mContext.getString(
                        R.string.recipe_nutritional_facts_protein_key))) {

                    tvRecipeScreenNutritionalFactsProteinValue.setText(
                            String.valueOf(recipeNutritionalFact.getQuantity()));
                    tvRecipeScreenNutritionalFactsProteinUnits.setText(
                            recipeNutritionalFact.getUnits());

                } else if (nutritionalFactTitle.equals(mContext.getString(
                        R.string.recipe_nutritional_facts_sodium_key))) {

                    tvRecipeScreenNutritionalFactsSodiumValue.setText(
                            String.valueOf(recipeNutritionalFact.getQuantity()));
                    tvRecipeScreenNutritionalFactsSodiumUnits.setText(
                            recipeNutritionalFact.getUnits());

                }  else {}

            }

            // Calculate intakes in terms of recommended intake per day and update UI elements
            populateRecommendedNutrientPerDayUIViews();

        }

    }

    /**
     * Calculate intakes in terms of recommended intake per day and update UI elements
     */
    private void populateRecommendedNutrientPerDayUIViews() {
        ViewTreeObserver viewTreeObserver = clRecipeScreenNutritionalFactsCalories.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener( new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                    clRecipeScreenNutritionalFactsCalories.
                            getViewTreeObserver().removeGlobalOnLayoutListener(this);
                } else {
                    clRecipeScreenNutritionalFactsCalories.
                            getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
                int width  = clRecipeScreenNutritionalFactsCalories.getMeasuredWidth();
                int height = clRecipeScreenNutritionalFactsCalories.getMeasuredHeight();

                if(mRecipeNutritionalFacts != null) {

                    for (RecipeNutritionalFact recipeNutritionalFact : mRecipeNutritionalFacts) {

                        String nutritionalFactTitle = recipeNutritionalFact.getTitle();

                        if (nutritionalFactTitle.equals(mContext.getString(
                                R.string.recipe_nutritional_facts_calories_key))) {

                            double recommendedNutrientPerDayInProCent =
                                    100 * recipeNutritionalFact.getQuantity() / (double) RECOMMENDED_CALORIES_PER_DAY;
                            ViewGroup.LayoutParams params = vRecipeScreenNutritionalFactsCaloriesDailyBar.getLayoutParams();
                            params.width = (int) (width * recommendedNutrientPerDayInProCent / 100);
                            vRecipeScreenNutritionalFactsCaloriesDailyBar.setLayoutParams(params);
                            tvRecipeScreenNutritionalFactsCaloriesProDay.setText(String.valueOf(
                                    NumberUtils.truncateQuantity(recommendedNutrientPerDayInProCent)));

                        } else if (nutritionalFactTitle.equals(mContext.getString(
                                R.string.recipe_nutritional_facts_fat_key))) {

                            double recommendedNutrientPerDayInProCent =
                                    100 * recipeNutritionalFact.getQuantity() / (double) RECOMMENDED_FAT_PER_DAY;
                            ViewGroup.LayoutParams params = vRecipeScreenNutritionalFactsFatDailyBar.getLayoutParams();
                            params.width = (int) (width * recommendedNutrientPerDayInProCent / 100);
                            vRecipeScreenNutritionalFactsFatDailyBar.setLayoutParams(params);
                            tvRecipeScreenNutritionalFactsFatProDay.setText(String.valueOf(
                                    NumberUtils.truncateQuantity(recommendedNutrientPerDayInProCent)));

                        } else if (nutritionalFactTitle.equals(mContext.getString(
                                R.string.recipe_nutritional_facts_cholesterol_key))) {

                            double recommendedNutrientPerDayInProCent =
                                    100 * recipeNutritionalFact.getQuantity() / (double) RECOMMENDED_CHOLESTEROL_PER_DAY;
                            ViewGroup.LayoutParams params = vRecipeScreenNutritionalFactsCholesterolDailyBar.getLayoutParams();
                            params.width = (int) (width * recommendedNutrientPerDayInProCent / 100);
                            vRecipeScreenNutritionalFactsCholesterolDailyBar.setLayoutParams(params);
                            tvRecipeScreenNutritionalFactsCholesterolProDay.setText(String.valueOf(
                                    NumberUtils.truncateQuantity(recommendedNutrientPerDayInProCent)));

                        } else if (nutritionalFactTitle.equals(mContext.getString(
                                R.string.recipe_nutritional_facts_carbs_key))) {

                            double recommendedNutrientPerDayInProCent =
                                    100 * recipeNutritionalFact.getQuantity() / (double) RECOMMENDED_CARBS_PER_DAY;
                            ViewGroup.LayoutParams params = vRecipeScreenNutritionalFactsCarbsDailyBar.getLayoutParams();
                            params.width = (int) (width * recommendedNutrientPerDayInProCent / 100);
                            vRecipeScreenNutritionalFactsCarbsDailyBar.setLayoutParams(params);
                            tvRecipeScreenNutritionalFactsCarbsProDay.setText(String.valueOf(
                                    NumberUtils.truncateQuantity(recommendedNutrientPerDayInProCent)));

                        } else if (nutritionalFactTitle.equals(mContext.getString(
                                R.string.recipe_nutritional_facts_protein_key))) {

                            double recommendedNutrientPerDayInProCent =
                                    100 * recipeNutritionalFact.getQuantity() / (double) RECOMMENDED_PROTEIN_PER_DAY;
                            ViewGroup.LayoutParams params = vRecipeScreenNutritionalFactsProteinDailyBar.getLayoutParams();
                            params.width = (int) (width * recommendedNutrientPerDayInProCent / 100);
                            vRecipeScreenNutritionalFactsProteinDailyBar.setLayoutParams(params);
                            tvRecipeScreenNutritionalFactsProteinProDay.setText(String.valueOf(
                                    NumberUtils.truncateQuantity(recommendedNutrientPerDayInProCent)));

                        } else if (nutritionalFactTitle.equals(mContext.getString(
                                R.string.recipe_nutritional_facts_sodium_key))) {

                            double recommendedNutrientPerDayInProCent =
                                    100 * recipeNutritionalFact.getQuantity() / (double) RECOMMENDED_SODIUM_PER_DAY;
                            ViewGroup.LayoutParams params = vRecipeScreenNutritionalFactsSodiumDailyBar.getLayoutParams();
                            params.width = (int) (width * recommendedNutrientPerDayInProCent / 100);
                            vRecipeScreenNutritionalFactsSodiumDailyBar.setLayoutParams(params);
                            tvRecipeScreenNutritionalFactsSodiumProDay.setText(String.valueOf(
                                    NumberUtils.truncateQuantity(recommendedNutrientPerDayInProCent)));

                        } else {}

                    }

                }

            }

        });

    }

}
