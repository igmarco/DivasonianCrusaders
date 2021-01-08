package LN;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import MD_Instrucción.Instrucción;
import MD_Instrucción.Operación;
import Utilidades.Facción;

public class Partida implements Runnable {
	
	private Socket s1;
	private Socket s2;
	
	private int turno;
	private int movimiento;
    
	private String nombre1;
	private String nombre2;

	private Instrucción instrucciónFacción1;
	private Instrucción instrucciónFacción2;

	private Ejecutor ejecutor;
	
	ObjectOutputStream oos1 = null;
	ObjectOutputStream oos2 = null;
	ObjectInputStream ois1 = null;
	ObjectInputStream ois2 = null;
    
    public Partida(Socket s1, Socket s2, String nombre1, String nombre2) {
    	
    	this.s1 = s1;
    	this.s2 = s2;
    	this.nombre1 = nombre1;
    	this.nombre2 = nombre2;
    	
    	turno = 0;
    	
    	ejecutor = new Ejecutor();
    	
    }
    
    public void run() {
		
		try {
			
			oos1 = new ObjectOutputStream(s1.getOutputStream());
			oos2 = new ObjectOutputStream(s2.getOutputStream());
			ois1 = new ObjectInputStream(s1.getInputStream());
			ois2 = new ObjectInputStream(s2.getInputStream());
			
			boolean haTerminado = false;
			
			while(!haTerminado) {
				
				instrucciónFacción1 = (Instrucción) ois1.readObject();
				instrucciónFacción2 = (Instrucción) ois2.readObject();
				
				for(movimiento = 0; movimiento < 6; movimiento++) {
					
					this.ejecutarOperación();
					
					this.mandarTableros();
					
				}
				
				turno++;
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }

//    public void iniciarPartida(String nombre1, String nombre2) {
//    	
//    	//Preparamos los socketes y todo eso para iniciarse la partidella.
//    	
//    }

//    public void finalizarPartida() {
//    	
//    	//Cerrando el chiringuito y los sockets.
//    	
//    }

    public void ejecutarOperación() {
    	
    	//Hacemos lo que haya que hacer con el ejecutor.
    	Operación op1 = instrucciónFacción1.getOperacion(movimiento);
    	Operación op2 = instrucciónFacción2.getOperacion(movimiento);
    	
    	if(op1 == null) {
    		
    		if(op2 == null) {
        		
        		//No hacer nada
        		
        	}
    		else {
    			
    			//Hace cosas solo el 2
    			
    		}
    		
    	}
    	else if(op2 == null) {
    		
    		//Hace cosas solo el 1
    		
    	}
    	else {
    		
    		//Hacen cosas los dos
    		
    	}
    	
    }

    public void pasarTurno(Instrucción i1, Instrucción i2) {
    	
    	instrucciónFacción1 = i1;
    	instrucciónFacción2 = i2;
    	turno++;
    	movimiento = 0;
    	
    }

    public boolean haTerminado() {
    	
    	return ejecutor.haTerminado();
    	
    }

    public Facción getGanador() {
    	
    	return ejecutor.getGanador();
    	
    }

    public void resolverTurno() {
    	
    	//Imagino que será solo esto:
    	ejecutor.resolverTurno();
    	
    }

    public void mandarTableros() {
    	
    	//Abran sus sockets que les vamos a meter tremendos tableros
    	
    }

}
