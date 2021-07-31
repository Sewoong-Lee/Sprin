package org.spring.my.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.my.dto.Member;
import org.spring.my.service.MailSendService;
import org.spring.my.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("member")
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private MemberService memberservice;
	
	@Autowired
	private MailSendService mailSendService;
	
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
	public void add(Member member , MultipartFile file1, Model model, HttpSession session) throws Exception {
		logger.info(member.toString()); //MultipartFile file1을 dto에 넣어서 한번에 받아도댐
		logger.info(file1.toString());
		
		Map<String, Object> rmap = memberservice.insert(member, file1);
		//인증키를 세션에 넣기
		//key를 Userid , value를 autoCode 인 세션 생성
		session.setAttribute(member.getUserid(), rmap.get("autoCode"));
		session.setMaxInactiveInterval(30*60); //30분 세션 유효
		
		model.addAttribute("rmap", rmap);
		logger.info(rmap.toString());
		
	}
	//이메일에서 메일 확인을 클릭했을때 처리
	@RequestMapping("joinConfirm")
	public String joinConfirm(String userid, String authCode, HttpSession session) {
		//위의 세션에 저장해둔 코드 변수에 저장
		String sessionAuthCode = (String)session.getAttribute(userid);
		//인증키 비교 검증
		if(sessionAuthCode == null) { //30분이 지나 세션이 사라진 경우 체크
			logger.info("세션 기간 만료");
			return "member/join";
		}else if(sessionAuthCode.equals(authCode)) {
			logger.info("인증 성공");
			//db의 emailauth를 1로 변경
			memberservice.emailauth(userid);
			return "redirect:join"; 
		}else {
			logger.info("인증키가 일치하니 않습니다");
			return "member/join";
		}
	}
	
	//로그인한 회원의 상세정보
	@GetMapping("info") 
	public void info(Model model, HttpSession session) {
		String userid = (String) session.getAttribute("userid"); //회원 아이디 세션 불러오기
		Member member = memberservice.selectone(userid);
		model.addAttribute("member", member);
	}
	
	@ResponseBody
	@GetMapping("idchack/{userid}") 
	public String idchack(Model model,@PathVariable("userid") String userid) {
		System.out.println("가입제안 아이디 : "+ userid);
		Member member = memberservice.selectone(userid);
		if(member == null) {
			return "0";
		}else {
			return "1";
		}
	}
	
	//이메일 전송
	@ResponseBody
	@RequestMapping("emailchack")
	public String emailCheck(String userid, String email, HttpSession session) throws Exception{
		String authCode = mailSendService.sendAuthMail(email, userid);
		//인증키를 세션에 넣기
		//key:userid, value : authCode 인 세션생성
		session.setAttribute(userid , authCode);
		session.setMaxInactiveInterval(60*60*2);
		return "ok";
	}
	
	
	
}
