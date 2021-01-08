package LN;

import MD_Tablero.Catapulta;
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
    public void dispararProyectiles(Catapulta c, int x, int y) {
    	
    	tablero.dispararProyectiles(c, (5-y)*9 + x-1);
    	
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
    
    //EN CASO DE QUE UNA MUEVA Y OTRA HAGA OTRA COSA USAMOS moverFicha(), Y SI HAY QUE MOVER DOS ENTONCES LE METEMOS A moverFichasALaVez()
    public void moverFichasALaVez(Ficha f1, Dirección d1, Ficha f2, Dirección d2) {
    		
    		int desde1 = tablero.dóndeEstá(f1);
    		int hasta1;
    		
    		if(d1 == Dirección.derecha) hasta1 = desde1 + 1;
    		else if(d1 == Dirección.izquierda) hasta1 = desde1 - 1;
    		else if(d1 == Dirección.abajo) hasta1 = desde1 + 9;
    		else hasta1 = desde1 - 9;
    		
    		int desde2 = tablero.dóndeEstá(f2);
    		int hasta2;
    		
    		if(d2 == Dirección.derecha) hasta2 = desde2 + 1;
    		else if(d2 == Dirección.izquierda) hasta2 = desde2 - 1;
    		else if(d2 == Dirección.abajo) hasta2 = desde2 + 9;
    		else hasta2 = desde2 - 9;
    		
    		//Comrpobaciones y órdenes
    		if(hasta1 == desde2) {
    			
    			tablero.moverFicha(f2, desde2, hasta2);
    			tablero.moverFicha(f1, desde1, hasta1);
    			
    		}
    		else if(hasta2 == desde1) {
    			
    			tablero.moverFicha(f1, desde1, hasta1);
    			tablero.moverFicha(f2, desde2, hasta2);
    			
    		}
    		else if(hasta1 == hasta2) {
    			
    			tablero.moverFichasALaMismaCasilla(f1, f2, desde1, desde2, hasta1);
    			
    		}
    	
    }
    
}
