package com.career.talentomobile.climeviewer.util;

import java.math.BigDecimal;

/**
 * Created by malkomich on 04/10/2016.
 */
public class MathUtils {

    public static double celsiusToFahrenheit(double celsius) {
        return celsius * 9 / 5.0 + 32;
    }

    public static double roundDouble(double number, int precision) {
        return new BigDecimal(number)
            .setScale(precision, BigDecimal.ROUND_HALF_EVEN)
            .doubleValue();
    }
}
