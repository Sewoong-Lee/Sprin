package org.spring.my.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.spring.my.service.NaverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("naver")
public class NaverController {
	
	@Autowired
	private NaverService naverService;
	
	//네이버 api가 실행할 네이버 로그인 콜백 주소 받기
	@RequestMapping("callback")
	public String callback(String code, String state, HttpSession session) throws Exception {
		//정보를 얻기위한 토큰 얻기
		String access_token = naverService.getNaverToken(code, state);
		//토큰을 이용한 개인정보 얻기
		Map<String, String> rsmap = naverService.getNaverUserInfo(access_token);
		
		//insert 메소드 내부에 이미 가입되어있다면 작동 안하도록 조치함
		naverService.insert(rsmap); //회원가입
		session.setAttribute("userid", rsmap.get("email")); //세션에 저장
		session.setAttribute("name", rsmap.get("name")); //세션에 저장
		
		return "main";
	}
	
	
}
