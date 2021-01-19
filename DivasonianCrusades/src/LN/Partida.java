package LN;
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

import MD_Instrucción.Disparo;
import MD_Instrucción.Instrucción;
import MD_Instrucción.Movimiento;
import MD_Instrucción.Operación;
import Utilidades.Facción;

public class Partida implements Runnable {
	
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
	
	private boolean empezada=false;
    
	//Para una nueva partida
    public Partida(Socket s1, Socket s2, String nombre1, String nombre2) {
    	
    	this.s1 = s1;
    	this.s2 = s2;
    	this.nombre1 = nombre1;
    	this.nombre2 = nombre2;
    	
    	this.rendición1 = false;
    	this.rendición1 = false;
    	
    	turno = 0;
    	
    	tablero = new Tablero();
    	
    	instrucciónFacción1 = new Instrucción(); 
    	instrucciónFacción2 = new Instrucción();
    	
    }
    
    //Por si hiciera falta
    public Partida(Socket s1, Socket s2, String nombre1, String nombre2, Tablero tablero, int turno) {
    	
    	this.s1 = s1;
    	this.s2 = s2;
    	this.nombre1 = nombre1;
    	this.nombre2 = nombre2;
    	
    	this.turno = turno;
    	
    	this.tablero = tablero;
    	instrucciónFacción1 = new Instrucción(); 
    	instrucciónFacción2 = new Instrucción();
    	
    }
    
    //Para cargar partida
    public Partida(Socket s1, Socket s2, String nombre1, String nombre2, String archivoPartida, boolean socket1EsElAzul) throws SAXException, IOException, ParserConfigurationException {
    	
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
    	
    	this.turno = Integer.parseInt(tableroElemento.getAttribute("turno"));
    	
    	this.tablero = Tablero.getFromElemento(tableroElemento);
    	instrucciónFacción1 = new Instrucción(); 
    	instrucciónFacción2 = new Instrucción();
    	
    }
    
    public void agenciarSockets(ObjectInputStream ois1,ObjectInputStream ois2, ObjectOutputStream oos1,ObjectOutputStream oos2, boolean azul) {
    	if(azul) {
        	this.oos1=oos1;
        	this.ois1=ois1;
        	this.oos2=oos2;
        	this.ois2=ois2;
    	}else {
          	this.oos2=oos1;
        	this.ois2=ois1;
        	this.oos1=oos2;
        	this.ois1=ois2;
    	}
    	empezada=true;
    }
    
