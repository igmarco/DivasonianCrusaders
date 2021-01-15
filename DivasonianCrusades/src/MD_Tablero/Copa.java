package MD_Tablero;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import Utilidades.Facción;

public class Copa extends Casilla {
	
    private Facción facción;

    private int vida;
    
    public Copa() {
		super();
		this.facción = Facción.Ambos;
		this.vida = 50;
	}
    
    public Copa(Facción facción) {
		super();
		this.facción = facción;
		this.vida = 50;
	}
    
    public Copa(Facción facción, int vida, HachaDivasónica hachaDivasónica) {
		super(hachaDivasónica);
		this.facción = facción;
		this.vida = vida;
	}
    
    public void sufrirDaño(int daño) {
    	
    	vida = vida - daño;
    	
    }
    
    public boolean estáMuerta() {
    	
    	return (vida <= 0);
    	
    }
    
    public Facción getFacción() {
    	
    	return this.facción;
    	
    }
    
    public int getVida() {
    	
    	return this.vida;
    	
    }
    
    public boolean equals(Casilla c) {

    	//Si tiene el mismo tipo de casilla (en caso de la copa (este en concreto) tiene que ser de la misma facción).
		
		if(c == null) return false;
		else if(c.getClass() != this.getClass()) return false;
		else if(((Copa) c).getFacción() != this.getFacción()) return false;
		else return true;
		
	}
    
    public Element getElemento(Document doc) {
        
        Element CasillaE = super.getElemento(doc);
        
        Element facciónE = doc.createElement("facción");
        if(this.facción == Facción.Ambos) facciónE.appendChild(doc.createTextNode("Ambos"));
        else if(this.facción == Facción.Facción1) facciónE.appendChild(doc.createTextNode("Facción1"));
        else /* if(this.facción == Facción.Facción2)*/ facciónE.appendChild(doc.createTextNode("Facción2"));
        
        Element vidaE = doc.createElement("vida");
        vidaE.appendChild(doc.createTextNode("" + this.vida));
		
        CasillaE.appendChild(vidaE);
        CasillaE.appendChild(facciónE);
		
		return CasillaE;
    	
    }

}
