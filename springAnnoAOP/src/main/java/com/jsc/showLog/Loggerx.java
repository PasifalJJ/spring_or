package com.jsc.showLog;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Loggerx {
    @Pointcut("execution(* com.jsc.service.impl.*.*(..))")
    public void loggerxCuppoint(){

    }

    @Before(value = "loggerxCuppoint()")
    public void showLog(JoinPoint joinPoint) {
        System.out.println("打印日志");
        String name = joinPoint.getSignature().getName();
        System.out.println("name = " + name);
    }

    @Around("loggerxCuppoint()")
    public void showLogAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("开启环绕");
        String name = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        joinPoint.proceed(args);
        System.out.println("关闭环绕");
    }
}
