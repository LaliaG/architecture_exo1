package com.example.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    @Before("execution(* com.example.demo.service.BookService.*(..))")
    public void logMethodEntry(JoinPoint joinPoint) {
        System.out.println("Entering method: " + joinPoint.getSignature().getName());
        System.out.println("Arguments: ");
        for (Object arg : joinPoint.getArgs()) {
            System.out.println(" - " + arg);
        }
    }

    @AfterReturning(pointcut = "execution(* com.example.demo.service.BookService.*(..))", returning = "result")
    public void logMethodExit(JoinPoint joinPoint, Object result) {
        System.out.println("Exiting method: " + joinPoint.getSignature().getName());
        if (result != null) {
            System.out.println("Return value: " + result);
        }
    }
}
