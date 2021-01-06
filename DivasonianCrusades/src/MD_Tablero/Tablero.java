package MD_Tablero;
import java.util.ArrayList;
import java.util.List;

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
    
    //ESTE MÉTODO ESTOY BASTANTE SEGURO DE QUE NO SE REQUIERE AQUÍ, YA QUE ESTA COMPROBACIÓN SE TENDRÁ QUE HACER EN LA CLASE Partida.
//    public boolean movimientoPosible(Ficha f) {
//    	
//    	// Devuelve false si la ficha ha sido trabada en combate en este turno (en una operación anterior a la siguiente).
//    	
//    }
    
    //A CAMBIO DEL ANTERIOR NECESITAREMOS ESTE PARA SABER ESTÁ TRABADO.
    public boolean estáTrabada(Ficha f) {
    	
    	// Devuelve true si la ficha está trabada en combate.
    	return (this.hayDosFichas(this.dóndeEstá(f)));
    	
    }
    
	public void moverFicha(Ficha f, int casillaOrigen, int casillaDestino) {
		
		//Mueve y resuelve combate
		
    }
	
	//NUEVO MÉTODO, PARA CUANDO DOS FICHAS SE MUEVEN A UNA SOLA CASILLA COMÚN
	public void moverFichasALaMismaCasilla(Ficha f1, Ficha f2, int casillaOrigen, int casillaDestino) {
		
		//Mueve y resuelve combate
		
    }

    public void resolverTurno() {
    	
    	//Resuelve el turno, haciendo en cada nodo lo que deba.
    	for(Nodo n : nodos) {
    		
    		n.resolverTurno();
    		
    	}
    	//Así? un poco simple en demasía, no creo que con esto esté todo.
    	
    }

    public boolean haTerminado() {
    	
    	boolean perdido = false;
    	
    	int posCopa1 = this.dóndeEstá(new Copa(Facción.Facción1));
    	int posCopa2 = this.dóndeEstá(new Copa(Facción.Facción2));
    	
    	boolean algunoVivo1 = false;
    	boolean algunoVivo2 = false;
    	
    	if(((Copa) this.nodos[posCopa1].getCasilla()).estáMuerta()) {
    		
    		return true;
    		
    	}
    	if(((Copa) this.nodos[posCopa2].getCasilla()).estáMuerta()) {
    		
    		return true;
    		
    	}
    	
    	for(int i = 0; i < 45; i++) {
    		
    		if(nodos[i].estáAquí(Facción.Facción1)) algunoVivo1 = true;
    		
    	}
    	
    	if(!algunoVivo1) return true;
    	
    	for(int i = 0; i < 45; i++) {
    		
    		if(nodos[i].estáAquí(Facción.Facción2)) algunoVivo2 = true;
    		
    	}
    	
    	if(!algunoVivo1) return true;
    	
    	return (false);
    	
    }

    public Facción getGanador() {
    	
    	//Indica qué facción ha ganado.
    	int posCopa1 = this.dóndeEstá(new Copa(Facción.Facción1));
    	int posCopa2 = this.dóndeEstá(new Copa(Facción.Facción2));
    	
    	boolean perdedor1 = false;
    	boolean perdedor2 = false;
    	boolean algunoVivo1 = false;
    	boolean algunoVivo2 = false;
    	
    	if(((Copa) this.nodos[posCopa1].getCasilla()).estáMuerta()) {
    		
    		perdedor1 = true;
    		
    	}
    	if(((Copa) this.nodos[posCopa2].getCasilla()).estáMuerta()) {
    		
    		perdedor2 = true;
    		
    	}
    	for(int i = 0; i < 45; i++) {
    		
    		if(nodos[i].estáAquí(Facción.Facción1)) algunoVivo1 = true;
    		
    	}
    	for(int i = 0; i < 45; i++) {
    		
    		if(nodos[i].estáAquí(Facción.Facción2)) algunoVivo2 = true;
    		
    	}
    	
    	if((perdedor1||!algunoVivo1) && (perdedor1||!algunoVivo1)) return Facción.Ambos;
    	else if(perdedor1||!algunoVivo1) return Facción.Facción2;
    	else if(perdedor2||!algunoVivo2) return Facción.Facción1;
    	else return null;
    	
    }

    //ESTO SERÁ NECESARIO SI QUEREMOS HACER UNA PRESENTACIÓN CON CONSOLA, SUPONGO.
