package com.code.secretary.service.security;

import java.util.List;

import com.code.secretary.entity.dto.security.Menu;

public interface SecurityService {
	public boolean authenticateUser(String username, String password, boolean ldapFlag);

	public List<Menu> getMenusByEmployeeIdAndMenuClassification(Long employeeId, Integer menuClassification);

	public boolean isMenuActionGranted(Long employeeId, String action);

	public boolean isSystemAdmin(String employeeEmail);

}
