package org.spring.my;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.spring.my.dao.StudentDAO;
import org.spring.my.dto.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//테스트 라이브러리를 사용하기 위한 사전작업
@RunWith(SpringJUnit4ClassRunner.class)
//세션을 만들기위한 파일의 경로와, 오토와이드를 하기위한 서블렛 컷텍스트 파일 경로를 지정
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/**/servlet-context.xml"})
public class stest {
	
	@Autowired
	private StudentDAO studentDAO;
	@Test
	public void testInsert() {
		Student student = new Student();
		student.setSno("1");
		student.setSname("1");
		student.setTel("1");
		student.setZip("1");
		student.setAddr1("1");
		student.setAddr2("1");
		System.out.println(student);
		
		studentDAO.insert(student);
	}
	
	@Test
	public void testUpdate() {
		Student student = new Student();
		student.setSno("1");
		student.setSname("3");
		student.setTel("3");
		student.setZip("3");
		student.setAddr1("3");
		student.setAddr2("3");
		System.out.println(student);
		
		studentDAO.update(student);
	}
	
	@Test
	public void testdelete() {
		String sno = "1";
		
		studentDAO.delete(sno);
	}
	
	@Test
	public void testselectone() {
		String sno = "1";
		
		Student student = studentDAO.selectone(sno);
		System.out.println("셀렉트원 : "+student);
	}
	
	@Test
	public void testselectlist() {
		Map<String, String> smap = new HashMap<String, String>();
		smap.put("findkey", "sname");
		smap.put("findvalue", "1");
		
		List<Student> slist = studentDAO.selectlist(smap);
		System.out.println("셀렉트리스트 : "+ slist);
	}
	
	

}
