package my.mongo.test;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class JoinController {

	@ResponseBody
	@RequestMapping("/join")
	public Map<String, Object> join() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "victolee");
		map.put("age", 26);
		
		return map;

	}
	
	
//	@ResponseBody
//	@RequestMapping("/insert")
//	public Sting insert(@ModelAttribute GuestBookVO vo) {
//		GuestBookVO guestBookVO = GuestBookService.insert(vo);
//		return "";
//	}
}
