package three.team.movie.service;

import java.util.List;
import java.util.Map;

import three.team.movie.dto.Mv_Page;
import three.team.movie.dto.Mv_movie_data;

public interface MovieDataService {

	public Map<String, Object> selectOne(int movie_num); //영화 한건조회 detail

	public Map<String, Object> selectList(Mv_Page page); //영화 리스트 

	public List<Map<String, Object>> selectListUser(Map<String, Object> findmap);//회원관심 리스트

	public Map<String, Object> replyPaging(int movie_num, int curPage); //게시물 페이징


}
