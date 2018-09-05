package com.udacity.mregtej.mymealplanner.ui;

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

import com.udacity.mregtej.mymealplanner.R;
import com.udacity.mregtej.mymealplanner.datamodel.Recipe;
import com.udacity.mregtej.mymealplanner.global.MyMealPlannerGlobals;
import com.udacity.mregtej.mymealplanner.ui.adapters.RecipeListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeListFragment extends Fragment {

    /* Key for storing the list state in savedInstanceState */
    private static final String RECIPE_LIST_STATE_KEY = "recipe-list-state";

    /** Key for storing the ingredients to buy in savedInstanceState */
    private static final String RECIPE_LIST_KEY = "recipe-list";

    @BindView(R.id.rv_recipe_list)
    RecyclerView rvRecipeList;

    ActionBar actionBar;
    Unbinder unbinder;

    /**
     * RecyclerView LayoutManager instance
     */
    private RecyclerView.LayoutManager mRecipeListLayoutManager;
    /**
     * Shopping Ingredients Custom ArrayAdapter
     */
    private RecipeListAdapter mRecipeListAdapter;
    /**
     * List state stored in savedInstanceState
     */
    private Parcelable mListStateRecipes;


    public RecipeListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Set ActionBar Title
        setActionBarTitle(getString(R.string.recipe_list_screen_title));

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_recipe_list, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        // Load & set GridLayout
        rvRecipeList.setHasFixedSize(true);
        setRecyclerViewLayoutManager(rootView);

        if (savedInstanceState != null) {

            List<Recipe> recipes = savedInstanceState.
                    getParcelableArrayList(RECIPE_LIST_KEY);
            mRecipeListAdapter = new RecipeListAdapter(recipes);
            rvRecipeList.setAdapter(mRecipeListAdapter);
            mRecipeListAdapter.notifyDataSetChanged();

        } else {

            ArrayList<Recipe> recipes = new ArrayList<>();
            // TODO Only used for testing - Dynamic injection
            // TODO Fill in
            recipes.add(null);
            recipes.add(null);
            recipes.add(null);
            recipes.add(null);
            recipes.add(null);
            recipes.add(null);
            recipes.add(null);
            recipes.add(null);
            recipes.add(null);
            recipes.add(null);
            recipes.add(null);
            recipes.add(null);
            mRecipeListAdapter = new RecipeListAdapter(recipes);

            // Set Adapter and notifyDataSetChanged
            rvRecipeList.setAdapter(mRecipeListAdapter);
            mRecipeListAdapter.notifyDataSetChanged();

        }

        return rootView;

    }

    private void setRecyclerViewLayoutManager(View rootView) {

        switch (this.getResources().getConfiguration().orientation) {
            case MyMealPlannerGlobals.LANDSCAPE_VIEW: // Landscape Mode
                mRecipeListLayoutManager = new GridLayoutManager(rootView.getContext(),
                        MyMealPlannerGlobals.RECIPE_GV_LAND_COLUMN_NUMB);
                break;
            case MyMealPlannerGlobals.PORTRAIT_VIEW: // Portrait Mode
            default:
                mRecipeListLayoutManager = new GridLayoutManager(rootView.getContext(),
                        MyMealPlannerGlobals.RECIPE_GV_PORT_COLUMN_NUMB);
                break;
        }
        rvRecipeList.setLayoutManager(mRecipeListLayoutManager);
    }

    private void setActionBarTitle(String title) {
        actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        if(actionBar != null) {
            actionBar.setTitle(title);
            if(!actionBar.isShowing()) {
                actionBar.show();
            }
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mListStateRecipes = mRecipeListLayoutManager.onSaveInstanceState();

        outState.putParcelable(RECIPE_LIST_STATE_KEY, mListStateRecipes);
        outState.putParcelableArrayList(RECIPE_LIST_KEY, (ArrayList<Recipe>)
                mRecipeListAdapter.getmRecipeList());
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            mListStateRecipes = savedInstanceState.getParcelable(RECIPE_LIST_STATE_KEY);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}

