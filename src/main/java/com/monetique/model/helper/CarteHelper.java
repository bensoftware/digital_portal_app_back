package com.monetique.model.helper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.stream.Stream;

import com.monetique.entities.CarteStock;
import com.monetique.entities.CarteUtilise;
import com.monetique.entities.TypeMontant;

public class CarteHelper {

	
	static Stream<String> line;
	static DateFormat df=new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
	
	
	public static CarteUtilise getCarteUtiliserByCarteStock(CarteStock c) {
		
		CarteUtilise cc= new CarteUtilise();
		
		cc.setCleNumeroSerie(c.getCleNumeroSerie());
		cc.setNumeroCarte(c.getNumeroCarte());
		cc.setCodeSecret(c.getCodeSecret());
		cc.setDateExpiration(c.getDateExpiration());
		cc.setId(c.getId());
		cc.setTypeMontant(c.getTypeMontant());
		
		return cc;
		
	}
	
	public static CarteStock getCarteStockItem(int operator,String item) throws Exception {
		CarteStock c= new CarteStock();
		
		if(operator==1) {
			
		}
		// chinguitel
		else if(operator==2) {
			c.setCleNumeroSerie(mySubString(item,1,4));
			c.setNumeroCarte(mySubString(item,6,11));
			c.setCodeSecret(mySubString(item,18,18));
			
			String date=mySubString(item,37,19);
			try {
				Date datef=df.parse(date);
				c.setDateExpiration(datef);
			} catch (Exception e) {
				throw new Exception("date non defini");
			}
			
			Double montant=null;
			try {
				montant=Double.parseDouble(mySubString(item,57,item.length()-56));
			} catch (Exception e) {
				throw new Exception("montant non defini");
				}
			TypeMontant typeMontant= new TypeMontant();
			typeMontant.setMontant(montant);

			c.setTypeMontant(typeMontant);
		}
		
	
		return c;
	}
	
	private static String mySubString(String myString, int start, int length) {
		int index=start-1;
	    return myString.substring(index, Math.min(index + length, myString.length()));
	}
	
	public static void moveFile(String name,String repIn,String repOut) throws IOException {
		
		Path file = Paths.get(repIn+"\\"+name);
		 Path path = Paths.get(repOut+"\\"+name);
		 
		 if(Files.isRegularFile(file)) {
			 // deplacer vers exception
			 Files.move(file, path, StandardCopyOption.REPLACE_EXISTING);
		  }	
	}
	
	public static void deleteFile(String name,String rep) throws IOException {
		
		 Path path = Paths.get(rep+"\\"+name);
		 
		 if(Files.isRegularFile(path)) {
			 // suppression
			 Files.deleteIfExists(path);
		  }	
	}
	
	
	public static Iterator<String> getListCarte(String filename,String url) throws IOException{
		
		 
		 Path path =Paths.get(url+"\\"+filename);;
		 line = Files.lines(path,StandardCharsets.ISO_8859_1);
		
		Iterator<String> it= null;

		try {
			it= line.iterator();
		} catch (Exception e) {
			// TODO: handle exception
		}
			return it;
	}
	
	public static Stream<String> getStreamCarte(String filename,String url) throws IOException{
		
		 
		 Path path =Paths.get(url+"\\"+filename);;
		 return Files.lines(path,StandardCharsets.ISO_8859_1);
		
	
	}
	
}
