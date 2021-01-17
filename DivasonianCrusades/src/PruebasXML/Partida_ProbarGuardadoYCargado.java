package PruebasXML;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Calendar;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import LN.Tablero;
import MD_Instrucción.Disparo;
import MD_Instrucción.Instrucción;
import MD_Instrucción.Movimiento;
import MD_Instrucción.Operación;
import Utilidades.Facción;

public class Partida_ProbarGuardadoYCargado implements Runnable {
	
	private Socket s1;
	private Socket s2;
	
	private int turno;
	private int movimiento;
    
	private String nombre1;
	private String nombre2; 
	
	private boolean rendición1;
	private boolean rendición2;

	private Instrucción instrucciónFacción1; 
	private Instrucción instrucciónFacción2;

	private Tablero tablero;
	
	ObjectOutputStream oos1 = null;
	ObjectOutputStream oos2 = null;
	ObjectInputStream ois1 = null;
	ObjectInputStream ois2 = null;
    
    public Partida_ProbarGuardadoYCargado(Socket s1, Socket s2, String nombre1, String nombre2) {
    	
    	this.s1 = s1;
    	this.s2 = s2;
    	this.nombre1 = nombre1;
    	this.nombre2 = nombre2;
    	
    	this.rendición1 = false;
    	this.rendición1 = false;
    	
    	turno = 0;
    	
    	tablero = new Tablero();
    	
    }
    
    public Partida_ProbarGuardadoYCargado(Socket s1, Socket s2, String nombre1, String nombre2, Tablero tablero, int turno) {
    	
    	this.s1 = s1;
    	this.s2 = s2;
    	this.nombre1 = nombre1;
    	this.nombre2 = nombre2;
    	
    	this.turno = turno;
    	
    	this.tablero = tablero;
    	
    }
    
    //Para cargar partida
    public Partida_ProbarGuardadoYCargado(Socket s1, Socket s2, String nombre1, String nombre2, String archivoPartida, boolean socket1EsElAzul) throws SAXException, IOException, ParserConfigurationException {
    	
    	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    	DocumentBuilder db = dbf.newDocumentBuilder();
    	Document doc = db.parse(archivoPartida);
    	
    	Element tableroElemento = doc.getDocumentElement();
    	
    	if(socket1EsElAzul) {
    		this.s1 = s1;
        	this.s2 = s2;
    	}
    	else {
    		this.s1 = s2;
        	this.s2 = s1;
    	}
    	this.nombre1 = nombre1;
    	this.nombre2 = nombre2;
    	
    	this.turno = Integer.parseInt(tableroElemento.getAttribute("tipo"));
    	
    	this.tablero = Tablero.getFromElemento(tableroElemento);
    	instrucciónFacción1 = new Instrucción(); 
    	instrucciónFacción2 = new Instrucción();
    	
    }
    
