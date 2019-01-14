package com.monetique;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class Teste {

	
	public static void main(String[] args) throws ParseException  {
		
		String dd="010617";
		
	    DateFormat df=new SimpleDateFormat("ddMMyy");

	    Date date=df.parse(dd);
	    System.out.println(date.toGMTString());
	}

}
