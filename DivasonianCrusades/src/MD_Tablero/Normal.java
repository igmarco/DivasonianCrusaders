package MD_Tablero;

public class Normal extends Casilla {
	
	public Normal() {
    	
		super();
	}
    
    public Normal(HachaDivasónica hachaDivasónica) {
		super(hachaDivasónica);
	}
    
    public Normal(HachaDivasónica hachaDivasónica, boolean casillaDeCuración) {
		super(hachaDivasónica, casillaDeCuración);
	}
    
    public Normal(HachaDivasónica hachaDivasónica, int curaciónAuxiliar) {
		
    	super(hachaDivasónica, curaciónAuxiliar);
		
	}
    
}
