package MD_Tablero;
import Utilidades.Facción;

public class Copa extends Casilla {
	
    public Facción facción;

    public int vida;

    public void sufrirDaño(int daño) {
    	
    	vida = vida - daño;
    	
    }
    
    public boolean estáMuerta() {
    	
    	return (vida <= 0);
    	
    }

}
