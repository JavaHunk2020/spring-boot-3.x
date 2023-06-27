package com.kuebiko.rest.controller;

public enum Role {
	
	CUSTOMER("Customer"),ADMIN("Admin"),EMPLOYEE("Employee");
	private String name;
	private Role(String name) {
		this.name=name;
	}
	
	public String getValue() {
		return name;
	}

}
