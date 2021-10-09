package org.spring.my.map;

import java.util.List;
import java.util.Map;

public interface mapService {

	public List<Map<String, Object>> selectlist(Map<String, Object> map);

	public List<Map<String, Object>> checkedselectlist(Map<String, Object> map);

	public List<Map<String, Object>> GoInsertList();
	

}
