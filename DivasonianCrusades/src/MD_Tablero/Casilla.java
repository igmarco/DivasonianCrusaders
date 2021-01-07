package MD_Tablero;

public abstract class Casilla {
	protected HachaDivasónica hachaDivasónica;
	protected boolean casillaDeCura;
	
	public Casilla() {
		
		this.hachaDivasónica = null;
		this.casillaDeCura = false;
		
	}
	
	public Casilla(HachaDivasónica hachaDivasónica) {
		
		this.hachaDivasónica = hachaDivasónica;
		this.casillaDeCura = false;
		
	}

	
	public Casilla(HachaDivasónica hachaDivasónica, boolean casillaDeCura) {
		
		this.hachaDivasónica = hachaDivasónica;
		this.casillaDeCura = casillaDeCura;
		
	}

    public void setHachaDivasónica(HachaDivasónica hachaDivasónica) {
    	
    	this.hachaDivasónica = hachaDivasónica;
    	
    }
    
public HachaDivasónica getHachaDivasónica() {
    	
    	return hachaDivasónica;
    	
    }
    
    public boolean equals(Casilla c) {

    	//Si tiene el mismo tipo de casilla (en caso de la copa tiene que ser de la misma facción).
		
		if(c == null) return false;
//		else if(c.getFacción() != this.getFacción()) return false;
		else if(c.getClass() != this.getClass()) return false;
		else return true;
		
	}

    public boolean tieneHacha() {
    	
    	return (hachaDivasónica != null);
    	
    }

}
