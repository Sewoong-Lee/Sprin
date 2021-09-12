package org.spring.my.dao;

import java.util.List;
import java.util.Map;

import org.spring.my.dto.Boardfile;

public interface FileDAO {
	public void insertboardfile(Map<String, Object> fmap);

	public List<Boardfile> selectlist(int bnum);
	
	public void delete(int fnum);
}
