package three.team.movie.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class Mv_genreDAOImpl implements Mv_genreDAO {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<Map<String, String>> getGenreList() {
		return sqlSession.selectList("three.team.movie.Mv_genreMapper.getGenreList");
	}

}
