package MD_Instrucción;
import MD_Tablero.Ficha;
import Utilidades.Dirección;

public class Movimiento extends Operación {
	private Dirección dirección;
	
	public Movimiento(Ficha f, Dirección dirección) {
		
		super(f);
		this.dirección = dirección;
		
	}

    public Dirección getDirección() {
    	
    	return dirección;
    	
    }

}
