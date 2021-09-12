package three.team.movie.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import three.team.movie.dto.MV_Board_Page;
import three.team.movie.dto.Mv_board;
import three.team.movie.dto.Mv_state;

@Repository
public class BoardDAOimpl implements BoardDAO{
	@Autowired
	private SqlSession SqlSession;
	
	@Override
	public List<Mv_board> eventlist() {
		
		return SqlSession.selectList("three.team.movie.BoardMapper.eventlist");
	}

	@Override
	public Mv_board selectone_board(int board_num) {
		
		return SqlSession.selectOne("three.team.movie.BoardMapper.selectone_board", board_num);
	}

	@Override
	public void insert(Mv_board mv_board) {
		
		SqlSession.insert("three.team.movie.BoardMapper.insert_board", mv_board);
	}

	@Override
	public void board_delete(int board_num) {
		SqlSession.delete("three.team.movie.BoardMapper.board_delete", board_num);
		
	}

	@Override
	public int selecttotcnt(MV_Board_Page mv_board_page) {
		
		return SqlSession.selectOne("three.team.movie.BoardMapper.selecttotcnt", mv_board_page);
	}

	@Override
	public List<Map<String, Object>> selectlist(MV_Board_Page mv_board_page) {
		
		return SqlSession.selectList("three.team.movie.BoardMapper.selectlist", mv_board_page);
	}

	@Override
	public Object state_selectone(Mv_state mv_state) {
		
		return SqlSession.selectOne("three.team.movie.BoardMapper.state_selectone", mv_state);
	}

	@Override
	public void state_insert(Mv_state mv_state) {
		SqlSession.insert("three.team.movie.BoardMapper.state_insert", mv_state);
		
	}

	@Override
	public void readcountadd(int board_num) {
		SqlSession.update("three.team.movie.BoardMapper.readcountadd", board_num);
		
	}

	@Override
	public void board_update(Mv_board mv_board) {
		SqlSession.update("three.team.movie.BoardMapper.board_update", mv_board);
		
	}

}
