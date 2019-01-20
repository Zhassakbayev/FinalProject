package com.epam.university_admissions.utils;

public class MysqlRequests {

    public static final String FIND_ALL_ENTRANTS = "SELECT * FROM university_admissions.entrant;";
    public static final String FIND_ENTRANT = "SELECT * FROM university_admissions.entrant WHERE entrant.ENTRANT_ID = ?;";
    public static final String FIND_ENTRANT_BY_USER_ID = "SELECT * FROM university_admissions.entrant WHERE entrant.USER_ID = ?;";
    public static final String INSERT_ENTRANT = "INSERT into university_admissions.entrant (IIN, CITY, DISTRICT, SCHOOL_NAME, USER_ID, IS_BLOCKED) VALUES (?,?,?,?,?,?);";
    public static final String UPDATE_ENTRANT = "UPDATE university_admissions.entrant SET entrant.IIN=?, entrant.CITY=?,entrant.DISTRICT=?,entrant.SCHOOL_NAME=?, entrant.USER_ID=?, entrant.IS_BLOCKED=? WHERE entrant.ENTRANT_ID=?;";
    public static final String DELETE_ENTRANT = "DELETE FROM university_admissions.entrant WHERE entrant.ENTRANT_ID=?;";
    public static final String FIND_ALL_ENTRANTS_IN_FACULTY = "SELECT university_admissions.entrant.* FROM university_admissions.entrant INNER JOIN university_admissions.faculty_entrants ON university_admissions.faculty_entrants.ENTRANT_ID=university_admissions.entrant.ENTRANT_ID WHERE university_admissions.faculty_entrants.FACULTY_ID=?;";

    public static final String INSERT_USER = "INSERT INTO university_admissions.user (FIRST_NAME, LAST_NAME, SECOND_NAME, DATE_OF_BIRTH, EMAIL,PASSWORD,ROLE) VALUES (?,?,?,?,?,?,?);";
    public static final String UPDATE_USER = "UPDATE university_admissions.user SET user.FIRST_NAME=?, user.LAST_NAME=?,user.SECOND_NAME=?,user.DATE_OF_BIRTH=?, user.EMAIL=?, user.PASSWORD=?, user.ROLE=?, user.LANG=? WHERE user.USER_ID=?;";
    public static final String DELETE_USER = "DELETE FROM university_admissions.user WHERE user.USER_ID=?;";
    public static final String FIND_USER = "SELECT * FROM university_admissions.user WHERE user.USER_ID = ?;";
    public static final String FIND_ALL_USER = "SELECT * FROM university_admissions.user;";
    public static final String FIND_USER_BY_EMAIL_PASSWORD = "SELECT * FROM university_admissions.user WHERE user.EMAIL =? AND user.PASSWORD=? ;";
    public static final String FIND_USER_BY_EMAIL = "SELECT * FROM university_admissions.user WHERE user.EMAIL =?;";

    public static final String INSERT_FACULTY = "INSERT into university_admissions.faculty (NAME_RU, NAME_EN, TOTAL_SEATS, BUDGET_SEATS) VALUES (?,?,?,?);";
    public static final String UPDATE_FACULTY = "UPDATE university_admissions.faculty SET faculty.NAME_RU=?, faculty.NAME_EN=?, faculty.TOTAL_SEATS=?, faculty.BUDGET_SEATS=?";
    public static final String DELETE_FACULTY = "DELETE FROM university_admissions.faculty WHERE faculty.FACULTY_ID=?;";
    public static final String FIND_FACULTY = "SELECT * FROM university_admissions.faculty WHERE faculty.FACULTY_ID =?;";
    public static final String FIND_ALL_FACULTY = "SELECT * FROM university_admissions.faculty;";
    public static final String FIND_FACULTY_BY_NAME = "SELECT * FROM university_admissions.faculty WHERE faculty.NAME_RU=? OR faculty.NAME_EN=?;";

