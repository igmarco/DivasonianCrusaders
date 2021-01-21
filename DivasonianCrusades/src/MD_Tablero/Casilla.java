package MD_Tablero;

import java.io.Serializable;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import Utilidades.Facción;

public abstract class Casilla implements Cloneable, Serializable {
	protected HachaDivasónica hachaDivasónica;
	protected int curaciónAuxiliar;
	
	public Casilla() {
		
		this.hachaDivasónica = null;
		this.curaciónAuxiliar = 0;
		
	}
	
	public Casilla(HachaDivasónica hachaDivasónica) {
		
		this.hachaDivasónica = hachaDivasónica;
		this.curaciónAuxiliar = 0;
		
	}

	
	public Casilla(HachaDivasónica hachaDivasónica, boolean casillaDeCura) { 
		
		this.hachaDivasónica = hachaDivasónica;
		
		if(casillaDeCura) this.curaciónAuxiliar = 5;
		else this.curaciónAuxiliar = 0;
		
	}
	
	public Casilla(HachaDivasónica hachaDivasónica, int curaciónAuxiliar) {
		
		this.hachaDivasónica = hachaDivasónica; 
		this.curaciónAuxiliar = curaciónAuxiliar;
		
	}

    public void setHachaDivasónica(HachaDivasónica hachaDivasónica) {
    	
    	this.hachaDivasónica = hachaDivasónica;
    	
    }
    
    public HachaDivasónica getHachaDivasónica() {
    	
    	return hachaDivasónica;
    	
    }
    
    public boolean equals(Casilla c) {

    	//Si tiene el mismo tipo de casilla (en caso de la copa tiene que ser de la misma facción).
		
		if(c == null) return false;
//		else if(c.getFacción() != this.getFacción()) return false;
		else if(c.getClass() != this.getClass()) return false;
		else return true;
		
	}

    public boolean tieneHacha() {
    	
    	return (hachaDivasónica != null);
    	
    }
    
    public int getCuraciónAuxiliar() {
    	
    	return this.curaciónAuxiliar;
    	
    }
    
    public boolean casillaDeCura() {
    	
    	return (this.curaciónAuxiliar != 0);
    	
    }
    
    public Object clone() {
    	
    	Object clone = null;
    	
    	try {
    		clone = super.clone();
    	} catch (CloneNotSupportedException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
    	
    	return clone;
    	
    }
    
    public Element getElemento(Document doc) {
        
        Element CasillaE = doc.createElement("Casilla"); 
        
        CasillaE.setAttribute("tipo", this.getClass().getSimpleName());
        
        if(this.hachaDivasónica != null) {
        	Element hachaDivasónicaE = this.hachaDivasónica.getElemento(doc);
        	CasillaE.appendChild(hachaDivasónicaE);
		}
        
        Element curaciónAuxiliarE = doc.createElement("curaciónAuxiliar");
        curaciónAuxiliarE.appendChild(doc.createTextNode("" + this.curaciónAuxiliar));
		
        CasillaE.appendChild(curaciónAuxiliarE);
		
		return CasillaE;
    	
    }
    
public static Casilla getFromElemento(Element e) {
        
        NodeList hijos = e.getChildNodes();
        Element hijo;
        
        HachaDivasónica hachaDivasónica = null;
        int curaciónAuxiliar = 0;
        int dañoProyectiles = 0;
        int dañoProyectilesVariable = 0;
    	int identificador = 0;
    	int dañoExtra = 0;
    	Facción facción = Facción.Ambos;
    	int vida = 0;
    	int curación = 0;
    	int curaciónVariable = 0;
        
        for(int i = 0; i < hijos.getLength(); i++) {
        	
        	if(hijos.item(i).getNodeType() == Node.ELEMENT_NODE) {
        		
        		hijo = (Element) hijos.item(i);
        		
        		if(hijo.getNodeName().equals("curaciónAuxiliar")) curaciónAuxiliar = Integer.parseInt(hijo.getFirstChild().getNodeValue());
        		else if(hijo.getNodeName().equals("dañoProyectiles")) dañoProyectiles = Integer.parseInt(hijo.getFirstChild().getNodeValue());
        		else if(hijo.getNodeName().equals("dañoProyectilesVariable")) dañoProyectilesVariable = Integer.parseInt(hijo.getFirstChild().getNodeValue());
        		else if(hijo.getNodeName().equals("identificador")) identificador = Integer.parseInt(hijo.getFirstChild().getNodeValue());
        		
        		else if(hijo.getNodeName().equals("hachaDivasónica")) hachaDivasónica = HachaDivasónica.getFromElemento(hijo);
        		
        		else if(hijo.getNodeName().equals("facción")) {
        			
        			if(hijo.getFirstChild().getNodeValue().equals("Ambos")) facción = Facción.Ambos;
                    else if(hijo.getFirstChild().getNodeValue().equals("Facción1")) facción = Facción.Facción1;
                    else /* if(hijo.getFirstChild().getNodeValue().equals("Ambos"))*/ facción = Facción.Facción2;
        			
        		}
        		
        		else if(hijo.getNodeName().equals("dañoExtra")) dañoExtra = Integer.parseInt(hijo.getFirstChild().getNodeValue());
        		else if(hijo.getNodeName().equals("vida")) vida = Integer.parseInt(hijo.getFirstChild().getNodeValue());
        		else if(hijo.getNodeName().equals("curación")) curación = Integer.parseInt(hijo.getFirstChild().getNodeValue());
        		else if(hijo.getNodeName().equals("curaciónVariable")) curaciónVariable = Integer.parseInt(hijo.getFirstChild().getNodeValue());
        		
        	}
        	
        }
        
        if(e.getAttribute("tipo").equals("Catapulta")) {
        	
        	return new Catapulta(dañoProyectiles, dañoProyectilesVariable, hachaDivasónica, identificador, curaciónAuxiliar);
        	
        }
        else if(e.getAttribute("tipo").equals("Colina")) {
        	
        	return new Colina(dañoExtra, hachaDivasónica, curaciónAuxiliar);
        	
        }
        else if(e.getAttribute("tipo").equals("Copa")) {
        	
        	return new Copa(facción, vida, hachaDivasónica);
        	
        }
        else if(e.getAttribute("tipo").equals("Curación")) {
	
        	return new Curación(curación, curaciónVariable, hachaDivasónica, identificador);
	
        }
        else if(e.getAttribute("tipo").equals("Normal")) {
	
        	return new Normal(hachaDivasónica, curaciónAuxiliar);
	
        }
        else {
        	
        	return null;
        	
        }
    	
    }

}
