package com.udacity.mregtej.mymealplanner.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.udacity.mregtej.mymealplanner.R;
import com.udacity.mregtej.mymealplanner.ui.utils.BottomNavigationViewHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeActivity extends AppCompatActivity {

    @BindView(R.id.iv_recipe_photo)
    ImageView ivRecipePhoto;
    @BindView(R.id.tv_recipe_name)
    TextView tvRecipeName;
    @BindView(R.id.app_bar)
    Toolbar appBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        ButterKnife.bind(this);
    }

}
