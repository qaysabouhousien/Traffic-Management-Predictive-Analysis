package com.Entity;


public class AddfStatus {


    private int year;
    private int allMV;
    private double trafficCapacityRatio;
    private int trafficStatus;
    private String estimationMethod;

    public AddfStatus(int year,String estimationMethod, int allMV, double trafficCapacityRatio, int trafficStatus) {
        this.year = year;
        this.estimationMethod= estimationMethod;
        this.allMV = allMV;
        this.trafficCapacityRatio = trafficCapacityRatio;
        this.trafficStatus = trafficStatus;
    }

    public AddfStatus() {
    }


    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getAllMV() {
        return allMV;
    }

    public String getEstimationMethod() {
        return estimationMethod;
    }

    public void setEstimationMethod(String estimationMethod) {
        this.estimationMethod = estimationMethod;
    }

    public void setAllMV(int allMV) {
        this.allMV = allMV;
    }

    public double getTrafficCapacityRatio() {
        return trafficCapacityRatio;
    }

    public void setTrafficCapacityRatio(double trafficCapacityRatio) {
        this.trafficCapacityRatio = trafficCapacityRatio;
    }

    public int getTrafficStatus() {
        return trafficStatus;
    }

    public void setTrafficStatus(int trafficStatus) {
        this.trafficStatus = trafficStatus;
    }
}

