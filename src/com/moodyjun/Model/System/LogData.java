package com.moodyjun.Model.System;

import com.moodyjun.Model.User.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class LogData {

    private UUID logID ;
    private User user;
    private LocalDateTime logInTime ;
    private LocalDateTime logOutTime ;

    public LogData(UUID logID, User currentUser, LocalDateTime logInTime) {
        this.logID = logID;
        this.user = currentUser;
        this.logInTime = logInTime;
        this.logOutTime = null;
    }

    public LogData(UUID logID, User currentUser, LocalDateTime logInTime , LocalDateTime logOutTime) {
        this.logID = logID;
        this.user = currentUser;
        this.logInTime = logInTime;
        this.logOutTime = logOutTime;
    }

    public UUID getLogID() {
        return logID;
    }

    public void setLogID(UUID logID) {
        this.logID = logID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getLogInTime() {
        return logInTime;
    }

    public void setLogInTime(LocalDateTime logInTime) {
        this.logInTime = logInTime;
    }

    public LocalDateTime getLogOutTime() {
        return logOutTime;
    }

    public void setLogOutTime(LocalDateTime logOutTime) {
        this.logOutTime = logOutTime;
    }

    public String dateTimeToString(LocalDateTime localDateTime){
        if(localDateTime==null) return "Current Status : Log In";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("EE yyyy-MM-dd HH:mm:ss a");
        return localDateTime.format(dateTimeFormatter);
    }

    @Override
    public String toString() {
        return logID+"\n"+ user.getId()+"\n"+ user.getName()+"\n"+ user.getRole()+"\n"+dateTimeToString(logInTime)+"\n"+dateTimeToString(logOutTime);

    }
}
