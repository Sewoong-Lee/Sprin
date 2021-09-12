package three.team.movie.dao;

import java.util.List;
import java.util.Map;

import three.team.movie.dto.Mv_Page;
import three.team.movie.dto.Mv_movie_data;

public interface MovieDataDAO {

	public Map<String, Object> selectOne(int movie_num); //영화 한건조회 detail
	
	public List<Mv_movie_data> selectList(Mv_Page page); //영화 전체 리스트 
	
	public List<Map<String, Object>> selectListUser(Map<String, Object> findmap);//회원관심 리스트

	public int selectTotCnt(Mv_Page page); //영화전체 게시물 조회수
	
	public int selectTotCntUser(String user_id); //회원관심 영화 리스트 조회

	public void insert(Mv_movie_data mv_movie_data);

	public Double starRating(int movie_num);
	
}
