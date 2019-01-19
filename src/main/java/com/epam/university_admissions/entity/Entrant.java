package com.epam.university_admissions.entity;

import com.epam.university_admissions.dao.ConnectionPool;

import java.sql.Connection;

public class Entrant extends Entity{

    private String iin;
    private String city;
    private String district;
    private String schoolName;
    private int userId;
    private boolean BlockedStatus;

    public Entrant(String iin, String city, String district, String schoolName, int userId) {
        this.iin = iin;
        this.city = city;
        this.district = district;
        this.schoolName = schoolName;
        this.userId = userId;
        this.BlockedStatus=false;
    }

    public Entrant(String iin, String city, String district, String schoolName,User user){
        this(iin,city,district,schoolName,user.getId());
    }
    Connection connection=ConnectionPool.getInstance().getConnection();
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
                ", userId=" + userId +
                ", BlockedStatus=" + BlockedStatus +
                '}';
    }
}
