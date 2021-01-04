package MD_Tablero;

public class Curación extends Casilla {

	private int curación;

	private int curaciónVariable;
	
	private int identificador;
	
	public Curación() {
    	
		super();
//		this.curación = ;
//		this.curaciónVariable = ;
		this.identificador = 0;
	}

    public Curación(int identificador) {
    	
		super();
//		this.curación = ;
//		this.curaciónVariable = ;
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
    
    public boolean equals(Casilla c) {

    	//Si tiene el mismo tipo de casilla (en caso de la catapulta y la curación tiene que tener el identificador igual (o 0)).
		
		if(c == null) return false;
		else if(c.getClass() != this.getClass()) return false;
		else if((((Curación) c).getIdentificador() != this.getIdentificador()) && (((Curación) c).getIdentificador() != 0)) return false;
		else return true;
		
	}

}
