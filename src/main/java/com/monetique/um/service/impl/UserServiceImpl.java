package com.monetique.um.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monetique.security.securityDispatcher.SecurityConstants;
import com.monetique.security.service.AppUserData;
import com.monetique.um.dao.entities.Rule;
import com.monetique.um.dao.entities.User;
import com.monetique.um.dao.repositories.UserRepository;
import com.monetique.um.service.IMailService;
import com.monetique.um.service.IUserService;
import com.monetique.um.utils.DateUtils;
import com.monetique.um.utils.Password;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	UserRepository userRepository;

	
	@Autowired
	IMailService  mailService;
	
	@Autowired
	AppUserData appUserData;
	
	@Autowired
	HttpServletRequest httpServletRequest;
	
	
	@Override
	public boolean addUser(User user) throws Exception {
		// TODO Auto-generated method stub
		String pwdOriginal = user.getPassword();
		if (user.getId() == null) {
			Date datec = new Date();
			try {
				
				String strDate = DateUtils.formatDateAll(datec);
				String passwordToEncode = user.getPassword().concat(strDate);
				String pwd = Password.getEncodedPasswordWithoutCharset(passwordToEncode);
				user.setPassword(pwd);
			} catch (UnsupportedEncodingException uee) {
				throw new Exception("Erreur init password ", uee);
			}
			user.setDateCreation(datec);
			user.setDateModPass(datec);
			user.setActif(true);
			user.setMdpamodifier(true);
			
		}
		
		if (user.getId() != null && user.getPassword() != null){
		User tmp = userRepository.getOne(user.getId());
		if (!tmp.getPassword().equals(user.getPassword())){
			
			try {
				String strDate = DateUtils.formatDateAll(user.getDateCreation());
				String passwordToEncode = user.getPassword().concat(strDate);
				String pwd = Password.getEncodedPasswordWithoutCharset(passwordToEncode);
				user.setPassword(pwd);
			} catch (UnsupportedEncodingException uee) {
				throw new Exception("Erreur init password ", uee);
			}
		}
		}
		
		User u = userRepository.saveAndFlush(user);
		if (u != null) {
			if (u.getId() != null) {
				//envoi mail		
				mailService.envoyerMailCreationUtilisateur(u,pwdOriginal);
				//journalService.logJournal(this.getClass().getMethods(), "createUtilisateur", "Ajout Utilisateur " + user.getMatricule(), "Utilisateur", u.getId());
			}
			/*else {
				journalService.logJournal(this.getClass().getMethods(), "createUtilisateur", "Modification Utilisateur " + user.getMatricule(), "Utilisateur", u.getId());
			}*/
			return true;
		}
		return false;
	}
	
	@Override
	public void updateUser(User user) throws Exception {	
		userRepository.saveAndFlush(user);
	}

	@Override
	public List<User> getAllUser() throws Exception {
		
		String jwt=httpServletRequest.getHeader(SecurityConstants.HEADER_STRING);
		
		Claims claims=Jwts.parser()
				.setSigningKey(SecurityConstants.SECRET)
				.parseClaimsJws(jwt.replace(SecurityConstants.TOKEN_PREFIX,""))
				.getBody();
		
		List<String>  roles= (List<String>) claims.get("roles");
		
		boolean isAdmin=false;
		
		if(roles!=null && roles.size()!=0) {
			for(String x : roles) {
				if(x.equals("Admin")) {
					isAdmin=true;
					break;
				}
			}
		}
		
		if(isAdmin) {
			return userRepository.findAll();
		}else {
			
			List<User> users= new ArrayList<>();
			
			for(User x : userRepository.findAll()) {
				boolean exist=existeAdmin(x.getRules());
				if(!exist)
					users.add(x);
			}
			return users;
		}
		
		
	}
	
	private boolean existeAdmin(Set<Rule> rules) {
		
		if(rules!=null && rules.size()!=0) {
			for(Rule x : rules) {
				if(x.getLibelle().equals("Admin")) {
					return true;
				}
			}
		}
		
		return false;
	}

	@Override
	public User getUser(long id) throws Exception {
        Optional<User> user= userRepository.findById(id);
		
		if(!user.isPresent())
			throw new Exception("cet utilisateur n'existe pas");	
		
		User res=user.get();
		
		Set<Rule> list=res.getRules();
		Set<Rule> list2= new TreeSet<>(new Comparator<Rule>() {

			@Override
			public int compare(Rule o1, Rule o2) {
				// TODO Auto-generated method stub
				return (int)( o1.getId() -o2.getId());
			}
		});	
		list2.addAll(list);
     	res.setRules(list2);
		
		return res;
	}

	@Override
	public void deleteUser(Long id) throws Exception {
		if (id == null)  throw new Exception("id utilisateur est nul");
		Optional<User> optUser = userRepository.findById(id);
		if (!optUser.isPresent())
			throw new Exception("Aucune utilisateur retrouvé avec cet identifiant");
		userRepository.delete(optUser.get());
		
	}

	@Override
	public void addRulesToUser(List<Rule> rules, long idUser) throws Exception {	

	   	if(idUser==0 && rules==null ) throw new Exception("Id rôle ou  user non définit");
		  Optional<User> userOp=userRepository.findById(idUser);   	
	   	
	   	if(userOp.isPresent()) {
	   		
	   		User user=userOp.get();
	   		Set<Rule> listRule=user.getRules();
	   		listRule.addAll(rules);
	   		userRepository.saveAndFlush(user);
	   		
	   	}
	}

	@Override
	public void removeRulesToUser(List<Rule> rules, long idUser) throws Exception {
		if(idUser==0 && rules==null ) throw new Exception("Id utilisateur ou liste des rules non définit");
		Optional<User> userOp=userRepository.findById(idUser);		   	
		   	if(userOp.isPresent()) {
		   		
		   		User user=userOp.get();
		   		
		   		Set<Rule> listRule=user.getRules(); 		
		   		// traitement de suppression
		   		rules.forEach(e->{
		   			for(Rule a : listRule) {
		   				if(e.getId()==a.getId()) {
		   					listRule.remove(a);
		   					break;
		   				}
		   			}
		   		});   		
		   		userRepository.saveAndFlush(user);
		   		
		   	}
		
	}

	@Override
	public void changeStatusUser(User user) throws Exception {
		Long id=user.getId();
		if (id == null) throw new Exception("Id de l'utilisateur non définit");	
		   		boolean active=false;
		   		if(!user.isActif())
		   			active=true;
		   		
		   		user.setActif(active);
		   		userRepository.saveAndFlush(user);
		 
	}

	@Override
	public long existsEmail(String email) throws Exception {
		return userRepository.existsEmail(email);
	}

	@Override
	public long existsEmail(String email, Long idUtilisateur) throws Exception {
		return userRepository.existsEmail(email, idUtilisateur);
	}
	

	@Override
	public void resetPassword(User user) throws Exception {
			if (user != null && (user.getEmail() == null || "".equals(user.getEmail())))
				throw new Exception("L'adresse mail enregistré sur ce compte n'est pas valide. Veuillez contacter votre administrateur");

			if (user.isActif() && !user.isMdpamodifier())
			user.setMdpamodifier(true);
			String newPassword = "monetique12345";
			// encodage  
			user.setPassword(newPassword);
			user = (User) appUserData.securityUserContext(user);
			
			

			userRepository.saveAndFlush(user);

			// Envoi du mail de rÃ©initialisation du mot de passe
			mailService.envoyerMailResetPassword(user, newPassword);
		
	}

	/**
	 * Mise à jour du mot de passe par l'admin ou utilisateur.
	*/
	
	@Override
	public void updatePassword(String userName,String actuelPwd, String newPwd) throws Exception {
		//recuperer l'userà partir de son login 
		User user = userRepository.findByuserName(userName);
		if (user != null && (user.getEmail() == null || "".equals(user.getEmail())))
			throw new Exception("L'adresse mail enregistré sur ce compte n'est pas valide. Veuillez contacter votre administrateur");
		
	   //vérifier que le mot de passe ancient a été saisie 
	   if (appUserData.CheckPasswordUser(user.getId(), actuelPwd)) {
		// encodage  
			user.setPassword(newPwd);
			user.setMdpamodifier(false);
			user.setDateModPass(new Date());
			user = (User) appUserData.securityUserContext(user);
			userRepository.saveAndFlush(user);
			//envoi mail notification mdp modifié
			mailService.envoyerMailModificationMdp(user,newPwd);
	   }
	   else {
			throw new Exception("Le mot de passe  saisie n'est pas correcte. Veuillez réesayez svp");
			
	   }
		//user.setMotdepasseprecedent(user.getMotdepasseprecedent());
		
		
	}

	@Override
	public void addRuleToUser(Rule rules, long idUser) throws Exception {

		   	
		   	if(idUser==0 && rules==null ) throw new Exception("Id rôle ou  user non définit");
			  Optional<User> userOp=userRepository.findById(idUser);   	
		   	
		   	if(userOp.isPresent()) {
		   		
		   		User user=userOp.get();
		   		Set<Rule> listRule=user.getRules();
		   		
		   		boolean existe=false;
		   		
		   		for(Rule ac: listRule) {
		   			if(ac.getLibelle().equals(rules.getLibelle())  && ac.getId()== rules.getId()) {
		   				existe=true;
		   				break;
		   			}
		   		}
		   		
		   		if(!existe)
		   			listRule.add(rules);
		   		
		   		userRepository.saveAndFlush(user);
		   		
		   	}
			
		
	}

	@Override
	public void removeRuleToUser(Rule rules, long idUser) throws Exception {
		// TODO Auto-generated method stub
		if(idUser==0 && rules==null ) throw new Exception("Id utilisateur ou  rules non définit");
		Optional<User> userOp=userRepository.findById(idUser);		   	
		   	if(userOp.isPresent()) {
		   		
		   		User user=userOp.get();
		   		
		   		Set<Rule> listRule=user.getRules(); 	
		   		

		   		boolean existe=false;
		   		Rule supp=null;
		   		
		   		for(Rule ac: listRule) {
		   			if(ac.getLibelle().equals(rules.getLibelle())  && ac.getId()== rules.getId()) {
		   				existe=true;
		   				supp=ac;
		   				break;
		   			}
		   		}
		   		
		   		if(existe)
		   		listRule.remove(supp);
		   		
		   		userRepository.saveAndFlush(user);
		   		
			   	
		   	}
	}

	@Override
	public boolean addNewUser(User user) throws Exception {
		// TODO Auto-generated method stub
		String pwdOriginal = "monetique12345";
		if (user.getId() == null) {
			Date datec = new Date();
			try {
				// encodage  
				user.setPassword(pwdOriginal);
				user = (User) appUserData.securityUserContext(user);
				
			} catch (Exception uee) {
				throw new Exception("Erreur init password ", uee);
			}
			user.setDateCreation(datec);
			user.setDateModPass(datec);
			user.setActif(true);
			user.setMdpamodifier(true);
			
		}
		if (user.getId() != null && user.getPassword() != null){
		User tmp = userRepository.getOne(user.getId());
		if (!tmp.getPassword().equals(user.getPassword())){
			
			try {
				String strDate = DateUtils.formatDateAll(user.getDateCreation());
				String passwordToEncode = user.getPassword().concat(strDate);
				String pwd = Password.getEncodedPasswordWithoutCharset(passwordToEncode);
				user.setPassword(pwd);
			} catch (UnsupportedEncodingException uee) {
				throw new Exception("Erreur init password ", uee);
			}
		}
		}
		
		User u = userRepository.saveAndFlush(user);
		if (u != null) {
		
			return true;
		}
		return false;
	}




}
