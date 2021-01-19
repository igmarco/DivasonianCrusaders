package MD_Tablero;

import Utilidades.Facción;

public class Guerrero extends Ficha {
	
	public Guerrero(Facción f) {
		
		super(null, f);
		super.daño = 18;
		super.vida = 150;
		super.vidaMáxima = 150;
		super.dañoVariable = 2;
		
	}
	
	public Guerrero(int daño, int vida, int vidaMáxima, int dañoVariable, HachaDivasónica hachaDivasónica,
			Facción facción) {
		super(daño, vida, vidaMáxima, dañoVariable, hachaDivasónica, facción);
	}
	
}
