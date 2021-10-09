package org.spring.my.map;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class mapDAO {
	@Autowired
	SqlSession SqlSession;

public List<Map<String, Object>> selectlist(Map<String, Object> map) {
		
		return SqlSession.selectList("org.spring.my.MapMapper.selectlist", map);
	}

	public List<Map<String, Object>> checkedselectlist(Map<String, Object> map) {
		System.out.println("진입");
		return SqlSession.selectList("org.spring.my.MapMapper.checkedselectlist", map);
	}

	public List<Map<String, Object>> GoInsertList() {
		// TODO Auto-generated method stub
		return SqlSession.selectList("org.spring.my.MapMapper.GoInsertList");
	}
	

}
