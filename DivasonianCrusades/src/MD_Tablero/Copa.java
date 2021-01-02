package MD_Tablero;
import Utilidades.Facción;

public class Copa extends Casilla {
	
    private Facción facción;

    private int vida;
    
    public Copa() {
		super();
//		this.facción = 
//		this.vida = 
	}
    
    public Copa(Facción facción) {
		super();
		this.facción = facción;
//		this.vida = 
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
    
    public boolean equals(Casilla c) {

    	//Si tiene el mismo tipo de casilla (en caso de la copa (este en concreto) tiene que ser de la misma facción).
		
		if(c == null) return false;
		else if(c.getClass() != this.getClass()) return false;
		else if(((Copa) c).getFacción() != this.getFacción()) return false;
		else return true;
		
	}

}
