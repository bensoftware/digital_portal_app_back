package com.monetique.dto.monetique;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileDto {
	byte[]  multipartHttpServletRequest;
	String  responseFileName;
	String  errorMessage;
	String cif;
}
