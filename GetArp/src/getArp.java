import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
 
class getArp
{
	private static BufferedReader br;

	public static void main(String []args)throws Exception	
	{
		 
		  File file = new File("/proc/net/arp"); 
		  br = new BufferedReader(new FileReader(file)); 
		  String st; 
		  while ((st = br.readLine()) != null) 
		  System.out.println(st); 
	}
}
