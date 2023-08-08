package com.demo.DemoApp.utilities;

import java.security.SecureRandom;

public class DemoAppUtils {
    private static final SecureRandom secureRandom = new SecureRandom();


    public static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    public static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
    public static final String NAME_REGEX = "^[A-Z][a-zA-Z]{0,39}$";

    public static final String TEST_IMAGE = "C:\\Users\\User\\IdeaProjects\\DemoApp\\DemoApp\\src\\main\\resources\\static\\passport.jpg";
    public static String generateOtp(){
        return String.valueOf(secureRandom.nextInt(101000, 1000000));
    }

}
