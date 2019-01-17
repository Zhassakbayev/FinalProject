package com.epam.university_admissions.entity;

public class FacultiesReport {

    private int facultyId;
    private String facultyName;
    private int iin;
    private String firstName;
    private String lastName;
    private String secondName;
    private int totalPointScore;

    public FacultiesReport(int facultyId, String facultyName, int iin, String firstName,
                           String lastName, String secondName, int totalPointScore) {
        this.facultyId = facultyId;
        this.facultyName = facultyName;
        this.iin = iin;
        this.firstName = firstName;
        this.lastName = lastName;
        this.secondName = secondName;
        this.totalPointScore = totalPointScore;
    }

    public int getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(int facultyId) {
        this.facultyId = facultyId;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public int getIin() {
        return iin;
    }

    public void setIin(int iin) {
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
                ", facultyName='" + facultyName + '\'' +
                ", iin=" + iin +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", totalPointScore=" + totalPointScore +
                '}';
    }
}
