package org.spring.my.service;

import java.util.HashMap;
import java.util.List;

import org.spring.my.dto.Student;
import org.springframework.stereotype.Service;


public interface StudentService {

	void insert(Student student);

	List<Student> selectlist(HashMap<String,String> findmap);

	Student selectlist(String sno);

	void update(Student student);

	void delete(String sno);

	Student selectone(String sno);

	
}
