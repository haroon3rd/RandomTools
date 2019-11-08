import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Inet4Address; 
import java.net.Socket;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Scanner;

public class IpSender {
    
	private static Scanner in;

	//Gets the local IP of the sender behind a NAT/Fire-wall.
	
//	public static String getLocalIp() {
//		String localIp = null;
//		try{
//	    	InetAddress ia = InetAddress.getLocalHost();  
//	        localIp = ia.getHostAddress();  
////	        System.out.println(localIp);
//			} catch (UnknownHostException e) {
//	        e.printStackTrace();
//	    }
//	    return localIp;
//	}
	


	public static InetAddress getLocalAddress() throws Exception {
		String temp ="11.0.0.1";
		InetAddress localAddress = null;
//	    int localPort = 0;
//	    try(final DatagramSocket socket = new DatagramSocket()){
//			  socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
//			  temp = socket.getLocalAddress().getHostAddress();
//			  localPort = socket.getLocalPort();
//	    } catch (UnknownHostException | SocketException e) {
//	        e.printStackTrace();
//	    }
//	    System.out.println("Sending IP address: " + localAddress + " using Port: " + localPort);
	    localAddress = InetAddress.getByName(temp);
	    return localAddress;
	}
	
	//Gets the real IP of the sender behind a NAT/Fire-wall.
	public static InetAddress getRouterAddress() throws IOException {
		String temp = null;
		InetAddress ipAddress = null;
		try {
			URL whatismyip = new URL("http://checkip.amazonaws.com");
	        BufferedReader in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
	        temp = in.readLine(); //you get the IP as a String
		}catch(UnknownHostException | SocketException | NullPointerException e) 
	        {
		    return null;    
//			System.out.print("NullPointerException Caught"); 
	        }
//        System.out.println("Sending IP address: " + ipAddress);
        if(in!=null) {
        in.close();
        }
        ipAddress = InetAddress.getByName(temp);
        return ipAddress;
	    
	}
	
	public static void printValues(InetAddress ip1) {
		System.out.println("For IP ~~~~~~~~~~~ : " + ip1);
        // isAnyLocalAddress() method 
        System.out.println("isAnyLocalAddress--: " + ip1.isAnyLocalAddress()); 
          
        // isLinkLocalAddress() method 
        System.out.println("isLinkLocalAddress-: " + ip1.isLinkLocalAddress()); 
          
        // isLoopbackAddress() method 
        System.out.println("isLoopbackAddress--: " + ip1.isLoopbackAddress()); 
          
        // isMCGlobal() method 
        System.out.println("isMCGlobal---------: " + ip1.isMCGlobal()); 
          
        // isMCLinkLocal() method 
        System.out.println("isMCLinkLocal------: " + ip1.isMCLinkLocal()); 
          
        // isMCNodeLocal() method 
        System.out.println("isMCNodeLocal------: " + ip1.isMCNodeLocal()); 
          
        // isMCOrgLocal() method 
        System.out.println("isMCOrgLocal-------: " + ip1.isMCOrgLocal()); 
          
        // isMCSiteLocal() method 
        System.out.println("isMCSiteLocal------: " + ip1.isMCSiteLocal()); 
          
        // isMulticastAddress() method 
        System.out.println("isMulticastAddress-: " + ip1.isMulticastAddress()); 
  
        // isSiteLocalAddress() method 
        System.out.println("isSiteLocalAddress-: " + ip1.isSiteLocalAddress()); 
		
		
	}
	
	
	public static void main(String[] args) throws Exception {
		
		InetAddress ip1 = getRouterAddress();
		printValues(ip1);
		
		InetAddress ip2 = getLocalAddress();
		printValues(ip2);
				 

		
		//        if (args.length != 1) {
//            System.err.println("Pass the server IP as the sole command line argument");
//            return;
//        }
//        String argument = args[0].toString();
//        try (Socket socket = new Socket(argument, 55555)) {
//        	String myIp = null;
//        	myIp = IpSender.getLocalAddress();
//        	in = new Scanner(socket.getInputStream());
//        	PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
//	        out.println(myIp);
//	        System.out.println(in.nextLine());
//        }
	
	}
}