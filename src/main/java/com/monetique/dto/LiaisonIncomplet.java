package com.monetique.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LiaisonIncomplet {
	private String nni;
	private String cif;
	private String telephone;
	private String compte;
}
