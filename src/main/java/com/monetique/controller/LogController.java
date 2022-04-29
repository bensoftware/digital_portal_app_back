package com.monetique.controller;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.monetique.dto.ResponseDtoApi;
import com.monetique.service.LogService;


@RestController
public class LogController {
	
	@Autowired
	private LogService logService;

	final Logger logger = LoggerFactory.getLogger(LogController.class);

	@RequestMapping(value = "/getLogs", method = RequestMethod.GET)
	public ResponseDtoApi getLogs() throws Exception {
		ResponseDtoApi responseDtoApi = new ResponseDtoApi(logService.getAllLog(), "Success");
		return responseDtoApi;
	}

	@RequestMapping(value = "/getLogByDateAndPhone/{phone}/{date1}/{date2}", method = RequestMethod.GET)
	public ResponseDtoApi getLogByDateAndPhone(@PathVariable Date date1, @PathVariable Date date2,
			@PathVariable String phone) throws Exception {
		ResponseDtoApi responseDtoApi = new ResponseDtoApi(logService.getLogByPhoneAndDate(phone,date1,date2),"Success");
		return responseDtoApi;
	}

	@RequestMapping(value = "/getLogByPhone/{phone}", method = RequestMethod.GET)
	public ResponseEntity<?> getLogByPhone(@PathVariable String phone) throws Exception {

		ResponseDtoApi responseDtoApi = null;
		try {
			responseDtoApi = new ResponseDtoApi(logService.getByPhone(phone), "Success");
		} catch (Exception e) {
			logger.info("Exception"+ e.getMessage());
			return new ResponseEntity<>("FROM MC_OTP : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (phone.trim().length() != 8) {
			return new ResponseEntity<>("Le numero est vide", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(responseDtoApi, HttpStatus.OK);
	}

	
	@RequestMapping(value ="/getLogByDate/{date1}/{date2}", method = RequestMethod.GET)
	public ResponseDtoApi getLogByDate(@PathVariable Date date1, @PathVariable Date date2) throws Exception {
		ResponseDtoApi responseDtoApi = new ResponseDtoApi(logService.getLogByTwoDate(date1, date2),"Success");
		return responseDtoApi;
	}
	

}