    public void run() {
    	
    	ArrayList<Tablero> tablerosDelTurno = new ArrayList<Tablero>();

		try {
			if(!empezada) {
				oos1 = new ObjectOutputStream(s1.getOutputStream());
				oos2 = new ObjectOutputStream(s2.getOutputStream());
				ois1 = new ObjectInputStream(s1.getInputStream());
				ois2 = new ObjectInputStream(s2.getInputStream());
			}
			
			boolean haTerminado = false;
			
			while(!haTerminado) {
				tablerosDelTurno.clear();
				rendición1 = ois1.readLine().split("-")[0].equals("SURR");
				rendición2 = ois2.readLine().split("-")[0].equals("SURR");
				
				if(rendición1 && rendición2) {
					
					haTerminado = true;
					
					oos1.writeBytes("SURR-El oponente se ha rendido.\r\n");
					oos2.writeBytes("SURR-El oponente se ha rendido.\r\n");
					oos1.flush();
					oos2.flush();
					
				}
				else if(rendición1) {
					
					haTerminado = true;
					oos2.writeBytes("SURR-El oponente se ha rendido.\r\n");
					oos2.flush();
					
				}
				else if(rendición2) {
					
					haTerminado = true;
					oos1.writeBytes("SURR-El oponente se ha rendido.\r\n");
					oos1.flush();
					
				}
				else {
					
					oos1.writeBytes("OK-La partida continúa.\r\n");
					oos2.writeBytes("OK-La partida continúa.\r\n");
					oos1.flush();
					oos2.flush();
					
					this.instrucciónFacción1.clear();
					this.instrucciónFacción2.clear();
					
					ois1.skip(ois1.available());
					ois2.skip(ois2.available());
					
					
					
//					for(Operación op : (ArrayList<Operación>)((Instrucción) ois1.readObject())) {
//						
//						System.out.println(op.toString());
//						instrucciónFacción1.add(op);
//						
//					}
					
					instrucciónFacción1.addAll((Instrucción) ois1.readObject());
					instrucciónFacción2.addAll((Instrucción) ois2.readObject());
					
					/**/ System.out.println(instrucciónFacción1.toString());
					/**/ System.out.println(instrucciónFacción2.toString());
					
					tablerosDelTurno.add((Tablero) this.tablero.clone()); //Tablero inicial
					
					for(movimiento = 0; movimiento < 6; movimiento++) {
						
						this.ejecutarOperación();
						
						tablerosDelTurno.add((Tablero) this.tablero.clone()); //Tablero del turno "movimiento"
						
						this.tablero.limpiarProyectiles(); //Limpiamos los booleanos de cayóProyectil tras el turno.
						
					}
					
					turno++;
					
					this.resolverTurno();
					
					tablerosDelTurno.add((Tablero)this.tablero.clone()); //Tablero tras resolver el turno
					
					ArrayList<Tablero> tablerosCopia = (ArrayList<Tablero>)tablerosDelTurno.clone();
					
					this.mandarTableros(oos1, oos2, tablerosCopia);
					
					
					
					System.out.println("Pum! Tableros mandados");
					
					haTerminado = this.tablero.haTerminado();
					
				}
				
			}
			
			oos1.writeObject(this.tablero.getGanador());
			oos2.writeObject(this.tablero.getGanador()); 
			
			
			
			//Esto habrá que quitarlo, pero de momento lo voy a dejar porque así el servidor puede ver quién gana y quién pierde.
			if(this.tablero.getGanador() == Facción.Facción1) System.out.println("¡Enhorabuena! Ha ganado el azul " + nombre1 + " en el turno " + turno);
			else System.out.println("¡Nada mal! Ha ganado el rojo " + nombre2 + " en el turno " + turno);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block

			try {
				oos1.writeBytes("AB-Un jugador ha abandonado!\r\n");
			} catch (IOException e2) {
				//Uno de los dos hará saltar una excepción.
			}
			try {
				oos2.writeBytes("AB-Un jugador ha abandonado!\r\n");
			} catch (IOException e1) {
				//Uno de los dos hará saltar una excepción.
			}
			
			//e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(this.s1!=null) {
				try {
					this.s1.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(this.s2!=null) {
				try {
					this.s2.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
    	
    }

//    public void iniciarPartida(String nombre1, String nombre2) {
//    	
//    	//Preparamos los socketes y todo para la partida.
//    	
//    }

//    public void finalizarPartida() {
//    	
//    	//Cerrando sockets.
//    	
//    }

    public void ejecutarOperación() {
    	
    	//Obtenemos las operaciones
    	Operación op1 = (Operación) instrucciónFacción1.getOperacion(movimiento);
    	Operación op2 = (Operación) instrucciónFacción2.getOperacion(movimiento);
    	
    	//Transformamos los movimientos de quien no puede mover en esperas:
    	if(op1 instanceof Movimiento && !this.tablero.movimientoPosible(op1.getFicha())) op1 = null;
    	if(op2 instanceof Movimiento && !this.tablero.movimientoPosible(op2.getFicha())) op2 = null;
    	
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
    
    public Tablero getTablero() {
    	return this.tablero;
    }
    
    public int getTurno() {
    	return this.turno;
    }
    
//    public void guardarPartida(Tablero tablero, int turno, String nombrePartida) throws ParserConfigurationException, TransformerException {
//    	
//    	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//    	DocumentBuilder db = dbf.newDocumentBuilder();
//    	Document doc = db.newDocument();
//    	
//    	Element tableroElemento = tablero.getElemento(doc);
//    	
//    	tableroElemento.setAttribute("turno", this.turno + "");
//    	
//    	doc.appendChild(tableroElemento);
//    	
//    	TransformerFactory tf = TransformerFactory.newInstance();
//    	Transformer t = tf.newTransformer();
//    	DOMSource source = new DOMSource(doc);
//    	File carpeta = new File("PartidasGuardadas");
//    	if (!carpeta.exists()) {
//            if (carpeta.mkdirs()) {
//                System.out.println("Directorio creado");
//            } else {
//                System.out.println("Error al crear directorio");
//            }
//        }
//    	StreamResult result = new StreamResult(new File("PartidasGuardadas//" + nombrePartida + "_" + Calendar.getInstance().getTime().toString().replaceAll(" ", "_").replaceAll(":", "-") + ".xml"));
//    	t.transform(source , result);
//    	
//    }

}
