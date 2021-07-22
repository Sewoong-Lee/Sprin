package org.spring.my.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.spring.my.dao.ReplyDAO;
import org.spring.my.dto.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReplyServiceimpl implements ReplyService{
	
	@Autowired
	private ReplyDAO replydao;
	
	@Transactional
	@Override
	public void insert(Reply reply) {
		//뷰에서 받은 데이터는 부모의 정보 이므로 댓글용으로 가공
		//글 순서 +1
		reply.setRestep(reply.getRestep()+1);
		//현재 추가하고자 하는 글순서보다 크거나 같은 정보는 update
		replydao.updaterestep(reply);
		//글 레벨 +1
		reply.setRelevel(reply.getRelevel()+1);
		
		//추가
		replydao.insert(reply);
		
	}

	@Override
	public List<Map<String, Object>> selectlist(int bnum, String userid) {
		Map<String, Object> findmap = new HashMap<String, Object>();
		findmap.put("bnum", bnum);
		findmap.put("userid", userid);
		
		return replydao.selectlist(findmap);
	}

	@Override
	public void update(Reply reply) {
		replydao.update(reply);
		
	}

	@Override
	public void delete(int rnum) {
		replydao.delete(rnum);
	}

}
