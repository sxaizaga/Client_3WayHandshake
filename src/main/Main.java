package main;
import java.io.BufferedReader;
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
		
		String host = "192.168.0.107";
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
			
			segmentIN=input.readLine();				
			messageIN=input.readLine();
			
			segmentOUT=(segOut+i)+"";
			
			if(messageIN.equals("SYN+ACK")) {
				System.out.println("Segment: "+segmentIN+"\nMessage: "+messageIN);
				Thread.sleep(4000);
				output.writeBytes(segmentOUT+"\n");
				output.writeBytes("ACK\n");
				Thread.sleep(5000);
				System.out.println("Closing connection");
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