package com.kuebiko.dao.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="credit_card_detail_tbl")
public class CreditCardDetailEntity {
	private int ccd;
	private int sid;
	private String email;
	private String applicationId;
	private String type;
	private String cardName;
	private String nameOnCard;
	private String climit;
	private String number;
	private String expDate;
	private int cvv;
	private byte[] photo;
	private Timestamp doa;
	private Timestamp dom;
	
	
	
	public String getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getCcd() {
		return ccd;
	}
	public void setCcd(int ccd) {
		this.ccd = ccd;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getExpDate() {
		return expDate;
	}
	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}
	public int getCvv() {
		return cvv;
	}
	public void setCvv(int cvv) {
		this.cvv = cvv;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	public String getNameOnCard() {
		return nameOnCard;
	}
	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}
	public String getClimit() {
		return climit;
	}
	public void setClimit(String climit) {
		this.climit = climit;
	}
	
	@Column(columnDefinition = "longblob")
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	public Timestamp getDoa() {
		return doa;
	}
	public void setDoa(Timestamp doa) {
		this.doa = doa;
	}
	public Timestamp getDom() {
		return dom;
	}
	public void setDom(Timestamp dom) {
		this.dom = dom;
	}


	

}
