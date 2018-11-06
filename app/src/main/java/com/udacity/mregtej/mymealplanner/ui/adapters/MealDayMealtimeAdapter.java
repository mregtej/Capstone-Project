package com.udacity.mregtej.mymealplanner.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udacity.mregtej.mymealplanner.R;
import com.udacity.mregtej.mymealplanner.datamodel.Recipe;

import java.util.List;

public class MealDayMealtimeAdapter extends RecyclerView.Adapter<MealDayMealtimeAdapter.ViewHolder> {

    List<Recipe> mMealDayRecipeList;
    private Context mContext;


    //--------------------------------------------------------------------------------|
    //                                 Constructors                                   |
    //--------------------------------------------------------------------------------|

    public MealDayMealtimeAdapter(List<Recipe> recipes) {
        this.mMealDayRecipeList = recipes;
    }


    //--------------------------------------------------------------------------------|
    //                              Override Methods                                  |
    //--------------------------------------------------------------------------------|

    @NonNull
    @Override
    public MealDayMealtimeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                                    int viewType) {
        mContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.view_mealday_mealtime_item, parent, false);
        return new MealDayMealtimeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealDayMealtimeAdapter.ViewHolder holder,
                                 int position) {

        // Retrieve i-ingredient from shopping ingredient list
        Recipe recipe = mMealDayRecipeList.get(position);

        // Set position-tag
        holder.mealDayMealtimeViewLayout.setTag(position);

        // Populate UI elements
        populateUIView(holder, recipe);

    }

    @Override
    public int getItemCount() {
        return mMealDayRecipeList.size();
    }


    //--------------------------------------------------------------------------------|
    //                             Getters / Setters                                  |
    //--------------------------------------------------------------------------------|

    public List<Recipe> getmMealDayRecipeList() {
        return mMealDayRecipeList;
    }


    //--------------------------------------------------------------------------------|
    //                              Support Classes                                   |
    //--------------------------------------------------------------------------------|

    class ViewHolder extends RecyclerView.ViewHolder {

        private final View mealDayMealtimeViewLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            mealDayMealtimeViewLayout = itemView;
        }

    }

    //--------------------------------------------------------------------------------|
    //                          Fragment--> Activity Comm                             |
    //--------------------------------------------------------------------------------|

    public interface MealPlannerMealtimeClickListener {
        public void onMealPlannerMealtimeClick(int position);
    }


    //--------------------------------------------------------------------------------|
    //                                 UI Methods                                     |
    //--------------------------------------------------------------------------------|

    /**
     * Populate UI view elements
     *
     * @param holder        ViewHolder (View container)
     * @param recipe        Recipe object
     */
    private void populateUIView(MealDayMealtimeAdapter.ViewHolder holder, Recipe recipe) {
        // TODO fill-in
    }

}
