package com.practise.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author dan.he
 * @Date 2022/8/1 16:37
 **/

@Component
@Aspect
@Slf4j

public class Aop2Aspect {

    @Pointcut("@annotation(com.practise.aspect.Aop2)")
    private void pointCutMethod(){
    }

    //声明前置通知
    @Before("pointCutMethod()")
    public void before(JoinPoint point){
        System.out.println("Aop2Aspect:doBefore");
    }

    //声明后置通知
    @AfterReturning(pointcut = "pointCutMethod()",returning = "returnValue")
    public void doAfterReturning(JoinPoint point,Object returnValue){
        System.out.println("Aop2Aspect:doAfterReturning");
    }

    //声明例外通知
    @AfterThrowing(pointcut = "pointCutMethod()",throwing = "e")
    public void doAfterThrowing(Exception e){
        System.out.println("Aop2Aspect:doAfterThrowing");
    }

    //声明最终通知
    @After("pointCutMethod()")
    public void doAfter(){
        System.out.println("Aop2Aspect:doAfter");
    }

    //声明环绕通知
    @Around("pointCutMethod()")
    public Object doAround(ProceedingJoinPoint point) throws Throwable {
        System.out.println("Aop2Aspect:doAround1");
        Object obj = point.proceed();
        System.out.println("Aop2Aspect:doAround2");
        return obj;
    }


}
