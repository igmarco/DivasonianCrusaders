package MD_Tablero;
import java.io.Serializable;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import Utilidades.Facción;

public abstract class Ficha implements Cloneable, Serializable{
    protected int daño;

    protected int vida;
    
    protected int vidaMáxima;

    protected int dañoVariable;

    protected HachaDivasónica hachaDivasónica;

	protected Facción facción;
    
    public boolean puedeMover;
    
    public int getDaño() {
		return daño;
	}

	public int getVida() {
		return vida;
	}
    
    public HachaDivasónica getHachaDivasónica() {
		return hachaDivasónica;
	}
    
    public boolean tieneHacha() {
		return (hachaDivasónica != null);
	}

	public Ficha(HachaDivasónica hachaDivasónica,
			Facción facción) {
		this.hachaDivasónica = hachaDivasónica;
		this.facción = facción;
		this.puedeMover = true;
	}
    
    public Ficha(int daño, int vida, int vidaMáxima, int dañoVariable, HachaDivasónica hachaDivasónica,
			Facción facción) {
		this.daño = daño;
		this.vida = vida;
		this.vidaMáxima = vidaMáxima;
		this.dañoVariable = dañoVariable;
		this.hachaDivasónica = hachaDivasónica;
		this.facción = facción;
		this.puedeMover = true;
	}
    
    public int realizarAtaque() {
    	
    	int hd = 0;
    	if(hachaDivasónica != null) hd = hachaDivasónica.sumarDaño();
    	return daño + (int) Math.floor(Math.random()*2*(dañoVariable)-dañoVariable) + hd;
    	
    }

    public int realizarAtaque(Ficha f) {
    	
    	int hd = 0;
    	if(hachaDivasónica != null) hd = hachaDivasónica.sumarDaño();
    	return daño + (int) Math.floor(Math.random()*2*(dañoVariable)-dañoVariable) + hd;
    	
    }

    public void sufrirDaño(int daño) {
    	
    	vida = vida - daño;
    	
    }

	public boolean estáMuerta() {
    	
    	return (vida <= 0);
    	
    }

    public Facción getFacción() {
    	
    	return facción;
    	
    }

    public void sufrirHacha() {
    	
    	if(hachaDivasónica != null) {
    		
    		vida = vida - hachaDivasónica.sufrirDañoPorTurno();
    		
    	}
    	
    }

    public void curarse(int v) {
    	
    	if(vida + v < vidaMáxima) {
    		
    		vida = vida + v;
    		
    	}
    	else {
    		
    		vida = vidaMáxima;
    		
    	}
    	
    }

    public boolean equals(Ficha f) {

    	//Si es el mismo tipo y facción (ya que cada jugador solo tiene una de cada tipo).
		
		if(f == null) return false;
		else if(f.getFacción() != this.getFacción()) return false;
		else if(f.getClass() != this.getClass()) return false;
		else return true;
		
	}

    public int realizarCarga(Ficha f) {
    	
    	return (int) Math.floor(this.realizarAtaque(f)*1.2);
    	
    }
    
    public int realizarAtaqueContraHuida(Ficha f) {
    	
    	return this.realizarCarga(f)/2;
    	
    }
    
    public void setHachaDivasónica(HachaDivasónica h) {
    	
    	this.hachaDivasónica = h;
    	
    }
    
    public int getMovs() {
    	 
    	return 2;
    	
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
        
        Element FichaE = doc.createElement("Ficha");
        
        FichaE.setAttribute("tipo", this.getClass().getSimpleName());
		
        Element dañoE = doc.createElement("daño");
        dañoE.appendChild(doc.createTextNode("" + this.daño));
        
        Element vidaE = doc.createElement("vida");
        vidaE.appendChild(doc.createTextNode("" + this.vida));
        
        Element vidaMáximaE = doc.createElement("vidaMáxima");
        vidaMáximaE.appendChild(doc.createTextNode("" + this.vidaMáxima))
        ;
        Element dañoVariableE = doc.createElement("dañoVariable");
        dañoVariableE.appendChild(doc.createTextNode("" + this.dañoVariable));
        
        if(this.hachaDivasónica != null) {
        	Element hachaDivasónicaE = this.hachaDivasónica.getElemento(doc);
        	FichaE.appendChild(hachaDivasónicaE);
		}
        
        Element facciónE = doc.createElement("facción");
        if(this.facción == Facción.Ambos) facciónE.appendChild(doc.createTextNode("Ambos"));
        else if(this.facción == Facción.Facción1) facciónE.appendChild(doc.createTextNode("Facción1"));
        else /* if(this.facción == Facción.Facción2)*/ facciónE.appendChild(doc.createTextNode("Facción2"));
        
        Element puedeMoverE = doc.createElement("puedeMover");
        puedeMoverE.appendChild(doc.createTextNode("" + this.puedeMover));
		
        FichaE.appendChild(dañoE);
        FichaE.appendChild(vidaE);
        FichaE.appendChild(vidaMáximaE);
        FichaE.appendChild(dañoVariableE);
        FichaE.appendChild(facciónE);
        FichaE.appendChild(puedeMoverE);
		
		return FichaE;
    	
    }
    
