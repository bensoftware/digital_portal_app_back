package com.monetique.model.helper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.monetique.dto.LogMc;
import com.monetique.dto.ResponseDtoApi;

public class LogMcHelper {
	
	public static List<LogMc> fromResponseToLogMc(ResponseEntity<ResponseDtoApi> response){
		List<LogMc> listLogMcs=new ArrayList<>();
		listLogMcs=response.getBody().getListLogs();
		//System.err.println("::::: " + listLogMcs);
		return listLogMcs;
	}

}
