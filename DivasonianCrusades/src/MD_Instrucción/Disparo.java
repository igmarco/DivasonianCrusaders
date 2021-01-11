package MD_Instrucción;

import MD_Tablero.Catapulta;
import MD_Tablero.Ficha;

public class Disparo extends Operación {
	
	private Catapulta catapulta;
	
	private int x;

	private int y;
	
	public Disparo(Ficha f, Catapulta catapulta, int posTablero) { 
		
		super(f);
		this.catapulta = catapulta;
		
		this.x = posTablero%9;
		this.y = 4-posTablero/9;
		
	}
	
	public Disparo(Ficha f, Catapulta catapulta, int x, int y) { 
		
		super(f);
		this.catapulta = catapulta;
		this.x = x;
		this.y = y;
		
	}
	
	public Catapulta getCatapulta() {
    	
    	return catapulta;
    	
    }

    public int getX() {
    	
    	return x;
    	
    }

    public int getY() {
    	
    	return y;
    	
    }

}
