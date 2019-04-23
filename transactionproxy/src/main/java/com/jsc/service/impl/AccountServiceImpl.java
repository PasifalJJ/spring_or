package com.jsc.service.impl;

import com.jsc.bean.Account;
import com.jsc.dao.impl.AccountDao;
import com.jsc.service.AccountService;
import com.jsc.transaction.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service("accountServiceImpl")
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDao accountDao;

    @Autowired
    private TransactionManager transactionManager;

    @Override
    public void transfer(String sourceName, String targetName, double money)  {
        transactionManager.beginTransaction();
        try {
            //1.根据名称查询转出账户
            Account source = accountDao.findByName(sourceName);
            //2.根据名称查询转入账户
            Account target = accountDao.findByName(targetName);
            //3.转出账户减钱
            source.setMoney(source.getMoney()-money);
            //4.转入账户加钱
            target.setMoney(target.getMoney()+money);
            //5.更新转出账户余额
            accountDao.update(source);
            //6.更新转入账户余额
            accountDao.update(target);
            transactionManager.commit();
        } catch (SQLException e) {
            transactionManager.rollback();
            e.printStackTrace();
        } finally {
            transactionManager.release();
        }
    }
}
