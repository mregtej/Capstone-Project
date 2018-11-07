package com.udacity.mregtej.mymealplanner.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import com.udacity.mregtej.mymealplanner.R;
import com.udacity.mregtej.mymealplanner.ui.utils.CalendarViewScrollable;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class AddMealDayToCalendarDialogFragment extends DialogFragment {

    private static final String YEAR_KEY = "year";
    private static final String MONTH_KEY = "month";
    private static final String DAY_KEY = "day";

    @BindView(R.id.cv_mealday_calendar)
    CalendarViewScrollable cvMealDayCalendar;
    @BindView(R.id.bt_mealday_cancel)
    Button btMealdayCancel;
    @BindView(R.id.bt_mealday_view)
    Button btMealdayView;
    @BindView(R.id.bt_mealday_plan)
    Button btMealdayPlan;

    private Toast mToast;
    private Context mContext;
    Unbinder unbinder;

    private int mYear;
    private int mMonth;
    private int mDayOfMonth;

    static AddMealDayToCalendarDialogFragment newInstance() {
        return new AddMealDayToCalendarDialogFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_dialog_add_mealday_to_calendar, container,
                false);
        getDialog().requestWindowFeature(STYLE_NO_TITLE);
        setCancelable(false);
        unbinder = ButterKnife.bind(this, rootView);
        mContext = getContext();

        if (savedInstanceState != null) {

            mYear = savedInstanceState.getInt(YEAR_KEY);
            mMonth = savedInstanceState.getInt(MONTH_KEY);
            mDayOfMonth = savedInstanceState.getInt(DAY_KEY);

            // Update Date on Calendar View
            updateCalendarView();

        } else {

            // Set Current Date on Calendar View
            setCurrentDateOnCalendarView();

            // Show Initial instructions
            showInitialInstructions();

        }

        // Set-Up Calendar listeners
        setCalendarViewListeners();

        return rootView;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(YEAR_KEY, mYear);
        outState.putInt(MONTH_KEY, mMonth);
        outState.putInt(DAY_KEY, mDayOfMonth);
    }

    private void showInitialInstructions() {
        if (mToast != null) {
            mToast.cancel();
        }
        mToast = Toast.makeText(mContext, mContext.getString(R.string.add_mealday_to_calendar_choose_day_instructions), Toast.LENGTH_LONG);
        mToast.show();
    }

    private void setCurrentDateOnCalendarView() {
        // Get current date
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(calendar.getTime());
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
    }

    private void setCalendarViewListeners() {
        // Set OnDateChangeListener (user chooses a different date)
        cvMealDayCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month,
                                            int dayOfMonth) {
                // Store date
                mYear = year;
                mMonth = month;
                mDayOfMonth = dayOfMonth;
            }
        });
    }



    private void updateCalendarView() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(mYear, mMonth, mDayOfMonth, 0, 0);
        cvMealDayCalendar.setDate(calendar.getTimeInMillis());
    }

    @OnClick({R.id.bt_mealday_cancel, R.id.bt_mealday_view, R.id.bt_mealday_plan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_mealday_cancel:
                dismiss();
                break;
            case R.id.bt_mealday_view:
                showMealDayCalendarDialogFragment();
                break;
            case R.id.bt_mealday_plan:
                // TODO Store MealDay on local DB
                if (mToast != null) {
                    mToast.cancel();
                }
                mToast = Toast.makeText(mContext, mContext.getString(R.string.not_yet_implemented),
                        Toast.LENGTH_SHORT);
                mToast.show();
                break;
        }
    }

    private void showMealDayCalendarDialogFragment() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.addToBackStack(null);
        DialogFragment newFragment = MealDayCalendarDialogFragment.newInstance(mYear, mMonth,
                mDayOfMonth);
        newFragment.show(ft, "dialog");
    }

}
