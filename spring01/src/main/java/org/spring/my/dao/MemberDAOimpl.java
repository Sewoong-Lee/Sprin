package org.spring.my.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.spring.my.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAOimpl implements MemberDAO{
	@Autowired
	SqlSession SqlSession;

	@Override
	public void insert(MemberDTO memberdto) {
		
		SqlSession.insert("org.spring.my.MemberMapper.insert", memberdto);
	}

	@Override
	public List<MemberDTO> selectlist() {
		
		return SqlSession.selectList("org.spring.my.MemberMapper.selectlist");
	}

	@Override
	public MemberDTO selectone(String userid) {
		
		return SqlSession.selectOne("org.spring.my.MemberMapper.selectone", userid);
	}

	@Override
	public int delet(String userid) {
		
		return SqlSession.delete("org.spring.my.MemberMapper.delet", userid);
	}

	@Override
	public int update(MemberDTO mdto) {
		
		return SqlSession.update("org.spring.my.MemberMapper.update", mdto);
	}
	
	
	
	
}
