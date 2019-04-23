package com.jsc.proxy;

import com.jsc.service.AccountService;
import com.jsc.service.impl.AccountServiceImpl;
import com.jsc.service.impl.AccountServiceImpl2;
import com.jsc.transaction.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Component("factoryBeanProxy")
public class FactoryBeanProxy {
    @Autowired
    @Qualifier("accountServiceImpl2")
    AccountService accountService;

    @Autowired
    TransactionManager transactionManager;

    //获取UserService的代理对象
    @Bean("accountServiceProxy")
    public AccountService getAccountServiceProxy() {

        AccountService accountServiceProxy = (AccountService) Proxy.newProxyInstance(accountService.getClass().getClassLoader(),
                accountService.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        if (method.getName().equals("transfer")) {
                            try {
                                transactionManager.beginTransaction();
                                method.invoke(accountService, args);
                                transactionManager.commit();
                            } catch (Exception e) {
                                transactionManager.rollback();
                                e.printStackTrace();
                            } finally {
                                transactionManager.release();
                            }
                            return null;
                        }
                        return method.invoke(accountService, args);
                    }
                });
        return accountServiceProxy;
    }
}
