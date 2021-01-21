package Utilidades;

import java.io.File;
import java.util.TimerTask;

public class BorradorDeArchivos extends TimerTask{
	
	public BorradorDeArchivos() {
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		File carpeta = new File("PartidasGuardadasServidor");
		
		for(File f : carpeta.listFiles())
			f.delete();
		
	}

}
