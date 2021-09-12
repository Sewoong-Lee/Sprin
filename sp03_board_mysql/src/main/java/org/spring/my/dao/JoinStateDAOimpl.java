package org.spring.my.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class JoinStateDAOimpl implements JoinStateDAO{
	
	@Autowired
	private SqlSession SqlSession;
	
	@Override
	public List<Map<String, Object>> joinstate() {
		
		return SqlSession.selectList("org.spring.my.JoinStateMapper.selectlist");
	}
	
}
