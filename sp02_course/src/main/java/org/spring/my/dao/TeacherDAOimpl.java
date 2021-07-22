package org.spring.my.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.spring.my.dto.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TeacherDAOimpl implements TeacherDAO{
	@Autowired
	private SqlSession SqlSession;

	@Override
	public void insert(Teacher teacher) {
		SqlSession.insert("org.spring.my.TeacherMapper.insert", teacher);
		
	}

	@Override
	public List<Teacher> selectlist() {
		
		return SqlSession.selectList("org.spring.my.TeacherMapper.selectlist");
	}
	
}
