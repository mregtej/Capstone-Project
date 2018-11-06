package com.udacity.mregtej.mymealplanner.ui.utils;

import android.content.Context;
import android.util.Log;

import com.udacity.mregtej.mymealplanner.R;

public class DateUtils {

    private static final String TAG = DateUtils.class.getSimpleName();

    public static String formatTime(String time, Context context) {
        try {
            String[] timeData = time.split(":");
            String outTime = "";
            int hours = Integer.parseInt(timeData[0]);
            int minutes = Integer.parseInt(timeData[1]);
            int seconds = Integer.parseInt(timeData[2]);
            if(hours > 0) {
                outTime = outTime.concat(hours + context.getString(R.string.hr));
            }
            if(minutes > 0) {
                outTime = outTime.concat(minutes + context.getString(R.string.min));
            }
            if(seconds > 0){
                outTime = outTime.concat(seconds + context.getString(R.string.s));
            }
            return outTime;

        } catch(Exception e) {
            Log.w(TAG, context.getString(R.string.invalid_time));
            return context.getString(R.string.unknown);
        }
    }

    public static String formatDate(int year, int month, int day, Context context) {
        String date = "";
        date = date.concat(String.valueOf(day));
        switch(day) {
            case 1:
            case 11:
            case 21:
            case 31:
                date = date.concat(context.getString(R.string.st_days) + " ");
                break;
            case 2:
            case 12:
            case 22:
                date = date.concat(context.getString(R.string.nd_days) + " ");
                break;
            case 3:
            case 13:
            case 23:
                date = date.concat(context.getString(R.string.rd_days ) + " ");
                break;
            default:
                date = date.concat(context.getString(R.string.th_days) + " ");
                break;
        }
        switch(month) {
            case 0:
                date = date.concat(context.getString(R.string.january) + ", ");
                break;
            case 1:
                date = date.concat(context.getString(R.string.feburary) + ", ");
                break;
            case 2:
                date = date.concat(context.getString(R.string.march) + ", ");
                break;
            case 3:
                date = date.concat(context.getString(R.string.april) + ", ");
                break;
            case 4:
                date = date.concat(context.getString(R.string.may) + ", ");
                break;
            case 5:
                date = date.concat(context.getString(R.string.june) + ", ");
                break;
            case 6:
                date = date.concat(context.getString(R.string.july) + ", ");
                break;
            case 7:
                date = date.concat(context.getString(R.string.august) + ", ");
                break;
            case 8:
                date = date.concat(context.getString(R.string.september) + ", ");
                break;
            case 9:
                date = date.concat(context.getString(R.string.october) + ", ");
                break;
            case 10:
                date = date.concat(context.getString(R.string.november) + ", ");
                break;
            case 11:
                date = date.concat(context.getString(R.string.december) + ", ");
                break;
        }
        date = date.concat(String.valueOf(year));
        return date;
    }
}
