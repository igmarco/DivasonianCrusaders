package MD_Tablero;

import Utilidades.Facción;

public class Arquero extends Ficha {
    public int dañoFlechas;

    public int rango;

    public int dañoFlechasVariable;
    
    public Arquero(Facción f) {
		
		super(null, f);
//		super.daño =
//		super.vida =
//		super.vidaMáxima =
//		super.dañoVariable =
//		this.dañoFlechas =
//		this.rango =
//		this.dañoFlechasVariable =
		
	}
	
	public Arquero(int daño, int vida, int vidaMáxima, int dañoVariable, HachaDivasónica hachaDivasónica,
			Facción facción, int dañoFlechas, int rango, int dañoFlechasVariable) {
		super(daño, vida, vidaMáxima, dañoVariable, hachaDivasónica, facción);
		this.dañoFlechas = dañoFlechas;
		this.rango = rango;
		this.dañoFlechasVariable = dañoFlechasVariable;
	}

    public int realizarDisparo() {
    	
    	return dañoFlechas + (int) Math.floor(Math.random()*2*(dañoFlechasVariable)-dañoFlechasVariable);
    	
    }

    public int getRango() {
    	
    	return rango;
    	
    }

}
