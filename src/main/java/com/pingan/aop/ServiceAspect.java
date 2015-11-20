package com.pingan.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
@Component
//声明这是一个切面Bean
@Aspect
public class ServiceAspect {
    private final static Logger LOGGER = LoggerFactory.getLogger(ServiceAspect.class);

	//配置切入点,该方法无方法体,主要为方便同类中其他方法使用此处配置的切入点
	@Pointcut("execution(* com.pingan.service.ExampleService.*(..))")
	public void aspect(){}

	/*
	 * 配置前置通知,使用在方法aspect()上注册的切入点
	 * 同时接受JoinPoint切入点对象,可以没有该参数
	 */
	@Before("aspect()")
	public void before(JoinPoint joinPoint){

	}

	//配置后置通知,使用在方法aspect()上注册的切入点
	@After("aspect()")
	public void after(JoinPoint joinPoint){
	}

	//配置环绕通知,使用在方法aspect()上注册的切入点
	@Around("aspect()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object obj = null;
        obj = joinPoint.proceed();
        return obj;
	}

	//配置后置返回通知,使用在方法aspect()上注册的切入点
	@AfterReturning("aspect()")
	public void afterReturn(JoinPoint joinPoint){
		LOGGER.info("afterReturn {}", joinPoint);
	}

	//配置抛出异常后通知,使用在方法aspect()上注册的切入点
	@AfterThrowing(pointcut="aspect()", throwing="ex")
	public void afterThrow(JoinPoint joinPoint, Exception ex){
		LOGGER.info("afterThrow {} ErrorMsg: {}", joinPoint, ex.getMessage());
	}
}