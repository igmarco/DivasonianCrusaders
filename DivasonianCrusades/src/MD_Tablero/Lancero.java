package MD_Tablero;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import Utilidades.Facción;

public class Lancero extends Ficha {
	
    public int dañoACaballería;
    
    public Lancero(Facción f) {
		
		super(null, f);
		super.daño = 15;
		super.vida = 100;
		super.vidaMáxima = 100;
		super.dañoVariable = 5;
		this.dañoACaballería = 35;
		
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
	
//	Creo que no hay por qué redefinir realizarCarga() porque en Ficha hace referencia a realizarAtaque().
	
	public Element getElemento(Document doc) {
        
        Element FichaE = super.getElemento(doc);
		
        Element dañoACaballeríaE = doc.createElement("dañoACaballería");
        dañoACaballeríaE.appendChild(doc.createTextNode("" + this.dañoACaballería));
        
        FichaE.appendChild(dañoACaballeríaE);
		
		return FichaE;
    	
    }
    
}
