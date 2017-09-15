/**
 * 
 */
package com.hd.kzscrm.manager.controller.aop.client;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author 黄霄仪
 * @date 2017年7月17日 下午4:43:58
 * 
 */
@Aspect  
public class CrmClientResourceControllerAop {

	@Pointcut("execution(* com.hd.kzscrm.manager.controller.client.CrmClientResourceController.newClientCheck(..))")
	private void newClientCheck(){
		
	}
	
	@Before("newClientCheck()")
	public void doBefore(){
		System.out.println("前置通知");
	}
	@After("newClientCheck()")
	public void doAfter(){
		System.out.println("后置通知");
	}
	//声明环绕通知  
    @Around("newClientCheck()")  
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {  
        System.out.println("进入方法---环绕通知");  
        Object o = pjp.proceed();  
        System.out.println(pjp.getSignature());
        System.out.println(pjp.getTarget());
        System.out.println(pjp.getThis());
        System.out.println(pjp.getArgs());
        System.out.println(pjp.getStaticPart());
        System.out.println(pjp.getKind());
        System.out.println(pjp.getSourceLocation());
        System.out.println("退出方法---环绕通知");  
        return o;  
    }  	
}
