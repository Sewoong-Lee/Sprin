package org.spring.my.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.spring.my.dto.Grade;

public interface GradeService {

	public List<Map<String, Object>> selectlist_subject(String sno);

	public void insert(Grade grade);

	public List<Map<String, Object>> selectlist(HashMap<String, String> fmap);
	
}
