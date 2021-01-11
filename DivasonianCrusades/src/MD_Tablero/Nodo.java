package MD_Tablero;
import Utilidades.Facción;

public class Nodo implements Cloneable{
    private Casilla casilla;

    private Ficha fichaDefensora;

    private Ficha fichaAtacante;
    
    public Nodo() {
    	
    	this.casilla = new Normal();
    	this.fichaDefensora = null;
    	this.fichaAtacante = null;
    	
    }
    
    public Nodo(Casilla casilla) {
    	
    	this.casilla = casilla;
    	this.fichaDefensora = null;
    	this.fichaAtacante = null;
    	
    }
    
    public Nodo(Ficha fichaDefensora) {
    	
    	this.casilla = new Normal();
    	this.fichaDefensora = fichaDefensora;
    	this.fichaAtacante = null;
    	
    }
    
    public Nodo(Casilla casilla, Ficha fichaDefensora) {
    	
    	this.casilla = casilla;
    	this.fichaDefensora = fichaDefensora;
    	this.fichaAtacante = null;
    	
    }
    
    public Nodo(Casilla casilla, Ficha fichaDefensora, Ficha fichaAtacante) {
    	
    	this.casilla = casilla;
    	this.fichaDefensora = fichaDefensora;
    	this.fichaAtacante = fichaAtacante;
    	
    }
    
    public Nodo(Ficha fichaDefensora, Ficha fichaAtacante) {
    	
    	this.casilla = new Normal();
    	this.fichaDefensora = fichaDefensora;
    	this.fichaAtacante = fichaAtacante;
    	
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
    	
    	this.fichaAtacante.puedeMover = true;
    	
    	if(this.casilla.tieneHacha() && this.fichaDefensora != null && this.fichaAtacante == null) {
    		
    		this.fichaDefensora.setHachaDivasónica(this.casilla.getHachaDivasónica());
    		this.casilla.setHachaDivasónica(null);
    		
    	}
    	
    	if(casilla.getHachaDivasónica() != null && fichaDefensora != null && fichaAtacante == null && fichaDefensora.getHachaDivasónica() == null) {
    		
    		this.fichaDefensora.setHachaDivasónica(casilla.getHachaDivasónica());
    		
    	}
    	
    	if (this.casilla instanceof Copa && this.fichaDefensora != null && this.fichaAtacante == null && this.fichaAtacante.getFacción() != ((Copa) this.casilla).getFacción()) {
    		
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
    		
    		this.fichaDefensora.curarse(((Curación) this.casilla).curar());
        	this.fichaAtacante.curarse(((Curación) this.casilla).curar());
    		
    	}
    	
    }

    private void sufrirHacha() {
    	
    	this.fichaDefensora.sufrirHacha();
    	this.fichaAtacante.sufrirHacha();
    	
    }

    public void recibirDisparo(int daño) {
    	
    	this.fichaDefensora.sufrirDaño(daño);
    	this.fichaAtacante.sufrirDaño(daño);
    	
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
		if(!this.fichaDefensora.estáMuerta()) {
			
			if(this.casilla instanceof Colina) this.fichaAtacante.sufrirDaño((this.fichaDefensora.realizarAtaque(this.fichaAtacante)) + ((Colina) this.casilla).getDañoExtra());
			else this.fichaAtacante.sufrirDaño(this.fichaDefensora.realizarAtaque(this.fichaAtacante));
			
		}

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
    	
    	clone = new Nodo(c, fD, fA);
    	
    	return clone;
    	
    }

}
