package com.career.talentomobile.climeviewer.util;

import java.math.BigDecimal;

/**
 * Utils class for mathematical operations.
 */
public class MathUtils {

    /**
     * Converts temperature in celsius to fahrenheit.
     *
     * @param celsius
     *                Temperature in celsius
     * @return double
     */
    public static double celsiusToFahrenheit(double celsius) {
        return celsius * 9 / 5.0 + 32;
    }

    /**
     * Round a double number and limits the precision.
     *
     * @param number
     *               Number to convert
     * @param precision
     *                  Number of decimal characters
     * @return double
     */
    public static double roundDouble(double number, int precision) {
        return new BigDecimal(number)
            .setScale(precision, BigDecimal.ROUND_HALF_EVEN)
            .doubleValue();
    }
}
