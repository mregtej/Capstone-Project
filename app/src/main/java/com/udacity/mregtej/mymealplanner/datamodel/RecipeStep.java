package com.udacity.mregtej.mymealplanner.datamodel;

import android.os.Parcel;
import android.os.Parcelable;

public class RecipeStep implements Parcelable {

    public RecipeStep(Parcel in) {}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    public static final Creator<RecipeStep> CREATOR = new Creator<RecipeStep>() {
        @Override
        public RecipeStep createFromParcel(Parcel source) {
            return new RecipeStep(source);
        }

        @Override
        public RecipeStep[] newArray(int size) {
            return new RecipeStep[size];
        }
    };

}
