package com.kuebiko.controller.dto;

public class CreditCardDTO {

	private int sid;
	private int cid;
	private String email;
	private String type;
	private String cardName;
	private String nameOnCard;
	private String climit;
	private String status;
	private String applicationId;
	private byte[] photo;
	
	private String number;
	private String expDate;
	private int cvv;
	
	

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

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	@Override
	public String toString() {
		return "CreditCardDTO [sid=" + sid + ", cid=" + cid + ", email=" + email + ", type=" + type + ", cardName="
				+ cardName + ", nameOnCard=" + nameOnCard + ",climit=" + climit + ", status=" + status
				+ ", applicationId=" + applicationId + "]";
	}

}
