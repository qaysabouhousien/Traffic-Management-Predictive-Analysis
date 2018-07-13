package com.Entity;

import java.util.Collection;

/**
 *
 * @author - Qays
 */
public class AddfCount {


    private MajorRoadCountingPoint point;
    private Collection<AddfStatus> statusOverTime;

    /**
     * Each Addf Count Has MajorRoadCountingPoint Attr which contains the point details
     * And A collection of statuses where each status represents its Addf Count Data in a year.
     * @param majorRoadCountingPoint counting point that is related the the specific status
     * @param statusOverTime a collection of addf Statuses for the counting point over the years.
     */
    public AddfCount(MajorRoadCountingPoint majorRoadCountingPoint,
                     Collection<AddfStatus> statusOverTime) {
        this.point = majorRoadCountingPoint;
        this.statusOverTime = statusOverTime;

    }

    public AddfCount() {
    }

    public MajorRoadCountingPoint getPoint() {
        return point;
    }

    public void setPoint(MajorRoadCountingPoint point) {
        this.point = point;
    }

    public Collection<AddfStatus> getStatusOverTime() {
        return statusOverTime;
    }

    public void setStatusOverTime(Collection<AddfStatus> statusOverTime) {
        this.statusOverTime = statusOverTime;
    }
}
