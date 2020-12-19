package MD_Tablero;
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
    }
	public void moverFicha(int casillaOrigen, int casillaDestino) {
    }

    public void resolverTurno() {
    }

    public boolean haTerminado() {
    	
    	boolean terminado = false;
    	
    	if(((Copa) nodos[this.dóndeEstá(new Copa(Facción.Facción1))].getCasilla()).estáMuerta()) terminado = true;
    	
    	return terminado;
    	
    }

    public Facción getGanador() {
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
    }

    private boolean hayFicha(int casilla) {
    	
    	return (nodos[casilla].hayFicha());
    	
    }

    private boolean hayFicha(Facción fc, int casilla) {
    	
    	return (nodos[casilla].hayFicha());
    	
    }

    private List<int> dóndeDisparar(Catapulta cp) {
    	
    	//Devuelve una lista de enteros que representan las posiciones de las casillas a las que le llega el rango la catapulta dada.
    	
    }

}
