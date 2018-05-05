package com.Entity;

import java.util.Objects;

public class MajorRoadCountingPoint{


    private int cp;
    private String region;
    private String localAuthority;
    private double latitude;
    private double longitude;
    private String road;
    private String roadName;
    private String roadCategory;
    private double linkLengthKm;

    public MajorRoadCountingPoint() {
    }

    public MajorRoadCountingPoint(int cp, double latitude, double longitude) {
        this.cp = cp;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getCp() {
        return cp;
    }

    public void setCp(int cp) {
        this.cp = cp;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MajorRoadCountingPoint)) return false;
        MajorRoadCountingPoint that = (MajorRoadCountingPoint) o;
        return getCp() == that.getCp();
    }

    @Override
    public int hashCode() {

        return Objects.hash(getCp());
    }
}
