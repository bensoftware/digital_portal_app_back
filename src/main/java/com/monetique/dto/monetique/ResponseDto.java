package com.monetique.dto.monetique;

import java.io.Serializable;
import lombok.Data;

@Data
public class ResponseDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private ClientCifDto data;
	private int errorCode;
    private String errorMessage;

}
