package MD_Tablero;

public class Catapulta extends Casilla {
	private int dañoProyectiles;

	private int dañoProyectilesVariable;

	private int rango;
	
	public Catapulta() {
		super();
//		this.dañoExtra = 
	}
    
    public Catapulta(int dañoProyectiles, int dañoProyectilesVariable, int rango, HachaDivasónica hachaDivasónica) {
		super(hachaDivasónica);
		this.dañoProyectiles = dañoProyectiles;
		this.dañoProyectilesVariable = dañoProyectilesVariable;
		this.rango = rango;
	}

    public int realizarDisparo() {
    	
    	return dañoProyectiles + (int) Math.floor(Math.random()*2*(dañoProyectilesVariable)-1);
    	
    }

    public int getRango() {
    	
    	return this.rango;
    	
    }

}
