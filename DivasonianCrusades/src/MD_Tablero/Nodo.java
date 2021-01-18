package MD_Tablero;
import java.io.Serializable;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import Utilidades.Facción;

public class Nodo implements Cloneable,Serializable{
    private Casilla casilla;

    private Ficha fichaDefensora;

    private Ficha fichaAtacante;
    
    private boolean cayóProyectil;
    
    public Nodo() {
    	
    	this.casilla = new Normal();
    	this.fichaDefensora = null;
    	this.fichaAtacante = null;
    	this.cayóProyectil = false;
    	
    }
    
    public Nodo(Casilla casilla) {
    	
    	this.casilla = casilla;
    	this.fichaDefensora = null;
    	this.fichaAtacante = null;
    	this.cayóProyectil = false;
    	
    }
    
    public Nodo(Ficha fichaDefensora) {
    	
    	this.casilla = new Normal();
    	this.fichaDefensora = fichaDefensora;
    	this.fichaAtacante = null;
    	this.cayóProyectil = false;
    	
    }
    
    public Nodo(Casilla casilla, Ficha fichaDefensora) {
    	
    	this.casilla = casilla;
    	this.fichaDefensora = fichaDefensora;
    	this.fichaAtacante = null;
    	this.cayóProyectil = false;
    	
    }
    
    public Nodo(Casilla casilla, Ficha fichaDefensora, Ficha fichaAtacante) {
    	
    	this.casilla = casilla;
    	this.fichaDefensora = fichaDefensora;
    	this.fichaAtacante = fichaAtacante;
    	this.cayóProyectil = false;
    	
    }
    
    public Nodo(Casilla casilla, Ficha fichaDefensora, Ficha fichaAtacante, boolean cayóProyectil) {
    	
    	this.casilla = casilla;
    	this.fichaDefensora = fichaDefensora;
    	this.fichaAtacante = fichaAtacante;
    	this.cayóProyectil = cayóProyectil;
    	
    }
    
    public Nodo(Ficha fichaDefensora, Ficha fichaAtacante) {
    	
    	this.casilla = new Normal();
    	this.fichaDefensora = fichaDefensora;
    	this.fichaAtacante = fichaAtacante;
    	this.cayóProyectil = false;
    	
    }

    public boolean estáAquí(Ficha f) {
    	
    	boolean está = false;
    	
    	if (f == null) está = false;
    	else if (f.equals(fichaDefensora) || f.equals(fichaAtacante)) está = true;
    	
    	return está;
    	
    }

    public boolean estáAquí(Casilla c) {
    	
    	boolean está = false;
    	
    	if (c == null) está = false;
    	else if (casilla.equals(c)) está = true;
    	
    	return está;
    	
    }

    public boolean estáAquí(Facción fc) {
    	
    	boolean está = false;
    	
    	if (((fichaDefensora != null) && (fichaDefensora.getFacción() == fc)) || ((fichaAtacante != null) && (fichaAtacante.getFacción() == fc))) está = true;
    	
    	return está;
    	
    }

    public void ponerFicha(Ficha f) {
    	
    	if(this.fichaDefensora == null) this.fichaDefensora = f;
    	else {
    		
    		this.fichaAtacante = f;
    		
    		//Voy a intentar que el daño se haga en Tablero.
//    		this.fichaDefensora.sufrirDaño(this.fichaAtacante.realizarCarga(this.fichaDefensora));
//    		if(!this.fichaDefensora.estáMuerta()) {
//    			
//    			if(this.casilla instanceof Colina) this.fichaAtacante.sufrirDaño((this.fichaDefensora.realizarAtaque(this.fichaAtacante)) + ((Colina) this.casilla).getDañoExtra());
//    			else this.fichaAtacante.sufrirDaño(this.fichaDefensora.realizarAtaque(this.fichaAtacante));
//    			
//    		}
//    		//Falta en el else quitar la ficha, creo.
    		
    	}
    	
    }

