package com.epam.university_admissions.validator;

public class SubjectValidator extends Validator {

    public boolean isViolatedSubjectParameters(String nameRu, String nameEn){
        boolean isViolated = false;
        checkOnCorrectFilled(nameRu);
        checkOnCorrectFilled(nameEn);
        if (!getViolations().isEmpty()){
            isViolated = true;
        }
        return isViolated;
    }
}
