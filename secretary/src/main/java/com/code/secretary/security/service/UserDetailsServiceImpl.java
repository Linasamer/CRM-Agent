//package com.code.secretary.security.service;
//
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.code.secretary.security.entity.UserDetailsImpl;
//import com.code.secretary.service.setup.EmployeeService;
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//	private EmployeeService employeeService;
//
//	public UserDetailsServiceImpl(EmployeeService employeeService) {
//		this.employeeService = employeeService;
//	}
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		return new UserDetailsImpl(employeeService.getEmployeeByEmail(username), username, "$2a$10$e5KWHKxH6FoxBRH..XdELOqqxZsKX72HsTBOsV/MTflTV1O2Aem7a");
//	}
//
//}
