package com.epam.university_admissions.entity;

public enum Role {
    CLIENT,ADMIN;

    public String getRole(){
        return name().toUpperCase();
    }

}
