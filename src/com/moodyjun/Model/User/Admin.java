package com.moodyjun.Model.User;
import com.moodyjun.Model.Util.ID;

public class Admin extends User {

    public Admin(ID id, int role, String name, String password, Gender gender,
                 int age, String email, String phoneNum) {
        super(id, role, name, password, gender, age, email, phoneNum);
    }
}
