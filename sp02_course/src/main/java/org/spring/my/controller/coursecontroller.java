package org.spring.my.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.my.dto.Course;
import org.spring.my.dto.CouseSubject;
import org.spring.my.dto.Subject;
import org.spring.my.dto.Teacher;
import org.spring.my.service.CourseService;
import org.spring.my.service.RequestService;
import org.spring.my.service.SubjectService;
import org.spring.my.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("course")
public class coursecontroller {
	private static final Logger logger = LoggerFactory.getLogger(coursecontroller.class);
	
	@Autowired
	public TeacherService teacherservice;
	@Autowired
	public SubjectService subjectservice;
	@Autowired
	public CourseService courseservice;
	@Autowired
	private RequestService requestservice;
	
	//과정 등록 폼으로 이동
	@GetMapping("add") //조회, 삭제일때 대부분 get방식
	public void add(Model model) {
		//교사 정보
		List<Teacher> tlist = teacherservice.selectlist();
		logger.info(tlist.toString());
		model.addAttribute("tlist", tlist);
		//과목 정보
		List<Subject> slist= subjectservice.selectlist();
		logger.info(slist.toString());
		model.addAttribute("slist", slist);
		
	}
	//과정개설 등록
	@PostMapping("add")  //포스트는 업데이트. 인서트 등 데이터가 많거나 중요한 정보가 있을떄 사용
	public void add(Course course, Model model, @RequestParam("jcode") List<String> jlist) {
		//jcode를 리스트로 받은후 코스dto에 넣어버림
		//@RequestParam("jcode") List<String> 형이 다르므로 @RequestParam 해줘야함(형과 이름이 같으면 생략 가능)
		logger.info(jlist.toString());
		course.setJlist(jlist);
		logger.info(course.toString());
		courseservice.insert(course);
		
	}
	
	//한건 조회
	@GetMapping("detail")
	public void detail(String ccode, Model model) {
		logger.info(ccode);
		//과정 한건 조회
		List<Map<String,Object>> clist = courseservice.selectone(ccode);
		model.addAttribute("clist", clist);
		logger.info(clist.toString());
		
		//수강자 리스트
		List<Map<String,Object>> rlist = requestservice.selectlist(ccode);
		model.addAttribute("rlist", rlist);
		logger.info(rlist.toString());
	}
	
	
	
}
