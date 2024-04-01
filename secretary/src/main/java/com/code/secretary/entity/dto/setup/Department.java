package com.code.secretary.entity.dto.setup;

import java.io.Serializable;

public class Department implements Serializable {
	private Long id;
	private String code;
	private String name;
	private String arabicName; // TODO to be removed after setup updates
	private Long actualManagerId;
	private Long parentUnitId;
	private String hKey;

	public Department() {

	}

	public Department(Long id, String code, String name, Long actualManagerId, Long parentUnitId, String hKey) {
		this.id = id;
		this.code = code;
		this.name = name;
		this.actualManagerId = actualManagerId;
		this.hKey = hKey;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		if (arabicName == null)
			arabicName = name;
	}

	public String getArabicName() {
		return arabicName;
	}

	public void setArabicName(String arabicName) {
		this.arabicName = arabicName;
		if (name == null)
			name = arabicName;
	}

	public Long getActualManagerId() {
		return actualManagerId;
	}

	public void setActualManagerId(Long actualManagerId) {
		this.actualManagerId = actualManagerId;
	}

	public Long getParentUnitId() {
		return parentUnitId;
	}

	public void setParentUnitId(Long parentUnitId) {
		this.parentUnitId = parentUnitId;
	}

	public String gethKey() {
		return hKey;
	}

	public void sethKey(String hKey) {
		this.hKey = hKey;
	}

}
