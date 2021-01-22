package Presentación;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public class ClienteGUI extends JFrame {

	private JPanel contentPane;
	private /*final*/ TableroGrafico tablero;
	private final JButton btContinuarPartida;
	private final JButton btRendirse;
	private String ip;
	
	private boolean salir = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClienteGUI frame = new ClienteGUI(/*true,null*/"localhost");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	//el boton nuevapartida abre un nuevo formulario para introducir el nombre y conectar con el servidor, continuar partida si ya hay una empezada vuelve a la interfaz de la partida
	// cargar partida es casi como nueva partida pero con un par de opciones mas para poder cargar una partida serializada en un documento XML,
	// guardar partida persiste el estado de la partida que ya debe de haber empezado en un documento XML y por ultimo rendirse manda un mensaje de rendicion
	// atraves del tablero para rendirse, salir sale. ademas los botones superiores proporcionan informacion extra acerca del juego y sus componentes. 
	public ClienteGUI(/*boolean nuevo, final TableroGrafico Tablero*/String ip) {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("Recursos\\iconoRefachero2.png"));
		
		this.ip = ip;
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if(tablero != null && salir) {
					tablero.salir(); 
				}
			}
		});
		setTitle("Divasonian Crusaders");
		final ClienteGUI main = this;
//		tablero = new TableroGrafico();
		setResizable(false);
		setBounds(100, 100, 395, 533);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 250, 205));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 230, 140));
		panel.setBounds(0, 0, 389, 504);
		contentPane.add(panel);
		panel.setLayout(null);
		
		final JButton btCargarPartida = new JButton("");
		btCargarPartida.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btCargarPartida.setIcon(new ImageIcon("Recursos\\CargarPartida2.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btCargarPartida.setIcon(new ImageIcon("Recursos\\CargarPartida.png"));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				
					boolean continuar = true;
					
					if(tablero != null) {
						
						int resp = JOptionPane.showConfirmDialog(null, "Crear una nueva partida eliminará la partida en curso. ¿Está seguro?", "Atención", JOptionPane.YES_NO_OPTION);
						
						if(resp == JOptionPane.NO_OPTION) {
							
							continuar = false; 
							
						}
						else {
							
							tablero.rendirse();
							
						}
						
					}
					
					if(continuar) {
						
						setVisible(false);
						//Deshabilitamos el botón continuar partida, ya que rompemos la partida que teníamos. Luego lo habilitamos al meter el nombre.
						btContinuarPartida.setEnabled(false);
						//OJO, en caso de que el jugador se eche para atrás no tiene sentido haber creado el TableroGrafico, no? eso hay que revisarlo.
						//Ya está revisado
						CargarPartida car = new CargarPartida(main);

						car.setVisible(true);
						
					}
			}
		});
		btCargarPartida.setForeground(new Color(255, 250, 240));
		btCargarPartida.setFont(new Font("Matura MT Script Capitals", Font.PLAIN, 20));
		btCargarPartida.setBackground(new Color(240, 230, 140));
		btCargarPartida.setIcon(new ImageIcon("Recursos\\CargarPartida.png"));
		btCargarPartida.setBounds(104, 249, 175, 47);
		btCargarPartida.setHorizontalTextPosition(SwingConstants.CENTER);
		btCargarPartida.setBorder(null);
		panel.add(btCargarPartida);
		
		final JButton btGuardarPartida = new JButton("");
		btGuardarPartida.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btGuardarPartida.setIcon(new ImageIcon("Recursos\\GuardarPartida2.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btGuardarPartida.setIcon(new ImageIcon("Recursos\\GuardarPartida.png"));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					tablero.guardarPartida();
					JOptionPane.showMessageDialog(null, "Su partida ha sido persistida con éxito.", "Al toque", JOptionPane.WARNING_MESSAGE);
				} catch (ParserConfigurationException | TransformerException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btGuardarPartida.setBackground(new Color(240, 230, 140));
		btGuardarPartida.setIcon(new ImageIcon("Recursos\\GuardarPartida.png"));
		btGuardarPartida.setBounds(104, 297, 175, 47);
		btGuardarPartida.setHorizontalTextPosition(SwingConstants.CENTER);
		btGuardarPartida.setBorder(null);
		panel.add(btGuardarPartida);
		
		final JButton btSalir = new JButton("");
		btSalir.addMouseListener(new MouseAdapter() { 
			public void mouseClicked(MouseEvent e) {
				
				boolean salir = true;
				
				if(tablero != null) {
					
					int resp = JOptionPane.showConfirmDialog(null, "Salir eliminará la partida en curso. ¿Está seguro?", "Atención", JOptionPane.YES_NO_OPTION);
					
					if(resp == JOptionPane.NO_OPTION) {
						
						salir = false;
						
					}else {
						
						tablero.salir();
						
					}
					
				}
				
				if(salir) { 
					
					System.exit(0);
					
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btSalir.setIcon(new ImageIcon("Recursos\\Salir2.1.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btSalir.setIcon(new ImageIcon("Recursos\\Salir2.png"));
			}
		});
		btSalir.setIcon(new ImageIcon("Recursos\\Salir2.png"));
		btSalir.setBackground(new Color(240, 230, 140));
		btSalir.setBounds(104, 419, 175, 47);
		btSalir.setBorder(null);
		panel.add(btSalir);
	
//		if(nuevo) {
			final JButton btNuevaPartida = new JButton("");
			btNuevaPartida.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					btNuevaPartida.setIcon(new ImageIcon("Recursos\\NuevaPartida2.png"));
				}
				@Override
				public void mouseExited(MouseEvent e) {
					btNuevaPartida.setIcon(new ImageIcon("Recursos\\NuevaPartida.png"));
				}
				@Override
				public void mouseClicked(MouseEvent e) {
					
								boolean continuar = true;
								
								if(tablero != null) {
									
									int resp = JOptionPane.showConfirmDialog(null, "Crear una nueva partida eliminará la partida en curso. ¿Está seguro?", "Atención", JOptionPane.YES_NO_OPTION);
									
									if(resp == JOptionPane.NO_OPTION) {
										
										continuar = false; 
										
									}
									else {
										
										tablero.rendirse();
										
									}
									
								}
								
								if(continuar) {
									
									setVisible(false);
									//Deshabilitamos el botón continuar partida, ya que rompemos la partida que teníamos. Luego lo habilitamos al meter el nombre.
									btContinuarPartida.setEnabled(false);
									//OJO, en caso de que el jugador se eche para atrás no tiene sentido haber creado el TableroGrafico, no? eso hay que revisarlo.
									//Ya está revisado
									Nombre nom = new Nombre(main);

									nom.setVisible(true);
									
								}

				}
			});
			btNuevaPartida.setBackground(new Color(240, 230, 140));
			btNuevaPartida.setFont(new Font("Matura MT Script Capitals", Font.PLAIN, 20));
			btNuevaPartida.setForeground(new Color(245, 245, 245));
			//btNuevaPartida.setBackground(new Color(240, 230, 140));
			btNuevaPartida.setIcon(new ImageIcon("Recursos\\NuevaPartida.png"));
			btNuevaPartida.setBounds(104, 153, 175, 47);
			btNuevaPartida.setHorizontalTextPosition(SwingConstants.CENTER);
			btNuevaPartida.setBorder(null);
			panel.add(btNuevaPartida);
//		}
//		else {
			btContinuarPartida = new JButton("");
			btContinuarPartida.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						setVisible(false); 
						/*T*/ tablero.setVisible(true);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			});
			btContinuarPartida.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					btContinuarPartida.setIcon(new ImageIcon("Recursos\\ContinuarBotonS.png"));
				}
				@Override
				public void mouseExited(MouseEvent e) {
					btContinuarPartida.setIcon(new ImageIcon("Recursos\\ContinuarBoton.png"));
				}
			});
			btContinuarPartida.setBackground(new Color(240, 230, 140));
			btContinuarPartida.setFont(new Font("Matura MT Script Capitals", Font.PLAIN, 20));
			btContinuarPartida.setForeground(new Color(245, 245, 245));
			//btNuevaPartida.setBackground(new Color(240, 230, 140));
			btContinuarPartida.setIcon(new ImageIcon("Recursos\\ContinuarBoton.png"));
			btContinuarPartida.setBounds(104, 201, 175, 47);
			btContinuarPartida.setHorizontalTextPosition(SwingConstants.CENTER);
			btContinuarPartida.setBorder(null);
			/**/ btContinuarPartida.setEnabled(false);
			panel.add(btContinuarPartida);
