package three.team.movie.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import three.team.movie.service.NaverService;

@Controller
@RequestMapping("naverLogin")
public class NaverLoginController {
	
	@Autowired
	private NaverService naverService;
	
	@RequestMapping("callBack")
	public String callBack(String code, String state, HttpSession session) throws Exception {
		String access_token = naverService.getToken(code, state);
		//가져온 토큰으로 개인정보 가져오기
		Map<String,String> resultMap = naverService.getUserInfo(access_token);
		naverService.insert(resultMap);
		session.setAttribute("user_id", resultMap.get("email"));
		session.setAttribute("name", resultMap.get("name"));
		return "/CCV";
	}
}
