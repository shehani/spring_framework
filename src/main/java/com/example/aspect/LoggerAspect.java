package com.example.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Slf4j
@Aspect
@Component
public class LoggerAspect {

//    @Around("execution(* com.example.*.*(..)) && !within(com.example.aspect.*) && !within(com.example.model.*)")
//    public void timeLogger(ProceedingJoinPoint joinPoint) throws Throwable{
//        log.info(joinPoint.getSignature().toString() + "method execution started");
//        Instant start = Instant.now();
//        Object anyReturn = joinPoint.proceed();
//        Instant end = Instant.now();
//        long time = Duration.between(start,end).toMillis();
//        log.info("Time tool for execution "+time);
//        log.info(joinPoint.getSignature().toString() + "method execution ended");
//    }



    @AfterThrowing(value = "execution(* com.example.*.*.*(..))", throwing = "ex")
    public void logException(JoinPoint joinPoint, Exception ex){
        log.error(joinPoint.getSignature().toString()+ "An exception happended due to "+ex.getMessage());
    }
}
