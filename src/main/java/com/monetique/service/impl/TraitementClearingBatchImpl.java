package com.monetique.service.impl;


import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monetique.dto.ClearingDto;
import com.monetique.dto.ItemBatch;
import com.monetique.dto.ItemInfo;
import com.monetique.dto.ResponseCl;
import com.monetique.dto.ResponseClDto;
import com.monetique.entities.Clearing;
import com.monetique.entities.ClearingFile;
import com.monetique.entities.ClearingRejeter;
import com.monetique.entities.Parametrage;
import com.monetique.helper.BatchHelper;
import com.monetique.helper.ClearingHelper;
import com.monetique.helper.CorrespondanteCodeHelper;
import com.monetique.repositories.ClearingFileRepository;
import com.monetique.repositories.ClearingRejeterRepository;
import com.monetique.repositories.ClearingRepository;
import com.monetique.repositories.ParametrageRepository;
import com.monetique.service.TraitementClearingBatchService;

@Service
public class TraitementClearingBatchImpl implements TraitementClearingBatchService {

	@Autowired
	ClearingRepository clearingRepository ;
	
	@Autowired
	ClearingRejeterRepository clearingRejeterRepository ;
	
	@Autowired
	ParametrageRepository parametrageRepository;

	@Autowired
	ClearingFileRepository clearingFileRepository;
	

	@Override
	public void removeFile(String filename) {
		// TODO Auto-generated method stub
		try {
			List<Clearing> list1=clearingRepository.getClearingByfile(filename);
			List<ClearingRejeter> list2=clearingRejeterRepository.getClearingByfile(filename);

			clearingRepository.deleteAll(list1);
			clearingRejeterRepository.deleteAll(list2);
			
			clearingFileRepository.deleteById(filename);
		} catch (Exception e) {
			// TODO: handle exception
		}

		
	}

