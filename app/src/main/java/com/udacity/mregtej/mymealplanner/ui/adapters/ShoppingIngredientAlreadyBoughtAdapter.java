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
import com.udacity.mregtej.mymealplanner.datamodel.ShoppingIngredient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShoppingIngredientAlreadyBoughtAdapter
        extends RecyclerView.Adapter<ShoppingIngredientAlreadyBoughtAdapter.ViewHolder> {

    List<ShoppingIngredient> mShoppingIngredientList;
    private Context mContext;
    private ShoppingIngredientAlreadyBoughtClickListener mShoppingIngredientAlreadyBoughtClickListener;


    //--------------------------------------------------------------------------------|
    //                                 Constructors                                   |
    //--------------------------------------------------------------------------------|

    public ShoppingIngredientAlreadyBoughtAdapter(List<ShoppingIngredient> ingredients,
                                                  ShoppingIngredientAlreadyBoughtClickListener listener ) {
        this.mShoppingIngredientList = ingredients;
        mShoppingIngredientAlreadyBoughtClickListener = listener;
    }


    //--------------------------------------------------------------------------------|
    //                              Override Methods                                  |
    //--------------------------------------------------------------------------------|

    @NonNull
    @Override
    public ShoppingIngredientAlreadyBoughtAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.view_shopping_ingredient_already_bought, parent, false);
        return new ShoppingIngredientAlreadyBoughtAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingIngredientAlreadyBoughtAdapter.ViewHolder holder, int position) {

        // Retrieve i-ingredient from shopping ingredient list
        ShoppingIngredient ingredient = mShoppingIngredientList.get(position);

        // Set position-tag
        holder.shoppingIngredientViewLayout.setTag(position);

        // Populate UI elements
        populateUIView(holder, ingredient);

        // Set OnClickListener
        setOnViewClickListener(holder);

    }

    @Override
    public int getItemCount() {
        return mShoppingIngredientList.size();
    }


    //--------------------------------------------------------------------------------|
    //                               Public methods                                   |
    //--------------------------------------------------------------------------------|

    public void removeItem(int i) {
        mShoppingIngredientList.remove(i);
    }

    public void addItem(ShoppingIngredient ingredient) {
        mShoppingIngredientList.add(ingredient);
    }

    public ShoppingIngredient getItem(int i) { return mShoppingIngredientList.get(i); }


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
        @BindView(R.id.iv_shopping_ingredient_remove)
        ImageView ingredientBackToBuyList;
        private final View shoppingIngredientViewLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            shoppingIngredientViewLayout = itemView;
            ButterKnife.bind(this, itemView);
        }

    }


    //--------------------------------------------------------------------------------|
    //                          Fragment--> Activity Comm                             |
    //--------------------------------------------------------------------------------|

    public interface ShoppingIngredientAlreadyBoughtClickListener {
        public void onShoppingIngredientBackToBuyListClick(int position);
    }


    //--------------------------------------------------------------------------------|
    //                                 UI Methods                                     |
    //--------------------------------------------------------------------------------|

    /**
     * Populate UI view elements
     *
     * @param holder     ViewHolder (View container)
     * @param ingredient Shopping Ingredient object
     */
    private void populateUIView(ShoppingIngredientAlreadyBoughtAdapter.ViewHolder holder, ShoppingIngredient ingredient) {
        holder.ingredientName.setText(ingredient.getName());
        holder.ingredientQuantity.setText(ingredient.getQuantity());
        holder.ingredientUnits.setText(ingredient.getUnits());
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
        holder.ingredientBackToBuyList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mShoppingIngredientAlreadyBoughtClickListener != null) {
                    mShoppingIngredientAlreadyBoughtClickListener.
                            onShoppingIngredientBackToBuyListClick((int)holder.shoppingIngredientViewLayout.getTag());
                }
            }
        });
    }

}

