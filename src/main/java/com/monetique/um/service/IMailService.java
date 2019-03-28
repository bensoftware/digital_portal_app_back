package com.monetique.um.service;

import org.springframework.stereotype.Service;

import com.monetique.um.dao.entities.User;

/**
 * Interface d'envoie des mails aux Utilisateurs.
 *
 * @author bpm digitale
 *
 */
@Service
public interface IMailService {

    public void envoyerMailCreationUtilisateur(User user, String pwdOriginal) throws Exception;
	
	public void envoyerMailModificationMdp(User user, String pwdOriginal) throws Exception;
	
	public void envoyerMailResetPassword(User user, String pwd) throws Exception;
}
