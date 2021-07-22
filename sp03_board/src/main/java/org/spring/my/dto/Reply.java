package org.spring.my.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class Reply {
	private int rnum;
	private int bnum;
	private String userid;
	private String content;
	private int likecnt;
	private int dislikecnt;
	private String ip;
	private int restep;
	private int relevel;
	//제이슨으로 값이 올때 데이터 형식을 바꿔줌
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private Date regdate;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private Date modifydate;
	
	
}
