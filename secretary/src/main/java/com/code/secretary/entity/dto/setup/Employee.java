package com.code.secretary.entity.dto.setup;

import java.io.Serializable;

public class Employee implements Serializable {

	private long id;
	private String name;
	private String email;
	private String rankDescription;
	private String socialId;
	private Integer militaryNumber;
	private Long actualDeptId;
	private String actualDeptName;

	public Employee() {
	}

	public Employee(long id, String name, String email, String rankDescription, String socialId, Integer militaryNumber,
			Long actualDeptId, String actualDeptName) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.rankDescription = rankDescription;
		this.socialId = socialId;
		this.militaryNumber = militaryNumber;
		this.actualDeptId = actualDeptId;
		this.actualDeptName = actualDeptName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRankDescription() {
		return rankDescription;
	}

	public void setRankDescription(String rankDescription) {
		this.rankDescription = rankDescription;
	}

	public String getSocialId() {
		return socialId;
	}

	public void setSocialId(String socialId) {
		this.socialId = socialId;
	}

	public Integer getMilitaryNumber() {
		return militaryNumber;
	}

	public void setMilitaryNumber(Integer militaryNumber) {
		this.militaryNumber = militaryNumber;
	}

	public Long getActualDeptId() {
		return actualDeptId;
	}

	public void setActualDeptId(Long actualDeptId) {
		this.actualDeptId = actualDeptId;
	}

	public String getActualDeptName() {
		return actualDeptName;
	}

	public void setActualDeptName(String actualDeptName) {
		this.actualDeptName = actualDeptName;
	}
}
