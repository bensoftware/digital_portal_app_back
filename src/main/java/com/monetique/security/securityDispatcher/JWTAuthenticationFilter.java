package com.monetique.security.securityDispatcher;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.monetique.security.entities.AppUser;
import com.monetique.security.service.AppUserData;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	AuthenticationManager authenticationManager;
	
	
	AppUserData appUserData;
	
	
	
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager,AppUserData appUserData) {
		super();
		this.authenticationManager = authenticationManager;
		this.appUserData=appUserData;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		AppUser appUser=null;
		// xxx url incoded
		//request.getParameter("username"); 
		try {
			appUser=new ObjectMapper().readValue(request.getInputStream(),AppUser.class);

			//System.out.println("username : "+appUser.getUsername()+" pass "+appUser.getPassword());
		}catch (Exception e) {
			throw new RuntimeException(e);
		}

		
		return authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(appUser.getUsername(), appUser.getPassword()));
	}
	
	// succés d'une authentification
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
	//	User springUser=(User) authResult.getPrincipal();
		
		String username = authResult.getPrincipal().toString();
		
		String jwt=null;
		try {
				
			jwt = Jwts.builder()
					.setSubject(username)
					.setExpiration(new Date(System.currentTimeMillis()+SecurityConstants.EXPIRATION_TIME))
					.claim("roles", appUserData.getAllRules(username))
					.claim("actions",authResult.getAuthorities())
					.signWith(SignatureAlgorithm.HS256,SecurityConstants.SECRET)
					.compact();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX+jwt);		
		
	}
}
