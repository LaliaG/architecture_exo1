package org.example.projectservice.infrastructure.config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TransactionAspect {

    @Before("execution(* org.example.projectservice.application.service.*.*(..))")
    public void manageTransaction() {
        // Logique de gestion des transactions
        System.out.println("Transaction managed...");
    }
}