    public Ficha quitarFicha(Ficha f) {
    	
    	Ficha freturn = null;
    	
    	if(f == null) return null;
    	
    	if(f.equals(this.fichaDefensora)) {
    		
    		freturn = this.fichaDefensora;
    		
    		//Voy a intentar que el daño se haga en Tablero.
    		
//    		if(this.fichaAtacante != null) {
//    			
//    			this.fichaDefensora.sufrirDaño(this.fichaAtacante.realizarAtaque(this.fichaDefensora));
//    			
//    		}
//    		if(this.fichaDefensora.estáMuerta()) {
//    			
//    			asdasdfghasfrhhqarfhafasdfhasdfghasdfhafhrahwerqa
//    			
//    		}
    		
    		this.fichaDefensora = this.fichaAtacante;
    		this.fichaAtacante = null;
    		
    	}
    	else if(f.equals(this.fichaAtacante)) {
    		
    		freturn = this.fichaAtacante;
    		
    		if(this.fichaAtacante != null) {
    			
    			this.fichaDefensora.sufrirDaño(this.fichaAtacante.realizarAtaque(this.fichaDefensora));
    			
    		}
    		
    		this.fichaAtacante = null;
    		
    	}
    	
    	return freturn;
    	
    }

    public void resolverTurno() {
    	
    	//Resolvemos combate, damos curación y sufrimos hacha. ¿Algo más? Quién sabe. Puede que resolver el disparo automático.
    	//Vale, no, resolver el disparo automático DESCARTADO.
    	//Claro, tenía que contemplar que hubiese hacha y que se la diese al último en pie (si lo hay).
    	//Epa, queda una cosa, saber si es una copa y resolver el daño también.
    	//Falta una última cosa. Si está el hacha divasónica tirada en el suelo, entonces la recoge la tropa.
    	
    	this.resolverCombate();
    	this.darCuración();
    	this.sufrirHacha();
    	
    	this.comprobarMuertes();
    	
    	if(this.fichaAtacante != null) this.fichaAtacante.puedeMover = true;
    	
    	if(this.casilla.tieneHacha() && this.fichaDefensora != null && this.fichaAtacante == null) {
    		
    		this.fichaDefensora.setHachaDivasónica(this.casilla.getHachaDivasónica());
    		this.casilla.setHachaDivasónica(null);
    		
    	}
    	
    	if(casilla.getHachaDivasónica() != null && fichaDefensora != null && fichaAtacante == null && fichaDefensora.getHachaDivasónica() == null) {
    		
    		this.fichaDefensora.setHachaDivasónica(casilla.getHachaDivasónica());
    		
    	}
    	
    	if (this.casilla instanceof Copa && this.fichaDefensora != null && this.fichaAtacante == null && this.fichaDefensora.getFacción() != ((Copa) this.casilla).getFacción()) { 
    		
    		((Copa) this.casilla).sufrirDaño(this.fichaDefensora.realizarAtaque());
    		
    	}
    	
    }

	private void resolverCombate() {
		
		if(this.fichaDefensora != null && this.fichaAtacante != null) {
			
			this.fichaDefensora.sufrirDaño(this.fichaAtacante.realizarAtaque(this.fichaDefensora));
    		this.fichaAtacante.sufrirDaño(this.fichaDefensora.realizarAtaque(this.fichaAtacante));
			
		}
		
    }

    private void darCuración() {
    	
    	if(this.casilla instanceof Curación) {
    		
    		if(this.fichaDefensora != null) this.fichaDefensora.curarse(((Curación) this.casilla).curar());
    		if(this.fichaAtacante != null) this.fichaAtacante.curarse(((Curación) this.casilla).curar());
    		
    	}
    	
    }

    private void sufrirHacha() {
    	
    	if(this.fichaDefensora != null) this.fichaDefensora.sufrirHacha();
    	if(this.fichaAtacante != null) this.fichaAtacante.sufrirHacha();
    	
    }

    public void recibirDisparo(int daño) {
    	
    	if(this.fichaDefensora != null)this.fichaDefensora.sufrirDaño(daño);
    	if(this.fichaAtacante != null)this.fichaAtacante.sufrirDaño(daño);
    	
    	this.comprobarMuertes();
    	
    }

