package MD_Tablero;

public abstract class Casilla implements Cloneable {
	protected HachaDivasónica hachaDivasónica;
	protected int curaciónAuxiliar;
	
	public Casilla() {
		
		this.hachaDivasónica = null;
		this.curaciónAuxiliar = 0;
		
	}
	
	public Casilla(HachaDivasónica hachaDivasónica) {
		
		this.hachaDivasónica = hachaDivasónica;
		this.curaciónAuxiliar = 0;
		
	}

	
	public Casilla(HachaDivasónica hachaDivasónica, boolean casillaDeCura) {
		
		this.hachaDivasónica = hachaDivasónica;
		
		if(casillaDeCura) {/* this.curaciónAuxiliar = ; */}
		else this.curaciónAuxiliar = 0;
		
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
    
    public int getCuraciónAuxiliar() {
    	
    	return this.curaciónAuxiliar;
    	
    }
    
    public boolean casillaDeCura() {
    	
    	return (this.curaciónAuxiliar != 0);
    	
    }
    
    public Object clone() {
    	
    	Object clone = null;
    	
    	try {
    		clone = super.clone();
    	} catch (CloneNotSupportedException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
    	
    	return clone;
    	
    }

}
