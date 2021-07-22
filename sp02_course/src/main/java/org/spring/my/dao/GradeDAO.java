package org.spring.my.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.spring.my.dto.Grade;

public interface GradeDAO {
	public void insert(Grade grade);
	
	List<Map<String,Object>> selectlist_subject(String sno);

	public List<Map<String, Object>> selectlist(HashMap<String, String> fmap);
}
