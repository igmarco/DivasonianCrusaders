package MD_Tablero;

import Utilidades.Facción;

public class Caballero extends Ficha {
    public int dañoCarga;
    
    public Caballero(Facción f) {
		
		super(null, f);
//		super.daño =
//		super.vida =
//		super.vidaMáxima =
//		super.dañoVariable =
//		this.dañoCarga =
		
	}
	
	public Caballero(int daño, int vida, int vidaMáxima, int dañoVariable, HachaDivasónica hachaDivasónica,
			Facción facción, int dañoCarga) {
		super(daño, vida, vidaMáxima, dañoVariable, hachaDivasónica, facción);
		this.dañoCarga = dañoCarga;
	}
    
    public int realizarCarga(Ficha f) {
    	
    	return dañoCarga + (int) Math.floor(Math.random()*2*(dañoVariable)-1);
    	
    }

}
