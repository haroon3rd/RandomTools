import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * A server program which receives IP from client to compare it with IP retrieved from socket. When
 * a client connects, a new thread is started to handle it (Though threading was not necessary). Receiving client data,
 * comparing it, and sending the response back is all done on the thread, allowing more clients to be handled concurrently.
 */
public class IpReceiver {

    /**
     * Runs the server. When a client connects, the server spawns a new thread to do
     * the servicing and immediately returns to listening. The application limits the
     * number of threads via a thread pool.
     */
    public static void main(String[] args) throws Exception {
        try (ServerSocket listener = new ServerSocket(55555)) {
            System.out.println("The IP comparison server is running...");
            ExecutorService pool = Executors.newFixedThreadPool(20);
            while (true) {
                pool.execute(new Comparer(listener.accept()));
            }
        }
    }

    private static class Comparer implements Runnable {
        private Socket socket;

        Comparer(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            String remoteIP = null;
        	remoteIP = socket.getInetAddress().getHostAddress();
        	System.out.println("Connected at IP: " + remoteIP + ", Port: "+ socket.getPort());
        	try {
        		Scanner in = new Scanner(socket.getInputStream());   			
        		String packetIP = in.nextLine().toString();
        		System.out.println("Received IP: " + packetIP);
//        		in.close();
//        		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
//    			out.println(remoteIP);
        		if (packetIP.equals(remoteIP)) {
//        			System.out.println("Packet IP: " + packetIP + " is an exact match with the socket IP: " + remoteIP);
        			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        			out.println("Packet IP: " + packetIP + " is an exact match with the socket IP: " + remoteIP);
        		}else {
//        			System.out.println("Packet IP: " + packetIP + " does not match the socket IP: " + remoteIP);
        			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        			out.println("Your IP: " + packetIP + " does not match the socket IP: " + remoteIP);	
        		}
            } catch (Exception e) {
                System.out.println("Error:" + socket);
            } finally {
                try { socket.close(); } catch (IOException e) {}
                System.out.println("Disconnected IP: " + remoteIP + ", Port: " + socket.getPort());
            }
        }
    }
}