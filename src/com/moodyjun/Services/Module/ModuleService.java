package com.moodyjun.Services.Module;

import com.moodyjun.Dao.Module.ModuleDao;
import com.moodyjun.Dao.Module.ResultDao;
import com.moodyjun.Dao.User.LecturerDao;
import com.moodyjun.Dao.User.StudentDao;
import com.moodyjun.Model.Module.Module;
import com.moodyjun.Model.Module.Result;
import com.moodyjun.Model.User.Lecturer;
import com.moodyjun.Model.User.Student;
import com.moodyjun.Model.User.User;
import com.moodyjun.Services.Enroll.EnrollService;

import javax.management.loading.MLetContent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ModuleService {

    private ModuleDao moduleDao ;
    private static ModuleService moduleService;

    private ModuleService(){moduleDao = ModuleDao.getInstance();};

    public int createModule(Module module){
        if(moduleDao.getModuleByID(module.getModuleId())==null){
            return moduleDao.createModule(module);
        };
        return 0;
    }

    public Module getModuleByID(String moduleID){ return moduleDao.getModuleByID(moduleID); }

    public int updateModule(Module newModule){
        if(moduleDao.getModuleByID(newModule.getModuleId())!=null) return moduleDao.updateModule(newModule);
        return 0;
    }

    public int deleteModule(String moduleID) {
        for(User user : LecturerDao.getInstance().getAllUser()) {
            Lecturer lecturer = (Lecturer) user;
            Module module = lecturer.getModuleList().stream().filter(mod -> mod.getModuleId().equals(moduleID)).findFirst().orElse(null);
            if(module != null){
                lecturer.getModuleList().remove(module);
                LecturerDao.getInstance().updateUser(lecturer);
            };

        }
        for(User user : StudentDao.getInstance().getAllUser()) {
            Student student = (Student) user;
            Module module = student.getModuleList().stream().filter(mod -> mod.getModuleId().equals(moduleID)).findFirst().orElse(null);
            if(module != null){
                student.getModuleList().remove(module);
                Result result = student.getResults().get(module.getModuleId());
                student.getResults().remove(module.getModuleId());
                StudentDao.getInstance().updateUser(student);
                ResultDao.getInstance().deleteResult(result.getResultID());
            };

        }
        return moduleDao.deleteModule(moduleID);
    };


    public static ModuleService getInstance(){
        if(moduleService==null){
            moduleService = new ModuleService();
        }
        return moduleService;
    }
}
