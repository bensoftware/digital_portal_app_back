package com.monetique.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationMc {
	private Long id;	
	private String pan;
	private String telephone;
	private boolean actif;
}
