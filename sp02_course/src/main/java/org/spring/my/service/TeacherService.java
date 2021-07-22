package org.spring.my.service;

import java.util.HashMap;
import java.util.List;

import org.spring.my.dto.Teacher;

public interface TeacherService {

	void insert(Teacher teacher);
	List<Teacher> selectlist();
	
}
