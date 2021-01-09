package MD_Tablero;

import Utilidades.Facción;

public class Lancero extends Ficha {
	
    public int dañoACaballería;
    
    public Lancero(Facción f) {
		
		super(null, f);
//		super.daño =
//		super.vida =
//		super.vidaMáxima =
//		super.dañoVariable =
//		this.dañoACaballería =
		
	}
	
	public Lancero(int daño, int vida, int vidaMáxima, int dañoVariable, HachaDivasónica hachaDivasónica,
			Facción facción, int dañoACaballería) {
		super(daño, vida, vidaMáxima, dañoVariable, hachaDivasónica, facción);
		this.dañoACaballería = dañoACaballería;
	}
	
	public int realizarAtaque(Ficha f) {
    	
		int hd = 0;
    	if(hachaDivasónica != null) hd = hachaDivasónica.sumarDaño();
		if(f instanceof Caballero) return dañoACaballería + (int) Math.floor(Math.random()*2*(dañoVariable)-dañoVariable) + hd;
		else return daño + (int) Math.floor(Math.random()*2*(dañoVariable)-dañoVariable) + hd;
    	
    }
	
	//Creo que no hay por qué redefinir realizarCarga() porque en Ficha hace referencia a realizarAtaque().
    
}
