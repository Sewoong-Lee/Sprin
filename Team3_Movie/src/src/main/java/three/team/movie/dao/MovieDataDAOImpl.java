package three.team.movie.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import three.team.movie.dto.Mv_Page;
import three.team.movie.dto.Mv_movie_data;


@Repository
public class MovieDataDAOImpl implements MovieDataDAO{

	@Autowired
	private SqlSession sqlsession;

	
	//영화 한건조회 detail
	@Override
	public  Map<String, Object> selectOne(int movie_num) {
		System.out.println("dao:"+movie_num);
		return sqlsession.selectOne("org.spring.my.MovieMapper.selectOne",movie_num);
	}

	//영화 전체 리스트 
	@Override
	public List<Mv_movie_data> selectList(Mv_Page page) {
		return sqlsession.selectList("org.spring.my.MovieMapper.selectList",page);
	}

	//회원 관심 리스트
	@Override
	public List<Map<String, Object>> selectListUser(Map<String, Object> findmap) {
		return sqlsession.selectList("org.spring.my.MovieMapper.selectListUser",findmap);
	}

	//영화 전체 수 구하기 
	@Override
	public int selectTotCnt(Mv_Page page) {
		return sqlsession.selectOne("org.spring.my.MovieMapper.selectTotCnt",page);
	}
	//회원 관심영화 리스트 수 구하기
	@Override
	public int selectTotCntUser(String user_id) {
	
		return sqlsession.selectOne("org.spring.my.MovieMapper.selectTotCntUser",user_id);
	}

	//영화 추가 
	@Override
	public void insert(Mv_movie_data mv_movie_data) {
		sqlsession.insert("org.spring.my.MovieMapper.insert", mv_movie_data);
	}

	//별평점 계산 
	@Override
	public Double starRating(int movie_num) {
	
		return sqlsession.selectOne("org.spring.my.MovieMapper.starRating",movie_num);
	}
	
	
	
	


	
}
