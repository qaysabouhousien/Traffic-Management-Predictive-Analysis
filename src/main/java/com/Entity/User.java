package com.Entity;

import java.sql.Date;
import java.util.Objects;

public abstract class User {

    private String id;
    private String name;
    private String password;
    private Date registration_date;
    private String type;

    public User(){}

    public User(String id, String name, String password, Date registration_date, String type) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.registration_date = registration_date;
        this.type = type;
    }

    public User(String name, String password){
        this.name = name;
        this.password = password;
    }








    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegistration_date() {
        return registration_date;
    }

    public void setRegistration_date(Date registration_date) {
        this.registration_date = registration_date;
    }


    @Override
    public boolean equals(Object o) {
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return this.getName().equalsIgnoreCase(user.getName())&&
                this.getPassword().equals(user.getPassword());
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
