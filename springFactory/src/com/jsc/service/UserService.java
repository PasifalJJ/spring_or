package com.jsc.service;

import com.jsc.factory.DaoFactory;
        import com.jsc.dao.IUserDao;

public class UserService {
    private IUserDao userDao=(IUserDao) DaoFactory.getBean("UserDao");
    public void add(){
        userDao.add();
    }
}
