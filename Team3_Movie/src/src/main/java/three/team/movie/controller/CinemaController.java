package three.team.movie.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import three.team.movie.dto.Cinema;
import three.team.movie.dto.Mv_city;
import three.team.movie.dto.Mv_sales;
import three.team.movie.dto.Mv_seats;
import three.team.movie.dto.Mv_time;
import three.team.movie.service.CinemaService;

@Controller
@RequestMapping("cinema")
public class CinemaController {
	private static final Logger logger = LoggerFactory.getLogger(CinemaController.class);

	@Autowired
	private CinemaService cinemaservice;
	
	//영상 테스트
	@RequestMapping("test")
	public void test() {

	}
	
	//지역을 누르면 영화관 보이게
	@ResponseBody
	@GetMapping("city/{city_code}")
	public List<Cinema> cine(@PathVariable("city_code") String city_code) {
		// System.out.println("zzz"+city_code);

		List<Cinema> Cinemalist = cinemaservice.cinemaselectlist(city_code);
		logger.info(Cinemalist.toString());
		return Cinemalist;
	}

	// 예매 준비 폼으로
	//********영화 디테일에서 영화 순번 받아와야함
	@GetMapping("city")
	public void city(Model model, Mv_sales mv_sales , int movie_num) { 
		//영화 순번 하드코딩
//		int movie_num = 1; //잠깐 하드코딩
		// 지역 리스트 가져오기
		List<Mv_city> citylist = cinemaservice.cityselectlist();
		// 예매 가능 날짜 가져오기
		List<Mv_time> timelist = cinemaservice.timeselectlist(movie_num);
		logger.info(citylist.toString());
		logger.info("timelist: " + timelist.toString());
		model.addAttribute("citylist", citylist);
		model.addAttribute("timelist", timelist);
		model.addAttribute("movie_num", movie_num); //영화 디테일에서 가져온 넘버

	}

	// 상영 시간표 보내기
	@ResponseBody
	@GetMapping("time")
	public List<Mv_time> time(String cinema_code,String selectday, int movie_num) {
		System.out.println("zzz "+cinema_code+"  "+selectday+"  "+movie_num);
		Map<String,Object> timemap = new HashMap<>();
		timemap.put("cinema_code", cinema_code);
		timemap.put("selectday", selectday);
		timemap.put("movie_num", movie_num);
		
		List<Mv_time> saletimelist = cinemaservice.saletimelist(timemap);
		logger.info(saletimelist.toString());
		return saletimelist;
	}
	
	// 좌석 보내기
	@ResponseBody
	@GetMapping("seats")
	public List<Mv_seats> seats(String time_code) {
		List<Mv_seats> seats_list = cinemaservice.seats_alllist(time_code);
		logger.info(seats_list.toString());
		return seats_list;
	}
	
//	//예매 버튼 누르면 저장 하기
//	@GetMapping("sales")
//	//public String sales(Mv_sales mv_sales,int movie_num, String city_code, String cinema_code, String time_code, String[] seats_code) {
//	public String sales(Mv_sales mv_sales,int movie_num, String city_code, String cinema_code,@RequestParam(value="seats_code",required=true) List<String> seats_code, HttpSession session) {
//		logger.info(movie_num +" "+ city_code +" "+ cinema_code);  //혹시몰라 가져온거
//		//logger.info(seats_code[0]);
//		logger.info(mv_sales.toString());
//		
//		// Mv_sales DTO에 값 넣기
//		//session.getAttribute("userid"); //세션에 저장된 아이디 가져오기
//		String user_id = "ddd"; //아이디 잠깐 하드코딩
//		mv_sales.setUser_id(user_id); //아이디 넣기
//		mv_sales.setTickets(seats_code.size()); //구매 티켓 수량
//		mv_sales.setSales_check("1"); //결제 여부 넣기
//		
//		//cinemaservice.salesinsert(mv_sales, seats_code);
//		
//		return null;
//	}
	
	 

}
