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

    public void moverFicha(Ficha f, Dirección d) {
    	
    	//ESTA COMPROBACIÓN SE HARÁ EN Partida.
//    	if(tablero.movimientoPosible(f)) {
    		
    		int desde = tablero.dóndeEstá(f);
    		int hasta; //Ojito porque aquí hay que pasar de coordenadas a posición en el vector.
    		
    		if(d == Dirección.derecha) hasta = desde + 1;
    		else if(d == Dirección.izquierda) hasta = desde - 1;
    		else if(d == Dirección.abajo) hasta = desde + 9;
    		else hasta = desde - 9;
    		
    		tablero.moverFicha(f, desde, hasta);
    		
//    	}
    	
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
    	//Pues puede ser, bro, no sé, tú sabrás
    	
    }

}
