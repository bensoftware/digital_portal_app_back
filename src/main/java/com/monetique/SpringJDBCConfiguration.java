package com.monetique;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import secure.SecurityBD;
import secure.SecurityFactory;
 

@Configuration
public class SpringJDBCConfiguration {

	private SecurityBD encryptor=null;

	public SpringJDBCConfiguration() {
		encryptor= SecurityFactory.getInstance();
	}
	
	    @Bean
	    public DataSource dataSource(@Value("${passworddb}") String password,@Value("${usernamedb}") String username,@Value("${host}") String host) {
		  
		//  System.out.println("pass "+password+" Username "+username);
		  
	    	String pwd= decrypte(password);
	    	//String pwd=password;
	    	
		  DriverManagerDataSource dataSource = new DriverManagerDataSource();
	        //MySQL database we are using
	        dataSource.setDriverClassName("org.postgresql.Driver");
	      //  dataSource.setUrl("jdbc:postgresql://30.30.1.142:5432/phonecarddb1");
	      //  dataSource.setUrl("jdbc:postgresql://30.30.1.140:5432/phonecarddb1");//change url  jdbc:oracle:thin:@30.30.1.71:1521:IMALUAT
	        dataSource.setUrl("jdbc:postgresql://"+host);//change url  jdbc:oracle:thin:@30.30.1.71:1521:IMALUAT

	        dataSource.setUsername(username);//change userid
	        dataSource.setPassword(pwd);//change pwd	        
	 
	        return dataSource;
	    }
	  
	  
	  private String decrypte(String encode) {
			
			return encryptor.decrypte(encode);
	  }


}