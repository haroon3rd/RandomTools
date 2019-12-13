package getDefaultGateway;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;


public class getDefaultGateway {


	public static void main(String[] args) throws IOException {
		HashSet<String> dgwSet = defaultGateway();
		System.out.println("The DGW for this machine: " + dgwSet);

	}

	public static HashSet<String> defaultGateway () {
		HashSet<String> ipSet = new HashSet<String>();
		BufferedReader output=null;
		try {
			Process result = Runtime.getRuntime().exec("ip route show");
			output = new BufferedReader(new InputStreamReader(result.getInputStream()));
			String thisLine;
			while( (thisLine= output.readLine()) !=null) {
				String [] words = thisLine.split(" ");
				if("default".equals(words[0])){
					String ip = words[2].trim();
					if(validIP(ip))
						ipSet.add(ip);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				output.close();
			}catch(Exception e) {}
		}

		return ipSet;
	}

	public static boolean validIP (String ips) {
		if ( ips == null || ips.isEmpty() ) {
			return false;
		}
		for (String ip: ips.split(",")) {
			try {
				String[] parts = ip.split( "\\." );
				if ( parts.length != 4 ) {
					return false;
				}
				for ( String s : parts ) {
					int i = Integer.parseInt( s );
					if ( (i < 0) || (i > 255) ) {
						return false;
					}
				}
				if ( ip.endsWith(".") ) {
					return false;
				}
			} catch (NumberFormatException nfe) {
				return false;
			}

		}
		return true;
	}

}


