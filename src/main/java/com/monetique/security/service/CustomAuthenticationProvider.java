package com.monetique.security.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.monetique.dto.AuthentificationIn;
import com.monetique.dto.AuthentificationOut;
import com.monetique.service.LdapService;

public class CustomAuthenticationProvider implements AuthenticationProvider  {

	
	LdapService ldapService;
	AppUserData appUserData;
	
	 public CustomAuthenticationProvider(AppUserData appUserData,LdapService ldapService) {
		// TODO Auto-generated constructor stub
		 this.appUserData=appUserData;
		 this.ldapService=ldapService;
	}
	
	
	
    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        String name = authentication.getName();
        Set<String> actions=new HashSet<>();
        
        // You can get the password here
        String password = authentication.getCredentials().toString();
        AuthentificationIn in= new AuthentificationIn();
        AuthentificationOut out;
        in.login=name;
        in.password=authentication.getCredentials().toString();
        
        try {
			 out=ldapService.login(in);
			 System.out.println(out.errorCode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
        
        if(out.errorCode==1)
        	return null;
        	try {
        		name=out.data.getsAMAccountName();
    			actions= appUserData.getAllActions(out.data.getsAMAccountName(),out);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
        
        
		Collection<GrantedAuthority> authorities=new ArrayList<>();
		
	//	actions.add("conscp");
		actions.forEach(r->{
			authorities.add(new SimpleGrantedAuthority(r));
		});
	
        
        
        // Your custom authentication logic here
  //      if (name.equals("admin") && password.equals("pwd")) {
        	
			
        	System.out.println("OUII ");
            Authentication auth = new UsernamePasswordAuthenticationToken(name,
                    password,authorities);
            

            return auth;
  //      }
//        else {
//        	System.out.println("NON !!!");
//        }

        
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
