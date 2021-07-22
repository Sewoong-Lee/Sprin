package org.spring.my.advice;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

//aop구현
@Component  //스프링이 자동으로 객체생성
@Aspect  //aop기능
public class AopAdvice {
	//언제(@Before) + 누구에게(("execution(* org.spring.my.controller.*.*(..))")) + 무엇을(public void beforeLogDAO(JoinPoint jp))
	
	//표현식 : 반환형 / 클래스명/ 메소드명 / 매개변수
	@Before("execution(* org.spring.my.controller.*.*(..))")
	public void beforeLogcontroller(JoinPoint jp) {
		//JoinPoint : 적용대상(실제 실행되는 객체)
		System.out.println("------------------------------");
		System.out.println(jp.getSignature().toLongString());
		System.out.println("매개변수 : "+ Arrays.toString(jp.getArgs()));
		System.out.println("------------------------------");
	}
	@Before("execution(* org.spring.my.dao.*.*(..))")
	public void beforeLogDAO(JoinPoint jp) {
		//JoinPoint : 적용대상(실제 실행되는 객체)
		System.out.println("------------------------------");
		System.out.println(jp.getSignature().toLongString());
		System.out.println("매개변수 : "+ Arrays.toString(jp.getArgs()));
		System.out.println("------------------------------");
	}
//	
	//정상 수행 후
	@AfterReturning(pointcut = "execution(* org.spring.my.*.*.*(..))", returning = "robj")
	public void afterLogService(JoinPoint jp, Object robj) {
		if(robj != null) {
			System.out.println("------------------------------");
			System.out.println(jp.getSignature().toLongString()); //어디서 찍혔는지
			System.out.println("리턴값 : "+ robj.toString());
			System.out.println("------------------------------");
		}
	}
//	
//	//예외가 발생
//	@AfterThrowing(pointcut = "execution(* org.spring.my.dao.*.*(..))", throwing = "eobj")
//	public void executionLog(JoinPoint jp, Exception eobj) {
//		System.out.println("------------------------------");
//		System.out.println(jp.getSignature().toLongString()); //어디서 찍혔는지
//		System.out.println("예외발생 : "+ eobj.toString());
//		System.out.println("------------------------------");
//		
//	}
	
	//모듈의 소요시간 체크
	@Around("execution(* org.spring.my.service.FileServiceimpl.*(..))")
	public Object timeLog(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("----------소요시간---------------");
		System.out.println(pjp.getSignature().toLongString());
		//시작시간
		long starttime = System.currentTimeMillis();
		Object rs = pjp.proceed(); //실제 실행해야할 메소드 실행
		//끝시간
		long endtime = System.currentTimeMillis();
		System.out.println("시간 : " + (endtime - starttime));
		
		System.out.println("----------소요시간---------------");
		
		return rs;  //호출한 메소드에 전달
	}
	
	
	
	
	
	
}
