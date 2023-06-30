package com.kuebiko.dao.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="credit_card_application_tbl")
public class CreditCardApplicationEntity {
	private int cid;
	private int sid;
	private String email;
	private String type;
	private String cardName;
	private String nameOnCard;
	private String climit;
	private String status;
	private String applicationId;
	private Timestamp doa;
	private Timestamp dom;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	@Column(length = 300)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(length = 100)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(length = 300)
	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	@Column(length = 300)
	public String getNameOnCard() {
		return nameOnCard;
	}

	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}

	
	@Column(length = 20)
	public String getClimit() {
		return climit;
	}

	public void setClimit(String climit) {
		this.climit = climit;
	}

	@Column(length = 20)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(length = 100)
	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
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
