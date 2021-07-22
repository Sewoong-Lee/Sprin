package org.spring.my.dao;

import java.util.HashMap;
import java.util.List;

import org.spring.my.dto.Teacher;

public interface TeacherDAO {

	void insert(Teacher teacher);

	List<Teacher> selectlist();

}
