//package com.Veggie.Cart.Config;
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
////import com.productcatalog.UserDetailsService.*;
//import io.jsonwebtoken.ExpiredJwtException;
//
//@Component
//public class RequestFilter extends OncePerRequestFilter {
//
//	@Autowired
//	private JwtUserDetailsService jwtUserDetailsService;
//
//	@Autowired
//	private JwtAuthenticationUtil jwtAuthenticationUtil;
//
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException {
//
//		String requestHeader = request.getHeader("Authorization");
//		String username = null;
//		String jwtAuthorizationToken = null;
//
//		if (requestHeader != null && requestHeader.startsWith("Bearer ")) { 
//			jwtAuthorizationToken = requestHeader.substring(7); //To remove Bearer word and get only the Token.
//			try {
//				username = jwtAuthenticationUtil.getUsernameFromToken(jwtAuthorizationToken);
//			} catch (IllegalArgumentException e) {
//				System.out.println("Unable to get JWT Token");
//			} catch (ExpiredJwtException e) {
//				System.out.println("JWT Token has expired");
//			}
//		} else {
//			logger.warn("JWT Token does not begin with Bearer String");
//		}
//
//		//Check whether username is null or not
//		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//
//			//If username is not null then validate token.
//			UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);
//
//			if (jwtAuthenticationUtil.validateToken(jwtAuthorizationToken, userDetails)) {
//
//				// If token is valid then manually set authentication to configure Spring Security.
//				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource()
//			    .buildDetails(request));
//				// To specify that the current user is authenticated.
//				//So that the current user passes the Spring Security Configurations authentication successfully.
//				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//			}
//		}
//		filterChain.doFilter(request, response);//To set filter and return response.
//	}
//
//}