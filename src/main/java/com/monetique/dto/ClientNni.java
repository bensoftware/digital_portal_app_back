package com.monetique.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientNni {
    private String nni;
    private String dateDeNaissance; 
    private String img;
    private String lieuNaissance;
    private String nationaliteIso;
    private String nomFamille;
    private String prenom;
    private String prenomPere;
    private String sexe;
    private Date dateInscription;


}
