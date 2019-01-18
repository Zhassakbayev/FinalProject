package com.epam.university_admissions.utils;

public class MysqlRequests {

    public static final String FIND_ALL_ENTRANTS = "SELECT * FROM university_admissions.entrant;";
    public static final String FIND_ENTRANT = "SELECT * FROM university_admissions.entrant WHERE entrant.ENTRANT_ID = ?;";
    public static final String FIND_ENTRANT_BY_USER_ID = "SELECT * FROM university_admissions.entrant WHERE entrant.USER_ID = ?;";
    public static final String INSERT_ENTRANT = "INSERT into university_admissions.entrant (IIN, CITY, DISTRICT, SCHOOL_NAME, USER_ID, IS_BLOCKED) VALUES ('?','?', '?', '?', '?', '?');";
    public static final String UPDATE_ENTRANT = "UPDATE university_admissions.entrant SET entrant.IIN=?, entrant.CITY=?,entrant.DISTRICT=?,entrant.SCHOOL_NAME=?, entrant.USER_ID=?, entrant.IS_BLOCKED=? WHERE entrant.ENTRANT_ID=?;";
    public static final String DELETE_ENTRANT = "DELETE FROM university_admissions.entrant WHERE entrant.ENTRANT_ID=?;;";

}
