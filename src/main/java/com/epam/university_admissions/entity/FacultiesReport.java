package com.epam.university_admissions.entity;

public class FacultiesReport {

    private int facultyId;
    private String facultyNameRu;
    private String facultyNameEn;
    private String iin;
    private String firstName;
    private String lastName;
    private String secondName;
    private int totalPointScore;

    public FacultiesReport(int facultyId, String facultyNameRu, String facultyNameEn, String iin, String firstName,
                           String lastName, String secondName, int totalPointScore) {
        this.facultyId = facultyId;
        this.facultyNameRu = facultyNameRu;
        this.facultyNameEn = facultyNameEn;
        this.iin = iin;
        this.firstName = firstName;
        this.lastName = lastName;
        this.secondName = secondName;
        this.totalPointScore = totalPointScore;
    }

    public FacultiesReport() {
    }

    public int getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(int facultyId) {
        this.facultyId = facultyId;
    }

    public String getFacultyNameRu() {
        return facultyNameRu;
    }

    public void setFacultyNameRu(String facultyNameRu) {
        this.facultyNameRu = facultyNameRu;
    }

    public String getFacultyNameEn() {
        return facultyNameEn;
    }

    public void setFacultyNameEn(String facultyNameEn) {
        this.facultyNameEn = facultyNameEn;
    }

    public String getIin() {
        return iin;
    }

    public void setIin(String iin) {
        this.iin = iin;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public int getTotalPointScore() {
        return totalPointScore;
    }

    public void setTotalPointScore(int totalPointScore) {
        this.totalPointScore = totalPointScore;
    }

    @Override
    public String toString() {
        return "FacultiesReport{" +
                "facultyId=" + facultyId +
                ", facultyNameRu='" + facultyNameRu + '\'' +
                ", facultyNameEn='" + facultyNameEn + '\'' +
                ", iin=" + iin +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", totalPointScore=" + totalPointScore +
                '}';
    }
}