    public boolean hayFicha() {
    	
    	return (this.fichaDefensora != null);
    	
    }
    
    //OJOOOO ESTE LO HE AÑADIDO DE FREE TOTALMENTE
    public boolean hayDosFichas() {
    	
    	return (this.fichaAtacante != null);
    	
    }

    //DE HECHO, CREO QUE ESTE ES IGUAL QUE EL MÉTODO estáAquí(Facción f).
//    public boolean hayFicha(Facción fc) {
//    	
//    }
    
    //ESTO LO AGREGO PARA GESTIONAR LAS CARGAS Y LAS HUIDAS DESDE Tablero.
    public void ejecutarCrga() {
    	
    	this.fichaDefensora.sufrirDaño(this.fichaAtacante.realizarCarga(this.fichaDefensora));
    	
		if(this.casilla instanceof Colina) this.fichaAtacante.sufrirDaño((this.fichaDefensora.realizarAtaque(this.fichaAtacante)) + ((Colina) this.casilla).getDañoExtra());
		else this.fichaAtacante.sufrirDaño(this.fichaDefensora.realizarAtaque(this.fichaAtacante));

		this.comprobarMuertes();
    	
    }
    
    public void ejecutarCrgasRespectivas() {
    	
    	this.fichaDefensora.sufrirDaño(this.fichaAtacante.realizarCarga(this.fichaDefensora));
    	this.fichaAtacante.sufrirDaño(this.fichaDefensora.realizarCarga(this.fichaAtacante));
    	
    	this.comprobarMuertes();
    	
    }
    
    public void ejecutarAtaqueContraHuida(Ficha f) {
    	//OJO, f ES LA FICHA QUE HUYE!! NO LA QUE ATACA
    	
    	if(f != null) {
    		
    		if(f.equals(this.fichaDefensora)) this.fichaDefensora.sufrirDaño(this.fichaAtacante.realizarCarga(this.fichaDefensora));
    		else if(f.equals(this.fichaAtacante)) this.fichaAtacante.sufrirDaño(this.fichaDefensora.realizarCarga(this.fichaAtacante));
    		
    		this.comprobarMuertes();
    		
    	}
    	
    }
    
    public void ejecutarAtaquesDeHuidas() {
    	
    	if(this.hayDosFichas()) {
    		
    		this.fichaDefensora.sufrirDaño(this.fichaAtacante.realizarCarga(this.fichaDefensora));
        	this.fichaAtacante.sufrirDaño(this.fichaDefensora.realizarCarga(this.fichaAtacante));
    		
    	}
    		
    	this.comprobarMuertes();
    	
    }
    
    private void comprobarMuertes() {
    	
    	if(this.fichaAtacante != null && this.fichaAtacante.estáMuerta()) {
    		
    		if(fichaAtacante.tieneHacha() && fichaDefensora.getHachaDivasónica() == null) {
    			
    			fichaDefensora.setHachaDivasónica(fichaAtacante.getHachaDivasónica());
    			
    		}
    		this.fichaAtacante = null;
    		
    	}
    	if(this.fichaDefensora != null && this.fichaDefensora.estáMuerta()) {
    		
    		if(fichaDefensora.tieneHacha() && casilla.getHachaDivasónica() == null) {
    			
    			casilla.setHachaDivasónica(fichaDefensora.getHachaDivasónica());
    			
    		}
    		
    		//ADVERTENCIA: POSIBLES PROBLEMAS DE PROG3
    		this.fichaDefensora = this.fichaAtacante;
    		this.fichaAtacante = null;
    		
    	}
    	
    }
    
    //POR SI SE HA TRABADO EN COMBATE ESTE TURNETT.
    public void noPuedeMover(Ficha f) {
    	
    	if (f != null) {
    		
    		if(f.equals(this.fichaAtacante)) this.fichaAtacante.puedeMover = false;
        	else if(f.equals(this.fichaDefensora)) this.fichaDefensora.puedeMover = false;
    		
    	}
    	
    }
    
