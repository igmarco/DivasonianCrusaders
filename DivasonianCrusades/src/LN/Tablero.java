package LN;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import MD_Tablero.Arquero;
import MD_Tablero.Bárbaro;
import MD_Tablero.Caballero;
import MD_Tablero.Casilla;
import MD_Tablero.Catapulta;
import MD_Tablero.Colina;
import MD_Tablero.Copa;
import MD_Tablero.Curación;
import MD_Tablero.Ficha;
import MD_Tablero.Guerrero;
import MD_Tablero.HachaDivasónica;
import MD_Tablero.Lancero;
import MD_Tablero.Nodo;
import MD_Tablero.Normal;
import Utilidades.Dirección;
import Utilidades.Facción;

public class Tablero implements Cloneable,Serializable{
    private Nodo[] nodos = new Nodo[45];

    public Tablero() {
    	nodos[18] = new Nodo(new Copa(Facción.Facción1));
		nodos[26] = new Nodo(new Copa(Facción.Facción2));
		
		nodos[5] = new Nodo(new Colina());
		nodos[39] = new Nodo(new Colina());
		
		nodos[20] = new Nodo(new Catapulta(1));
		nodos[24] = new Nodo(new Catapulta(2));
		
		nodos[2] = new Nodo(new Curación(1));
		nodos[42] = new Nodo(new Curación(2)); 
		
		nodos[22] = new Nodo(new Normal(new HachaDivasónica()));
		
		nodos[1] = new Nodo(new Normal(null, true));
		nodos[3] = new Nodo(new Normal(null, true));
		nodos[10] = new Nodo(new Normal(null, true));
		nodos[11] = new Nodo(new Normal(null, true));
		nodos[12] = new Nodo(new Normal(null, true));
		
		nodos[32] = new Nodo(new Normal(null, true));
		nodos[33] = new Nodo(new Normal(null, true));
		nodos[34] = new Nodo(new Normal(null, true));
		nodos[41] = new Nodo(new Normal(null, true));
		nodos[43] = new Nodo(new Normal(null, true));
		
		nodos[0] = new Nodo(new Lancero(Facción.Facción1));
		nodos[4] = new Nodo();
		nodos[6] = new Nodo();
		nodos[7] = new Nodo();
		
		nodos[8] = new Nodo(new Lancero(Facción.Facción2));
		nodos[9] = new Nodo(new Arquero(Facción.Facción1));
		nodos[13] = new Nodo();
		nodos[14] = new Nodo();
		nodos[15] = new Nodo();
		
		nodos[16] = new Nodo();
		nodos[17] = new Nodo(new Arquero(Facción.Facción2));
		nodos[19] = new Nodo(new Guerrero(Facción.Facción1));
		nodos[21] = new Nodo();
		nodos[23] = new Nodo();
		
		nodos[25] = new Nodo(new Guerrero(Facción.Facción2));
		nodos[27] = new Nodo(new Bárbaro(Facción.Facción1));
		nodos[28] = new Nodo();
		nodos[29] = new Nodo();
		nodos[30] = new Nodo();
		
		nodos[31]= new Nodo();
		nodos[35] = new Nodo(new Bárbaro(Facción.Facción2));
		nodos[36] = new Nodo(new Caballero(Facción.Facción1));
		nodos[37] = new Nodo();
		nodos[38] = new Nodo();
		
		nodos[40] = new Nodo();
		nodos[44] = new Nodo(new Caballero(Facción.Facción2));
	}
    
    public Tablero(Nodo[] nodos) {
		super();
		this.nodos = nodos;
	}
    
    public boolean movimientoPosible(Ficha f) {
    	
    	// Devuelve false si la ficha ha sido trabada en combate en este turno (en una operación anterior a la siguiente).
    	return (nodos[this.dóndeEstá(f)]).puedeMover(f);
    	
    }
    
    public boolean estáTrabada(Ficha f) {
    	
    	// Devuelve true si la ficha está trabada en combate.
    	return (this.hayDosFichas(this.dóndeEstá(f)));
    	
    }
    
