package com.career.talentomobile.climeviewer.model;

/**
 * Geo Location of a place which wraps the latitude and longitude values.
 */
public class Coordinates {

    private final double latitude;
    private final double longitude;

    public Coordinates(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Gets the latitude of the geo location
     *
     * @return Latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Gets the longitude of the geo location
     *
     * @return Longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals()
     */
    @Override
    public boolean equals(Object object) {
        return object instanceof Coordinates &&
            ((Coordinates)object).getLatitude() == latitude &&
            ((Coordinates)object).getLongitude() == longitude;
    }

    /**
     * Check if the current coordinates are near to the given coordinates.
     *
     * @param coords
     *               Geo location of a second place
     * @return boolean
     */
    public boolean isNear(Coordinates coords) {
        boolean latitudeValid = Math.abs(Math.abs(latitude) - Math.abs(coords.getLatitude())) <= 3;
        boolean longitudeValid = Math.abs(Math.abs(longitude) - Math.abs(coords.getLongitude())) <= 3;
        return latitudeValid && longitudeValid;
    }
}
