package com.monetique.service;

import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.monetique.dto.Carte;
import com.monetique.dto.RequestReleveDto;

import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
@Service
public class ReleveMCServiceImpl implements ReleveMCService{
	@Autowired
	private ResourceLoader resourceLoader;
    @Value("${host.urlVerifReleveMC}")
	String urlVerifReleveMC;
	@Autowired
    RestTemplate restTemplate;
	@Override
	public Carte getInfo(String pan) {
		Carte res=null;
		String url= urlVerifReleveMC+"/getInfo/"+pan;
		ResponseEntity<Carte> response = restTemplate.getForEntity(url, Carte.class);
		if(response.getStatusCode().equals(HttpStatus.OK)) {
			 res= response.getBody(); 
		}
		return res;
	}
	@Override
	public JasperPrint generateInfoReleve(String pan,HttpServletResponse response) throws Exception {
		RequestReleveDto res=null;
		String url= urlVerifReleveMC+"/generateInfoRapport/"+pan;
		ResponseEntity<RequestReleveDto> resp = restTemplate.getForEntity(url, RequestReleveDto.class);
		if(resp.getStatusCode().equals(HttpStatus.OK)) {
			 res= resp.getBody(); 
		}
		  
	      response.setContentType("application/x-download");
	   	  response.setHeader("Content-Disposition", String.format("attachment; filename=\"resultats.pdf\""));

		  
		  String path = resourceLoader.getResource("classpath:releve.jrxml").getURI().getPath();
		  JasperReport jasperReport = JasperCompileManager.compileReport(path);
		  JRBeanCollectionDataSource dataSource=new JRBeanCollectionDataSource(res.getMonetiqueClasses());
		  JasperPrint jasperPrint=JasperFillManager.fillReport(jasperReport,res.getMap(), dataSource);

		 		  
	   	  String pathT = resourceLoader.getResource("classpath:transaction.jrxml").getURI().getPath();
		  JasperReport jasperReportT = JasperCompileManager.compileReport(pathT);
		  JRBeanCollectionDataSource dataSourceT=new JRBeanCollectionDataSource(res.getMonetiqueB());
		  JasperPrint jasperPrintT=JasperFillManager.fillReport(jasperReportT,res.getMap(), dataSourceT);
		  List<JRPrintPage> pages = jasperPrintT .getPages();
		  for (int j = 0; j < pages.size(); j++) {
		      JRPrintPage object = (JRPrintPage)pages.get(j);
		      jasperPrint.addPage(object);
		  }
	  OutputStream out = response.getOutputStream();
	  JasperExportManager.exportReportToPdfStream(jasperPrint, out);
	  out.flush();
	return jasperPrint;	
	}

}
