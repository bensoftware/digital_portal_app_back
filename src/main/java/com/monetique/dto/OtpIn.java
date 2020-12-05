package com.monetique.dto;

import javax.xml.bind.annotation.XmlElement;

public class OtpIn {
	@XmlElement (required = true)
	public String telephone;
}
