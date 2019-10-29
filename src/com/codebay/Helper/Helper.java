package com.codebay.Helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Helper {
    public static boolean containsNumbers(String data){
        return data.matches("^[0-9]*$");
    }

    public static boolean tooShort(String data) {
        return data.length() <= 3;
    }

    public static boolean trueOrFalse(String data){
        if(data.equals("false") || data.equals("true")){
            return true;
        }
        return false;
    }

    public static boolean isEmail(String data){
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(data);
        return matcher.find();
    }

    public static boolean isAscendingOrDescending(String data){
        return data.equals("ascending") || data.equals("descending");
    }

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
}
