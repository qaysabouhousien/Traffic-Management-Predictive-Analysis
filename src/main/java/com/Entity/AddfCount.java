package com.Entity;

import java.util.Collection;

public class AddfCount {

//  Each Addf Count Has MajorRoadCountingPoint Attr which contains the point details
// And A collection of statuses where each status represents its Addf Count Data in a year.
    private MajorRoadCountingPoint point;
    private Collection<AddfStatus> statusOverTime;
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
