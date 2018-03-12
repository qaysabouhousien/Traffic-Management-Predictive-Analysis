package com.Entity;

public class MajorRoadCountingPoint extends CountingPoint{



    private String aJunction;
    private String bJunction;
    private double linkLengthMiles;
    private double linkLengthKm;
    private double aRefE;
    private double aRefN;
    private double bRefE;
    private double bRefN;

    public MajorRoadCountingPoint(){super();}

    public MajorRoadCountingPoint(int cp, String region, String localAuthority, String localAuthorityCode, int sRefE, int sRefN, double sRefLatitude, double sRefLongitude, String road, String roadName, String roadCategory, String aJunction, String bJunction, double linkLengthMiles, double linkLengthKm, double aRefE, double aRefN, double bRefE, double bRefN) {
        super(cp, region, localAuthority, localAuthorityCode, sRefE, sRefN, sRefLatitude, sRefLongitude, road, roadName, roadCategory);
        this.aJunction = aJunction;
        this.bJunction = bJunction;
        this.linkLengthMiles = linkLengthMiles;
        this.linkLengthKm = linkLengthKm;
        this.aRefE = aRefE;
        this.aRefN = aRefN;
        this.bRefE = bRefE;
        this.bRefN = bRefN;
    }

    public String getaJunction() {
        return aJunction;
    }

    public void setaJunction(String aJunction) {
        this.aJunction = aJunction;
    }

    public String getbJunction() {
        return bJunction;
    }

    public void setbJunction(String bJunction) {
        this.bJunction = bJunction;
    }



    public double getLinkLengthMiles() {
        return linkLengthMiles;
    }

    public void setLinkLengthMiles(double linkLengthMiles) {
        this.linkLengthMiles = linkLengthMiles;
    }

    public double getLinkLengthKm() {
        return linkLengthKm;
    }

    public void setLinkLengthKm(double linkLengthKm) {
        this.linkLengthKm = linkLengthKm;
    }

    public double getaRefE() {
        return aRefE;
    }

    public void setaRefE(double aRefE) {
        this.aRefE = aRefE;
    }

    public double getaRefN() {
        return aRefN;
    }

    public void setaRefN(double aRefN) {
        this.aRefN = aRefN;
    }

    public double getbRefE() {
        return bRefE;
    }

    public void setbRefE(double bRefE) {
        this.bRefE = bRefE;
    }

    public double getbRefN() {
        return bRefN;
    }

    public void setbRefN(double bRefN) {
        this.bRefN = bRefN;
    }
}
