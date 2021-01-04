package MD_Tablero;
import java.util.ArrayList;
import java.util.List;

import MD_Instrucción.Instrucción;
import Utilidades.Facción;

public class Tablero {
    public Nodo[] nodos = new Nodo[45];

    public Tablero() {
//		this.nodos = 
	}
    
    public Tablero(Nodo[] nodos) {
		super();
		this.nodos = nodos;
	}
    
    public boolean movimientoPosible(Ficha f, Facción fc) {
    	
    	// Devuelve false si una ficha ha sido trabada en combate en este turno (en una operación anterior a la siguiente).
    	
    }
    
	public void moverFicha(int casillaOrigen, int casillaDestino) {
		
		//Si puede, mueve la ficha.
		
    }

    public void resolverTurno() {
    	
    	//Resuelve el turno, haciendo en cada nodo lo que deba.
    	
    }

    public boolean haTerminado() {
    	
    	boolean terminado = false;
    	
    	if(((Copa) nodos[this.dóndeEstá(new Copa(Facción.Facción1))].getCasilla()).estáMuerta()) terminado = true;
    	
    	return terminado;
    	
    }

    public Facción getGanador() {
    	
    	//Indica qué facción ha ganado.
    	
    }

    public boolean comprobarInstrucción(Instrucción i, Facción fc) {
    }

    public int dóndeEstá(Ficha f, Facción fc) {
    	
    	for(int i = 0; i < 45; i++) {
    		
    		if(nodos[i].estáAquí(f, fc)) return i;
    		
    	}
    	
    }

    public int dóndeEstá(Casilla c) {
    	
    	for(int i = 0; i < 45; i++) {
    		
    		if(nodos[i].estáAquí(c)) return i;
    		
    	}
    	
    }

    public void dispararProyectiles(int casillaObjetivo) {
    	
    	//Hace daño de catapulta a los bichos que haya en la casilla casillaObjetivo.
    	
    }

    private boolean hayFicha(int casilla) {
    	
    	return (nodos[casilla].hayFicha());
    	
    }

    private boolean hayFicha(Facción fc, int casilla) {
    	
    	return (nodos[casilla].hayFicha());
    	
    }

    private List<Integer> dóndeDisparar(Catapulta cp) {
    	
    	//Devuelve una lista de enteros que representan las posiciones de las casillas a las que le llega el rango la catapulta dada.
    	List<Integer> posiciones = new ArrayList<Integer>();
    	
    	if()
    	
    	return posiciones;
    	
    }

}
