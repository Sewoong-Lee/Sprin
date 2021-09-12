package org.spring.my.service;

import java.util.List;
import java.util.Map;

import org.spring.my.dto.Board;
import org.spring.my.dto.Page;

public interface BoardService {

	public void insert(Board board) throws Exception;

	public List<Map<String,Object>> selectlist(Page page) throws Exception;

	public Map<String, Object> selectone(int bnum, String userid) throws Exception;

	public void readcountadd(int bnum, String userid) throws Exception;
	
	public void updatelikecnt(int bnum, String userid) throws Exception;
	
	public void updatelikecntcancel(int bnum, String userid) throws Exception;

	public void updatedislikecnt(int bnum, String userid) throws Exception;

	void updatedislikecntcancel(int bnum, String userid) throws Exception;

	public void updateremoveyn(int bnum) throws Exception;

	public void update(Board board, List<Integer> filedeletelist) throws Exception;

}
