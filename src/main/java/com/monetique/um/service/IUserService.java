package com.monetique.um.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.monetique.um.dao.entities.Groupe;
import com.monetique.um.dao.entities.Rule;
import com.monetique.um.dao.entities.User;

@Service
public interface IUserService {
	public boolean addUser(User User) throws Exception;
	public boolean addNewUser(User User) throws Exception;
	public void updateUser(User user) throws Exception;
	public List<User> getAllUser() throws Exception;
	public User getUser(long id) throws Exception;
	public void deleteUser(Long id) throws Exception;
	public void addRulesToUser(List<Rule> rules,long idUser) throws Exception;
	public void removeRulesToUser(List<Rule> rules,long idUser) throws Exception;
	public void addRuleToUser(Rule rules,long idUser) throws Exception;
	public void removeRuleToUser(Rule rules,long idUser) throws Exception;
	public void changeStatusUser(User user) throws Exception;
	public long existsEmail(String email) throws Exception;
	public long existsEmail(String email, Long idUtilisateur) throws Exception;
	public void resetPassword(User user) throws Exception;
	public void updatePassword(String userName, String actuelPwd, String newPwd) throws Exception;
	public void addGroupeToUser(Groupe groupe,long idUser) throws Exception;
	public void removeGroupeToUser(Groupe groupe,long idUser) throws Exception;
}
