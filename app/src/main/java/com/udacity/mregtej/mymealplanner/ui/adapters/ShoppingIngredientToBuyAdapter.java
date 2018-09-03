package com.udacity.mregtej.mymealplanner.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.udacity.mregtej.mymealplanner.datamodel.ShoppingIngredient;
import com.udacity.mregtej.mymealplanner.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShoppingIngredientToBuyAdapter
        extends RecyclerView.Adapter<ShoppingIngredientToBuyAdapter.ViewHolder> {

    List<ShoppingIngredient> mShoppingIngredientList;
    private Context mContext;


    //--------------------------------------------------------------------------------|
    //                                 Constructors                                   |
    //--------------------------------------------------------------------------------|

    public ShoppingIngredientToBuyAdapter(List<ShoppingIngredient> ingredients) {
        this.mShoppingIngredientList = ingredients;
    }


    //--------------------------------------------------------------------------------|
    //                              Override Methods                                  |
    //--------------------------------------------------------------------------------|

    @NonNull
    @Override
    public ShoppingIngredientToBuyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                                        int viewType) {
        mContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.view_shopping_ingredient_to_buy, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        // Retrieve i-ingredient from shopping ingredient list
        ShoppingIngredient ingredient = mShoppingIngredientList.get(position);

        // Set position-tag
        holder.shoppingIngredientViewLayout.setTag(position);

        // Populate UI elements
        populateUIView(holder, ingredient);

    }

    @Override
    public int getItemCount() {
        return mShoppingIngredientList.size();
    }


    //--------------------------------------------------------------------------------|
    //                             Getters / Setters                                  |
    //--------------------------------------------------------------------------------|

    public List<ShoppingIngredient> getmShoppingIngredientList() {
        return mShoppingIngredientList;
    }


    //--------------------------------------------------------------------------------|
    //                              Support Classes                                   |
    //--------------------------------------------------------------------------------|

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_shopping_ingredient_name)
        TextView ingredientName;
        @BindView(R.id.tv_shopping_ingredient_quantity)
        TextView ingredientQuantity;
        @BindView(R.id.tv_shopping_ingredient_units)
        TextView ingredientUnits;
        @BindView(R.id.iv_shopping_ingredient_check)
        ImageView ingredientBought;
        private final View shoppingIngredientViewLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            shoppingIngredientViewLayout = itemView;
            ButterKnife.bind(this, itemView);
        }

    }


    //--------------------------------------------------------------------------------|
    //                                 UI Methods                                     |
    //--------------------------------------------------------------------------------|

    /**
     * Populate UI view elements
     *
     * @param holder        ViewHolder (View container)
     * @param ingredient    Shopping Ingredient object
     */
    private void populateUIView(ViewHolder holder, ShoppingIngredient ingredient) {
        holder.ingredientName.setText(ingredient.getName());
        holder.ingredientQuantity.setText(ingredient.getQuantity());
        holder.ingredientUnits.setText(ingredient.getUnits());
    }

}
