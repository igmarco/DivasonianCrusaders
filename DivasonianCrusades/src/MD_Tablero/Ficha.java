package MD_Tablero;
import Utilidades.Facción;

public abstract class Ficha {
    protected int daño;

    protected int vida;
    
    protected int vidaMáxima;

    protected int dañoVariable;

    protected HachaDivasónica hachaDivasónica;

    protected Facción facción;
    
    public boolean puedeMover;
    
    public HachaDivasónica getHachaDivasónica() {
		return hachaDivasónica;
	}

	public Ficha(HachaDivasónica hachaDivasónica,
			Facción facción) {
		this.hachaDivasónica = hachaDivasónica;
		this.facción = facción;
		this.puedeMover = true;
	}
    
    public Ficha(int daño, int vida, int vidaMáxima, int dañoVariable, HachaDivasónica hachaDivasónica,
			Facción facción) {
		this.daño = daño;
		this.vida = vida;
		this.vidaMáxima = vidaMáxima;
		this.dañoVariable = dañoVariable;
		this.hachaDivasónica = hachaDivasónica;
		this.facción = facción;
		this.puedeMover = true;
	}
    
    public int realizarAtaque() {
    	
    	return daño + (int) Math.floor(Math.random()*2*(dañoVariable)-1);
    	
    }

    public int realizarAtaque(Ficha f) {
    	
    	return daño + (int) Math.floor(Math.random()*2*(dañoVariable)-1);
    	
    }

    public void sufrirDaño(int daño) {
    	
    	vida = vida - daño;
    	
    }

	public boolean estáMuerta() {
    	
    	return (vida<=0);
    	
    }

    public Facción getFacción() {
    	
    	return facción;
    	
    }

    public void sufrirHacha() {
    	
    	if(hachaDivasónica != null) {
    		
    		vida = vida - hachaDivasónica.getVidaPorTurno();
    		
    	}
    	
    }

    public void curarse(int v) {
    	
    	if(vida + v < vidaMáxima) {
    		
    		vida = vida + v;
    		
    	}
    	else {
    		
    		vida = vidaMáxima;
    		
    	}
    	
    }

    public boolean equals(Ficha f) {

    	//Si es el mismo tipo y facción (ya que cada jugador solo tiene una de cada tipo).
		
		if(f == null) return false;
		else if(f.getFacción() != this.getFacción()) return false;
		else if(f.getClass() != this.getClass()) return false;
		else return true;
		
	}

    public int realizarCarga(Ficha f) {
    	
    	return this.realizarAtaque(f);
    	
    }
    
    public void setHachaDivasónica(HachaDivasónica h) {
    	
    	this.hachaDivasónica = h;
    	
    }

}
