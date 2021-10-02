package org.spring.my.map;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class mapServiceimpl implements mapService {
	@Autowired
	private mapDAO DAO;
	
	@Override
	public List<Map<String, Object>> selectlist(Map<String, Object> map) {
		
		return DAO.selectlist(map);
	}

	
}
