package org.example.exercice2_architecture.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class NotifyAspect {

    /*
    @Pointcut("@annotation(org.example.exercice2_architecture.annotation.Notify)")
    public void notifyPointCut() {}

    @AfterReturning("notifyPointCut()")
    public void sendNotification(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("Notification envoyée après la méthode : " + methodName);

    }*/

    @Around("@annotation(org.example.exercice2_architecture.annotation.Notify)")
    public Object aroundNotify(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = joinPoint.proceed();

        System.out.println("Notification after method execution.");

        return result;
    }
}
