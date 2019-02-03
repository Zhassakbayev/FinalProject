package com.epam.university_admissions.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EntrantValidator extends Validator {

    private static final Pattern VALID_IIN_REGEX = Pattern.compile("\\b\\d{12}\\b");

    public boolean isViolatedEntrantParameters(String iin, String city, String district, String schoolName){
        boolean isViolated = false;
        validateIIN(iin);
        checkOnCorrectFilled(district);
        checkOnCorrectFilled(schoolName);
        if (!getViolations().isEmpty()){
            isViolated = true;
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
