package MD_Tablero;

import Utilidades.Facción;

public class Arquero extends Ficha {
    public int dañoFlechas;

    public int rango;

    public int dañoFlechasVariable;
    
    public Arquero(Facción f) {
		
		super(null, f);
		super.daño = 10;
		super.vida = 50;
		super.vidaMáxima = 50;
		super.dañoVariable = 3;
		this.dañoFlechas = 10;
		this.dañoFlechasVariable = 5;
		
	}
	
	public Arquero(int daño, int vida, int vidaMáxima, int dañoVariable, HachaDivasónica hachaDivasónica,
			Facción facción, int dañoFlechas, int dañoFlechasVariable) {
		super(daño, vida, vidaMáxima, dañoVariable, hachaDivasónica, facción);
		this.dañoFlechas = dañoFlechas;
		this.dañoFlechasVariable = dañoFlechasVariable;
	}

    public int realizarDisparo() {
    	
    	return dañoFlechas + (int) Math.floor(Math.random()*2*(dañoFlechasVariable)-dañoFlechasVariable);
    	
    }

}
