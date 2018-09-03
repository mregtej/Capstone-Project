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
import android.widget.ImageView;

import com.udacity.mregtej.mymealplanner.R;
import com.udacity.mregtej.mymealplanner.datamodel.ShoppingIngredient;
import com.udacity.mregtej.mymealplanner.ui.adapters.ShoppingIngredientAlreadyBoughtAdapter;
import com.udacity.mregtej.mymealplanner.ui.adapters.ShoppingIngredientToBuyAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShoppingListFragment extends Fragment {

    /** Key for storing the list state in savedInstanceState */
    private static final String SHOPPING_INGREDIENTS_TO_BUY_LIST_STATE_KEY = "shopping-ingredients-to-buy-list-state";

    /** Key for storing the list state in savedInstanceState */
    private static final String SHOPPING_INGREDIENTS_ALREADY_BOUGHT_LIST_STATE_KEY = "shopping-ingredients-already-bought-list-state";

    /** Key for storing the ingredients to buy in savedInstanceState */
    private static final String SHOPPING_INGREDIENTS_TO_BUY_KEY = "shopping-ingredients-to-buy";

    /** Key for storing the ingredients already bought in savedInstanceState */
    private static final String SHOPPING_INGREDIENTS_ALREADY_BOUGHT_KEY = "shopping-ingredients-already-bought";

    @BindView(R.id.iv_shopping_list_to_buy_title_collapse)
    ImageView ivShoppingListToBuyTitleCollapse;
    @BindView(R.id.iv_shopping_list_to_buy_header_check_all)
    ImageView ivShoppingListToBuyHeaderCheckAll;
    @BindView(R.id.rv_shopping_list_to_buy_body_table)
    RecyclerView rvShoppingListToBuyBodyTable;
    @BindView(R.id.iv_shopping_list_already_bought_title_collapse)
    ImageView ivShoppingListAlreadyBoughtTitleCollapse;
    @BindView(R.id.iv_shopping_list_already_bought_header_remove_all)
    ImageView ivShoppingListAlreadyBoughtHeaderRemoveAll;
    @BindView(R.id.rv_shopping_list_already_bought_body_table)
    RecyclerView rvShoppingListAlreadyBoughtBodyTable;

    Unbinder unbinder;
    ActionBar actionBar;
    /** List state stored in savedInstanceState */
    private Parcelable mListStateIngredientsToBuy;
    private Parcelable mListStateIngredientsAlreadyBought;

    /** RecyclerView LayoutManager instance */
    private RecyclerView.LayoutManager mShoppingIngredientsToBuyLayoutManager;
    /** Shopping Ingredients Custom ArrayAdapter */
    private ShoppingIngredientToBuyAdapter mShoppingIngredientsToBuyAdapter;
    /** RecyclerView LayoutManager instance */
    private RecyclerView.LayoutManager mShoppingIngredientsAlreadyBoughtLayoutManager;
    /** Shopping Ingredients Custom ArrayAdapter */
    private ShoppingIngredientAlreadyBoughtAdapter mShoppingIngredientsAlreadyBoughtAdapter;

    public ShoppingListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Set ActionBar Title
        setActionBarTitle(getString(R.string.shopping_list_screen_title));

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shopping_list, container, false);
        unbinder = ButterKnife.bind(this, view);

        // Load & set GridLayout
        rvShoppingListToBuyBodyTable.setHasFixedSize(true);
        rvShoppingListAlreadyBoughtBodyTable.setHasFixedSize(true);
        setRecyclerViewLayoutManager(view);

        if (savedInstanceState != null) {

            List<ShoppingIngredient> ingredientsToBuy = savedInstanceState.
                    getParcelableArrayList(SHOPPING_INGREDIENTS_TO_BUY_KEY);
            List<ShoppingIngredient> ingredientsAlreadyBought = savedInstanceState.
                    getParcelableArrayList(SHOPPING_INGREDIENTS_ALREADY_BOUGHT_KEY);

            mShoppingIngredientsToBuyAdapter = new ShoppingIngredientToBuyAdapter(ingredientsToBuy);
            mShoppingIngredientsAlreadyBoughtAdapter = new ShoppingIngredientAlreadyBoughtAdapter(ingredientsAlreadyBought);

            rvShoppingListToBuyBodyTable.setAdapter(mShoppingIngredientsToBuyAdapter);
            mShoppingIngredientsToBuyAdapter.notifyDataSetChanged();
            rvShoppingListAlreadyBoughtBodyTable.setAdapter(mShoppingIngredientsAlreadyBoughtAdapter);
            mShoppingIngredientsAlreadyBoughtAdapter.notifyDataSetChanged();

        } else {

            ArrayList<ShoppingIngredient> ingredientsToBuy = new ArrayList<>();
            ingredientsToBuy.add(new ShoppingIngredient("Spaguetti Noodles", "250", "g", false));
            ingredientsToBuy.add(new ShoppingIngredient("Bacon", "100", "g", false));
            ingredientsToBuy.add(new ShoppingIngredient("Milk", "200", "ml", false));
            mShoppingIngredientsToBuyAdapter = new ShoppingIngredientToBuyAdapter(ingredientsToBuy);

            ArrayList<ShoppingIngredient> ingredientsAlreadyBought = new ArrayList<>();
            ingredientsAlreadyBought.add(new ShoppingIngredient("Salt", "", "tspc", false));
            ingredientsAlreadyBought.add(new ShoppingIngredient("Pepper", "", "tspc", false));
            mShoppingIngredientsAlreadyBoughtAdapter = new ShoppingIngredientAlreadyBoughtAdapter(ingredientsAlreadyBought);

            // Set Adapter and notifyDataSetChanged
            rvShoppingListToBuyBodyTable.setAdapter(mShoppingIngredientsToBuyAdapter);
            mShoppingIngredientsToBuyAdapter.notifyDataSetChanged();
            rvShoppingListAlreadyBoughtBodyTable.setAdapter(mShoppingIngredientsAlreadyBoughtAdapter);
            mShoppingIngredientsAlreadyBoughtAdapter.notifyDataSetChanged();

        }

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mListStateIngredientsToBuy = mShoppingIngredientsToBuyLayoutManager.onSaveInstanceState();
        mListStateIngredientsAlreadyBought = mShoppingIngredientsAlreadyBoughtLayoutManager.onSaveInstanceState();

        outState.putParcelable(SHOPPING_INGREDIENTS_TO_BUY_LIST_STATE_KEY, mListStateIngredientsToBuy);
        outState.putParcelable(SHOPPING_INGREDIENTS_ALREADY_BOUGHT_LIST_STATE_KEY, mListStateIngredientsToBuy);

        outState.putParcelableArrayList(SHOPPING_INGREDIENTS_TO_BUY_KEY,
                (ArrayList<ShoppingIngredient>) mShoppingIngredientsToBuyAdapter.getmShoppingIngredientList());
        outState.putParcelableArrayList(SHOPPING_INGREDIENTS_ALREADY_BOUGHT_KEY,
                (ArrayList<ShoppingIngredient>) mShoppingIngredientsAlreadyBoughtAdapter.getmShoppingIngredientList());
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if(savedInstanceState != null) {
            mListStateIngredientsToBuy = savedInstanceState.getParcelable(SHOPPING_INGREDIENTS_TO_BUY_LIST_STATE_KEY);
            mListStateIngredientsAlreadyBought = savedInstanceState.getParcelable(SHOPPING_INGREDIENTS_ALREADY_BOUGHT_LIST_STATE_KEY);
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

    /**
     * Set RecyclerView's LayoutManager.
     * Different layouts for portrait and landscape mode
     *
     * @param rootView Activity view
     */
    private void setRecyclerViewLayoutManager(View rootView) {
        mShoppingIngredientsToBuyLayoutManager = new GridLayoutManager(rootView.getContext(), 1);
        rvShoppingListToBuyBodyTable.setLayoutManager(mShoppingIngredientsToBuyLayoutManager);
        mShoppingIngredientsAlreadyBoughtLayoutManager = new GridLayoutManager(rootView.getContext(), 1);
        rvShoppingListAlreadyBoughtBodyTable.setLayoutManager(mShoppingIngredientsAlreadyBoughtLayoutManager);
    }

}
