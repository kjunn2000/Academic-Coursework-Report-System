package com.moodyjun.Dao.User;

import com.moodyjun.Model.Util.ID;

public class UserDaoFactory {

    public static UserDao getUserDaoByRole(int role){
        return switch (role) {
            case 0 -> AdminDao.getInstance();
            case 1 -> LecturerDao.getInstance();
            case 2 -> StudentDao.getInstance();
            default -> null;
        };
    }

    public static UserDao getUserDaoByID(ID id){
        return switch (id.getRolePart()) {
            case "AD" -> AdminDao.getInstance();
            case "LC" -> LecturerDao.getInstance();
            case "TP" -> StudentDao.getInstance();
            default -> null;
        };
    }
}
