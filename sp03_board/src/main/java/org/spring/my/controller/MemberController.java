package org.spring.my.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.my.dto.Member;
import org.spring.my.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("member")
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private MemberService memberservice;
	
	@RequestMapping("jusoPopup") // callback시 post방식으로 실행
	public String test2() {

		return "member/jusoPopup";
	}
	
	//회원가입 폼으로
	@GetMapping("join") 
	public void add(Model model) {
		
	}
	
	//회원가입 저장
	@PostMapping("join") 
	public void add(Member member , MultipartFile file1, Model model) {
		logger.info(member.toString()); //MultipartFile file1을 dto에 넣어서 한번에 받아도댐
		logger.info(file1.toString());
		
		Map<String, Object> rmap = memberservice.insert(member, file1);
		model.addAttribute("rmap", rmap);
		logger.info(rmap.toString());
		
	}
	
	//로그인한 회원의 상세정보
	@GetMapping("info") 
	public void info(Model model, HttpSession session) {
		String userid = (String) session.getAttribute("userid"); //회원 아이디 세션 불러오기
		Member member = memberservice.selectone(userid);
		model.addAttribute("member", member);
		
	}
	
	
	
}
