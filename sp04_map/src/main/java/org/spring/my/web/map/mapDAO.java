package neo.com.kor.sdfac.testmap.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("mapDAO")
public class mapDAO extends EgovAbstractDAO {
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectSerch(Map<String, Object> mapmap) throws DataAccessException {
		System.out.println("dao 들어옴");
		return (ArrayList<Map<String, Object>>) list("testmap.selectlist", mapmap); 
	}

	public List<Map<String, Object>> checkedselectSerch(Map<String, Object> mapmap) {
		
		return (ArrayList<Map<String, Object>>) list("testmap.checkedselectSerch", mapmap); 
	}

	public void mapInsert(Map<String, Object> commandMap) {
		
		insert("testmap.mapInsert", commandMap);
	}

}
