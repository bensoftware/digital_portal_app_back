//package com.monetique;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.text.NumberFormat;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import com.monetique.um.dao.entities.Groupe;
//import com.monetique.um.dao.entities.Rule;
//import com.monetique.um.dao.entities.User;
//import com.monetique.um.service.IRuleService;
//import com.monetique.um.service.IUserService;
//
//
//
//
//
//
//@Component
//public class TestFCM implements CommandLineRunner{
//
//	@Autowired
//	IUserService iUserService;
//	@Autowired
//	IRuleService iRuleService;
//	@Override
//	public void run(String... args) throws Exception {
//
//
//		String FILE_NAME = "C:\\dossier\\test.xlsx";
//		
//		   FileInputStream excelFile;
//			try {
//				 excelFile = new FileInputStream(new File(FILE_NAME));
//				 Workbook workbook = new XSSFWorkbook(excelFile);
//			        Sheet datatypeSheet = workbook.getSheetAt(0);
//			        Iterator<Row> iterator = datatypeSheet.iterator();
//			        
//			        int cpt=0;
//			        
//			        // itteration ligne par ligne
//			        while (iterator.hasNext()) {
//
//		                Row currentRow = iterator.next();
//		                Iterator<Cell> cellIterator = currentRow.iterator();
//
//		         
//		                if(cpt==0) {
//		                	cpt=1;
//		                	continue;
//		                }
//		                
//		                
//		                
//		                int c=0;
//		                String item=null;
//		 
//		                String identifiant=null;
//		                
//		                String grade=null;
//		                String agence=null;
//
//		                // itteration colonne par colonne
//		                while (cellIterator.hasNext()) {
//
//		                    Cell currentCell = cellIterator.next();
//		                    //getCellTypeEnum shown as deprecated for version 3.15
//		                    //getCellTypeEnum ill be renamed to getCellType starting from version 4.0
//		                    
//		                    try {
//			                    item=  currentCell.getStringCellValue();
//
//							} catch (Exception e) {
//								// TODO: handle exception
//			                    item=  convertDoubleToString(currentCell.getNumericCellValue()) ;
//
//							}
//
//		                  
//		                    
//		                    if(c==0) {
//		                    	identifiant=item;
//		                    	
//		                    }
//		                    else if(c==1) {
//		                    	grade=item;
//		                    	
//		                    }
//		                    else if(c==2) {
//		                    	agence=item;
//		                    	
//		                    }
//		                
//		                    c++;
//
//		                }
//		                
//		                System.out.println("Ligne "+cpt+" "+identifiant+" "+grade+" "+agence);
//		                
//		                User user=null;
//		                Groupe groupe=null;
//		                Rule role=null;
//		                
//		                // recuperation ou creation de l'utilisateur
//		                
//		                // recuperarion ou creation du groupe
//		                
//		                
//		                // recuperarion du role
//		                if(grade.equals("CA")) {
//			                role= iRuleService.getRule(11);
//		                }
//		                else {
//		                	 role= iRuleService.getRule(10);
//		                }
//		                
//		                
//		                iUserService.addRuleToUser(role, user.getId());
//		                iUserService.addGroupeToUser(groupe, user.getId());
//		                
//		                cpt++;
//		            }
//
//			        
//
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			
//
//
//		System.out.println("*** FIN FIN");
//		
//	}
//	
//	public static String convertDoubleToString(Double d) {
//		NumberFormat fmt = NumberFormat.getInstance(); 
//		fmt.setGroupingUsed(false); 
//		fmt.setMaximumIntegerDigits(999); 
//		fmt.setMaximumFractionDigits(999);
//		return fmt.format(d);
//	}
//
//
//}
