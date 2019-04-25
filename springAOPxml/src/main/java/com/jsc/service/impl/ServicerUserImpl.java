package com.jsc.service.impl;

import com.jsc.service.ServiceUser;
import org.springframework.stereotype.Service;

@Service("servicerUserImpl")
public class ServicerUserImpl implements ServiceUser {
    @Override
    public void find() {
        System.out.println("查找用户");
    }

    @Override
    public void insert() {
        System.out.println("删除用户");
    }
}
