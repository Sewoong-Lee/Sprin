package org.spring.my.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.spring.my.dto.Request;
import org.springframework.beans.factory.annotation.Autowired;

public interface RequestDAO {
	
	void insert(Request request);

	List<Map<String, Object>> selectlist(String ccode);

}
