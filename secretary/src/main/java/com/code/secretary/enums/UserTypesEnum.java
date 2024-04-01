package com.code.secretary.enums;

public enum UserTypesEnum {

	MANAGER("M"),
	SECRETARY("S");

	private String code;

	private UserTypesEnum(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
