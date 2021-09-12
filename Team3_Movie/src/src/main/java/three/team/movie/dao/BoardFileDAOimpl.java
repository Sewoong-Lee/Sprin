package three.team.movie.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import three.team.movie.dto.Mv_board_file;

@Repository
public class BoardFileDAOimpl implements BoardFileDAO{
	
	@Autowired
	private SqlSession SqlSession;
	
	@Override
	public void insertboardfile(Map<String, Object> fmap) {
		
		SqlSession.insert("three.team.movie.BoardFileMapper.insertboardfile", fmap);
	}

	@Override
	public List<Mv_board_file> select_board_filelist(int board_num) {
		// TODO Auto-generated method stub
		return SqlSession.selectList("three.team.movie.BoardFileMapper.select_board_filelist", board_num);
	}

	@Override
	public void board_file_delete(int board_num) {
		SqlSession.delete("three.team.movie.BoardFileMapper.board_file_delete", board_num);
		
	}

	@Override
	public void board_filr_delete(int board_file_num) {
		SqlSession.delete("three.team.movie.BoardFileMapper.board_filr_delete", board_file_num);
		
	}

}
