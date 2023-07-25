package com.optimissa.training.userservice.util;

import java.util.regex.Pattern;

public class UserUtil {
    private static final String VALID_EMAIL_ADDRESS_REGEX = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
    private static final String VALID_PHONE_REGEX = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$";

    public boolean checkEmail(String email) {
        if (email.equals("")) return false;
        return Pattern.compile(VALID_EMAIL_ADDRESS_REGEX, Pattern.CASE_INSENSITIVE).matcher(email).find();
    }

    public boolean checkPhone(String phone) {
        if (phone.equals("")) return true;
        return Pattern.compile(VALID_PHONE_REGEX, Pattern.CASE_INSENSITIVE).matcher(phone).find();
    }
}
