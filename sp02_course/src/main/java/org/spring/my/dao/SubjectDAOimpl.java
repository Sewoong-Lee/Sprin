package org.spring.my.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.spring.my.dto.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SubjectDAOimpl implements SubjectDAO{
	@Autowired
	private SqlSession SqlSession;


	@Override
	public List<Subject> selectlist() {
		
		return SqlSession.selectList("org.spring.my.SubjectMapper.selectlist");
	}

}
