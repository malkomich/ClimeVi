package com.career.talentomobile.climeviewer;

import java.math.BigDecimal;

/**
 * Created by malkomich on 04/10/2016.
 */
public class Util {

    public static double celsiusToFahrenheit(double celsius) {
        return new BigDecimal(celsius * 9 / 5.0 + 32)
            .setScale(1, BigDecimal.ROUND_HALF_EVEN)
            .doubleValue();
    }
}
