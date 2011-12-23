package client;
import java.net.*;
public class Client
{
  public static void main(String[] args)
                             throws  Exception
  {
    DatagramSocket  socket;
    DatagramPacket  packet;
    InetAddress     address, fromAddress;
    String			messageString;
    byte[]          message;
    int             fromPort, port = 1313;

    //
    // Send request
    //
    messageString = new String ("ahoj");
    socket = new DatagramSocket();
    address = InetAddress.getByName("localhost");
    message = messageString.getBytes();
    packet = new DatagramPacket(message, message.length,
                                address, port);
    socket.send(packet);

    //
    // Receive reply and print
    //
    packet = new DatagramPacket(message,
                                message.length);
    socket.receive(packet);
    int length = packet.getLength();
    fromAddress = packet.getAddress();
    fromPort = packet.getPort();
    String received = new String(packet.getData(),0,length);
    System.out.println("Received: " + received + "   from: " 
    		+ fromAddress + ":" + fromPort);
    socket.close();
  }
}