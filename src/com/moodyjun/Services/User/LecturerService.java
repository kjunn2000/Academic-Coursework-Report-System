package com.moodyjun.Services.User;

import com.moodyjun.Dao.User.*;
import com.moodyjun.Model.User.Lecturer;
import com.moodyjun.Model.User.User;
import com.moodyjun.Model.Util.ID;

import java.util.ArrayList;
import java.util.List;

public class LecturerService implements UserServices{
    private static LecturerService lecturerService;
    private UserDao lecturerDao;

    private LecturerService() { lecturerDao = getUserDaoList().get(1);}

    public List<Lecturer> getAllUser(){
        List<Lecturer> lecturerList = new ArrayList<>();
        for(User user : lecturerDao.getAllUser()){
            lecturerList.add((Lecturer) user);
        }
        return lecturerList;
    }

    public User getUserByID(ID userID){
        return lecturerDao.getUserByID(userID);
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
        lecturerDao.updateUser(newUser);
        return 1;
    }

    public static LecturerService getInstance() {
        if(lecturerService == null) {
            lecturerService = new LecturerService() ;
            return lecturerService;
        }
        return lecturerService;
    }

}
