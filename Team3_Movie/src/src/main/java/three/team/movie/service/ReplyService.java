package three.team.movie.service;

import java.util.List;
import java.util.Map;

import three.team.movie.dto.Mv_movie_reply;

public interface ReplyService {

	public void insert(Mv_movie_reply reply); //댓글 추가

	public List<Map<String, Object>> selectList(Map<String, Object> replyMap); //원본글 리스트 

	public Mv_movie_reply selectOne(int mr_num); //원본글 디테일 

	public void delete(int mr_num); //원본글 삭제 

	public void modify(Mv_movie_reply reply); //원본글 수정


}
