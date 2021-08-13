package com.monetique.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Approbation {
	private String approve;
	private String bearerCode;
	private String languageId;
	private String modifiedBy;
	private String userId;
	private String remarks;

}
