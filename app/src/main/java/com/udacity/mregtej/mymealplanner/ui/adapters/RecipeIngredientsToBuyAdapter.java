package com.udacity.mregtej.mymealplanner.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.udacity.mregtej.mymealplanner.R;
import com.udacity.mregtej.mymealplanner.datamodel.RecipeIngredient;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeIngredientsToBuyAdapter
        extends RecyclerView.Adapter<RecipeIngredientsToBuyAdapter.ViewHolder> {

    private List<RecipeIngredient> mRecipeIngredientList;
    private Context mContext;

    //--------------------------------------------------------------------------------|
    //                                 Constructors                                   |
    //--------------------------------------------------------------------------------|

    public RecipeIngredientsToBuyAdapter(List<RecipeIngredient> recipeIngredients) {
        this.mRecipeIngredientList = recipeIngredients;
    }


    //--------------------------------------------------------------------------------|
    //                              Override Methods                                  |
    //--------------------------------------------------------------------------------|

    @NonNull
    @Override
    public RecipeIngredientsToBuyAdapter.ViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.view_recipe_ingredient_to_buy, parent,
                false);
        return new RecipeIngredientsToBuyAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeIngredientsToBuyAdapter.ViewHolder holder,
                                 int position) {

        // Retrieve i-ingredient from shopping ingredient list
        RecipeIngredient ingredient = mRecipeIngredientList.get(position);

        // Set position-tag
        holder.recipeIngredientViewLayout.setTag(position);

        // Populate UI elements
        populateUIView(holder, ingredient);

        // Set OnClickListener
        setOnViewClickListener(holder);

    }

    @Override
    public int getItemCount() {
        return mRecipeIngredientList.size();
    }

    //--------------------------------------------------------------------------------|
    //                              Support Classes                                   |
    //--------------------------------------------------------------------------------|

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_recipe_ingredient_name)
        TextView ingredientName;
        @BindView(R.id.tv_recipe_ingredient_quantity)
        TextView ingredientQuantity;
        @BindView(R.id.tv_recipe_ingredient_units)
        TextView ingredientUnits;
        @BindView(R.id.iv_recipe_ingredient_check)
        ImageView ingredientBought;
        private final View recipeIngredientViewLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            recipeIngredientViewLayout = itemView;
            ButterKnife.bind(this, itemView);
        }

    }

    //--------------------------------------------------------------------------------|
    //                                 UI Methods                                     |
    //--------------------------------------------------------------------------------|

    /**
     * Populate UI view elements
     *
     * @param holder     ViewHolder (View container)
     * @param ingredient Recipe Ingredient object
     */
    private void populateUIView(RecipeIngredientsToBuyAdapter.ViewHolder holder,
                                RecipeIngredient ingredient) {
        holder.ingredientName.setText(ingredient.getIngredient());
        holder.ingredientQuantity.setText(String.valueOf(ingredient.getQuantity()));
        holder.ingredientUnits.setText(ingredient.getMeasure());
    }

    //--------------------------------------------------------------------------------|
    //                              Support Methods                                   |
    //--------------------------------------------------------------------------------|

    /**
     * Set a film click-listener on the film-view
     *
     * @param    holder    ViewHolder (View container)
     */
    private void setOnViewClickListener(final ViewHolder holder) {
        /*
        holder.ingredientBackToBuyList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mShoppingIngredientAlreadyBoughtClickListener != null) {
                    mShoppingIngredientAlreadyBoughtClickListener.
                            onShoppingIngredientBackToBuyListClick(
                                    (int)holder.shoppingIngredientViewLayout.getTag());
                }
            }
        }); */
    }

}
