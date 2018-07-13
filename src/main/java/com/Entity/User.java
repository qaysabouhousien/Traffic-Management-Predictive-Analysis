package com.Entity;

import java.sql.Date;
import java.util.Objects;

/**
 * abstract User class extended in Admin and Program Manager.
 * @author - Qays
 */
public abstract class User {

    private String id;
    private String name;
    private String password;
    private Date registration_date;
    private String type;

    public User(){}

    /**
     *
     * @param id user id
     * @param name user name
     * @param password user password
     * @param registration_date reg date
     * @param type user type(admin/manager)
     */
    public User(String id, String name, String password, Date registration_date, String type) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.registration_date = registration_date;
        this.type = type;
    }

    /**
     * this constructor is used on login operation, since we only have a user name and a password from the user
     * @param name user Name
     * @param password User password
     */
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

    /**
     * compares the objects using userName
     * @param o other user to compare to
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return this.getName().equalsIgnoreCase(user.getName());
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
