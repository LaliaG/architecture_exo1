package org.example.employeeservice.infrastructure.config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* org.example.employeeservice.application.service.*.*(..))")
    public void logBeforeServiceMethods() {
        // Logique de logging avant l'exécution des méthodes de service
        System.out.println("Method executed...");
    }
}
