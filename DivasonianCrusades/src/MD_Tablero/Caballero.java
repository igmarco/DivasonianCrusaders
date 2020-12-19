package MD_Tablero;

public class Caballero extends Ficha {
    public int dañoCarga;
    
    public int realizarCarga(Ficha f) {
    	
    	return dañoCarga + (int) Math.floor(Math.random()*2*(dañoVariable)-1);
    	
    }

}
