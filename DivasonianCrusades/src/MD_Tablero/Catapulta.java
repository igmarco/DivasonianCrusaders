package MD_Tablero;

public class Catapulta extends Casilla {
	private int dañoProyectiles;

	private int dañoProyectilesVariable;

	private int rango;
	
	private int identificador;
	
	public Catapulta() {
		super();
//		this.dañoProyectiles = ;
//		this.dañoProyectilesVariable = ;
//		this.rango = ;
		this.identificador = 0;
	}
	
	public Catapulta(int identificador) {
		super();
//		this.dañoProyectiles = ;
//		this.dañoProyectilesVariable = ;
//		this.rango = ;
		this.identificador = identificador;
	}
    
    public Catapulta(int dañoProyectiles, int dañoProyectilesVariable, int rango, HachaDivasónica hachaDivasónica, int identificador) {
		super(hachaDivasónica);
		this.dañoProyectiles = dañoProyectiles;
		this.dañoProyectilesVariable = dañoProyectilesVariable;
		this.rango = rango;
		this.identificador = identificador;
	}

    public int realizarDisparo() {
    	
    	return dañoProyectiles + (int) Math.floor(Math.random()*2*(dañoProyectilesVariable)-1);
    	
    }

    public int getRango() {
    	
    	return this.rango;
    	
    }
    
    public int getIdentificador() {
    	
    	return this.identificador;
    	
    }
    
    public boolean equals(Casilla c) {

    	//Si tiene el mismo tipo de casilla (en caso de la catapulta y la curación hay que tener un identificador que determine la posición).
		
		if(c == null) return false;
		else if(c.getClass() != this.getClass()) return false;
		else if((((Catapulta) c).getIdentificador() != this.getIdentificador()) && (((Catapulta) c).getIdentificador() != 0)) return false;
		else return true;
		
	}

}
