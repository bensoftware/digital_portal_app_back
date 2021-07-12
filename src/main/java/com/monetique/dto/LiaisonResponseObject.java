package com.monetique.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LiaisonResponseObject {

	private String serviceRequestId;
	private LiaisonResponseResponse response;

}
