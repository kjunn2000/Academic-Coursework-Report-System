package com.moodyjun.Dao.User;

import com.moodyjun.Model.User.User;
import com.moodyjun.Model.Util.ID;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import java.util.List;

public interface UserDao  {

    File[] fileList = new File[]{new File("admin.txt"),
            new File("lecturer.txt"),
            new File("student.txt")};

    int createUser(User user);

    int updateUser(User newUser );

    User getUserByID(ID id);

    User getUserByName(String name);

    List<User> getAllUser();

    int deleteUser(ID userID);

    default int saveToFile(File file , List<User> userList) {
        String newData="";
        PrintWriter printWriter;
        for(User user : userList) newData+=  user.toString()+"\n\n\n";
        try {
            printWriter = new PrintWriter(file);
            printWriter.write(newData);
            printWriter.close();
            System.out.println("Update Successfully");
            return 1;
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
        return 0;
    }


}