    public void run() {
    	
    	ArrayList<Tablero> tablerosDelTurno = new ArrayList<Tablero>();
    	
    	try {
			this.guardarPartida(this.tablero, this.turno, this.nombre1, this.nombre2, "PartidaDePrueba");
		} catch (ParserConfigurationException | TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }

    public void ejecutarOperación() {
    	
    	//Hacemos lo que haya que hacer con el ejecutor.
    	Operación op1 = (Operación) instrucciónFacción1.getOperacion(movimiento);
    	Operación op2 = (Operación) instrucciónFacción2.getOperacion(movimiento);
    	
    	if(op1 == null) {
    		
    		if(op2 == null) {
        		
        		//No hacer nada
        		
        	}
    		else {
    			
    			//Hace cosas solo el 2
    			if(op2 instanceof Movimiento) {
    				
    				this.tablero.moverFicha(((Movimiento) op2).getFicha(), ((Movimiento) op2).getDirección());
    				
    			}
    			else if(op2 instanceof Disparo) {
    				
    				this.tablero.dispararProyectiles(((Disparo) op2).getCatapulta(), ((Disparo) op2).getX(), ((Disparo) op2).getY(), ((Disparo) op2).getFicha());
    				
    			}
    			
    		}
    		
    	}
    	else if(op2 == null) {
    		
    		//Hace cosas solo el 1
    		if(op1 instanceof Movimiento) {
				
				this.tablero.moverFicha(((Movimiento) op1).getFicha(), ((Movimiento) op1).getDirección());
				
			}
			else if(op1 instanceof Disparo) {
				
				this.tablero.dispararProyectiles(((Disparo) op1).getCatapulta(), ((Disparo) op1).getX(), ((Disparo) op1).getY(), ((Disparo) op1).getFicha());
				
			}
    		
    	}
    	else {
    		
    		//Hacen cosas los dos
			if(op2 instanceof Disparo) {
				
				//Ahora hace cosas el 1
				if(op1 instanceof Movimiento) {
					
					this.tablero.moverFicha(((Movimiento) op1).getFicha(), ((Movimiento) op1).getDirección());
					
				}
				else if(op1 instanceof Disparo) {
					
					this.tablero.dispararProyectiles(((Disparo) op1).getCatapulta(), ((Disparo) op1).getX(), ((Disparo) op1).getY(), ((Disparo) op1).getFicha());
					
				}
				
				//Y después caen los disparos
				this.tablero.dispararProyectiles(((Disparo) op2).getCatapulta(), ((Disparo) op2).getX(), ((Disparo) op2).getY(), ((Disparo) op2).getFicha());
				
			}
			else if(op1 instanceof Disparo) {
				
				//Ahora hace cosas el 2
				if(op2 instanceof Movimiento) {
					
					this.tablero.moverFicha(((Movimiento) op2).getFicha(), ((Movimiento) op2).getDirección());
					
				}
				else if(op2 instanceof Disparo) {
					
					//No entrará aquí, pero dejamos el código para recordar cuál es el modo de completar la casuística de Operaciones.
					this.tablero.dispararProyectiles(((Disparo) op2).getCatapulta(), ((Disparo) op2).getX(), ((Disparo) op2).getY(), ((Disparo) op2).getFicha());
					
				}
				
				//Y después caen los disparos
				this.tablero.dispararProyectiles(((Disparo) op1).getCatapulta(), ((Disparo) op1).getX(), ((Disparo) op1).getY(), ((Disparo) op1).getFicha());
				
			}
			else {
				
				//Los dos hacen movimientos
				this.tablero.moverFichasALaVez(((Movimiento) op1).getFicha(), ((Movimiento) op1).getDirección(), ((Movimiento) op2).getFicha(), ((Movimiento) op2).getDirección());
				
			}
    		
    	}
    	
    }

    public void pasarTurno(Instrucción i1, Instrucción i2) {
    	
    	instrucciónFacción1 = i1;
    	instrucciónFacción2 = i2;
    	turno++;
    	movimiento = 0;
    	
    }

    public boolean haTerminado() {
    	
    	return tablero.haTerminado();
    	
    }

    public Facción getGanador() {
    	
    	return tablero.getGanador();
    	
    }

    public void resolverTurno() {
    	
    	//Imagino que será solo esto:
    	tablero.resolverTurno();
    	
    }

    public void mandarTableros(ObjectOutputStream oos1, ObjectOutputStream oos2, ArrayList<Tablero> tablerosDelTurno) {
    	
    	//Abran sus sockets que les vamos a meter tremendos tableros
    	
    	try {
    		
			oos1.writeObject(tablerosDelTurno);
			oos2.writeObject(tablerosDelTurno);
			oos1.flush();
			oos2.flush();
			
			oos1.writeObject(this.tablero.haTerminado());
			oos2.writeObject(this.tablero.haTerminado());
			oos1.flush();
			oos2.flush();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    public void guardarPartida(Tablero tablero, int turno, String nombre1, String nombre2, String nombrePartida) throws ParserConfigurationException, TransformerException {
    	
    	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    	DocumentBuilder db = dbf.newDocumentBuilder();
    	Document doc = db.newDocument();
    	
    	doc.appendChild(tablero.getElemento(doc));
    	
    	TransformerFactory tf = TransformerFactory.newInstance();
    	Transformer t = tf.newTransformer();
    	DOMSource source = new DOMSource(doc);
    	File carpeta = new File("PartidasGuardadas");
    	if (!carpeta.exists()) {
            if (carpeta.mkdirs()) {
                System.out.println("Directorio creado");
            } else {
                System.out.println("Error al crear directorio");
            }
        }
    	StreamResult result = new StreamResult(new File("PartidasGuardadas//" + nombrePartida + "_" + Calendar.getInstance().getTime().toString().replaceAll(" ", "_").replaceAll(":", "-") + ".xml"));
    	t.transform(source , result);
    	
    }
    
    public void cargarPartida() {
    	
    	
    	
    }

}
