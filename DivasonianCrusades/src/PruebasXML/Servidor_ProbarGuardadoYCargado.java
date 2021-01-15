package PruebasXML;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import LN.Partida;

public class Servidor_ProbarGuardadoYCargado {

	public static void main(String[] args) {
		
		ExecutorService thread = null;
		
		thread = Executors.newSingleThreadExecutor();
		
		Partida_ProbarGuardadoYCargado p = new Partida_ProbarGuardadoYCargado(new Socket(),new Socket(), "nombre1", "nombre2");
		thread.execute(p);
		
	}

}
