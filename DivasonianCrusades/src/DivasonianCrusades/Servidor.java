package DivasonianCrusades;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import LN.Partida;

public class Servidor {

	public static void main(String[] args) {
		
		ExecutorService pool = null;
		DataOutputStream dos1 = null;
		DataOutputStream dos2 = null;
		DataInputStream dis1 = null;
		DataInputStream dis2 = null;
		
		try {
			
			ServerSocket ss = new ServerSocket(58000);
			pool = Executors.newCachedThreadPool();
			
			while(true) {
				
				Socket s1 = ss.accept();
				dos1 = new DataOutputStream(s1.getOutputStream());
				dos1.writeBytes("OK1");
				
				Socket s2 = ss.accept();
				dos2 = new DataOutputStream(s2.getOutputStream());
				dos1.writeBytes("OK2");
				dos2.writeBytes("OK2");
				
				dis1 = new DataInputStream(s1.getInputStream());
				dis2 = new DataInputStream(s2.getInputStream());
				
				Partida p = new Partida(s1,s2, dis1.readLine(), dis2.readLine());
				pool.execute(p);
				
			}
			
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		finally {
			System.out.println("Cerrado.");
			if(pool != null)
				pool.shutdown();
		}

	}

}
