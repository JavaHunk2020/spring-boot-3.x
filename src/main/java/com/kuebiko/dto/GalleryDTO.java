package com.kuebiko.dto;

import java.util.Date;

public class GalleryDTO {
	private int id;
	private String name;
	private long size;
	private Date doe;
	private byte photo[];

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

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public Date getDoe() {
		return doe;
	}

	public void setDoe(Date doe) {
		this.doe = doe;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

}
