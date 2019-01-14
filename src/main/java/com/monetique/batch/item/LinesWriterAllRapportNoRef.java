package com.monetique.batch.item;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemWriter;

import com.monetique.entities.ClearingRejeter;



public class LinesWriterAllRapportNoRef implements ItemWriter<ClearingRejeter>, StepExecutionListener {

    private final Logger logger = LoggerFactory.getLogger(LinesWriterAllRapportNoRef.class);
    
    int i=0;
    int ligne =1;
    Workbook workbook=new XSSFWorkbook();
    Sheet sheet;
    
    
    @Override
    public void beforeStep(StepExecution stepExecution) {
        logger.debug("Line Writer initialized.");
      
        this.sheet = (Sheet) workbook.createSheet("Relever Clearing rejeté");
        
  		Row header = sheet.createRow(0);
		  
		Cell c1=header.createCell(0);
		c1.setCellValue("typeEnregistrement");
		Cell c2=header.createCell(1);
		c2.setCellValue("numeroDeSerie");
		Cell c3=header.createCell(2);
		c3.setCellValue("dateDeProcessing");
		Cell c4=header.createCell(3);
		c4.setCellValue("numeroDeCommercant");
		Cell c5=header.createCell(4);
		c5.setCellValue("numeroDuTerminal");
		Cell c6=header.createCell(5);
		c6.setCellValue("compteCommercant");
		Cell c7=header.createCell(6);
		c7.setCellValue("nomCommercant");
		Cell c8=header.createCell(7);
		c8.setCellValue("locationCommercant");
		Cell c9=header.createCell(8);
		c9.setCellValue("territoire");
	    
		header.createCell(9).setCellValue("pan");
		header.createCell(10).setCellValue("numeroComptePorteur");
		header.createCell(11).setCellValue("dateDexpiration");
		header.createCell(12).setCellValue("flagDopposition");
		header.createCell(13).setCellValue("referenceTransaction");
		header.createCell(14).setCellValue("numeroDeRemise");
		header.createCell(15).setCellValue("dateRemise");
		header.createCell(16).setCellValue("numeroTransaction");
		header.createCell(17).setCellValue("dateDeTransaction");
		header.createCell(18).setCellValue("numeroAutorisation");
		header.createCell(19).setCellValue("montantDautorisation");
		header.createCell(20).setCellValue("montantTransactionGross");
		header.createCell(21).setCellValue("monnaie");
		header.createCell(22).setCellValue("exposantMonnaie");
		header.createCell(23).setCellValue("fraisInterchange");
		header.createCell(24).setCellValue("signeFraisInterchange");
		header.createCell(25).setCellValue("commissionFraisPorteur");
		header.createCell(26).setCellValue("montantNetCreditCommercant");
		header.createCell(27).setCellValue("commissionCommercantCredit");
		header.createCell(28).setCellValue("codeBanqueEmettrice");
		header.createCell(29).setCellValue("codeBanqueAcquereur");
		header.createCell(30).setCellValue("fraisConversion");
		header.createCell(31).setCellValue("codeCategirieCommercant");
		header.createCell(32).setCellValue("codeSysteme");
		header.createCell(33).setCellValue("fraisCentre");
		header.createCell(34).setCellValue("statusTransaction");
		header.createCell(35).setCellValue("notUsed");
		header.createCell(36).setCellValue("referenceAutorisation");
		
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        logger.debug("Line Writer ended.");
        
        Path pn = Paths.get("C://Clearing/rapport/releve_clearing_rejete_"+getDate(new Date())+".xlsx");

			
			try {
				workbook.write(Files.newOutputStream(pn));
				workbook.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
        return ExitStatus.COMPLETED;
    }

    @Override
    public void write(List<? extends ClearingRejeter> lines) throws Exception {
       
    	System.out.println("tranche "+i);
    	
    	for (ClearingRejeter c : lines) {
        	Row header = sheet.createRow(ligne);
  		  
    		Cell c1=header.createCell(0);
    		c1.setCellValue(c.getTypeEnregistrement() );
    		Cell c2=header.createCell(1);
    		c2.setCellValue(c.getNumeroDeSerie());
    		Cell c3=header.createCell(2);
    		c3.setCellValue(c.getDateDeProcessing());
    		Cell c4=header.createCell(3);
    		c4.setCellValue(c.getNumeroDeCommercant());
    		Cell c5=header.createCell(4);
    		c5.setCellValue(c.getNumeroDuTerminal());
    		Cell c6=header.createCell(5);
    		c6.setCellValue(c.getCompteCommercant());
    		Cell c7=header.createCell(6);
    		c7.setCellValue(c.getNomCommercant());
    		Cell c8=header.createCell(7);
    		c8.setCellValue(c.getLocationCommercant());
    		Cell c9=header.createCell(8);
    		c9.setCellValue(c.getTerritoire());
    	    
    		header.createCell(9).setCellValue(c.getPan());
    		header.createCell(10).setCellValue(c.getNumeroComptePorteur());
    		header.createCell(11).setCellValue(c.getDateDexpiration());
    		header.createCell(12).setCellValue(c.getFlagDopposition());
    		header.createCell(13).setCellValue(c.getReferenceTransaction());
    		header.createCell(14).setCellValue(c.getNumeroDeRemise());
    		header.createCell(15).setCellValue(c.getDateRemise());
    		header.createCell(16).setCellValue(c.getNumeroTransaction());
    		header.createCell(17).setCellValue(c.getDateDeTransaction());
    		header.createCell(18).setCellValue(c.getNumeroAutorisation());
    		header.createCell(19).setCellValue(c.getMontantDautorisation());
    		header.createCell(20).setCellValue(c.getMontantTransactionGross());
    		header.createCell(21).setCellValue(c.getMonnaie());
    		header.createCell(22).setCellValue(c.getExposantMonnaie());
    		header.createCell(23).setCellValue(c.getFraisInterchange());
    		header.createCell(24).setCellValue(c.getSigneFraisInterchange());
    		header.createCell(25).setCellValue(c.getCommissionFraisPorteur());
    		header.createCell(26).setCellValue(c.getMontantNetCreditCommercant());
    		header.createCell(27).setCellValue(c.getCommissionCommercantCredit());
    		header.createCell(28).setCellValue(c.getCodeBanqueEmettrice());
    		header.createCell(29).setCellValue(c.getCodeBanqueAcquereur());
    		header.createCell(30).setCellValue(c.getFraisConversion());
    		header.createCell(31).setCellValue(c.getCodeCategirieCommercant());
    		header.createCell(32).setCellValue(c.getCodeSysteme());
    		header.createCell(33).setCellValue(c.getFraisCentre());
    		header.createCell(34).setCellValue(c.getStatusTransaction());
    		header.createCell(35).setCellValue(c.getNotUsed());
    		header.createCell(36).setCellValue(c.getReferenceAutorisation());
    		
    		ligne++;
        }

    	i++;
    }
    
    
    
    
	private  String getDate(Date d) {
		String res="";
		
		Date date = d;
		int annee= date.getYear()+1900;
		int mois= date.getMonth()+1;
		int jour=date.getDate();
		
		String jour2,mois2;
		
		if(jour<10)
			jour2="0"+jour;
		else
			jour2=""+jour;
		
		if(mois<10)
			mois2="0"+mois;
		else
			mois2=""+mois;
		
		
		res=jour2+"-"+mois2+"-"+annee;
		
		return res;
	}

    
}