//		}
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("Recursos\\LogoD.png"));
		lblNewLabel.setBounds(48, 33, 303, 113);
		panel.add(lblNewLabel);
		
		btRendirse = new JButton("");
		btRendirse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					btContinuarPartida.setEnabled(false);
					btRendirse.setEnabled(false);
					tablero.rendirse();
					tablero = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btRendirse.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btRendirse.setIcon(new ImageIcon("Recursos\\RendirseS.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btRendirse.setIcon(new ImageIcon("Recursos\\Rendirse.png"));
			}
		});
		btRendirse.setBackground(new Color(240, 230, 140));
		btRendirse.setFont(new Font("Matura MT Script Capitals", Font.PLAIN, 20));
		btRendirse.setForeground(new Color(245, 245, 245));
		btRendirse.setIcon(new ImageIcon("Recursos\\Rendirse.png"));
		btRendirse.setBounds(105, 361, 175, 47);
		btRendirse.setHorizontalTextPosition(SwingConstants.CENTER);
		btRendirse.setBorder(null);
		/**/ btRendirse.setEnabled(false);
		panel.add(btRendirse);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 389, 22);
		panel.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("Herramientas");
		mnNewMenu.setBounds(0, 0, 83, 20);
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Cambiar la IP del servidor");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Object resultado = JOptionPane.showInputDialog(null, "Introduzca la dirección IP en la que se encuentra\r\nel servidor de Divasonian Crusaders.", "IP del servidor", JOptionPane.QUESTION_MESSAGE, null, null, getIp());
				
				if(resultado != null) {
					
					setIp((String) resultado);
					
				}
				
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Ayuda");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// Al pulsar esto se muestra la descripción básica de la aplicación y su uso.
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							Ayuda frameAyuda = new Ayuda();
							frameAyuda.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				
			}
		});
		mntmNewMenuItem_1.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("M\u00E1s informaci\u00F3n");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// Al pulsar esto se muestra la información de nosotros
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							Información frameInformación = new Información();
							frameInformación.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				
			}
		});
		mntmNewMenuItem_2.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(mntmNewMenuItem_2);
		
	}
	
	public void habilitarContinuar() {
		
		btContinuarPartida.setEnabled(true); 
		
	}
	
	public void habilitarRendirse() {
		
		this.btRendirse.setEnabled(true);
		
	}
	
	public void deshabilitarContinuar() {
		
		btContinuarPartida.setEnabled(false); 
		
	}
	
	public void deshabilitarRendirse() {
		
		this.btRendirse.setEnabled(false);
		
	}
	
	public void salir(boolean estado) {
		this.salir=estado;
	}
	
	public String getIp() {
		
		return this.ip;
		
	}
	
	public void setIp(String ip) {
		
		this.ip = ip;
		
	}
	
	public void setTableroGrafico(TableroGrafico tg) {
		
		this.tablero = tg;
		
	}
	
	public String getIP() {
		
		return this.ip;
		
	}
	
	public void limpiarTablero() {
		
		this.tablero = null;
		
	}
	
	public void restaurarMenu() {
		
		this.btContinuarPartida.setEnabled(false);
		this.btRendirse.setEnabled(false);
		
	}
	
}
