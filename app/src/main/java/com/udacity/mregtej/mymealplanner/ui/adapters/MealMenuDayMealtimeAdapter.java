package com.udacity.mregtej.mymealplanner.ui.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.udacity.mregtej.mymealplanner.R;
import com.udacity.mregtej.mymealplanner.datamodel.Recipe;
import com.udacity.mregtej.mymealplanner.global.MyMealPlannerGlobals;
import com.udacity.mregtej.mymealplanner.ui.utils.UrlUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MealMenuDayMealtimeAdapter extends
        RecyclerView.Adapter<MealMenuDayMealtimeAdapter.ViewHolder> {

    /** Aspect Ratio of Recipe Image (PosterWidth / PosterHeight) */
    private static final double RECIPE_IMAGE_ASPECT_RATIO = 0.80;

    /**ScreenWidth (in px) - Runtime resize of GridView elements */
    private final int mScreenWidth;

    LinkedHashMap<String,Recipe> mMealList;
    private Context mContext;
    private MealMenuDayMealtimeClickListener mMealMenuDayMealtimeClickListener;
    private static String[] mMealTimeTitles;


    //--------------------------------------------------------------------------------|
    //                                 Constructors                                   |
    //--------------------------------------------------------------------------------|

    public MealMenuDayMealtimeAdapter(LinkedHashMap<String,Recipe> mealRecipes,
                                      MealMenuDayMealtimeClickListener listener) {
        this.mMealList = mealRecipes;
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
        mMealTimeTitles = mContext.getResources().getStringArray(R.array.mealtime_titles);
        return new MealMenuDayMealtimeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealMenuDayMealtimeAdapter.ViewHolder holder,
                                 int position) {

        // Retrieve
        Set<Map.Entry<String, Recipe>> mealTimeSet = mMealList.entrySet();
        Map.Entry<String, Recipe> mealTimeElement = (new ArrayList<Map.Entry<String, Recipe>>(mealTimeSet)).get(position);
        String mealtime = mealTimeElement.getKey();
        Recipe recipe = mealTimeElement.getValue();

        // Set position-tag
        holder.mealMenuDayMealtimeViewLayout.setTag(position);

        // Populate UI elements
        populateUIView(holder, mealtime, recipe);

        // Set OnViewClickListeners
        setOnViewClickListener(holder);

    }

    @Override
    public int getItemCount() {
        return mMealList.size();
    }


    //--------------------------------------------------------------------------------|
    //                             Getters / Setters                                  |
    //--------------------------------------------------------------------------------|

    public HashMap<String, Recipe> getmMealList() {
        return mMealList;
    }


    //--------------------------------------------------------------------------------|
    //                              Support Classes                                   |
    //--------------------------------------------------------------------------------|

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_mealtime_menu_meal_title)
        TextView mealTimeTitle;
        @BindView(R.id.iv_mealtime_menu_meal_update)
        ImageView mealTimeRecipeUpdate;
        @BindView(R.id.iv_mealtime_menu_meal_recipe_photo)
        ImageView mealTimeRecipePhoto;
        @BindView(R.id.tv_mealtime_menu_meal_recipe_name)
        TextView mealTimeRecipeName;

        private final View mealMenuDayMealtimeViewLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            mealMenuDayMealtimeViewLayout = itemView;
            ButterKnife.bind(this, itemView);
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
        public void onMealRecipeClick(Recipe recipe);
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
    private void populateUIView(MealMenuDayMealtimeAdapter.ViewHolder holder, String mealtime,
                                Recipe recipe) {
        holder.mealTimeTitle.setText(mealtime);
        // TODO Implement recipe update button
        // TODO Handle retrieved empty/null recipes
        String imageUrl = recipe.getImageUrl();
        if(imageUrl != null && !imageUrl.isEmpty() && UrlUtils.isValid(imageUrl)) {
            Picasso.get()
                    .load(imageUrl)
                    .error(ContextCompat.getDrawable(mContext, R.drawable.im_recipe))
                    .fit()
                    .into(holder.mealTimeRecipePhoto);
        }
        holder.mealTimeRecipeName.setText(recipe.getTitle());
    }

    //--------------------------------------------------------------------------------|
    //                              Support Methods                                   |
    //--------------------------------------------------------------------------------|

    /**
     * Set a recipe click-listener on the meal-day menu view
     *
     * @param    holder    ViewHolder (View container)
     */
    private void setOnViewClickListener(final MealMenuDayMealtimeAdapter.ViewHolder holder) {
        holder.mealMenuDayMealtimeViewLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mMealMenuDayMealtimeClickListener != null) {
                    mMealMenuDayMealtimeClickListener.onMealRecipeClick(
                            mMealList.get(holder.mealTimeTitle.getText().toString()));
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
