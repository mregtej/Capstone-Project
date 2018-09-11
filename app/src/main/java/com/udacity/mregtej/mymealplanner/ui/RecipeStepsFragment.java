package com.udacity.mregtej.mymealplanner.ui;


import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udacity.mregtej.mymealplanner.R;
import com.udacity.mregtej.mymealplanner.datamodel.RecipeStep;
import com.udacity.mregtej.mymealplanner.ui.adapters.RecipeStepsAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeStepsFragment extends Fragment {

    /** Key for storing the list state in savedInstanceState */
    private static final String RECIPE_STEP_LIST_STATE_KEY = "recipe-step-list-state";

    /** Key for storing the recipe steps in savedInstanceState */
    private static final String RECIPE_STEP_LIST_KEY = "recipe-step-list";

    /** RecyclerView instance */
    private RecyclerView rvRecipeStepsList;
    /** RecyclerView LayoutManager instance */
    private RecyclerView.LayoutManager mRecipeStepsLayoutManager;
    /** Recipe Steps Custom ArrayAdapter */
    private RecipeStepsAdapter mRecipeStepsAdapter;
    /** List state stored in savedInstanceState */
    private Parcelable mListStateRecipeSteps;

    public RecipeStepsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_recipe_steps, container, false);

        //
        rvRecipeStepsList = (RecyclerView)rootView.findViewById(R.id.rv_recipe_steps);

        // Load & set GridLayout
        rvRecipeStepsList.setHasFixedSize(true);
        setRecyclerViewLayoutManager(rootView);

        if (savedInstanceState != null) {

            List<RecipeStep> recipeSteps = savedInstanceState.
                    getParcelableArrayList(RECIPE_STEP_LIST_KEY);
            mRecipeStepsAdapter = new RecipeStepsAdapter(recipeSteps);
            rvRecipeStepsList.setAdapter(mRecipeStepsAdapter);
            mRecipeStepsAdapter.notifyDataSetChanged();

        } else {

            ArrayList<RecipeStep> recipeSteps = new ArrayList<>();
            // TODO Fill in
            recipeSteps.add(null);
            recipeSteps.add(null);
            recipeSteps.add(null);
            recipeSteps.add(null);
            recipeSteps.add(null);
            recipeSteps.add(null);
            mRecipeStepsAdapter = new RecipeStepsAdapter(recipeSteps);

            // Set Adapter and notifyDataSetChanged
            rvRecipeStepsList.setAdapter(mRecipeStepsAdapter);
            mRecipeStepsAdapter.notifyDataSetChanged();

        }

        return rootView;
    }

    private void setRecyclerViewLayoutManager(View rootView) {
        mRecipeStepsLayoutManager = new GridLayoutManager(rootView.getContext(), 1,
                GridLayoutManager.VERTICAL, false);
        rvRecipeStepsList.setLayoutManager(mRecipeStepsLayoutManager);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        mListStateRecipeSteps = mRecipeStepsLayoutManager.onSaveInstanceState();
        outState.putParcelable(RECIPE_STEP_LIST_STATE_KEY, mListStateRecipeSteps);
        outState.putParcelableArrayList(RECIPE_STEP_LIST_KEY,
                (ArrayList<RecipeStep>) mRecipeStepsAdapter.getmRecipeSteps());
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if(savedInstanceState != null) {
            mListStateRecipeSteps = savedInstanceState.getParcelable(RECIPE_STEP_LIST_STATE_KEY);
        }
    }

}
