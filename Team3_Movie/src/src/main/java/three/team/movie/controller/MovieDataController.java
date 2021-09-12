package three.team.movie.controller;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import three.team.movie.dao.MovieDataDAO;
import three.team.movie.dto.Mv_Page;
import three.team.movie.dto.Mv_movie_data;
import three.team.movie.service.MovieApiService;
import three.team.movie.service.MovieDataService;
import three.team.movie.service.ReplyService;


@Controller
@RequestMapping("moviedata")
@SessionAttributes("page") 
public class MovieDataController {
	
	private static final Logger logger = LoggerFactory.getLogger(MovieDataController.class);
	
	@Autowired
	private MovieApiService movieApiService; 
	
	@Autowired
	private MovieDataService movieDataService;
	
	@Autowired
	private MovieDataDAO movieDataDAO;
	
	@Autowired
	private ReplyService replyService;
	
	//세션유저아이디 체크..로그인시 뷰 보이게..
	
	//인서트는 관리자만 가능 (우선DB에서만 인서트해서 보류중.)
	
	//페이지 생성 
	@RequestMapping("/")
	public String home(Mv_Page mv_Page,Model model,HttpSession session, HttpServletRequest request) {
		String user_id = (String) session.getAttribute("user_id");
		System.out.println("user_id"+user_id);
		model.addAttribute("mv_Page",mv_Page);
		session.setAttribute("curpageTot",1);
		session.setAttribute("curpageUser",1);
		
		if(user_id != null) {
			return "redirect:listuser";
		}else {
			return "redirect:list";
		}
		
		
	}
	
	//상세조회 폼으로 detail 
	@RequestMapping("detail")
	public String detail(HttpServletRequest request, Model model,HttpServletResponse response,HttpSession session,RedirectAttributes rattr){ 
		int movie_num = Integer.parseInt(request.getParameter("movie_num"));
		//영화 한건 조회 (detail)
		System.out.println(movie_num);
		 Map<String, Object> mv_movie_data = movieDataService.selectOne(movie_num);
		 logger.info("info디테일"+mv_movie_data.toString());
	
//		 Cookie c = new Cookie("curpageTot",request.getParameter("curPage"));
//		 response.addCookie(c);
//		 System.out.println("쿠키값"+c.toString());
		 
		 model.addAttribute("movieDetail",mv_movie_data);
		 int curPage=1;
		 if(request.getParameter("curPage")==null) {curPage=1;}
		 else { curPage=Integer.parseInt(request.getParameter("curPage")); }

		 int curPageListUser=1;
		 if(request.getParameter("curPageListUser")==null) {curPageListUser=1;}
		 else { curPageListUser=Integer.parseInt(request.getParameter("curPageListUser")); }
		 
		 session.setAttribute("curpageTot",curPage);
		 session.setAttribute("curpageUser",curPageListUser);
		 
		 int curPageReply=1;
		 if(request.getParameter("curPageReply")==null) {curPageReply=1;}
		 else { curPageReply=Integer.parseInt(request.getParameter("curPageReply")); }
	
		 //별 평점 계산 
		 
		 Double starResult = movieDataDAO.starRating(movie_num);
		 if(starResult==null) {
			 starResult=0.0;
		 }
		 
		 System.out.println("별평점=="+starResult);
		 //댓글 페이징
		 Map<String, Object> replyMap = movieDataService.replyPaging(movie_num,curPageReply);
		 replyMap.put("movie_num", movie_num);
		 List<Map<String, Object>> replyList = replyService.selectList(replyMap);
		 
		 model.addAttribute("starResult",starResult);
		 model.addAttribute("replyMap",replyMap);
		 model.addAttribute("replyList",replyList);
		 logger.info("댓글 전체리스트 "+replyList.toString());
		 return "movie/detail";
	}

	//영화 전체 리스트 selectList (해더부분의 영화리스트 클릭/메인화면 영화리스트 )
	@GetMapping("list")
	public String list(@ModelAttribute("mv_Page")Mv_Page mv_Page,Model model,HttpServletResponse response) {
		System.out.println("파인드키:==="+mv_Page.getFindkey());
		System.out.println("페이지====="+mv_Page);
		//쿠키영화 등록
		
		Map<String, Object> movieMap = movieDataService.selectList(mv_Page);
		System.out.println("무비리스트===: "+movieMap);
		model.addAttribute("movieList", (List<Mv_movie_data>) movieMap.get("list"));//전체 리스트
	
		return "movie/mainlist";
	}
	
