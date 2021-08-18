package com.monetique.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerificationMobileResponse {
	private String updateMajBankily;
	private String cif;
	private String additionalReference;
	private String telephone;
	private String partyId;
	private String userId;
	private String status;
	private String nomBankily;
	private String otp;
	private String login_id;
	private String dateExpiration;
	private String tentative;
	private String statusUser;
	private String blocage;
	private String nni;
	private String listC;
	private String incomplet;
	private String existe;

}
