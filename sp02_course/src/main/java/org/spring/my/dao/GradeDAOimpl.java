package org.spring.my.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.spring.my.dto.Grade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GradeDAOimpl implements GradeDAO{
	
	@Autowired
	private SqlSession SqlSession;
	
	@Override
	public void insert(Grade grade) {
		SqlSession.insert("org.spring.my.GradeMapper.insert", grade);
	}

	@Override
	public List<Map<String, Object>> selectlist_subject(String sno) {
		
		return SqlSession.selectList("org.spring.my.GradeMapper.selectlist_subject", sno);
	}

	@Override
	public List<Map<String, Object>> selectlist(HashMap<String, String> fmap) {
		// TODO Auto-generated method stub
		return SqlSession.selectList("org.spring.my.GradeMapper.selectlist", fmap);
	}

}
