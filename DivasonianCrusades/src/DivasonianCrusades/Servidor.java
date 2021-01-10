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
				s1.setKeepAlive(true); //Creo que con esto se evita el hecho de que se pierda la conexión con tiempo de espera.
				dos1 = new DataOutputStream(s1.getOutputStream());
				dis1 = new DataInputStream(s1.getInputStream());
				dos1.writeBytes("OK1\r\n");
				dos1.flush();
				
				Socket s2 = ss.accept();
				s2.setKeepAlive(true); //Creo que con esto se evita el hecho de que se pierda la conexión con tiempo de espera.
				dos2 = new DataOutputStream(s2.getOutputStream());
				dos1.writeBytes("OK2\r\n");
				dos2.writeBytes("OK2\r\n");
				dos1.flush();
				dos2.flush();
				
				dis2 = new DataInputStream(s2.getInputStream());
				String nombre1 = dis1.readLine();
				String nombre2 = dis2.readLine();
				dos1.writeBytes(nombre2+"\n\r");
				dos2.writeBytes(nombre1+"\r\n");
				dos1.flush();
				dos2.flush();
				Partida p = new Partida(s1,s2, nombre1, nombre2);
				pool.execute(p);
				
			}
			
		}
		catch(IOException e) {
			e.printStackTrace();
		}

	}

}
