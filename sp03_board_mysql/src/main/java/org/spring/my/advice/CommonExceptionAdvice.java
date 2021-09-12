package org.spring.my.advice;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

//컨트롤러에서 발생하는 예외를 전문적으로 처리하는 클래스
@ControllerAdvice 
public class CommonExceptionAdvice {
	
	@ExceptionHandler(NoHandlerFoundException.class)  //클래스의 예외를 처리해주는게 애다
	public String common404(Exception e, HttpServletRequest request, Model model) {
		
		System.out.println("예외 발생");
		
		System.out.println(e.toString());
		e.printStackTrace();
		model.addAttribute("url", request.getRequestURL());  //url가져오기
		return "error/error404";
	}
	
	@ExceptionHandler(Exception.class)  //클래스의 예외를 처리해주는게 애다
	public String common(Exception e) {
		
		System.out.println("예외 발생");
		
		System.out.println(e.toString());
		e.printStackTrace();
		
		return "error/errorCommon";
	}
}