    //VIENE DEL ANTIGUO Ejecutor, SIRVE PARA MOVER CON UNA DIRECCIÓN EN LUGAR DE CON LOS intS.
    public void moverFicha(Ficha f, Dirección d) { 
    		
    	System.out.println(f.getClass().getSimpleName() + ": " + this.dóndeEstá(f) + " " + d);
    	
    	int desde = this.dóndeEstá(f);
    	int hasta; //Ojito porque aquí hay que pasar de coordenadas a posición en el vector.
    		
    	if(d == Dirección.norte) hasta = desde - 9;
    	else if(d == Dirección.sur) hasta = desde + 9;
    	else if(d == Dirección.este) hasta = desde + 1;
    	else if(d == Dirección.oeste) hasta = desde - 1;
    	else if(d == Dirección.noreste) hasta = desde - 8;
    	else if(d == Dirección.noroeste) hasta = desde - 10;
    	else if(d == Dirección.sureste) hasta = desde + 10;
    	else /*if(d == Dirección.suroeste)*/ hasta = desde + 8;
    	
    	System.out.println(f.getClass().getSimpleName() + ": " + desde + " " + hasta);
    		
    	this.moverFicha(f, desde, hasta);
    	
    }
    
	public void moverFicha(Ficha f, int casillaOrigen, int casillaDestino) {
		
		//Mueve y resuelve combate
		if(nodos[casillaOrigen].hayDosFichas()) {
			
			nodos[casillaOrigen].ejecutarAtaqueContraHuida(f);
			
		}
		
		if(nodos[casillaOrigen].estáAquí(f)) {
			
			Ficha freal = nodos[casillaOrigen].quitarFicha(f);
			nodos[casillaDestino].ponerFicha(freal);
			
			//Si se pone en una cercana al arquero enemigo le dispara
			
			
			if(nodos[casillaDestino].hayDosFichas()) {
				
				nodos[casillaDestino].ejecutarCrga();
				nodos[casillaDestino].noPuedenMover(); //En caso de que haya muerto este método no hará nada.
				
			}
			
		}
		
    }
	
	//VIENE DEL ANTIGUO Ejecutor, SIRVE PARA MOVER CON UNA DIRECCIÓN EN LUGAR DE CON LOS intS.
	//EN CASO DE QUE UNA MUEVA Y OTRA HAGA OTRA COSA USAMOS moverFicha(), Y SI HAY QUE MOVER DOS ENTONCES LE METEMOS A moverFichasALaVez()
    public void moverFichasALaVez(Ficha f1, Dirección d1, Ficha f2, Dirección d2) {
    		
    		System.out.println(f1.getClass().getSimpleName() + ": " + this.dóndeEstá(f1) + " " + d1);
    		System.out.println(f2.getClass().getSimpleName() + ": " + this.dóndeEstá(f2) + " " + d2);
    	
    		int desde1 = this.dóndeEstá(f1);
    		int hasta1;
    		
    		if(d1 == Dirección.norte) hasta1 = desde1 - 9;
    		else if(d1 == Dirección.sur) hasta1 = desde1 + 9;
    		else if(d1 == Dirección.este) hasta1 = desde1 + 1;
    		else if(d1 == Dirección.oeste) hasta1 = desde1 - 1;
    		else if(d1 == Dirección.noreste) hasta1 = desde1 - 8;
    		else if(d1 == Dirección.noroeste) hasta1 = desde1 - 10;
    		else if(d1 == Dirección.sureste) hasta1 = desde1 + 10;
    		else /*if(d == Dirección.suroeste)*/ hasta1 = desde1 + 8;
    		
    		int desde2 = this.dóndeEstá(f2);
    		int hasta2;
    		
    		if(d2 == Dirección.norte) hasta2 = desde2 - 9;
    		else if(d2 == Dirección.sur) hasta2 = desde2 + 9;
    		else if(d2 == Dirección.este) hasta2 = desde2 + 1;
    		else if(d2 == Dirección.oeste) hasta2 = desde2 - 1;
    		else if(d2 == Dirección.noreste) hasta2 = desde2 - 8;
    		else if(d2 == Dirección.noroeste) hasta2 = desde2 - 10;
    		else if(d2 == Dirección.sureste) hasta2 = desde2 + 10;
    		else /*if(d == Dirección.suroeste)*/ hasta2 = desde2 + 8;
    		
    		System.out.println(f1.getClass().getSimpleName() + ": " + desde1 + " " + hasta1);
    		System.out.println(f2.getClass().getSimpleName() + ": " + desde2 + " " + hasta2);
    		
    		//Comrpobaciones y órdenes
    		
    		if(desde1 == desde2) {
    			
    			moverFichasDeLaMismaCasilla(f1, f2, desde1, hasta1, hasta2);
    			
    		}
    		else if(hasta1 == desde2 && hasta2 == desde1) {
    			
    			cruzarFichas(f1, f2, desde1, desde2); 
    			
    		}
    		else if(hasta1 == desde2) {
    			
    			this.moverFicha(f2, desde2, hasta2);
    			this.moverFicha(f1, desde1, hasta1);
    			
    		}
    		else if(hasta2 == desde1) {
    			
    			this.moverFicha(f1, desde1, hasta1);
    			this.moverFicha(f2, desde2, hasta2);
    			
    		}
    		else if(hasta1 == hasta2) {
    			
    			this.moverFichasALaMismaCasilla(f1, f2, desde1, desde2, hasta1);
    			
    		}
    		else {
    			
    			this.moverFicha(f1, desde1, hasta1);
    			this.moverFicha(f2, desde2, hasta2);
    			
    		}
    	
    }
	
