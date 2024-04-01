//package com.code.secretary.security.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Service;
//
//import com.code.secretary.entity.dto.security.AuthenticationRequest;
//import com.code.secretary.entity.dto.security.AuthenticationResponse;
//import com.code.secretary.entity.dto.setup.Employee;
//import com.code.secretary.exceptions.custom.BusinessException;
//import com.code.secretary.security.entity.UserDetailsImpl;
//import com.code.secretary.security.util.JwtUtilities;
//import com.code.secretary.service.security.SecurityService;
//
//@Service
//public class AuthenticationService {
//	@Value("${ldap.enabled}") // get flag from application.properties
//	private boolean ldapEnabled;
//
//	private SecurityService securityService;
//
//	private AuthenticationManager authenticationManager;
//
//	@Autowired
//	private AuthenticationService(SecurityService securityService, AuthenticationManager authenticationManager) {
//		this.securityService = securityService;
//		this.authenticationManager = authenticationManager;
//	}
//
//	// TODO: exception handling
//	public AuthenticationResponse authenticateUser(AuthenticationRequest authenticationRequest) {
//		if (!securityService.authenticateUser(authenticationRequest.getUsername(), authenticationRequest.getPassword(), ldapEnabled))
//			throw new BusinessException("error_invalidUserNameOrPassword");
//
//		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getSystemPassword());
//		Authentication authentication = authenticationManager.authenticate(authenticationToken);
//		SecurityContextHolder.getContext().setAuthentication(authentication);
//
//		String token = JwtUtilities.generateJwtToken(authenticationRequest.getUsername());
//		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//
//		/*
//		 * List<Menu> mainMenu = securityService.getMenusByEmployeeIdAndMenuClassification(userDetails.getEmployee().getId(), MenuClassificationEnum.MAIN.getCode());
//		 * List<Menu> workflowMenu = securityService.getMenusByEmployeeIdAndMenuClassification(userDetails.getEmployee().getId(), MenuClassificationEnum.WORKFLOW.getCode());
//		 * List<Menu> reportsMenu = securityService.getMenusByEmployeeIdAndMenuClassification(userDetails.getEmployee().getId(), MenuClassificationEnum.REPORTS.getCode());
//		 * return new AuthenticationResponse(userDetails.getEmployee(), token, mainMenu, workflowMenu, reportsMenu);
//		 */
//
//		return new AuthenticationResponse(userDetails.getEmployee(), token, null, null, null);
//	}
//
//	public Employee getLoginEmployee() {
//		return ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmployee();
//	}
//}
