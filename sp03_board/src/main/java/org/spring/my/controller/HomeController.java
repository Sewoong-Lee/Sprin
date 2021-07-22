package org.spring.my.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.spring.my.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@Autowired
	private MemberService memberservice;
	
	//메인으로 이동
	@RequestMapping("/")
	public String main() {
		return "main";
	}
	
	//로그인 폼으로
	@GetMapping("login") 
	public void login() {
				
	}
		
	//로그인
	@PostMapping("login") 
	public String login(String userid, String passwd, Model model, HttpSession session) {
		//0:로그인 완료 (정상회원) => 메인으로
		//1:아이디 없음  => 로그인으로
		//2:비밀번호 불일치  => 로그인으로
			
		Map<String, Object> rmap= memberservice.login(userid, passwd);
			
		if((int)rmap.get("rcode") == 0) { //로그인 성공
			//로그인 정보 세션에 저장
			session.setAttribute("userid", userid);  //세션에 아이디 저장
			//model.addAttribute("rmap", rmap);
			return "redirect:/";
		}else {
			model.addAttribute("rmap", rmap);
			return "login";
		}
			
	}
	
	//로그아웃
	@GetMapping("logout") 
	public String logout(HttpSession session) {
		//모든 세션변수 삭제
		session.invalidate();
		
		return "redirect:/";
	}
	
	
	
}
