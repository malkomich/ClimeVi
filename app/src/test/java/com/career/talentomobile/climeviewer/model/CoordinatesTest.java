package com.career.talentomobile.climeviewer.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CoordinatesTest {

    private static final double LAT = 41.65518;
    private static final double LON = -4.72372;

    private Coordinates coordinates;

    @Before
    public void setUp() {
        coordinates = new Coordinates(LAT, LON);
    }

    @Test
    public void equalsWithWrongObject() {

        assertFalse(coordinates.equals(new Object()));
    }

    @Test
    public void equalsFalse() {

        Coordinates coordinatesMock = mock(Coordinates.class);
        when(coordinatesMock.getLatitude()).thenReturn(LAT);
        when(coordinatesMock.getLongitude()).thenReturn(LAT);

        assertFalse(coordinates.equals(coordinatesMock));
    }

    @Test
    public void equalsTrue() {

        Coordinates coordinatesMock = mock(Coordinates.class);
        when(coordinatesMock.getLatitude()).thenReturn(LAT);
        when(coordinatesMock.getLongitude()).thenReturn(LON);

        assertTrue(coordinates.equals(coordinatesMock));
    }

    @Test
    public void isLatitudeFarAway() {

        Coordinates coordinatesMock = mock(Coordinates.class);
        when(coordinatesMock.getLatitude()).thenReturn(LAT + 3.1);
        when(coordinatesMock.getLongitude()).thenReturn(LON + 1.0);

        assertFalse(coordinates.isNear(coordinatesMock));
    }

    @Test
    public void isLongitudeFarAway() {

        Coordinates coordinatesMock = mock(Coordinates.class);
        when(coordinatesMock.getLatitude()).thenReturn(LAT + 1.0);
        when(coordinatesMock.getLongitude()).thenReturn(LON + 3.1);

        assertFalse(coordinates.isNear(coordinatesMock));
    }

    @Test
    public void isNear() {

        Coordinates coordinatesMock = mock(Coordinates.class);
        when(coordinatesMock.getLatitude()).thenReturn(LAT + 3.0);
        when(coordinatesMock.getLongitude()).thenReturn(LON + 3.0);

        assertTrue(coordinates.isNear(coordinatesMock));
    }

}