	//NUEVO MÉTODO, PARA CUANDO DOS FICHAS SE MUEVEN A UNA SOLA CASILLA COMÚN
	public void moverFichasALaMismaCasilla(Ficha f1, Ficha f2, int casillaOrigen1, int casillaOrigen2, int casillaDestino) {
		
		//Mueve y resuelve combate
		if(nodos[casillaOrigen1].hayDosFichas()) {
			
			nodos[casillaOrigen1].ejecutarAtaqueContraHuida(f1);
			nodos[casillaOrigen2].ejecutarAtaqueContraHuida(f2);
			
		}
		
		if(nodos[casillaOrigen2].hayDosFichas()) {
			
			nodos[casillaOrigen2].ejecutarAtaqueContraHuida(f2);
			
		}
		
		if(nodos[casillaOrigen1].estáAquí(f1) && nodos[casillaOrigen2].estáAquí(f2)) {
			
			Ficha freal1 = nodos[casillaOrigen1].quitarFicha(f1);
			nodos[casillaDestino].ponerFicha(freal1);
			
			Ficha freal2 = nodos[casillaOrigen2].quitarFicha(f2);
			nodos[casillaDestino].ponerFicha(freal2);
			
			nodos[casillaDestino].ejecutarCrgasRespectivas();
			
			nodos[casillaDestino].noPuedenMover(); //En caso de que haya muerto este método no hará nada.
			
		}
		else if(nodos[casillaOrigen1].estáAquí(f1)) {
			
			Ficha freal1 = nodos[casillaOrigen1].quitarFicha(f1);
			nodos[casillaDestino].ponerFicha(freal1);
			
		}
		else if(nodos[casillaOrigen2].estáAquí(f2)) {
			
			Ficha freal2 = nodos[casillaOrigen2].quitarFicha(f2);
			nodos[casillaDestino].ponerFicha(freal2);
			
		}
		
    }
	
	public void moverFichasDeLaMismaCasilla(Ficha f1, Ficha f2, int casillaOrigen, int casillaDestino1, int casillaDestino2) {
		
		nodos[casillaOrigen].ejecutarAtaquesDeHuidas();
		
		if(nodos[casillaOrigen].estáAquí(f1)) {
			
			Ficha freal1 = nodos[casillaOrigen].quitarFicha(f1);
			nodos[casillaDestino1].ponerFicha(freal1);
			
			if(nodos[casillaDestino1].hayDosFichas()) {
				
				nodos[casillaDestino1].ejecutarCrga();
				nodos[casillaDestino1].noPuedenMover(); //En caso de que haya muerto este método no hará nada.
				
			}
			
		}
		
		if(nodos[casillaOrigen].estáAquí(f2)) {
			
			Ficha freal2 = nodos[casillaOrigen].quitarFicha(f2);
			nodos[casillaDestino2].ponerFicha(freal2);
			
			if(nodos[casillaDestino2].hayDosFichas()) {
				
				nodos[casillaDestino2].ejecutarCrga();
				nodos[casillaDestino2].noPuedenMover(); //En caso de que haya muerto este método no hará nada.
				
			}
			
		}
		
    }
	
