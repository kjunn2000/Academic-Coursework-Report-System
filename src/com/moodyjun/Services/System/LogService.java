package com.moodyjun.Services.System;

import com.moodyjun.Dao.System.LogDao;
import com.moodyjun.Dao.User.AdminDao;
import com.moodyjun.Dao.User.LecturerDao;
import com.moodyjun.Dao.User.StudentDao;
import com.moodyjun.Dao.User.UserDao;
import com.moodyjun.Model.System.LogData;
import com.moodyjun.Model.User.User;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LogService {

    private User currentUser;
    private LogDao logDao = LogDao.getInstance();
    private static LogService logService ;

    private LogService() { }

    public User logIn(String userName , String password){
        List<UserDao> userDaoList  = getUserDaoList() ;
        for(UserDao userDao : userDaoList){
            currentUser = userDao.getUserByName(userName) ;
            if(currentUser !=null) {
                if(verifyPassword(password)){
                    createLogData(new LogData(UUID.randomUUID(), currentUser, LocalDateTime.now()));
                    System.out.println("Log In Successfully.");
                    return currentUser;
                }else{
                    System.out.println("Password invalid.");
                    break;
                }
            }
        }
        System.out.println("User does not exist.");
        return null ;
    }

    public int logOut(){
        LogData currentLogData = logDao.getLogDataList().stream()
                                    .filter(logData -> logData.getLogOutTime()==null)
                                    .findFirst().orElse(null);
        if(currentLogData==null) {
            return 1;
        }
        else{
            updateLogData(currentLogData);
            System.out.println("Log out successfully");
        }
        return 1 ;
    }

    private List<UserDao> getUserDaoList(){
        List<UserDao> userDaoList = new ArrayList<>();
        userDaoList.add(AdminDao.getInstance());
        userDaoList.add(LecturerDao.getInstance());
        userDaoList.add(StudentDao.getInstance());
        return userDaoList ;
    }

    private boolean verifyPassword( String password ){
        return password.equals(currentUser.getPassword());
    }

    private int createLogData(LogData logData){
        logDao.createLogData(logData);
        return 1;
    }

    private int updateLogData(LogData logData){
        logData.setLogOutTime(LocalDateTime.now());
        logDao.updateLogData(logData);
        return 1;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public static LogService getInstance(){
        if(logService == null) {
            synchronized (LogService.class){
                if(logService == null) {
                    logService = new LogService();
                }
            }
        }
        return logService;
    }


}
