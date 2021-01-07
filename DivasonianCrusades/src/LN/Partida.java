package LN;
import MD_Instrucción.Instrucción;
import Utilidades.Facción;

public class Partida {
    public int turno;

    public int movimiento;
    
    public String nombre1;
    
    public String nombre2;

    public Instrucción instrucciónFacción1;

    public Instrucción instrucciónFacción2;

    public Ejecutor ejecutor;
    
    public Partida() {
    	
    	
    	
    }

    public void iniciarPartida(String nombre1, String nombre2) {
    	
    	//Preparamos los socketes y todo eso para iniciarse la partidella.
    	
    }

    public void finalizarPartida() {
    	
    	//Cerrando el chiringuito y los sockets.
    	
    }

    public void ejecutarOperación() {
    	
    	//Hacemos lo que haya que hacer con el ejecutor.
    	
    }

    public void pasarTurno(Instrucción i1, Instrucción i2) {
    	
    	instrucciónFacción1 = i1;
    	instrucciónFacción2 = i2;
    	turno++;
    	movimiento = 0;
    	
    }

    public boolean haTerminado() {
    	
    	return ejecutor.haTerminado();
    	
    }

    public Facción getGanador() {
    	
    	return ejecutor.getGanador();
    	
    }

    public void resolverTurno() {
    	
    	//Imagino que será solo esto:
    	ejecutor.resolverTurno();
    	
    }

    public void mandarTableros() {
    	
    	//Abran sus sockets que les vamos a meter tremendos tableros
    	
    }

}
