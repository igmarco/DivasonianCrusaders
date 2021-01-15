package MD_Tablero;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import Utilidades.Facción;

public class Arquero extends Ficha {
    public int dañoFlechas;

    public int dañoFlechasVariable;
    
    public Arquero(Facción f) {
		
		super(null, f);
		super.daño = 10;
		super.vida = 50;
		super.vidaMáxima = 50;
		super.dañoVariable = 3;
		this.dañoFlechas = 10;
		this.dañoFlechasVariable = 5;
		
	}
	
	public Arquero(int daño, int vida, int vidaMáxima, int dañoVariable, HachaDivasónica hachaDivasónica,
			Facción facción, int dañoFlechas, int dañoFlechasVariable) {
		super(daño, vida, vidaMáxima, dañoVariable, hachaDivasónica, facción);
		this.dañoFlechas = dañoFlechas;
		this.dañoFlechasVariable = dañoFlechasVariable;
	}

    public int realizarDisparo() {
    	
    	return dañoFlechas + (int) Math.floor(Math.random()*2*(dañoFlechasVariable)-dañoFlechasVariable);
    	
    }
    
    public Element getElemento(Document doc) {
        
        Element FichaE = super.getElemento(doc);
		
        Element dañoFlechasE = doc.createElement("dañoFlechas");
        dañoFlechasE.appendChild(doc.createTextNode("" + this.dañoFlechas));
        
        Element dañoFlechasVariableE = doc.createElement("dañoFlechasVariable");
        dañoFlechasVariableE.appendChild(doc.createTextNode("" + this.dañoFlechasVariable));
        
        FichaE.appendChild(dañoFlechasE);
        FichaE.appendChild(dañoFlechasVariableE);
		
		return FichaE;
    	
    }

}
