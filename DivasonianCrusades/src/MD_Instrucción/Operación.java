package MD_Instrucción;
import java.io.Serializable;

import MD_Tablero.Ficha;

public class Operación implements Serializable{
    private Ficha ficha;
    
    public Operación(Ficha ficha) {
    	
    	this.ficha = ficha;
    	
    }

    public Ficha getFicha() {
    	
    	return ficha;
    	
    }
    
    public String toString() {
    	return this.getClass().getCanonicalName()+" Ficha: "+this.getFicha().getClass().getCanonicalName();
    }

}
