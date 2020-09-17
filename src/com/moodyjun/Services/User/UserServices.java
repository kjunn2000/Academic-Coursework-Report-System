package com.moodyjun.Services.User;

import com.moodyjun.Dao.User.AdminDao;
import com.moodyjun.Dao.User.LecturerDao;
import com.moodyjun.Dao.User.StudentDao;
import com.moodyjun.Dao.User.UserDao;
import com.moodyjun.Model.User.Lecturer;
import com.moodyjun.Model.User.Student;
import com.moodyjun.Model.User.User;

import java.util.ArrayList;
import java.util.List;

public interface UserServices {

    int updateProfile( User newUser);
    default List<UserDao> getUserDaoList(){
        List<UserDao> userDaoList = new ArrayList<>();
        userDaoList.add(AdminDao.getInstance());
        userDaoList.add(LecturerDao.getInstance());
        userDaoList.add(StudentDao.getInstance());
        return userDaoList;
    }

}
