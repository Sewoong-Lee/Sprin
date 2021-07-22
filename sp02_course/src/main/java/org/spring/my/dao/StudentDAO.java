package org.spring.my.dao;

import java.util.List;
import java.util.Map;

import org.spring.my.dto.Student;

public interface StudentDAO {
	public void insert(Student student);
	public void update(Student student);
	public void delete(String sno);
	public Student selectone(String sno);
	public List<Student> selectlist(Map<String, String> findmap);
}
