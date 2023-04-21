package com.optimissa.training.userservice.util;

import org.springframework.context.annotation.Bean;

import java.util.regex.Pattern;

public class UserUtil {
    private static final String VALID_EMAIL_ADDRESS_REGEX = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";

    public boolean checkEmail(String email) {
        return Pattern.compile(VALID_EMAIL_ADDRESS_REGEX, Pattern.CASE_INSENSITIVE).matcher(email).find();
    }
}
