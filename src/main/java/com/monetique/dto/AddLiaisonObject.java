package com.monetique.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddLiaisonObject {
	private String serviceRequestId;
	private String mfsTenantId;
	private String serviceFlow;
	private String referenceServiceRequestId;
	private String originalServiceRequestId;
	private String status;
	private List<ErrorLiason> errors;

}
