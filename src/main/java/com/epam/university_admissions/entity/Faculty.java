package com.epam.university_admissions.entity;

public class Faculty extends Entity {

    private String nameRu;
    private String nameEn;
    private int totalSeats;
    private int budgetSeats;

    public Faculty(String nameRu, String nameEn, int totalSeats, int budgetSeats) {
        this.nameRu = nameRu;
        this.nameEn = nameEn;
        this.totalSeats = totalSeats;
        this.budgetSeats = budgetSeats;
    }

    public String getNameRu() {
        return nameRu;
    }

    public void setNameRu(String nameRu) {
        this.nameRu = nameRu;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public int getBudgetSeats() {
        return budgetSeats;
    }

    public void setBudgetSeats(int budgetSeats) {
        this.budgetSeats = budgetSeats;
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "nameRu='" + nameRu + '\'' +
                ", nameEn='" + nameEn + '\'' +
                ", totalSeats=" + totalSeats +
                ", budgetSeats=" + budgetSeats +
                '}';
    }
}
