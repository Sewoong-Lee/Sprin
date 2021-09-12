package three.team.movie.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import three.team.movie.dto.Mv_movie_reply;

@Repository
public class ReplyDAOImpl implements ReplyDAO{

	@Autowired
	private SqlSession sqlSession;
	
	//댓글 추가 
	@Override
	public void insert(Mv_movie_reply reply) {
		sqlSession.insert("org.spring.my.ReplyMapper.insert",reply);
	}
	//댓글 전체리스트
	@Override
	public List<Map<String, Object>> selectList(Map<String, Object> replyMap) {
		System.out.println("DDDDD=========="+replyMap+"dao-----------------");
		return sqlSession.selectList("org.spring.my.ReplyMapper.selectList",replyMap);
	}

	//댓글 디테일
	@Override
	public Mv_movie_reply selectOne(int mr_num) {
		
		return sqlSession.selectOne("org.spring.my.ReplyMapper.selectOne",mr_num);
	}
	
	//댓글 삭제 
	@Override
	public void delete(int mr_num) {
		sqlSession.delete("org.spring.my.ReplyMapper.delete",mr_num);
	}
	//원본글 수정
	@Override
	public void modify(Mv_movie_reply reply) {
		sqlSession.update("org.spring.my.ReplyMapper.update",reply);
		
	}
	//영화 게시물 수 구하기 
	@Override
	public int totReplCnt(int movie_num){
		return sqlSession.selectOne("org.spring.my.ReplyMapper.totReplCnt",movie_num);
	}
	


	
}
