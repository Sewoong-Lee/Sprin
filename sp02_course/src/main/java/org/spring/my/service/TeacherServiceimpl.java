package org.spring.my.service;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.my.dao.TeacherDAO;
import org.spring.my.dto.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceimpl implements TeacherService {
	private static final Logger logger = LoggerFactory.getLogger(TeacherServiceimpl.class);
	
	@Autowired
	private TeacherDAO teacherdao;
	
	@Override
	public void insert(Teacher teacher) {
		
		teacherdao.insert(teacher);
	}

	@Override
	public List<Teacher> selectlist() {
		
		return teacherdao.selectlist();
	}
	
	
	
	
	
	
	
	
}
