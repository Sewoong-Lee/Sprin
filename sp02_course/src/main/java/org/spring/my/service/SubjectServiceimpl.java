package org.spring.my.service;

import java.util.List;

import org.spring.my.dao.SubjectDAO;
import org.spring.my.dto.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectServiceimpl implements SubjectService {
	
	@Autowired
	private SubjectDAO subjectdao;
	
	@Override
	public List<Subject> selectlist() {
		
		return subjectdao.selectlist();
	}

}
