package com.code.secretary.enums.workflow;

public enum WFProcessesGroupsEnum {

	SECRETARY_GROUP(1);

	private int code;

	private WFProcessesGroupsEnum(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}
