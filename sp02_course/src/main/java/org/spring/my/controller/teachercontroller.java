package org.spring.my.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.my.dto.Teacher;
import org.spring.my.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("teacher")
public class teachercontroller {
	private static final Logger logger = LoggerFactory.getLogger(teachercontroller.class);
	
	@Autowired
	private TeacherService teacherservice;
	
	@GetMapping("add")
	public void add() {
		//void는 경로명과 같은곳으로 리턴하겠다
		//void일 경우 : prefix + mapping 경로 + suffix
		///student/add.jsp
	}
	
	//저장
	@PostMapping("add")
	public String add(Teacher teacher) {
		logger.info(teacher.toString());
			
		teacherservice.insert(teacher);
		return "teacher/add";
	}
	
	
	
	
	
	
	
}
