package MD_Tablero;
import Utilidades.Facción;

public class Nodo {
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

    public boolean estáAquí(Ficha f) {
    	
    	boolean está = false;
    	
    	if (f.equals(fichaDefensora) || f.equals(fichaAtacante)) está = true;
    	
    	return está;
    	
    }

    public boolean estáAquí(Casilla c) {
    	
    	boolean está = false;
    	
    	if (casilla.equals(c)) está = true;
    	
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
    		
    		this.fichaDefensora.sufrirDaño(this.fichaAtacante.realizarCarga(this.fichaDefensora));
    		if(!this.fichaDefensora.estáMuerta()) {
    			
    			if(this.casilla.equals(new Colina())) this.fichaAtacante.sufrirDaño((this.fichaDefensora.realizarAtaque(this.fichaAtacante)) + ((Colina) this.casilla).getDañoExtra());
    			else this.fichaAtacante.sufrirDaño(this.fichaDefensora.realizarAtaque(this.fichaAtacante));
    			
    		}
    		
    	}
    	
    }

    public void quitarFicha(Ficha f) {
    	
    	//Cómo gestiono el hecho de que hacen pupa los que se quedan a los que se van? T.T
    	
    	if(f.equals(this.fichaDefensora)) {
    		
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
    		
    		if(this.fichaAtacante != null) {
    			
    			this.fichaDefensora.sufrirDaño(this.fichaAtacante.realizarAtaque(this.fichaDefensora));
    			
    		}
    		
    		this.fichaAtacante = null;
    		
    	}
    	
    }

    public void resolverTurno() {
    	
    	//Resolvemos combate, damos curación y sufrimos hacha. ¿Algo más? Quién sabe. Puede que resolver el disparo automático.
    	//Vale, no, resolver el disparo automático DESCARTADO.
    	//Claro, tenía que contemplar que hubiese hacha y que se la diese al último en pie (si lo hay).
    	//Epa, queda una cosa, saber si es una copa y resolver el daño también.
    	
    	this.resolverCombate();
    	this.darCuración();
    	this.sufrirHacha();
    	
    	if(this.fichaAtacante.estáMuerta()) this.fichaAtacante = null;
    	if(this.fichaDefensora.estáMuerta()) {
    		
    		//ADVERTENCIA: POSIBLES PROBLEMAS DE PROG3
    		this.fichaDefensora = this.fichaAtacante;
    		this.fichaAtacante = null;
    		
    	}
    	
    	if(this.casilla.tieneHacha() && this.fichaDefensora != null && this.fichaAtacante == null) {
    		
    		this.fichaDefensora.setHachaDivasónica(this.casilla.getHachaDivasónica());
    		this.casilla.setHachaDivasónica(null);
    		
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
    	
    	if(this.casilla.equals(new Curación())) {
    		
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

}
