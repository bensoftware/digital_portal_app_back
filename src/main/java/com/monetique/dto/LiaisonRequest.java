package com.monetique.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LiaisonRequest {
	private String cif;
	private String bearerCode;
	private String languageId;

}