	public void moverFichasDeLaMismaCasillaALaMismaCasilla(Ficha f1, Ficha f2, int casillaOrigen, int casillaDestino) /*Modo retranqueta*/ {
		
		
		nodos[casillaOrigen].ejecutarAtaquesDeHuidas();
		
		if(nodos[casillaOrigen].estáAquí(f1)) {
			
			Ficha freal1 = nodos[casillaOrigen].quitarFicha(f1);
			nodos[casillaDestino].ponerFicha(freal1);
			
		}
		
		if(nodos[casillaOrigen].estáAquí(f2)) {
			
			Ficha freal2 = nodos[casillaOrigen].quitarFicha(f2);
			nodos[casillaDestino].ponerFicha(freal2);
			
		}
		
    }
	
	public void cruzarFichas(Ficha f1, Ficha f2, int casillaOrigen1, int casillaOrigen2) {
		
		if (Math.random()>=0.5) {
		
			this.moverFicha(f1, casillaOrigen1, casillaOrigen2);
			
			nodos[casillaOrigen2].ejecutarCrgasRespectivas();
			
			nodos[casillaOrigen2].noPuedenMover(); //En caso de que haya muerto este método no hará nada.
		
		}
		else {
			
			this.moverFicha(f2, casillaOrigen2, casillaOrigen1);
			
			nodos[casillaOrigen1].ejecutarCrgasRespectivas();
			
			nodos[casillaOrigen1].noPuedenMover(); //En caso de que haya muerto este método no hará nada.
			
		}
		
    }

    public void resolverTurno() {
    	
    	//Resuelve el turno, haciendo en cada nodo lo que debe.
    	
    	List<Integer> dóndeDisparar1 = this.dóndeDispararFlechas(Facción.Facción1);
    	List<Integer> dóndeDisparar2 = this.dóndeDispararFlechas(Facción.Facción2);
    	
    	int i = 0;
    	
    	for(Nodo n : nodos) {
    		
    		//No hay temor de que dispare un arquero inexistente (muerto), ya que en ese caso dóndeDispararX no contendría i.
    		if(dóndeDisparar1.contains(i) && !n.hayDosFichas() && n.estáAquí(Facción.Facción2)) {
    			
    			n.recibirDisparo(((Arquero) (nodos[this.dóndeEstá(new Arquero(Facción.Facción1))].getFichaDefensora())).realizarDisparo());
    			
    		}
    		if(dóndeDisparar2.contains(i) && !n.hayDosFichas() && n.estáAquí(Facción.Facción1)) {
    			
    			n.recibirDisparo(((Arquero) (nodos[this.dóndeEstá(new Arquero(Facción.Facción2))].getFichaDefensora())).realizarDisparo());
    			
    		}
    		n.resolverTurno();
    		
    		i++;
    		
    	}
    	//Así? un poco simple en demasía, no creo que con esto esté todo.
    	//No, faltaba añadir el hecho de que hay que disparar a quien hay que disparar
    	
    }

