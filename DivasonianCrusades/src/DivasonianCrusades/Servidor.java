package DivasonianCrusades;

import java.awt.EventQueue;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Timer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import LN.Partida;
import LN.Tablero;
import Presentación.ServidorGUI;
import Utilidades.BorradorDeArchivos;
import Utilidades.BorradorDelArchivo;

public class Servidor {

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServidorGUI frame = new ServidorGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		ExecutorService pool = null;
		Timer timer = new Timer();
		 
		try {
			
			ServerSocket ss = new ServerSocket(58000);
			pool = Executors.newCachedThreadPool();
			
			timer.schedule(new BorradorDeArchivos(), 0);
			
			while(true) {
				
				Socket s1 = null;
				Socket s2 = null;
				
				ObjectOutputStream dos1 = null;
				ObjectOutputStream dos2 = null;
				ObjectInputStream dis1 = null;
				ObjectInputStream dis2 = null;
				
				BufferedWriter bw = null;
				
				try {
					
					s1 = ss.accept();
					/**/ s2 = ss.accept();
					
					s1.setKeepAlive(true); //Creo que con esto se evita el hecho de que se pierda la conexión con tiempo de espera.
					dos1 = new ObjectOutputStream(new DataOutputStream(s1.getOutputStream()));			
					dis1 = new ObjectInputStream(new DataInputStream(s1.getInputStream()));
					dos2 = new ObjectOutputStream(new DataOutputStream(s2.getOutputStream()));
					dis2 = new ObjectInputStream(new DataInputStream(s2.getInputStream()));	
					
					dos1.writeBytes("OK1-primero\r\n");
					dos1.flush();
					String caso1 = dis1.readLine();
					
					dos2.writeBytes("OK1-segundo\r\n");
					dos2.flush();
					String caso2 = dis2.readLine();
					
					if(caso1.split("-")[0].equals("NEW") && caso2.split("-")[0].equals("NEW")) {

						
//						s2 = ss.accept();
						s2.setKeepAlive(true); //Creo que con esto se evita el hecho de que se pierda la conexión con tiempo de espera.
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
						dos1.writeBytes(nombre2+"\r\n");
						dos2.writeBytes(nombre1+"\r\n");
						dos1.flush();
						dos2.flush();
						dos1.writeBytes("NEW\r\n");
						dos2.writeBytes("NEW\r\n");
						dos1.flush();
						dos2.flush();
						Partida p = new Partida(s1,s2, nombre1, nombre2, dis1, dis2, dos1, dos2);
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
					}else if(caso1.split("-")[0].equals("NEW") && caso2.split("-")[0].equals("LOAD")) {
//						s2 = ss.accept();
						s2.setKeepAlive(true); //Creo que con esto se evita el hecho de que se pierda la conexión con tiempo de espera.
						dos1.writeBytes("OK2\r\n");
						dos2.writeBytes("OK2\r\n");
						dos1.flush();
						dos2.flush();
						
						
						String nombre1 = dis1.readLine();
						String nombre2 = dis2.readLine();
						dos1.writeBytes(nombre2+"\r\n");
						dos2.writeBytes(nombre1+"\r\n");
						dos1.flush();
						dos2.flush();
						dos1.writeBytes("LOAD\r\n");
						dos2.writeBytes("NEW\r\n");
						dos1.flush();
						dos2.flush();
						String nombrePartida = dis2.readLine();
						
						//------------------- RECIBO DE TABLERO.XML -------------------------
						
						File partidaXML = new File(nombrePartida);
						
						if(partidaXML.exists()) {
							
							dos2.writeBytes("EXISTS-El fichero existe en la memoria del servidor.\r\n");
							dos2.flush();
							
						}
						else {
							
							dos2.writeBytes("NEXISTS-El fichero NO existe en la memoria del servidor.\r\n");
							dos2.flush();
							
							bw = new BufferedWriter(new FileWriter(nombrePartida));
							String lineaXML;
							while(!(lineaXML = dis2.readLine()).split("-")[0].equals("OK3")) {
								
								bw.write(lineaXML + "\r\n");
								
							}
							bw.flush();
							
							timer.schedule(new BorradorDelArchivo(nombrePartida), 10000);
							
						}
						
						//------------------------------------------------------------------
						
						String color = dis2.readLine();
						boolean azul;
						if(color.equals("A"))
							azul= true;
						else
							azul = false;					
						Partida p = new Partida(s1,s2, nombre1, nombre2, dis1, dis2, dos1, dos2, nombrePartida,!azul);
						Tablero tab =p.getTablero();
						if(azul)
							dos1.writeBytes("R\r\n");
						else
							dos1.writeBytes("A\r\n");
						dos1.flush();
						int turno = p.getTurno();
						dos1.writeInt(turno);
						dos2.writeInt(turno);
						dos1.flush();
						dos2.flush();
//						ObjectOutputStream ob2 = new ObjectOutputStream(dos2);
//						ObjectOutputStream ob1 = new ObjectOutputStream(dos1);
						dos1.writeObject(tab);
						dos1.flush();
						dos2.writeObject(tab);
						dos2.flush();
//						ObjectInputStream obi1 = new ObjectInputStream(dis1);
//						ObjectInputStream obi2 = new ObjectInputStream(dis2);
//						p.agenciarSockets(obi1, obi2,ob1, ob2,azul);
						pool.execute(p);
					}else if(caso1.split("-")[0].equals("LOAD")) {
//						s2 = ss.accept();
						s2.setKeepAlive(true); //Creo que con esto se evita el hecho de que se pierda la conexión con tiempo de espera.
						dos1.writeBytes("OK2\r\n");
						dos2.writeBytes("OK2\r\n");
						dos1.flush();
						dos2.flush();
						
						
						String nombre1 = dis1.readLine();
						String nombre2 = dis2.readLine();
						dos1.writeBytes(nombre2+"\r\n");
						dos2.writeBytes(nombre1+"\r\n");
						dos1.flush();
						dos2.flush();
						dos1.writeBytes("LOAD\r\n");
						dos2.writeBytes("LOAD\r\n");
						dos1.flush();
						dos2.flush();
						String nombrePartida = dis1.readLine();
						
						//------------------- RECIBO DE TABLERO.XML -------------------------
						
						File partidaXML = new File(nombrePartida);
						
						if(partidaXML.exists()) {
							
							dos1.writeBytes("EXISTS-El fichero existe en la memoria del servidor.\r\n");
							dos1.flush();
							
						}
						else {
							
							dos1.writeBytes("NEXISTS-El fichero NO existe en la memoria del servidor.\r\n");
							dos1.flush();
							
							bw = new BufferedWriter(new FileWriter(nombrePartida));
							String lineaXML;
							while(!(lineaXML = dis1.readLine()).split("-")[0].equals("OK3")) {
								
								bw.write(lineaXML + "\r\n");
								
							}
							bw.flush();
							
							timer.schedule(new BorradorDelArchivo(nombrePartida), 7200000);
							
						}
						
						//------------------------------------------------------------------
						
						String color = dis1.readLine();
						boolean azul;
						if(color.equals("A"))
							azul= true;
						else
							azul = false;					
						Partida p = new Partida(s1,s2, nombre1, nombre2, dis1, dis2, dos1, dos2, nombrePartida,azul);
						Tablero tab =p.getTablero();
						if(azul)
							dos2.writeBytes("R\r\n");
						else
							dos2.writeBytes("A\r\n");
						dos2.flush();
						int turno = p.getTurno();
						dos1.writeInt(turno);
						dos2.writeInt(turno);
						dos1.flush();
						dos2.flush();
//						ObjectOutputStream ob2 = new ObjectOutputStream(dos2);
//						ObjectOutputStream ob1 = new ObjectOutputStream(dos1);
						dos1.writeObject(tab);
						dos1.flush();
						dos2.writeObject(tab);
						dos2.flush();
//						ObjectInputStream obi1 = new ObjectInputStream(dis1);
//						ObjectInputStream obi2 = new ObjectInputStream(dis2);
//						p.agenciarSockets(obi1, obi2,ob1, ob2,azul);
						pool.execute(p);
					}
					
				} catch (SocketException e) {
					// TODO Auto-generated catch block
					
					if(s1 != null) {
						s1.close();
					}
					if(s2 != null) {
						s2.close();
					}
					
					e.printStackTrace();
				}catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SAXException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParserConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finally {
					
					if(bw != null) {
						
						try {
							bw.close();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}
					
				}

				
			}
			
		} 
		catch (BindException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Error: Puerto utilizado.", "Error", JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		}
		catch (IOException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Error desconocido.", "Error", JOptionPane.WARNING_MESSAGE);
			e1.printStackTrace();
		}
		finally {
			if(pool != null) {
				pool.shutdown();
			}
			salir();
		}

	}
	
	public static void salir() {
		
		System.exit(0);
		
	}

}
