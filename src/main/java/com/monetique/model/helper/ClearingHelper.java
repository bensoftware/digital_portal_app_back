package com.monetique.model.helper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import com.monetique.entities.Clearing;
import com.monetique.entities.ClearingRejeter;

public class ClearingHelper {

	
	static Stream<String> line;
	
	public static List<Path> getAllFilesNames() throws IOException {
		
		List<Path> list=new ArrayList<>();
		
		 Path path = Paths.get("C:\\Clearing\\in");
		 try(DirectoryStream<Path> listing = Files.newDirectoryStream(path)){
			 
           for(Path file : listing){
        	   if(Files.isRegularFile(file)) {
        		   list.add(file);
        	   }
				 
			 }
			
			 return list;
		 }
	}
	
	public static List<Path> getAllFilesNamesGIMTEL() throws IOException {
		
		List<Path> list=new ArrayList<>();
		
		 Path path = Paths.get("C:\\ClearingApp\\Gimtel\\in");
		 try(DirectoryStream<Path> listing = Files.newDirectoryStream(path)){
			 
           for(Path file : listing){
        	   if(Files.isRegularFile(file)) {
        		   list.add(file);
        	   }
				 
			 }
			
			 return list;
		 }
	}
	
	public static List<Path> getAllFilesNamesSS() throws IOException {
		
		List<Path> list=new ArrayList<>();
		
		 Path path = Paths.get("C:\\ClearingApp\\SS\\in");
		 try(DirectoryStream<Path> listing = Files.newDirectoryStream(path)){
			 
           for(Path file : listing){
        	   if(Files.isRegularFile(file)) {
        		   list.add(file);
        	   }
				 
			 }
			
			 return list;
		 }
	}
/*	public static DirectoryStream<Path> getAllFilesNames() throws IOException {
		 Path path = Paths.get("C:\\Clearing\\in");
		 DirectoryStream<Path> listing = Files.newDirectoryStream(path);
	     return listing;
	}*/
	
	
	public static void moveFile(String name) throws IOException {
		
		Path file = Paths.get("C:\\ClearingApp\\in\\"+name);
		 Path path = Paths.get("C:\\ClearingApp\\out\\"+name);
		 
		 if(Files.isRegularFile(file)) {
			 // deplacer vers exception
			 Files.move(file, path, StandardCopyOption.REPLACE_EXISTING);
		  }	
	}
	
  public static void moveFileGiMTEL(String name) throws IOException {
		
		Path file = Paths.get("C:\\ClearingApp\\Gimtel\\in\\"+name);
		 Path path = Paths.get("C:\\ClearingApp\\Gimtel\\archive\\"+name);
		 
		 if(Files.isRegularFile(file)) {
			 // deplacer vers exception
			 Files.move(file, path, StandardCopyOption.REPLACE_EXISTING);
		  }	
	}
  
  public static void moveFileGiMTELException(String name) throws IOException {
		
		Path file = Paths.get("C:\\ClearingApp\\Gimtel\\in\\"+name);
		 Path path = Paths.get("C:\\ClearingApp\\Gimtel\\exception\\"+name);
		 
		 if(Files.isRegularFile(file)) {
			 // deplacer vers exception
			 Files.move(file, path, StandardCopyOption.REPLACE_EXISTING);
		  }	
	}
  
  public static void moveFileSS(String name) throws IOException {
		
		Path file = Paths.get("C:\\ClearingApp\\SS\\in\\"+name);
		 Path path = Paths.get("C:\\ClearingApp\\SS\\archive\\"+name);
		 
		 if(Files.isRegularFile(file)) {
			 // deplacer vers exception
			 Files.move(file, path, StandardCopyOption.REPLACE_EXISTING);
		  }	
	}
  
  public static void moveFileSSException(String name) throws IOException {
		
		Path file = Paths.get("C:\\ClearingApp\\SS\\in\\"+name);
		 Path path = Paths.get("C:\\ClearingApp\\SS\\exception\\"+name);
		 
		 if(Files.isRegularFile(file)) {
			 // deplacer vers exception
			 Files.move(file, path, StandardCopyOption.REPLACE_EXISTING);
		  }	
	}
	
	 
	
	private static String mySubString(String myString, int start, int length) {
		int index=start-1;
	    return myString.substring(index, Math.min(index + length, myString.length()));
	}
	
