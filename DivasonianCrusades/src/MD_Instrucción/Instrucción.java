package MD_Instrucción;

import java.util.ArrayList;
import java.util.List;

public class Instrucción<Operación> extends ArrayList<Operación> {
	
	public Instrucción() {
		super();
	}
	
	public Operación getOperacion(int x) {
		return this.get(x);
	}
	
}
