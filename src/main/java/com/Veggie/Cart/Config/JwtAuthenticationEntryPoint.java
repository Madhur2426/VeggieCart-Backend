//package com.Veggie.Cart.Config;
//
//import java.io.IOException;
//import java.io.Serializable;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.stereotype.Component;
//
//import jakarta.servlet.ServletException;
//
//@Component
//public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {
//
//	
//	private static final long serialVersionUID = -7858869558953243875L;
//
////	//To handle unauthorized access attempts.
////	public void commence(HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response, AuthenticationException authException) throws IOException,ServletException {
////		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
////	}
//
//	@Override
//	public void commence(jakarta.servlet.http.HttpServletRequest request,
//			jakarta.servlet.http.HttpServletResponse response, AuthenticationException authException)
//			throws IOException, ServletException {
//		// TODO Auto-generated method stub
//		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
//
//		
//	}
//
//	
//	
//}