    public void noPuedenMover() {
    	
    	if(this.fichaAtacante != null) this.fichaAtacante.puedeMover = false;
    	if(this.fichaDefensora != null) this.fichaDefensora.puedeMover = false;
    	
    }
    
    public boolean puedeMover(Ficha f) {
    	
    	if (f == null) return false;
    	if(f.equals(this.fichaAtacante)) return this.fichaAtacante.puedeMover;
    	else if(f.equals(this.fichaDefensora)) return this.fichaDefensora.puedeMover;
    	else return false;
    }
    
    //Getters y setters:
    
    public Casilla getCasilla() {
		return casilla;
	}

	public void setCasilla(Casilla casilla) {
		this.casilla = casilla;
	}

	public Ficha getFichaDefensora() {
		return fichaDefensora;
	}

	public void setFichaDefensora(Ficha fichaDefensora) {
		this.fichaDefensora = fichaDefensora;
	}

	public Ficha getFichaAtacante() {
		return fichaAtacante;
	}

	public void setFichaAtacante(Ficha fichaAtacante) {
		this.fichaAtacante = fichaAtacante;
	}
	
	public Ficha getFicha(Facción faccion) {
		Ficha f=null;
		if(this.getFichaDefensora().getFacción() == faccion) {
			f = this.getFichaDefensora();
		}else if(this.getFichaAtacante().getFacción() == faccion) {
			f = this.getFichaAtacante();
		}
		return f;
	}
	
	public Object clone() {
    	
    	Object clone = null;
    	
    	Casilla c = null;
    	Ficha fD = null;
    	Ficha fA = null;
    	
    	if(this.casilla != null) c = (Casilla) this.casilla.clone();
    	if(this.fichaDefensora != null) fD = (Ficha) this.fichaDefensora.clone();
    	if(this.fichaAtacante != null) fA = (Ficha) this.fichaAtacante.clone();
    	
    	clone = new Nodo(c, fD, fA, this.getCayóProyectil());
    	
    	return clone;
    	
    }
	
	public Element getElemento(Document doc) {

		Element nodoE = doc.createElement("Nodo");
		
		if(this.casilla != null) {
			Element casillaE = this.casilla.getElemento(doc);
			nodoE.appendChild(casillaE);
		}
		
		if(this.fichaDefensora != null) {
			Element fichaDefensoraE = this.fichaDefensora.getElemento(doc);
			nodoE.appendChild(fichaDefensoraE);
		}
		
		if(this.fichaAtacante != null) {
			Element fichaAtacanteE = this.fichaAtacante.getElemento(doc);
			nodoE.appendChild(fichaAtacanteE);
		}
		
		return nodoE;
		
	}
	
	public static Nodo getFromElemento(Element e) {
        
        NodeList hijos = e.getChildNodes();
        Element hijo;
        
        Casilla casilla = null;
        Ficha fichaDefensora = null;
        Ficha fichaAtacante = null;
        
        for(int i = 0; i < hijos.getLength(); i++) {
        	
        	if(hijos.item(i).getNodeType() == Node.ELEMENT_NODE) {
        		
        		hijo = (Element) hijos.item(i);
        		
        		if(hijo.getNodeName().equals("Casilla")) casilla = Casilla.getFromElemento(hijo);
        		
        		else if(hijo.getNodeName().equals("Ficha")) {
        			
        			if(fichaDefensora == null) fichaDefensora = Ficha.getFromElemento(hijo);
        			else if(fichaAtacante == null) fichaAtacante = Ficha.getFromElemento(hijo);
        			
        		}
        		
        	}
        	
        }
        
        return new Nodo(casilla, fichaDefensora, fichaAtacante);
    	
    }
	
	public void caeProyectil() {
		
		this.cayóProyectil = true;
		
	}
	
	public void limpiarProyectil() {
		
		this.cayóProyectil = false;
		
	}
	
	public boolean getCayóProyectil() {
		
		return this.cayóProyectil;
		
	}

}
