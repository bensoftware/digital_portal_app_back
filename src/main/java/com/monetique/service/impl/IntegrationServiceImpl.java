package com.monetique.service.impl;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monetique.dto.ItemInfo;
import com.monetique.entities.CarteStock;
import com.monetique.entities.IntegrationException;
import com.monetique.entities.IntegrationFile;
import com.monetique.entities.Operateur;
import com.monetique.entities.Parametrage;
import com.monetique.entities.TypeMontant;
import com.monetique.model.helper.CarteHelper;
import com.monetique.repositories.IntegrationExceptionRepository;
import com.monetique.repositories.IntegrationFileRepository;
import com.monetique.repositories.ParametrageRepository;
import com.monetique.repositories.TypeMontantRepository;
import com.monetique.service.CarteStockService;
import com.monetique.service.IntegrationService;
import com.monetique.service.OperateurService;
import com.monetique.service.SecuriteService;

@Service
public class IntegrationServiceImpl implements IntegrationService {

	
	@Autowired
	ParametrageRepository parametrageRepository;
	
	@Autowired
	OperateurService operateurService;
	
	@Autowired
	TypeMontantRepository typeMontantRepository;
	
	@Autowired
	CarteStockService carteStockService;
	
	@Autowired
	SecuriteService securiteService;
	
	@Autowired
	IntegrationFileRepository integrationFileRepository;
	
	@Autowired
	IntegrationExceptionRepository integrationExceptionRepository;

	@Override
	public List<CarteStock> fileToCarteStock(int operator,String filename) throws Exception {
		// TODO Auto-generated method stub
		
		Optional<Parametrage> optP= parametrageRepository.findById(1L);
		
		if(!optP.isPresent())
			throw new Exception("parametrage innexistant");
				
		Operateur operateur= operateurService.getOperatorByCode(operator);

		Parametrage parametrage= optP.get();
		List<CarteStock> list= new ArrayList<>();

		String urlIn=null;
		
		
		// mauritel
		if(operator==1) {
			urlIn=parametrage.getUrlInMauritel();
		}
		// Chinguittel
		else if(operator==2) {
			urlIn=parametrage.getUrlInChinguitel();
		}
		
		Stream<String> stream= CarteHelper.getStreamCarte(filename, urlIn);
		
		
		
		Iterator<String> it= stream.iterator();
		

		try {
			// iteration
			while (it.hasNext()) {
				String item = (String) it.next();
				
				if(item.length()==0 || item.matches("\\s+")) {
					break;
				}

				
				CarteStock carteStock=CarteHelper.getCarteStockItem(operator, item);
				
				double montant= carteStock.getTypeMontant().getMontant();
				
				TypeMontant existe=typeMontantRepository.getExistMontantByOperateur(montant,operator);
				
				if(existe==null) {
					existe=typeMontantRepository.save(new TypeMontant(montant,operateur));
				}
				
				carteStock.setTypeMontant(existe);
				
				list.add(carteStock);
				
			} 
			
		} catch (Exception e) {
			stream.close();
		}
		
	
		
		stream.close();
				
		return list;
	}

