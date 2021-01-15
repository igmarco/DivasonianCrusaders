package MD_Tablero;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

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
    
    public Element getElemento(Document doc) {
        
        Element FichaE = super.getElemento(doc);
		
        Element dañoCargaE = doc.createElement("dañoCarga");
        dañoCargaE.appendChild(doc.createTextNode("" + this.dañoCarga));
        
        FichaE.appendChild(dañoCargaE);
		
		return FichaE;
    	
    }

}
