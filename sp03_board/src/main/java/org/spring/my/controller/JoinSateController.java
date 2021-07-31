package org.spring.my.controller;

import java.util.List;
import java.util.Map;

import org.spring.my.dao.JoinStateDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("joinstate")
public class JoinSateController {
	
	@Autowired
	private JoinStateDAO joinstateDAO;
	
	//회원가입 폼으로
	@GetMapping("list") 
	public void add(Model model) {
		List<Map<String, Object>> jlist = joinstateDAO.joinstate();
		model.addAttribute("jlist", jlist);
	}
}
