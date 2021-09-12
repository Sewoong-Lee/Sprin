package three.team.movie.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import three.team.movie.dao.MovieDataDAO;
import three.team.movie.dao.ReplyDAO;
import three.team.movie.dto.Mv_Page;
import three.team.movie.dto.Mv_movie_data;

@Service
public class MovieDataServiceImpl implements MovieDataService{

	@Autowired
	private MovieDataDAO movieDataDAO;
	
	@Autowired
	private ReplyDAO replyDAO;

	//영화 디테일 
	@Override
	public Map<String, Object> selectOne(int movie_num) {
		return movieDataDAO.selectOne(movie_num);//영화 한건조회 detail
	}

	//영화 전체 리스트 
	@Override
	public Map<String, Object> selectList(Mv_Page page) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		//영화 전체 갯수 구하기
		int totCnt = movieDataDAO.selectTotCnt(page);
		System.out.println("전체리스트 수====="+totCnt);
		//페이징 처리
		Mv_Page paging = paging(page,totCnt);
		
		System.out.println("전체페이지===="+paging);
		returnMap.put("list", movieDataDAO.selectList(paging));
		returnMap.put("paging", paging);
		return returnMap;
	}
	
	
	
	
	//회원관심 영화리스트 
	@Override
	public List<Map<String, Object>> selectListUser(Map<String, Object> findmap) {
		String user_id = (String) findmap.get("user_id");
		//페이징처리
		Mv_Page page = (Mv_Page)findmap.get("mvUser_Page");
		
		//관심영화리스트 갯수조회
		int totCnt = movieDataDAO.selectTotCntUser(user_id);
		System.out.println("회원 리스트 수====="+totCnt);
		page.setCurPage(page.getCurPageListUser());
		
		//페이징 처리 
		page = paging(page,totCnt);
		System.out.println("컬페이지번호:"+page.getCurPage());
		page.setCurPageListUser(page.getCurPage());
		page.setCurPage(0);
		
		findmap.put("mvUser_Page", page);
		System.out.println("회원 페이지 ====="+findmap.toString());
		return movieDataDAO.selectListUser(findmap);
	}

	//페이징 처리 
	public Mv_Page paging(Mv_Page page,int totCnt) {
//		//영화 전체 구하기
//		int totCnt = movieDataDAO.selectTotCnt(page);
		
		//전체페이지  
		int totPage = totCnt/page.getPerPage();
		if(totCnt % page.getPerPage()!=0)totPage += 1;
		page.setTotPage(totPage);
		//현재 페이지
		int curPage = page.getCurPage();
		//시작번호
		int statNum = (curPage-1)*page.getPerPage()+1;
		page.setStartNum(statNum);
		//끝번호
		int endNum = statNum+page.getPerPage()-1;
		page.setEndNum(endNum);
		
		//시작페이지
		int startPage = curPage -((curPage-1)%page.getPerBlock());
		page.setStartPage(startPage);
		//끝페이지 
		int endPage = startPage+page.getPerBlock()-1;
		if (endPage>totPage)endPage=totPage;
		page.setEndPage(endPage);
		
		return page;
	}

	@Override
	public Map<String, Object> replyPaging(int movie_num, int curPageReply) {
		//게시물 총 수 구하기 
		int totreplycnt = replyDAO.totReplCnt(movie_num);
		System.out.println("전체게시물"+totreplycnt);
		Mv_Page page = new Mv_Page();
		page.setPerPage(10);//10개 게시물로 
		page.setCurPageReply(curPageReply);
		Mv_Page replypage =paging(page,totreplycnt);
		
		System.out.println("게시물 페이징==="+replypage);
		Map<String, Object> replyMap =new HashMap<String, Object>();
		replyMap.put("replypage", replypage);
		System.out.println("replyMap============="+replyMap);
		return replyMap;
	}
	
	
	
	
	
}

