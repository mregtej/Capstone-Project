package com.udacity.mregtej.mymealplanner.ui.utils;

import android.util.Log;

public class DateUtils {

    private static final String TAG = DateUtils.class.getSimpleName();

    public static String formatTime(String time) {
        try {
            String[] timeData = time.split(":");
            String outTime = "";
            int hours = Integer.parseInt(timeData[0]);
            int minutes = Integer.parseInt(timeData[1]);
            int seconds = Integer.parseInt(timeData[2]);
            if(hours > 0) {
                outTime = outTime.concat(hours + "hr");
            }
            if(minutes > 0) {
                outTime = outTime.concat(minutes + "min");
            }
            if(seconds > 0){
                outTime = outTime.concat(seconds + "s");
            }
            return outTime;

        } catch(Exception e) {
            Log.w(TAG, "Invalid time");
            return "Unknown";
        }
    }
}
