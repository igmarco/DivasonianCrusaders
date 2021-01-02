package MD_Tablero;

public class Curación extends Casilla {

	private int curación;

	private int curaciónVariable;

    public Curación() {
    	
		super();
//		this.curación = ;
//		this.curaciónVariable = ;
	}
    
    public Curación(int curación, int curaciónVariable, HachaDivasónica hachaDivasónica) {
		super(hachaDivasónica);
		this.curación = curación;
		this.curaciónVariable = curaciónVariable;
	}
    
    public int curar() {
    	
    	return curación + (int) Math.floor(Math.random()*2*(curaciónVariable)-1);
    	
    }

}
