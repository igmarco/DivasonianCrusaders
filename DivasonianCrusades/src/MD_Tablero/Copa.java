package MD_Tablero;
import Utilidades.Facción;

public class Copa extends Casilla {
	
    public Facción facción;

    public int vida;
    
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
    
    public Copa(Facción facción, int vida) {
		super();
		this.facción = facción;
		this.vida = vida;
	}
    
    public void sufrirDaño(int daño) {
    	
    	vida = vida - daño;
    	
    }
    
    public boolean estáMuerta() {
    	
    	return (vida <= 0);
    	
    }

}
