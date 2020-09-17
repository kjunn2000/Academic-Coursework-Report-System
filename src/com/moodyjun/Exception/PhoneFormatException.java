package com.moodyjun.Exception;

public class PhoneFormatException extends Exception{

    private String phone;

    public PhoneFormatException(String phone){
        this.phone = phone;
    }

    @Override
    public String toString() {
        return this.phone+" is not in the phone format - xxx-xxxxxxx";
    }
}
