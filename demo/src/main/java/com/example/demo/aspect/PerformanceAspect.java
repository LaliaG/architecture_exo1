package com.example.demo.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class PerformanceAspect {

    @Around("execution(* com.example.demo.service.BookService.*(..))")
    public Object measureMethodExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();  // exécute la méthode réelle
        long timeTaken = System.currentTimeMillis() - startTime;
        System.out.println("Time taken by " + proceedingJoinPoint.getSignature().getName() + " is " + timeTaken + "ms");
        return result;
    }
}
