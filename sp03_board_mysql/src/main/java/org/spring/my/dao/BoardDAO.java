package org.spring.my.dao;

import java.util.List;
import java.util.Map;

import org.spring.my.dto.Board;
import org.spring.my.dto.Page;

public interface BoardDAO {
	public void insert(Board board) throws Exception;
	
	public void delete(int bnum) throws Exception;
	
	public void update(Board board) throws Exception;
	
	public Map<String, Object> selectone(Map<String, Object> findmap) throws Exception;
	
	public List<Map<String,Object>> selectlist(Page page) throws Exception;

	public int selecttotcnt(Page page) throws Exception;

	public void readcountadd(int bnum) throws Exception;
	
	public void updatelikecnt(int bnum) throws Exception;

	public void updatelikecntcancel(int bnum) throws Exception;

	public void updatedislikecnt(int bnum) throws Exception;

	public void updatedislikecntcancel(int bnum) throws Exception;

	public void updateremoveyn(int bnum) throws Exception;
}
