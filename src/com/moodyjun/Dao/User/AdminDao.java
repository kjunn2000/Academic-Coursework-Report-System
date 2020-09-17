package com.moodyjun.Dao.User;

import com.moodyjun.Model.User.Admin;
import com.moodyjun.Model.User.Gender;
import com.moodyjun.Model.User.User;
import com.moodyjun.Model.Util.ID;

import java.io.*;
import java.util.*;

public class AdminDao implements UserDao {

    private final File file;
    private List<User> userList ;
    private static AdminDao adminDao;

    private AdminDao() { this.file = fileList[0]; userList = getAllUser();}


    @Override
    public int createUser(User newUser){
        userList.add(newUser);
        return saveToFile(file,userList);
    }

    @Override
    public int updateUser( User newUser ) {
        User previousUser = getUserByID(newUser.getId());
        int indexToUpdate = userList.indexOf(previousUser);
        userList.set(indexToUpdate,newUser);
        return saveToFile(file,userList);
    }

    @Override
    public User getUserByID(ID id) {
        return getAllUser().stream()
                .filter(user -> user.getId().equals(id))
                .findFirst().orElse(null);
    }

    @Override
    public User getUserByName(String userName){
        return getAllUser().stream()
                .filter(user -> user.getName().equals(userName))
                .findFirst().orElse(null);
    }

    @Override
    public List<User> getAllUser() {
        userList = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(file);
            while(scanner.hasNext()) {
                ID id = ID.fromString(scanner.nextLine());
                int role = Integer.parseInt(scanner.nextLine());
                String name = scanner.nextLine();
                String password = scanner.nextLine();
                Gender gender = Gender.valueOf(scanner.nextLine());
                int age = Integer.parseInt(scanner.nextLine());
                String email = scanner.nextLine();
                String phoneNum = scanner.nextLine();
                scanner.nextLine();
                scanner.nextLine();
                userList.add(new Admin(id,role,name,password,gender,age,email,phoneNum));
            }
        } catch (FileNotFoundException e) { System.out.println("File not found."); }

        return userList ;
    }

    @Override
    public int deleteUser(ID userID) {
        User userDelete = getUserByID(userID);
        userList.remove(userDelete);
        return saveToFile(file,userList);
    }

    public List<User> getUserList() {
        return userList;
    }

    public static AdminDao getInstance() {
        if(adminDao == null ) {
             synchronized (AdminDao.class){
                 if(adminDao == null ) adminDao = new AdminDao();
             }
        }
        return adminDao;
    }

}
