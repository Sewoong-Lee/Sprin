package org.spring.my.service;

import java.util.List;
import java.util.Map;

import org.spring.my.dao.CourseDAO;
import org.spring.my.dto.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceimpl implements CourseService {
	@Autowired
	private CourseDAO coursedao;
	
	@Override
	public void insert(Course course) {
		
		coursedao.insert(course);
	}

	@Override
	public List<Map<String, Object>> selectone(String ccode) {
		
		return coursedao.selectone(ccode);
	}

}
