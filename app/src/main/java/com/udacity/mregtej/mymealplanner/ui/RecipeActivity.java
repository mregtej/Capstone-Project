package com.udacity.mregtej.mymealplanner.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.mregtej.mymealplanner.R;
import com.udacity.mregtej.mymealplanner.datamodel.Recipe;
import com.udacity.mregtej.mymealplanner.ui.utils.DateUtils;
import com.udacity.mregtej.mymealplanner.ui.utils.UrlUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecipeActivity extends AppCompatActivity {

    private static final String FRAGMENT_NAME_SAVE_INSTANCE_KEY = "fragment-name";

    private static final String RECIPE_KEY = "recipe";

    @BindView(R.id.iv_recipe_photo)
    ImageView ivRecipePhoto;
    @BindView(R.id.tv_recipe_name)
    TextView tvRecipeName;
    @BindView(R.id.tv_recipe_author)
    TextView tvRecipeAuthor;
    @BindView(R.id.tv_recipe_data_timer_time)
    TextView tvRecipeDataTimerTime;
    @BindView(R.id.iv_recipe_icon_bookmark)
    ImageView ivRecipeIconBookmark;
    @BindView(R.id.iv_recipe_icon_plan)
    ImageView ivRecipeIconPlan;
    @BindView(R.id.app_bar)
    Toolbar appBar;
    @BindView(R.id.collapsing_toolbar_layout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.abl_appbar_layout)
    AppBarLayout ablAppbarLayout;
    @BindView(R.id.v_recipe_photo_scrim)
    View vRecipePhotoScrim;
    @BindView(R.id.ll_recipe_main_data)
    FrameLayout llRecipeMainData;
    @BindView(R.id.iv_recipe_data_timer_icon)
    ImageView ivRecipeDataTimerIcon;
    @BindView(R.id.ll_recipe_timer_data)
    FrameLayout llRecipeTimerData;
    @BindView(R.id.fl_recipe_header)
    FrameLayout flRecipeHeader;
    @BindView(R.id.fl_recipe_screen_fragment_container)
    FrameLayout flRecipeScreenFragmentContainer;

    private String sFragmentName;
    private Recipe mRecipe;
    private Toast mToast;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        ButterKnife.bind(this);
        mContext = this;

        if (savedInstanceState == null) {

            if (getIntent().hasExtra(RECIPE_KEY)) {
                mRecipe = getIntent().getParcelableExtra(RECIPE_KEY);
            }

            // Load MealPlansFragment by default
            Fragment recipeFragment = new RecipeDataFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable(RECIPE_KEY, mRecipe);
            recipeFragment.setArguments(bundle);
            sFragmentName = getString(R.string.recipe_screen_title);
            getSupportFragmentManager().
                    beginTransaction().replace(R.id.fl_recipe_screen_fragment_container,
                    recipeFragment, sFragmentName).commit();
        } else {
            sFragmentName = savedInstanceState.getString(FRAGMENT_NAME_SAVE_INSTANCE_KEY);
            mRecipe = savedInstanceState.getParcelable(RECIPE_KEY);
            getSupportFragmentManager().findFragmentByTag(sFragmentName);
        }

        // Update UI elements using recipe data retrieved from Realtime Database
        updateUI();

        // Set Collapsing ActionBar Listener
        setActionBarChangedListener();

        // Set Backward Navigation Click Listeners
        setBackwardNavigationClickListener();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(FRAGMENT_NAME_SAVE_INSTANCE_KEY, sFragmentName);
        outState.putParcelable(RECIPE_KEY, mRecipe);
        super.onSaveInstanceState(outState);
    }

    /**
     * Set Backward Navigation Click Listener (ActionBar)
     */
    private void setBackwardNavigationClickListener() {
        appBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    /**
     * Set Collapsing ActionBar Listener for handling the collapsing / expanding behaviour
     */
    private void setActionBarChangedListener() {
        ablAppbarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle(mRecipe.getTitle());
                    ivRecipeIconBookmark.setVisibility(View.GONE);
                    ivRecipeIconPlan.setVisibility(View.GONE);
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbarLayout.setTitle(" ");
                    ivRecipeIconBookmark.setVisibility(View.VISIBLE);
                    ivRecipeIconPlan.setVisibility(View.VISIBLE);
                    isShow = false;
                }
            }
        });
    }

    /**
     * Update UI elements using recipe data retrieved from Realtime Database
     */
    private void updateUI() {

        // Set Recipe Title
        String recipeTitle = mRecipe.getTitle();
        if (recipeTitle != null && !recipeTitle.isEmpty() && !recipeTitle.equals(" ")) {
            tvRecipeName.setText(recipeTitle);
        }

        // Set Recipe Author
        tvRecipeName.setText(mRecipe.getTitle());
        String author = mRecipe.getAuthor();
        if (author != null && !author.isEmpty() && !author.equals(" ")) {
            tvRecipeAuthor.setText(author);
        }

        // Set Cooking Time
        tvRecipeDataTimerTime.setText(DateUtils.formatTime(mRecipe.getCookingTime()));

        // Set Recipe Image
        String imageUrl = mRecipe.getImageUrl();
        if (imageUrl != null && !imageUrl.isEmpty() && UrlUtils.isValid(imageUrl)) {
            Picasso.get().load(imageUrl).error(ContextCompat.getDrawable(this,
                    R.drawable.im_recipe)).fit().into(ivRecipePhoto);
        }
    }

    @OnClick({R.id.iv_recipe_icon_bookmark, R.id.iv_recipe_icon_plan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_recipe_icon_bookmark:
                // TODO Implement Bookmarks
                if (mToast != null) { mToast.cancel(); }
                mToast = Toast.makeText(mContext, getString(R.string.not_yet_implemented),
                        Toast.LENGTH_SHORT);
                mToast.show();
                break;
            case R.id.iv_recipe_icon_plan:
                // TODO Implmentent Add To Calendar
                if (mToast != null) { mToast.cancel(); }
                mToast = Toast.makeText(mContext, getString(R.string.not_yet_implemented),
                        Toast.LENGTH_SHORT);
                mToast.show();
                break;
        }
    }
}
