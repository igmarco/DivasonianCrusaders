package MD_Tablero;

import java.io.Serializable;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class HachaDivasónica implements Serializable {
    public int dañoExtra;
    
    public int dañoExtraVariable;

    public int vidaPorTurno;
    
    public int vidaPorTurnoVariable;
    
    public HachaDivasónica() {
    	
    	dañoExtra = 10;
        dañoExtraVariable = 7;
        vidaPorTurno = 5;
        vidaPorTurnoVariable = 4;
    	
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
    
    public Element getElemento(Document doc) {

		Element HachaDivasónicaE = doc.createElement("HachaDivasónica");
		
		Element dañoExtraE = doc.createElement("dañoExtra");
		dañoExtraE.appendChild(doc.createTextNode("" + this.dañoExtra));
        
        Element dañoExtraVariableE = doc.createElement("dañoExtraVariableE");
        dañoExtraVariableE.appendChild(doc.createTextNode("" + this.dañoExtraVariable));
        
        Element vidaPorTurnoE = doc.createElement("vidaPorTurno");
        vidaPorTurnoE.appendChild(doc.createTextNode("" + this.vidaPorTurno));
        
        Element vidaPorTurnoVariableE = doc.createElement("vidaPorTurnoVariable");
        vidaPorTurnoVariableE.appendChild(doc.createTextNode("" + this.vidaPorTurnoVariable));
		
        HachaDivasónicaE.appendChild(dañoExtraE);
        HachaDivasónicaE.appendChild(dañoExtraVariableE);
        HachaDivasónicaE.appendChild(vidaPorTurnoE);
        HachaDivasónicaE.appendChild(vidaPorTurnoVariableE);
		
		return HachaDivasónicaE;
		
	}

}
