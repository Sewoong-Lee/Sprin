package org.spring.my.controller;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.my.HomeController;
import org.spring.my.dto.Student;
import org.spring.my.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("student")
public class studentcontroller {
	
	private static final Logger logger = LoggerFactory.getLogger(studentcontroller.class);
	
	@Autowired
	private StudentService studentservice;
	
	//등록폼
	@GetMapping("add")
	public void add() {
		//void는 경로명과 같은곳으로 리턴하겠다
		//void일 경우 : prefix + mapping 경로 + suffix
		///student/add.jsp
	}
	//저장
	@PostMapping("add")
	public String add(Student student) {
		logger.info(student.toString());
		
		studentservice.insert(student);
		return "redirect:list";
	}
	
	//팝업 띄우기
	//겟방식 포스트방식 전체 처리
	//GET: 주소창 띄우시
	//post : 콜백 주소(주소 결과값)
	@RequestMapping("jusoPopup")
	public String test2() {

		return "student/jusoPopup";
	}
	
	@GetMapping("/")
	public String list() {
		//void는 경로명과 같은곳으로 리턴하겠다
		//void일 경우 : prefix + mapping 경로 + suffix
		///student/add.jsp
		return "student/list";
	}
	//조회
	@RequestMapping("list")
	public String list(@RequestParam HashMap<String,String> findmap, Model model) {
		logger.info(findmap.toString()); //매개 변수 2개를 맵으로 한번에 받음
		
		List<Student> slist = studentservice.selectlist(findmap);
		
		model.addAttribute("slist", slist);
		return "student/list";
	}
	
	//상세 조회
	@GetMapping("detail")
	public String detail(String sno, Model model) {
		logger.info(sno); 
		
		Student student = studentservice.selectlist(sno);
		
		model.addAttribute("student", student);
		logger.info(student.toString()); 
		return "student/detail";
	}
	
	//수정폼으로
	@GetMapping("modify")
	public String modify(String sno, Model model) {
		logger.info(sno); 
			
		Student student = studentservice.selectlist(sno);
			
		model.addAttribute("student", student);
		logger.info(student.toString()); 
		return "student/modify";
	}
	
	//수정
		@PostMapping("modify")
		public String modify(@ModelAttribute Student student, RedirectAttributes ratter) {
			logger.info(student.toString()); 
			
			studentservice.update(student);
			
			logger.info(student.toString()); 
			ratter.addAttribute("sno", student.getSno());
			return "redirect:detail";
		}
		
	//삭제
	@GetMapping("delete")
	public String delete(String sno) {
		logger.info(sno); 
				
		studentservice.delete(sno);
			
		return "redirect:list";
	}
		
		
	//중복 체크
	@GetMapping("nocheck")
	public String nocheck(@ModelAttribute("sno") String sno, Model model) {
		//@ModelAttribute("sno") 뷰까지 다시 전달
		logger.info("체크"+sno); 
		
		Student student = studentservice.selectone(sno);
		
		String msg, useok;
		
		if(student == null) {
			msg = "사용 가능 학번";
			useok = "y"; //사용 가능 여부
		}else {
			msg = "중복 학번";
			useok = "n";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("useok", useok);
		return "student/add";
	}	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
}