	@Override
	public List<ItemBatch> getIntegClearingFiles(String filename) throws Exception {

		 
		List<ItemBatch> res= new ArrayList<>();
		 
		boolean isExist=false;
	    boolean isNoNomenclature=false;
	    Optional<ClearingFile>  clearingFile;
	    Iterator<String> list= null;
	    ClearingFile file=null;
	    
	   	 Parametrage p=parametrageRepository.findById(1L).get();

	    
	    final DateFormat df=new SimpleDateFormat("ddMMyy");

	    
	  	String[] tab= filename.split("\\.");
    	String dd=tab[1];
    	
        clearingFile=clearingFileRepository.findById(filename);

        if(clearingFile.isPresent()) {
    		System.out.println("file deja integré "+filename);
    		isExist=true;
    		res.add(new ItemBatch(filename, BatchHelper.getCorrespondanceEtatBatch(1), false));
            return res;
        }else {
    		
    		// test de la nomenclature
    		if(!filename.matches("BCLEAR\\.[0-9]{6}\\.[0-9]+\\.[0-9]+")) {
               isNoNomenclature=true;
               res.add(new ItemBatch(filename, BatchHelper.getCorrespondanceEtatBatch(2), false));
               return res;
    		}
    		else {
        		 file= clearingFileRepository.save(new ClearingFile(filename));
    		}
    		
    	}
        
   		  try {
   	        	list=   ClearingHelper.getListTransactionGiMTELFile(filename,p.getUrlInGimtel());
   			} catch (IOException e) {
   				try {
   					ClearingHelper.moveFileGiMTELException(filename,p.getUrlInGimtel(),p.getUrlOutGimtel());
   				} catch (IOException e1) {
   					// TODO Auto-generated catch block
   					e1.printStackTrace();
   				}
   				
   			}

        
     	int index=1;
    	if(list.hasNext()) {
    		
        	Clearing cl=ClearingHelper.getClearingItem(list.next());
        	cl.setIndex(index);
    		index++;
    		
        	
        	// pour les date introuvale
        	Date date=null;
       	    try {
       	    	date=df.parse(dd);
       	    	if(cl.getDateDeProcessing()==null) {
       	    		cl.setDateDeProcessing(date);
       	    	}else {
       	    		if(!cl.getDateDeProcessing().equals(date)) {
       	    			cl.setDateDeProcessing(date);
       	    		}
       	    	}
       		} catch (Exception e) {
       			// TODO: handle exception
       		}
       	    
       	    cl.setClearingFile(file);
       	    clearingRepository.save(cl);
    	}
      	 res.add(new ItemBatch(filename, BatchHelper.getCorrespondanceEtatBatch(0), true));

      	 // deplacer vers archive
     	
         try {
 			ClearingHelper.moveFileGiMTEL(filename,p.getUrlInGimtel(),p.getUrlOutGimtel());
 		} catch (IOException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
      	 
        return res;
	}
	
	@Override
	public List<String> getListClearingFiles() throws Exception {
		// TODO Auto-generated method stub
		List<String> res= new ArrayList<>();
		
		Parametrage parametrage=parametrageRepository.findById(1L).get();

		Path path = Paths.get(parametrage.getUrlInGimtel());
		
		try(DirectoryStream<Path> listing = Files.newDirectoryStream(path)){

			    for(Path file : listing){

			    	Path clearing= file;
			    	if(Files.isRegularFile(clearing)) {
			    		
			    		String filename=file.getFileName().toString();
			    		
			    		if(filename.matches("BCLEAR.+")) {
			    			res.add(filename);
			    		}
			    		
			    	}
			    }
					
			  } catch (IOException e) {
			    e.printStackTrace();
			  }	
		
		return res;
	}

	


	@Override
	public ResponseClDto getConsultationCompense(Date date) throws Exception {
		
		
		
		List<ItemInfo> infrmationDebit=new ArrayList<>();
		
		List<ItemInfo> infrmationCredit=new ArrayList<>();
		
		String tdate=getDateString(date);
	    DateFormat df=new SimpleDateFormat("ddMMyyyy");

		Date du=df.parse(tdate);
		
		List<Clearing> clearings= clearingRepository.getClearingPerso3(du);
		
		boolean succes=true;

		if(clearings==null || clearings.size()==0) {
			succes=false;
			
		 return new ResponseClDto(infrmationDebit, infrmationCredit,0, succes);

		}
		
		
		// debit
		
		double montantRetraitBpmOtherBank=0;
		double fraisCentreClientBPM=0;
		double fraisCentreAchatTPE=0;

		double fraisInterOtherBank=0;		
		double montantAchatClientBPMsurTPE=0;
		
		double fraisRecalculPIN=0;
		double fraisPersonnalisation=0;
		
		
        // credit
		double montantRetraitOtherBankBpm=0;
		double fraisInterBPM=0;
		double montantNetCommercant=0;
		double commissionBank=0;


		
		double totalMontantID=0;
		double totalMontantIC=0;
		
		double totalFraisInterID=0;
		double totalFraisInterIC=0;
		
		double totalFraisCentreID=0;
		
		 double montant=0;
		 double fraisInter=0;
		 double fraisCentre=0;

		for(Clearing cl: clearings) {
			 if(cl.getTypeEnregistrement().equals("20")) {
		
				 // carte BPM 
				 if(cl.getPan().matches("637103.+")) {
					 
					 // achat client BPM sur TPE
					 if(cl.getCodeOperation().equals("05000")) {
						 montant=getMontantByDec(cl.getMontantTransactionGross(),2);			

						 montantAchatClientBPMsurTPE+=montant;
					 }
					 // retrait BPM autre bank
					 else if(cl.getCodeOperation().equals("07000")) {
						 montant=getMontantByDec(cl.getMontantTransactionGross(),2);
						 fraisInter=getMontantByDec(cl.getFraisInterchange(),2);
						 fraisCentre=getMontantByDec(cl.getFraisCentre(),2);				
						 
						 montantRetraitBpmOtherBank+=montant;
						 fraisInterOtherBank+=fraisInter;
						 fraisCentreClientBPM+=fraisCentre;
						
					 }
					 // frais recalcul PIN
					 else if(cl.getCodeOperation().equals("10102")) {
						 fraisCentre=getMontantByDec(cl.getFraisCentre(),2);				
						 fraisRecalculPIN+=fraisCentre;
					 }
					 // frais personnalisation
					 else if(cl.getCodeOperation().equals("10100")) {
						 fraisCentre=getMontantByDec(cl.getFraisCentre(),2);				
						 fraisPersonnalisation+=fraisCentre;
					 }
					 

					 

					
				 }
				 // Carte autre
				 else {	
					 // retrait autre bank sur gab bpm
					 if(cl.getCodeOperation().equals("07000")) {
						 montant=getMontantByDec(cl.getMontantTransactionGross(),2);
						 fraisInter=getMontantByDec(cl.getFraisInterchange(),2);
						 
						 montantRetraitOtherBankBpm+=montant;
						 fraisInterBPM+=fraisInter;
					 }
					 
				
					 

				 }				 
						 
			 }
			 else if(cl.getTypeEnregistrement().equals("10")) {
				 
				 // encaisement commercant
				 if(cl.getCodeOperation().equals("05000")) {
					 
					 montant=getMontantByDec(cl.getMontantTransactionGross(),2);
					 fraisInter=getMontantByDec(cl.getMontantNetCreditCommercant(),2);
					 fraisCentre=getMontantByDec(cl.getFraisCentre(),2);
					 
					 totalMontantIC+=montant;
					 
					 montantNetCommercant+=fraisInter;
					 commissionBank+=(montant-fraisInter)-fraisCentre;
					 fraisCentreAchatTPE+=fraisCentre; 

				 }
			 }
		}
		

		double totalD=montantRetraitBpmOtherBank+fraisCentreClientBPM+fraisInterOtherBank+montantAchatClientBPMsurTPE+fraisRecalculPIN+fraisPersonnalisation;
		double totalC=montantRetraitOtherBankBpm+fraisInterBPM+montantNetCommercant+commissionBank;
		

		 double diff= totalD-totalC;
		 
		 System.out.println(diff);
		//getMontantByDec(,2);

			
		 
		 infrmationDebit.add(new ItemInfo("Montant total Retrait client BPM sur GAB confreres", montantRetraitBpmOtherBank));
		 infrmationDebit.add(new ItemInfo("Commission banque confrere sur retrait client BPM", fraisInterOtherBank));
		 infrmationDebit.add(new ItemInfo("Commission GIMTEL sur retrait client BPM", fraisCentreClientBPM));
		 infrmationDebit.add(new ItemInfo("Achat client BPM sur TPE", montantAchatClientBPMsurTPE));
		 infrmationDebit.add(new ItemInfo("Commission GIMTEL sur Achat", fraisCentreAchatTPE));
		 infrmationDebit.add(new ItemInfo("Total frais personnalisation Carte", fraisPersonnalisation));
		 infrmationDebit.add(new ItemInfo("Total frais recalcul PIN", fraisRecalculPIN));

		 
		 infrmationCredit.add(new ItemInfo("Montant total Retrait client confrere sur nos GAB",montantRetraitOtherBankBpm));
		 infrmationCredit.add(new ItemInfo("Commission banque BPM sur retrait client confrere", fraisInterBPM));
		 infrmationCredit.add(new ItemInfo("Montant total Net commerçant", montantNetCommercant));
		 infrmationCredit.add(new ItemInfo("Commission de la banque sur les achat TPE", commissionBank));

		    ResponseClDto responseClDto = new ResponseClDto(infrmationDebit, infrmationCredit,diff, succes);

		 return responseClDto;
	}

	
	private String getDateString(Date date) {
		int jour =date.getDate();
		int mois=date.getMonth()+1;
		int annee=date.getYear()+1900;
		
		String tjour=""+jour,tmois=""+mois,tannee=""+annee;
		
		if(jour<10) {
			tjour="0"+jour;
		}
		if(mois<10) {
			tmois="0"+mois;
		}
		
		String res=tjour+tmois+tannee;
		
		return res;
	}
	
	@Override
	public ResponseCl preIntClearingByFileName(String filename) throws Exception {
		
		Parametrage parametrage=parametrageRepository.findById(1L).get();
		 Iterator<String> it= ClearingHelper.getListOprerationPreIntGiMTELFile(filename,parametrage.getUrlInGimtel());

		 // initialisation des listes
		 
		    List<ClearingDto> list2=null;
		   
		    int list3=0;
		    
			List<ItemInfo> infrmationClearing=new ArrayList<>();
			
			
			Set<String> etats=new HashSet<>();
			etats.add(ClearingHelper.getCorrespondanceAnomalieClearing(-1));
			
			List<ClearingDto> list=new ArrayList<>();		
			
			 // map cl gimtel par code op
			 Map<String, List<ClearingDto>> mapClGimtel= new HashMap<>();
			 // map liste anomalie
			 Map<String,Integer> mapCInfoExcep= new HashMap<>();
		     
			 int totalAnomalie=0;
			 
		 while (it.hasNext()) {
				
			 String item= it.next();
			 ClearingDto cl=ClearingHelper.getClearingDtoItem(item);
			 
			 // verification d'anomalie
			 String etatDesc="";	
			 int cpt=0;

			 
			// ajout du clearing 
			 if(etatDesc.equals("")) {
				 cl.setEtat(ClearingHelper.getCorrespondanceAnomalieClearing(-1));
			 }else {
				 cl.setEtat(etatDesc);
			 }			 
			 list.add(cl);
			 
			 
			 // information cl
			 
			 int code= Integer.parseInt(cl.getCodeOperation());
			 
				if(code!=0) {
					String codeS=  CorrespondanteCodeHelper.formatageCodeTransClGIMTEL(code);
					// recuperer la liste par code
					if(mapClGimtel.containsKey(codeS)) {
						list2=	mapClGimtel.get(codeS);
					}else {
						list2= new ArrayList<>();
	                 mapClGimtel.put(codeS,list);
					}
					
					list2.add(cl);
					
					mapClGimtel.replace(codeS, list2);
					
					
				}
				
		
				
			 
		 }
		 
		 // information cl 
		 infrmationClearing.add(new ItemInfo("Total Opération", list.size()));
		 
			for(String x : mapClGimtel.keySet() ) {
				infrmationClearing.add(new ItemInfo(x, mapClGimtel.get(x).size()));
			}
		
	    ResponseCl responseCl = new ResponseCl(infrmationClearing, null, etats, list);
		 
		return responseCl;
	
	}


	private double getMontantByDec(String res,int dec) {
		
		double m=0;
		
		String res2=res.substring(0, res.length()-dec)+"."+res.substring(res.length()-dec);
		
		m=Double.parseDouble(res2);
		return m; 
	}






}
