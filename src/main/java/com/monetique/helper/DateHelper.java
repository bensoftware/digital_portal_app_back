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

public static String getDateDir(Date d) {
	String res="";
	
	Date date = d;
	int annee= date.getYear()+1900;
	int mois= date.getMonth()+1;
	int jour=date.getDate();
	
	String jour2,mois2;
	
	if(jour<10)
		jour2="0"+jour;
	else
		jour2=""+jour;
	
	if(mois<10)
		mois2="0"+mois;
	else
		mois2=""+mois;
	
	
	res=jour2+"-"+mois2+"-"+annee;
	
	return res;
}

}
