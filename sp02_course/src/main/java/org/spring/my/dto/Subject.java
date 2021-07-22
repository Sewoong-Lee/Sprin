package org.spring.my.dto;

public class Subject {
	private String jcode;
	private String jname;
	private String jbook;
	
	public Subject() {
		super();
	}
	
	

	public Subject(String jcode, String jname, String jbook) {
		super();
		this.jcode = jcode;
		this.jname = jname;
		this.jbook = jbook;
	}



	public String getJcode() {
		return jcode;
	}

	public void setJcode(String jcode) {
		this.jcode = jcode;
	}

	public String getJname() {
		return jname;
	}

	public void setJname(String jname) {
		this.jname = jname;
	}

	public String getJbook() {
		return jbook;
	}

	public void setJbook(String jbook) {
		this.jbook = jbook;
	}

	@Override
	public String toString() {
		return "Subject [jcode=" + jcode + ", jname=" + jname + ", jbook=" + jbook + "]";
	}
	
	
	
	
	
	
}
