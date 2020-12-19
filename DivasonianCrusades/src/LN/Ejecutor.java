package LN;

import MD_Instrucción.Instrucción;
import MD_Tablero.Ficha;
import MD_Tablero.Tablero;
import Utilidades.Dirección;
import Utilidades.Facción;

public class Ejecutor {
    public Tablero tablero;
    
    public Ejecutor(Tablero tablero) {
		
		this.tablero = tablero;
		
	}

	public Ejecutor() {
	
		this.tablero = new Tablero();
	
	}

    public void moverFicha(Ficha f, Facción fc, Dirección d) {
    	
    	if(tablero.movimientoPosible(f, fc)) {
    		
    		int desde = tablero.dóndeEstá(f,fc);
//    		int hasta = //Ojito porque aquí hay que pasar de coordenadas a posición en el vector.
    		
    		tablero.moverFicha(desde, hasta);
    		
    	}
    	
    }
    public void dispararProyectiles(int x, int y) {
    }

    public boolean haTerminado() {
    	
    	return tablero.haTerminado();
    	
    }

    public Facción getGanador() {
    	
    	return tablero.getGanador();
    	
    }

    public void resolverTurno() {
    	
    	tablero.resolverTurno();
    	//Puede que haya que hacer algo más
    	
    }

}
