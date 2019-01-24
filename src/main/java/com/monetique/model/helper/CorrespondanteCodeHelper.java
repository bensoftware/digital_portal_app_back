package com.monetique.model.helper;

public class CorrespondanteCodeHelper {

	public static String formatageCodeTransClSS(int code) {
		String res=null;
		
switch (code) {
		
        case 10237:
	       res="frais commission retrais GAB";
	    break;
        case 21031:
 	       res="recharge prepayée par GAB";
 	    break;
        case 10109:
 	       res="frais rachat carte prepayée";
 	    break;
        case 18001:
 	       res="montant Rachat carte prepayée";
 	    break;
        case 10902:
  	       res="frais carte";
  	    break;
        case 19001:
  	       res="recharge carte";
  	    break;
        case 10226:
  	       res="frais demande releve";
  	    break;
        case 10129:
   	       res="frais main levée";
   	    break;
        case 10107:
   	       res="frais initialisation carte prepayé";
   	    break;
        case 10106:
   	       res="frais d'adhesion de carte";
   	    break;
        case 10128:
   	       res="frais oppsition";
   	    break;
		case 25000:
			res="inversion Achat";
			break;
		case 7004:
			res="Quasi cash (Achat)";
			break;
		case 10102:
			res="Frais recalcul PIN";
			break;
		case 5000:
			res="Achat";
			break;
		case 27000:
			res="Inversion retrait";
			break;
		case 10900:
			res="Frais de personnalisation";
			break;
		case 7000:
			res="Retrait";
			break;
		case 7001:
			res="Cash Advanced (retrait TPE)";
			break;
		case 15000:
			res="sales draft changeback (rembourssement)";
			break;
		case 6000:
			res="refound (rembourssement)";
			break;	
			default :
			res="code"+code+"inconnu";
			break;		
		}
		
		return res;
	}
	
	
	public static String formatageCodeTransClGIMTEL(int code) {
		String res=null;
	
		switch (code) {
		
		case 25000:
			res="inversion Achat";
			break;
		case 7004:
			res="Quasi cash (Achat)";
			break;
		case 10102:
			res="Frais recalcul PIN";
			break;
		case 5000:
			res="Achat";
			break;
		case 27000:
			res="Inversion retrait";
			break;
		case 10100:
			res="Frais de personnalisation";
			break;
		case 7000:
			res="Retrait";
			break;
		case 7001:
			res="Cash Advanced (retrait TPE)";
			break;
		case 15000:
			res="sales draft changeback (rembourssement)";
			break;
		case 6000:
			res="refound (rembourssement)";
			break;	

		
		}
		
		
		return res;
	}
}
