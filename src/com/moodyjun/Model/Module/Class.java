package com.moodyjun.Model.Module;

import com.moodyjun.Model.Util.ID;

import java.time.LocalDateTime;
import java.util.Objects;

public class Class {
    String classType;
    LocalDateTime dateTime;
    int duration;
    String location;

    public Class(String classType, LocalDateTime dateTime, int duration ,String location) {
        this.classType = classType;
        this.dateTime = dateTime;
        this.duration = duration;
        this.location = location;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }


    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getLocation() { return location; }

    public void setLocation(String location) { this.location = location; }

    public String dateTimeToString() {
        return dateTime.getDayOfWeek()+"-"+dateTime.getHour()+"-"+dateTime.getMinute();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Class aClass = (Class) o;
        return duration == aClass.duration &&
                Objects.equals(classType, aClass.classType) &&
                Objects.equals(dateTime, aClass.dateTime) &&
                Objects.equals(location, aClass.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(classType, dateTime, duration, location);
    }

    @Override
    public String toString() {
        return classType+" "+dateTimeToString() + " "
                + duration + " " + location;
    }
}
