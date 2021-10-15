package com.monetique.helper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateHelper {
	
	public static Date getDateSansHeure(Date d) throws ParseException {
   	 DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); 
   	 Date res = formatter.parse(formatter.format(d));
   	 return res;
   }
	
public static Date getDateDu(Date index) {
    	
    	Calendar cIndex= Calendar.getInstance();
		cIndex.setTime(index);
		cIndex.add(Calendar.DATE, 1);
		
		return cIndex.getTime();
    	
    }

public static Date getDateAu(Date index) {
	
	Calendar cIndex= Calendar.getInstance();
	cIndex.setTime(index);
	cIndex.add(Calendar.DATE, -1);
	
	return cIndex.getTime();
	
}

}
