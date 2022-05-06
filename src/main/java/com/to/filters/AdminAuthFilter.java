package com.to.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.filter.GenericFilterBean;

import com.to.Constants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class AdminAuthFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
		HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

		// getting the bearer string
		String authHeader = httpRequest.getHeader("Authorization");

		// checking the header is null or not
		System.out.println("Auth header string " + authHeader);
		if (authHeader != null) {
			// split the string into the array
			String[] authHeaderArr = authHeader.split("Bearer ");

			if (authHeaderArr.length > 1 && authHeaderArr[1] != null) {
				String token = authHeaderArr[1];

				try {

					Claims claims = Jwts.parser().setSigningKey(Constants.API_SECRET_KEY_STRING).parseClaimsJws(token)
							.getBody();
					httpRequest.setAttribute("adminId",claims.get("adminId").toString());
					
					System.out.println("Admin Filter : "+claims.get("adminId").toString());

				} catch (Exception e) {
					httpResponse.sendError(HttpStatus.FORBIDDEN.value(), "Invalid/Expired Admin token reason :"+e.getMessage());
					return;
				}
			}  
			else {
				httpResponse.sendError(HttpStatus.FORBIDDEN.value(), "Authorization tocken must be Bearer[token] in admin token");
				return;
				
			}
		}else {
			httpResponse.sendError(HttpStatus.FORBIDDEN.value(), "Authorization token must be provided for admin");
			return;
		}
		
		//If all is set then continue the process
		filterChain.doFilter(servletRequest, servletResponse);
       
	}

}