    public boolean haTerminado() {
    	
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
    	
    	if (!algunoVivo2) return true;
    	
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
    
  //VIENE DEL ANTIGUO Ejecutor, SIRVE PARA MOVER CON UNA DIRECCIÓN EN LUGAR DE CON LOS intS.
    public void dispararProyectiles(Catapulta c, int x, int y, Ficha f) {
    	
    	this.dispararProyectiles(c, (4-y)*9 + x, f);
    	
    }
    
    public void dispararProyectiles(Catapulta catapulta, int casillaObjetivo, Ficha f) {
    	
    	//Hace daño de catapulta a los bichos que haya en la casilla casillaObjetivo.
    	//Ojo! Solo en caso de que la ficha siga ahí o no se haya trabado en combate.
    	if(f != null && f.equals(this.nodos[this.dóndeEstá(catapulta)].getFichaDefensora()) && !this.nodos[this.dóndeEstá(catapulta)].hayDosFichas()) {
    		
    		nodos[casillaObjetivo].recibirDisparo(catapulta.realizarDisparo()); 
    		nodos[casillaObjetivo].caeProyectil(); //Para indicar que aquí ha caído un proyectil este turno.
    		
    	}
    	
    }

    public boolean hayFicha(int casilla) {
    	
    	return (nodos[casilla].hayFicha());
    	
    }
    
    //OJOOOO ESTE LO HE AÑADIDO DE FREE TOTALMENTE
    public boolean hayDosFichas(int casilla) {
    	
    	return (nodos[casilla].hayDosFichas());
    	
    }

    public boolean hayFicha(Facción fc, int casilla) {
    	
    	return (nodos[casilla].hayFicha());
    	
    }
    
    public List<Integer> catapultasQuePuedesDisparar(Facción fc){
    	
    	List<Integer> listaCasillas = new ArrayList<Integer>();
    	
    	int dóndeCata1 = this.dóndeEstá(new Catapulta(1));
    	int dóndeCata2 = this.dóndeEstá(new Catapulta(2));
    	
    	if(this.nodos[dóndeCata1].estáAquí(fc) && !this.nodos[dóndeCata1].hayDosFichas()) listaCasillas.add(dóndeCata1);
    	if(this.nodos[dóndeCata2].estáAquí(fc) && !this.nodos[dóndeCata2].hayDosFichas()) listaCasillas.add(dóndeCata2);
    	
    	return listaCasillas;
    	
    }
    
    public List<Integer> dóndeDispararProyectiles(Catapulta cp) {
    	
    	//Devuelve una lista de enteros que representan las posiciones de las casillas a las que le llega el rango la catapulta dada.
    	List<Integer> posiciones = new ArrayList<Integer>();
    	
    	if(cp == null) return posiciones;
    	
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
    	
    	//Devuelve una lista de enteros que representan las posiciones de las casillas a las que le llega el rango el arquero de la facción dada.
    	List<Integer> posiciones = new ArrayList<Integer>();
    	
    	int whereIsTheArcher = this.dóndeEstá(new Arquero(fc));
    	
    	//En caso de que el arquero no esté (haya muerto) o que esté trabado en combate: No dispara a ningún sitio: F
    	if(whereIsTheArcher == -1 || nodos[whereIsTheArcher].hayDosFichas()) return posiciones;
    	
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
    	
    	if(whereIsTheArcher == 0) {
			
			posiciones.remove((Integer) 8);
			posiciones.remove((Integer) 7);
			
		}
		else if(whereIsTheArcher == 9) {
			
			posiciones.remove((Integer) 8);
			posiciones.remove((Integer) 17);
			posiciones.remove((Integer) 7);
			posiciones.remove((Integer) 16);
			
		}
		else if(whereIsTheArcher == 18) {
			
			posiciones.remove((Integer) 17);
			posiciones.remove((Integer) 26);
			posiciones.remove((Integer) 8);
			posiciones.remove((Integer) 16);
			posiciones.remove((Integer) 25);
			posiciones.remove((Integer) 7);
			
		}
		else if(whereIsTheArcher == 27) {
			
			posiciones.remove((Integer) 26);
			posiciones.remove((Integer) 35);
			posiciones.remove((Integer) 17);
			posiciones.remove((Integer) 25);
			posiciones.remove((Integer) 34);
			posiciones.remove((Integer) 16);
			
		}
		else if(whereIsTheArcher == 36) {
			
			posiciones.remove((Integer) 35);
			posiciones.remove((Integer) 44);
			posiciones.remove((Integer) 34);
			posiciones.remove((Integer) 43);
			posiciones.remove((Integer) 26);
			posiciones.remove((Integer) 25);
			
		}
		else if(whereIsTheArcher == 8) {
			
			posiciones.remove((Integer) 0);
			posiciones.remove((Integer) 1);
			posiciones.remove((Integer) 9);
			posiciones.remove((Integer) 10);
			posiciones.remove((Integer) 18);
			posiciones.remove((Integer) 19);
			
		}
		else if(whereIsTheArcher == 17) {
			
			posiciones.remove((Integer) 27);
			posiciones.remove((Integer) 28);
			posiciones.remove((Integer) 9);
			posiciones.remove((Integer) 10);
			posiciones.remove((Integer) 18);
			posiciones.remove((Integer) 19);
			
		}
		else if(whereIsTheArcher == 26) {

			posiciones.remove((Integer) 36);
			posiciones.remove((Integer) 37);
			posiciones.remove((Integer) 18);
			posiciones.remove((Integer) 19);
			posiciones.remove((Integer) 27);
			posiciones.remove((Integer) 28);
			
		}
		else if(whereIsTheArcher == 35) {
			
			posiciones.remove((Integer) 27);
			posiciones.remove((Integer) 28);
			posiciones.remove((Integer) 36);
			posiciones.remove((Integer) 37);
			
		}
		else if(whereIsTheArcher == 44) {
			
			posiciones.remove((Integer) 36);
			posiciones.remove((Integer) 37);
			
		}
    	
    	for(int i = 0; i < posiciones.size(); i++) {
    		
    		if(posiciones.get(i) < 0 || posiciones.get(i) >= 45) {
    			
    			posiciones.remove((Integer) i);
    			
    		}
    		
    	}
    	
    	return posiciones;
    	
    }
    
    //PARA ASISTIR EL MOVIMIENTO DENTRO DEL CLIENTE
    public List<Integer> dóndePuedeMover(Ficha f) {
    	List<Integer> movPosibles = new ArrayList<Integer>();
		Integer x;
    	//Faltan cosas
    	int dóndeEstá = this.dóndeEstá(f);
    	
    	if(dóndeEstá -9 >=0)
    		movPosibles.add(dóndeEstá - 9);
    	
    	if(dóndeEstá -8 >=0)
    		movPosibles.add(dóndeEstá - 8);
    	
    	if(dóndeEstá -10 >=0)
    		movPosibles.add(dóndeEstá - 10);
    	
    	if(dóndeEstá -1 >=0)
    		movPosibles.add(dóndeEstá - 1);
    	
    	if(dóndeEstá +9 <45)
    		movPosibles.add(dóndeEstá + 9);
    	
    	if(dóndeEstá +8 <45)
    		movPosibles.add(dóndeEstá + 8);
    	
    	if(dóndeEstá +10 <45)
    		movPosibles.add(dóndeEstá + 10);
    	
    	if(dóndeEstá +1 <45)
    		movPosibles.add(dóndeEstá + 1);
    	
    	int j;
    	
    	if(dóndeEstá == 0) {
			x = 8;
			movPosibles.remove(x);
			x = 17;
			movPosibles.remove(x);
			x = 26;
			movPosibles.remove(x);
			
		}
		else if(dóndeEstá == 9) {
			x = 17;
			movPosibles.remove(x);
			x = 8;
			movPosibles.remove(x);
			x = 26;
			movPosibles.remove(x);
			x = 35;
			movPosibles.remove(x);
			
		}
		else if(dóndeEstá == 18) {
			x = 26;
			movPosibles.remove(x);
			x = 17;
			movPosibles.remove(x);
			x = 35;
			movPosibles.remove(x);
			x = 8;
			movPosibles.remove(x);
		}
		else if(dóndeEstá == 27) {
			x = 35;
			movPosibles.remove(x);
			x = 26;
			movPosibles.remove(x);
			x = 44;
			movPosibles.remove(x);
		}
		else if(dóndeEstá == 36) {
			x = 44;
			movPosibles.remove(x);
			x=35;
			movPosibles.remove(x);
		}
		else if(dóndeEstá == 8) {
			x = 0;
			movPosibles.remove(x);
			x=9;
			movPosibles.remove(x);
			x = 18;
			movPosibles.remove(x);
		}
		else if(dóndeEstá == 17) {
			x=9;
			movPosibles.remove(x);
			x = 18;
			movPosibles.remove(x);
			x = 27;
			movPosibles.remove(x);
			
		}
		else if(dóndeEstá == 26) {
			x = 18;
			movPosibles.remove(x);
			x = 9;
			movPosibles.remove(x);
			x = 27;
			movPosibles.remove(x);
			x= 0;
			movPosibles.remove(x);
			x = 36;
			movPosibles.remove(x);
		}
		else if(dóndeEstá == 35) {
			x = 27;
			movPosibles.remove(x);
			x = 18;
			movPosibles.remove(x);
			x = 36;
			movPosibles.remove(x);
		}
		else if(dóndeEstá == 44) {
			x = 36;
			movPosibles.remove(x);
			x = 27;
			movPosibles.remove(x);
			
		}
    	
    	for(int i = 0; i < movPosibles.size(); i++) {
    		
//    		if(movPosibles.get(i) < 0 || movPosibles.get(i) >= 45) {
//    			
//    			j = movPosibles.remove(i);
//    			
//    		}
/*    		else */ if(nodos[movPosibles.get(i)].estáAquí(f.getFacción())) {
    			
    			j = movPosibles.remove(i);
    			
    		}
    		
    	}
    	
    	return movPosibles;
    	
    }
    
    public List<Integer> quiénesPuedenMover(Facción fc) {
    	
    	//A esto hay que quitarle los que ya han movido dos (o si es el caballo tres) veces en este turno.
    	List<Integer> movPosibles = new ArrayList<Integer>();
    	
    	for(int i = 0; i < 45; i++) {
    		
    		if(nodos[i].estáAquí(fc)) movPosibles.add(i);
    		
    	}
    	
    	return movPosibles;
    	
    }
    
   public List<Integer> quiénesNOPuedenMover(Facción fc) {
    	
    	//A esto hay que quitarle los que ya han movido dos (o si es el caballo tres) veces en este turno.
    	List<Integer> movPosibles = new ArrayList<Integer>();
    	
    	for(int i = 0; i < 45; i++) {
    		
    		if(!nodos[i].estáAquí(fc)) movPosibles.add(i);
    		
    	}
    	
    	return movPosibles;
    	
    }
    
    public Ficha quéFichaHay(Facción fc, int casilla) {
    	
    	if(nodos[casilla].estáAquí(fc)) {
    		
    		Ficha f;
    		
    		if((f = nodos[casilla].getFichaDefensora()).getFacción() == fc) return f;
    		else return nodos[casilla].getFichaAtacante();
    		
    	}
    	else return null;
    	
    }

    public Nodo getNodo(int i) {
    	
    	return this.nodos[i];
    	
    }
    
    public Object clone() {
    	
    	Object clone = null;
    	
    	Nodo[] nodosClonados = new Nodo[45];
    	
    	for(int i = 0; i < 45; i++) {
    		
    		nodosClonados[i] = (Nodo) this.nodos[i].clone();
    		
    	}
    	
    	clone = new Tablero(nodosClonados);
    	
    	return clone;
    	
    }
    
    //Métodos "mover" para que no se resulva la carga ni los daños:
    
    public void moverFichaGraficamente(Ficha f, Dirección d) {
    		
    		int desde = this.dóndeEstá(f);
    		int hasta; //Ojito porque aquí hay que pasar de coordenadas a posición en el vector.
    		
    		if(d == Dirección.norte) hasta = desde - 9;
    		else if(d == Dirección.sur) hasta = desde + 9;
    		else if(d == Dirección.este) hasta = desde + 1;
    		else if(d == Dirección.oeste) hasta = desde - 1;
    		else if(d == Dirección.noreste) hasta = desde - 8;
    		else if(d == Dirección.noroeste) hasta = desde - 10;
    		else if(d == Dirección.sureste) hasta = desde + 10;
    		else /*if(d == Dirección.suroeste)*/ hasta = desde + 8;
    		
    		this.moverFichaGraficamente(f, desde, hasta);
    	
    }
    
	public void moverFichaGraficamente(Ficha f, int casillaOrigen, int casillaDestino) {
		
		Ficha freal = nodos[casillaOrigen].quitarFicha(f);
		nodos[casillaDestino].ponerFicha(freal);
		
    }
	
	public Element getElemento(Document doc) {
		
		Element tableroE = doc.createElement("Tablero");
		
		for(int i = 0; i < 45; i++) {
			
			tableroE.appendChild(this.nodos[i].getElemento(doc));
			
		}
		
		return tableroE;
		
	}
	
	public static Tablero getFromElemento(Element e) {
        
        NodeList hijos = e.getChildNodes();
        Element hijo;
        
        Nodo[] nodos = new Nodo[45];
        int index = 0;
        
        for(int i = 0; i < hijos.getLength(); i++) {
        	
        	if(hijos.item(i).getNodeType() == Node.ELEMENT_NODE) {
        		
        		hijo = (Element) hijos.item(i);
        		
        		nodos[index] = Nodo.getFromElemento(hijo);
        		index++;
        		
        	}
        	
        }
        
        return new Tablero(nodos);
    	
    }
	
	public void limpiarProyectiles() {
		
		for(int i = 0; i < 45; i++) {
			
			nodos[i].limpiarProyectil();
			
		}
		
	}
    
}
