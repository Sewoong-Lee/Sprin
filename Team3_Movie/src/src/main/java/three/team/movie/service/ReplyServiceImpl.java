package three.team.movie.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import three.team.movie.dao.ReplyDAO;
import three.team.movie.dto.Mv_movie_reply;

@Service
public class ReplyServiceImpl implements ReplyService{

	@Autowired
	private ReplyDAO replyDAO;
	
	//댓글 추가 
	@Override
	public void insert(Mv_movie_reply reply) {
		replyDAO.insert(reply);
	}

	//댓글리스트
	@Override
	public List<Map<String, Object>> selectList(Map<String, Object> replyMap) {
		return replyDAO.selectList(replyMap);
	}

	//댓글 디테일
	@Override
	public Mv_movie_reply selectOne(int mr_num) {
		return replyDAO.selectOne(mr_num);
	}

	//원본글 삭제 
	@Override
	public void delete(int mr_num) {
		replyDAO.delete(mr_num);
	}

	//원본글 수정
	@Override
	public void modify(Mv_movie_reply reply) {
		replyDAO.modify(reply);
	}



}
