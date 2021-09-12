package three.team.movie.dao;

import java.util.List;
import java.util.Map;

import three.team.movie.dto.Mv_movie_reply;

public interface ReplyDAO {

	public void insert(Mv_movie_reply reply); //댓글 추가
	
	public List<Map<String, Object>> selectList(Map<String, Object> replyMap); //댓글리스트 
	
	public Mv_movie_reply selectOne(int mr_num); //댓글디테일

	public void delete(int mr_num);	//원본글 삭제 

	public void modify(Mv_movie_reply reply); //원본글 수정

	public int totReplCnt(int movie_num); //영화 게시물 수 구하기 
	
	
}
