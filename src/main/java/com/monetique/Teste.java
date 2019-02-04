package com.monetique;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.monetique.entities.Clearing;
import com.monetique.entities.Reference;
import com.monetique.model.helper.ClearingHelper;
import com.monetique.repositories.ClearingRepository;
import com.monetique.service.TraitementClearingBatchService;

@Component
public class Teste implements CommandLineRunner {

	
	@Autowired
	ClearingRepository clearingRepository;
	
	@Autowired
	TraitementClearingBatchService batchService ;
	
	public static void main(String[] args) throws ParseException, IOException  {
		
	/*	String dd="010617";
		
	    DateFormat df=new SimpleDateFormat("ddMMyy");

	    Date date=df.parse(dd);
	    System.out.println(date.toGMTString());*/
/*	    
	String dd="13112017";
		
	    DateFormat df=new SimpleDateFormat("ddMMyyyy");

	    Date date=df.parse(dd);
	    System.out.println(date.toGMTString());*/
	    
	    
	//    String m="53034711458874521";
	    
	  //   System.out.println(m.indexOf("530347"));  
	    
	    /*int size= m.length();
	    
	    String res= m.substring(0, size-2);
	    String res2= m.substring(size-2, size);
	    System.out.println(res);
	    System.out.println(res2);
	    
	    String montant = res+"."+res2;
	    Double mm= Double.parseDouble(montant);
	    
	    System.out.println(mm);*/
		/*
		 Path path =Paths.get("C:\\Monetique\\test.txt");

		 Stream<String> line;
		 line = Files.lines(path,StandardCharsets.ISO_8859_1);
			
			Iterator<String> it= null;

			try {
				it= line.iterator();
			} catch (Exception e) {
				// TODO: handle exception
			}
	
			
			while (it.hasNext()) {
				String item =it.next();
				System.out.println(item.length());	
				
				System.out.println(mySubString(item,401,12));
				System.out.println(mySubString(item,413,15));
				System.out.println(mySubString(item,428,3));
				System.out.println(mySubString(item,431,item.length()-430));
			}*/
/*	    File file = new File("C:\\\\Monetique\\\\test2.txt");
	    FileWriter fw;
			
	    try {
	      //Création de l'objet
	      fw = new FileWriter(file);
	      String str = "Bonjour à tous, amis Zéros !\n";
	      str += "\tComment allez-vous ? \n";
	      //On écrit la chaîne
	      fw.write(str);
	      //On ferme le flux
	      fw.close();
	


	    } catch (FileNotFoundException e) {
	      e.printStackTrace();
	    } catch (IOException e) {
	      e.printStackTrace();
	    }*/
		
		
	}

	private static String mySubString(String myString, int start, int length) {
		int index=start-1;
	    return myString.substring(index, Math.min(index + length, myString.length()));
	}

	@Override
	public void run(String... args) throws Exception {

	Reference ref= new Reference("15505519022080014657954", "05000");
		
	 Clearing cl= clearingRepository.findById(ref).get();

		
		List<Clearing>
		 list= new ArrayList<>();
		
		list.add(cl);
	//	list.add(clearingRepository.findById(new Reference("35442949031003000057122", "05000")).get());
	//	list.add(clearingRepository.findById(new Reference("25481049023031023070956", "05000")).get());

		//batchService.generationClearingFileByCl(list);
	}
}
