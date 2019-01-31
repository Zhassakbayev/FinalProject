package com.epam.university_admissions.validator;

public class Validator {

    private static final String IS_POSITIVE_NUMBERS = "\\d+";
    private static final String CHECK_FOR_FILLED = "^\\p{L}[\\p{L}\\s]*\\p{L}$";
    private static final String IS_LATIN_WORD = "[a-zA-Z ]+";
    private static final String IS_CYRILLIC_WORD = "[а-яА-Я ]+";

    private static boolean checkNullOrEmptyString(String... values) {
        boolean temp = false;
        for (String value : values) {
            if (value == null || value.isEmpty()){
                System.out.println("Field" + value + "is not null or empty");
                temp = true;
            }
        }
        return temp;
    }

    public static boolean checkForFilled(String... values) {
        boolean temp = false;
        if (checkNullOrEmptyString(values))
            temp = false;
        for (String value : values){
            if (value.matches(CHECK_FOR_FILLED)){
                temp = true;
            }
        }
        return temp;
    }

    public static boolean isCyrillicWord(String... values){
        boolean temp = false;
        if (!checkNullOrEmptyString(values)){
            for (String value : values){
                if (value.matches(IS_CYRILLIC_WORD)){
                    temp = true;
                }
            }
        }
        return temp;
    }

    public static boolean isLatinWord(String... values){
        boolean temp = false;
        if (!checkNullOrEmptyString(values)){
            for (String value : values){
                if (value.matches(IS_LATIN_WORD)){
                    temp = true;
                }
            }
        }
        return temp;
    }

    public static boolean isPositiveNumbers(String... values){
        boolean temp = false;
        if (!checkNullOrEmptyString(values)){
            for (String value : values){
                if (value.matches(IS_POSITIVE_NUMBERS)){
                    temp = true;
                }
            }
        }
        return temp;
    }

    public static boolean checkSeatsInFaculty(byte total,byte budget) {
        return budget < total;
    }

    private static boolean checkNullNumbers(Number... values){
        boolean temp = false;
        for (Number value : values) {
            if (value == null){
                System.out.println("Field" + value + "is not null or empty");
                temp = true;
            }
        }
        return temp;
    }
}
