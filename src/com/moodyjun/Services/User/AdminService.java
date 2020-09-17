package com.moodyjun.Services.User;

import com.moodyjun.Dao.User.*;
import com.moodyjun.Model.User.User;
import com.moodyjun.Model.Util.ID;

import java.util.ArrayList;
import java.util.List;

public class AdminService implements UserServices{
    private static AdminService adminServices ;
    private UserDao userDao;

    private AdminService() { }

    public int createUser(User user) {
        int flag = 1;
        for(UserDao userDao : getUserDaoList()){
            if(userDao.getUserByName(user.getName())!=null) flag = 0;
        }
        if(flag == 1){
            userDao = UserDaoFactory.getUserDaoByRole(user.getRole());
            if(userDao.getAllUser().size()!=0)user.setId(ID.nextID(userDao.getAllUser().get(userDao.getAllUser().size()-1).getId()));
            else user.setId(new ID(user.getRole() == 0 ? "AD" : (user.getRole() == 1) ? "LC" : "TP",000000));
            userDao.createUser(user);
            return 1;
        }
        return 0;
    }

    public User getUserByID(ID userID){
        UserDao userDao = UserDaoFactory.getUserDaoByID(userID);
        if (userDao==null) return null;
        return userDao.getUserByID(userID);
    }

    @Override
    public int updateProfile(User newUser) {
        userDao = UserDaoFactory.getUserDaoByRole(newUser.getRole());
        if(userDao.getUserByID(newUser.getId())==null) {
            return -1;
        }
        for(UserDao userDao : getUserDaoList()){
            if(userDao.getAllUser().stream()
                    .filter(user -> (user.getName().equals(newUser.getName()))
                            && !(user.getId().equals(newUser.getId())) )
                    .findFirst().orElse(null)!=null){
                return 0;
            }
        }
        userDao.updateUser(newUser);
        return 1;
    }

    public int deleteUser(ID deleteUserID) {
        userDao = UserDaoFactory.getUserDaoByID(deleteUserID);
        if(userDao.getUserByID(deleteUserID)!=null){
            userDao.deleteUser(deleteUserID);
            return 1 ;
        }
        return 0;
    }

    public static AdminService getInstance() {
        if(adminServices == null) {
            adminServices = new AdminService() ;
            return adminServices ;
        }
        return adminServices ;
    }


}
