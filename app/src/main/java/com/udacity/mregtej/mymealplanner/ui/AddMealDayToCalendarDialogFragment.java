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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class AddMealDayToCalendarDialogFragment extends DialogFragment {

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

        // Set-Up Calendar (listeners, UI style...)
        setUpCalendarView();

        // Show Initial instructions
        showInitialInstructions();

        return rootView;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void showInitialInstructions() {
        if (mToast != null) {
            mToast.cancel();
        }
        mToast = Toast.makeText(mContext, mContext.getString(R.string.add_mealday_to_calendar_choose_day_instructions), Toast.LENGTH_LONG);
        mToast.show();
    }

    private void setUpCalendarView() {
        cvMealDayCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month,
                                            int dayOfMonth) {
                // Store date
                mYear = year;
                mMonth = month;
                mDayOfMonth = dayOfMonth;
                // Enable View Day and Plan buttons
                if(!btMealdayView.isEnabled()) { btMealdayView.setEnabled(true); }
                if(!btMealdayPlan.isEnabled()) { btMealdayPlan.setEnabled(true); }
            }
        });
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
