package com.kuebiko.dao.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "passport_details_tbl")
public class PassportEntity {

	private int id;
	private String number;
	private Date exp;
	private String name;
	private String address;
	private Date doe;
	private String photo;
	
	
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	private SignupEntity signupEntity;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fsid",unique = true)
	public SignupEntity getSignupEntity() {
		return signupEntity;
	}

	public void setSignupEntity(SignupEntity signupEntity) {
		this.signupEntity = signupEntity;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}




	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Date getExp() {
		return exp;
	}

	public void setExp(Date exp) {
		this.exp = exp;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDoe() {
		return doe;
	}

	public void setDoe(Date doe) {
		this.doe = doe;
	}

}
