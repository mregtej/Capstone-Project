package com.udacity.mregtej.mymealplanner.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;

import com.udacity.mregtej.mymealplanner.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShoppingListFragment extends Fragment {

    @BindView(R.id.bt_shopping_list_to_buy_collapse)
    Button btShoppingListToBuyCollapse;
    @BindView(R.id.bt_shopping_list_to_buy_ingredients_bought_all)
    Button btShoppingListToBuyIngredientsBoughtAll;
    @BindView(R.id.gv_shopping_list_to_buy_ingredients)
    GridView gvShoppingListToBuyIngredients;
    @BindView(R.id.bt_shopping_list_already_bought_collapse)
    Button btShoppingListAlreadyBoughtCollapse;
    @BindView(R.id.gv_shopping_list_already_bought_ingredients)
    GridView gvShoppingListAlreadyBoughtIngredients;

    Unbinder unbinder;
    ActionBar actionBar;

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
        return view;
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
