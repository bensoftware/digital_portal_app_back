package com.monetique.service;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import com.monetique.dto.TransactionMc;

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
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
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
		  List<TransactionMc> list1=new ArrayList<>();
		  list1.add(new TransactionMc());
		  list1.addAll(res.getMonetiqueClasses());
		  System.err.println(list1);
		  JRBeanCollectionDataSource dataSource=new JRBeanCollectionDataSource(list1);
		  Map<String,Object> mapR= res.getMap();
		  mapR.put("releves",dataSource);
		  JasperPrint jasperPrint=JasperFillManager.fillReport(jasperReport,mapR, dataSource);

		 		  
	   	  String pathT = resourceLoader.getResource("classpath:transaction.jrxml").getURI().getPath();
		  JasperReport jasperReportT = JasperCompileManager.compileReport(pathT);
		  List<TransactionMc> list2=new ArrayList<>();
		  list2.add(new TransactionMc());
		  list2.addAll(res.getMonetiqueB());
		  JRBeanCollectionDataSource dataSourceT=new JRBeanCollectionDataSource(list2);
		  Map<String,Object> map2= res.getMap();
		  map2.put("transaction",dataSourceT);
		  JasperPrint jasperPrintT=JasperFillManager.fillReport(jasperReportT,map2, dataSourceT);
		  if(res.getMonetiqueB().size()>0) {
			  List<JRPrintPage> pages = jasperPrintT .getPages();
			  for (int j = 0; j < pages.size(); j++) {
			      JRPrintPage object = (JRPrintPage)pages.get(j);
			      jasperPrint.addPage(object);
			  }
		  }

		 
	  OutputStream out = response.getOutputStream();
	  JasperExportManager.exportReportToPdfStream(jasperPrint, out);
	  out.flush();
	return jasperPrint;	
	}
	@Override
	public JasperPrint generateInfoBetwwenDateReleve(String pan, long dateDu, long dateAu,
			HttpServletResponse response) throws Exception {
		RequestReleveDto res=null;
		String url= urlVerifReleveMC+"/generateInfoRapportByIntervall/"+pan+"/"+dateDu+"/"+dateAu;
		ResponseEntity<RequestReleveDto> resp = restTemplate.getForEntity(url, RequestReleveDto.class);
		if(resp.getStatusCode().equals(HttpStatus.OK)) {
			 res= resp.getBody(); 
		}
	      response.setContentType("application/x-download");
	   	  response.setHeader("Content-Disposition", String.format("attachment; filename=\"resultats.pdf\""));

		  
		  String path = resourceLoader.getResource("classpath:releve.jrxml").getURI().getPath();
		  JasperReport jasperReport = JasperCompileManager.compileReport(path);
		  List<TransactionMc> list1=new ArrayList<>();
		  list1.add(new TransactionMc());
		  list1.addAll(res.getMonetiqueClasses());
		  JRBeanCollectionDataSource dataSource=new JRBeanCollectionDataSource(list1);
		  Map<String,Object> mapR= res.getMap();
		  mapR.put("releves",dataSource);
		  JasperPrint jasperPrint=JasperFillManager.fillReport(jasperReport,mapR, dataSource);

		 		  
	   	  String pathT = resourceLoader.getResource("classpath:transaction.jrxml").getURI().getPath();
		  JasperReport jasperReportT = JasperCompileManager.compileReport(pathT);
		  List<TransactionMc> list2=new ArrayList<>();
		  list2.add(new TransactionMc());
		  list2.addAll(res.getMonetiqueB());
		  JRBeanCollectionDataSource dataSourceT=new JRBeanCollectionDataSource(list2);
		  Map<String,Object> map2= res.getMap();
		  map2.put("transaction",dataSourceT);
		  JasperPrint jasperPrintT=JasperFillManager.fillReport(jasperReportT,map2, dataSourceT);
		  if(res.getMonetiqueB().size()>0) {
			  List<JRPrintPage> pages = jasperPrintT .getPages();
			  for (int j = 0; j < pages.size(); j++) {
			      JRPrintPage object = (JRPrintPage)pages.get(j);
			      jasperPrint.addPage(object);
			  }
		  }
		 
	  OutputStream out = response.getOutputStream();
	  JasperExportManager.exportReportToPdfStream(jasperPrint, out);
	  out.flush();
	return jasperPrint;	
	}

}
