package com.tb.store.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

//@Component//将当前类的创建和维护交给spring容器来维护
//@Aspect//将当前类标记为切面类
//ProceedingJoinPoint接口表示连接点，目标方法的对象。
public class TimerAspect {
//    @Around("execution(* com.tb.store.service.impl.*.*(..))")
//    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//        //记录开始时间
//        long start = System.currentTimeMillis();
//        Object result = proceedingJoinPoint.proceed(); //调用目标方法：login
//        //记录结束时间
//        long end = System.currentTimeMillis();
//        System.out.println("耗时："+(end-start));
//        return result;
//    }
}
