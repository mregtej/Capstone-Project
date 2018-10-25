package com.udacity.mregtej.mymealplanner.ui.utils;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class NumberUtils {

    public static double truncateQuantity(double quantity) {
        double truncatedQty = 0;
        DecimalFormat df = new DecimalFormat("#.#");
        df.setRoundingMode(RoundingMode.CEILING);
        String sf = df.format(quantity);
        truncatedQty = Double.valueOf(sf);
        return truncatedQty;
    }

}
