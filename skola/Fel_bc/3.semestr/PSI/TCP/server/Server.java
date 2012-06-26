package server;

import java.net.*;
import java.io.*;

public class Server {
    public static void main(String[] args) throws IOException {
        PrintWriter out = null;
        BufferedReader in = null;
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(1313);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 1313.");
            System.exit(1);
        }
        Socket clientSocket = null;
        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }
        System.out.println("client accepted from: " + clientSocket.getInetAddress() 
        		+ ":" + clientSocket.getPort());
        try {
        	out = new PrintWriter(clientSocket.getOutputStream(), true);
        	in = new BufferedReader(
        		new InputStreamReader(
				clientSocket.getInputStream()));
        } catch (IOException e) {
            System.err.println("Couldn't get I/O.");
            System.exit(1);
        }	
        String inputLine, outputLine;
        while ((inputLine = in.readLine()) != null) {
     	    System.out.println("request: " + inputLine);
        	outputLine = inputLine.toUpperCase();
            out.println(outputLine);
         }
	    System.out.println("server ending");
        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }
}
