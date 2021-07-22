package org.spring.my.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.my.dto.Request;
import org.spring.my.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("request")
public class requestcontroller {
	
	private static final Logger logger = LoggerFactory.getLogger(requestcontroller.class);
	
	@Autowired
	private RequestService requestservice;
	
	
	@GetMapping("add")
	public String request(Request request, Model model, RedirectAttributes ratter) {
		logger.info(request.toString());
		
		requestservice.insert(request);
		//수강신청 조회 화면으로 이동
		//addFlashAttribute : 한번 뿌리고 사라짐
		ratter.addFlashAttribute("msg", "수강신청 완료"); //한번만 사용
		ratter.addAttribute("ccode", request.getCcode()); //계속 유지
		//course의 컨트롤러로 이동
		return "redirect:/course/detail";
	}
}
