package com.udacity.mregtej.mymealplanner.ui;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.udacity.mregtej.mymealplanner.R;
import com.udacity.mregtej.mymealplanner.datamodel.RecipeIngredient;
import com.udacity.mregtej.mymealplanner.ui.adapters.RecipeIngredientsToBuyAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeIngredientsFragment extends Fragment {

    /** Key for retrieving the recipe ingredients from Arguments */
    private static final String RECIPE_INGREDIENTS_LIST_KEY = "recipe-ingredient-list";
    /** Key for storing the recipe ingredients to buy in savedInstanceState */
    private static final String RECIPE_INGREDIENTS_TO_BUY_LIST_KEY = "recipe-ingredient-to-buy-list";
    /** Key for storing the recipe ingredients already bought in savedInstanceState */
    private static final String RECIPE_INGREDIENTS_ALREADY_BOUGHT_LIST_KEY = "recipe-ingredient-already-bought-list";

    private static final int MAX_NUMBER_OF_SERVINGS = 40;
    private static final int MIN_NUMBER_OF_SERVINGS = 1;

    @BindView(R.id.tv_recipe_screen_ingredients_servings_number)
    TextView tvRecipeScreenIngredientsServingsNumber;
    @BindView(R.id.iv_recipe_screen_ingredients_increase_servings)
    ImageView ivRecipeScreenIngredientsIncreaseServings;
    @BindView(R.id.iv_recipe_screen_ingredients_decrease_servings)
    ImageView ivRecipeScreenIngredientsDecreaseServings;
    @BindView(R.id.iv_recipe_screen_ingredients_to_buy_title_collapse)
    ImageView ivRecipeScreenIngredientsToBuyTitleCollapse;
    @BindView(R.id.iv_recipe_screen_ingredients_to_buy_header_check_all)
    ImageView ivRecipeScreenIngredientsToBuyHeaderCheckAll;
    @BindView(R.id.iv_recipe_screen_ingredients_already_bought_title_collapse)
    ImageView ivRecipeScreenIngredientsAlreadyBoughtTitleCollapse;
    @BindView(R.id.iv_recipe_screen_ingredients_already_bought_header_remove_all)
    ImageView ivRecipeScreenIngredientsAlreadyBoughtHeaderRemoveAll;

    private ArrayList<RecipeIngredient> mRecipeIngredients;
    private ArrayList<RecipeIngredient> mRecipeIngredientsToBuy;
    private ArrayList<RecipeIngredient> mRecipeIngredientsAlreadyBought;

    /** RecyclerView instance */
    private RecyclerView rvRecipeScreenIngredientsToBuy;
    /** RecyclerView LayoutManager instance */
    private RecyclerView.LayoutManager mRecipeScreenIngredientsToBuyLayoutManager;
    /** Recipe Steps Custom ArrayAdapter */
    private RecipeIngredientsToBuyAdapter mRecipeIngredientsToBuyAdapter;
    /** List state stored in savedInstanceState */
    private Parcelable mListStateRecipeIngredientsToBuy;

    /** RecyclerView instance */
    private RecyclerView rvRecipeScreenIngredientsAlreadyBought;
    /** RecyclerView LayoutManager instance */
    private RecyclerView.LayoutManager mRecipeScreenIngredientsAlreadyBoughtLayoutManager;
    /** Recipe Steps Custom ArrayAdapter */
    // private RecipeIngredientsAlreadyBoughtAdapter mRecipeScreenIngredientsAlreadyBoughtAdapter;
    /** List state stored in savedInstanceState */
    private Parcelable mListStateRecipeIngredientsAlreadyBought;

    Unbinder unbinder;

    public RecipeIngredientsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_recipe_ingredients, container, false);

        rvRecipeScreenIngredientsToBuy =
                (RecyclerView) rootView.findViewById(R.id.rv_recipe_screen_ingredients_to_buy_body_table);
        rvRecipeScreenIngredientsAlreadyBought =
                (RecyclerView) rootView.findViewById(R.id.rv_recipe_screen_ingredients_already_bought_body_table);

        // Load & set GridLayout
        rvRecipeScreenIngredientsToBuy.setHasFixedSize(true);
        rvRecipeScreenIngredientsAlreadyBought.setHasFixedSize(true);
        setRecyclerViewLayoutManager(rootView);

        if (savedInstanceState != null) {

            mRecipeIngredientsToBuy = savedInstanceState.
                    getParcelableArrayList(RECIPE_INGREDIENTS_TO_BUY_LIST_KEY);
            if (mRecipeIngredientsToBuy != null) {
                mRecipeIngredientsToBuyAdapter = new RecipeIngredientsToBuyAdapter(mRecipeIngredientsToBuy);
                rvRecipeScreenIngredientsToBuy.setAdapter(mRecipeIngredientsToBuyAdapter);
                mRecipeIngredientsToBuyAdapter.notifyDataSetChanged();
            }

        } else {

            mRecipeIngredients = getArguments().getParcelableArrayList(RECIPE_INGREDIENTS_LIST_KEY);
            if (mRecipeIngredients != null) {
                // Mark all ingredients as "to buy" at first time
                mRecipeIngredientsToBuyAdapter = new RecipeIngredientsToBuyAdapter(mRecipeIngredients);
                rvRecipeScreenIngredientsToBuy.setAdapter(mRecipeIngredientsToBuyAdapter);
                mRecipeIngredientsToBuyAdapter.notifyDataSetChanged();
            }

        }

        // Set OnClickListeners
        setOnClickListeners();

        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.iv_recipe_screen_ingredients_increase_servings,
            R.id.iv_recipe_screen_ingredients_decrease_servings,
            R.id.iv_recipe_screen_ingredients_to_buy_title_collapse,
            R.id.iv_recipe_screen_ingredients_to_buy_header_check_all,
            R.id.iv_recipe_screen_ingredients_already_bought_title_collapse,
            R.id.iv_recipe_screen_ingredients_already_bought_header_remove_all})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_recipe_screen_ingredients_increase_servings:
                increaseServings();
                break;
            case R.id.iv_recipe_screen_ingredients_decrease_servings:
                decreaseServings();
                break;
            case R.id.iv_recipe_screen_ingredients_to_buy_title_collapse:
                break;
            case R.id.iv_recipe_screen_ingredients_to_buy_header_check_all:
                break;
            case R.id.iv_recipe_screen_ingredients_already_bought_title_collapse:
                break;
            case R.id.iv_recipe_screen_ingredients_already_bought_header_remove_all:
                break;
        }
    }

    /**
     * Set OnClickListeners for:
     *  - Expand/Collapse to buy list button
     *  - Expand/Collapse already bought list button
     *  - Mark all ingredients as bought button
     *  - Remove all bought ingredients button
     */
    private void setOnClickListeners() {

    }

    private void setRecyclerViewLayoutManager(View rootView) {
        mRecipeScreenIngredientsToBuyLayoutManager = new GridLayoutManager(rootView.getContext(),
                1, GridLayoutManager.VERTICAL, false);
        rvRecipeScreenIngredientsToBuy.setLayoutManager(mRecipeScreenIngredientsToBuyLayoutManager);
    }

    private void increaseServings() {
        int servings = Integer.parseInt(tvRecipeScreenIngredientsServingsNumber.getText().toString());
        if(servings < MAX_NUMBER_OF_SERVINGS) {
            tvRecipeScreenIngredientsServingsNumber.setText(String.valueOf(servings+1));
        }
    }

    private void decreaseServings() {
        int servings = Integer.parseInt(tvRecipeScreenIngredientsServingsNumber.getText().toString());
        if(servings > MIN_NUMBER_OF_SERVINGS) {
            tvRecipeScreenIngredientsServingsNumber.setText(String.valueOf(servings-1));
        }
    }

}
