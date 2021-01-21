package Utilidades;

import java.io.File;
import java.util.TimerTask;

public class BorradorDelArchivo extends TimerTask{

	String nombreArchivo;
	
	public BorradorDelArchivo(String nombreArchivo) {
		
		this.nombreArchivo = nombreArchivo;
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		File archivo = new File(nombreArchivo);
		if(archivo.exists()) archivo.delete();
		
	}

}
