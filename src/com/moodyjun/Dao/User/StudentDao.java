package com.moodyjun.Dao.User;

import com.moodyjun.Dao.Module.ModuleDao;
import com.moodyjun.Dao.Module.ResultDao;
import com.moodyjun.Model.Module.Class;
import com.moodyjun.Model.Module.Module;
import com.moodyjun.Model.Module.Result;
import com.moodyjun.Model.User.Gender;
import com.moodyjun.Model.User.Student;
import com.moodyjun.Model.User.User;
import com.moodyjun.Model.Util.ID;

import java.io.*;
import java.util.*;

public class StudentDao implements UserDao {

    private File file;
    private Scanner scanner ;
    private List<User> userList ;
    private static StudentDao studentDao;
    private ModuleDao moduleDao ;
    private ResultDao resultDao;

    private StudentDao() {
        this.file = fileList[2];
        this.moduleDao = ModuleDao.getInstance();
        userList = getAllUser();
    }

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
        this.resultDao = ResultDao.getInstance();
        userList = new ArrayList<>();
        String[] moduleIDList ;
        List<Module> moduleList = new ArrayList<>() ;
        String[] resultsString ;
        Map<String, Result> results = new HashMap<>();
        try {
            scanner = new Scanner(file);
            while(scanner.hasNext()) {
                ID id = ID.fromString(scanner.nextLine());
                int role = Integer.parseInt(scanner.nextLine());
                String name = scanner.nextLine();
                String password = scanner.nextLine();
                Gender gender = Gender.valueOf(scanner.nextLine());
                int age = Integer.parseInt(scanner.nextLine());
                String email = scanner.nextLine();
                String phoneNum = scanner.nextLine();
                int level = Integer.parseInt(scanner.nextLine());
                String intakeCode = scanner.nextLine();
                moduleIDList = scanner.nextLine().split(",");
                resultsString = scanner.nextLine().split(",");
                moduleList = new ArrayList<>();
                results = new LinkedHashMap<>();
                int count = 0 ;
                if(!moduleIDList[0].equals("Empty")){
                    for(String moduleID : moduleIDList){
                        moduleList.add(moduleDao.getModuleByID(moduleID));
                        results.put(moduleID,resultDao.getResultByID(UUID.fromString(resultsString[count])));
                        count++;
                    }
                }
                scanner.nextLine();
                scanner.nextLine();
                Student student = new Student(id,role,name,password,gender,age,email,phoneNum,level,intakeCode,moduleList,results);
                userList.add(student);
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

    public static StudentDao getInstance() {
        if(studentDao == null ) {
            synchronized (StudentDao.class){
                if(studentDao == null ) studentDao = new StudentDao();
            }
        }
        return studentDao;
    }

}
