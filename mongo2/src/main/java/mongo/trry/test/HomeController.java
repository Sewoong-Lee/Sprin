package mongo.trry.test;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("controller")
public class HomeController {
	
	@Autowired
	private UserModifyServiceimpl userModifyService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		System.out.println("홈 진입");
		
		return "home";
	}
	
	//인서트
	@ResponseBody
	@RequestMapping(value = "/mongoinputajax", method = RequestMethod.POST, produces = "application/json" )
	public Map<String, Object> MongoInsert(@RequestBody Map<String, Object> info) {
		Map<String, Object> retVal = new HashMap<String, Object>();
		System.out.println("인서트 진입");
		System.out.println(info);
		
		//등록 서비스 호출
		userModifyService.insertuserinfo(info);
		
		retVal.put("val", "1");
		
		
		return retVal;
	}
	//삭제
	@ResponseBody
	@RequestMapping(value = "/mongoremove", method = RequestMethod.POST )
	public String Mongoremove(String value) {
		System.out.println("삭제 컨트롤러 진입");
		
		System.out.println(value);
		//삭제 서비스 호출
		userModifyService.removemongo("name",value);
		
		
		return "성공";
	}
	
	//수정
	@ResponseBody
	@RequestMapping(value = "/mongoupdate", method = RequestMethod.POST )
	public String Mongoupdate(@RequestParam Map<String, Object> map) {
		System.out.println("삭제 컨트롤러 진입");
		
		System.out.println(map);
		//삭제 서비스 호출
		userModifyService.updatemongo(map);
		
		
		return "성공";
	}
	
	//조회
	@ResponseBody
	@RequestMapping(value = "/mongofind", method = RequestMethod.POST )
	public List<MongoDTO> Mongofind(String name) {
		System.out.println("조회 컨트롤러 진입");
		
		System.out.println(name);
		//삭제 서비스 호출
		List<MongoDTO> map = userModifyService.findmongo(name);
		
		System.out.println(map.toString());
		return map;
	}
	
	
	
	
}
