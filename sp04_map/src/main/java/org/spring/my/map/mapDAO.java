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
	

}
