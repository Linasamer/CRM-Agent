package com.code.secretary.entity.dto.security;

import java.io.Serializable;
import java.util.List;

import com.code.secretary.entity.dto.setup.Employee;

public class AuthenticationResponse implements Serializable {

	private String token;
	private Employee employee;

	private List<Menu> mainMenu;
	private List<Menu> workflowMenu;
	private List<Menu> reportsMenu;

	public AuthenticationResponse(Employee employee, String token, List<Menu> mainMenu, List<Menu> workflowMenu, List<Menu> reportsMenu) {
		this.token = token;
		this.employee = employee;
		this.mainMenu = mainMenu;
		this.workflowMenu = workflowMenu;
		this.reportsMenu = reportsMenu;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public List<Menu> getMainMenu() {
		return mainMenu;
	}

	public void setMainMenu(List<Menu> mainMenu) {
		this.mainMenu = mainMenu;
	}

	public List<Menu> getWorkflowMenu() {
		return workflowMenu;
	}

	public void setWorkflowMenu(List<Menu> workflowMenu) {
		this.workflowMenu = workflowMenu;
	}

	public List<Menu> getReportsMenu() {
		return reportsMenu;
	}

	public void setReportsMenu(List<Menu> reportsMenu) {
		this.reportsMenu = reportsMenu;
	}

}
