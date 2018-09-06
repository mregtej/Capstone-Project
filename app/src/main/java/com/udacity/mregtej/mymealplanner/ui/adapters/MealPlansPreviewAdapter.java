package com.udacity.mregtej.mymealplanner.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udacity.mregtej.mymealplanner.R;
import com.udacity.mregtej.mymealplanner.datamodel.MealPlan;

import java.util.List;

public class MealPlansPreviewAdapter
        extends RecyclerView.Adapter<MealPlansPreviewAdapter.ViewHolder> {

    List<MealPlan> mMealPlanList;
    private Context mContext;
    private MealPlansPreviewClickListener mMealPlansPreviewClickListener;


    //--------------------------------------------------------------------------------|
    //                                 Constructors                                   |
    //--------------------------------------------------------------------------------|

    public MealPlansPreviewAdapter(List<MealPlan> mealPlans, MealPlansPreviewClickListener listener) {
        this.mMealPlanList = mealPlans;
        this.mMealPlansPreviewClickListener = listener;
    }


    //--------------------------------------------------------------------------------|
    //                              Override Methods                                  |
    //--------------------------------------------------------------------------------|

    @NonNull
    @Override
    public MealPlansPreviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                                 int viewType) {
        mContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.card_meal_plan_preview, parent, false);
        return new MealPlansPreviewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealPlansPreviewAdapter.ViewHolder holder,
                                 int position) {

        // Retrieve i-ingredient from shopping ingredient list
        MealPlan mealPlan = mMealPlanList.get(position);

        // Set position-tag
        holder.mealPlanViewLayout.setTag(position);

        // Populate UI elements
        populateUIView(holder, mealPlan);

        // Set OnClickListener
        setOnViewClickListener(holder);

    }

    @Override
    public int getItemCount() {
        return mMealPlanList.size();
    }


    //--------------------------------------------------------------------------------|
    //                             Getters / Setters                                  |
    //--------------------------------------------------------------------------------|

    public List<MealPlan> getmMealPlanList() {
        return mMealPlanList;
    }


    //--------------------------------------------------------------------------------|
    //                              Support Classes                                   |
    //--------------------------------------------------------------------------------|

    class ViewHolder extends RecyclerView.ViewHolder {

        private final View mealPlanViewLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            mealPlanViewLayout = itemView;
        }

    }

    //--------------------------------------------------------------------------------|
    //                          Fragment--> Activity Comm                             |
    //--------------------------------------------------------------------------------|

    public interface MealPlansPreviewClickListener {
        public void onMealPlansPreviewClickListenerClick(MealPlan mealPlan);
    }


    //--------------------------------------------------------------------------------|
    //                                 UI Methods                                     |
    //--------------------------------------------------------------------------------|

    /**
     * Populate UI view elements
     *
     * @param holder        ViewHolder (View container)
     * @param mealPlan      Meal Plan object
     */
    private void populateUIView(MealPlansPreviewAdapter.ViewHolder holder, MealPlan mealPlan) {
        // TODO fill-in
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
        holder.mealPlanViewLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mMealPlansPreviewClickListener != null) {
                    mMealPlansPreviewClickListener
                            .onMealPlansPreviewClickListenerClick(mMealPlanList.get(
                                    (int)holder.mealPlanViewLayout.getTag()));
                }
            }
        });
    }

}
