package com.kuebiko.dao.entity;

//class which is used to map with database 
//table is called entity
public class SignupEntity {
	
	private int sid;
	private String name;
	private String email;
	private String gender;
	
	public SignupEntity() {
		
	}
	
	public SignupEntity(int sid, String name, String email, String gender) {
		this.sid = sid;
		this.name = name;
		this.email = email;
		this.gender = gender;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	

}