package MD_Tablero;

import Utilidades.Facción;

public class Guerrero extends Ficha {
	
	public Guerrero(Facción f) {
		
		super(null, f);
//		super.daño =
//		super.vida =
//		super.vidaMáxima =
//		super.dañoVariable =
		
	}
	
	public Guerrero(int daño, int vida, int vidaMáxima, int dañoVariable, HachaDivasónica hachaDivasónica,
			Facción facción) {
		super(daño, vida, vidaMáxima, dañoVariable, hachaDivasónica, facción);
	}
	
}
