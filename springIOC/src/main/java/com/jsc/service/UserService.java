package com.jsc.service;

import com.jsc.dao.IUserDao;
import com.jsc.dao.impl.UserDao;

public class UserService {
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void add(){
        userDao.add();
    }
}