	@Override
	public int integrationVoucher(int operator, String filename) throws Exception {
		// TODO Auto-generated method stub
	    Optional<Parametrage> optP= parametrageRepository.findById(1L);
		
		if(!optP.isPresent())
			throw new Exception("parametrage innexistant");
		
		
        Operateur operateur= operateurService.getOperatorByCode(operator);
		Parametrage parametrage= optP.get();
		String urlIn=null;
		String urlOut=null;		
		
		// mauritel
		if(operator==1) {
			urlIn=parametrage.getUrlInMauritel();
			urlOut=parametrage.getUrlOutMauritel();
		}
		// Chinguittel
		else if(operator==2) {
			urlIn=parametrage.getUrlInChinguitel();
			urlOut=parametrage.getUrlOutChinguitel();
		}
		
		
		List<CarteStock> cartes= fileToCarteStock(operator, filename);
		int nonIntegrer= 0;
		
		Date dateAc= new Date();
		
		for(CarteStock x : cartes) {
			int rep= carteStockService.saveCarteStock(x,dateAc);
			if(rep==1) {
				// ajouter l'exception
				integrationExceptionRepository.save(new IntegrationException(1, "code de serie deja existant", x.getCleNumeroSerie()+x.getNumeroCarte() , dateAc));
				nonIntegrer++;		
			}
			else if(rep==2) {
				// ajouter l'exception
				integrationExceptionRepository.save(new IntegrationException(2, "recharge expirée", x.getCleNumeroSerie()+x.getNumeroCarte() , dateAc));
				nonIntegrer++;		
			}
		}
		
		// logger l'integration
		IntegrationFile integrationFile=new IntegrationFile(filename, new Date(), nonIntegrer, cartes.size(), operateur);
		integrationFileRepository.save(integrationFile);
		
		// deplacer vers le out
		//CarteHelper.moveFile(filename, urlIn, urlOut);
		
		// cryptage du contenu par l'algo RSA
		 Path path =Paths.get(urlIn+"\\"+filename);
		Stream<String> line = Files.lines(path,StandardCharsets.ISO_8859_1);
		List<String> list= line.filter(e -> e.length()>0).map(e->securiteService.setCryptageLigne(e)).collect(Collectors.toList());
		line.close();
		
		// suppression
		CarteHelper.deleteFile(filename, urlIn);

		
		// le mettre dans un nouveau fichier
		path =Paths.get(urlOut+"\\"+filename);
        Files.write(path, list);
		
		
		
		return nonIntegrer;
	}

	@Override
	public List<String> getListVouchers(int operateur) throws Exception {
		// TODO Auto-generated method stub
				List<String> res= new ArrayList<>();
				
				String url=null;
				
				  Optional<Parametrage> optP= parametrageRepository.findById(1L);
					
					if(!optP.isPresent())
						throw new Exception("parametrage innexistant");
										
					Parametrage parametrage= optP.get();	
					
				if(operateur==1) {
					url= parametrage.getUrlInMauritel();
				}else if(operateur==2) {
					url=parametrage.getUrlInChinguitel();
				}else {
					throw new Exception("operateur innexistant");
				}
				
				Path path = Paths.get(url);
				
				try(DirectoryStream<Path> listing = Files.newDirectoryStream(path)){

					    for(Path file : listing){

					    	Path clearing= file;
					    	if(Files.isRegularFile(clearing)) {
					    		
					    		String filename=file.getFileName().toString();
					    		
					    	/*	if(filename.matches("BCLEAR.+")) {
					    			res.add(filename);
					    		}*/
					    		res.add(filename);
					    	}
					    }
							
					  } catch (IOException e) {
					    e.printStackTrace();
					  }	
				
				return res;
	}

	@Override
	public List<IntegrationFile> getHistoriqueIntegration(int operateur) throws Exception {
		
	return integrationFileRepository.getAllIntegrationFileByOperator(operateur);
		
	}

	@Override
	public List<ItemInfo> getInfoVoucher(int operator,String filename) throws Exception {
		
		Optional<Parametrage> optP= parametrageRepository.findById(1L);
		
		if(!optP.isPresent())
			throw new Exception("parametrage innexistant");
				

		Parametrage parametrage= optP.get();
		
		List<ItemInfo> itemInfos= new ArrayList<>();
		
		
		String urlIn=null;
		
		
		// mauritel
		if(operator==1) {
			urlIn=parametrage.getUrlInMauritel();
		}
		// Chinguittel
		else if(operator==2) {
			urlIn=parametrage.getUrlInChinguitel();
		}
		
		Stream<String> stream= CarteHelper.getStreamCarte(filename, urlIn);
		
		
		
		Iterator<String> it= stream.iterator();
		

		int nbTotal=0;
		double montantTotal=0;
		
		try {
			// iteration
			while (it.hasNext()) {
				String item = (String) it.next();
				
				if(item.length()==0 || item.matches("\\s+")) {
					break;
				}

				
				CarteStock carteStock=CarteHelper.getCarteStockItem(operator, item);
				
				double montant= carteStock.getTypeMontant().getMontant();
				

				montantTotal+=montant;
				nbTotal++;
				
				
			} 
		} catch (Exception e) {
			stream.close();
		}
		
	
		
		itemInfos.add(new ItemInfo("Total des recharge", nbTotal));
		itemInfos.add(new ItemInfo("Montant total", montantTotal));
		
		stream.close();
				
		return itemInfos;
	}

	
}