//    public boolean comprobarInstrucción(Instrucción i, Facción fc) {
//    	
//    	//Comprobar si se puede ejecutar el conjunto de operaciones o no.
//    	
//    }

    public int dóndeEstá(Ficha f) {
    	
    	for(int i = 0; i < 45; i++) {
    		
    		if(nodos[i].estáAquí(f)) return i;
    		
    	}
    	
    	return -1;
    	
    }

    public int dóndeEstá(Casilla c) {
    	
    	for(int i = 0; i < 45; i++) {
    		
    		if(nodos[i].estáAquí(c)) return i;
    		
    	}
    	
    	return -1;
    	
    }
    
    public void dispararProyectiles(Catapulta catapulta, int casillaObjetivo) {
    	
    	//Hace daño de catapulta a los bichos que haya en la casilla casillaObjetivo.
    	nodos[casillaObjetivo].recibirDisparo(catapulta.realizarDisparo());
    	
    }

    private boolean hayFicha(int casilla) {
    	
    	return (nodos[casilla].hayFicha());
    	
    }
    
    //OJOOOO ESTE LO HE AÑADIDO DE FREE TOTALMENTE
    private boolean hayDosFichas(int casilla) {
    	
    	return (nodos[casilla].hayDosFichas());
    	
    }

    private boolean hayFicha(Facción fc, int casilla) {
    	
    	return (nodos[casilla].hayFicha());
    	
    }
    
    //ESTE MÉTODO NO CREO QUE LO UTILICEMOS. F
    public List<Integer> dóndeDispararProyectiles(Catapulta cp) {
    	
    	//Devuelve una lista de enteros que representan las posiciones de las casillas a las que le llega el rango la catapulta dada.
    	List<Integer> posiciones = new ArrayList<Integer>();
    	
    	if(cp.getIdentificador() == 1) {
    		
    		int posArr[] = {4,5,6,7,8,14,15,16,17,23,24,25,26,32,33,34,35,40,41,42,43,44};
    		
    		for(int i = 0; i < posArr.length; i++) {
    			
    			posiciones.add(posArr[i]);
    			
    		}
    		
    	}
    	else if(cp.getIdentificador() == 2) {
    		
    		int posArr[] = {0,1,2,3,4,9,10,11,12,18,19,20,21,27,28,29,30,36,37,38,39,40};
    		
    		for(int i = 0; i < posArr.length; i++) {
    			
    			posiciones.add(posArr[i]);
    			
    		}
    		
    	}
    	
    	return posiciones;
    	
    }
    
    public List<Integer> dóndeDispararFlechas(Facción fc) {
    	
    	//Devuelve una lista de enteros que representan las posiciones de las casillas a las que le llega el rango la catapulta dada.
    	List<Integer> posiciones = new ArrayList<Integer>();
    	
    	int whereIsTheArcher = this.dóndeEstá(new Arquero(fc));
    	
    	posiciones.add(whereIsTheArcher - 9 - 1);
    	posiciones.add(whereIsTheArcher - 9);
    	posiciones.add(whereIsTheArcher - 9 + 1);
    	posiciones.add(whereIsTheArcher - 1);
    	posiciones.add(whereIsTheArcher + 1);
    	posiciones.add(whereIsTheArcher + 9 - 1);
    	posiciones.add(whereIsTheArcher + 9);
    	posiciones.add(whereIsTheArcher + 9 + 1); 
    	if(fc == Facción.Facción1) {
    		
    		posiciones.add(whereIsTheArcher - 9 + 2);
        	posiciones.add(whereIsTheArcher + 2);
        	posiciones.add(whereIsTheArcher + 9 + 2); 
    		
    	}
    	else if(fc == Facción.Facción2) {
    		
    		posiciones.add(whereIsTheArcher - 9 - 2);
        	posiciones.add(whereIsTheArcher - 2);
        	posiciones.add(whereIsTheArcher + 9 - 2); 
    		
    	}
    	
    	int j;
    	
    	for(int i = 0; i < posiciones.size(); i++) {
    		
    		if(posiciones.get(i) < 0 || posiciones.get(i) >= 45) {
    			
    			j = posiciones.remove(i);
    			
    		}
    		
    	}
    	
    	return posiciones;
    	
    }

}
