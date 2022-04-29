package com.monetique.service;

import java.util.Date;
import java.util.List;

import com.monetique.dto.LogMc;

public interface LogService {

	public LogMc findById(Long id) throws Exception;

	public List<LogMc> getLogByTwoDate(Date date1, Date date2) throws Exception;

	public List<LogMc> getLogByPhoneAndDate(String phone,Date date1, Date date2) throws Exception;

	public List<LogMc> getByPhone(String phone) throws Exception;

	public List<LogMc> getAllLog() throws Exception;

}
