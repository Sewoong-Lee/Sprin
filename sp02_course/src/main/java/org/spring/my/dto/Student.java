package org.spring.my.dto;

import java.util.Date;

public class Student {
	private String sno;
	private String sname;
	private String tel;
	private String zip;
	private String addr1;
	private String addr2;
	private Date regdate;
	
	public Student() {
		super();
	}


	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
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

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "Student [sno=" + sno + ", sname=" + sname + ", tel=" + tel + ", zip=" + zip + ", addr1=" + addr1
				+ ", addr2=" + addr2 + ", regdate=" + regdate + "]";
	}
	
	
}
