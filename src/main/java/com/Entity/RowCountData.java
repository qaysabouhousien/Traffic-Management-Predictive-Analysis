package com.Entity;

import java.sql.Date;

public abstract class RowCountData {

    private int cp;
    private char iDir;
    private int year;
    private Date dCount;
    private int hour;
    private int pc;
    private int twoWMV;
    private int car;
    private int bus;
    private int lvg;
    private int hgvr2;
    private int hgvr3;
    private int hgvr4;
    private int hgva5;
    private int hgva6;
    private int hgv;
    private int amv;

    public RowCountData() {
    }

    public RowCountData(int cp,char iDir, int year, Date dCount, int hour, int pc, int twoWMV, int car, int bus, int lvg, int hgvr2, int hgvr3, int hgvr4, int hgva5, int hgva6, int hgv, int amv) {
        this.cp = cp;
        this.iDir = iDir;
        this.year = year;
        this.dCount = dCount;
        this.hour = hour;
        this.pc = pc;
        this.twoWMV = twoWMV;
        this.car = car;
        this.bus = bus;
        this.lvg = lvg;
        this.hgvr2 = hgvr2;
        this.hgvr3 = hgvr3;
        this.hgvr4 = hgvr4;
        this.hgva5 = hgva5;
        this.hgva6 = hgva6;
        this.hgv = hgv;
        this.amv = amv;
    }

    public char getiDir() {
        return iDir;
    }

    public void setiDir(char iDir) {
        this.iDir = iDir;
    }

    public int getCp() {
        return cp;
    }

    public void setCp(int cp) {
        this.cp = cp;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Date getdCount() {
        return dCount;
    }

    public void setdCount(Date dCount) {
        this.dCount = dCount;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getPc() {
        return pc;
    }

    public void setPc(int pc) {
        this.pc = pc;
    }

    public int getTwoWMV() {
        return twoWMV;
    }

    public void setTwoWMV(int twoWMV) {
        this.twoWMV = twoWMV;
    }

    public int getCar() {
        return car;
    }

    public void setCar(int car) {
        this.car = car;
    }

    public int getBus() {
        return bus;
    }

    public void setBus(int bus) {
        this.bus = bus;
    }

    public int getLvg() {
        return lvg;
    }

    public void setLvg(int lvg) {
        this.lvg = lvg;
    }

    public int getHgvr2() {
        return hgvr2;
    }

    public void setHgvr2(int hgvr2) {
        this.hgvr2 = hgvr2;
    }

    public int getHgvr3() {
        return hgvr3;
    }

    public void setHgvr3(int hgvr3) {
        this.hgvr3 = hgvr3;
    }

    public int getHgvr4() {
        return hgvr4;
    }

    public void setHgvr4(int hgvr4) {
        this.hgvr4 = hgvr4;
    }

    public int getHgva5() {
        return hgva5;
    }

    public void setHgva5(int hgva5) {
        this.hgva5 = hgva5;
    }

    public int getHgva6() {
        return hgva6;
    }

    public void setHgva6(int hgva6) {
        this.hgva6 = hgva6;
    }

    public int getHgv() {
        return hgv;
    }

    public void setHgv(int hgv) {
        this.hgv = hgv;
    }

    public int getAmv() {
        return amv;
    }

    public void setAmv(int amv) {
        this.amv = amv;
    }
}
