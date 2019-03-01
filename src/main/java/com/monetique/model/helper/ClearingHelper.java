package com.monetique.model.helper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;


public class ClearingHelper {

	
	static Stream<String> line;
	static DateFormat df=new SimpleDateFormat("ddMMyy");
	
	public static List<Path> getAllFilesNames() throws IOException {
		
		List<Path> list=new ArrayList<>();
		
		 Path path = Paths.get("C:\\Clearing\\in");
		 try(DirectoryStream<Path> listing = Files.newDirectoryStream(path)){
			 
           for(Path file : listing){
        	   if(Files.isRegularFile(file)) {
        		   list.add(file);
        	   }
				 
			 }
			
			 return list;
		 }
	}
	
	public static List<Path> getAllFilesNamesGIMTEL() throws IOException {
		
		List<Path> list=new ArrayList<>();
		
		 Path path = Paths.get("C:\\ClearingApp\\Gimtel\\in");
		 try(DirectoryStream<Path> listing = Files.newDirectoryStream(path)){
			 
           for(Path file : listing){
        	   if(Files.isRegularFile(file)) {
        		   list.add(file);
        	   }
				 
			 }
			
			 return list;
		 }
	}
	
	public static List<Path> getAllFilesNamesSS() throws IOException {
		
		List<Path> list=new ArrayList<>();
		
		 Path path = Paths.get("C:\\ClearingApp\\SS\\in");
		 try(DirectoryStream<Path> listing = Files.newDirectoryStream(path)){
			 
           for(Path file : listing){
        	   if(Files.isRegularFile(file)) {
        		   list.add(file);
        	   }
				 
			 }
			
			 return list;
		 }
	}
/*	public static DirectoryStream<Path> getAllFilesNames() throws IOException {
		 Path path = Paths.get("C:\\Clearing\\in");
		 DirectoryStream<Path> listing = Files.newDirectoryStream(path);
	     return listing;
	}*/
	
	
	public static void moveFile(String name) throws IOException {
		
		Path file = Paths.get("C:\\ClearingApp\\in\\"+name);
		 Path path = Paths.get("C:\\ClearingApp\\out\\"+name);
		 
		 if(Files.isRegularFile(file)) {
			 // deplacer vers exception
			 Files.move(file, path, StandardCopyOption.REPLACE_EXISTING);
		  }	
	}
	
  public static void moveFileGiMTEL(String name) throws IOException {
		
		Path file = Paths.get("C:\\ClearingApp\\Gimtel\\in\\"+name);
		 Path path = Paths.get("C:\\ClearingApp\\Gimtel\\archive\\"+name);
		 
		 if(Files.isRegularFile(file)) {
			 // deplacer vers exception
			 Files.move(file, path, StandardCopyOption.REPLACE_EXISTING);
		  }	
	}
  
  public static void moveFileGiMTELException(String name) throws IOException {
		
		Path file = Paths.get("C:\\ClearingApp\\Gimtel\\in\\"+name);
		 Path path = Paths.get("C:\\ClearingApp\\Gimtel\\exception\\"+name);
		 
		 if(Files.isRegularFile(file)) {
			 // deplacer vers exception
			 Files.move(file, path, StandardCopyOption.REPLACE_EXISTING);
		  }	
	}
  
  public static void moveFileSS(String name) throws IOException {
		
		Path file = Paths.get("C:\\ClearingApp\\SS\\in\\"+name);
		 Path path = Paths.get("C:\\ClearingApp\\SS\\archive\\"+name);
		 
		 if(Files.isRegularFile(file)) {
			 // deplacer vers exception
			 Files.move(file, path, StandardCopyOption.REPLACE_EXISTING);
		  }	
	}
  
  public static void moveFileSSException(String name) throws IOException {
		
		Path file = Paths.get("C:\\ClearingApp\\SS\\in\\"+name);
		 Path path = Paths.get("C:\\ClearingApp\\SS\\exception\\"+name);
		 
		 if(Files.isRegularFile(file)) {
			 // deplacer vers exception
			 Files.move(file, path, StandardCopyOption.REPLACE_EXISTING);
		  }	
	}
	
	 
	
	private static String mySubString(String myString, int start, int length) {
		int index=start-1;
	    return myString.substring(index, Math.min(index + length, myString.length()));
	}
	

	

	
	public static void closingStram() throws IOException{
	 
		line.close();

     }

	public static Iterator<String> getList(String file) throws IOException{
		
		 
		 Path path =Paths.get("C:\\Clearing\\in\\"+file);;
		 line = Files.lines(path,StandardCharsets.ISO_8859_1);
		
		Iterator<String> it= null;

		try {
			it= line.iterator();
		} catch (Exception e) {
			// TODO: handle exception
		}
			return it;
	}
	
	public static Iterator<String> getListTransactionGiMTELFile(String file) throws IOException{
		
		 
		 Path path =Paths.get("C:\\ClearingApp\\Gimtel\\in\\"+file);;
		 line = Files.lines(path,StandardCharsets.ISO_8859_1);
		
		Iterator<String> it= null;

		try {
			it= line.iterator();
		} catch (Exception e) {
			// TODO: handle exception
		}
			return it;
	}
	
	public static Iterator<String> getListTransactionSSFile(String file) throws IOException{
		
		 
		 Path path =Paths.get("C:\\ClearingApp\\SS\\in\\"+file);;
		 line = Files.lines(path,StandardCharsets.ISO_8859_1);
		
		Iterator<String> it= null;

		try {
			it= line.iterator();
		} catch (Exception e) {
			// TODO: handle exception
		}
			return it;
	}
}
