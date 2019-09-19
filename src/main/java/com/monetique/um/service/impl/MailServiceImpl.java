package com.monetique.um.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.monetique.service.MailNotificationService;
import com.monetique.um.dao.entities.User;
import com.monetique.um.service.IMailService;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Service
public class MailServiceImpl implements IMailService{
	
	@Autowired
    private JavaMailSender sender;
    
    @Autowired
    private Configuration freemarkerConfig;

	
	private String urlApp="http://127.0.0.1:4200";

	@Override
	public void envoyerMailCreationUtilisateur(User user, String pwdOriginal) throws Exception {
		MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        Map<String, Object> model = new HashMap();
        model.put("url", urlApp);
        model.put("nomUser", user.getNom());
        model.put("prenomUser", user.getPrenom());
        model.put("login", user.getUserName());
        model.put("motdepasse", pwdOriginal);
        freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/");
        Template t;
		try {
			t = freemarkerConfig.getTemplate("velocity/creation-user.ftl");
			 String text;
			try {
				text = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
				try {
					helper.setText(text, true);
				} catch (MessagingException e) {
					e.printStackTrace();
				} // set to html
			} catch (IOException | TemplateException e) {
				e.printStackTrace();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}		
        try {
			helper.setFrom(new InternetAddress("cheiknarim@esp.sn", "no-reply@gmail.com"));
		} catch (UnsupportedEncodingException | MessagingException e) {
			e.printStackTrace();
		}
        try {
			helper.setTo(user.getEmail());
		} catch (MessagingException e) {
			e.printStackTrace();
		}
        
        try {
			helper.setSubject("Invitation sur l'application de gestion des réclamations ");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
        sender.send(message);
	}

	@Override
	public void envoyerMailModificationMdp(User user, String pwdOriginal) throws Exception {
		MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        Map<String, Object> model = new HashMap();
        model.put("url", urlApp);
        model.put("nomUser", user.getNom());
        model.put("prenomUser", user.getPrenom());
        model.put("login", user.getUserName());
        model.put("motdepasse", pwdOriginal);
        freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/");
        Template t;
		try {
			t = freemarkerConfig.getTemplate("velocity/mdp_modifier.ftl");
			 String text;
			try {
				text = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
				try {
					helper.setText(text, true);
				} catch (MessagingException e) {
					e.printStackTrace();
				} // set to html
			} catch (IOException | TemplateException e) {
				e.printStackTrace();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}		
        try {
			helper.setFrom(new InternetAddress("bankily@bpm.mr", "no-reply@bpm.mr"));
		} catch (UnsupportedEncodingException | MessagingException e) {
			e.printStackTrace();
		}
        try {
			helper.setTo(user.getEmail());
		} catch (MessagingException e) {
			e.printStackTrace();
		}
        
        try {
			helper.setSubject("Modification de votre mot de passe ");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
        sender.send(message);
	}

	@Override
	public void envoyerMailResetPassword(User user, String pwd) throws Exception {
		MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        Map<String, Object> model = new HashMap();
        model.put("url", urlApp);
        model.put("nomUser", user.getNom());
        model.put("prenomUser", user.getPrenom());
        model.put("login", user.getUserName());
        model.put("motdepasse", pwd);
        freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/");
        Template t;
		try {
			t = freemarkerConfig.getTemplate("velocity/motDePassOublie.ftl");
			 String text;
			try {
				text = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
				try {
					helper.setText(text, true);
				} catch (MessagingException e) {
					e.printStackTrace();
				} // set to html
			} catch (IOException | TemplateException e) {
				e.printStackTrace();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}		
        try {
			helper.setFrom(new InternetAddress("cheiknarim@esp.sn", "no-reply@gmail.com"));
		} catch (UnsupportedEncodingException | MessagingException e) {
			e.printStackTrace();
		}
        try {
			helper.setTo(user.getEmail());
		} catch (MessagingException e) {
			e.printStackTrace();
		}
        
        try {
			helper.setSubject("Réinitialisation de votre mot de passe ");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
        sender.send(message);
	}

	
	private String convertDoubleToString(Double d) {
		NumberFormat fmt = NumberFormat.getInstance(); 
		fmt.setGroupingUsed(false); 
		fmt.setMaximumIntegerDigits(999); 
		fmt.setMaximumFractionDigits(999);
		return fmt.format(d);
	}
	
}
