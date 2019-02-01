package com.epam.university_admissions.validator;

import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProfileValidator extends Validator {

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])", Pattern.CASE_INSENSITIVE);
    private static final Pattern VALID_PASSWORD_REGEX = Pattern.compile("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}");
    private static final Pattern VALID_IIN_REGEX = Pattern.compile("\\b\\d{12}\\b");


    public boolean isViolatedUserParameters(String firstName, String lastName, String secondName, String dateOfBirth,
                                                 String email, String password, String lang) {
        boolean isViolated = false;
        checkOnCorrectFilled(firstName);
        checkOnCorrectFilled(lastName);
        checkOnCorrectFilled(secondName);
        isDateValid(dateOfBirth);
        validateEmail(email);
        checkOnCorrectFilled(password);
        checkOnCorrectFilled(lang);
        if (!getViolations().isEmpty()){
            isViolated = true;
        }
        return isViolated;
    }

    public boolean isViolatedEntrantParameters(String iin, String city, String district, String schoolName, String isBlocked){
       boolean isViolated = false;
        validateIIN(iin);
        checkOnCorrectFilled(district);
        checkOnCorrectFilled(schoolName);
        if (!getViolations().isEmpty()){
            isViolated = true;
        }
        return isViolated;
    }

    private boolean validateEmail(String email) {
        boolean isViolated = true;
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email.trim());
        if (!matcher.find()){
            addViolation("Email must be a well-formed address");
            isViolated = false;
        }
        return isViolated;
    }

    private boolean validatePassword(String password){
        boolean isViolated = true;
        Matcher matcher = VALID_PASSWORD_REGEX.matcher(password.trim());
        if (!matcher.find()){
            addViolation("Password must be well-formed");
            isViolated = false;
        }
        return isViolated;
    }

    private boolean isDateValid(String date) {
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
        myFormat.setLenient(false);
        boolean isViolated;
        try {
            myFormat.parse(date);
            isViolated = true;
        } catch (Exception e) {
            isViolated = false;
            addViolation("Date of birth entered incorrectly");
        }
        return isViolated;
    }

    private boolean validateIIN(String iin) {
        boolean isViolated = true;
        Matcher matcher = VALID_IIN_REGEX.matcher(iin.trim());
        if (checkNullOrEmptyString(iin)){
            if (!matcher.find()){
            addViolation("IIN must be 12 digit numbers");
            isViolated = false;
            }
        }
        return isViolated;
    }
}
