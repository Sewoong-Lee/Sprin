package org.spring.my.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.spring.my.dto.Board;
import org.spring.my.dto.Page;
import org.spring.my.dto.Reply;
import org.spring.my.service.BoardService;
import org.spring.my.service.FileService;
import org.spring.my.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("board")
@SessionAttributes("page")  // @ModelAttribute("page") 의 내용을 세션에도 담는다
public class BoardController {
	
	@Autowired
	private BoardService boardservice;
	@Autowired
	private FileService fileservice;
	@Autowired
	private ReplyService replyservice;
	
	//게시물 등록으로
	@GetMapping("add")
	public void add() {
	}
	
	//게시물 등록으로
	@PostMapping("add")
	public String add(Board board, HttpServletRequest request) throws Exception{
		//사용자의 아이피 주입
		board.setIp(request.getRemoteAddr());
		boardservice.insert(board);
		
		return "redirect:list";
	}
	//세션 생성
	@RequestMapping("/")  // 항상 얘 먼저 시작해서 세션이 생성되게 만들어야함
	public String home(Page page, Model model) { 
		//모델을 생성해서 @SessionAttributes 에 생성
		model.addAttribute("page", page);  //맨 위의 @SessionAttributes("page")에도 생성
		
		return "redirect:list";  //세션에 저장후 리스트 실행
	}
	
	//리스트 조회 후 폼으로 이동
	//@ModelAttribute("page") : 뷰까지 페이지 내용 전달
	//@ModelAttribute 1.단독 : 내용을 뷰까지 전달 / 2.@SessionAttributes("page")와 함께 : 세션에도 내용 저장
	@GetMapping("list")
	public void list(Model model,@ModelAttribute("page") Page page) throws Exception{
		
		List<Map<String,Object>> blist = boardservice.selectlist(page);
		model.addAttribute("blist", blist);
		//model.addAttribute("page", page);
	}
	
	//상세조회 폼으로 이동
	@GetMapping("detail")
	public void detail(Model model, int bnum, HttpSession seeeion) throws Exception{
		//임시로 새션 아이디를 정할 수 있다
		//seeeion.setAttribute("userid", "www");
		//조회수 +1
		String userid = (String)seeeion.getAttribute("userid"); //접속된 아이디
		boardservice.readcountadd(bnum, userid); //잠깐 하드코딩
		
		//한건 조회
		Map<String, Object> bfmap = boardservice.selectone(bnum, userid);
		model.addAttribute("bfmap", bfmap);

	}
	
	//좋아요를 눌렀을때
	@ResponseBody
	@GetMapping("like/{bnum}")
	public String like(@PathVariable("bnum") int bnum, HttpSession seeeion) throws Exception{
		String userid = (String)seeeion.getAttribute("userid"); //접속된 아이디
		
		boardservice.updatelikecnt(bnum, userid);
		return "ok";
	}
	
	//좋아요 취소
	@ResponseBody
	@GetMapping("likecancel/{bnum}")
	public String likecancel(@PathVariable("bnum") int bnum, HttpSession seeeion) throws Exception{
		String userid = (String)seeeion.getAttribute("userid"); //접속된 아이디
		boardservice.updatelikecntcancel(bnum, userid);
		return "ok";
	}
	
	//싫어요를 눌렀을때
	@ResponseBody
	@GetMapping("dislike/{bnum}")
	public String dislike(@PathVariable("bnum") int bnum, HttpSession seeeion) throws Exception{
		String userid = (String)seeeion.getAttribute("userid"); //접속된 아이디
			
		boardservice.updatedislikecnt(bnum, userid);
		return "ok";
	}
	
	//싫어요 취소
	@ResponseBody
	@GetMapping("dislikecancel/{bnum}")
	public String dislikecancel(@PathVariable("bnum") int bnum, HttpSession seeeion) throws Exception{
		String userid = (String)seeeion.getAttribute("userid"); //접속된 아이디
		boardservice.updatedislikecntcancel(bnum, userid);
		return "ok";
	}
	
	// 원본 내용 삭제(removeyn 값 변경)
	@GetMapping("delete")
	public String removeyn(int bnum) throws Exception{
		boardservice.updateremoveyn(bnum);

		return "redirect:list";
	}
	
	//게시물 수정 폼으로
	@GetMapping("modify")
	public String modify(int bnum, HttpSession seeeion, Model model) throws Exception{
		String userid = (String)seeeion.getAttribute("userid"); //접속된 아이디
		//한건 조회
		Map<String, Object> bfmap = boardservice.selectone(bnum, userid);
		model.addAttribute("bfmap", bfmap);
		
		return "board/modify";
	}
	
	//게시물 수정 
	@PostMapping("modify")  //@RequestParam 보내온 데이터 형식을 바꿔줌 //required : 데이터가 없어도 에러가 안생김
	public String modify(Board board, 
			@RequestParam(value = "filedelete", required = false) List<Integer> filedeletelist, 
			HttpServletRequest request) throws Exception{ 
		
		//보드에 아이피 세팅
		board.setIp(request.getRemoteAddr());
		boardservice.update(board, filedeletelist);
		
		return "redirect:list";
	}
	
	
	
	
	
}
