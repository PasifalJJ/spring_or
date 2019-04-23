package com.jsc.dao.impl;

import com.jsc.bean.Account;

import java.sql.SQLException;

public interface AccountDao {
    /**
     * 根据用户姓名查询用户信息
     * @param name
     * @return
     */
    Account findByName(String name) throws SQLException;

    /**
     * 更新账户信息
     * @param source
     */
    void update(Account source) throws SQLException;



}
