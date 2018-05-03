package com.Entity;

import java.util.Collection;

public class AddfCount {

    private int CP;
    private Collection<AddfStatus> statusOverTime;
    public AddfCount(int CP, Collection<AddfStatus> statusOverTime) {
        this.CP = CP;
        this.statusOverTime = statusOverTime;

    }

    public AddfCount() {
    }

    public int getCP() {
        return CP;
    }

    public void setCP(int CP) {
        this.CP = CP;
    }

    public Collection<AddfStatus> getstatusOverTime() {
        return statusOverTime;
    }

    public void setstatusOverTime(Collection<AddfStatus> statusOverTime) {
        this.statusOverTime = statusOverTime;
    }
}
