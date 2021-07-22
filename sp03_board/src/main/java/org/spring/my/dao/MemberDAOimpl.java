package org.spring.my.dao;

import org.apache.ibatis.session.SqlSession;
import org.spring.my.dto.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAOimpl implements MemberDAO{
	@Autowired
	private SqlSession SqlSession;

	@Override
	public void insert(Member member) {
		SqlSession.insert("org.spring.my.MemberMapper.insert", member);
		
	}

	@Override
	public Member selectone(String userid) {
		// TODO Auto-generated method stub
		return SqlSession.selectOne("org.spring.my.MemberMapper.selectone", userid);
	}
	
	
}
