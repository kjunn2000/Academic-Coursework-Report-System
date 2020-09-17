package com.moodyjun.Services.Result;

import com.moodyjun.Dao.Module.ResultDao;
import com.moodyjun.Dao.User.StudentDao;
import com.moodyjun.Model.Module.Result;
import com.moodyjun.Model.User.Student;
import com.moodyjun.Model.User.User;

import java.util.HashMap;
import java.util.List;

public class KeyResultService {

    private static KeyResultService keyResultService;
    private StudentDao studentDao ;
    private ResultDao resultDao ;

    private KeyResultService(){resultDao = ResultDao.getInstance();}

    public HashMap<Student,Result> getStudentResultListByModuleID(String moduleID){
        HashMap<Student , Result> resultHashMap= new HashMap<>();
        for(User user :StudentDao.getInstance().getUserList()) {
            Student student = (Student) user;
            Result result = student.getResults().get(moduleID);
            if(result!=null) resultHashMap.put(student,result);
        }
        return resultHashMap;
    };

    public int saveResult(List<Result> resultList){
        for (Result result : resultList) {
            if(result.getTestMark() >result.getModule().getTestMarkPct()
                    || result.getExamMark() >result.getModule().getExamMarkPct()
                    || result.getAssignmentMark() >result.getModule().getAssignmentMarkPct()){
                return 0 ;
            }else{
                resultDao.updateResult(result);
            }
        }
        return 1;
    }

    public static KeyResultService getInstance(){
        if(keyResultService==null){
            keyResultService = new KeyResultService();
        }
        return keyResultService;
    }
}
