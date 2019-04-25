package com.jsc.test;


import com.jsc.service.AccountService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:applicationContext.xml")
public class AccountServiceTest {
//    @Autowired
//    private AccountService accountService;

    @Test
    public void accountServiceTest() {
        ApplicationContext ApplicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        AccountService accountService = (AccountService) ApplicationContext.getBean("accountServicexxx");
        accountService.saveAccount();
    }
}
