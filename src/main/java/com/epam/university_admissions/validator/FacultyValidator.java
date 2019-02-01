package com.epam.university_admissions.validator;

public class FacultyValidator extends Validator {

    public boolean isViolatedFacultyParameters(String nameRu, String nameEn, int totalSeats, int budgetSeats){
        boolean isViolated = false;
        checkOnCorrectFilled(nameRu);
        checkOnCorrectFilled(nameEn);
        isCyrillicWord(nameRu);
        isLatinWord(nameEn);
        isPositiveNumbers(String.valueOf(totalSeats));
        isPositiveNumbers(String.valueOf(budgetSeats));
        checkSeatsInFaculty(totalSeats,budgetSeats);
        if (!getViolations().isEmpty()){
            isViolated = true;
        }
        return isViolated;
    }

    private boolean checkSeatsInFaculty(int total, int budget) {
        boolean isViolated = true;
        if (!checkNullNumbers(total) && !checkNullNumbers(budget)){
            if (!(budget <total)){
                addViolation("Budget seats must be less total seats");
                isViolated = false;
            }
        }
        return isViolated;
    }

    private boolean checkNullNumbers(Number value){
        boolean isViolated = false;
        if (value ==null){
            addViolation("The number should not be null");
            isViolated = true;
        }
        return isViolated;
    }
}
