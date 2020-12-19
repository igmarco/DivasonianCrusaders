package MD_Tablero;


//TERMINADA SALVO PARÁMETROS


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

	public int getDañoExtra() {
    	
    	return dañoExtra + (int) Math.floor(Math.random()*2*(dañoExtraVariable)-1);
    	
    }

    public int getVidaPorTurno() {
    	
    	return vidaPorTurno + (int) Math.floor(Math.random()*2*(vidaPorTurnoVariable)-1);
    	
    }

}
