package com.monetique.helper;

public class BatchHelper {

	public static String getCorrespondanceEtatBatch(int etat) {
		
		String res=null;
		
		switch (etat) {
		case 0:
			res="integré avec succès";
			break;
		case 1:
			res="déja integré";
			break;
		case 2:
			res="non integré, nom du fichier non conforme";
			break;
		case 3:
			res="erreur d'integration";
			break;		
	}
	
	return res;
	
}
	
}
