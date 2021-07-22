package org.spring.my.service;

import java.util.List;
import java.util.Map;

import org.spring.my.dto.Course;

public interface CourseService {

	void insert(Course course);

	List<Map<String, Object>> selectone(String ccode);

}
