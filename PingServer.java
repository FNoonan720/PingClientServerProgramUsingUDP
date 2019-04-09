import java.net.*;
import java.util.*;

public class PingServer {

	
	public static void main(String[] args) throws Exception {
		
		@SuppressWarnings("resource")
		DatagramSocket serverSocket = new DatagramSocket(2014); // Server will run on port no. 2014
		byte[] receiveData = new byte[1024];
	
		
		while(true) {
			// Create a random number generator for use in packet loss simulation
			Random random = new Random();
			
			// Create a datagram packet to hold incoming UDP packet
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			
			// Get the client message
			serverSocket.receive(receivePacket);
			
			String sentence = new String(receivePacket.getData(), 0, receivePacket.getLength());
			
			InetAddress ipAddress = receivePacket.getAddress(); // Get client's IP
			int port = receivePacket.getPort(); // Get client's port #
			
			// Print out the client's IP address, port #, and the message
			System.out.println("Client's Port #:\t" + port);
			System.out.println("Client's IP:\t\t" + ipAddress);
			System.out.println("Client's Message:\t" + sentence);
			
			// Capitalize the message from the client
			String capSentence = sentence.toUpperCase();
			
			// Simulate packet loss
			int rand = random.nextInt(10); // Random # in the range of 0 to 10
			
			// If rand is less than 4 we consider the packet lost and do not reply
			if(rand < 4) {
				System.out.println("\tReply not sent\n");
				continue;
			}
			
			byte[] sendData = capSentence.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, port);
			serverSocket.send(sendPacket);
			System.out.println("\tReply to client sent\n");
			
		}

	}

}
