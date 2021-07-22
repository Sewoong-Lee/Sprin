package org.spring.my.dto;

public class CouseSubject {
	private String ccode;
	private String jcode;
	
	public CouseSubject() {
		super();
	}

	public CouseSubject(String ccode, String jcode) {
		super();
		this.ccode = ccode;
		this.jcode = jcode;
	}

	public String getCcode() {
		return ccode;
	}

	public void setCcode(String ccode) {
		this.ccode = ccode;
	}

	public String getJcode() {
		return jcode;
	}

	public void setJcode(String jcode) {
		this.jcode = jcode;
	}

	@Override
	public String toString() {
		return "CouseSubject [ccode=" + ccode + ", jcode=" + jcode + "]";
	}
	
	
	
}
