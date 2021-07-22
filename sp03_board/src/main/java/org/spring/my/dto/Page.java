package org.spring.my.dto;

import java.util.Date;

import lombok.Data;

@Data
public class Page {
	//검색값
	private String findkey;
	private String findvalue;
	
	//페이징처리(하드코딩)
	private int curpage = 1; //현재 페이지
	private int perpage = 10; //한페이지당 게시물수
	private int perblock = 10; //한 화면 페이지의 블럭의 수
	
	//페이징처리(계산 예정)
	private int totpage; //전체 페이지수
	private int startnum; //시작번호
	private int endnum; //끝번호
	private int startpage; //블럭의 시작 페이지 
	private int endpage; // 블럭의 끝 페이지
	
	
}
