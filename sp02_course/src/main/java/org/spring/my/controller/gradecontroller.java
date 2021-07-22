package org.spring.my.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.my.dto.Grade;
import org.spring.my.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import oracle.jdbc.proxy.annotation.Post;

@Controller
@RequestMapping("grade")
public class gradecontroller {
	private static final Logger logger = LoggerFactory.getLogger(gradecontroller.class);
	
	@Autowired
	private GradeService gradeservice;
	
	//성적등록 폼으로 이동
	@GetMapping("add")
	public void add(Model model) {
		
	}
	//과정 한건 조회
	@GetMapping("subjectlist")
	public String subjectlist(String sno, Model model) {
		List<Map<String,Object>> rlist = gradeservice.selectlist_subject(sno);
		
		model.addAttribute("rlist", rlist);
		return "grade/add";
	}
	
	//성적 점수 저장
	@PostMapping("add")
	public void add(Grade grade) {
		logger.info(grade.toString());
		gradeservice.insert(grade);
	}
	
	//전체 성적 리스트 폼
	@GetMapping("list")
	public void selectlist(Model model) {
		//List<Map<String,Object>> rlist = gradeservice.selectlist();
		
		//model.addAttribute("rlist", rlist);
		
	}
	
	//전체 성적 리스트 폼
	@PostMapping("list")
	public String selectlist(String findkey, String findvalue, Model model) {
		HashMap<String, String> fmap = new HashMap<String, String>();
		fmap.put("findkey", findkey);
		fmap.put("findvalue", findvalue);
		
		logger.info(fmap.toString());
		List<Map<String,Object>> rlist = gradeservice.selectlist(fmap);
		
		model.addAttribute("rlist", rlist);
		return "grade/list";
	}
	
	
	
	
	
	
	
	
}
