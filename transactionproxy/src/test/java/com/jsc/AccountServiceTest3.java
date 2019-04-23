package com.jsc;

import com.jsc.config.SpringConfig;
import com.jsc.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class AccountServiceTest3 {
  /*  @Autowired
    FactoryBeanProxy factoryBeanProxy;*/

    @Autowired
    @Qualifier("accountServiceProxy")
    AccountService accountService;

    @Test
    public void testTransfer() throws SQLException {
        accountService.transfer("zhangsan", "lisi", 300);
    }
}
