package com.code.secretary.enums.workflow;

public enum WFPositionsEnum {
	;

	private int code;

	private WFPositionsEnum(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}
