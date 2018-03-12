package com.Entity;

public abstract class CountingPoint {

    private int cp;
    private String region;
    private String localAuthority;
    private String localAuthorityCode;
    private int     sRefE;
    private int     sRefN;
    private double sRefLatitude;
    private double sRefLongitude;
    private String road;
    private String roadName;
    private String roadCategory;

    public CountingPoint(){}

    public CountingPoint(int cp, String region, String localAuthority, String localAuthorityCode, int sRefE, int sRefN, double sRefLatitude, double sRefLongitude, String road, String roadName, String roadCategory) {
        this.cp = cp;
        this.region = region;
        this.localAuthority = localAuthority;
        this.localAuthorityCode = localAuthorityCode;
        this.sRefE = sRefE;
        this.sRefN = sRefN;
        this.sRefLatitude = sRefLatitude;
        this.sRefLongitude = sRefLongitude;
        this.road = road;
        this.roadName = roadName;
        this.roadCategory = roadCategory;
    }

    public int getCp() {
        return cp;
    }


    public void setCp(int cp) {
        this.cp = cp;
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

    public String getLocalAuthorityCode() {
        return localAuthorityCode;
    }

    public void setLocalAuthorityCode(String localAuthorityCode) {
        this.localAuthorityCode = localAuthorityCode;
    }

    public int getsRefS() {
        return sRefE;
    }

    public void setsRefE(int sRefE) {
        this.sRefE = sRefE;
    }

    public int getsRefN() {
        return sRefN;
    }

    public void setsRefN(int sRefN) {
        this.sRefN = sRefN;
    }

    public double getsRefLatitude() {
        return sRefLatitude;
    }

    public void setsRefLatitude(double sRefLatitude) {
        this.sRefLatitude = sRefLatitude;
    }

    public double getsRefLongitude() {
        return sRefLongitude;
    }

    public void setsRefLongitude(double sRefLongitude) {
        this.sRefLongitude = sRefLongitude;
    }

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
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
}
