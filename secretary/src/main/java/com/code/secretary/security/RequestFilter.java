//package com.code.secretary.security;
//
//import java.io.IOException;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import com.code.secretary.security.service.UserDetailsServiceImpl;
//import com.code.secretary.security.util.JwtUtilities;
//
//import io.jsonwebtoken.ExpiredJwtException;
//
//@Component
//public class RequestFilter extends OncePerRequestFilter {
//
//	private UserDetailsServiceImpl userDetailsServiceImpl;
//
//	@Autowired
//	public RequestFilter(UserDetailsServiceImpl userDetailsServiceImpl) {
//		this.userDetailsServiceImpl = userDetailsServiceImpl;
//	}
//
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//		// TODO: exception handling
//		try {
//			String jwtToken = parseJwt(request);
//			String username = null;
//
//			if (jwtToken != null)
//				username = JwtUtilities.getUsernameFromJwtToken(jwtToken);
//
//			if (username != null) {
//				UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(username);
//
//				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//			}
//
//			response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
//
//			filterChain.doFilter(request, response);
//		} catch (ExpiredJwtException e) {
//			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//		}
//	}
//
//	private String parseJwt(HttpServletRequest request) {
//		String authorizationHeader = request.getHeader("Authorization");
//
//		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//			return authorizationHeader.substring(7, authorizationHeader.length());
//		}
//
//		return null;
//	}
//}