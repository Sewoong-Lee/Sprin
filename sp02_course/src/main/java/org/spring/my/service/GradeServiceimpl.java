package org.spring.my.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.spring.my.dao.GradeDAO;
import org.spring.my.dto.Grade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GradeServiceimpl implements GradeService{
	
	@Autowired
	private GradeDAO gradedao;
	
	@Override
	public List<Map<String, Object>> selectlist_subject(String sno) {
		
		return gradedao.selectlist_subject(sno);
	}

	@Override
	public void insert(Grade grade) {
		gradedao.insert(grade);
		
	}

	@Override
	public List<Map<String, Object>> selectlist(HashMap<String, String> fmap) {
		
		return gradedao.selectlist(fmap);
	}
	
}
