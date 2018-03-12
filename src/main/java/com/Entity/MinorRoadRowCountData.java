package com.Entity;

import java.sql.Date;

public class MinorRoadRowCountData extends RowCountData {



    public MinorRoadRowCountData(){super();}


    public MinorRoadRowCountData(int cp,char iDir, int year, Date dCount, int hour, int pc, int twoWMV, int car, int bus, int lvg, int hgvr2, int hgvr3, int hgvr4, int hgva5, int hgva6, int hgv, int amv) {
        super(cp, iDir,year, dCount, hour, pc, twoWMV, car, bus, lvg, hgvr2, hgvr3, hgvr4, hgva5, hgva6, hgv, amv);
    }
}
