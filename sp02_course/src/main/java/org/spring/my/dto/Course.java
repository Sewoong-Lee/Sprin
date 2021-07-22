package org.spring.my.dto;

import java.util.Date;
import java.util.List;

public class Course {
	private String ccode;
	private String cname;
	private String tno;
	private String startdate;
	private String enddate;
	private String complete;
	private String cperson;
	private int maxcnt;
	private Date regdate;
	private Date modifydate;
	private List<String> jlist; //과목코드 리스트
	
	public List<String> getJlist() {
		return jlist;
	}
	public void setJlist(List<String> jlist) {
		this.jlist = jlist;
	}
	
	public String getCcode() {
		return ccode;
	}
	public void setCcode(String ccode) {
		this.ccode = ccode;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getTno() {
		return tno;
	}
	public void setTno(String tno) {
		this.tno = tno;
	}
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public String getComplete() {
		return complete;
	}
	public void setComplete(String complete) {
		this.complete = complete;
	}
	public String getCperson() {
		return cperson;
	}
	public void setCperson(String cperson) {
		this.cperson = cperson;
	}
	public int getMaxcnt() {
		return maxcnt;
	}
	public void setMaxcnt(int maxcnt) {
		this.maxcnt = maxcnt;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public Date getModifydate() {
		return modifydate;
	}
	public void setModifydate(Date modifydate) {
		this.modifydate = modifydate;
	}
	@Override
	public String toString() {
		return "Course [ccode=" + ccode + ", cname=" + cname + ", tno=" + tno + ", startdate=" + startdate
				+ ", enddate=" + enddate + ", complete=" + complete + ", cperson=" + cperson + ", maxcnt=" + maxcnt
				+ ", regdate=" + regdate + ", modifydate=" + modifydate + ", jlist=" + jlist + "]";
	}
	
	
	
	
	
	
}
