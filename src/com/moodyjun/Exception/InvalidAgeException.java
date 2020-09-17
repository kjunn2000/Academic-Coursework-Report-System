package com.moodyjun.Exception;

public class InvalidAgeException extends Exception{

    private int age;

    public InvalidAgeException(int age){
        this.age = age;
    }

    public String toString(){
        return this.age+" is not in the range of the age.";
    }

}
