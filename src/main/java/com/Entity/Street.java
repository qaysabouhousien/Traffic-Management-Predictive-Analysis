package com.Entity;


public class Street {


    private String region;
    private String localAuthority;
    private String road;
    private String roadCategory;

    public Street(){}

    public Street( String region, String localAuthority, String road,String roadCategory) {
        this.region = region;
        this.localAuthority = localAuthority;
        this.road = road;
        this.roadCategory = roadCategory;
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

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
    }


    public String getRoadCategory() {
        return roadCategory;
    }

    public void setRoadCategory(String roadCategory) {
        this.roadCategory = roadCategory;
    }
}
