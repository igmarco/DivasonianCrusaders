package MD_Tablero;

import Utilidades.Facción;

public class Caballero extends Ficha {
    public int dañoCarga;
    
    public Caballero(Facción f) {
		
		super(null, f);
		super.daño = 20;
		super.vida = 100;
		super.vidaMáxima = 100;
		super.dañoVariable = 10;
		this.dañoCarga = 30;
		
	}
	
	public Caballero(int daño, int vida, int vidaMáxima, int dañoVariable, HachaDivasónica hachaDivasónica,
			Facción facción, int dañoCarga) {
		super(daño, vida, vidaMáxima, dañoVariable, hachaDivasónica, facción);
		this.dañoCarga = dañoCarga;
	}
    
    public int realizarCarga(Ficha f) {
    	
    	int hd = 0;
    	if(hachaDivasónica != null) hd = hachaDivasónica.sumarDaño();
    	return (int) Math.floor((dañoCarga + (int) Math.floor(Math.random()*2*(dañoVariable)-dañoVariable) + hd)*1.2);
    	
    }
    
    public int getMovs() {
    	
    	return 3; 
    	
    }

}
