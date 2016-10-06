package com.github.malkomich.climeviewer.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MathUtilsTest {

    @Test
    public void celsiusToFahrenheit() {
        double celsius = 25.25;
        double fahrenheit = MathUtils.celsiusToFahrenheit(celsius);
        assertEquals(77.45, fahrenheit, 0.001);
    }

    @Test
    public void roundDoubleNegativePrecision() {
        double number = 25.2555555554;
        double rounded = MathUtils.roundDouble(number, -1);
        // If precision is negative, it is rounded by units, by dozens...
        assertEquals(30, rounded, 0.1);
    }

    @Test
    public void roundDoublePrecisionZero() {
        double number = 25.2555555554;
        double rounded = MathUtils.roundDouble(number, 0);
        assertEquals(25, rounded, 0.1);
    }

    @Test
    public void roundDouble() throws Exception {
        double number = 25.2555555554;
        double rounded = MathUtils.roundDouble(number, 1);
        assertEquals(25.3, rounded, 0.01);
    }

}