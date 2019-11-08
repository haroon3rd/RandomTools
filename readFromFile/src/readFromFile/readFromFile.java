package readFromFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner; 

public class readFromFile 

{
	private static Scanner scanner;
	private static BufferedReader bufferRead;

	public static List<String> readFileInList(String fileName) 
	{ 
	    List<String> lines = Collections.emptyList(); 
	    try
	    { 
	      lines = 
	       Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8); 
	    } 
	  
	    catch (IOException e) 
	    { 
	      // do something 
	      e.printStackTrace(); 
	    } 
	    return lines; 
	 }  
	  
	public static String readFileAsString(String fileName)throws Exception 
	{ 
	  String data = ""; 
	  data = new String(Files.readAllBytes(Paths.get(fileName))); 
	  return data; 
	}

	public static void main(String[] args) throws Exception 
	{ 
		//Just runs inside a main function
		// pass the path to the file as a parameter 
		FileReader fr = new FileReader("/home/amran/test.txt"); 
		int i; 
		while ((i=fr.read()) != -1)
			 System.out.print((char) i);
		fr.close();
		//---------------------------------
		
		//--Also runs inside a main function
		// We need to provide file path as the parameter: 
		// double backquote is to avoid compiler interpret words 
		// like \test as \t (ie. as a escape sequence) 
		File file3 = new File("/home/amran/test.txt"); 
		bufferRead = new BufferedReader(new FileReader(file3)); 
		String st; 
		
		while ((st = bufferRead.readLine()) != null)
			System.out.println(st); 
		//---------------------------------
		
		//--Also runs inside a main function
		File file1 = new File("/home/amran/test.txt"); 
		scanner = new Scanner(file1); 
		// we just need to use \\Z as delimiter 
		scanner.useDelimiter("\\Z"); 
		System.out.println(scanner.next()); 
		//----------------------------------
		
		//--Also runs inside a main function
		File file2 = new File("/home/amran/test.txt"); 
		scanner = new Scanner(file2); 
		// we just need to use \\Z as delimiter 
		scanner.useDelimiter("\\Z"); 
		System.out.println(scanner.next()); 
		//-----------------------------------
		
		// For public static List<String> readFileInList(String fileName) 
		List list = readFileInList("/home/amran/test.txt"); 
		  
	    Iterator<String> itr = list.iterator(); 
	    while (itr.hasNext()) 
	    	System.out.println(itr.next()); 
		//-----------------------------------
	    
	    //For public static String readFileAsString(String fileName)
		String data = readFileAsString("/home/amran/test.txt"); 
		System.out.println(data); 
	} 
} 