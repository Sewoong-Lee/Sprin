package three.team.movie.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import three.team.movie.dao.BoardDAO;
import three.team.movie.dto.MV_Board_Page;
import three.team.movie.dto.Mv_board;
import three.team.movie.dto.Mv_board_file;
import three.team.movie.dto.Mv_state;

@Service
public class BoardServiceimpl implements BoardService{
	
	@Autowired
	private BoardFileService boardFileService;
	
	@Autowired
	private BoardDAO boardDAO;
	
	@Override
	public List<Map<String,Object>> boardlist(MV_Board_Page mv_board_page) {
		//**페이지 값 구하기
		//전체 게시물 수 구하기
		int totcnt = boardDAO.selecttotcnt(mv_board_page);
		
		//전체 페이지 수 구하기
		int totpage = totcnt / mv_board_page.getPerpage();
		if(totcnt % mv_board_page.getPerpage() != 0) { //나머지가 있으면 1페이지 추가
			totpage += 1;
		}
		mv_board_page.setTotpage(totpage);
		
		//현재 페이지
		int  curpage = mv_board_page.getCurpage();
		//시작번호
		int startnum = (curpage-1) * mv_board_page.getPerpage() + 1;
		mv_board_page.setStartnum(startnum);
		//끝번호
		int endnum = startnum + mv_board_page.getPerpage() -1;
		mv_board_page.setEndnum(endnum);
		
		//시작페이지
		int startpage = curpage - ((curpage-1) % mv_board_page.getPerblock());
		mv_board_page.setStartpage(startpage);
		//끝페이지
		int endpage = startpage + mv_board_page.getPerblock() -1;
		if(endpage > totpage) endpage = totpage;
		mv_board_page.setEndpage(endpage);
		
		return boardDAO.selectlist(mv_board_page);
		
	}

	@Override
	public Map<String, Object> selectone(int board_num) {
		//이벤트 내용 가져오기
		Mv_board mv_board = boardDAO.selectone_board(board_num);
		
		//이벤트 파일 가져오기
		List<Mv_board_file> bflist = boardFileService.select_board_filelist(board_num);
		
		Map<String,Object> bdetail_map = new HashMap<String, Object>();
		bdetail_map.put("mv_board", mv_board);
		bdetail_map.put("bflist", bflist);
		
		return bdetail_map;
	}
	
	@Transactional
	@Override
	public void insert(Mv_board mv_board) {
		//게시물 저장
		boardDAO.insert(mv_board);//인서트를 하고나면 저장 후 board_num이 셋팅완료
		System.out.println("서비스 mv_board : "+mv_board);
		
		//board디티오의 파일 이름 저장
		//List<MultipartFile> files= board.getFiles();
		
		//파일을 업로드 하고 db에 저장(변환 안하고 보드 파일스 그냥 넘김)
		boardFileService.insertdoardfilelist(mv_board.getFiles(), mv_board.getBoard_num());
		
	}
	
	@Transactional
	@Override
	public void board_delete(int board_num) {
		boardFileService.board_file_delete(board_num);
		
		boardDAO.board_delete(board_num);
		
	}
	
	@Transactional
	@Override
	public void readcountadd(int board_num, String user_id) {
		//중복조회수 방지 체크
		Mv_state mv_state = new Mv_state();
		mv_state.setGubun("1");
		mv_state.setNum(board_num);
		mv_state.setStategubun("0");
		mv_state.setUser_id(user_id);
		
		if(boardDAO.state_selectone(mv_state) == null) {
			//기존에 조회 정보가 없다면 매니져테이블에 인서트 및 조회 +1 
			boardDAO.state_insert(mv_state);
			boardDAO.readcountadd(board_num);
		}
		
		
	}

	//게시물 수정
	@Override
	public void board_update(Mv_board mv_board, List<Integer> filedeletelist) {
		boardDAO.board_update(mv_board);
		
		//기존 파일 삭제
		//널체크
		if(filedeletelist != null) {
			//삭제
			for(int board_file_num : filedeletelist) {
				boardFileService.board_filr_delete(board_file_num);
			}
		}
		//파일 추가
		boardFileService.insertdoardfilelist(mv_board.getFiles(), mv_board.getBoard_num());
		
		
	}

	
	
	
}
