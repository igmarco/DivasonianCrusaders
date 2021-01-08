package Presentación;
import MD_Instrucción.Instrucción;
import MD_Instrucción.Operación;
import MD_Tablero.Tablero;
import Utilidades.Facción;

public class Jugador {
    public Facción facción;

    public String nombre;

    public Tablero tablero;

    public void intoducirInstrucción(Instrucción i) {
    }

    public boolean comprobarInstrucción(Instrucción i) {
    	
    	boolean posible = true;
    	
    	//Supongo que hará esto, aunque no estoy nada seguro.
    	for(int op = 0; op < 5; op++) {
    		
    		posible = posible && this.comprobarOperación(i.getOperacion(op));
    		
    		if(posible) {
    			
    			//Aquí realiza la operación en su tablero y tal porque hay que ir actualizando los movimientos. Claro, para ello hay que guardar una copia original del tablero, ya que si no es posible hay que devolver a su posición original a las fichas.
    			
    		}
    		else {
    			
    			//Retornamos el tablero a su posición original y devolvemos false.
    			
    		}
    		
    	}
    	
    	return posible;
    	
    }

    public boolean comprobarOperación(Operación o) {
    	
    	//Utiliza los métodos tablero.comprobarMovimiento y tablero.comprobarDisparo y esos, que con casi total seguridad no están puestos en la clase tablero todavía
    	return false;
    	
    }

    public void rendirse() {
    	
    	//Pues F la partida.
    	
    }

    public void pintar() {
    	
    	//De este método desconfío.
    	
    }

    public void setTablero(Tablero t) {
    	
    	tablero = t;
    	
    }

}
