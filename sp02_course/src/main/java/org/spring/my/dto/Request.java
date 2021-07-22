package org.spring.my.dto;

public class Request {
	private String ccode;
	private String sno;
	
	public String getCcode() {
		return ccode;
	}
	public void setCcode(String ccode) {
		this.ccode = ccode;
	}
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
	@Override
	public String toString() {
		return "Request [ccode=" + ccode + ", sno=" + sno + "]";
	}
	
	
}
