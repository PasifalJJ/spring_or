package com.jsc.showLog;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class Loggerx {
    public void showLog(JoinPoint joinPoint) {
        System.out.println("打印日志");
        String name = joinPoint.getSignature().getName();
        System.out.println("name = " + name);
    }

    public void showLogAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("打印日志");
        String name = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        joinPoint.proceed(args);
        System.out.println("name = " + name);
        System.out.println("-------------");
    }
}
