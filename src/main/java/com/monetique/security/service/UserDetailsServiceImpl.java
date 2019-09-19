package com.monetique.security.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.monetique.security.entities.AppUser;
import com.monetique.um.dao.repositories.UserRepository;


@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	AppUserData appUserData;
	
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser user=null;
		Set<String> actions=null;
		try {
			user = accountService.findUserByUsername(username);
			actions= appUserData.getAllActions(username);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(user==null) {
			throw new UsernameNotFoundException(username);
		}else {
			com.monetique.um.dao.entities.User users=userRepository.findByuserName(username);
		
		    if(!users.isActif()) {
		    	try {
					throw new Exception("utilisateur suspendu");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	
				return null;

		    }
		}
	
		
		if(actions==null || actions.size()==0) {
			try {
				throw new Exception("l'utilisateur n'a pas de privilége");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		Collection<GrantedAuthority> authorities=new ArrayList<>();
		
		
		
		actions.forEach(r->{
			authorities.add(new SimpleGrantedAuthority(r));
		});
		
		
		return new User(user.getUsername(), user.getPassword(), authorities);
	}
	

	

}
