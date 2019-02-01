package com.monetique.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.datatype.DatatypeFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monetique.entities.Clearing;
import com.monetique.entities.ClearingRejeter;
import com.monetique.model.helper.ClearingHelper;
import com.monetique.repositories.ClearingRejeterRepository;
import com.monetique.repositories.ClearingRepository;

import mr.bpm.mbanking.ws.ListMonetiqueCarte;
import mr.bpm.mbanking.ws.MonetiqueClass;
import mr.bpm.mbanking.ws.MonetiqueWS;
import mr.bpm.mbanking.ws.MonetiqueWSService;

@Service
public class TraitementClearingBatchImpl implements TraitementClearingBatchService {

	@Autowired
	ClearingRepository clearingRepository ;
	
	@Autowired
	ClearingRejeterRepository clearingRejeterRepository ;

	
	
	@Override
	public void verificationClGIMTELDate(Date du, Date au) throws Exception {

		List<Clearing> listCl= clearingRepository.getClearingByDateIntervall(du, au);		
		List<ClearingRejeter> listClR= clearingRejeterRepository.getClearingByDateIntervall(du, au);
		
		
		// recuperation des données dans SS (Daily)
		MonetiqueWSService b =new MonetiqueWSService();
		MonetiqueWS service =b.getMonetiqueWSPort();
		
		GregorianCalendar gdu = new GregorianCalendar();
		gdu.setTime(du);
		
		GregorianCalendar gau = new GregorianCalendar();
		gau.setTime(au);
		
		ListMonetiqueCarte lmAut= service.soldeSSOut( DatatypeFactory.newInstance().newXMLGregorianCalendar(gdu), DatatypeFactory.newInstance().newXMLGregorianCalendar(gau));
		List<MonetiqueClass> listClSS= lmAut.getList();
		
		System.out.println("----------------Treaitement ----------- ");
		System.out.println("");
		System.out.println("date du "+du.toGMTString());
		System.out.println("date au "+au.toGMTString());
		System.out.println("");
		System.out.println("Clearing avec Ref Transaction "+listCl.size());
		System.out.println("Clearing sans Ref Transaction "+listClR.size());
		System.out.println("Total des transaction dans Daily "+listClSS.size());
		// liste des cl GIMTEL ! listCl ; avec RRN , listClR : sans RRN
		// liste des integration daily : listClSS
		
		// filtrage suivant des critére ;
		
		// filtrage par type de carte 
		// par masterCard 530347....
		/*
		List<Clearing > listCl2= new ArrayList<>();
		List<ClearingRejeter > listClR2= new ArrayList<>();
		List<MonetiqueClass > listClSS2= new ArrayList<>();
		
		if(listCl!=null) {
			for(Clearing c : listCl) {
				
				if(c.getPan().indexOf("530347")>=0) {
					listCl2.add(c);
				}	
			}
		}
	
if(listClR!=null) {
	  for(ClearingRejeter c : listClR) {
			
		  if(c.getPan().indexOf("530347")>=0) {
			  listClR2.add(c);
			}
		}	
		}
      
if(listClSS!=null) {
	 for(MonetiqueClass c : listClSS) {

			if(c.getLieu().indexOf("530347")>=0) {
				listClSS2.add(c);
				}
		}	
		}
       
listCl=listCl2;
listClR=listClR2;
listClSS=listClSS2;
		//
		
		System.out.println("size "+listCl.size());
		System.out.println("size "+listClR.size());
		System.out.println("size daily ss "+listClSS.size());*/
		

		// map cl gimtel par code op
		Map<Integer, List<Clearing>> mapClGimtel= new HashMap<>();
		
		
		// cl avec rrn
		for(Clearing x : listCl) {
			
			List<Clearing> list=null;
			
			int code=0;

			try {
				code=Integer.parseInt(x.getCodeOperation());	
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			if(code!=0) {
				// recuperer la liste par code
				if(mapClGimtel.containsKey(code)) {
					list=	mapClGimtel.get(code);
				}else {
					list= new ArrayList<>();
                    mapClGimtel.put(code,list);
				}
				
				list.add(x);
				
				mapClGimtel.replace(code, list);
				
				
			}
			
		}
		
		// cl sans rrn
		for(ClearingRejeter x : listClR) {
			
			List<Clearing> list=null;
			
			int code=0;

			try {
				code=Integer.parseInt(x.getCodeOperation());	
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			if(code!=0) {
				// recuperer la liste par code
				if(mapClGimtel.containsKey(code)) {
					list=	mapClGimtel.get(code);
				}else {
					list= new ArrayList<>();
                    mapClGimtel.put(code,list);
				}
				Clearing ad= ClearingHelper.getClearingByClRejeter(x);
				ad.setClearingFile(x.getClearingFile());
				ad.setIndex(x.getIndex());
				list.add(ad);
				mapClGimtel.replace(code, list);
				
				
			}
			
		}
		
		
		// map daily SS par code op
		Map<Integer, List<MonetiqueClass>> mapDailySS= new HashMap<>();
		
		for(MonetiqueClass x : listClSS) {
			
			List<MonetiqueClass> list=null;
			
			int code=0;

			try {
				code=x.getCode();	
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			if(code!=0) {
				// recuperer la liste par code
				if(mapDailySS.containsKey(code)) {
					list=	mapDailySS.get(code);
				}else {
					list= new ArrayList<>();
					mapDailySS.put(code,list);
				}
				list.add(x);
				mapDailySS.replace(code, list);
				
				
			}
			
		}
		
		
		System.out.println("--Total des Clearing par code de transaction dans GIMTEL Clearing--");
				
		for(int x : mapClGimtel.keySet() ) {
			System.out.println("["+x+"] = "+mapClGimtel.get(x).size());
		}
		
		System.out.println("--Total des Integration Cl par code de transaction dans SS--");
		
		for(int x : mapDailySS.keySet() ) {
			System.out.println("["+x+"] = "+mapDailySS.get(x).size());
		}
		
		System.out.println("---------------Stat---------------");
		
		System.out.println("+ Achat");
		int m1=0,m2=0,m3=0,m4=0;
		try {
			m1=mapClGimtel.get(5000).size();
		} catch (Exception e) {
			// TODO: handle exception
			m1=0;
		}
		try {
			m2=mapClGimtel.get(7004).size();
		} catch (Exception e) {
			// TODO: handle exception
			m2=0;
		}
		try {
			m3=mapDailySS.get(5000).size();
		} catch (Exception e) {
			// TODO: handle exception
			m3=0;
		}
		System.out.println("Cl GIMTEL "+( m1+ m2));
		System.out.println("SS daily "+m3);
		
		System.out.println("");
		System.out.println("+ Retrait");
		try {
			m1=mapClGimtel.get(7000).size();
		} catch (Exception e) {
			// TODO: handle exception
			m1=0;
		}
		try {
			m2=mapClGimtel.get(7001).size();
		} catch (Exception e) {
			// TODO: handle exception
			m2=0;
		}
		try {
			m3=mapDailySS.get(7000).size();
		} catch (Exception e) {
			// TODO: handle exception
			m3=0;
		}
		System.out.println("Cl GIMTEL "+( m1+ m2));
		System.out.println("SS daily "+m3);
		
		System.out.println("");
		System.out.println("+ Inversion");
		try {
			m1=mapClGimtel.get(25000).size();
		} catch (Exception e) {
			// TODO: handle exception
			m1=0;
		}
		try {
			m2=mapClGimtel.get(27000).size();
		} catch (Exception e) {
			// TODO: handle exception
			m2=0;
		}
		try {
			m3=mapDailySS.get(27000).size();
		} catch (Exception e) {
			// TODO: handle exception
			m3=0;
		}
		try {
			m4=mapDailySS.get(25000).size();
		} catch (Exception e) {
			// TODO: handle exception
			m4=0;
		}
		System.out.println("Cl GIMTEL "+(m1+m2));
		System.out.println("SS daily "+( m3+m4));
		System.out.println("");
		System.out.println("+ Rembourssement");
		try {
			m1=mapClGimtel.get(6000).size();
		} catch (Exception e) {
			// TODO: handle exception
			m1=0;
		}
		try {
			m2=mapClGimtel.get(15000).size();
		} catch (Exception e) {
			// TODO: handle exception
			m2=0;
		}
		try {
			m3=mapDailySS.get(6000).size();
		} catch (Exception e) {
			// TODO: handle exception
			m3=0;
		}
		System.out.println("Cl GIMTEL "+( m1+m2));
		System.out.println("SS daily "+m3);
		
		
/*		System.out.println("les rembourssements GIMTEL");
		for(Clearing c : mapClGimtel.get(6000)) {
			System.out.println("montant "+c.getMontantTransactionGross()+" Devise "+c.getMonnaie()+" pan "+c.getPan());
		}
		
		System.out.println("les rembourssements SS");
		for(MonetiqueClass c : mapDailySS.get(6000)) {
			System.out.println("montant "+c.getMontantOrigine()+" Devise "+c.getDeviseOrigine()+" pan "+c.getLieu());
		}*/
		
		System.out.println("--------------- Verification ---------------");
		
		
		for(int x : mapClGimtel.keySet() ) {
			
			int code =x;
			
			// achat dans SS
			if(code==5000 || code==7004) {
				
				System.out.println("les achats non integrer");
				List<Clearing> lCl=mapClGimtel.get(code);
				
				for(Clearing c : lCl) {
					Clearing res=null;
					Double	pan=null;
					Double refAut=null;
					Double codeAuth=null;
					try {
						refAut=Double.parseDouble(c.getReferenceAutorisation());	
					} catch (Exception e) {
						refAut=0.0;
					}
					try {
						codeAuth=Double.parseDouble(c.getNumeroAutorisation());	
					} catch (Exception e) {
						codeAuth=0.0;
								
					}
					try {
					 pan = Double.parseDouble(c.getPan());	
					} catch (Exception e) {
						// TODO: handle exception
						 pan=0.0;
					}
					
                    List<MonetiqueClass> lss= mapDailySS.get(5000);
                    if(lss!=null && lss.size()!=0) {
                    	 for(MonetiqueClass m :lss) {
    						 // meme carte
                    		 
    						 if(pan.equals(Double.parseDouble(m.getLieu()) )) {
    							 
    							 // les ref auth ne sont pas null
    							 double ref1=refAut;
    							 double ref2= m.getRefCl();
    							 if(ref1!=0 && ref2!=0) {
    								 
    							 }else {
    								 ref1=codeAuth;
    								 ref2=m.getCodeAuth();
    							 }
    							 if(ref1==ref2) {
    								 res=c;
    								 break;
    							 }
    						 }
    					 }
                    }
					
					 
					 if(res==null) {
						 try {
							 System.out.println("Num ligne "+c.getIndex()+" du fichier  "+c.getClearingFile().getNom());
						} catch (Exception e) {
							// TODO: handle exception
						}

					 }
					
				}
				
				
				
			}
			// retrait
			else if(code==7000 || code==7001) {
				System.out.println("les retraits non integrer");
				List<Clearing> lCl=mapClGimtel.get(code);
				
				for(Clearing c : lCl) {
					Clearing res=null;
					Double	pan=null;
					Double refAut=null;
					Double codeAuth=null;
					try {
						refAut=Double.parseDouble(c.getReferenceAutorisation());	
					} catch (Exception e) {
						refAut=0.0;
					}
					try {
						codeAuth=Double.parseDouble(c.getNumeroAutorisation());	
					} catch (Exception e) {
						codeAuth=0.0;
					}
					try {
					 pan = Double.parseDouble(c.getPan());	
					} catch (Exception e) {
						// TODO: handle exception
						pan=0.0;
					}
					
                    List<MonetiqueClass> lss= mapDailySS.get(7000);
                    if(lss!=null && lss.size()!=0) {
                    	for(MonetiqueClass m :lss) {
   						 // meme carte
   						 if(pan.equals(Double.parseDouble(m.getLieu()) )) {
   							// les ref auth ne sont pas null
   							 double ref1=refAut;
   							 double ref2= m.getRefCl();
   							 if(ref1!=0 && ref2!=0) {
   								 
   							 }else {
   								 ref1=codeAuth;
   								 ref2=m.getCodeAuth();
   							 }
   							 if(ref1==ref2) {
   								 res=c;
   								 break;
   							 }
   						 }
   					 }
                    }
					 
					 
                    if(res==null) {
						 try {
							 System.out.println("Num ligne "+c.getIndex()+" du fichier  "+c.getClearingFile().getNom());
						} catch (Exception e) {
							// TODO: handle exception
						}

					 }
				}
			}
			
			// inversion 
			else if(code==25000 || code==27000) {
				System.out.println("les inversions non integrer");
				List<Clearing> lCl=mapClGimtel.get(code);
				
				for(Clearing c : lCl) {
					Clearing res=null;
					Double	pan=null;
					Double refAut=null;
					Double codeAuth=null;
					try {
						refAut=Double.parseDouble(c.getReferenceAutorisation());	
					} catch (Exception e) {
						refAut=0.0;
					}
					try {
						codeAuth=Double.parseDouble(c.getNumeroAutorisation());	
					} catch (Exception e) {
						codeAuth=0.0;
					}
					try {
					 pan = Double.parseDouble(c.getPan());	
					} catch (Exception e) {
						// TODO: handle exception
						pan=0.0;
					}
					
                    List<MonetiqueClass> lss= mapDailySS.get(code);
                    if(lss!=null && lss.size()!=0) {
                    	 for(MonetiqueClass m :lss) {
    						 // meme carte
    						 if(pan.equals(Double.parseDouble(m.getLieu()) )) {
    							// les ref auth ne sont pas null
    							 double ref1=refAut;
    							 double ref2= m.getRefCl();
    							 if(ref1!=0 && ref2!=0) {
    								 
    							 }else {
    								 ref1=codeAuth;
    								 ref2=m.getCodeAuth();
    							 }
    							 if(ref1==ref2) {
    								 res=c;
    								 break;
    							 }
    						 }
    					 }
                    }
					
                    if(res==null) {
						 try {
							 System.out.println("Num ligne "+c.getIndex()+" du fichier  "+c.getClearingFile().getNom());
						} catch (Exception e) {
							// TODO: handle exception
						}

					 }
				}
			}
			
			// rembourssement 
			else if(code==6000 || code==15000) { 
				System.out.println("les rembourssements non integrer");
				List<Clearing> lCl=mapClGimtel.get(code);
				
				for(Clearing c : lCl) {
					Clearing res=null;
					Double	pan=null;
					Double montant=null;
					int devise=0;
					try {
						devise=Integer.parseInt(c.getMonnaie());	
					} catch (Exception e) {
						devise=0;
					}
					try {
						String re=c.getMontantTransactionGross();
						int size= re.length();
						if(devise==478) {
							devise=929;
							String mk1= re.substring(0, size-3);
							String mk2= re.substring(size-3, size); 
							String mk3 = mk1+"."+mk2;
							montant= Double.parseDouble(mk3);
						}else {
							String mk1= re.substring(0, size-2);
							String mk2= re.substring(size-2, size); 
							String mk3 = mk1+"."+mk2;
							montant= Double.parseDouble(mk3);
						}
						
						
					} catch (Exception e) {
					}
				
					try {
					 pan = Double.parseDouble(c.getPan());	
					} catch (Exception e) {
						// TODO: handle exception
						 pan=0.0;
					}
					
                    List<MonetiqueClass> lss= mapDailySS.get(6000);
                    if(lss!=null && lss.size()!=0) {
                   	 for(MonetiqueClass m :lss) {
						 // meme carte
						 if(pan.equals(Double.parseDouble(m.getLieu()) )) {
							// les ref auth ne sont pas null
							 double mOrigine=m.getMontantOrigine();
							 int deviseO=m.getDeviseOrigine();
							 
							 if(devise==deviseO && montant.equals(mOrigine)) {
								 res=c;
								 break; 
							 }
						
							 
						 }
					 }
                    }
                    if(res==null) {
						 try {
							 System.out.println("Num ligne "+c.getIndex()+" du fichier  "+c.getClearingFile().getNom());
							 
						} catch (Exception e) {
							// TODO: handle exception
						}
					
					 }
				}
			}
			
		}
		
		
	}
	


}
