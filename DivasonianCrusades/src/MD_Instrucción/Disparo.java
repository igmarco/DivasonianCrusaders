package MD_Instrucción;

import MD_Tablero.Ficha;

public class Disparo extends Operación {
	private int x;

	private int y;
	
	public Disparo(Ficha f, int x, int y) {
		
		super(f);
		this.x = x;
		this.y = y;
		
	}

    public int getX() {
    	
    	return x;
    	
    }

    public int getY() {
    	
    	return y;
    	
    }

}
