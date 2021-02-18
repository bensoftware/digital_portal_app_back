package com.monetique.um.helper;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import com.monetique.dto.Client;


public class CommercialHelper {
	 static DateFormat mediumDateFormat = DateFormat.getDateTimeInstance(
		        DateFormat.MEDIUM,
		        DateFormat.MEDIUM);
	public static String getSuspiciousMessage(Client client ,String userName, String host) {
		String msg;
		
	 	msg="ALERT-BLACK LIST \nTel: "+client.getTelephone()+"\nCIF: "+client.getCif()+"\nNNI: "+client.getNni()+"\nConsulté Par:\nUser: "+userName+"\nHost: "+host+"\nLe: "+mediumDateFormat.format(new Date());
		return	 msg;
	}
}