	//로그인시 등록된 태그별 영화 리스트 출력 + (인기)최신영화 순으로 출력
	@RequestMapping("listuser") //@ModelAttribute
	public String movieListUser(@ModelAttribute("mv_Page")Mv_Page mv_Page,Model model,HttpSession session) {
		String user_id = (String) session.getAttribute("user_id");//세션 아이디 가져오기 
		Map<String, Object> findmap = new HashMap<String, Object>();
		findmap.put("user_id", user_id);
		
		//page객체 생성 회원관심리스트 페이지용  
		Mv_Page page2 = new Mv_Page();
		page2.setCurPageListUser(mv_Page.getCurPageListUser());
		findmap.put("mvUser_Page", page2);
		
//		session.setAttribute("curpageListUser",mv_Page.getCurPageListUser());
//		session.setAttribute("curpageListTot", mv_Page.getCurPage());
		
		//회원관심 리스트 페이징 처리 값 넘기기 
		List<Map<String, Object>> movieListUser = movieDataService.selectListUser(findmap);
		model.addAttribute("movieListUser",movieListUser); //회원관심 영화리스트
		model.addAttribute("mvUser_Page",findmap.get("mvUser_Page"));
		System.out.println("회원 리스트 페이지========="+findmap.get("mvUser_Page"));
		
		//전체 리스트(검색값 뷰에서 추가 해야함)
		Map<String, Object> movieMap = movieDataService.selectList(mv_Page);
		logger.info("ddddd"+movieMap.toString());
		System.out.println("전체 페이지========="+mv_Page);
		model.addAttribute("movieList",movieMap.get("list"));		//영화전체 리스트 (등록된/최신순으로)
		
		return "movie/list";
	}
	
	//영화 전체리스트 ajax
	@ResponseBody
	@GetMapping("listTotAjax")
	public Map<String, Object> listTotAjax(HttpServletRequest request, HttpServletResponse response){
		Mv_Page mv_Page = new Mv_Page();
		
		int curPage = Integer.parseInt(request.getParameter("curPage"));
		mv_Page.setCurPage(curPage);
		
		//전체 리스트(검색값 뷰에서 추가 해야함)
		Map<String, Object> movieMap = movieDataService.selectList(mv_Page);
		logger.info(movieMap.toString());
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		returnMap.put("movieList", (List<Mv_movie_data>) movieMap.get("list"));//전체 리스트
		returnMap.put("mvUser_Page", movieMap.get("paging"));
		returnMap.put("success","ok");
		return returnMap;	
	}
	
	//회원관심 영화리스트 ajax 용
	@ResponseBody
	@GetMapping("listuserAjax")
	public Map<String, Object> listUserAjax(HttpServletRequest request, HttpServletRequest response,HttpSession session) {
		String user_id = (String) session.getAttribute("user_id");
		
		Map<String, Object> findmap = new HashMap<String, Object>();
		findmap.put("user_id", user_id);
		//page객체 생성 회원관심리스트 페이지용  
		int curPageListUser = Integer.parseInt(request.getParameter("curPageListUser"));
		Mv_Page page2 = new Mv_Page();
		page2.setCurPageListUser(curPageListUser);
		findmap.put("mvUser_Page", page2);
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		List<Map<String, Object>> movieListUser = movieDataService.selectListUser(findmap);
		returnMap.put("movieListUser",movieListUser); //회원관심 영화리스트
		returnMap.put("mvUser_Page",findmap.get("mvUser_Page"));
		returnMap.put("success", "ok");
		return returnMap;
		
	}
	
	
	//테스트 
	@GetMapping("test")
	public String test() {
		return "movie/test";
	}
	
	
	
	//메인화면 구성시 selectList 2개  (main 화면 뷰)
	//1 회원관심영화 리스트 2 인기개봉자(최신영화순부터) 리스트 출력 2개 같이 출력
	
	//해더화면 검색창에 장르별+제목	
	
	//네이버 api 호출
	@RequestMapping("api")
	public void getApi_naver() {
		String codeName="크루엘라";
		movieApiService.MovieApiCall(codeName);
	}
	
	
	
	
}
