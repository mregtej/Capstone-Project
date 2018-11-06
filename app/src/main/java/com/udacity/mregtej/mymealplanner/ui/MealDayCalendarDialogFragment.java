package com.udacity.mregtej.mymealplanner.ui;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.udacity.mregtej.mymealplanner.R;
import com.udacity.mregtej.mymealplanner.datamodel.Recipe;
import com.udacity.mregtej.mymealplanner.ui.adapters.MealDayMealtimeAdapter;
import com.udacity.mregtej.mymealplanner.ui.utils.DateUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class MealDayCalendarDialogFragment extends DialogFragment {

    /** Key for storing the list state in savedInstanceState */
    private static final String MEALDAY_MEALTIME_RECIPE_LIST_STATE_KEY = "mealday-mealtime-recipe-list-state";

    /** Key for storing the meal-day recipes to buy in savedInstanceState */
    private static final String MEALDAY_MEALTIME_RECIPE_LIST_KEY = "mealday-mealtime-recipe-list";

    private static final String YEAR_KEY = "year";
    private static final String MONTH_KEY = "month";
    private static final String DAY_KEY = "day";

    @BindView(R.id.rv_mealday_mealtime_data)
    RecyclerView rvMealdayMealtimeData;
    @BindView(R.id.bt_mealday_mealtime_back)
    Button btMealdayMealtimeBack;
    @BindView(R.id.tv_mealday_mealtime_date)
    TextView tvMealdayMealtimeDate;

    /**
     * RecyclerView LayoutManager instance
     */
    private RecyclerView.LayoutManager mMealdayMealtimeLayoutManager;
    /**
     * Meal-day Recipes Custom ArrayAdapter
     */
    private MealDayMealtimeAdapter mMealdayMealtimeAdapter;
    /**
     * List state stored in savedInstanceState
     */
    private Parcelable mListStateMealdayMealtime;
    /**
     * ButterKnife un-binder
     */
    Unbinder unbinder;
    private Context mContext;
    private int mYear;
    private int mMonth;
    private int mDay;

    static MealDayCalendarDialogFragment newInstance(int year, int month, int day) {
        MealDayCalendarDialogFragment fragment = new MealDayCalendarDialogFragment();
        Bundle args = new Bundle();
        args.putInt(YEAR_KEY, year);
        args.putInt(MONTH_KEY, month);
        args.putInt(DAY_KEY, day);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mYear = getArguments().getInt(YEAR_KEY);
        mMonth = getArguments().getInt(MONTH_KEY);
        mDay = getArguments().getInt(DAY_KEY);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_dialog_mealday_calendar,
                container, false);
        getDialog().requestWindowFeature(STYLE_NO_TITLE);
        setCancelable(false);
        mContext = getContext();
        unbinder = ButterKnife.bind(this, rootView);

        // Load & set GridLayout
        rvMealdayMealtimeData.setHasFixedSize(true);
        setRecyclerViewLayoutManager(rootView);

        if (savedInstanceState != null) {

            List<Recipe> mealDayRecipes = savedInstanceState.
                    getParcelableArrayList(MEALDAY_MEALTIME_RECIPE_LIST_KEY);
            mYear = savedInstanceState.getInt(YEAR_KEY);
            mMonth = savedInstanceState.getInt(MONTH_KEY);
            mDay = savedInstanceState.getInt(DAY_KEY);
            mMealdayMealtimeAdapter = new MealDayMealtimeAdapter(mealDayRecipes);
            rvMealdayMealtimeData.setAdapter(mMealdayMealtimeAdapter);
            mMealdayMealtimeAdapter.notifyDataSetChanged();

        } else {

            ArrayList<Recipe> mealDayRecipes = new ArrayList<>();
            // TODO Fill in with data retrieved from local DB
            mealDayRecipes.add(null);
            mealDayRecipes.add(null);
            mealDayRecipes.add(null);
            mealDayRecipes.add(null);
            mMealdayMealtimeAdapter = new MealDayMealtimeAdapter(mealDayRecipes);
            // Set Adapter and notifyDataSetChanged
            rvMealdayMealtimeData.setAdapter(mMealdayMealtimeAdapter);
            mMealdayMealtimeAdapter.notifyDataSetChanged();

        }

        // Populate UI
        populateUI();

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.bt_mealday_mealtime_back)
    public void onViewClicked() {
        dismiss();
    }

    private void setRecyclerViewLayoutManager(View rootView) {
        mMealdayMealtimeLayoutManager = new GridLayoutManager(rootView.getContext(),
                1, GridLayoutManager.VERTICAL, false);
        rvMealdayMealtimeData.setLayoutManager(mMealdayMealtimeLayoutManager);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        mListStateMealdayMealtime = mMealdayMealtimeLayoutManager.onSaveInstanceState();
        outState.putParcelable(MEALDAY_MEALTIME_RECIPE_LIST_STATE_KEY, mListStateMealdayMealtime);
        outState.putParcelableArrayList(MEALDAY_MEALTIME_RECIPE_LIST_KEY,
                (ArrayList<Recipe>) mMealdayMealtimeAdapter.getmMealDayRecipeList());
        outState.putInt(YEAR_KEY, mYear);
        outState.putInt(MONTH_KEY, mMonth);
        outState.putInt(DAY_KEY, mDay);

    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            mListStateMealdayMealtime = savedInstanceState.
                    getParcelable(MEALDAY_MEALTIME_RECIPE_LIST_STATE_KEY);
        }
    }

    private void populateUI() {
        tvMealdayMealtimeDate.setText(DateUtils.formatDate(mYear, mMonth, mDay, mContext));
    }

}
