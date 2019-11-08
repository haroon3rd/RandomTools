package RealIp;

public class RealIp {

import java.net.*;
import java.io.*;

	public static void main(String[] args) {
	
	URL whatismyip = new URL("http://checkip.amazonaws.com");
	BufferedReader in = new BufferedReader(new InputStreamReader(
	                whatismyip.openStream()));

	String ip = in.readLine(); //Gets the IP as a String.
	System.out.println(ip);
}
}
