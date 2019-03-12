package com.monetique.model.helper;

import java.util.Calendar;
import java.util.Date;

import com.monetique.entities.Notification;

public class DateHelper {

	
	//static DateFormat df=new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
	
	
	public static boolean getEpuisementDelaiStatusNotf(Notification c) {
		
		boolean res=false;
	
		double jour= 86400; // seconde
		
		if(c.getStatus()==1) {
			Calendar cDate=Calendar.getInstance();
			Date date= new Date((long) (c.getDate().getTime()+(jour*1000)) );
			cDate.setTime(date);
			Calendar cActuel=Calendar.getInstance();
			
			 if(cDate.after(cActuel)){
				 res=true;
	                
	            }
		}
		
		return res;
	}
	
	
}
