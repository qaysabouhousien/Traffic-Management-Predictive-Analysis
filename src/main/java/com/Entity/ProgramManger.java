package com.Entity;

public class ProgramManger extends User{

    private int registrationBy;


    public ProgramManger(){super();}

    public ProgramManger(int registrationBy) {
        this.registrationBy = registrationBy;
    }

    public ProgramManger(String name, String password, int registrationBy) {
        super(name, password);
        this.registrationBy = registrationBy;
    }

    public int getRegistrationBy() {
        return registrationBy;
    }

    public void setRegistrationBy(int registrationBy) {
        this.registrationBy = registrationBy;
    }
}
