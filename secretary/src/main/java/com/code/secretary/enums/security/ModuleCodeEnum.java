package com.code.secretary.enums.security;

public enum ModuleCodeEnum {

	MODULE_CODE_IN_SECURITY("SECRETARY"),
	MODULE_CODE_IN_UNIFIED_UPLOADER("SECRETARY_SYS");

	private String code;

	private ModuleCodeEnum(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}