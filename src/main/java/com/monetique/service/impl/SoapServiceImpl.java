package com.monetique.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monetique.dto.InputGetAllAmountCardDto;
import com.monetique.dto.InputSendPhoneCardDto;
import com.monetique.dto.OutputGetAllAmountCardDto;
import com.monetique.dto.OutputSendPhoneCardDto;
import com.monetique.dto.Status;
import com.monetique.entities.CarteStock;
import com.monetique.entities.Expediteur;
import com.monetique.entities.Operateur;
import com.monetique.entities.SessionApi;
import com.monetique.service.CarteStockService;
import com.monetique.service.CarteUtiliserService;
import com.monetique.service.ExpediteurService;
import com.monetique.service.MontantService;
import com.monetique.service.OperateurService;
import com.monetique.service.SecuriteService;
import com.monetique.service.SessionApiService;
import com.monetique.service.SoapService;

@Service
public class SoapServiceImpl implements SoapService {

	
	@Autowired
	OperateurService operateurService;
	
	@Autowired
	SecuriteService securiteService ;
	
	@Autowired
	MontantService montantService;
	
	@Autowired
	CarteStockService carteStockService;
	
	@Autowired
	CarteUtiliserService carteUtiliserService;
	
	@Autowired
	ExpediteurService expediteurService;
	
	@Autowired
	SessionApiService sessionApiService;
	
	@Override
	public OutputSendPhoneCardDto sendPhoneCard(InputSendPhoneCardDto in) throws Exception {
		// TODO Auto-generated method stub
		
		OutputSendPhoneCardDto out = new OutputSendPhoneCardDto();
		
		int codeStatus=0;
		int codeError=0;
		
		SessionApi sessionApi= null;
		
		try {
			sessionApi= sessionApiService.authenticationSessionApi(in.username, in.password);
            if(sessionApi!=null) {
    			
    			// test operateur
    			Operateur operateur=null;
    			try {
    				operateur= operateurService.getOperatorByCode(in.operator);
    			} catch (Exception e) {
    				// TODO: handle exception
    			}
    			
    			
    			if(operateur==null) {
    				codeStatus=1;
    				codeError=102;
    				out.setStatus(new Status(codeStatus, codeError));
    				return out;
    			}
    			
    			//test montant
    			boolean existAmount=false;
    			try {
    				existAmount= montantService.checkAmount(in.operator, in.amount);	
    			} catch (Exception e) {
    				// TODO: handle exception
    			}
    			
    			if(!existAmount) {
    				codeStatus=1;
    				codeError=100;
    				out.setStatus(new Status(codeStatus, codeError));
    				return out;
    			}
    			
    			// teste expediteur
    			Expediteur server=null;
    			try {
    				server= expediteurService.getExpediteurByCode(in.serverSender);		
    			} catch (Exception e) {
    				// TODO: handle exception
    			}
    		
    		
    		if(server==null) {
    			codeStatus=1;
    			codeError=104;
    			out.setStatus(new Status(codeStatus, codeError));
    			return out;
    		}
    		
    			
    			//get recharge
    		CarteStock carteStock=null;
    		try {
    			carteStock= carteStockService.getRechargeStock(in.operator, in.amount);			
    		} catch (Exception e) {
    			// TODO: handle exception
    		}
    			
    			if(carteStock==null) {
    				codeStatus=1;
    				codeError=103;
    				out.setStatus(new Status(codeStatus, codeError));
    				return out;
    			}
    			
    			//save recharge utilisé
    			
    			if(in.senderPhoneNumber==null || in.senderPhoneNumber.length()==0) {
    				codeStatus=1;
    				codeError=105;
    				out.setStatus(new Status(codeStatus, codeError));
    				return out;
    			}
    			String receiverPhoneNumber=in.receiverPhoneNumber;
    			if(in.receiverPhoneNumber==null || in.receiverPhoneNumber.length()==0) {
    				receiverPhoneNumber=in.senderPhoneNumber;
    			}
    			
    			
    			boolean  isSaveRechUsed=false;
    			try {
    				isSaveRechUsed=carteUtiliserService.saveCarteStock(carteStock, in.senderPhoneNumber, receiverPhoneNumber, server);
    			} catch (Exception e) {
    				// TODO: handle exception
    			}
    			
    			if(!isSaveRechUsed) {
    				codeStatus=1;
    				codeError=106;
    				out.setStatus(new Status(codeStatus, codeError));
    				return out;
    			}
    			
    			//supp recharge
    			boolean  isDeleteRechUsed=false;
    			try {
    				isDeleteRechUsed=carteStockService.deleteRechargeStock(carteStock.getId());
    			} catch (Exception e) {
    				// TODO: handle exception
    			}
    			
    			
    			if(!isDeleteRechUsed) {
    				codeStatus=1;
    				codeError=106;
    				out.setStatus(new Status(codeStatus, codeError));
    				return out;
    			}
    			
    			
    			// decrypte Code
    			carteStock=securiteService.setDecryptageCarte(carteStock);
    			
                out.setCode(carteStock.getCodeSecret());
    			out.setStatus(new Status(codeStatus, codeError));
    			return out;
            }
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		codeStatus=1;
		codeError=101;
		out.setStatus(new Status(codeStatus, codeError));
		return out;
	
	}

	@Override
	public OutputGetAllAmountCardDto getAllAmount(InputGetAllAmountCardDto in) throws Exception {
		OutputGetAllAmountCardDto out = new OutputGetAllAmountCardDto();
		
		int codeStatus=0;
		int codeError=0;
		
	SessionApi sessionApi= null;
		
		try {
			sessionApi= sessionApiService.authenticationSessionApi(in.username, in.password);
            if(sessionApi!=null) {
            	// test operateur
    			Operateur operateur=null;
    			try {
    				operateur= operateurService.getOperatorByCode(in.operator);
    			} catch (Exception e) {
    				// TODO: handle exception
    			}
    			
    			
    			if(operateur==null) {
    				codeStatus=1;
    				codeError=102;
    				out.setStatus(new Status(codeStatus, codeError));
    				return out;
    			}
    			
    		
    		    List<Double> amounts= montantService.getListByOperator(in.operator);
    			
                out.setAmounts(amounts);
    			out.setStatus(new Status(codeStatus, codeError));
    			return out;
            }
            
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		codeStatus=1;
		codeError=101;
		out.setStatus(new Status(codeStatus, codeError));
		return out;
		
	}
	
	



}
