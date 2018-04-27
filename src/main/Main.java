package main;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Main {

	public static void main(String[] args) {
		
		String host = "192.168.0.107";
		int port=49190;
		Socket sc;		
		String segmentIN="", segmentOUT="";
		DataOutputStream mensaje;
		DataInputStream entrada = null;
		
		Random ran = new Random();
		int segOut=ran.nextInt(4000);
		segmentOUT=segOut+"";
		
		try
		{
			sc = new Socket( host ,port); 
			System.out.print("Connecting to: "+sc.getInetAddress()+":"+sc.getPort());
			mensaje = new DataOutputStream(sc.getOutputStream());
			
			
			mensaje.writeBytes(segmentOUT);
			mensaje.writeBytes("SYN");
			
			segmentIN=entrada.readLine();
			
			System.out.println(segmentIN);
			
			mensaje.writeBytes(segmentOUT);
			mensaje.writeBytes("ACK");
			//mensaje.writeUTF("ACK");

			sc.close();
	
			}
		catch(Exception e)
		{
			System.out.println("Error: "+e.getMessage());
	
		}
	}
}