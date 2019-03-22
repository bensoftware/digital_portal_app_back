package com.monetique.model.helper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.monetique.entities.Notification;

public class DateHelper {

	
	//static DateFormat df=new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
	
	public static Date getDateFormat(String date) throws ParseException {

		DateFormat df=new SimpleDateFormat("dd/MM/yyyy");

	    return df.parse(date);
	}
	
	public static boolean getDelaiStatusNotf(Notification c) {
		
		boolean res=false;
	
	//	double jour= 86400; // seconde
		
		if(c.getStatus()==1 || c.getStatus()==0) {
			
			Calendar cDate= Calendar.getInstance();
			cDate.setTime(c.getDate());
			// incrementation par un jour
			cDate.add(Calendar.DATE, 1); 

			Calendar cActuel=Calendar.getInstance();
			
			 if(cActuel.after(cDate)){
				 res=true;	                
	            }
		}
		
		return res;
	}
	
	
}
