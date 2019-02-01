package com.epam.university_admissions.validator;

import java.util.ArrayList;
import java.util.List;

public abstract class Validator {

    private static final String IS_POSITIVE_NUMBERS = "\\d+";
    private static final String CHECK_FOR_FILLED = "^\\p{L}[\\p{L}\\s]*\\p{L}$";
    private static final String IS_LATIN_WORD = "[a-zA-Z ]+";
    private static final String IS_CYRILLIC_WORD = "[а-яА-Я ]+";
    private final List<String> violations = new ArrayList<>();


    public boolean checkNullOrEmptyString(String value) {
        boolean isViolated= false;
            if (value == null || value.isEmpty()){
                violations.add("Field" + value + "can not be null or empty!");
                isViolated = true;
            }
            return isViolated;
    }

    public boolean checkOnCorrectFilled(String value) {
        boolean isViolated = true;
        if (!checkNullOrEmptyString(value)){
            if (!value.matches(CHECK_FOR_FILLED)){
            violations.add(value + "is mandatory");
                isViolated = false;
            }
        }
        return isViolated;
    }

    public boolean isCyrillicWord(String value){
        boolean isViolated = true;
        if (!checkNullOrEmptyString(value)){
            if (!value.matches(IS_CYRILLIC_WORD)){
            violations.add(value + "is not contained cyrillic words");
                isViolated = false;
            }
        }
        return isViolated;
    }

    public boolean isLatinWord(String value){
        boolean isViolated = true;
        if (!checkNullOrEmptyString(value)){
            if (!value.matches(IS_LATIN_WORD)){
            violations.add(value + "is not contained latin words");
                isViolated = false;
            }
        }
        return isViolated;
    }

    public boolean isPositiveNumbers(String value){
        boolean isViolated = true;
        if (!checkNullOrEmptyString(value)){
            if (!value.matches(IS_POSITIVE_NUMBERS)){
                violations.add(value + "is not positive decimal number");
                isViolated = false;
            }
        }
        return isViolated;
    }

    public List<String> getViolations() {
        return violations;
    }

    public void addViolation(String value){
        violations.add(value);
    }
}
