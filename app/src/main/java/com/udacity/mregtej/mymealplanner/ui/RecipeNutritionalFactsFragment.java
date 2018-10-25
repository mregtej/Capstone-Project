package com.udacity.mregtej.mymealplanner.ui;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.udacity.mregtej.mymealplanner.R;
import com.udacity.mregtej.mymealplanner.datamodel.RecipeNutritionalFact;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeNutritionalFactsFragment extends Fragment {

    /** Key for passing the recipe nutritional facts to RecipeIngredientsFragment */
    private static final String RECIPE_NUTRITIONAL_FACTS_LIST_KEY = "recipe-nutritional-fact-list";

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
    @BindView(R.id.v_recipe_screen_nutritional_facts_sugars_daily_bar)
    View vRecipeScreenNutritionalFactsSugarsDailyBar;
    @BindView(R.id.tv_recipe_screen_nutritional_facts_sugars_value)
    TextView tvRecipeScreenNutritionalFactsSugarsValue;
    @BindView(R.id.tv_recipe_screen_nutritional_facts_sugars_units)
    TextView tvRecipeScreenNutritionalFactsSugarsUnits;
    @BindView(R.id.tv_recipe_screen_nutritional_facts_sugars_pro_day)
    TextView tvRecipeScreenNutritionalFactsSugarsProDay;
    @BindView(R.id.cl_recipe_screen_nutritional_facts_sugars)
    ConstraintLayout clRecipeScreenNutritionalFactsSugars;
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

    private ArrayList<RecipeNutritionalFact> mRecipeNutritionalFacts;
    private Context mContext;
    Unbinder unbinder;

    public RecipeNutritionalFactsFragment() {
        // Required empty public constructor
    }


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
                        R.string.recipe_nutritional_facts_fat_key))) {

                    tvRecipeScreenNutritionalFactsFatValue.setText(
                            String.valueOf(recipeNutritionalFact.getQuantity()));
                    tvRecipeScreenNutritionalFactsFatUnits.setText(
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

                } else if (nutritionalFactTitle.equals(mContext.getString(
                        R.string.recipe_nutritional_facts_sugars_key))) {

                    tvRecipeScreenNutritionalFactsSugarsValue.setText(
                            String.valueOf(recipeNutritionalFact.getQuantity()));
                    tvRecipeScreenNutritionalFactsSugarsUnits.setText(
                            recipeNutritionalFact.getUnits());

                } else {}

            }

        }

    }

}
