package com.moodyjun.Dao.Module;

import com.moodyjun.Model.Module.Module;
import com.moodyjun.Model.Module.Result;

import java.io.*;
import java.util.*;

public class ResultDao {

    private File file;
    private PrintWriter printWriter;
    private Scanner scanner ;
    private String newData ;
    private List<Result> resultList ;
    private static ResultDao resultDao;
    private ModuleDao moduleDao ;

    private ResultDao() {resultList = getAllResult();}

    public int createResult(Result result){
        resultList.add(result);
        return writeToFile();
    }

    public int updateResult( Result newResult ) {
        Result previousResult = getResultByID(newResult.getResultID());
        int indexToUpdate = resultList.indexOf(previousResult);
        resultList.set(indexToUpdate,newResult);
        return writeToFile();
    }

    public Result getResultByID(UUID id) {
        return getAllResult().stream().filter(result -> result.getResultID().equals(id)).findFirst().orElse(null);
    }

    public List<Result> getAllResult() {
        resultList = new ArrayList<>();
        file = new File("result.txt");
        moduleDao = ModuleDao.getInstance();
        try {
            scanner = new Scanner(file);
            while(scanner.hasNext()){
                UUID resultID = UUID.fromString(scanner.nextLine());
                Module module = moduleDao.getModuleByID(scanner.nextLine());
                int quizMark = Integer.parseInt(scanner.nextLine());
                int labTestMark = Integer.parseInt(scanner.nextLine());
                int assignmentMark = Integer.parseInt(scanner.nextLine());
                int totalMark = Integer.parseInt(scanner.nextLine());
                String comment = scanner.nextLine();
                scanner.nextLine();
                scanner.nextLine();
                resultList.add(new Result(resultID,module,quizMark,labTestMark,assignmentMark,totalMark,comment));
            }
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
        return resultList;
    }

    public int deleteResult(UUID resultID) {
        Result resultToDelete = getResultByID(resultID);
        resultList.remove(resultToDelete);
        return writeToFile();
    }

    private int writeToFile(){
        newData="";
        for(Result result : resultList) newData += result.toString() + "\n\n\n";
        file = new File("result.txt");
        try {
            printWriter = new PrintWriter(file);
            printWriter.write(newData);
            printWriter.close();
            System.out.println("Update successfully.");
            return 1;
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
        return 0 ;

    }

    public List<Result> getResultList() {
        return resultList;
    }

    public static ResultDao getInstance() {
        if(resultDao == null ) {
            synchronized (ResultDao.class){
                if(resultDao == null ) resultDao = new ResultDao();
            }
        }
        return resultDao;
    }

}
