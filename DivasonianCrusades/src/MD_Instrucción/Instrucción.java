package MD_Instrucción;

public class Instrucción {
	private Operación[] operaciones = new Operación[6];
	
	public Instrucción() {
		
		for(int i = 0; i<6; i++) {
			
			operaciones[i] = null;
			
		}
		
	}
	
	public Instrucción(Operación[] operaciones) {
		
		this.operaciones = operaciones;
		
	}

    public Operación getOperacion(int n) {
    	
    	return operaciones[n];
    	
    }

}
