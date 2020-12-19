package MD_Tablero;
import Utilidades.Facción;

public class Nodo {
    public Casilla casilla;

    public Ficha fichaDefensora;

    public Ficha fichaAtacante;

    public boolean estáAquí(Ficha f, Facción fc) {
    	
    	boolean está = false;
    	
    	//¡OJO! ¡¡¡¡¡SI NO HAY FICHA Y ES NULL NO LO ESTAMOS CONTEMPLANDO!!!!!
    	
    	if (fichaDefensora.equals(f) || fichaAtacante.equals(f)) está = true;
    	
    	return está;
    	
    }

    public boolean estáAquí(Casilla c) {
    	
    	boolean está = false;
    	
    	if (casilla.equals(c)) está = true;
    	
    	return está;
    	
    }

    public boolean estáAquí(Facción fc) {
    	
    	boolean está = false;
    	
    	if (fichaDefensora.getFacción() == fc || fichaAtacante.getFacción() == fc) está = true;
    	
    	return está;
    	
    }

    public void ponerFicha() {
    	
    	//Por aquí me quedo, que estoy cansado. De todos modos no creo que esto sea sin parámetros, necesitará saber qué maldita ficha es.
    	
    }

    public void quitarFicha() {
    	
    	//Aquí lo mismo.
    	
    }

    public void resolverTurno() {
    	
    	//Resolvemos combate, damos curación y sufrimos hacha. ¿Algo más? Quién sabe. Puede que resolver el disparo automático.
    	
    }

	private void resolverCombate() {
    }

    private void darCuración() {
    }

    private void sufrirHacha() {
    }

    public void recibirDisparo(int daño) {
    }

    public boolean hayFicha() {
    }

    public boolean hayFicha(Facción fc) {
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

}
