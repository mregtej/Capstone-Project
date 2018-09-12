package com.udacity.mregtej.mymealplanner.ui.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.udacity.mregtej.mymealplanner.R;
import com.udacity.mregtej.mymealplanner.datamodel.Recipe;
import com.udacity.mregtej.mymealplanner.global.MyMealPlannerGlobals;

import java.util.List;

public class MealMenuDayMealtimeAdapter extends
        RecyclerView.Adapter<MealMenuDayMealtimeAdapter.ViewHolder> {

    /** Aspect Ratio of Recipe Image (PosterWidth / PosterHeight) */
    private static final double RECIPE_IMAGE_ASPECT_RATIO = 0.80;

    /**ScreenWidth (in px) - Runtime resize of GridView elements */
    private final int mScreenWidth;

    List<Recipe> mMealDayRecipeList;
    private Context mContext;
    private MealMenuDayMealtimeClickListener mMealMenuDayMealtimeClickListener;


    //--------------------------------------------------------------------------------|
    //                                 Constructors                                   |
    //--------------------------------------------------------------------------------|

    public MealMenuDayMealtimeAdapter(List<Recipe> recipes, MealMenuDayMealtimeClickListener listener) {
        this.mMealDayRecipeList = recipes;
        this.mMealMenuDayMealtimeClickListener = listener;
        mScreenWidth = getScreenWidth();
    }


    //--------------------------------------------------------------------------------|
    //                              Override Methods                                  |
    //--------------------------------------------------------------------------------|

    @NonNull
    @Override
    public MealMenuDayMealtimeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                                    int viewType) {
        mContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.view_mealtime_menu, parent, false);
        return new MealMenuDayMealtimeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealMenuDayMealtimeAdapter.ViewHolder holder,
                                 int position) {

        // Retrieve i-ingredient from shopping ingredient list
        Recipe recipe = mMealDayRecipeList.get(position);

        // Set position-tag
        holder.mealMenuDayMealtimeViewLayout.setTag(position);

        // Populate UI elements
        populateUIView(holder, recipe);

        // Set OnViewClickListeners
        setOnViewClickListener(holder);

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

        private final View mealMenuDayMealtimeViewLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            mealMenuDayMealtimeViewLayout = itemView;

            // Resize film poster size
            resizeRecipeCard();

        }

        private void resizeRecipeCard() {
            int width;
            int height;
            switch (mContext.getResources().getConfiguration().orientation) {
                case MyMealPlannerGlobals.LANDSCAPE_VIEW: // Landscape Mode
                    width = ((mScreenWidth - 16) / MyMealPlannerGlobals.MEAL_MENU_DAY_RECIPE_GV_LAND_COLUMN_NUMB);
                    height = (int) (width / RECIPE_IMAGE_ASPECT_RATIO);
                    mealMenuDayMealtimeViewLayout.setLayoutParams(new LinearLayout.LayoutParams(width, height));
                    break;
                case MyMealPlannerGlobals.PORTRAIT_VIEW: // Portrait Mode
                default:
                    width = ((mScreenWidth - 16) / MyMealPlannerGlobals.MEAL_MENU_DAY_RECIPE_GV_PORT_COLUMN_NUMB);
                    height = (int) (width / RECIPE_IMAGE_ASPECT_RATIO);
                    mealMenuDayMealtimeViewLayout.setLayoutParams(new LinearLayout.LayoutParams(width, height));
                    break;
            }
        }
    }

    //--------------------------------------------------------------------------------|
    //                          Fragment--> Activity Comm                             |
    //--------------------------------------------------------------------------------|

    public interface MealMenuDayMealtimeClickListener {
        public void onMealMenuDayMealtimeClick(int position);
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
    private void populateUIView(MealMenuDayMealtimeAdapter.ViewHolder holder, Recipe recipe) {
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
    private void setOnViewClickListener(final MealMenuDayMealtimeAdapter.ViewHolder holder) {
        holder.mealMenuDayMealtimeViewLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mMealMenuDayMealtimeClickListener != null) {
                    mMealMenuDayMealtimeClickListener.onMealMenuDayMealtimeClick(
                            (int)holder.mealMenuDayMealtimeViewLayout.getTag());
                }
            }
        });
    }

    private static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    private static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

}
