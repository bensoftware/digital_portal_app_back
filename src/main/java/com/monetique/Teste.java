package com.monetique;

import java.io.IOException;
import java.text.ParseException;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class Teste implements CommandLineRunner {

	
	
	
	
	public static void main(String[] args) throws ParseException, IOException  {
		
		
		
/*		BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
		
		String messageClair = "secret-data";
		
		String secret="passe12345@2145@ssQQAAzzdhhgfbbnhkkk,nhgb";
		
		textEncryptor.setPasswordCharArray(secret.toCharArray());
		
		String myEncryptedText = textEncryptor.encrypt(messageClair);
		
		System.out.println(myEncryptedText);
		
		String plainText = textEncryptor.decrypt(myEncryptedText);
		
		System.out.println(plainText);*/
		
		
	/*	PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
		encryptor.setPoolSize(4);
		encryptor.setPassword("some-random-data");
		encryptor.setAlgorithm("PBEWithMD5AndTripleDES");
		String privateData = "secret-data";
		encryptor.setPassword("some-random-passwprd");
		encryptor.setAlgorithm("PBEWithMD5AndTripleDES");
		
		String encryptedText = encryptor.encrypt(privateData);
		System.out.println(encryptedText);
		
		String plainText = encryptor.decrypt(encryptedText);
		System.out.println(plainText);*/
		
	/*	String res= "1902	00070000001	0000 0000 0000 000	31.12.2021 23:59:00	200";
		
		double d=0;
		
		try {
			d=Double.parseDouble(res);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("error");
		}
		System.out.println(d);*/
		
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

/*	Reference ref= new Reference("15505519022080014657954", "05000");
		
	 Clearing cl= clearingRepository.findById(ref).get();

		
		List<Clearing>
		 list= new ArrayList<>();
		
		list.add(cl);*/
	//	list.add(clearingRepository.findById(new Reference("35442949031003000057122", "05000")).get());
	//	list.add(clearingRepository.findById(new Reference("25481049023031023070956", "05000")).get());

		//batchService.generationClearingFileByCl(list);
	}
}