	public static ClearingRejeter getClearingRejeterByCl(Clearing i) {
		ClearingRejeter c= new ClearingRejeter();
		
		c.setTypeEnregistrement(i.getTypeEnregistrement());
		c.setNumeroDeSerie(i.getNumeroDeSerie());
		c.setCodeOperation(i.getCodeOperation());
		c.setDateDeProcessing(i.getDateDeProcessing());
		c.setNumeroDeCommercant(i.getNumeroDeCommercant());
		c.setNumeroDuTerminal(i.getNumeroDuTerminal());
		c.setCompteCommercant(i.getCompteCommercant());
		c.setNomCommercant(i.getNomCommercant());
		c.setLocationCommercant(i.getLocationCommercant());
		c.setTerritoire(i.getTerritoire());
		c.setPan(i.getPan());
		c.setNumeroComptePorteur(i.getNumeroComptePorteur());
		c.setDateDexpiration(i.getDateDexpiration());
		c.setFlagDopposition(i.getFlagDopposition());
		c.setReferenceTransaction(i.getReferenceTransaction());
		c.setNumeroDeRemise(i.getNumeroDeRemise());
		c.setDateRemise(i.getDateRemise());
		c.setNumeroTransaction(i.getNumeroTransaction());
		c.setDateDeTransaction(i.getDateDeTransaction());
		c.setNumeroAutorisation(i.getNumeroAutorisation());
		c.setMontantDautorisation(i.getMontantDautorisation());
		c.setMontantTransactionGross(i.getMontantTransactionGross());
		c.setMonnaie(i.getMonnaie());
		c.setExposantMonnaie(i.getExposantMonnaie());
		c.setFraisInterchange(i.getFraisInterchange());
		c.setSigneFraisInterchange(i.getSigneFraisInterchange());
		c.setCommissionFraisPorteur(i.getCommissionFraisPorteur());
		c.setMontantNetCreditCommercant(i.getMontantNetCreditCommercant());
		c.setCommissionCommercantCredit(i.getCommissionCommercantCredit());
		c.setCodeBanqueEmettrice(i.getCodeBanqueEmettrice());
		c.setCodeBanqueAcquereur(i.getCodeBanqueAcquereur());
		c.setFraisConversion(i.getFraisConversion());
		c.setCodeCategirieCommercant(i.getCodeCategirieCommercant());
		c.setCodeSysteme(i.getCodeSysteme());
		c.setFraisCentre(i.getFraisCentre());
		c.setStatusTransaction(i.getStatusTransaction());
		c.setNotUsed(i.getNotUsed());
		c.setReferenceAutorisation(i.getReferenceAutorisation());
		
		return c;
	}
	
	public static Clearing getClearingItem(String item) {
		Clearing c= new Clearing();
		
		c.setTypeEnregistrement(mySubString(item,1,2));
		c.setNumeroDeSerie(mySubString(item,3,6));
		c.setCodeOperation(mySubString(item,9,5));
		c.setDateDeProcessing(mySubString(item,14,6));
		c.setNumeroDeCommercant(mySubString(item,20,15));
		c.setNumeroDuTerminal(mySubString(item,35,8));
		c.setCompteCommercant(mySubString(item,43,24));
		c.setNomCommercant(mySubString(item,67,25));
		c.setLocationCommercant(mySubString(item,92,25));
		c.setTerritoire(mySubString(item,117,1));
		c.setPan(mySubString(item,118,19));
		c.setNumeroComptePorteur(mySubString(item,137,24));
		c.setDateDexpiration(mySubString(item,161,4));
		c.setFlagDopposition(mySubString(item,165,1));
		c.setReferenceTransaction(mySubString(item,166,23));
		c.setNumeroDeRemise(mySubString(item,189,6));
		c.setDateRemise(mySubString(item,195,6));
		c.setNumeroTransaction(mySubString(item,201,6));
		c.setDateDeTransaction(mySubString(item,207,6));
		c.setNumeroAutorisation(mySubString(item,213,6));
		c.setMontantDautorisation(mySubString(item,219,15));
		c.setMontantTransactionGross(mySubString(item,234,15));
		c.setMonnaie(mySubString(item,249,3));
		c.setExposantMonnaie(mySubString(item,252,1));
		c.setFraisInterchange(mySubString(item,253,15));
		c.setSigneFraisInterchange(mySubString(item,268,1));
		c.setCommissionFraisPorteur(mySubString(item,269,15));
		c.setMontantNetCreditCommercant(mySubString(item,284,15));
		c.setCommissionCommercantCredit(mySubString(item,299,15));
		c.setCodeBanqueEmettrice(mySubString(item,314,5));
		c.setCodeBanqueAcquereur(mySubString(item,319,5));
		c.setFraisConversion(mySubString(item,324,15));
		c.setCodeCategirieCommercant(mySubString(item,339,4));
		c.setCodeSysteme(mySubString(item,343,1));
		c.setFraisCentre(mySubString(item,344,15));
		c.setStatusTransaction(mySubString(item,359,1));
		c.setNotUsed(mySubString(item,360,41));
		c.setReferenceAutorisation(mySubString(item,401,12));

	//	CompositeKey key=new CompositeKey(c.getReferenceTransaction(), c.getNumeroAutorisation());

	//	c.setKey(key);
		return c;
	}
	
	public static void closingStram() throws IOException{
	 
		line.close();

     }

	public static Iterator<String> getList(String file) throws IOException{
		
		 
		 Path path =Paths.get("C:\\Clearing\\in\\"+file);;
		 line = Files.lines(path,StandardCharsets.ISO_8859_1);
		
		Iterator<String> it= null;

		try {
			it= line.iterator();
		} catch (Exception e) {
			// TODO: handle exception
		}
			return it;
	}
	
	public static Iterator<String> getListTransactionGiMTELFile(String file) throws IOException{
		
		 
		 Path path =Paths.get("C:\\ClearingApp\\Gimtel\\in\\"+file);;
		 line = Files.lines(path,StandardCharsets.ISO_8859_1);
		
		Iterator<String> it= null;

		try {
			it= line.iterator();
		} catch (Exception e) {
			// TODO: handle exception
		}
			return it;
	}
	
	public static Iterator<String> getListTransactionSSFile(String file) throws IOException{
		
		 
		 Path path =Paths.get("C:\\ClearingApp\\SS\\in\\"+file);;
		 line = Files.lines(path,StandardCharsets.ISO_8859_1);
		
		Iterator<String> it= null;

		try {
			it= line.iterator();
		} catch (Exception e) {
			// TODO: handle exception
		}
			return it;
	}
}
