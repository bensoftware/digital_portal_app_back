package com.monetique.helper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class TransferHelper {

	public static void transferFichier(String in, String out) throws IOException {
		  Path pin = Paths.get(in);
          Path pout = Paths.get(out); 
           if(Files.isRegularFile(pin)) {
                 // Deplacer vers exception
                 Files.copy(pin, pout, StandardCopyOption.REPLACE_EXISTING);
            }    
	}
}
