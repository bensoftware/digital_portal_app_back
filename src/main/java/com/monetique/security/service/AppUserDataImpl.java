package com.monetique.security.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.monetique.dto.AuthentificationOut;
import com.monetique.security.entities.AppRole;
import com.monetique.security.entities.AppUser;
import com.monetique.um.dao.entities.Action;
import com.monetique.um.dao.entities.Rule;
import com.monetique.um.dao.entities.User;
import com.monetique.um.dao.repositories.RuleRepository;
import com.monetique.um.dao.repositories.UserRepository;

@Service
public class AppUserDataImpl implements AppUserData{

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	RuleRepository ruleRepository;
	
	
	@Override
	public AppUser findUserByUsername(String username) throws Exception {
		// TODO Auto-generated method stub
		User user=null;
		user=userRepository.findByuserName(username.toLowerCase());
		
		if (user==null)
			user=userRepository.findByuserName(username.toUpperCase());
		
		if (user==null)
			user=userRepository.findByuserName(username);
		
		if(user!=null) {
			// recuperer le context User et le mettre dans AppUser
			AppUser appUser=new AppUser(user.getUserName(), user.getPassword());
			
			List<AppRole> appRoles=new ArrayList<>();
			
			for(Rule r : user.getRules()) {
				appRoles.add(new AppRole(r.getLibelle()));
			}
			
			appUser.setRoles(appRoles);	
			
			return appUser;
		}else {
			throw new  Exception("l'utilisateur n'existe pas");
		}
	
	}

	@Override
	public Object securityUserContext(Object user) {
		// TODO Auto-generated method stub
		User u= (User) user;	
		String hashPw=bCryptPasswordEncoder.encode(u.getPassword());
		u.setPassword(hashPw);
		return u;
	}

	@Override
	public boolean CheckPasswordUser(long idUser,String password) throws Exception {
		// TODO Auto-generated method stub
		
		Optional<User> optuser =userRepository.findById(idUser);
		
		if(!optuser.isPresent()) {
			throw new Exception("user n'existe pas");
		}
		User user=optuser.get();
		
		if(bCryptPasswordEncoder.matches(password , user.getPassword())) {

			return true;
		}
		return false;
	}

	@Override
	public Set<String> getAllActions(String username,AuthentificationOut out) throws Exception {
		//System.out.println("******************");

		User user =userRepository.findByuserName(username.toLowerCase());
		
		if(user==null)
			user =userRepository.findByuserName(username);
		if(user==null)
		       user =userRepository.findByuserName(username.toUpperCase());
		
		Set<Rule> rules=new HashSet<>();
		
		if(user==null) {
			User newUser =new User();
			newUser.setDateCreation(new Date());
			newUser.setActif(true);
			newUser.setEmail(out.data.getEmail());
			newUser.setNom(out.data.getGivenName());
			newUser.setPrenom(out.data.getSn());
			newUser.setUserName(out.data.getsAMAccountName());
			Rule rule=ruleRepository.findBylibelle("Default");
			rules.add(rule);
			newUser.setRules(rules);
			user=userRepository.save(newUser);
			
			
		}
		
		System.out.println(user.getUserName());
		
		 rules=user.getRules();
		Set<String> actions=new HashSet<>();
		
		
		
		if(rules.isEmpty()) {
			throw new Exception("user n a pas de role");
		}
		
		for(Rule r: rules) {
			
			// rôle active
			
			if(r.isActive()) {
				List<Action> listA=r.getActions();
				if(listA.size()!=0) {
					for(Action a : listA) {
						actions.add(a.getMotCle());
					}
				}
			}
		
		}
		
		return actions;
	}
	

	@Override
	public Set<String> getAllRules(String username) throws Exception {
		
	User user =userRepository.findByuserName(username);
		
		
		if(user==null) {
			//throw new Exception("user n'existe pas");
			return null;
		}
		
		Set<String> rules=new HashSet<>();
		
		Collection<Rule> list=user.getRules();
		
		if(list==null || list.size()==0) {
			throw new Exception("user n'a pas de role");
		}
		
		list.forEach(r ->{
			rules.add(r.getLibelle());
		});
		
		return rules;
	}

	@Override
	public String securePassword(String password) {
		// TODO Auto-generated method stub
		return bCryptPasswordEncoder.encode(password);
	}

	@Override
	public boolean checkSecurePassword(String password, String passwordEncode) {
		// TODO Auto-generated method stub
		if(bCryptPasswordEncoder.matches(password , passwordEncode)) {

			return true;
		}
		return false;
	}
	
	

}
