package three.team.movie.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import three.team.movie.dto.Mv_user;
import three.team.movie.service.MailAuthService;
import three.team.movie.service.Mv_genreService;
import three.team.movie.service.Mv_userService;

@Controller
public class HomeController {
	
	@Autowired
	private Mv_genreService mv_genreService;
	@Autowired
	private MailAuthService mailAuthService;
	@Autowired
	private Mv_userService mv_userService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@GetMapping("CCV")
	public void main() {}
	
	@GetMapping("emailAuth")
	public void emailAuth() {}
	
	@ResponseBody
	//@GetMapping(value="emailCheck",  produces="application/text; charset=utf-8")
	@GetMapping("emailCheck")
	public Map<String, Object> emailCheck(String email, HttpSession session) throws Exception {
		Map<String, String> emailChecked = mv_userService.emailChecked(email);
		System.out.println(emailChecked);
		String msg = "";
		int code ;
		Map<String, Object> resultMap = new HashMap<>();
		if (emailChecked == null) {
			//가입 가능
			String authCode = mailAuthService.sendMail(email);
			session.setAttribute(email, authCode);
			session.setMaxInactiveInterval(60*1);
			msg = "인증 메일 전송이 완료되었습니다.";
			code = 1;
		}
		else {
			//가입 불가
			msg = "이미 가입된 회원입니다.";
			code = 0;
		}
		resultMap.put("msg", msg);
		resultMap.put("code", code);
		System.out.println("결과 : " + resultMap);
		return resultMap;
	}
	
	@RequestMapping("getSignUpF")
	public String signUpF(@RequestParam("email") String email,
							@RequestParam("msg") String msg, Model model) {
		List<Map<String, String>> genreList = mv_genreService.getGenreList();
		System.out.println(genreList);
		System.out.println("email : " + email);
		System.out.println("msg : " + msg);
		model.addAttribute("email", email);
		model.addAttribute("msg", msg);
		model.addAttribute("genreList", genreList);
		return "/signUp";
	}
	
	@PostMapping("addMember")
	public String signUp(Mv_user mv_user, Model model) {
		return "redirect:login";
	}
	
	@GetMapping("codeCheck")
	public String codeCheck(String email, String authCode, HttpSession session, RedirectAttributes rattr) {
		String sessionAuthCode = (String)session.getAttribute(email);
		String msg = "";
		if (sessionAuthCode == null) { //세션 기간이 종료되면 key사라짐
			logger.info("세션 기간 만료");
			msg = "인증 시간이 종료되었습니다.\n다시 시도해주세요.";
			rattr.addFlashAttribute("msg", msg);
			return "redirect:CCV";
		}else if (sessionAuthCode.equals(authCode)) {
			logger.info("인증 성공");
			msg = "이메일 인증에 성공하였습니다.";
			rattr.addAttribute("email", email);
			rattr.addAttribute("msg", msg);
			return "redirect:getSignUpF";
		}else {
			logger.info("인증키가 일치하지 않습니다.");
			msg = "인증키가 일치하지 않습니다.\n다시 시도해주세요.";
			rattr.addAttribute("msg", msg);
			return "redirect:emailAuth";
		}

	}
	@GetMapping("emailAuthCheck")
	public void emailAuthCheck() {}
	
	
}
