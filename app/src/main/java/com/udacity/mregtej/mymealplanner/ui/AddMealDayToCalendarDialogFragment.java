package com.udacity.mregtej.mymealplanner.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.udacity.mregtej.mymealplanner.R;
import com.udacity.mregtej.mymealplanner.ui.utils.CalendarViewScrollable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class AddMealDayToCalendarDialogFragment extends DialogFragment {

    @BindView(R.id.cv_mealday_calendar)
    CalendarViewScrollable cvMealdayCalendar;

    private Toast mToast;
    private Context mContext;
    Unbinder unbinder;

    static AddMealDayToCalendarDialogFragment newInstance() {
        return new AddMealDayToCalendarDialogFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dialog_add_mealday_to_calendar,
                container, false);
        unbinder = ButterKnife.bind(this, rootView);
        mContext = getContext();

        showInitialInstructions();

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void showInitialInstructions() {
        if(mToast != null) { mToast.cancel(); }
        mToast = Toast.makeText(
                mContext,
                mContext.getString(R.string.add_mealday_to_calendar_choose_day_instructions),
                Toast.LENGTH_LONG);
        mToast.show();
    }

}
