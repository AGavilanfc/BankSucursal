package com.optimissa.training.clientservice.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClientUtils {

    public static boolean isValidEmail(String mail) {
        String regex = "^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(mail);
        return matcher.find();
    }

}
