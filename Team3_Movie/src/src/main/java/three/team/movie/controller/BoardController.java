package three.team.movie.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import three.team.movie.dto.MV_Board_Page;
import three.team.movie.dto.Mv_board;
import three.team.movie.service.BoardService;

@RequestMapping("board")
@SessionAttributes("mv_board_page")
@Controller
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private BoardService boardService;
	
	//세션 생성
	@RequestMapping("/")  // 항상 얘 먼저 시작해서 세션이 생성되게 만들어야함
	public String addsession(MV_Board_Page mv_board_page, Model model) { 
		//모델을 생성해서 @SessionAttributes 에 생성
		model.addAttribute("mv_board_page", mv_board_page);  //맨 위의 @SessionAttributes("page")에도 생성
		
		return "redirect:boardlist";  //세션에 저장후 리스트 실행
	}
	
	
	@GetMapping("boardlist")
	public void  getevent(Model model, @ModelAttribute("mv_board_page") MV_Board_Page mv_board_page ) {
		List<Map<String,Object>> boardlist =  boardService.boardlist(mv_board_page);
		model.addAttribute("boardlist",boardlist);
	}
	
	//이벤트 공지 등록으로
	@GetMapping("boardadd")
	public void  boardadd() {
		
	}
	//이벤트 공지 등록
	@PostMapping("boardadd")
	public String eventadd(Mv_board mv_board, HttpServletRequest request) throws Exception{
		//사용자의 아이피 주입
		mv_board.setIp(request.getRemoteAddr());
		String user_id = (String) request.getAttribute("user_id");
		mv_board.setUser_id(user_id);  
		boardService.insert(mv_board);
		
		return "redirect:boardlist";
	}
	
	@GetMapping("boarddetail")
	public void  eventdetail(Model model, int board_num, HttpSession seeeion) {
		logger.info(board_num+"");
		String user_id =(String) seeeion.getAttribute("user_id");
		boardService.readcountadd(board_num, user_id);
		
		Map<String, Object> board_detail_map = boardService.selectone(board_num);
		model.addAttribute("board_detail_map", board_detail_map);
	}
	
	//게시물 삭제
	@GetMapping("boarddelete")
	public String  eventdelete(int board_num) {
		logger.info(board_num+"");
		boardService.board_delete(board_num);
		
		return "redirect:boardlist";
	}
	
	//게시물 수정
	@GetMapping("boardmodify")
	public String  eventmodifyform(int board_num, Model model) {
		logger.info(board_num+"");
		
		Map<String, Object> board_list = boardService.selectone(board_num);
		model.addAttribute("board_list", board_list);
		
		
		return "board/boardmodify";
	}
	
	//게시물 수정
	@PostMapping("boardmodify")
	public String  eventmodify(Mv_board mv_board, @RequestParam(value = "filedelete", required = false) List<Integer> filedeletelist, 
			HttpServletRequest request) {
		logger.info(mv_board.toString());
		
		mv_board.setIp(request.getRemoteAddr());
		boardService.board_update(mv_board, filedeletelist);
		
		
		return "redirect:boardlist";
	}
	
	
}
