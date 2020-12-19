package MD_Tablero;

public class Arquero extends Ficha {
    public int dañoFlechas;

    public int rango;

    public int dañoFlechasVariable;

    public int realizarDisparo() {
    	
    	return dañoFlechas + (int) Math.floor(Math.random()*2*(dañoFlechasVariable)-1);
    	
    }

    public int getRango() {
    	
    	return rango;
    	
    }

}
