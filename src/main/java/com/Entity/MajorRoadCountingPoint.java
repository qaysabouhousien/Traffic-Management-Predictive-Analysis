package com.Entity;

import java.util.Objects;

public class MajorRoadCountingPoint{


    private int cp;
    private String region;
    private String localAuthority;
    private double latitude;
    private double longitude;
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

    public MajorRoadCountingPoint(int cp, String region, String localAuthority,
                                  double latitude, double longitude, String roadName,
                                  String roadCategory, double linkLengthKm) {
        this.cp = cp;
        this.region = region;
        this.localAuthority = localAuthority;
        this.latitude = latitude;
        this.longitude = longitude;
        this.roadName = roadName;
        this.roadCategory = roadCategory;
        this.linkLengthKm = linkLengthKm;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getLocalAuthority() {
        return localAuthority;
    }

    public void setLocalAuthority(String localAuthority) {
        this.localAuthority = localAuthority;
    }

    public String getRoadName() {
        return roadName;
    }

    public void setRoadName(String roadName) {
        this.roadName = roadName;
    }

    public String getRoadCategory() {
        return roadCategory;
    }

    public void setRoadCategory(String roadCategory) {
        this.roadCategory = roadCategory;
    }

    public double getLinkLengthKm() {
        return linkLengthKm;
    }

    public void setLinkLengthKm(double linkLengthKm) {
        this.linkLengthKm = linkLengthKm;
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
//        If the CP is the same Then its the Same CP.
        return getCp() == that.getCp();
    }

    @Override
    public int hashCode() {

        return Objects.hash(getCp());
    }
}
