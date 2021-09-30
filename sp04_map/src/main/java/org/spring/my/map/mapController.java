package org.spring.my.map;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class mapController {
	@Autowired
	private mapService mapservice;
	
	@RequestMapping("/map")
	public String home() { 
		
		return "map/kakao210929";
	}
	
	@RequestMapping("/map/mapSerch")
	public String home(@RequestParam Map<String, Object> map, Model model) { 
		System.out.println(map.toString());
		
		return "map";
	}
	
	
	
}
