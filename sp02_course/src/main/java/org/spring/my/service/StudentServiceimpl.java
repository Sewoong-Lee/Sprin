package org.spring.my.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.my.dao.StudentDAO;
import org.spring.my.dto.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceimpl implements StudentService {
	
	private static final Logger logger = LoggerFactory.getLogger(StudentServiceimpl.class);
	
	@Autowired
	private StudentDAO studentdao;
	
	@Override
	public void insert(Student student) {
		studentdao.insert(student);
		
	}

	@Override
	public List<Student> selectlist(HashMap<String,String> findmap) {
		//Map<String, String> findmap = new HashMap<String, String>();
		//findmap.put("findkey", findkey);
		//findmap.put("findvalue", findvalue);
		
		return studentdao.selectlist(findmap);
	}

	@Override
	public Student selectlist(String sno) {
		
		return studentdao.selectone(sno);
	}

	@Override
	public void update(Student student) {
		
		studentdao.update(student);
	}

	@Override
	public void delete(String sno) {
		studentdao.delete(sno);
		
	}

	@Override
	public Student selectone(String sno) {
		// TODO Auto-generated method stub
		return studentdao.selectone(sno);
	}

}
