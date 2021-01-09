package MD_Tablero;

public class HachaDivasónica {
    public int dañoExtra;
    
    public int dañoExtraVariable;

    public int vidaPorTurno;
    
    public int vidaPorTurnoVariable;
    
    public HachaDivasónica() {
    	
//    	dañoExtra = ;
//        dañoExtraVariable = ;
//        vidaPorTurno = ;
//        vidaPorTurnoVariable = ;
    	
    }

    public HachaDivasónica(int dañoExtra, int dañoExtraVariable, int vidaPorTurno, int vidaPorTurnoVariable) {
		super();
		this.dañoExtra = dañoExtra;
		this.dañoExtraVariable = dañoExtraVariable;
		this.vidaPorTurno = vidaPorTurno;
		this.vidaPorTurnoVariable = vidaPorTurnoVariable;
	}

	public int sumarDaño() {
    	
    	return dañoExtra + (int) Math.floor(Math.random()*2*(dañoExtraVariable)-1);
    	
    }

    public int sufrirDañoPorTurno() {
    	
    	return vidaPorTurno + (int) Math.floor(Math.random()*2*(vidaPorTurnoVariable)-1);
    	
    }

}
