package com.example.Service_Discovery;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;



@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger =
            LoggerFactory.getLogger(LoggingAspect.class);

    @Around("execution(* com.example.service.*.*(..))")
    public Object logExecution(ProceedingJoinPoint joinPoint) throws Throwable {

        long startTime= System.currentTimeMillis();
        logger.info("Method started: {}", joinPoint.getSignature());

        Object result = joinPoint.proceed();

        long endTime = System.currentTimeMillis();
        logger.info("mehtod execute time taken {} ms", endTime-startTime );
        logger.info("Method finished: {}", joinPoint.getSignature());

        return result;
    }
}