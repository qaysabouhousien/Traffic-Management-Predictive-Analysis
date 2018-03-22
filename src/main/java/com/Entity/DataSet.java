package com.Entity;

import java.sql.Date;

public class DataSet {
    private int id;
    private  String name;
    private Date uploadDate;
    private int adminId;
    public DataSet(String name,int adminId){
        this.name = name;
        this.adminId = adminId;
    }
    public DataSet() { }

    public DataSet(int id, String name, Date uploadDate, int adminId) {
        this.id = id;
        this.name = name;
        this.uploadDate = uploadDate;
        this.adminId = adminId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }
}
