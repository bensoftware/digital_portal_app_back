package com.monetique.helper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import com.monetique.dto.ClearingDto;
import com.monetique.entities.Clearing;
import com.monetique.entities.ClearingRejeter;

public class ClearingHelper {

	
	static Stream<String> line;
	static DateFormat df=new SimpleDateFormat("ddMMyy");
	
	public static ClearingDto getClearingDtoItem(String item) throws ParseException {
		ClearingDto c= new ClearingDto();
		
		c.setTypeEnregistrement(mySubString(item,1,2));
		c.setNumeroDeSerie(mySubString(item,3,6));
		c.setCodeOperation(mySubString(item,9,5));
		
		
		String dateP=mySubString(item,14,6);
		try {
			Date date=df.parse(dateP);
			c.setDateDeProcessing(date);
		} catch (Exception e) {
			c.setDateDeProcessing(null);
		}
        
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
		c.setMonnaie(mySubString(item,249,3));
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
		
		String montantSellment=null;
		String deviseSellment=null;
		String reste=null;
		
		
		try {
			 montantSellment=mySubString(item,413,15);
			 deviseSellment=mySubString(item,428,3);
			 reste =mySubString(item,431,item.length()-430);
			
		} catch (Exception e) {
			// TODO: handle exception
			 reste =mySubString(item,413,item.length()-412);
		}
	
		c.setMontantSetllment(montantSellment);
		c.setDeviseSellment(deviseSellment);
		c.setReste(reste);		
		return c;
	}
	
	
	public static Iterator<String> getListOprerationPreIntGiMTELFile(String file,String in) throws IOException{
		
		 
		 Path path =Paths.get(in+"\\"+file);;
		 line = Files.lines(path,StandardCharsets.ISO_8859_1);
		
		Iterator<String> it= null;

		try {
			it= line.iterator();
		} catch (Exception e) {
			// TODO: handle exception
		}
			return it;
	}

	public static String getCorrespondanceAnomalieClearing(int etat) throws ParseException {
		
		String res= null;
		
		switch (etat) {
		case -1:
			res="Pas d'anomalie";
			break;
		/*case 1:
			res="Sans reference autorisation";
			break;
		case 2:
			res="Sans numéro autorisation";
			break;
		case 3:
			res="Sans montant de transaction";
			break;*/
		case 4:
			res="monnaie MRO";
			break;
		case 5:
			res="Commission sur la Carte Mauriposte";
			break;
	/*	case 6:
			res="Sans montant Settlment";
			break;*/
			
		default:
			break;
		}
		
		
		
		
		return res;
	}
	
	
	public static String getCorrespondanceCorrectionClearing(int etat) throws ParseException {
		
		String res= null;
		
		switch (etat) {
		case -1:
			res="Non modifiée";
			break;
		/*case 1:
			res="Sans reference autorisation";
			break;
		case 2:
			res="Sans numéro autorisation";
			break;
		case 3:
			res="Sans montant de transaction";
			break;*/
		case 1:
			res="Modifiée";
			break;
		case 2:
			res="Modifiée et corrigée";
			break;
	/*	case 6:
			res="Sans montant Settlment";
			break;*/
			
		default:
			break;
		}
		
		
		
		
		return res;
	}
	
public static List<Path> getAllFilesNamesGIMTEL(String filename) throws IOException {
		
		List<Path> list=new ArrayList<>();
		
		 Path path = Paths.get(filename);
		 try(DirectoryStream<Path> listing = Files.newDirectoryStream(path)){
			 
           for(Path file : listing){
        	   if(Files.isRegularFile(file)) {
        		   list.add(file);
        	   }
				 
			 }
			
			 return list;
		 }
	}

public static Clearing getClearingItem(String item) throws ParseException {
	Clearing c= new Clearing();
	
	c.setTypeEnregistrement(mySubString(item,1,2));
	c.setNumeroDeSerie(mySubString(item,3,6));
	c.setCodeOperation(mySubString(item,9,5));
	
	
	String dateP=mySubString(item,14,6);
	try {
		Date date=df.parse(dateP);
		c.setDateDeProcessing(date);
	} catch (Exception e) {
		c.setDateDeProcessing(null);
	}
    
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
	
	String montantSellment=null;
	String deviseSellment=null;
	String reste=null;
	
	
	try {
		 montantSellment=mySubString(item,413,15);
		 deviseSellment=mySubString(item,428,3);
		 reste =mySubString(item,431,item.length()-430);
		
	} catch (Exception e) {
		// TODO: handle exception
		 reste =mySubString(item,413,item.length()-412);
	}

	c.setMontantSetllment(montantSellment);
	c.setDeviseSellment(deviseSellment);
	c.setReste(reste);

	return c;
}

private static String mySubString(String myString, int start, int length) {
	int index=start-1;
    return myString.substring(index, Math.min(index + length, myString.length()));
}

public static Iterator<String> getListTransactionGiMTELFile(String file,String in) throws IOException{
	
	 
	 Path path =Paths.get(in+"\\"+file);;
	 line = Files.lines(path,StandardCharsets.ISO_8859_1);
	
	Iterator<String> it= null;

	try {
		it= line.iterator();
	} catch (Exception e) {
		// TODO: handle exception
	}
		return it;
}

public static void moveFileGiMTELException(String name,String in, String out) throws IOException {
	
		Path file = Paths.get(in+"\\"+name);
		 Path path = Paths.get(out+"\\"+name);
		 
		 if(Files.isRegularFile(file)) {
			 // deplacer vers exception
			 Files.move(file, path, StandardCopyOption.REPLACE_EXISTING);
		  }	
	}

public static void moveFileGiMTEL(String name,String in,String out) throws IOException {
	
		Path file = Paths.get(in+"\\"+name);
		 Path path = Paths.get(out+"\\"+name);
		 
		 if(Files.isRegularFile(file)) {
			 // deplacer vers exception
			 Files.move(file, path, StandardCopyOption.REPLACE_EXISTING);
		  }	
	}

public static ClearingRejeter getClearingRejeterByCl(Clearing i) {
	ClearingRejeter c= new ClearingRejeter();
	
	c.setIndex(i.getIndex());
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
	
	c.setMontantSetllment(i.getMontantSetllment());
	c.setDeviseSellment(i.getDeviseSellment());
	c.setReste(i.getReste());
	
	return c;
}

}
