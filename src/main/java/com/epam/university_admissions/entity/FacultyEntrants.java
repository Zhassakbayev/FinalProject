package com.epam.university_admissions.entity;

public class FacultyEntrants extends Entity {

    private int facultyId;
    private int entrantId;

    public FacultyEntrants(int facultyId, int entrantId) {
        this.facultyId = facultyId;
        this.entrantId = entrantId;
    }

    public FacultyEntrants(Faculty faculty,Entrant entrant){
        this(faculty.getId(),entrant.getId());
    }

    public FacultyEntrants(){}

    public int getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(int facultyId) {
        this.facultyId = facultyId;
    }

    public int getEntrantId() {
        return entrantId;
    }

    public void setEntrantId(int entrantId) {
        this.entrantId = entrantId;
    }

    @Override
    public String toString() {
        return "FacultyEntrants{" +
                "facultyId=" + facultyId +
                ", entrantId=" + entrantId +
                '}';
    }
}
