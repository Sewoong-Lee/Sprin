package org.spring.my.dao;

import java.util.List;
import java.util.Map;

import org.spring.my.dto.Board;
import org.spring.my.dto.Page;

public interface BoardDAO {
	public void insert(Board board);
	
	public void delete(int bnum);
	
	public void update(Board board);
	
	public Map<String, Object> selectone(Map<String, Object> findmap);
	
	public List<Board> selectlist(Page page);

	public int selecttotcnt(Page page);

	public void readcountadd(int bnum);
	
	public void updatelikecnt(int bnum);

	public void updatelikecntcancel(int bnum);

	public void updatedislikecnt(int bnum);

	public void updatedislikecntcancel(int bnum);

	public void updateremoveyn(int bnum);
}
