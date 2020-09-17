package com.moodyjun.Exception;

public class IDFormatException extends Exception{

    private String id;

    public IDFormatException(String id){
        this.id = id;
    }

    public String toString(){
        return id+" is not correct ID format.";
    }
}
