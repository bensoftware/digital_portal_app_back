package com.monetique.security.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.monetique.security.securityDispatcher.SecurityConstants;
import com.monetique.security.service.AccountService;
import com.monetique.security.service.AppUserData;
import com.monetique.um.dao.repositories.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@RestController
@Transactional
public class AccountRestController {
	
	@Autowired
	AccountService accountService;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	AppUserData appUserData;
	
	@Autowired
	UserRepository userRepository;
	
	@RequestMapping(value = "/deconnect" , method = RequestMethod.POST)
	public  @ResponseBody void deconnect(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String jwt=request.getHeader(SecurityConstants.HEADER_STRING);
		
		Claims claims=Jwts.parser()
				.setSigningKey(SecurityConstants.SECRET)
				.parseClaimsJws(jwt.replace(SecurityConstants.TOKEN_PREFIX,""))
				.getBody();
		
		
		String new_jwt=Jwts.builder()
				.setSubject(claims.getSubject())
				.setExpiration(new Date(System.currentTimeMillis()))
				.claim("roles",claims.get("roles"))
				.claim("actions",claims.get("actions"))
				.signWith(SignatureAlgorithm.HS256,SecurityConstants.SECRET)
				.compact();
		
		response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX+new_jwt);	
		
	}

	//@PreAuthorize("hasAuthority('AMK')")
/*	@RequestMapping(value = "/getUser" , method = RequestMethod.GET)
	public @ResponseBody User  changePws(@RequestParam String username) {
		
	    return userRepository.findByuserName(username);
	}*/
	
/*	@RequestMapping(value = "/register" , method = RequestMethod.POST)
	public @ResponseBody AppUser  register(@RequestBody ResgisterForm resgisterForm) {
		
		if(!resgisterForm.getPassword().equals(resgisterForm.getRepassword())) {
			throw new RuntimeException("You must confirm your password");
		}
		
		AppUser appUser=accountService.findUserByUsername(resgisterForm.getUsername());
		if(appUser!=null) {
			throw new RuntimeException("This user already exist !");
		}		
		
		AppUser user=new AppUser(resgisterForm.getUsername(),resgisterForm.getPassword());
	    accountService.saveUser(user);
	    accountService.addRoleToUser(resgisterForm.getUsername(),"USER");
	    
	    return user;
	}
	*/

	
/*	@Secured("ADMIN")
	@RequestMapping(value = "/changePws" , method = RequestMethod.POST)
	public @ResponseBody AppUser  changePws(@RequestBody ResgisterForm resgisterForm) {
		

		
		AppUser appUser=accountService.findUserByUsername(resgisterForm.getUsername());
		if(appUser==null) {
			throw new RuntimeException("This user not exist !");
		}		
		
		System.out.println("pass req "+resgisterForm.getPassword());
		
		System.out.println("pass sr "+appUser.getPassword());

		if(bCryptPasswordEncoder.matches(resgisterForm.getPassword() , appUser.getPassword())) {
			System.out.println("yes !!!!");
			String newPass= bCryptPasswordEncoder.encode(resgisterForm.getRepassword());
			appUser.setPassword(newPass);
			accountService.majUser(appUser);
		}

	    return null;
	}*/
	
	



	
	

	
	
}
