package com.monetique.um.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerificationImalResponse {
	private boolean updateMajBankily;
	private int cif;
	private String additionalReference;
	private String telephone;
	private String partyId;
	private String userId;
	private String status;
	private String nomBankily;
	private String login_id;
	private String otp;
	private String dateExpiration;
	private String tentative;
	private String statusUser;
	private String blocage;
	private long nni;
	private String listC;

}
