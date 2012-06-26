package server;

import java.net.*;
public class Server
{
  public static void main(String[] args)
                             throws  Exception
  {
    DatagramSocket  socket;
    DatagramPacket  packet;
    InetAddress     address;
    byte[]          message = new byte[256];
    int             port = 1313;
    int				fromPort;

    //
    // Create socket
    //
    socket = new DatagramSocket(port);
    
    //
    // Receive request and print
    //
    packet = new DatagramPacket(message, message.length);
    socket.receive(packet);
    int length = packet.getLength();
    address = packet.getAddress();
    fromPort = packet.getPort();
    String received = new String(packet.getData(),0,length);
    System.out.println("Received (" + length + "): " + received + "   from: " 
    		+ address + ":" + fromPort);
    
    //
    // Send reply and print
    //
    message = received.toUpperCase().getBytes();
    packet = new DatagramPacket(message, message.length, address, fromPort);
    socket.send(packet);
    System.out.println("Sending (" + message.length + "): " + new String(message));

    //
    // Close socket
    //
    socket.close();
  }
}