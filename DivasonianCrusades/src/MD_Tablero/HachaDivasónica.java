package MD_Tablero;

public class HachaDivasónica {
	
	private int dañoExtra;
	private int vidaPorTurno;
	
	public HachaDivasónica(int de, int vpt) {
		this.dañoExtra = de;
		this.vidaPorTurno = vpt;
	}
	
	public int getDañoExtra() {
		return this.dañoExtra;
	}
	
	public int getVidaPorTurno() {
		return this.vidaPorTurno;
	}
	
}
