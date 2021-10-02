package org.spring.my.map;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class mapController {
	@Autowired
	private mapService mapservice;

	@RequestMapping("/map")
	public String home() {

		return "map/kakao210929";
		/* return "map/NewFile"; */
	}
	
	@ResponseBody
	@RequestMapping("/map/mapSerch")
	public List<Map<String, Object>> home(@RequestParam Map<String, Object> map, Model model) {
		System.out.println(map.toString());

		List<Map<String, Object>> listmap = mapservice.selectlist(map);
		System.out.println(listmap.toString());
		
		return listmap;
	}
	

}
