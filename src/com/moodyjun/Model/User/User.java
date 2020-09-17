package com.moodyjun.Model.User;

import com.moodyjun.Model.Util.ID;

import java.util.UUID;

public abstract class User {

    private String name ;
    private ID id ;
    private int role ;
    private String password ;
    private Gender gender;
    private int age ;
    private String email;
    private String phoneNum;

    public User(ID id, int role ,String name, String password
            ,Gender gender, int age, String email, String phoneNum) {

        this.id = id;
        this.role = role;
        this.name = name;
        this.password = password;
        this.gender = gender;
        this.age = age;
        this.email = email;
        this.phoneNum = phoneNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) { this.id = id; }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPhoneNum() { return phoneNum; }

    public void setPhoneNum(String phoneNum) { this.phoneNum = phoneNum; }

    public String toString() {
        return this.id+"\n"+this.role+"\n" +this.name+"\n"+this.password
                +"\n"+this.gender+"\n"+this.age+"\n"+email+"\n"+phoneNum;
    }


}
