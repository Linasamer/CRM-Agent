package com.code.secretary.entity.dto.security;

import java.io.Serializable;

public class Menu implements Serializable {
	private Long id;
	private String arabicName;
	private String latinName;
	private String name;
	private Integer showInMenu;
	private Long parentId;
	private String url;
	private Integer classification;

	public Menu() {

	}

	public Menu(Long id, Integer classification, String arabicName, String latinName, String name, Integer showInMenu, Long parentId, String url) {
		this.id = id;
		this.classification = classification;
		this.arabicName = arabicName;
		this.latinName = latinName;
		this.name = name;
		this.showInMenu = showInMenu;
		this.parentId = parentId;
		this.url = url;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getArabicName() {
		return arabicName;
	}

	public void setArabicName(String arabicName) {
		this.arabicName = arabicName;
	}

	public String getLatinName() {
		return latinName;
	}

	public void setLatinName(String latinName) {
		this.latinName = latinName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getShowInMenu() {
		return showInMenu;
	}

	public void setShowInMenu(Integer showInMenu) {
		this.showInMenu = showInMenu;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getClassification() {
		return classification;
	}

	public void setClassification(Integer classification) {
		this.classification = classification;
	}
}
