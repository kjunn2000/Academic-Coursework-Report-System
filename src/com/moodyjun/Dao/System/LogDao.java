package com.moodyjun.Dao.System;

import com.moodyjun.Dao.User.UserDao;
import com.moodyjun.Dao.User.UserDaoFactory;
import com.moodyjun.Model.System.LogData;
import com.moodyjun.Model.User.User;
import com.moodyjun.Model.Util.ID;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class LogDao {

    private final File file ;
    private FileWriter fileWriter ;
    private PrintWriter printWriter ;
    private Scanner scanner ;
    private StringBuilder logDataBuilder;
    private List<LogData> logDataList ;
    private static LogDao logDao ;

    private LogDao(){
        file = new File("log_data.txt");
        logDataList = getAllLogData();
    }

    public int createLogData(LogData logData){
        logDataList.add(logData);
        saveToFile();
        if (saveToFile() == 1) return 1;
        return 0  ;
    }

    public LogData getLogDataByID(UUID logID){
        return logDataList.stream().filter(logData -> logData.getLogID().equals(logID)).findFirst().orElse(null);
    }

    public List<LogData> getAllLogData(){
        logDataList = new ArrayList<>();
        try {
            scanner = new Scanner(file);
            while(scanner.hasNext()){
                UUID logID = UUID.fromString(scanner.nextLine());
                String idString = scanner.nextLine();
                ID userID = new ID(idString.substring(0,2),Integer.parseInt(idString.substring(2,8)));
                String userName = scanner.nextLine();
                int userRole = Integer.parseInt(scanner.nextLine());
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("EE yyyy-MM-dd HH:mm:ss a");
                LocalDateTime logInTime = LocalDateTime.parse(scanner.nextLine(),dateTimeFormatter);
                String logOutString = scanner.nextLine();
                LocalDateTime logOutTime  = null;
                if(!(logOutString.equals("Current Status : Log In"))) {
                    logOutTime = LocalDateTime.parse(logOutString,dateTimeFormatter);
                }
                scanner.nextLine();
                scanner.nextLine();
                UserDao userDao = UserDaoFactory.getUserDaoByRole(userRole);
                User user =userDao.getUserByID(userID);
                LogData logData = new LogData(logID,user,logInTime,logOutTime);
                logDataList.add(logData);
            }
            return logDataList;
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
        return null ;
    }

    public int updateLogData(LogData newLogData ){
        LogData previousLogData = getLogDataByID(newLogData.getLogID());
        int indexToUpdate = logDataList.indexOf(previousLogData);
        logDataList.set(indexToUpdate,newLogData);
        if (saveToFile() == 1) return 1;
        return 0  ;
    }

    public int deleteLogData(UUID logID){
        LogData previousLogData = getLogDataByID(logID);
        logDataList.remove(previousLogData);
        if (saveToFile() == 1) return 1;
        return 0  ;
    }

    private int saveToFile(){
        logDataBuilder = new StringBuilder("");
        for(LogData logData : logDataList){
            logDataBuilder.append(logData.toString()+"\n\n\n");
        }
        try {
            printWriter = new PrintWriter(file);
            printWriter.write(logDataBuilder.toString());
            printWriter.close();
            return 1 ;
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
        return 0;
    }

    public List<LogData> getLogDataList() {
        return logDataList;
    }

    public static LogDao getInstance(){
        if(logDao==null){
            logDao = new LogDao();
        }
        return logDao ;
    }
}
