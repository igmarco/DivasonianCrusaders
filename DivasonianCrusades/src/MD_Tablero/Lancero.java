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
    	
		if(f instanceof Caballero) return dañoACaballería + (int) Math.floor(Math.random()*2*(dañoVariable)-1);
		else return daño + (int) Math.floor(Math.random()*2*(dañoVariable)-1);
    	
    }
	
	//Creo que no hay por qué redefinir realizarCarga() porque en Ficha hace referencia a realizarAtaque().
    
}
