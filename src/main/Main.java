package main;
import java.io.BufferedReader;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Main {
	

	public static void main(String[] args) {
		
		String host = "127.0.0.1";
		int port=49190;
		Socket sc;		
		String segmentIN="", segmentOUT="";
		String messageIN="";
		DataOutputStream output;
		String linea;	
		int i=0;
		Random ran = new Random();
		int segOut=ran.nextInt(4000);
		segmentOUT=(segOut+i)+"";
		
		try
		{
			sc = new Socket( host ,port); 
			DataInputStream input = new DataInputStream(sc.getInputStream());
			output = new DataOutputStream(sc.getOutputStream());
			System.out.print("Connected to: "+sc.getInetAddress()+":"+sc.getPort()+"\n\n");				
			output.writeBytes(segmentOUT+"\n");
			output.flush();
			output.writeBytes("SYN\n");
			output.flush();		
			i++;
			System.out.println("===Message Sent===");
			System.out.println("==================");
			System.out.println("Segment: "+segmentOUT+"   .|\n==================\nMessage: "+"SYN"+"    .|");
			System.out.println("==================\n");
			
			segmentIN=input.readLine();				
			messageIN=input.readLine();
			
			segmentOUT=(segOut+i)+"";
			
			if(messageIN.equals("SYN+ACK")) {
				System.out.println("===Message Received===");
				System.out.println("==================");
				System.out.println("Segment: "+segmentIN+"   .|\n==================\nMessage: "+messageIN+".|");
				System.out.println("==================\n");
				Thread.sleep(4000);
				System.out.println("===Message Sent===");
				output.writeBytes(segmentOUT+"\n");
				output.writeBytes("ACK\n");
				System.out.println("==================");
				System.out.println("Segment: "+segmentOUT+"   .|\n==================\nMessage: "+"ACK"+"    .|");
				System.out.println("==================\n");
				Thread.sleep(5000);
				System.out.println("Connection closed");
				sc.close();
			}else {
				output.writeBytes("RESET");
				sc.close();
			}
		}
		catch(Exception e)
		{
			System.out.println("Error: "+e.getMessage());
	
		}
	}
	
}