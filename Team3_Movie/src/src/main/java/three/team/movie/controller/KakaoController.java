package three.team.movie.controller;

import org.springframework.stereotype.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import three.team.movie.dto.Mv_sales;
import three.team.movie.service.CinemaService;
import three.team.movie.service.KakaoService;

@SessionAttributes("mv_sales")
@Controller
public class KakaoController {
	private static final Logger logger = LoggerFactory.getLogger(KakaoController.class);
	@Autowired
    private KakaoService kakaoService;
	@Autowired
	private CinemaService cinemaservice;
    
    
	@GetMapping("/kakao")
    public void kakaoPayform() {
        
    }
	
//    @GetMapping("/KakaoService")
//    public void kakaoPayGet() {
//        
//    }
    
    @PostMapping("/KakaoService")
    public String kakaoPay(Mv_sales mv_sales, HttpSession session, Model model) {
    	logger.info("KakaoService post............................................");
        
    	// Mv_sales DTO에 값 넣기
    	//session.getAttribute("userid"); //세션에 저장된 아이디 가져오기
    	String user_id = "ddd"; //아이디 잠깐 하드코딩
    	mv_sales.setUser_id(user_id); //아이디 넣기
    	//예매 번호 넣기
    	int sal_num = cinemaservice.slectmaxsal_num();
    	mv_sales.setSal_num(sal_num+1);
    	
    	mv_sales.setTickets(mv_sales.getSeats_code().size()); //구매 티켓 수량
    	mv_sales.setSales_check("1"); //결제 여부 넣기
    	
    	logger.info("컨트롤러 mv_sales : "+mv_sales.toString());
    	
    	//모델을 생성해서 @SessionAttributes 에 생성
    	model.addAttribute("Mv_sales", mv_sales);  //위의 @SessionAttributes("mv_sales")에도 생성
    	
        return "redirect:" + kakaoService.kakaoPayReady(mv_sales); 
 
    }
    
    @GetMapping("kakaoPaySuccess")
    public void kakaoPaySuccess(@RequestParam("pg_token") String pg_token, Model model,@ModelAttribute("mv_sales") Mv_sales mv_sales) {
    	logger.info("kakaoPaySuccess get............................................");
    	logger.info("kakaoPaySuccess pg_token : " + pg_token);
    	
    	logger.info("kakaoPaySuccess mv_sales : " + mv_sales);
    	//예매 및 좌석 인서트
    	cinemaservice.salesinsert(mv_sales);
    	
    	model.addAttribute("info", KakaoService.kakaoPayInfo(pg_token,mv_sales));
    }
}
