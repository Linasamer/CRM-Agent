package com.code.secretary.enums.security;

public enum MenuClassificationEnum {
	MAIN(1),
	WORKFLOW(2),
	REPORTS(3);

	private int code;

	private MenuClassificationEnum(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}
