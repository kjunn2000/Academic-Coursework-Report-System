package com.moodyjun.Exception;

public class MarksPctException extends Exception{
    private int quiz;
    private int lab;
    private int ass;

    public MarksPctException(int quiz,int lab,int ass){
        this.quiz = quiz ;
        this.lab = lab;
        this.ass = ass;
    }

    public String toString(){
        return quiz+"+"+lab+"+"+ass+" are not equal 100%. Incorrect marking weightage.";
    }
}
