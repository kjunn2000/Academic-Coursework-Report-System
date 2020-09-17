package com.moodyjun.Services.Enroll;

import com.moodyjun.Dao.Module.ModuleDao;
import com.moodyjun.Dao.Module.ResultDao;
import com.moodyjun.Dao.User.LecturerDao;
import com.moodyjun.Dao.User.StudentDao;
import com.moodyjun.Model.Module.Class;
import com.moodyjun.Model.Module.Module;
import com.moodyjun.Model.Module.Result;
import com.moodyjun.Model.User.Lecturer;
import com.moodyjun.Model.User.Student;
import com.moodyjun.Model.User.User;
import com.moodyjun.Model.Util.ID;

import java.util.*;
import java.util.stream.Collectors;

public class EnrollService {
    private static EnrollService enrollService ;
    private StudentDao studentDao;
    private LecturerDao lecturerDao;
    private ModuleDao moduleDao;
    private ResultDao resultDao;

    private EnrollService(){
        studentDao = StudentDao.getInstance();
        lecturerDao = LecturerDao.getInstance();
        moduleDao = ModuleDao.getInstance();
        resultDao = ResultDao.getInstance();
    };

    public List<Module> getAllAvailModuleByID(ID userID){
        List<Module> allModule = moduleDao.getModuleList();
        List<Module> availModule ;
        if(userID.getRolePart().equals("LC")) {
            availModule = new ArrayList<>();
            Lecturer lecturer = (Lecturer) lecturerDao.getUserByID(userID);
            for(Module module : allModule){
                int flag = 1;
                for(Module m  :lecturer.getModuleList()){
                    if(m.getModuleId().equals(module.getModuleId())) flag=0;
                }
                if(flag == 1) availModule.add(module);
            }
            return availModule;
        }else if(userID.getRolePart().equals("TP")) {
            Student student = (Student) studentDao.getUserByID(userID);
            availModule = new ArrayList<>();
            for (Module module : allModule) {
                for (String intakeCode : module.getIntakeCodeList()) {
                    if (intakeCode.equals(student.getIntakeCode())) {
                        availModule.add(module);
                        break;
                    }
                }
            }
            for (Module module : student.getModuleList()) {
                availModule = availModule.stream().filter(m -> !m.getModuleId().equals(module.getModuleId())).collect(Collectors.toList());
            }
            return availModule;
        }
        return null;
    }

    public int addModuleToStud(ID studentID , List<String> moduleIDList){
        Student student = (Student) studentDao.getUserByID(studentID) ;
        if (student.getModuleList().size()==7) {
            return -1 ;
        }
        for(String moduleID : moduleIDList) {
            Module moduleToAdd = moduleDao.getModuleByID(moduleID);
            if(moduleToAdd.getMaxNumOfStud()==moduleToAdd.getNumOfStud()) return 0 ;
            moduleToAdd.setNumOfStud(moduleToAdd.getNumOfStud()+1);
            student.getModuleList().add(moduleToAdd);
            Result result = new Result(UUID.randomUUID(),moduleToAdd,0,0,0,0,"");
            student.getResults().put(moduleToAdd.getModuleId(),result);
            resultDao.createResult(result);
            moduleDao.updateModule(moduleToAdd);
        }
        studentDao.updateUser(student);
        return 1;
    }

    public int removeModuleFromStud(ID studentID , List<String> moduleIDList){

        Student student = (Student) studentDao.getUserByID(studentID) ;
        if (student.getModuleList().size()==0) {return 0;}
        for(String moduleID : moduleIDList){
                Module moduleRemove = moduleDao.getModuleByID(moduleID);
                moduleRemove.setNumOfStud(moduleRemove.getNumOfStud()-1);
                student.getModuleList().remove(moduleRemove);
                Result resultToRemove = student.getResults().get(moduleID);
                student.getResults().remove(moduleID);
                resultDao.deleteResult(resultToRemove.getResultID());
                moduleDao.updateModule(moduleRemove);
        }
        studentDao.updateUser(student);
        return 1 ;
    }

    public int addModuleToLec(ID lecturerID , List<String> moduleIDList) {
        Lecturer lecturer = (Lecturer) lecturerDao.getUserByID(lecturerID) ;
        if (lecturer.getModuleList().size()==4) {
            return -1 ;
        }
        for(String moduleID : moduleIDList) {
            Module moduleToAdd = moduleDao.getModuleByID(moduleID);
            lecturer.getModuleList().add(moduleToAdd);
        }
        lecturerDao.updateUser(lecturer);
        return 1;
    }

    public int removeModuleFromLec(ID lecturerID , List<String> moduleIDList){
        Lecturer lecturer = (Lecturer) lecturerDao.getUserByID(lecturerID) ;
        if (lecturer.getModuleList().size()==0) {return 0;}
        for(String moduleID : moduleIDList){
            Module moduleRemove = moduleDao.getModuleByID(moduleID);
            System.out.println(moduleRemove);
            lecturer.getModuleList().remove(moduleRemove);
        }
        lecturerDao.updateUser(lecturer);
        return 1;
    }

    public static EnrollService getInstance(){
        if(enrollService==null) enrollService = new EnrollService();
        return enrollService;
    };
}
