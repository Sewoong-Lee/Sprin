package org.spring.my;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//서블릿
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		model.addAttribute("zzz", "qqqq");
		model.addAttribute("serverTime", formattedDate );
		//servlet - context.xml 의 Resolver의 prefix와 suffix에 의해 경로 설정
		//web-inf/views/home.jsp로 리턴하라는뜻 (서블릿 컨텍스트에 정의 되어있음)
		return "home";
	}
	
	@RequestMapping("stringmethod") //슬레시 없어도 알아서 붙여줌
	public String stringmethod(Model model) {
		model.addAttribute("name","그라카카카카카");
		//포워드 방식으로 이동
		return "0201/20210630_01";
	}
	
	//리턴값이 void
	//void 일경우 매핑정보의 jsp로 이동
	@RequestMapping("0201/20210630_02")
	public void voidMethod(Model model) {
		model.addAttribute("age", 20);
	}
	
	//폼으로 이동
	//전달 방식(메소드)에 따라 따로 작동
	@RequestMapping(value = "getparam", method = RequestMethod.GET) 
	public String getparam() {
		
		return "0201/20210630_03_param";
	}
	
	//파라메터 전달받음
	//매핑 정보와 메소드 방식이 일치해야함
	@RequestMapping(value = "getparam", method = RequestMethod.POST) 
	//public String getparam(@RequestParam(value = "nicname") String name, @RequestParam int age, Model model) {
	//변수명과 파라메터의 네임이 같다면 생략 가능
	public String getparam(String name, int age, Model model) {
		logger.info("이름"+name);
		//스트링만 들어가서 앞에 문자열을 놔줘서 문자로 변환
		logger.info("나이"+age);
		//System.out.println(name + " "+ age);
		model.addAttribute("name", name);
		model.addAttribute("age", age);
		return "0201/20210630_03_param";
	}
	
	//반지름 구하기
	@RequestMapping(value = "area", method = RequestMethod.GET) 
	public String area() {
		
		return "0201/20210630_04_area";
	}
	@RequestMapping(value = "area", method = RequestMethod.POST) 
	//@ModelAttribute로 변수명을 동일하게 작성하여 자동으로 다시 다음 폼까지 전달 (두개 보낼거면 두개)
	//@RequestParam : 값이 없을경우 디폴트 값 설정
	public String area(@ModelAttribute("name") String name,@RequestParam(defaultValue = "0") double area, Model model) {
		logger.info("반지름"+area);
		double ar = area * area * Math.PI;
		
		model.addAttribute("ar", "원의 넓이는 "+ar);
		return "0201/20210630_04_area";
	}
	
	//로그인
	@RequestMapping(value = "login", method = RequestMethod.GET) 
	public String login() {
		
		return "0201/20210630_05_login";
	}
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(@ModelAttribute("userid")String userid, String passwd, Model model) {
		logger.info("아이디"+userid);
		logger.info("비번"+passwd);
		
		String myid = "qqq";
		String mypw = "123";
		
		String msg = "";
		if(myid.equals(userid) && mypw.equals(passwd)) {
			msg = userid+"님 환영합니다.";
		}else {
			msg = "로그인실패";
		}
		
		model.addAttribute("msg", msg);
		return "0201/20210630_05_login";
	}
	
	
	
	
	
}
