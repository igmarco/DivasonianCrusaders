package MD_Tablero;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Colina extends Casilla {
	private int dañoExtra;

	public Colina() {
		super();
		this.dañoExtra = 10;
	}
	
	public Colina(int dañoExtra, HachaDivasónica hachaDivasónica) {
		super(hachaDivasónica);
		this.dañoExtra = dañoExtra;
	}
    
    public Colina(int dañoExtra, HachaDivasónica hachaDivasónica, boolean casillaDeCuración) {
		super(hachaDivasónica, casillaDeCuración);
		this.dañoExtra = dañoExtra;
	}
    
    public int getDañoExtra() {
    	
    	return this.dañoExtra;
    	
    }
    
    public Element getElemento(Document doc) {
        
        Element CasillaE = super.getElemento(doc);
        
        Element dañoExtraE = doc.createElement("dañoExtra");
        dañoExtraE.appendChild(doc.createTextNode("" + this.dañoExtra));
		
        CasillaE.appendChild(dañoExtraE);
		
		return CasillaE;
    	
    }
	
} 
