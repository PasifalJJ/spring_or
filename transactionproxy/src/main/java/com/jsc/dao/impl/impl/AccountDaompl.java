package com.jsc.dao.impl.impl;

import com.jsc.bean.Account;
import com.jsc.dao.impl.AccountDao;
import com.jsc.jdbcutils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository("accountDaompl")
public class AccountDaompl implements AccountDao {
    @Autowired
    private QueryRunner queryRunner;

    @Autowired
    private JDBCUtils jdbcUtils;
    @Override
    public Account findByName(String name) throws SQLException {
        String sql="select * from account where username = ?";
        //return queryRunner.query(sql,new BeanHandler<Account>(Account.class));
        return queryRunner.query(jdbcUtils.getThreadConnection(),sql,new BeanHandler<Account>(Account.class),name);
    }

    @Override
    public void update(Account account) throws SQLException {
        String sql = "update account set money = ? where username = ? ";
        //queryRunner.update(sql,account.getMoney(),account.getUsername());
        queryRunner.update(jdbcUtils.getThreadConnection(),sql,account.getMoney(),account.getUsername());
    }
}
