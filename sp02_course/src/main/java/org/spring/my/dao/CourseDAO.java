package org.spring.my.dao;

import java.util.List;
import java.util.Map;

import org.spring.my.dto.Course;

public interface CourseDAO {

	void insert(Course course);
	List<Map<String,Object>> selectone(String ccode);
}
