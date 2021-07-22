package org.spring.my.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class testcontroller {
	private static final Logger logger = LoggerFactory.getLogger(studentcontroller.class);
	
	//주소 샘플창 열기
	@GetMapping("Sample")
	public String test() {

		return "jusotest/Sample";
	}
	
	
	//팝업 띄우기
	//겟방식 포스트방식 전체 처리
	//GET: 주소창 띄우시
	//post : 콜백 주소(주소 결과값)
	@RequestMapping("jusoPopup")
	public String test2() {

		return "jusotest/jusoPopup";
	}
	
	
	
}