    public static Ficha getFromElemento(Element e) {
        
        NodeList hijos = e.getChildNodes();
        Element hijo;
        
        int daño = 0;
        int vida = 0;
        int vidaMáxima = 0;
        int dañoVariable = 0;
        HachaDivasónica hachaDivasónica = null;
    	Facción facción = Facción.Ambos;
        boolean puedeMover = true;
        int dañoFlechas = 0;
        int dañoFlechasVariable = 0;
        int dañoCarga = 0;
        int dañoACaballería = 0;
        
        for(int i = 0; i < hijos.getLength(); i++) {
        	
        	if(hijos.item(i).getNodeType() == Node.ELEMENT_NODE) {
        		
        		hijo = (Element) hijos.item(i);
        		
        		if(hijo.getNodeName().equals("daño")) daño = Integer.parseInt(hijo.getFirstChild().getNodeValue());
        		else if(hijo.getNodeName().equals("vida")) vida = Integer.parseInt(hijo.getFirstChild().getNodeValue());
        		else if(hijo.getNodeName().equals("vidaMáxima")) vidaMáxima = Integer.parseInt(hijo.getFirstChild().getNodeValue());
        		else if(hijo.getNodeName().equals("dañoVariable")) dañoVariable = Integer.parseInt(hijo.getFirstChild().getNodeValue());
        		
        		else if(hijo.getNodeName().equals("hachaDivasónica")) hachaDivasónica = HachaDivasónica.getFromElemento(hijo);
        		
        		else if(hijo.getNodeName().equals("facción")) {
        			
        			if(hijo.getFirstChild().getNodeValue().equals("Ambos")) facción = Facción.Ambos;
                    else if(hijo.getFirstChild().getNodeValue().equals("Facción1")) facción = Facción.Facción1;
                    else /* if(hijo.getFirstChild().getNodeValue().equals("Ambos"))*/ facción = Facción.Facción2;
        			
        		}
        		
        		else if(hijo.getNodeName().equals("puedeMover")) puedeMover = Boolean.parseBoolean(hijo.getFirstChild().getNodeValue());
        		else if(hijo.getNodeName().equals("dañoFlechas")) dañoFlechas = Integer.parseInt(hijo.getFirstChild().getNodeValue());
        		else if(hijo.getNodeName().equals("dañoFlechasVariable")) dañoFlechasVariable = Integer.parseInt(hijo.getFirstChild().getNodeValue());
        		else if(hijo.getNodeName().equals("dañoCarga")) dañoCarga = Integer.parseInt(hijo.getFirstChild().getNodeValue());
        		else if(hijo.getNodeName().equals("dañoACaballería")) dañoACaballería = Integer.parseInt(hijo.getFirstChild().getNodeValue());
        		
        	}
        	
        }
        
        if(e.getAttribute("tipo").equals("Arquero")) {
        	
        	return new Arquero(daño, vida, vidaMáxima, dañoVariable, hachaDivasónica, facción, dañoFlechas, dañoFlechasVariable);
        	
        }
        else if(e.getAttribute("tipo").equals("Bárbaro")) {
        	
        	return new Bárbaro(daño, vida, vidaMáxima, dañoVariable, hachaDivasónica, facción);
        	
        }
        else if(e.getAttribute("tipo").equals("Caballero")) {
        	
        	return new Caballero(daño, vida, vidaMáxima, dañoVariable, hachaDivasónica, facción, dañoCarga);
        	
        }
        else if(e.getAttribute("tipo").equals("Guerrero")) {
	
        	return new Guerrero(daño, vida, vidaMáxima, dañoVariable, hachaDivasónica, facción);
	
        }
        else if(e.getAttribute("tipo").equals("Lancero")) {
	
        	return new Lancero(daño, vida, vidaMáxima, dañoVariable, hachaDivasónica, facción, dañoACaballería);
	
        }
        else {
        	
        	return null;
        	
        }
    	
    }

}
