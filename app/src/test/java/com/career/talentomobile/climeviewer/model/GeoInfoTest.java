package com.career.talentomobile.climeviewer.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GeoInfoTest {

    private static final double NORTH = 42.31184202963337;
    private static final double SOUTH = 38.780592308137166;
    private static final double EAST = -4.625818675180751;
    private static final double WEST = -6.33199;

    private GeoInfo geoInfo;

    @Before
    public void setUp() throws Exception {
        // Preparate mock objects
        GeoStation stationMock1 = mock(GeoStation.class);
        GeoStation stationMock2 = mock(GeoStation.class);
        GeoStation stationMock3 = mock(GeoStation.class);
        when(stationMock1.getGeoPoints()).thenReturn(
            new GeoPoints(NORTH, 41.582116252145596, EAST, -4.821621324819249)
        );
        when(stationMock2.getGeoPoints()).thenReturn(
            new GeoPoints(41.7282457478544, SOUTH, -4.925818675180751, -5.331997538252334)
        );
        when(stationMock3.getGeoPoints()).thenReturn(null);
        when(stationMock3.getCoordinates()).thenReturn(
            new Coordinates(41.70611, WEST)
        );

        geoInfo = new GeoInfo();
        geoInfo.addStation(stationMock1);
        geoInfo.addStation(stationMock2);
        geoInfo.addStation(stationMock3);
    }

    @Test
    public void getAreaPoints() {
        GeoPoints area = geoInfo.getAreaPoints();
        assertEquals(NORTH, area.getNorth(), 0.00001);
        assertEquals(SOUTH, area.getSouth(), 0.00001);
        assertEquals(EAST, area.getEast(), 0.00001);
        assertEquals(WEST, area.getWest(), 0.00001);
    }

}