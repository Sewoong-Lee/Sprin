package org.spring.my.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.my.HomeController;
import org.spring.my.dto.MemberDTO;
import org.spring.my.service.MemberService;
import org.spring.my.service.MemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "member")
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	//스프링에게 객체를 생성해서 주입 (MemberServiceImpl)
	//해당 클래스에 어노테이션이 주입되어 스프링의 관리하에 두어야한다
	//인터페이스 사용이유
	//1)개발자간의 약속을 정함
	//2)클래스(객체)간의 연결을 느슨하게 해준다.
	@Autowired 
	private MemberService memberservice;
	
	//회원가입 폼으로 이동
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String insert() {
		
		return "0202_Member/join";
	}
	
	//회원 가입
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String insert(MemberDTO memberdto, RedirectAttributes rattr) {
		logger.info(memberdto.toString());
		
		memberservice.insert(memberdto);
		
		//리다이랙트 방식으로 정보를 보내는 방법
		//addFlashAttribute : 리다이렉트방식으로 정보를 보내는데 한번만 작동
		rattr.addFlashAttribute("msg", "완완완~");
		
		//redirect 방식 : resolver(뒤에 jsp등 붙는거) 적용 안됨 (아래 리스트로 이동)
		//resolver가 작동하면 뷰로 가는거 안되면 메소드 실행(뒤에 join 주소만 바꾸겠다)
		//return "forward:list"; 주소 변경 안됨
		return "redirect:list"; //주소 변경
	}
	
	//리스트
	@RequestMapping(value = "/list")
	public String list(Model model) {
		
		//조회
		List<MemberDTO> list = memberservice.list();
		model.addAttribute("list", list);
		
		logger.info(list.toString());
		return "0202_Member/list";
	}
	
	//상세조회
	@RequestMapping(value = "/detail")
	public String detail(String userid, Model model) {
		logger.info(userid);
		//상세조회
		MemberDTO mdto = memberservice.selectone(userid);
		model.addAttribute("mdto", mdto);
		logger.info(mdto.toString());
		return "0202_Member/detail";
	}
	
	//삭제
		@RequestMapping(value = "/delet")
		public String delet(String userid, RedirectAttributes rattr) {
			logger.info("삭제"+userid);
			//삭제
			int cnt = memberservice.delet(userid);
			rattr.addFlashAttribute("cnt", cnt);
			
			return "redirect:list";
		}
	
	//수정폼
		@GetMapping("/modify")
		public String modify(String userid, Model model) {
			logger.info(userid);
			//수정폼
			MemberDTO mdto = memberservice.selectone(userid);
			model.addAttribute("mdto", mdto);
			logger.info(mdto.toString());
			return "0202_Member/modify";
		}
	//수정
		@PostMapping("/modify")
		public String modify(MemberDTO mdto, Model model, RedirectAttributes rattr) {
			logger.info(mdto.toString());
			//수정
			int cnt = memberservice.update(mdto);
			
			rattr.addAttribute("userid", mdto.getUserid());
			return "redirect:detail";
		}
	
	
}
