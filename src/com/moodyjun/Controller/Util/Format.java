package com.moodyjun.Controller.Util;

public class Format {

    public static boolean isEmailValid(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    public static boolean isPhoneNumValid(String phone){
        String regex = "\\d{3}-\\d{7,8}";
        return phone.matches(regex);
    }

    public static boolean isIDValid(String id){
        String regex = "(?:AD|LC|TP)\\d{6}";
        return id.matches(regex);
    }

}
