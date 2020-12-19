package MD_Tablero;
import Utilidades.Facción;

public class Ficha {
    public int daño;

    public int vida;
    
    public int vidaMáxima;

    public int dañoVariable;

    public HachaDivasónica hachaDivasónica;

    public Facción facción;
    
    public Ficha(HachaDivasónica hachaDivasónica,
			Facción facción) {
		super();
		this.hachaDivasónica = hachaDivasónica;
		this.facción = facción;
	}
    
    public Ficha(int daño, int vida, int vidaMáxima, int dañoVariable, HachaDivasónica hachaDivasónica,
			Facción facción) {
		super();
		this.daño = daño;
		this.vida = vida;
		this.vidaMáxima = vidaMáxima;
		this.dañoVariable = dañoVariable;
		this.hachaDivasónica = hachaDivasónica;
		this.facción = facción;
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
    	
    	//Si es el mismo tipo y facción (ya que cada jugador solo tiene una de cada tipo.)
    	
    }

    public int realizarCarga(Ficha f) {
    	
    	return this.realizarAtaque(f);
    	
    }

}
