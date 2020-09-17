package com.moodyjun.Exception;

public class EmailFormatException extends Exception{
    private String email;

    public EmailFormatException(String email){
        this.email=email;
    }

    public String toString(){
        return this.email+" is not in the email format - xxx@xxx.xxx.";
    }

}
