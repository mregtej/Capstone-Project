package com.udacity.mregtej.mymealplanner.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.udacity.mregtej.mymealplanner.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyAccountFragment extends Fragment {

    @BindView(R.id.iv_my_account_user_photo)
    ImageView ivMyAccountUserPhoto;
    @BindView(R.id.iv_my_account_user_name)
    TextView ivMyAccountUserName;
    @BindView(R.id.iv_my_account_statistics)
    ImageView ivMyAccountStatistics;
    @BindView(R.id.iv_my_account_statistics_description)
    TextView ivMyAccountStatisticsDescription;
    @BindView(R.id.bt_my_account_send_feedback)
    Button btMyAccountSendFeedback;
    @BindView(R.id.bt_my_account_about_me)
    Button btMyAccountAboutMe;
    @BindView(R.id.tv_my_account_favourite_recipes_number)
    TextView tvMyAccountFavouriteRecipesNumber;
    @BindView(R.id.tv_my_account_my_meals_number)
    TextView tvMyAccountMyMealsNumber;
    @BindView(R.id.tv_my_account_planned_meals_number)
    TextView tvMyAccountPlannedMealsNumber;

    Unbinder unbinder;
    ActionBar actionBar;

    public MyAccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Hide ActionBar
        hideActionBar();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_account, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    private void hideActionBar() {
        actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null && actionBar.isShowing()) {
            actionBar.hide();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
