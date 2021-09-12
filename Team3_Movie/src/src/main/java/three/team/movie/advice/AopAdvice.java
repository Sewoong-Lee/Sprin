package three.team.movie.advice;

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
@Component //spring이 자동객체 생성
@Aspect
public class AopAdvice {
	//언제 + 누구에게 + 무엇을
	//표현식 : 반환형 / 클래스명 / 메소드명 / 매개변수
	@Before("execution(* three.team.movie.controller.*.*(..))")
	public void beforeLogDAO(JoinPoint jp) {
		System.out.println("-------------------------");
		System.out.println(jp.getSignature().toLongString());
		System.out.println("매개변수:" + Arrays.toString(jp.getArgs()));
		System.out.println("-------------------------");
	}
	
	//정상수행후
	@AfterReturning(pointcut = "execution(* three.team.movie.service.*.*(..))", returning = "rObj" )
	public void afterLogService(JoinPoint jp, Object rObj) {
		if (rObj != null) {
			System.out.println("-------------------------");
			System.out.println(jp.getSignature().toLongString());
			System.out.println("리턴 값:" + rObj.toString());
			System.out.println("-------------------------");
		}
	}
	
	//예외가 발생
//	@AfterThrowing(pointcut = "execution(* org.spring.my.dao.*.*(..))", throwing = "eObj")
//	public void excetpionLog(JoinPoint jp, Exception eObj) {
//		System.out.println("-------------------------");
//		System.out.println(jp.getSignature().toLongString());
//		System.out.println("예외발생 : " + eObj.toString());
//		System.out.println("-------------------------");
//	}
	
	
	
	
}
