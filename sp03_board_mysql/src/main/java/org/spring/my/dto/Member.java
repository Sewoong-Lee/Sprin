package org.spring.my.dto;

import java.util.Date;

import lombok.Data;

@Data
public class Member {
	private String userid;
	private String passwd;
	private String email;
	private String zip;
	private String addr1;
	private String addr2;
	private String filename;
	private String memo;
	private String admin;
	private String emailauth;
	private String simplejoin;
	private Date regdate;
	
}
