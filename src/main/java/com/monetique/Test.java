package com.monetique;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.monetique.service.LogService;

@Component
public class Test implements CommandLineRunner {

	@Autowired
	private LogService logService;
	
	@Override
	public void run(String... args) throws Exception {
		Date myDate = new Date(2014-1900,02-1,11);
		Date myDate2 = new Date(2022-1900,04-1,26);
		
		//logService.getAllLog();
		System.err.println("List : " + logService.getLogByPhoneAndDate("37818077",myDate,myDate2));
	}

}
