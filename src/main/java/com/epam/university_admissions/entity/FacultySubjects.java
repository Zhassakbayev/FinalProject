package com.epam.university_admissions.entity;

public class FacultySubjects extends Entity {

    private int facultyId;
    private int subjectId;

    public FacultySubjects(int facultyId, int subjectId) {
        this.facultyId = facultyId;
        this.subjectId = subjectId;
    }

    public FacultySubjects(Faculty faculty,Subject subject){
        this(faculty.getId(),subject.getId());
    }

    public FacultySubjects(){}

    public int getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(int facultyId) {
        this.facultyId = facultyId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    @Override
    public String toString() {
        return "FacultySubjects{" +
                "facultyId=" + facultyId +
                ", subjectId=" + subjectId +
                '}';
    }
}
