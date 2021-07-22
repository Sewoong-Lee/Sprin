package org.spring.my.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.spring.my.dto.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CourseDAOimpl implements CourseDAO{
	@Autowired
	private SqlSession SqlSession;

	@Override
	public void insert(Course course) {
		SqlSession.insert("org.spring.my.CourseMapper.insert", course);
		SqlSession.insert("org.spring.my.CourseMapper.insert_subject", course);
	}
	//과정 한건 조회
	@Override
	public List<Map<String,Object>> selectone(String ccode) {
		//과목이 여러건이므로 리스트로 반환
		return SqlSession.selectList("org.spring.my.CourseMapper.selectone", ccode);
	}
	
}
