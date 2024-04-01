package com.code.secretary.entity.dto.setup;

import java.io.Serializable;

public class EmployeePhoto implements Serializable {
	private byte[] photo;

	public EmployeePhoto(byte[] photo) {
		this.photo = photo;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
}
