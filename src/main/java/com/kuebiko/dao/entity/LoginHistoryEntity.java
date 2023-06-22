package com.kuebiko.dao.entity;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//class which is used to map with database 
//table is called entity
@Entity
@Table(name="login_history_tbl")
public class LoginHistoryEntity {
	
	@Id //means it is primary key
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private Timestamp ltime ;
	private Timestamp logouttime ;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "signup_fid")
	private SignupEntity signupEntity;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Timestamp getLtime() {
		return ltime;
	}

	public void setLtime(Timestamp ltime) {
		this.ltime = ltime;
	}

	public Timestamp getLogouttime() {
		return logouttime;
	}

	public void setLogouttime(Timestamp logouttime) {
		this.logouttime = logouttime;
	}

	public SignupEntity getSignupEntity() {
		return signupEntity;
	}

	public void setSignupEntity(SignupEntity signupEntity) {
		this.signupEntity = signupEntity;
	}
	
	

}