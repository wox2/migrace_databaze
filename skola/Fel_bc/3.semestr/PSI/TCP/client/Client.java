package client;
import java.net.*;
import java.io.*;

public class Client {
	public static void main(String[] args) throws IOException {

	        Socket echoSocket = null;
	        PrintWriter out = null;
	        BufferedReader in = null;

	        try {
	            echoSocket = new Socket("localhost", 1313);
	            out = new PrintWriter(echoSocket.getOutputStream(), true);
	            in = new BufferedReader(new InputStreamReader(
	                                        echoSocket.getInputStream()));
	        } catch (UnknownHostException e) {
	            System.err.println("Don't know about host: localhost.");
	            System.exit(1);
	        } catch (IOException e) {
	            System.err.println("Couldn't get I/O for localhost");
	            System.exit(1);
	        }
		String userInput = "ahoj";
		System.out.println("sending: " + userInput);
	    out.println(userInput);
	    System.out.println("echo: " + in.readLine());
	    System.out.println("socket: " + echoSocket.toString());
	    

		out.close();
		in.close();
		echoSocket.close();
	    }

}
