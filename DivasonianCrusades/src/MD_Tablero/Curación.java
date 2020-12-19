package MD_Tablero;


//TERMINADA SALVO PARÁMETROS


public class Curación extends Casilla {

	public int curación;

    public int curaciónVariable;

    public Curación() {
    	
		super();
//		this.curación = ;
//		this.curaciónVariable = ;
	}
    
    public Curación(int curación, int curaciónVariable) {
		super();
		this.curación = curación;
		this.curaciónVariable = curaciónVariable;
	}
    
    public int curar() {
    	
    	return curación + (int) Math.floor(Math.random()*2*(curaciónVariable)-1);
    	
    }

}
