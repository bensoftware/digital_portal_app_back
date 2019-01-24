package com.monetique;

import java.text.ParseException;

import org.springframework.stereotype.Component;

@Component
public class Teste {

	
	public static void main(String[] args) throws ParseException  {
		
	/*	String dd="010617";
		
	    DateFormat df=new SimpleDateFormat("ddMMyy");

	    Date date=df.parse(dd);
	    System.out.println(date.toGMTString());*/
/*	    
	String dd="13112017";
		
	    DateFormat df=new SimpleDateFormat("ddMMyyyy");

	    Date date=df.parse(dd);
	    System.out.println(date.toGMTString());*/
	    
	    
	    String m="53034711458874521";
	    
	     System.out.println(m.indexOf("530347"));  
	    
	    /*int size= m.length();
	    
	    String res= m.substring(0, size-2);
	    String res2= m.substring(size-2, size);
	    System.out.println(res);
	    System.out.println(res2);
	    
	    String montant = res+"."+res2;
	    Double mm= Double.parseDouble(montant);
	    
	    System.out.println(mm);*/
	}

}
