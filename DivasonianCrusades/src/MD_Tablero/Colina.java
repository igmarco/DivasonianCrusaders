package MD_Tablero;

public class Colina extends Casilla {
	private int dañoExtra;

	public Colina() {
		super();
//		this.dañoExtra = 
	}
	
	public Colina(int dañoExtra, HachaDivasónica hachaDivasónica) {
		super(hachaDivasónica);
		this.dañoExtra = dañoExtra;
	}
    
    public Colina(int dañoExtra, HachaDivasónica hachaDivasónica, boolean casillaDeCuración) {
		super(hachaDivasónica, casillaDeCuración);
		this.dañoExtra = dañoExtra;
	}
    
    public int getDañoExtra() {
    	
    	return this.dañoExtra;
    	
    }
	
} 
