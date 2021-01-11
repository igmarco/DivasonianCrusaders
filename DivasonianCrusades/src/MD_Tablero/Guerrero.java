package MD_Tablero;

import Utilidades.Facción;

public class Guerrero extends Ficha {
	
	public Guerrero(Facción f) {
		
		super(null, f);
		super.daño = 15;
		super.vida = 200;
		super.vidaMáxima = 200;
		super.dañoVariable = 2;
		
	}
	
	public Guerrero(int daño, int vida, int vidaMáxima, int dañoVariable, HachaDivasónica hachaDivasónica,
			Facción facción) {
		super(daño, vida, vidaMáxima, dañoVariable, hachaDivasónica, facción);
	}
	
}
