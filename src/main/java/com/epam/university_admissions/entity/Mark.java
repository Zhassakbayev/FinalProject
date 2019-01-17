package com.epam.university_admissions.entity;

public class Mark extends Entity {

    private int entrantId;
    private int subjectId;
    private int mark;

    public Mark(int entrantId, int subjectId, int mark) {
        this.entrantId = entrantId;
        this.subjectId = subjectId;
        this.mark = mark;
    }

    public Mark(Entrant entrant, Subject subject,int mark){
        this(entrant.getId(),subject.getId(),mark);
    }

    public Mark(){}

    public int getEntrantId() {
        return entrantId;
    }

    public void setEntrantId(int entrantId) {
        this.entrantId = entrantId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "Mark{" +
                "entrantId=" + entrantId +
                ", subjectId=" + subjectId +
                ", mark=" + mark +
                '}';
    }


}
