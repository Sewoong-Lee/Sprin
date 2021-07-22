package org.spring.my.dto;

public class MemberDTO {
	private String userid;
	private String passwd;
	private String name;
	private String newpasswd;
	public MemberDTO() {
		super();
	}

	public MemberDTO(String userid, String passwd, String name, String newpasswd) {
		super();
		this.userid = userid;
		this.passwd = passwd;
		this.name = name;
		this.newpasswd = newpasswd;
	}

	public MemberDTO(String userid, String passwd, String name) {
		super();
		this.userid = userid;
		this.passwd = passwd;
		this.name = name;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNewpasswd() {
		return newpasswd;
	}

	public void setNewpasswd(String newpasswd) {
		this.newpasswd = newpasswd;
	}

	@Override
	public String toString() {
		return "MemberDTO [userid=" + userid + ", passwd=" + passwd + ", name=" + name + ", newpasswd=" + newpasswd
				+ "]";
	}

	
	
	
	
	
	
}
