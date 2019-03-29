package com.monetique.um.service;

import org.springframework.stereotype.Service;

import com.monetique.entities.MontantNotification;
import com.monetique.um.dao.entities.User;

/**
 * Interface d'envoie des mails aux Utilisateurs.
 *
 * @author bpm digitale
 *
 */
@Service
public interface IMailService {

    public void envoyerMailAlertExpiration(MontantNotification m,int type) throws Exception;

    public void envoyerMailAlertEpuisement(MontantNotification m,int type) throws Exception;

    public void envoyerMailCreationUtilisateur(User user, String pwdOriginal) throws Exception;
	
	public void envoyerMailModificationMdp(User user, String pwdOriginal) throws Exception;
	
	public void envoyerMailResetPassword(User user, String pwd) throws Exception;
}
