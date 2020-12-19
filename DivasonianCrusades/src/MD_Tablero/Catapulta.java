package MD_Tablero;

public class Catapulta extends Casilla {
    public int dañoProyectiles;

    public int dañoProyectilesVariable;

    public int rango;

    public int realizarDisparo() {
    	
    	return dañoProyectiles + (int) Math.floor(Math.random()*2*(dañoProyectilesVariable)-1);
    	
    }

    public int getRango() {
    }

}
