package com.Entity;

import java.util.Collection;

public class AddfCount {

    private int CP;
    private Collection<AddfStatus> statuses;
    public AddfCount(int CP, Collection<AddfStatus> statuses) {
        this.CP = CP;
        this.statuses = statuses;

    }

    public AddfCount() {
    }

    public int getCP() {
        return CP;
    }

    public void setCP(int CP) {
        this.CP = CP;
    }

    public Collection<AddfStatus> getStatuses() {
        return statuses;
    }

    public void setStatuses(Collection<AddfStatus> statuses) {
        this.statuses = statuses;
    }
}
