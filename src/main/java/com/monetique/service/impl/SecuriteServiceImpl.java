package com.monetique.service.impl;


import java.security.PublicKey;
import java.util.Optional;

import javax.crypto.Cipher;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monetique.entities.CarteStock;
import com.monetique.entities.Parametrage;
import com.monetique.model.helper.GestionClesRSA;
import com.monetique.repositories.ParametrageRepository;
import com.monetique.service.SecuriteService;

@Service
public class SecuriteServiceImpl implements SecuriteService {

	@Autowired
	ParametrageRepository parametrageRepository;
	
	public static String KEY="digital_api_12345@!";
	
	@Override
	public CarteStock setCryptageCarte(CarteStock c) throws Exception {

		String recharge= c.getCodeSecret();
		
		PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
		encryptor.setPoolSize(4);
		encryptor.setPassword(KEY);
		encryptor.setAlgorithm("PBEWithMD5AndTripleDES");
		String encryptedText = encryptor.encrypt(recharge);
		c.setCodeSecret(encryptedText);
		return c;
		
	}

	@Override
	public CarteStock setDecryptageCarte(CarteStock c) throws Exception {
		
		String recharge= c.getCodeSecret();
		
		PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
		encryptor.setPoolSize(4);
		encryptor.setPassword(KEY);
		encryptor.setAlgorithm("PBEWithMD5AndTripleDES");
		String plainText = encryptor.decrypt(recharge);
		
        c.setCodeSecret(plainText);		
		return c;
	}

	@Override
	public String setCryptageLigne(String ligne)  {
        
		Optional<Parametrage> optOp= parametrageRepository.findById(1L);
		Parametrage parametrage= optOp.get();

		 // Recuperation de la cle publique
        PublicKey clePublique = GestionClesRSA.lectureClePublique(parametrage.getUrlClePublique());
		
        // Chiffrement du message
        byte[] bytes = null;
        try {
            Cipher chiffreur = Cipher.getInstance("RSA");
            chiffreur.init(Cipher.ENCRYPT_MODE, clePublique);
            bytes = chiffreur.doFinal(ligne.getBytes());
        } catch(Exception e) {
            System.err.println("Erreur lors du chiffrement : " + e);
        }
        
        String s = new String(bytes);
        return s;
	}

	/*@Override
	public String setDecryptageLigne(String ligne) {
		
		Optional<Parametrage> optOp= parametrageRepository.findById(1L);
		
		Parametrage parametrage= optOp.get();

        PrivateKey clePrivee = GestionClesRSA.lectureClePrivee();
		return null;
		
	}*/


}
