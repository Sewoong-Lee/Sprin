package org.spring.my.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.spring.my.dto.Boardfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FileDAOimpl implements FileDAO{
	
	@Autowired
	private SqlSession SqlSession;
	
	@Override
	public void insertboardfile(Map<String, Object> fmap) {
		
		SqlSession.insert("org.spring.my.BoaedFileMapper.insertboardfile", fmap);
	}
	
	@Override
	public List<Boardfile> selectlist(int bnum) {
		
		return SqlSession.selectList("org.spring.my.BoaedFileMapper.selectlist", bnum);
	}

}
