package com.monetique.security.service;

import java.util.Date;
import java.util.List;
import mr.bpm.mbanking.entities.LogMc;

public interface LogService {

	public void save(LogMc logMc) throws Exception;

	public LogMc findById(Long id) throws Exception;

	public List<LogMc> getLogByTwoDate(Date date1, Date date2) throws Exception;

	public List<LogMc> getLogByPhoneAndDate(Date date1, Date date2, String phone) throws Exception;

	public List<LogMc> getByPhone(String phone) throws Exception;

	public List<LogMc> getAllLog() throws Exception;

}
