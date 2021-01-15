package MD_Tablero;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Curación extends Casilla {

	private int curación;

	private int curaciónVariable;
	
	private int identificador;
	
	public Curación() {
    	
		super();
		this.curación = 10;
		this.curaciónVariable = 5;
		this.identificador = 0;
	}

    public Curación(int identificador) {
    	
		super();
		this.curación = 10;
		this.curaciónVariable = 5;
		this.identificador = identificador;
	}
    
    public Curación(int curación, int curaciónVariable, HachaDivasónica hachaDivasónica,int identificador) {
		super(hachaDivasónica);
		this.curación = curación;
		this.curaciónVariable = curaciónVariable;
		this.identificador = identificador;
	}
    
    public int curar() {
    	
    	return curación + (int) Math.floor(Math.random()*2*(curaciónVariable)-1);
    	
    }
    
    public int getIdentificador() {
    	
    	return this.identificador;
    	
    }
    
    public int getCuración() {
    	
    	return this.curación;
    	
    }
    
    public boolean equals(Casilla c) {

    	//Si tiene el mismo tipo de casilla (en caso de la catapulta y la curación tiene que tener el identificador igual (o 0)).
		
		if(c == null) return false;
		else if(c.getClass() != this.getClass()) return false;
		else if((((Curación) c).getIdentificador() != this.getIdentificador()) && (((Curación) c).getIdentificador() != 0)) return false;
		else return true;
		
	}
    
    public Element getElemento(Document doc) {
        
        Element CasillaE = super.getElemento(doc);
        
        Element curaciónE = doc.createElement("curación");
        curaciónE.appendChild(doc.createTextNode("" + this.curación));
        
        Element curaciónVariableE = doc.createElement("curaciónVariable");
        curaciónVariableE.appendChild(doc.createTextNode("" + this.curaciónVariable));
        
        Element identificadorE = doc.createElement("identificador");
        identificadorE.appendChild(doc.createTextNode("" + this.identificador));
		
        CasillaE.appendChild(curaciónE);
        CasillaE.appendChild(curaciónVariableE);
        CasillaE.appendChild(identificadorE);
		
		return CasillaE;
    	
    }

}
