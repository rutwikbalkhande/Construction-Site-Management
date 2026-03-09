package com.example.Worker.aspectaop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspectAOP {

    @Around("execution(* com.example.Worker.service.impl.*.*(..))")
    public Object logAroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {

        log.info("Method started...: {}", joinPoint.getSignature().getName());

          Object result = joinPoint.proceed();           // service logic Run after next log print

        log.info("method Finished...: {}", joinPoint.getSignature().getName());

        return result;


        // Implementing AOP for logging don't need to implement log in each service controller.
        // Runs Before & after every method inside service.impl package


        //@Before - print log before method execution
        //@After - pring log after method execution
        //@AfterReturning - log after successfully returning
        //@AfterThrowing - after throwing exeption
        //@Around - print log before and after execution
    }
}