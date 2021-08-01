package three.team.movie.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import three.team.movie.service.ApiService;


@Controller
@RequestMapping("naverapi")
public class NaverApiController {

	private static final Logger logger = LoggerFactory.getLogger(NaverApiController.class);
	
	@Autowired
	private ApiService apiService;
	
	
	@RequestMapping("naver")
	public void getApi_naver() {
		
		String codeName="보스 베이비";
		apiService.apiCall(codeName);
		
	}
	
	
	
}
