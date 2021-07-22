package org.spring.my.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.spring.my.dto.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RequestDAOimpl implements RequestDAO{
	
	@Autowired
	private SqlSession SqlSession;
	
	@Override
	public void insert(Request request) {
		SqlSession.insert("org.spring.my.RequestMapper.insert", request);
		
	}

	@Override
	public List<Map<String, Object>> selectlist(String ccode) {
		
		return SqlSession.selectList("org.spring.my.RequestMapper.selectlist", ccode);
	}

}
