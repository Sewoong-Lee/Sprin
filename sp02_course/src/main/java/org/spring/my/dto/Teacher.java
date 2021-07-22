package org.spring.my.dto;

public class Teacher {
	private String tno;
	private String tname;
	private String tel;
	private String zip;
	private String addr1;
	private String addr2;
	private String skill;
	private String regdate;
	
	public Teacher() {
		super();
	}

	public Teacher(String tno, String tname, String tel, String zip, String addr1, String addr2, String skill,
			String regdate) {
		super();
		this.tno = tno;
		this.tname = tname;
		this.tel = tel;
		this.zip = zip;
		this.addr1 = addr1;
		this.addr2 = addr2;
		this.skill = skill;
		this.regdate = regdate;
	}

	public String getTno() {
		return tno;
	}

	public void setTno(String tno) {
		this.tno = tno;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getAddr2() {
		return addr2;
	}

	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "Teacher [tno=" + tno + ", tname=" + tname + ", tel=" + tel + ", zip=" + zip + ", addr1=" + addr1
				+ ", addr2=" + addr2 + ", skill=" + skill + ", regdate=" + regdate + "]";
	}
	
	
	
	
}
