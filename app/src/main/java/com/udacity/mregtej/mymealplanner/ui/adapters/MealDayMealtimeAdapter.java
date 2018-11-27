package com.udacity.mregtej.mymealplanner.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.udacity.mregtej.mymealplanner.R;
import com.udacity.mregtej.mymealplanner.datamodel.Recipe;
import com.udacity.mregtej.mymealplanner.ui.utils.UrlUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MealDayMealtimeAdapter extends RecyclerView.Adapter<MealDayMealtimeAdapter.ViewHolder> {

    LinkedHashMap<String,Recipe> mMealDayRecipeList;
    private Context mContext;


    //--------------------------------------------------------------------------------|
    //                                 Constructors                                   |
    //--------------------------------------------------------------------------------|

    public MealDayMealtimeAdapter(LinkedHashMap<String, Recipe> mMealDayRecipeList) {
        this.mMealDayRecipeList = mMealDayRecipeList;
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


        // Retrieve
        Set<Map.Entry<String, Recipe>> mealTimeSet = mMealDayRecipeList.entrySet();
        Map.Entry<String, Recipe> mealTimeElement =
                (new ArrayList<Map.Entry<String, Recipe>>(mealTimeSet)).get(position);
        String mealtime = mealTimeElement.getKey();
        Recipe recipe = mealTimeElement.getValue();

        // Set position-tag
        holder.mealDayMealtimeViewLayout.setTag(position);

        // Populate UI elements
        populateUIView(holder, mealtime, recipe);

    }

    @Override
    public int getItemCount() {
        return mMealDayRecipeList.size();
    }


    //--------------------------------------------------------------------------------|
    //                             Getters / Setters                                  |
    //--------------------------------------------------------------------------------|

    public LinkedHashMap<String, Recipe> getmMealDayRecipeList() {
        return mMealDayRecipeList;
    }


    //--------------------------------------------------------------------------------|
    //                              Support Classes                                   |
    //--------------------------------------------------------------------------------|

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_mealday_mealtime_title)
        TextView mealTimeTitle;
        @BindView(R.id.iv_mealday_mealtime_recipe_photo)
        ImageView mealTimeRecipePhoto;
        @BindView(R.id.tv_mealday_mealtime_recipe_name)
        TextView mealTimeRecipeName;
        private final View mealDayMealtimeViewLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            mealDayMealtimeViewLayout = itemView;
            ButterKnife.bind(this, itemView);
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
    private void populateUIView(MealDayMealtimeAdapter.ViewHolder holder, String mealTime,
                                Recipe recipe) {
        holder.mealTimeTitle.setText(mealTime);
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

}
