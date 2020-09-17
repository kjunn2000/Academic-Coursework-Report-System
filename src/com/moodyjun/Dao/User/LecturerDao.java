package com.moodyjun.Dao.User;

import com.moodyjun.Dao.Module.ModuleDao;
import com.moodyjun.Model.Module.Class;
import com.moodyjun.Model.Module.Module;
import com.moodyjun.Model.Module.Result;
import com.moodyjun.Model.User.Gender;
import com.moodyjun.Model.User.Lecturer;
import com.moodyjun.Model.User.User;
import com.moodyjun.Model.Util.ID;

import java.io.*;
import java.util.*;

public class LecturerDao implements UserDao {

    private File file;
    private Scanner scanner ;
    private List<User> userList ;
    private static LecturerDao lecturerDao;
    private ModuleDao moduleDao ;

    private LecturerDao() {
        this.file = fileList[1];
        this.moduleDao = ModuleDao.getInstance();
        userList = getAllUser();
    }

    @Override
    public int createUser(User newUser){
        userList.add(newUser);
        return saveToFile(file,userList);
    }

    @Override
    public int updateUser(  User newUser ) {
        User previousUser = getUserByID(newUser.getId());
        int indexToUpdate = userList.indexOf(previousUser);
        userList.set(indexToUpdate,newUser);
        return saveToFile(file,userList);
    }

    @Override
    public User getUserByID(ID id) {
        return getUserList().stream()
                .filter(user -> user.getId().equals(id))
                .findFirst().orElse(null);
    }

    @Override
    public User getUserByName(String userName){
        return getUserList().stream()
                .filter(user -> user.getName().equals(userName))
                .findFirst().orElse(null);
    }

    @Override
    public List<User> getAllUser() {
        userList = new ArrayList<>();
        String[] moduleIDList ;
        List<Module> moduleList = new ArrayList<>();
        try {
            scanner = new Scanner(file);
            while (scanner.hasNext()) {
                moduleList = new ArrayList<>();
                ID id = ID.fromString(scanner.nextLine());
                int role = Integer.parseInt(scanner.nextLine());
                String name = scanner.nextLine();
                String password = scanner.nextLine();
                Gender gender = Gender.valueOf(scanner.nextLine());
                int age = Integer.parseInt(scanner.nextLine());
                String email = scanner.nextLine();
                String phoneNum = scanner.nextLine();
                moduleIDList = scanner.nextLine().split(",");
                if(!moduleIDList[0].equals("Empty")){
                    for(String moduleID : moduleIDList) {
                        moduleList.add(moduleDao.getModuleByID(moduleID));
                    }
                }
                scanner.nextLine();
                scanner.nextLine();
                Lecturer lecturer = new Lecturer(id, role, name, password, gender, age, email, phoneNum, moduleList);
                userList.add(lecturer);
                for(Module module : moduleList) module.getLecturerList().add(lecturer);
            }
            scanner.close();
        }catch (FileNotFoundException e) { System.out.println("File not found."); }

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

    public static LecturerDao getInstance() {
        if(lecturerDao == null ) {
            synchronized (LecturerDao.class){
                if(lecturerDao == null ) lecturerDao = new LecturerDao();
            }
        }
        return lecturerDao;
    }

}
