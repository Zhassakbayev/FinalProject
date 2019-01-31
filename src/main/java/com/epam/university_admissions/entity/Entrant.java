package com.epam.university_admissions.entity;

import com.epam.university_admissions.dao.ConnectionPool;

import java.sql.Connection;

public class Entrant extends User{

    private String iin;
    private String city;
    private String district;
    private String schoolName;
    private boolean BlockedStatus;

    public Entrant(String iin, String city, String district, String schoolName ) {
        this.iin = iin;
        this.city = city;
        this.district = district;
        this.schoolName = schoolName;
        this.BlockedStatus=false;
    }

    public Entrant(){}

    public String getIin() {
        return iin;
    }

    public void setIin(String iin) {
        this.iin = iin;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public boolean getBlockedStatus() {
        return BlockedStatus;
    }

    public void setBlockedStatus(boolean blockedStatus) {
        BlockedStatus = blockedStatus;
    }

    @Override
    public String toString() {
        return "Entrant{" +
                "entrant_id=" + getId() +
                ", iin=" + iin +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", schoolName='" + schoolName + '\'' +
                ", BlockedStatus=" + BlockedStatus +
                '}';
    }
}
