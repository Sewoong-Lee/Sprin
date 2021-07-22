package org.spring.my.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.spring.my.dto.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

//스프링의 관리하에 둔다.
@Repository
public class StudentDAOimpl implements StudentDAO{
	//섹션 객체 생성
	@Autowired
	private SqlSession SqlSession;
	
	@Override
	public void insert(Student student) {
		SqlSession.insert("org.spring.my.StudentMapper.insert", student);
		
	}

	@Override
	public void update(Student student) {
		SqlSession.update("org.spring.my.StudentMapper.update", student);
		
	}

	@Override
	public void delete(String sno) {
		SqlSession.delete("org.spring.my.StudentMapper.delete", sno);
		
	}

	@Override
	public Student selectone(String sno) {
		return SqlSession.selectOne("org.spring.my.StudentMapper.selectone", sno);
		
	}

	@Override
	public List<Student> selectlist(Map<String, String> findmap) {
		return SqlSession.selectList("org.spring.my.StudentMapper.selectlist", findmap);
	}
	
}
