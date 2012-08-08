package br.com.fiap.monitor.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Log {

	private File arquivo;
	
	public void getLinhas(String caminho, OutputStream out, int maxLines) throws FileNotFoundException, IOException {
		
		OutputStreamWriter writer = new OutputStreamWriter(out);
		Scanner fileScanner = new Scanner(new File(caminho));  
	    int inicioLinha=0;  
	    ArrayList<String> lineNumbers = new ArrayList<String>();    
	    while(fileScanner.hasNextLine()){  
	          String line = fileScanner.nextLine();      
	              lineNumbers.add(line);  
	    }  
	   	     if (lineNumbers.size() > maxLines){
	    	 inicioLinha = lineNumbers.size() - maxLines;
	    }else{
	   	 inicioLinha = 0;
	    }
		
	    for (int i=lineNumbers.size(); inicioLinha < i; inicioLinha++){
	    	writer.write(lineNumbers.get(inicioLinha));
	    	writer.write("\n");		
	    }
	    writer.flush();
		

	}
	
	public void getLinhas(String caminho, OutputStream out, int maxLines, String expression) throws FileNotFoundException, IOException {
	
		OutputStreamWriter writer = new OutputStreamWriter(out);
		Scanner fileScanner = new Scanner(new File(caminho));  
	    int inicioLinha=0;  
	    ArrayList<String> lineNumbers = new ArrayList<String>();  
	    Pattern pattern =  Pattern.compile(expression);
	    Matcher matcher = null;  
	    while(fileScanner.hasNextLine()){  
	          String line = fileScanner.nextLine();    
	          matcher = pattern.matcher(line);  
	          if(matcher.find()){  
	              lineNumbers.add(line);  
	  	           }  
	    }  
	   	     if (lineNumbers.size() > maxLines){
	    	 inicioLinha = lineNumbers.size() - maxLines;
	    }else{
	   	 inicioLinha = 0;
	    }
		
	    for (int i=lineNumbers.size(); inicioLinha < i; inicioLinha++){
	    	writer.write(lineNumbers.get(inicioLinha));
	    	writer.write("\n");		
	    }
	    writer.flush();
	}
	
}
