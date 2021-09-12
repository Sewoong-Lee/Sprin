package three.team.movie.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import three.team.movie.dto.Mv_movie_reply;
import three.team.movie.service.MovieDataService;
import three.team.movie.service.ReplyService;

@Controller
@RequestMapping("reply")
public class ReplyController {

	private static final Logger logger = LoggerFactory.getLogger(ReplyController.class);
	
	@Autowired
	private ReplyService replyService;
	
	@Autowired
	private MovieDataService movieDataService;
	
	//원본댓글 추가 
	@ResponseBody
	@RequestMapping(value = "/",method = RequestMethod.POST)
	public String add(@RequestBody Mv_movie_reply reply, HttpServletRequest request,HttpSession session) {
		String user_id = (String) session.getAttribute("user_id");
		System.out.println("유저아이디: "+user_id);
		reply.setUser_id(user_id);
		reply.setIp(request.getRemoteAddr());
		logger.info(reply.toString());
		//댓글 추가
		replyService.insert(reply);
		return "ok";
	}
	
	//원본댓글 전체리스트 조회 
	@ResponseBody
	@RequestMapping("list")
	public List<Map<String,Object>> list(HttpServletRequest request){
		System.out.println("댓글넘버"+request.getParameter("movie_num"));
		
		int movie_num = Integer.parseInt(request.getParameter("movie_num"));
		 int curPage=1;
		 if(request.getParameter("curPage")==null) {curPage=1;}
		 else {curPage=Integer.parseInt(request.getParameter("curPage")); }
		//댓글 리스트 
		 Map<String, Object> replyMap = movieDataService.replyPaging(movie_num,curPage);
		 replyMap.put("movie_num", movie_num);
		 List<Map<String, Object>> replyList = replyService.selectList(replyMap);
		
		logger.info(replyList.toString());
		
		return replyList;
	}
	
	//원본댓글 디테일 
	@ResponseBody
	@GetMapping("detail")
	public Mv_movie_reply detail(@ModelAttribute Mv_movie_reply reply,Model model) {
		int mr_num = reply.getMr_num();
		System.out.println("디테일----"+mr_num);
		Mv_movie_reply replyResult = replyService.selectOne(mr_num);
		model.addAttribute("replyResult",replyResult);
		return replyResult;
	}
		
//	//원본댓글 수정 get
//	@GetMapping("modify")
//	public String update(int mr_num, HttpSession session,Model model,RedirectAttributes rattr) {
//		System.out.println(mr_num);
//		Mv_movie_reply reply = replyService.selectOne(mr_num);
//		String userid = reply.getUser_id();
//		String session_userid = (String) session.getAttribute("user_id");
//		if(userid.equals(session_userid)) {
//			model.addAttribute("reply",reply);
//			return "movie/reply_modify";
//		}else {
//			rattr.addFlashAttribute("msg","회원권한이 없습니다");
//			return "redirect:detail?mr_num="+mr_num;
//		}
//	}
	
	//원본댓글 수정 post
	@ResponseBody
	@RequestMapping("modify")
	public void update(@ModelAttribute Mv_movie_reply reply,RedirectAttributes rattr) {
		rattr.addFlashAttribute("msg","수정을 완료하였습니다");
		replyService.modify(reply);
	}
	
	//원본댓글 삭제
	@GetMapping("delete")
	public String delete(int mr_num,int movie_num,RedirectAttributes rattr) {
		System.out.println(mr_num);
		replyService.delete(mr_num);
		rattr.addFlashAttribute("msg","삭제를 완료하였습니다");
		return "redirect:/moviedata/detail?movie_num="+movie_num;
	}
	
	
	
}
