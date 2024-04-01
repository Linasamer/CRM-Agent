//package com.code.secretary.security.entity;
//
//import java.util.ArrayList;
//import java.util.Collection;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import com.code.secretary.entity.dto.setup.Employee;
//
//public class UserDetailsImpl implements UserDetails {
//
//	private String username;
//	private String password;
//	private Employee employee;
//
//	public UserDetailsImpl(Employee employee, String username, String password) {
//		this.employee = employee;
//		this.username = username;
//		this.password = password;
//	}
//
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		return new ArrayList<>();
//	}
//
//	@Override
//	public String getPassword() {
//		return password;
//	}
//
//	@Override
//	public String getUsername() {
//		return username;
//	}
//
//	@Override
//	public boolean isAccountNonExpired() {
//		return true;
//	}
//
//	@Override
//	public boolean isAccountNonLocked() {
//		return true;
//	}
//
//	@Override
//	public boolean isCredentialsNonExpired() {
//		return true;
//	}
//
//	@Override
//	public boolean isEnabled() {
//		return true;
//	}
//
//	public Employee getEmployee() {
//		return employee;
//	}
//}
