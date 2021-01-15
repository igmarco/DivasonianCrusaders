package LN;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

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
    
    public Partida(Socket s1, Socket s2, String nombre1, String nombre2) {
    	
    	this.s1 = s1;
    	this.s2 = s2;
    	this.nombre1 = nombre1;
    	this.nombre2 = nombre2;
    	
    	this.rendición1 = false;
    	this.rendición1 = false;
    	
    	turno = 0;
    	
    	tablero = new Tablero();
    	
    }
    
    public Partida(Socket s1, Socket s2, String nombre1, String nombre2, Tablero tablero, int turno) {
    	
    	this.s1 = s1;
    	this.s2 = s2;
    	this.nombre1 = nombre1;
    	this.nombre2 = nombre2;
    	
    	this.turno = turno;
    	
    	this.tablero = tablero;
    	
    }
    
    public void run() {
    	
    	Tablero[] tablerosDelTurno = new Tablero[7]; //Lo ideal sería que fuese una List serializable.

		try {
			
			oos1 = new ObjectOutputStream(s1.getOutputStream());
			oos2 = new ObjectOutputStream(s2.getOutputStream());
			ois1 = new ObjectInputStream(s1.getInputStream());
			ois2 = new ObjectInputStream(s2.getInputStream());
			
			boolean haTerminado = false;
			
			while(!haTerminado) {
				
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
					
					instrucciónFacción1 = (Instrucción) ois1.readObject();
					instrucciónFacción2 = (Instrucción) ois2.readObject();
					
					for(movimiento = 0; movimiento < 6; movimiento++) {
						
						this.ejecutarOperación();
						
						tablerosDelTurno[movimiento] = this.tablero;
						
					}
					
					turno++;
					
					this.resolverTurno();
					
					tablerosDelTurno[6] = this.tablero;
					
					this.mandarTableros(oos1, oos2, tablerosDelTurno);
					
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
		}
    	
    }

//    public void iniciarPartida(String nombre1, String nombre2) {
//    	
//    	//Preparamos los socketes y todo eso para iniciarse la partidella.
//    	
//    }

//    public void finalizarPartida() {
//    	
//    	//Cerrando el chiringuito y los sockets.
//    	
//    }

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

    public void mandarTableros(ObjectOutputStream oos1, ObjectOutputStream oos2, Tablero[] tablerosDelTurno) {
    	
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

}
