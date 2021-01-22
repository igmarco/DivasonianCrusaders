package Utilidades;

import java.util.TimerTask;

import DivasonianCrusades.Servidor;

public class CerradorServidor extends TimerTask {
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Servidor.salir();
	}

}
