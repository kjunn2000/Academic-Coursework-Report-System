package com.moodyjun.Services.User;

import com.moodyjun.Dao.User.StudentDao;
import com.moodyjun.Dao.User.UserDao;
import com.moodyjun.Model.User.Student;
import com.moodyjun.Model.User.User;

import java.util.ArrayList;
import java.util.List;

public class StudentService implements UserServices{
    private static StudentService studentService;
    private final UserDao studentDao ;
    private StudentService() {studentDao = getUserDaoList().get(2); }


    public List<Student> getAllStudent(){
        List<Student> studList = new ArrayList<>();
        for(User user : studentDao.getAllUser()){
            studList.add((Student) user);
        }
        return studList;
    }

    @Override
    public int updateProfile(User newUser) {
        for(UserDao userDao : getUserDaoList()){
            if(userDao.getAllUser().stream()
                    .filter(user -> (user.getName().equals(newUser.getName()))
                            && !(user.getId().equals(newUser.getId())) )
                    .findFirst().orElse(null)!=null){
                return 0;
            }
        }
        studentDao.updateUser(newUser);
        return 1;
    }

    public static StudentService getInstance() {
        if(studentService == null) {
            studentService = new StudentService() ;
            return studentService;
        }
        return studentService;
    }
}
