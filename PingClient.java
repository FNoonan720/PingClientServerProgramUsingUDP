import java.io.IOException;
import java.net.*;
import java.util.*;

public class PingClient {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		Date date;
		String localIP = "127.0.0.1";
        int port = 2014;
        
        DatagramSocket socket = new DatagramSocket();
        byte[] msg = new byte[1024];
        
        InetAddress address = InetAddress.getByName(localIP);
        
        ArrayList<String> messageList = new ArrayList<String>(Arrays.asList("This is the first message.", "This, the second message, follows the first message.",
        		"Here is the third message.", "Thus, this is the quaternary message.", "I'm the fifth of the messages.",
        		"Hear me: I am message number six. Fear number seven. For he ate nine.", "It is I, the seventh message.",
        		"Am I message eight or \"eight\"?", "I am message number nine and I fear no other message.", "I am the first double-digit message, the tenth."));
    
        for(int i = 1; i <= messageList.size(); i++) {
        	date = new Date();
        	
        	String message = messageList.get(i-1);
        	
        	System.out.println("Packet " + i + " sent at " + date.toString() + "\n\tMessage: " + message);
        	
        	String response;
        	
        	msg = message.getBytes();
        	
        	DatagramPacket packet = new DatagramPacket(msg, msg.length, address, port);
        	socket.send(packet);
        	
        	msg = new byte[1024];
        	
        	packet = new DatagramPacket(msg, msg.length);
        	
        	socket.setSoTimeout(1000);
        	try {
        		long startTime = System.nanoTime();
        		socket.receive(packet);        		
        		response = new String(packet.getData(), 0, packet.getLength());
        		long endTime = System.nanoTime();
        		long time = endTime - startTime;
            	System.out.println("\tResponse: " + response + "\n\tReceived in " + time/1000 + " microseconds.\n");
        	}
        	catch (SocketTimeoutException e) {
        		System.out.println("\tResponse: N/A\n\tTimeout reached.\n");
        	}
            
        }
        
        socket.close();
		
	}

}