    public static final String INSERT_MARK = "INSERT into university_admissions.mark (ENTRANT_ID, SUBJECT_ID, MARK) VALUES (?,?,?);";
    public static final String UPDATE_MARK = "UPDATE university_admissions.mark SET mark.ENTRANT_ID=?, mark.SUBJECT_ID=?, mark.MARK=?;";
    public static final String DELETE_MARK = "DELETE FROM university_admissions.mark WHERE mark.MARK_ID=?;";
    public static final String FIND_MARK = "SELECT * FROM university_admissions.mark WHERE mark.MARK_ID=?;";
    public static final String FIND_ALL_MARK = "SELECT * FROM university_admissions.mark;";
    public static final String FIND_MARK_BY_ENTRANT_SUBJECT = "SELECT * FROM university_admissions.mark WHERE mark.ENTRANT_ID=? AND mark.SUBJECT_ID=?;";

    public static final String INSERT_SUBJECT = "INSERT into university_admissions.subject (NAME_RU, NAME_EN) VALUES (?,?);";
    public static final String UPDATE_SUBJECT = "UPDATE university_admissions.subject SET subject.NAME_RU=?, subject.NAME_EN=?;";
    public static final String DELETE_SUBJECT = "DELETE FROM university_admissions.subject WHERE subject.SUBJECT_ID=?;";
    public static final String FIND_SUBJECT = "SELECT * FROM university_admissions.subject WHERE subject.SUBJECT_ID=?;";
    public static final String FIND_ALL_SUBJECT = "SELECT * FROM university_admissions.subject;";
    public static final String FIND_SUBJECTS_IN_FACULTY = "SELECT university_admissions.subject.* FROM university_admissions.subject INNER JOIN university_admissions.faculty_subjects ON subject.SUBJECT_ID=faculty_subjects.SUBJECT_ID WHERE faculty_subjects.FACULTY_ID=?;";

    public static final String INSERT_FACULTY_ENTRANTS = "INSERT into university_admissions.faculty_entrants (ENTRANT_ID, FACULTY_ID) VALUES (?,?);";
    public static final String UPDATE_FACULTY_ENTRANTS = "UPDATE university_admissions.faculty_entrants SET faculty_entrants.ENTRANT_ID=?, faculty_entrants.FACULTY_ID=?;";
    public static final String DELETE_FACULTY_ENTRANTS = "DELETE FROM university_admissions.faculty_entrants WHERE faculty_entrants.ID=?;";
    public static final String FIND_FACULTY_ENTRANTS = "SELECT * FROM university_admissions.faculty_entrants WHERE faculty_entrants.ID=?;";
    public static final String FIND_ALL_FACULTY_ENTRANTS = "SELECT * FROM university_admissions.faculty_entrants;";
    public static final String FIND_FACULTY_ENTRANTS_BY_FACULTY_ENTRANT = "SELECT * FROM university_admissions.faculty_entrants WHERE faculty_entrants.ENTRANT_ID=? AND faculty_entrants.FACULTY_ID=?;";

    public static final String INSERT_FACULTY_SUBJECTS = "INSERT into university_admissions.faculty_subjects (FACULTY_ID, SUBJECT_ID) VALUES (?,?);";
    public static final String UPDATE_FACULTY_SUBJECTS = "UPDATE university_admissions.faculty_subjects SET faculty_subjects.FACULTY_ID=?, faculty_subjects.SUBJECT_ID=?;";
    public static final String DELETE_FACULTY_SUBJECTS = "DELETE FROM university_admissions.faculty_subjects WHERE faculty_subjects.ID=?;";
    public static final String FIND_FACULTY_SUBJECTS = "SELECT * FROM university_admissions.faculty_subjects WHERE faculty_subjects.ID=?;";
    public static final String FIND_ALL_FACULTY_SUBJECTS = "SELECT * FROM university_admissions.faculty_subjects;";

    public static final String FIND_FACULTY_REPORT = "SELECT * FROM university_admissions.faculties_report WHERE faculties_report.FACULTY_ID=?;";
































}
