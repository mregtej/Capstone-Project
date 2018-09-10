package com.udacity.mregtej.mymealplanner.ui;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.udacity.mregtej.mymealplanner.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecipeActivity extends AppCompatActivity {

    private static final String FRAGMENT_NAME_SAVE_INSTANCE_KEY = "fragment-name";
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

    private String sFragmentName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        ButterKnife.bind(this);

        ablAppbarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle(getString(R.string.recipe_screen_default_recipe_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbarLayout.setTitle(" ");//carefull there should a space between double quote otherwise it wont work
                    isShow = false;
                }
            }
        });

        if (savedInstanceState == null) {
            // Load MealPlansFragment by default
            sFragmentName = getString(R.string.recipe_screen_title);
            getSupportFragmentManager().
                    beginTransaction().replace(R.id.fl_recipe_screen_fragment_container, new RecipeDataFragment(), sFragmentName).commit();
        } else {
            sFragmentName = savedInstanceState.getString(FRAGMENT_NAME_SAVE_INSTANCE_KEY);
            getSupportFragmentManager().findFragmentByTag(sFragmentName);
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(FRAGMENT_NAME_SAVE_INSTANCE_KEY, sFragmentName);
        super.onSaveInstanceState(outState);
    }

    @OnClick({R.id.iv_recipe_icon_bookmark, R.id.iv_recipe_icon_plan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_recipe_icon_bookmark:
                break;
            case R.id.iv_recipe_icon_plan:
                break;
        }
    }
}
