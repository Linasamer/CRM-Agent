// package com.code.secretary.service;
//
// import org.springframework.beans.factory.annotation.Autowired;
//
// import com.code.secretary.entity.dto.setup.Employee;
// import com.code.secretary.security.service.AuthenticationService;
//
// public abstract class BaseService {
// private AuthenticationService authenticationService;
//
// public BaseService() {
// }
//
// @Autowired
// public void setAuthenticationService(AuthenticationService authenticationService) {
// this.authenticationService = authenticationService;
// }
//
// protected Employee getCurrentEmployee() {
// return this.authenticationService.getLoginEmployee();
// }
// }
