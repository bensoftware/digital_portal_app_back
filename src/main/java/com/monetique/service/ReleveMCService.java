package com.monetique.service;

import javax.servlet.http.HttpServletResponse;

import com.monetique.dto.Carte;

import net.sf.jasperreports.engine.JasperPrint;

public interface ReleveMCService {
	public Carte getInfo(String pan);
	public JasperPrint generateInfoReleve(String pan,HttpServletResponse response) throws Exception;

	public JasperPrint generateInfoBetwwenDateReleve(String pan,long dateDu,long dateAu,HttpServletResponse response) throws Exception;

}
