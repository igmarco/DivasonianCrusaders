package MD_Tablero;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import Utilidades.Facción;

public class Bárbaro extends Ficha {
	
	public Bárbaro(Facción f) {
		
		super(null, f);
		super.daño = 30;
		super.vida = 100;
		super.vidaMáxima = 100;
		super.dañoVariable = 15;
		
	}
	
	public Bárbaro(int daño, int vida, int vidaMáxima, int dañoVariable, HachaDivasónica hachaDivasónica,
			Facción facción) {
		super(daño, vida, vidaMáxima, dañoVariable, hachaDivasónica, facción);
	}
	
}
