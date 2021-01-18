package DivasonianCrusades;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import LN.Partida;
import LN.Tablero;

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
				String caso = dis1.readLine();
				if(caso.split("-")[0].equals("NEW")) {

					
					Socket s2 = ss.accept();
					s2.setKeepAlive(true); //Creo que con esto se evita el hecho de que se pierda la conexión con tiempo de espera.
					dos2 = new DataOutputStream(s2.getOutputStream());
					dis2 = new DataInputStream(s2.getInputStream());	
					dos1.writeBytes("OK2\r\n");
					dos2.writeBytes("OK2\r\n");
					dos1.flush();
					dos2.flush();
					
					
					String nombre1 = dis1.readLine();
					String nombre2 = dis2.readLine();
	//				boolean terminado1=false;
	//				boolean terminado2=false;
	//				if(nombre1.split("-").equals("AB")) {
	//					terminado1 = true;
	//				}
	//				if(nombre1.split("-").equals("AB")) {
	//					terminado2 = true;
	//				}
					dos1.writeBytes(nombre2+"\n\r");
					dos2.writeBytes(nombre1+"\r\n");
					dos1.flush();
					dos2.flush();
					dos1.writeBytes("NEW\r\n");
					dos1.flush();
					dos1.writeBytes("NEW\r\n");
					dos2.writeBytes("NEW\r\n");
					dos1.flush();
					dos2.flush();
					Partida p = new Partida(s1,s2, nombre1, nombre2);
					pool.execute(p);
	//				if(!terminado1 && ! terminado2) {
	//					dos1.writeBytes(nombre2+"\n\r");
	//					dos2.writeBytes(nombre1+"\r\n");
	//					dos1.flush();
	//					dos2.flush();
	//					Partida p = new Partida(s1,s2, nombre1, nombre2);
	//					pool.execute(p);
	//				}else if(terminado1){
	//					dos2.writeBytes("Cagón");
	//					dos2.flush();
	//					dos2.writeBytes("SURR-El oponente se ha rendido.\r\n");
	//					dos2.flush();
	//				}else if(terminado2) {
	//					dos1.writeBytes("Cagón");
	//					dos1.flush();
	//					dos1.writeBytes("SURR-El oponente se ha rendido.\r\n");
	//					dos1.flush();
				}else if(caso.split("-")[0].equals("LOAD")) {
					Socket s2 = ss.accept();
					s2.setKeepAlive(true); //Creo que con esto se evita el hecho de que se pierda la conexión con tiempo de espera.
					dos2 = new DataOutputStream(s2.getOutputStream());
					dis2 = new DataInputStream(s2.getInputStream());	
					dos1.writeBytes("OK2\r\n");
					dos2.writeBytes("OK2\r\n");
					dos1.flush();
					dos2.flush();
					
					
					String nombre1 = dis1.readLine();
					String nombre2 = dis2.readLine();
					dos1.writeBytes(nombre2+"\n\r");
					dos2.writeBytes(nombre1+"\r\n");
					dos1.flush();
					dos2.flush();
					String nombrePartida = dis1.readLine();
					String color = dis1.readLine();
					boolean azul;
					if(color.equals("A"))
						azul= true;
					else
						azul = false;					
					Partida p = new Partida(s1,s2, nombre1, nombre2,nombrePartida,azul);
					Tablero tab =p.getTablero();
					ObjectOutputStream ob1 = new ObjectOutputStream(dos1);
					ob1.writeObject(tab);
					ob1.flush();
					ObjectOutputStream ob2 = new ObjectOutputStream(dos2);
					ob2.writeObject(tab);
					ob2.flush();
					ob2.writeObject(!azul);
					ob2.flush();
					pool.execute(p);
				}
				
			}
			
		}
		catch(IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
