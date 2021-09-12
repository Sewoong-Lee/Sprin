package org.spring.my.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.spring.my.dto.Board;
import org.spring.my.dto.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAOimpl implements BoardDAO{
	
	@Autowired
	private SqlSession SqlSession;
	
	@Override
	public void insert(Board board) throws Exception{
		SqlSession.insert("org.spring.my.BoardMapper.insert", board);
		
	}

	@Override
	public void delete(int bnum) throws Exception{
		SqlSession.delete("org.spring.my.BoardMapper.delete", bnum);
		
	}

	@Override
	public void update(Board board) throws Exception{
		SqlSession.update("org.spring.my.BoardMapper.update", board);
		
	}

	@Override
	public Map<String, Object> selectone(Map<String, Object> findmap) throws Exception{
		
		return SqlSession.selectOne("org.spring.my.BoardMapper.selectone", findmap);
	}

	@Override
	public List<Map<String,Object>> selectlist(Page page) throws Exception{
		
		return SqlSession.selectList("org.spring.my.BoardMapper.selectlist", page);
	}
	
	//전체 게시물 수 구하기
	@Override
	public int selecttotcnt(Page page) throws Exception{
		
		return SqlSession.selectOne("org.spring.my.BoardMapper.selecttotcnt", page);
	}

	@Override
	public void readcountadd(int bnum) throws Exception{
		SqlSession.selectOne("org.spring.my.BoardMapper.readcountadd", bnum);
		
	}

	@Override
	public void updatelikecnt(int bnum) throws Exception{
		SqlSession.selectOne("org.spring.my.BoardMapper.updatelikecnt", bnum);
		
	}

	@Override
	public void updatelikecntcancel(int bnum) throws Exception{
		SqlSession.selectOne("org.spring.my.BoardMapper.updatelikecntcancel", bnum);
		
	}

	@Override
	public void updatedislikecnt(int bnum) throws Exception{
		SqlSession.update("org.spring.my.BoardMapper.updatedislikecnt", bnum);
		
	}

	@Override
	public void updatedislikecntcancel(int bnum) throws Exception{
		SqlSession.update("org.spring.my.BoardMapper.updatedislikecntcancel", bnum);
		
	}

	@Override
	public void updateremoveyn(int bnum) throws Exception{
		SqlSession.update("org.spring.my.BoardMapper.updateremoveyn", bnum);
		
	}

}
