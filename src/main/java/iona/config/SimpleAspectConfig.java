package iona.config;

import iona.logger.IonaLogger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class SimpleAspectConfig {

    @Pointcut("execution(* iona..*(..))")
    public void myPointCut(){
    }

    @Before(value = "myPointCut()")
    public void before(JoinPoint jp){
        IonaLogger.info("AOP的Before方法执行");
    }

}
