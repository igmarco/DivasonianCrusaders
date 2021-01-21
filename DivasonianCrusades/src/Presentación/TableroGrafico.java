package Presentación;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
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

import LN.Tablero;
import MD_Instrucción.Disparo;
import MD_Instrucción.Instrucción;
import MD_Instrucción.Movimiento;
import MD_Instrucción.Operación;
import MD_Tablero.Arquero;
import MD_Tablero.Bárbaro;
import MD_Tablero.Caballero;
import MD_Tablero.Casilla;
import MD_Tablero.Catapulta;
import MD_Tablero.Colina;
import MD_Tablero.Copa;
import MD_Tablero.Curación;
import MD_Tablero.Ficha;
import MD_Tablero.Guerrero;
import MD_Tablero.Lancero;
import Utilidades.Dirección;
import Utilidades.Facción;

public class TableroGrafico extends JFrame {
	
	private JPanel contentPane;
	
	private int turno;
	private int maniobra;
	
	private JButton btnMenu;
	private final JButton btnDisparar= new JButton("");
	private final JButton Mover= new JButton("");
	private final JButton btnEsperar= new JButton("");
	private final JButton btnCancelar= new JButton("");
	private final JButton Listo= new JButton("");
	/**/ private boolean introducirOperacionEnCurso= false;
	
	private ActionListener al;
	
	private List<Tablero> tableros;
//	/**/ private Tablero[] tablerosRecibidos; //Lo ideal sería que se reutilizase la lista tableros.
	
	private int tabI;
	
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel Maniobra;
	private JLabel Operaciones;
	
	private Tablero tab;
	
	private String nombre;
	private String oponente;
	
	private final JButton[] casillas = new JButton[45];
	
	private boolean azul;
	
	private boolean acabado=false;
	
	JLabel Turno;
	
	private JTextArea txtCasilla;
	private JTextArea txtFichaDef;
	private JTextArea txtFichaAt;
	
	private boolean catA=true,catR=true;
	
	private Integer movsF=0,movsC=0,movsB=3,movsG=0,movsL =0;
	
	private Facción faccion;
	
	private Instrucción<Operación> inst;
	
	private Socket s;
	
	private ObjectOutputStream out;
	private ObjectInputStream  in;

	/**
	 * Create the frame.
	 */
	
	//Este es el constructor, es un monstruo pero basicamente crea toda la interfaz principal y le añade a todos los botones los eventos necesarios para que todo funcione
	// para siguiente y anterior una vez se ha pasado turno avanza o retorcede en la visualizacion de los tableros resultado
	// para confirmar acaba el turno mandando las 6 operaciones al servidor y establece una comunicacion con el servidor para ver si se ha terminado la partida o si esta sigue.
	// mover, disparar y esperar se espera lo que hacen, ademas está comentado mas abajo.
	// menu vuelve al menu principal pero sin cerrar la interfaz.
	public TableroGrafico(final ClienteGUI menu, Socket s, ObjectInputStream inPasado, ObjectOutputStream outPasado) {
		
		this.s = s;
		this.in = inPasado;
		this.out = outPasado;
		
		
		setTitle("Divasonian Crusaders");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				salir();
			}
		});
		this.turno=0;
		this.maniobra = 0;
		this.s=s;
		this.tableros = new ArrayList<Tablero>();
//		/**/ this.tablerosRecibidos = new Tablero[7];
		this.inst = new Instrucción();
		tab = new Tablero();
		this.tableros.add(tab);
		setResizable(false);
		final TableroGrafico tablero = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1080, 751);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 230, 140));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);


		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("Recursos\\LogoDivasonian.png"));
		lblNewLabel.setBounds(60, 28, 618, 127);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("Recursos\\LogoBLS.png"));
		lblNewLabel_1.setBounds(756, 21, 292, 156);
		contentPane.add(lblNewLabel_1);

		JPanel panel = new JPanel();
		panel.setBounds(30, 236, 702, 390);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(5, 9, 0, 0));

		final JButton cas0 = new JButton("");
		cas0.setBackground(new Color(245, 245, 220));
		panel.add(cas0);

		JButton cas1 = new JButton("");
		cas1.setBackground(new Color(245, 245, 220));
		panel.add(cas1);

		JButton cas2 = new JButton("");
		cas2.setBackground(new Color(245, 245, 220));
		panel.add(cas2);

		JButton cas3 = new JButton("");
		cas3.setBackground(new Color(245, 245, 220));
		panel.add(cas3);

		JButton cas4 = new JButton("");
		cas4.setBackground(new Color(245, 245, 220));
		panel.add(cas4);

		JButton cas5 = new JButton("");
		cas5.setBackground(new Color(245, 245, 220));
		panel.add(cas5);

		JButton cas6 = new JButton("");
		cas6.setBackground(new Color(245, 245, 220));
		panel.add(cas6);

		JButton cas7 = new JButton("");
		cas7.setBackground(new Color(245, 245, 220));
		panel.add(cas7);

		final JButton cas8 = new JButton("");
		cas8.setBackground(new Color(245, 245, 220));
		panel.add(cas8);

		final JButton cas9 = new JButton("");
		cas9.setBackground(new Color(245, 245, 220));
		panel.add(cas9);

		JButton cas10 = new JButton("");
		cas10.setBackground(new Color(245, 245, 220));
		panel.add(cas10);

		JButton cas11 = new JButton("");
		cas11.setBackground(new Color(245, 245, 220));
		panel.add(cas11);

		JButton cas12 = new JButton("");
		cas12.setBackground(new Color(245, 245, 220));
		panel.add(cas12);

		JButton cas13 = new JButton("");
		cas13.setBackground(new Color(245, 245, 220));
		panel.add(cas13);

		JButton cas14 = new JButton("");
		cas14.setBackground(new Color(245, 245, 220));
		panel.add(cas14);

		JButton cas15 = new JButton("");
		cas15.setBackground(new Color(245, 245, 220));
		panel.add(cas15);

		JButton cas16 = new JButton("");
		cas16.setBackground(new Color(245, 245, 220));
		panel.add(cas16);

		final JButton cas17 = new JButton("");
		cas17.setBackground(new Color(245, 245, 220));
		panel.add(cas17);

		final JButton cas18 = new JButton("");
		cas18.setBackground(new Color(245, 245, 220));
		panel.add(cas18);

		final JButton cas19 = new JButton("");
		cas19.setBackground(new Color(245, 245, 220));
		panel.add(cas19);

		JButton cas20 = new JButton("");
		cas20.setBackground(new Color(245, 245, 220));
		panel.add(cas20);

		JButton cas21 = new JButton("");
		cas21.setBackground(new Color(245, 245, 220));
		panel.add(cas21);

		final JButton cas22 = new JButton("");
		cas22.setBackground(new Color(245, 245, 220));
		panel.add(cas22);

		JButton cas23 = new JButton("");
		cas23.setBackground(new Color(245, 245, 220));
		panel.add(cas23);

		JButton cas24 = new JButton("");
		cas24.setBackground(new Color(245, 245, 220));
		panel.add(cas24);

		final JButton cas25 = new JButton("");
		cas25.setBackground(new Color(245, 245, 220));
		panel.add(cas25);

		final JButton cas26 = new JButton("");
		cas26.setBackground(new Color(245, 245, 220));
		panel.add(cas26);

		final JButton cas27 = new JButton("");
		cas27.setBackground(new Color(245, 245, 220));
		panel.add(cas27);

		JButton cas28 = new JButton("");
		cas28.setBackground(new Color(245, 245, 220));
		panel.add(cas28);

		JButton cas29 = new JButton("");
		cas29.setBackground(new Color(245, 245, 220));
		panel.add(cas29);

		JButton cas30 = new JButton("");
		cas30.setBackground(new Color(245, 245, 220));
		panel.add(cas30);

		JButton cas31 = new JButton("");
		cas31.setBackground(new Color(245, 245, 220));
		panel.add(cas31);

		JButton cas32 = new JButton("");
		cas32.setBackground(new Color(245, 245, 220));
		panel.add(cas32);

		JButton cas33 = new JButton("");
		cas33.setBackground(new Color(245, 245, 220));
		panel.add(cas33);

		JButton cas34 = new JButton("");
		cas34.setBackground(new Color(245, 245, 220));
		panel.add(cas34);

		final JButton cas35 = new JButton("");
		cas35.setBackground(new Color(245, 245, 220));
		panel.add(cas35);

		final JButton cas36 = new JButton("");
		cas36.setBackground(new Color(245, 245, 220));
		panel.add(cas36);

		JButton cas37 = new JButton("");
		cas37.setBackground(new Color(245, 245, 220));
		panel.add(cas37);

		JButton cas38 = new JButton("");
		cas38.setBackground(new Color(245, 245, 220));
		panel.add(cas38);

		JButton cas39 = new JButton("");
		cas39.setBackground(new Color(245, 245, 220));
		panel.add(cas39);

		JButton cas40 = new JButton("");
		cas40.setBackground(new Color(245, 245, 220));
		panel.add(cas40);

		JButton cas41 = new JButton("");
		cas41.setBackground(new Color(245, 245, 220));
		panel.add(cas41);

		JButton cas42 = new JButton("");
		cas42.setBackground(new Color(245, 245, 220));
		panel.add(cas42);

		JButton cas43 = new JButton("");
		cas43.setBackground(new Color(245, 245, 220));
		panel.add(cas43);

		final JButton cas44 = new JButton("");
		cas44.setBackground(new Color(245, 245, 220));
		panel.add(cas44);

		this.casillas[0] = cas0;
		this.casillas[1] = cas1;
		this.casillas[2] = cas2;
		this.casillas[3] = cas3;
		this.casillas[4] = cas4;
		this.casillas[5] = cas5;
		this.casillas[6] = cas6;
		this.casillas[7] = cas7;
		this.casillas[8] = cas8;
		this.casillas[9] = cas9;
		this.casillas[10] = cas10;
		this.casillas[11] = cas11;
		this.casillas[12] = cas12;
		this.casillas[13] = cas13;
		this.casillas[14] = cas14;
		this.casillas[15] = cas15;
		this.casillas[16] = cas16;
		this.casillas[17] = cas17;
		this.casillas[18] = cas18;
		this.casillas[19] = cas19;
		this.casillas[20] = cas20;
		this.casillas[21] = cas21;
		this.casillas[22] = cas22;
		this.casillas[23] = cas23;
		this.casillas[24] = cas24;
		this.casillas[25] = cas25;
		this.casillas[26] = cas26;
		this.casillas[27] = cas27;
		this.casillas[28] = cas28;
		this.casillas[29] = cas29;
		this.casillas[30] = cas30;
		this.casillas[31] = cas31;
		this.casillas[32] = cas32;
		this.casillas[33] = cas33;
		this.casillas[34] = cas34;
		this.casillas[35] = cas35;
		this.casillas[36] = cas36;
		this.casillas[37] = cas37;
		this.casillas[38] = cas38;
		this.casillas[39] = cas39;
		this.casillas[40] = cas40;
		this.casillas[41] = cas41;
		this.casillas[42] = cas42;
		this.casillas[43] = cas43;
		this.casillas[44] = cas44;
		
		//-------------------------------------- MÉTODO Pintar() ---------------
		
		this.pintar(tab);
		
		//-------------------------------------- AQUÍ TERMINA ------------------

		final JButton btnAnteriorMovimiento = new JButton("");
		final JButton btnSiguienteMovimiento = new JButton("");
		btnAnteriorMovimiento.setEnabled(false);
		btnSiguienteMovimiento.setEnabled(false);
		
		btnMenu = new JButton("");
		btnMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnMenu.setIcon(new ImageIcon("Recursos\\Menu2.png"));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnMenu.setIcon(new ImageIcon("Recursos\\Menu.png"));
			}

			@Override
			public void mouseClicked(MouseEvent e) {

//				ClienteGUI frame = new ClienteGUI(false, tablero);
				setVisible(false);
//				frame.setVisible(true);
				/**/ menu.setVisible(true);
			}
		});
		btnMenu.setBackground(new Color(240, 230, 140));
		btnMenu.setIcon(new ImageIcon("Recursos\\Menu.png"));
		btnMenu.setBorder(null);
		btnMenu.setBounds(35, 647, 179, 47);
		contentPane.add(btnMenu);

		Turno = new JLabel("Turno: "+this.turno);
		Turno.setFont(new Font("Consolas", Font.PLAIN, 14));
		Turno.setBounds(231, 651, 119, 29);
		contentPane.add(Turno);

		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(751, 204, 9, 490);
		contentPane.add(separator);

		lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setFont(new Font("Consolas", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(40, 196, 196, 25);
		contentPane.add(lblNewLabel_3);

		lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setFont(new Font("Consolas", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(269, 196, 196, 25);
		contentPane.add(lblNewLabel_4);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(770, 478, 278, 14);
		contentPane.add(separator_1);

		JLabel lb_Casilla = new JLabel("Casilla:");
		lb_Casilla.setFont(new Font("Consolas", Font.PLAIN, 12));
		lb_Casilla.setBounds(769, 516, 77, 20);
		contentPane.add(lb_Casilla);

		JLabel lb_Info = new JLabel("Informaci\u00F3n:");
		lb_Info.setFont(new Font("Consolas", Font.PLAIN, 12));
		lb_Info.setBounds(770, 489, 89, 20);
		contentPane.add(lb_Info);

		JLabel lb_Ficha = new JLabel("Ficha:");
		lb_Ficha.setFont(new Font("Consolas", Font.PLAIN, 12));
		lb_Ficha.setBounds(770, 612, 46, 14);
		contentPane.add(lb_Ficha);

		txtCasilla = new JTextArea();
		txtCasilla.setFont(new Font("Consolas", Font.PLAIN, 11));
		txtCasilla.setEditable(false);
		txtCasilla.setBounds(832, 514, 216, 82);
		contentPane.add(txtCasilla);

		txtFichaDef = new JTextArea();
		txtFichaDef.setFont(new Font("Consolas", Font.PLAIN, 11));
		txtFichaDef.setBounds(832, 609, 135, 71);
		contentPane.add(txtFichaDef);
		Mover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/**/ introducirOperacionEnCurso = true;
				moverClick();
			}
		});

		Mover.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Mover.setIcon(new ImageIcon("Recursos\\MoverS.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Mover.setIcon(new ImageIcon("Recursos\\Mover.png"));
			}
		});

		Mover.setBackground(new Color(240, 230, 140));
		Mover.setIcon(new ImageIcon("Recursos\\Mover.png"));
		Mover.setBorder(null);
		Mover.setBounds(823, 206, 179, 47);
		contentPane.add(Mover);
		btnDisparar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/**/ introducirOperacionEnCurso = true;
				boolean catVal = true;
				final List<Integer> catapultas = tab.catapultasQuePuedesDisparar(faccion);
				if(catapultas.size()>0) {
					if(catapultas.size()==2) {
						if(((catapultas.get(0)==20||catapultas.get(1)==20)&&!catA)&&((catapultas.get(0)==24||catapultas.get(1)==24)&&!catR)) {
							catVal=false;
							JOptionPane.showMessageDialog(null, "Ninguna de sus fichas están en una catapulta Válida", "Atención", JOptionPane.WARNING_MESSAGE);
						}
		    			if((catapultas.get(0)==20||catapultas.get(1)==20) && !catA) {
		    				casillas[20].setEnabled(false);
		    				catapultas.remove(new Integer(20));
		    			}else if((catapultas.get(0)==24||catapultas.get(1)==24) && !catR) {
		    				casillas[24].setEnabled(false);
		    				catapultas.remove(new Integer(24));
		    			}
					}else if(catapultas.size()==1){
						if(catapultas.get(0)==20&&!catA) {
							catVal=false;
							JOptionPane.showMessageDialog(null, "Ninguna de sus fichas están en una catapulta Válida", "Atención", JOptionPane.WARNING_MESSAGE);
						}else if(catapultas.get(0)==24&&!catR){
							catVal=false;
							JOptionPane.showMessageDialog(null, "Ninguna de sus fichas están en una catapulta Válida", "Atención", JOptionPane.WARNING_MESSAGE);
						}
					}
					if(catVal) {
						bloquearCasillasDisparo(false,catapultas);
						btnCancelar.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								bloquearCasillasDisparo(true,catapultas);
							}
						});
					}
				}else {
					JOptionPane.showMessageDialog(null, "Ninguna de sus fichas están en una catapulta", "Atención", JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		btnDisparar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnDisparar.setIcon(new ImageIcon("Recursos\\DispararS.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnDisparar.setIcon(new ImageIcon("Recursos\\Disparar.png"));
			}
		});
		btnDisparar.setBackground(new Color(240, 230, 140));
		btnDisparar.setIcon(new ImageIcon("Recursos\\Disparar.png"));
		btnDisparar.setBounds(822, 264, 179, 47);
		btnDisparar.setBorder(null);
		contentPane.add(btnDisparar);
		btnEsperar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				inst.add(null);
				Tablero tab2 = tab;
				tableros.add(tab2);
				Operaciones.setText("Ops.: "+inst.size()+"/6");
				if(inst.size()==6) {
		    		btnDisparar.setEnabled(false);
			    	btnEsperar.setEnabled(false);
			    	Mover.setEnabled(false);
				}
			}
		});

		btnEsperar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnEsperar.setIcon(new ImageIcon("Recursos\\EsperarS.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnEsperar.setIcon(new ImageIcon("Recursos\\Esperar.png"));
			}
		});

		btnEsperar.setIcon(new ImageIcon("Recursos\\Esperar.png"));
		btnEsperar.setBackground(new Color(240, 230, 140));
		btnEsperar.setBorder(null);
		btnEsperar.setBounds(822, 322, 179, 47);
		contentPane.add(btnEsperar);
		this.agregarFuncionalidadOriginalBtnCancelar();

		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnCancelar.setIcon(new ImageIcon("Recursos\\DeshacerS.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnCancelar.setIcon(new ImageIcon("Recursos\\Deshacer.png"));
			}
		});
		btnCancelar.setBackground(new Color(240, 230, 140));
		btnCancelar.setIcon(new ImageIcon("Recursos\\Deshacer.png"));
		btnCancelar.setBounds(822, 380, 179, 47);
		btnCancelar.setBorder(null);
		contentPane.add(btnCancelar);

		Listo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean hacer = true;
				if(inst.size()!=6) {
					int resp = JOptionPane.showConfirmDialog(null, "Aún no ha realizado todas sus operaciones, ¿Está seguro?", "Atención", JOptionPane.YES_NO_OPTION);
					if(resp == JOptionPane.YES_OPTION) {
						for(int i = inst.size()-1; i<6;i++) {
							inst.add(null);
							tableros.add((Tablero)tab.clone());
						}
					}else {
						hacer = false;
					}
				}
					if(hacer) {
					try {
						out.writeBytes("OK-Todo bien bro\r\n");
						out.flush();
						String resultado = in.readLine();
						String[] resultados = resultado.split("-");
						if(resultados[0].equals("SURR")) {
							acabado=true;
							menu.limpiarTablero();
							Victoria vic = new Victoria(nombre,menu,tablero);
							vic.setVisible(true);
							setVisible(false);
							//HABRÁ QUE MANDAR UN MENSAJE DE VICTORIA O ALGO SIMILAR
							
						}else {
							maniobra=0;
							Instrucción inst2 = (Instrucción)inst.clone();
							out.writeObject(inst2);
							out.flush();
							System.out.println(inst.toString()); 
							inst.clear();
							/**/ tableros = (ArrayList<Tablero>) in.readObject();
							tabI = 0;
							acabado = (Boolean)in.readObject();
							tab = tableros.get(0);
							Operaciones.setText("Ops.:" + inst.size() + "/6");
							limpiarActions();
							pintar(tab);
							btnSiguienteMovimiento.setEnabled(true);
							resetearMovs();
							casillasMenu(false);
							resetearCatapultas();
							System.out.println("nuevo turno -------------------");
						}
					}catch(IOException ex) {
						ex.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		Listo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Listo.setIcon(new ImageIcon("Recursos\\ConfirmarS.png")); //ATENCIÓN, ESTO DEBERÍA SER ListoS
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Listo.setIcon(new ImageIcon("Recursos\\Confirmar.png")); //ATENCIÓN, ESTO DEBERÍA SER Listo
			}
		});
		Listo.setIcon(new ImageIcon("Recursos\\Confirmar.png")); //ATENCIÓN, ESTO DEBERÍA SER Listo
		Listo.setBackground(new Color(240, 230, 140));
		Listo.setBounds(902, 438, 100, 29);
		Listo.setBorder(null);
		contentPane.add(Listo);

		Operaciones = new JLabel("Ops.: "+this.inst.size()+"/6");
		Operaciones.setFont(new Font("Consolas", Font.PLAIN, 13));
		Operaciones.setBounds(825, 438, 77, 29);
		contentPane.add(Operaciones);
		
		

		btnSiguienteMovimiento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tabI<7) {
//					Tablero actual = tableros.get(tabI+1);
					/**/ tab = (Tablero)tableros.get(tabI+1).clone();
					if(tabI==0) {
						btnAnteriorMovimiento.setEnabled(true);
					}
					limpiarActions();
					pintar(/*actual*/tab);
					tabI++;
					if(tabI==7) {
						if(acabado) {
							menu.limpiarTablero();
							Tablero tabVic = tableros.get(tableros.size()-1);
							Facción faccionGanadora = tabVic.getGanador();
							if(faccionGanadora == faccion) {
								Victoria vic = new Victoria(nombre,menu,tablero);
								vic.setVisible(true);
								setVisible(false);
							}else if(faccionGanadora != Facción.Ambos) {
								Derrota der = new Derrota(nombre,menu,tablero);
								der.setVisible(true);
								setVisible(false);
							}else {
								
								Empate der = new Empate(nombre,menu,tablero);
								der.setVisible(true);
								setVisible(false);
								
							}
							
						}else {
							btnSiguienteMovimiento.setEnabled(false);
							btnAnteriorMovimiento.setEnabled(false);
							casillasMenu(true);
//						tab = (Tablero)actual.clone();
							turno++;
							Turno.setText("Turno: "+turno);
							maniobra=0;
							Maniobra.setText("Maniobra: "+maniobra);
						}
					}else {
						maniobra++;
						Maniobra.setText("Maniobra: "+maniobra);
						
					}
				}
			}
		});
		btnSiguienteMovimiento.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnSiguienteMovimiento.setIcon(new ImageIcon("Recursos\\SiguienteS.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnSiguienteMovimiento.setIcon(new ImageIcon("Recursos\\Siguiente.png"));
			}
		});
		btnSiguienteMovimiento.setBackground(new Color(240, 230, 140));
		btnSiguienteMovimiento.setIcon(new ImageIcon("Recursos\\Siguiente.png"));
		btnSiguienteMovimiento.setBorder(null);
		btnSiguienteMovimiento.setBounds(555, 647, 179, 47);
		contentPane.add(btnSiguienteMovimiento);

		Maniobra = new JLabel("Maniobra: "+/*this.inst.size()*/ this.maniobra);
		Maniobra.setFont(new Font("Consolas", Font.PLAIN, 14));
		Maniobra.setBounds(231, 673, 119, 29);
		contentPane.add(Maniobra);

		txtFichaAt = new JTextArea();
		txtFichaAt.setFont(new Font("Consolas", Font.PLAIN, 11));
		txtFichaAt.setBounds(977, 609, 71, 71);
		contentPane.add(txtFichaAt);

		btnAnteriorMovimiento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tabI!=0) {
//					Tablero actual = tableros.get(tabI-1);
					/**/ tab = (Tablero)tableros.get(tabI-1).clone();
						
					if(tabI==7) {
						btnSiguienteMovimiento.setEnabled(true);
						turno--;
						Turno.setText("Turno: "+turno);
					}
					limpiarActions();
					pintar(/*actual*/tab);
//					/**/ tab = (Tablero)actual.clone();
					tabI--;
					maniobra--;
					Maniobra.setText("Maniobra: "+maniobra);
					if(tabI==0) {
						btnAnteriorMovimiento.setEnabled(false);
					}
				}
			}
		});
		btnAnteriorMovimiento.setBackground(new Color(240, 230, 140));
		btnAnteriorMovimiento.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAnteriorMovimiento.setIcon(new ImageIcon("Recursos\\AnteriorS.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnAnteriorMovimiento.setIcon(new ImageIcon("Recursos\\Anterior.png"));
			}
		});
		btnAnteriorMovimiento.setIcon(new ImageIcon("Recursos\\Anterior.png"));
		btnAnteriorMovimiento.setBounds(366, 647, 179, 47);
		btnAnteriorMovimiento.setBorder(null);
		contentPane.add(btnAnteriorMovimiento);
	}

	//pone los nombres y las facciones.
	public void setNombre(String nombre1, String nombre2, boolean azul) {
		if (azul) {
			lblNewLabel_3.setText("Tú: " + nombre1 + " (Azul)");
			lblNewLabel_4.setText("Oponente: " + nombre2 + " (Rojo)");
			this.faccion = Facción.Facción1;
		} else {
			lblNewLabel_3.setText("Tú: " + nombre1 + " (Rojo)");
			lblNewLabel_4.setText("Oponente: " + nombre2 + " (Azul)");
			this.faccion = Facción.Facción2;
		}
		this.azul = azul;
		System.out.println(this.azul);
		this.nombre = nombre1;
		this.oponente = nombre2;
	}

	
	
	
	
	//Este metodo añade eventos para que al poner el ratón encima del boton en cuestion muestre una informacion adicional sobre la vida y estado de una ficha o sobre una casilla.
	public void ratonInNodo(int i) {

		String casillaInfo = "";
		String fichaDefInfo = "";
		String fichaAtInfo = "";

		Casilla casilla = this.tab.getNodo(i).getCasilla();
		Ficha fichaDef = this.tab.getNodo(i).getFichaDefensora();
		Ficha fichaAt = this.tab.getNodo(i).getFichaAtacante();

		// Info. de la casilla

		casillaInfo += casilla.getClass().getCanonicalName().split("\\.")[casilla.getClass().getCanonicalName().split("\\.").length-1] + "\r\n";
		
		if(casillaInfo.equals("Copa")) {
		
			casillaInfo = "Corona";
			
		}

		if (casilla instanceof Copa) {

			if(((Copa) casilla).getFacción()== Facción.Facción1)

				casillaInfo += "Azul" + "\r\n";
			
			else
				
				casillaInfo += "Rojo" + "\r\n";
			
			casillaInfo += "Vida: " + ((Copa) casilla).getVida() + "\r\n";

		} else if (casilla instanceof Curación) {
			
			casillaInfo += "Identificador: " + ((Curación) casilla).getIdentificador() + "\r\n";
			casillaInfo += "Curación: " + ((Curación) casilla).getCuración() + "\r\n";

		} else if (casilla instanceof Catapulta) {

			if(((Catapulta) casilla).getIdentificador() == 1)
				
				casillaInfo += "Identificador: Azul \r\n";
			
			else
				
				casillaInfo += "Identificador: Rojo \r\n";
			
			casillaInfo += "Uso: ";

			if (fichaDef != null && fichaAt == null) {
				
				if(fichaDef.getFacción().equals(Facción.Facción1))

					casillaInfo += "Azul" + "\r\n";
				
				else
					
					casillaInfo += "Rojo" + "\r\n";

			}

		} else if (casilla instanceof Colina) {

			casillaInfo += "Ataque defensivo: +" + ((Colina) casilla).getDañoExtra() + "\r\n";

		}

		if (casilla.tieneHacha()) {

			casillaInfo += "Hacha Divasónica: Sí" + "\r\n";

		} else {

			casillaInfo += "Hacha Divasónica: No" + "\r\n";

		}

		if (casilla.casillaDeCura()) {

			casillaInfo += "Curación (extra): +: " + casilla.getCuraciónAuxiliar();

		}

		// Info. de la/s ficha/s.
		
		if(fichaDef == null) {
			
			fichaDefInfo = "Ninguna";
			
		}
		else {
			
			fichaDefInfo += fichaDef.getClass().getCanonicalName().split("\\.")[fichaDef.getClass().getCanonicalName().split("\\.").length-1];
			if (fichaAt != null) {

				fichaDefInfo += "  vs." + "\r\n";
				fichaAtInfo += fichaAt.getClass().getCanonicalName().split("\\.")[fichaAt.getClass().getCanonicalName().split("\\.").length-1] + "\r\n";

			} else
				fichaDefInfo += "\r\n";

			if(fichaDef.getFacción().equals(Facción.Facción1))

				fichaDefInfo += "Facción: Azul" + "\r\n";
			
			else
				
				fichaDefInfo += "Facción: Rojo" + "\r\n";
			
			if (fichaAt != null) {
				
				if(fichaAt.getFacción().equals(Facción.Facción1))

					fichaAtInfo += "Azul" + "\r\n";
				
				else
					
					fichaAtInfo += "Rojo" + "\r\n";
				
			}

			fichaDefInfo += "Vida: " + fichaDef.getVida() + "\r\n";
			if (fichaAt != null)
				fichaAtInfo += fichaAt.getVida() + "\r\n";

			if (fichaDef.tieneHacha()) {

				fichaDefInfo += "Hacha Div.: Sí" + "\r\n";

			} else {

				fichaDefInfo += "Hacha Div.: No" + "\r\n";

			}
			if (fichaAt != null) {

				if (fichaAt.tieneHacha())
					fichaAtInfo += "Sí" + "\r\n";
				else
					fichaAtInfo += "No" + "\r\n";

			}
			
		}

		

		this.txtCasilla.setText(casillaInfo);
		this.txtFichaDef.setText(fichaDefInfo);
		this.txtFichaAt.setText(fichaAtInfo);
	}

	//este metodo recorre todo el tablero tab dado y pinta las casillas y fichas correspondientes ademas de añadirles sus eventos correspondientes.
    public void pintar(Tablero tab) {
    	
    	//Representar gráficamente el tablero tab
    	for (int i = 0; i < 45; i++) {
    		
    		//Con esto limpia lo que hay
    		this.casillas[i].setIcon(null);
    		
    		
    		this.casillas[i].setEnabled(true);
    		this.casillas[i].setBackground(new Color(245, 245, 220));
			final Integer x = i;
			Casilla cas = tab.getNodo(i).getCasilla();
			Ficha f = tab.getNodo(i).getFichaDefensora();
			Ficha fat = tab.getNodo(i).getFichaAtacante();
			
			if(tab.getNodo(i).getCayóProyectil()) {
				
				this.casillas[i].setIcon(new ImageIcon("Recursos\\ImpactoProyectilBC.png"));
				this.casillas[i].addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						ratonInImpactoProyectil(x);
					}

					@Override
					public void mouseExited(MouseEvent e) {
						ratonOutImpactoProyectil(x);
					}
				});
				
			}
			else if (cas instanceof Catapulta) {
				if (((Catapulta) cas).getIdentificador() == 1) {
					if (f == null) {
						this.casillas[i].setIcon(new ImageIcon("Recursos\\CatapultaAzul.png"));
						this.casillas[i].addMouseListener(new MouseAdapter() {
							@Override
							public void mouseEntered(MouseEvent e) {
								ratonInCatapultaAzul(x);
							}

							@Override
							public void mouseExited(MouseEvent e) {
								ratonOutCatapultaAzul(x);
							}
						});
					}
					else {
						this.pintarFicha(tab, i);
					}
				} else {
					if (f == null) {
						this.casillas[i].setIcon(new ImageIcon("Recursos\\CatapultaRoja.png"));
						this.casillas[i].addMouseListener(new MouseAdapter() {
							@Override
							public void mouseEntered(MouseEvent e) {
								ratonInCatapultaRojo(x);
							}

							@Override
							public void mouseExited(MouseEvent e) {
								ratonOutCatapultaRojo(x);
							}
						});
					} else {
						this.pintarFicha(tab, i);
					}
				}
			} else if (cas instanceof Colina) {
				if(f == null) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\Colina.png"));
					this.casillas[i].addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent e) {
							ratonInColina(x);
						}

						@Override
						public void mouseExited(MouseEvent e) {
							ratonOutColina(x);
						}
					});
				}else {
					this.pintarFicha(tab, i);
				}
			} else if (cas instanceof Curación) {
				if(f == null) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\Curarse.png"));
					this.casillas[i].addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent e) {
							ratonInCurarse(x);
						}

						@Override
						public void mouseExited(MouseEvent e) {
							ratonOutCurarse(x);
						}
					});
				}else {
					this.pintarFicha(tab, i);
				}
			} else if (cas instanceof Copa) {
				if(f == null) {
					if (((Copa) cas).getFacción().equals(Facción.Facción1)) {
						this.casillas[i].setIcon(new ImageIcon("Recursos\\CoronaBC.png"));
						this.casillas[i].addMouseListener(new MouseAdapter() {
							@Override
							public void mouseEntered(MouseEvent e) {
								ratonInCoronaAzul(x);
							}

							@Override
							public void mouseExited(MouseEvent e) {
								ratonOutCoronaAzul(x);
							}
						});
					} else {
						this.casillas[i].setIcon(new ImageIcon("Recursos\\CoronaBC.png"));
						this.casillas[i].addMouseListener(new MouseAdapter() {
							@Override
							public void mouseEntered(MouseEvent e) {
								ratonInCoronaRojo(x);
							}

							@Override
							public void mouseExited(MouseEvent e) {
								ratonOutCoronaRojo(x);
							}
						});
					}
				}else {
					this.pintarFicha(tab, i);
				}

			}else if(cas.getHachaDivasónica() != null && f == null){
				this.casillas[i].setIcon(new ImageIcon("Recursos\\HachaDivasonica.png"));
				this.casillas[i].addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						ratonInHacha(x);
					}

					@Override
					public void mouseExited(MouseEvent e) {
						ratonOutHacha(x);
					}
				});
			}else {
				this.pintarFicha(tab, i);
			}
			
			this.casillas[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					
					ratonInNodo(x);
					
				}
			});
			
		}
    	
    }
    
    //Para dado el tablero y una posicion pinta la ficha de la misma, y añade sus eventos visuales.
    public void pintarFicha(Tablero tab, int i) {
    	Ficha f = tab.getNodo(i).getFichaDefensora();
    	Ficha f2 = tab.getNodo(i).getFichaAtacante();
    	final Integer x = i;
    	if (f instanceof Arquero) {
    		if(f2 == null) {
    			if (f.getFacción().equals(Facción.Facción1)) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\ArqueroAzulBC.png"));
					this.casillas[i].addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent e) {
							ratonInArqueroAzul(x);
						}

						@Override
						public void mouseExited(MouseEvent e) {
							ratonOutArqueroAzul(x);
						}
					});
    			} else {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\ArqueroRojoBC.png"));
					this.casillas[i].addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent e) {
							ratonInArqueroRojo(x);
						}

						@Override
						public void mouseExited(MouseEvent e) {
							ratonOutArqueroRojo(x);
						}
					});
				}
    		}else {
    			this.pintarFichaCruzada(tab, i);
    		}
		} else if (f instanceof Lancero) {
			if(f2 == null) {
				if (f.getFacción().equals(Facción.Facción1)) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\LanceroAzulBC.png"));
					this.casillas[i].addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent e) {
							ratonInLanceroAzul(x);
						}

						@Override
						public void mouseExited(MouseEvent e) {
							ratonOutLanceroAzul(x);
						}
					});
				}
				else {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\LanceroRojoBC.png"));
					this.casillas[i].addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent e) {
							ratonInLanceroRojo(x);
						}	

						@Override
						public void mouseExited(MouseEvent e) {
							ratonOutLanceroRojo(x);
						}
					});
				}
			}else {
				this.pintarFichaCruzada(tab, i);
			}
		} else if (f instanceof Guerrero) {
			if(f2 == null) {
				if (f.getFacción().equals(Facción.Facción1)) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\GuerreroAzulBC.png"));
					this.casillas[i].addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent e) {
							ratonInGuerreroAzul(x);
						}

						@Override
						public void mouseExited(MouseEvent e) {
							ratonOutGuerreroAzul(x);
						}
					});
				}
				else {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\GuerreroRojoBC.png"));
					this.casillas[i].addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent e) {
							ratonInGuerreroRojo(x);
						}	

						@Override
						public void mouseExited(MouseEvent e) {
							ratonOutGuerreroRojo(x);
						}
					});
				}
			}else {
				this.pintarFichaCruzada(tab, i);
			}
		} else if (f instanceof Bárbaro) {
			if(f2 == null) {
				if (f.getFacción().equals(Facción.Facción1)) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\BarbaroAzulBC.png"));
					this.casillas[i].addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent e) {
							ratonInBarbaroAzul(x);
						}

						@Override
						public void mouseExited(MouseEvent e) {
							ratonOutBarbaroAzul(x);
						}
					});
				}
				else {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\BarbaroRojoBC.png"));
					this.casillas[i].addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent e) {
							ratonInBarbaroRojo(x);
						}	

						@Override
						public void mouseExited(MouseEvent e) {
							ratonOutBarbaroRojo(x);
						}
					});
				}
			}else {
				this.pintarFichaCruzada(tab, i);
			}
		} else if (f instanceof Caballero) {
			if(f2 == null) {
				if (f.getFacción().equals(Facción.Facción1)) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\CaballeroAzulBC.png"));
					this.casillas[i].addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent e) {
							ratonInCaballeroAzul(x);
						}

						@Override
						public void mouseExited(MouseEvent e) {
							ratonOutCaballeroAzul(x);
						}
					});
				}
				else {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\CaballeroRojoBC.png"));
					this.casillas[i].addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent e) {
							ratonInCaballeroRojo(x);
						}	

						@Override
						public void mouseExited(MouseEvent e) {
							ratonOutCaballeroRojo(x);
						}
					});
				}
			}else {
				this.pintarFichaCruzada(tab, i);
			}
		}
    }
    
    //dado el tablero y una posicion pinta las dos fichas combatienes y añade sus respectivos eventos visuales.
    public void pintarFichaCruzada(Tablero tab, int i) {
    	Ficha f1 = tab.getNodo(i).getFichaAtacante();
    	Ficha f2 = tab.getNodo(i).getFichaDefensora();
    	final Integer x = i;
    	if (f2 instanceof Arquero) {
			if (f2.getFacción().equals(Facción.Facción1)) {
				final String color = "A";
				final String defensor= "F";
				if(f1 instanceof Arquero) {
					final String atacante = "F";
					this.casillas[i].setIcon(new ImageIcon("Recursos\\FvFABC.png"));
					this.casillas[i].addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent e) {
							ratonInConflicto(x, color, defensor, atacante);
						}	

						@Override
						public void mouseExited(MouseEvent e) {
							ratonOutConflicto(x, color, defensor, atacante);
						}
					});
				}else if(f1 instanceof Bárbaro) {
					final String atacante = "B";
					this.casillas[i].setIcon(new ImageIcon("Recursos\\FvBABC.png"));
					this.casillas[i].addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent e) {
							ratonInConflicto(x, color, defensor, atacante);
						}	

						@Override
						public void mouseExited(MouseEvent e) {
							ratonOutConflicto(x, color, defensor, atacante);
						}
					});
				}else if(f1 instanceof Caballero) {
					final String atacante = "C";
					this.casillas[i].setIcon(new ImageIcon("Recursos\\FvCABC.png"));
					this.casillas[i].addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent e) {
							ratonInConflicto(x, color, defensor, atacante);
						}	

						@Override
						public void mouseExited(MouseEvent e) {
							ratonOutConflicto(x, color, defensor, atacante);
						}
					});
				}else if(f1 instanceof Guerrero) {
					final String atacante = "G";
					this.casillas[i].setIcon(new ImageIcon("Recursos\\FvGABC.png"));
					this.casillas[i].addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent e) {
							ratonInConflicto(x, color, defensor, atacante);
						}	

						@Override
						public void mouseExited(MouseEvent e) {
							ratonOutConflicto(x, color, defensor, atacante);
						}
					});
				}else if(f1 instanceof Lancero) {
					final String atacante = "L";
					this.casillas[i].setIcon(new ImageIcon("Recursos\\FvLABC.png"));
					this.casillas[i].addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent e) {
							ratonInConflicto(x, color, defensor, atacante);
						}	

						@Override
						public void mouseExited(MouseEvent e) {
							ratonOutConflicto(x, color, defensor, atacante);
						}
					});
				}
			} else {
				final String color = "R";
				final String defensor= "F";
				if(f1 instanceof Arquero) {
					final String atacante = "F";
					this.casillas[i].setIcon(new ImageIcon("Recursos\\FvFRBC.png"));
					this.casillas[i].addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent e) {
							ratonInConflicto(x, color, defensor, atacante);
						}	

						@Override
						public void mouseExited(MouseEvent e) {
							ratonOutConflicto(x, color, defensor, atacante);
						}
					});
				}else if(f1 instanceof Bárbaro) {
					final String atacante = "B";
					this.casillas[i].setIcon(new ImageIcon("Recursos\\FvBRBC.png"));
					this.casillas[i].addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent e) {
							ratonInConflicto(x, color, defensor, atacante);
						}	

						@Override
						public void mouseExited(MouseEvent e) {
							ratonOutConflicto(x, color, defensor, atacante);
						}
					});
				}else if(f1 instanceof Caballero) {
					final String atacante = "C";
					this.casillas[i].setIcon(new ImageIcon("Recursos\\FvCRBC.png"));
					this.casillas[i].addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent e) {
							ratonInConflicto(x, color, defensor, atacante);
						}	

						@Override
						public void mouseExited(MouseEvent e) {
							ratonOutConflicto(x, color, defensor, atacante);
						}
					});
				}else if(f1 instanceof Guerrero) {
					final String atacante = "G";
					this.casillas[i].setIcon(new ImageIcon("Recursos\\FvGRBC.png"));
					this.casillas[i].addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent e) {
							ratonInConflicto(x, color, defensor, atacante);
						}	

						@Override
						public void mouseExited(MouseEvent e) {
							ratonOutConflicto(x, color, defensor, atacante);
						}
					});
				}else if(f1 instanceof Lancero) {
					final String atacante = "L";
					this.casillas[i].setIcon(new ImageIcon("Recursos\\FvLRBC.png"));
					this.casillas[i].addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent e) {
							ratonInConflicto(x, color, defensor, atacante);
						}	

						@Override
						public void mouseExited(MouseEvent e) {
							ratonOutConflicto(x, color, defensor, atacante);
						}
					});
				}
			}
		} else if (f2 instanceof Bárbaro) {
			final String defensor= "B";
			if (f2.getFacción().equals(Facción.Facción1)) {
				final String color = "A";
				if(f1 instanceof Arquero) {
					final String atacante = "F";
					this.casillas[i].setIcon(new ImageIcon("Recursos\\BvFABC.png"));
					this.casillas[i].addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent e) {
							ratonInConflicto(x, color, defensor, atacante);
						}	

						@Override
						public void mouseExited(MouseEvent e) {
							ratonOutConflicto(x, color, defensor, atacante);
						}
					});
				}else if(f1 instanceof Bárbaro) {
					final String atacante = "B";
					this.casillas[i].setIcon(new ImageIcon("Recursos\\BvBABC.png"));
					this.casillas[i].addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent e) {
							ratonInConflicto(x, color, defensor, atacante);
						}	

						@Override
						public void mouseExited(MouseEvent e) {
							ratonOutConflicto(x, color, defensor, atacante);
						}
					});
				}else if(f1 instanceof Caballero) {
					final String atacante = "C";
					this.casillas[i].setIcon(new ImageIcon("Recursos\\BvCABC.png"));
					this.casillas[i].addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent e) {
							ratonInConflicto(x, color, defensor, atacante);
						}	

						@Override
						public void mouseExited(MouseEvent e) {
							ratonOutConflicto(x, color, defensor, atacante);
						}
					});
				}else if(f1 instanceof Guerrero) {
					final String atacante = "G";
					this.casillas[i].setIcon(new ImageIcon("Recursos\\BvGABC.png"));
					this.casillas[i].addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent e) {
							ratonInConflicto(x, color, defensor, atacante);
						}	

						@Override
						public void mouseExited(MouseEvent e) {
							ratonOutConflicto(x, color, defensor, atacante);
						}
					});
				}else if(f1 instanceof Lancero) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\BvLABC.png"));
					final String atacante = "L";
					this.casillas[i].addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent e) {
							ratonInConflicto(x, color, defensor, atacante);
						}	

						@Override
						public void mouseExited(MouseEvent e) {
							ratonOutConflicto(x, color, defensor, atacante);
						}
					});
				}
			}else {
				final String color = "R";
				if(f1 instanceof Arquero) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\BvFRBC.png"));
					final String atacante = "F";
					this.casillas[i].addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent e) {
							ratonInConflicto(x, color, defensor, atacante);
						}	

						@Override
						public void mouseExited(MouseEvent e) {
							ratonOutConflicto(x, color, defensor, atacante);
						}
					});
				}else if(f1 instanceof Bárbaro) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\BvBRBC.png"));
					final String atacante = "B";
					this.casillas[i].addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent e) {
							ratonInConflicto(x, color, defensor, atacante);
						}	

						@Override
						public void mouseExited(MouseEvent e) {
							ratonOutConflicto(x, color, defensor, atacante);
						}
					});
				}else if(f1 instanceof Caballero) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\BvCRBC.png"));
					final String atacante = "C";
					this.casillas[i].addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent e) {
							ratonInConflicto(x, color, defensor, atacante);
						}	

						@Override
						public void mouseExited(MouseEvent e) {
							ratonOutConflicto(x, color, defensor, atacante);
						}
					});
				}else if(f1 instanceof Guerrero) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\BvGRBC.png"));
					final String atacante = "G";
					this.casillas[i].addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent e) {
							ratonInConflicto(x, color, defensor, atacante);
						}	

						@Override
						public void mouseExited(MouseEvent e) {
							ratonOutConflicto(x, color, defensor, atacante);
						}
					});
				}else if(f1 instanceof Lancero) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\BvLRBC.png"));
					final String atacante = "L";
					this.casillas[i].addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent e) {
							ratonInConflicto(x, color, defensor, atacante);
						}	

						@Override
						public void mouseExited(MouseEvent e) {
							ratonOutConflicto(x, color, defensor, atacante);
						}
					});
				}
			}
		} else if (f2 instanceof Guerrero) {
			final String defensor= "G";
			if (f2.getFacción().equals(Facción.Facción1)) {
				final String color = "A";
				if(f1 instanceof Arquero) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\GvFABC.png"));
					final String atacante = "F";
					this.casillas[i].addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent e) {
							ratonInConflicto(x, color, defensor, atacante);
						}	

						@Override
						public void mouseExited(MouseEvent e) {
							ratonOutConflicto(x, color, defensor, atacante);
						}
					});
				}else if(f1 instanceof Bárbaro) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\GvBABC.png"));
					final String atacante = "B";
					this.casillas[i].addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent e) {
							ratonInConflicto(x, color, defensor, atacante);
						}	

						@Override
						public void mouseExited(MouseEvent e) {
							ratonOutConflicto(x, color, defensor, atacante);
						}
					});
				}else if(f1 instanceof Caballero) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\GvCABC.png"));
					final String atacante = "C";
					this.casillas[i].addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent e) {
							ratonInConflicto(x, color, defensor, atacante);
						}	

						@Override
						public void mouseExited(MouseEvent e) {
							ratonOutConflicto(x, color, defensor, atacante);
						}
					});
				}else if(f1 instanceof Guerrero) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\GvGABC.png"));
					final String atacante = "G";
					this.casillas[i].addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent e) {
							ratonInConflicto(x, color, defensor, atacante);
						}	

						@Override
						public void mouseExited(MouseEvent e) {
							ratonOutConflicto(x, color, defensor, atacante);
						}
					});
				}else if(f1 instanceof Lancero) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\GvLABC.png"));
					final String atacante = "L";
					this.casillas[i].addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent e) {
							ratonInConflicto(x, color, defensor, atacante);
						}	

						@Override
						public void mouseExited(MouseEvent e) {
							ratonOutConflicto(x, color, defensor, atacante);
						}
					});
				}
			}else {
				final String color = "R";
				if(f1 instanceof Arquero) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\GvFRBC.png"));
					final String atacante = "F";
					this.casillas[i].addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent e) {
							ratonInConflicto(x, color, defensor, atacante);
						}	

						@Override
						public void mouseExited(MouseEvent e) {
							ratonOutConflicto(x, color, defensor, atacante);
						}
					});
				}else if(f1 instanceof Bárbaro) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\GvBRBC.png"));
					final String atacante = "B";
					this.casillas[i].addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent e) {
							ratonInConflicto(x, color, defensor, atacante);
						}	

						@Override
						public void mouseExited(MouseEvent e) {
							ratonOutConflicto(x, color, defensor, atacante);
						}
					});
				}else if(f1 instanceof Caballero) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\GvCRBC.png"));
					final String atacante = "C";
					this.casillas[i].addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent e) {
							ratonInConflicto(x, color, defensor, atacante);
						}	

						@Override
						public void mouseExited(MouseEvent e) {
							ratonOutConflicto(x, color, defensor, atacante);
						}
					});
				}else if(f1 instanceof Guerrero) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\GvGRBC.png"));
					final String atacante = "G";
					this.casillas[i].addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent e) {
							ratonInConflicto(x, color, defensor, atacante);
						}	

						@Override
						public void mouseExited(MouseEvent e) {
							ratonOutConflicto(x, color, defensor, atacante);
						}
					});
				}else if(f1 instanceof Lancero) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\GvLRBC.png"));
					final String atacante = "L";
					this.casillas[i].addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent e) {
							ratonInConflicto(x, color, defensor, atacante);
						}	

						@Override
						public void mouseExited(MouseEvent e) {
							ratonOutConflicto(x, color, defensor, atacante);
						}
					});
				}
			}
		} else if (f2 instanceof Lancero) {
			final String defensor= "L";
			if (f2.getFacción().equals(Facción.Facción1)) {
				final String color = "A";
				if(f1 instanceof Arquero) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\LvFABC.png"));
					final String atacante = "F";
					this.casillas[i].addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent e) {
							ratonInConflicto(x, color, defensor, atacante);
						}	

						@Override
						public void mouseExited(MouseEvent e) {
							ratonOutConflicto(x, color, defensor, atacante);
						}
					});
				}else if(f1 instanceof Bárbaro) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\LvBABC.png"));
					final String atacante = "B";
					this.casillas[i].addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent e) {
							ratonInConflicto(x, color, defensor, atacante);
						}	

						@Override
						public void mouseExited(MouseEvent e) {
							ratonOutConflicto(x, color, defensor, atacante);
						}
					});
				}else if(f1 instanceof Caballero) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\LvCABC.png"));
					final String atacante = "C";
					this.casillas[i].addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent e) {
							ratonInConflicto(x, color, defensor, atacante);
						}	

						@Override
						public void mouseExited(MouseEvent e) {
							ratonOutConflicto(x, color, defensor, atacante);
						}
					});
				}else if(f1 instanceof Guerrero) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\LvGaBC.png"));
					final String atacante = "G";
					this.casillas[i].addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent e) {
							ratonInConflicto(x, color, defensor, atacante);
						}	

						@Override
						public void mouseExited(MouseEvent e) {
							ratonOutConflicto(x, color, defensor, atacante);
						}
					});
				}else if(f1 instanceof Lancero) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\LvLABC.png"));
					final String atacante = "L";
					this.casillas[i].addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent e) {
							ratonInConflicto(x, color, defensor, atacante);
						}	

						@Override
						public void mouseExited(MouseEvent e) {
							ratonOutConflicto(x, color, defensor, atacante);
						}
					});
				}
			}else {
				final String color = "R";
				if(f1 instanceof Arquero) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\LvFRBC.png"));
					final String atacante = "F";
					this.casillas[i].addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent e) {
							ratonInConflicto(x, color, defensor, atacante);
						}	

						@Override
						public void mouseExited(MouseEvent e) {
							ratonOutConflicto(x, color, defensor, atacante);
						}
					});
				}else if(f1 instanceof Bárbaro) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\LvBRBC.png"));
					final String atacante = "B";
					this.casillas[i].addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent e) {
							ratonInConflicto(x, color, defensor, atacante);
						}	

						@Override
						public void mouseExited(MouseEvent e) {
							ratonOutConflicto(x, color, defensor, atacante);
						}
					});
				}else if(f1 instanceof Caballero) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\LvCRBC.png"));
					final String atacante = "C";
					this.casillas[i].addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent e) {
							ratonInConflicto(x, color, defensor, atacante);
						}	

						@Override
						public void mouseExited(MouseEvent e) {
							ratonOutConflicto(x, color, defensor, atacante);
						}
					});
				}else if(f1 instanceof Guerrero) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\LvGRBC.png"));
					final String atacante = "G";
					this.casillas[i].addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent e) {
							ratonInConflicto(x, color, defensor, atacante);
						}	

						@Override
						public void mouseExited(MouseEvent e) {
							ratonOutConflicto(x, color, defensor, atacante);
						}
					});
				}else if(f1 instanceof Lancero) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\LvLRBC.png"));
					final String atacante = "L";
					this.casillas[i].addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent e) {
							ratonInConflicto(x, color, defensor, atacante);
						}	

						@Override
						public void mouseExited(MouseEvent e) {
							ratonOutConflicto(x, color, defensor, atacante);
						}
					});
				}
			}
		} else if (f2 instanceof Caballero) {
			System.out.println("He entrado caballero");
			final String defensor= "C";
			if (f2.getFacción().equals(Facción.Facción1)) {
				final String color = "A";
				if(f1 instanceof Arquero) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\CvFABC.png"));
					final String atacante = "F";
					this.casillas[i].addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent e) {
							ratonInConflicto(x, color, defensor, atacante);
						}	

						@Override
						public void mouseExited(MouseEvent e) {
							ratonOutConflicto(x, color, defensor, atacante);
						}
					});
				}else if(f1 instanceof Bárbaro) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\CvBABC.png"));
					final String atacante = "B";
					this.casillas[i].addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent e) {
							ratonInConflicto(x, color, defensor, atacante);
						}	

						@Override
						public void mouseExited(MouseEvent e) {
							ratonOutConflicto(x, color, defensor, atacante);
						}
					});
				}else if(f1 instanceof Caballero) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\CvCABC.png"));
					final String atacante = "C";
					this.casillas[i].addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent e) {
							ratonInConflicto(x, color, defensor, atacante);
						}	

						@Override
						public void mouseExited(MouseEvent e) {
							ratonOutConflicto(x, color, defensor, atacante);
						}
					});
				}else if(f1 instanceof Guerrero) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\CvGABC.png"));
					final String atacante = "G";
					this.casillas[i].addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent e) {
							ratonInConflicto(x, color, defensor, atacante);
						}	

						@Override
						public void mouseExited(MouseEvent e) {
							ratonOutConflicto(x, color, defensor, atacante);
						}
					});
				}else if(f1 instanceof Lancero) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\CvLABC.png"));
					final String atacante = "L";
					this.casillas[i].addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent e) {
							ratonInConflicto(x, color, defensor, atacante);
						}	

						@Override
						public void mouseExited(MouseEvent e) {
							ratonOutConflicto(x, color, defensor, atacante);
						}
					});
				}
			}else {
				final String color = "R";
				if(f1 instanceof Arquero) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\CvFRBC.png"));
					final String atacante = "F";
					this.casillas[i].addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent e) {
							ratonInConflicto(x, color, defensor, atacante);
						}	

						@Override
						public void mouseExited(MouseEvent e) {
							ratonOutConflicto(x, color, defensor, atacante);
						}
					});
				}else if(f1 instanceof Bárbaro) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\CvBRBC.png"));
					final String atacante = "B";
					this.casillas[i].addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent e) {
							ratonInConflicto(x, color, defensor, atacante);
						}	

						@Override
						public void mouseExited(MouseEvent e) {
							ratonOutConflicto(x, color, defensor, atacante);
						}
					});
				}else if(f1 instanceof Caballero) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\CvCRBC.png"));
					final String atacante = "C";
					this.casillas[i].addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent e) {
							ratonInConflicto(x, color, defensor, atacante);
						}	

						@Override
						public void mouseExited(MouseEvent e) {
							ratonOutConflicto(x, color, defensor, atacante);
						}
					});
				}else if(f1 instanceof Guerrero) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\CvGRBC.png"));
					final String atacante = "G";
					this.casillas[i].addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent e) {
							ratonInConflicto(x, color, defensor, atacante);
						}	

						@Override
						public void mouseExited(MouseEvent e) {
							ratonOutConflicto(x, color, defensor, atacante);
						}
					});
				}else if(f1 instanceof Lancero) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\CvLRBC.png"));
					final String atacante = "L";
					this.casillas[i].addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent e) {
							ratonInConflicto(x, color, defensor, atacante);
						}	

						@Override
						public void mouseExited(MouseEvent e) {
							ratonOutConflicto(x, color, defensor, atacante);
						}
					});
				}
			}
		}
    }
    
    //Bandera blanca y se lo notifica al servidor.
    public void rendirse() {
    	try {
    		out.writeBytes("SURR-Me he rendido.\r\n");
    		out.flush();
    	}catch(IOException ex) {
    		ex.printStackTrace();
    	}
    }
    
    //Todo este codigo está muerto en un princio era lo que hacia mover, pero debido a que daba errores y no fué diseñado demasiado bien se comentó y ahora permanece aqui para el recuerdo.
    
//    public void moverClick() {
//	    	List<Integer> casAModificar = this.tab.quiénesPuedenMover(this.faccion);
//	    	for(Integer posicion: casAModificar) {
//	    		final Integer x = posicion;
//	    		this.casillas[posicion].addMouseListener(new MouseAdapter() {
//					@Override
//					public void mouseClicked(MouseEvent e) {
//	        			botonesMoverFase2(false,tab.getNodo(x).getFicha(faccion));
//	        			botonesClicarFase2(tab.getNodo(x).getFicha(faccion),x);
//	        			btnCancelar.addActionListener(new ActionListener() {
//	        				public void actionPerformed(ActionEvent arg0) {
//	        	    				pintar(tab);
//	        					}
//	        	    		});
//	        			for(MouseListener ac : casillas[x].getMouseListeners()) {
//	        				casillas[x].removeMouseListener(ac);
//	        			}
//					}
//	    		});
//	    	}
//	    	this.botonesMoverFase1(false);
//	    	this.btnCancelar.addActionListener(new ActionListener() {
//	    		
//				public void actionPerformed(ActionEvent arg0) {
//	    			botonesMoverFase1(true);
//	    			for( JButton boton : casillas) {
//	    				for(ActionListener al : boton.getActionListeners()) {
//	    					boton.removeActionListener( al );
//	    				}
//	    			}
//	    		}
//	    	});
//
//    }
//    
//    public void botonesMoverFase1(boolean estado) {
//	    	this.btnDisparar.setEnabled(estado);
//	    	this.btnEsperar.setEnabled(estado);
//	    	this.Mover.setEnabled(estado);
//	    	List<Integer> casAModificar = this.tab.quiénesNOPuedenMover(this.faccion);
//	    	for(Integer posicion : casAModificar) {
//	    		this.casillas[posicion].setEnabled(estado);
//	    	}
//	    	casAModificar = this.tab.quiénesPuedenMover(this.faccion);
//	    	for(Integer posicion : casAModificar) {
//	    		Ficha efarda = this.tab.getNodo(posicion).getFicha(faccion);
//	    		if(efarda instanceof Arquero) {
//	    			if(this.movsF == this.tab.getNodo(posicion).getFicha(faccion).getMovs()) {
//	    				this.casillas[posicion].setEnabled(estado);
//	    			}
//	    		}
//	    		if(efarda instanceof Caballero) {
//	    			if(this.movsC == this.tab.getNodo(posicion).getFicha(faccion).getMovs())
//	    				this.casillas[posicion].setEnabled(estado);
//	    		}
//	    		if(efarda instanceof Bárbaro) {
//	    			if(this.movsB == this.tab.getNodo(posicion).getFicha(faccion).getMovs())
//	    				this.casillas[posicion].setEnabled(estado);
//	    		}
//	    		if(efarda instanceof Guerrero) {
//	    			if(this.movsG == this.tab.getNodo(posicion).getFicha(faccion).getMovs())
//	    				this.casillas[posicion].setEnabled(estado);
//	    		}
//	    		if(efarda instanceof Lancero) {
//	    			if(this.movsL == this.tab.getNodo(posicion).getFicha(faccion).getMovs())
//	    				this.casillas[posicion].setEnabled(estado);
//	    		}
//	    	}
//    }
//    
//    public void botonesMoverFase2(boolean estado, Ficha f) {
//	    	this.btnDisparar.setEnabled(estado);
//	    	this.btnEsperar.setEnabled(estado);
//	    	this.Mover.setEnabled(estado);
//    		List<Integer> casAModificar = this.tab.dóndePuedeMover(f);
//    		for(int i=0; i<45; i++) {
//    			this.casillas[i].setEnabled(estado);
//    		}
//    		for(Integer posicion : casAModificar) {
//    			this.casillas[posicion].setEnabled(!estado);
//    		}
//    	
//    }
//    
//    public void botonesClicarFase2(Ficha f, int j) {
//    	
//		List<Integer> casAModificar = tab.dóndePuedeMover(f);
//		for(Integer posicion : casAModificar) {
//			final Ficha efe = f;
//			final int x = posicion;
//			final int y = j;
//			this.casillas[posicion].setBackground(Color.white);
//			this.casillas[posicion].enable(true);
//			for(MouseListener ms : this.casillas[posicion].getMouseListeners()) {
//				this.casillas[posicion].removeMouseListener(ms);
//			}
//			this.casillas[posicion].addMouseListener(new MouseAdapter() {
//				@Override
//				public void mouseClicked(MouseEvent e) {
//					System.out.println("Entré");
//					moverEnSí(efe,y,x);
//				}
//			});
//				
//		}
//    }
//    
//    //
//    public void moverEnSí(Ficha f, int j , int posicion) {
//    	System.out.println("He entrado en mover en sí");
//    	int resta =posicion-j;
//    	Dirección direccion = null;
//		switch(resta) {
//			case -9:{
//				direccion = Dirección.norte;
//				break;
//			}
//			case 9:{
//				direccion = Dirección.sur;
//				break;
//			}
//			case -1:{
//				direccion = Dirección.oeste;
//				break;
//			}
//			case 1:{
//				direccion = Dirección.este;
//				break;
//			}
//			case 8:{
//				direccion = Dirección.suroeste;
//				break;
//			}
//			case 10:{
//				direccion = Dirección.sureste;
//				break;
//			}
//			case -8:{
//				direccion = Dirección.noreste;
//				break;
//			}
//			case -10:{
//				direccion = Dirección.noroeste;
//				break;
//			}
//				
//		}
//    	Movimiento mov = new Movimiento(f,direccion);
//    	this.inst.add(mov);
//    	/**/ introducirOperacionEnCurso = false;
////    	Tablero tab2 = tab;
//    	/**/ Tablero tab2 = (Tablero) tab.clone();
//    	this.tab = tab2;
//    	tab2.moverFichaGraficamente(f, direccion);
//    	this.tableros.add(tab2);
//		botonesMoverFase1(true);
//		for(int i=0; i<45;i++) {
//			this.casillas[i].setIcon(null);
//			for(MouseListener ac : this.casillas[i].getMouseListeners()) {
//				this.casillas[i].removeMouseListener(ac);
//			}
//		}
//		System.out.println(this.inst.size());
//		Operaciones.setText("Ops.: "+this.inst.size()+"/6");
//		if(this.inst.size()==6) {
//    		this.btnDisparar.setEnabled(false);
//	    	this.btnEsperar.setEnabled(false);
//	    	this.Mover.setEnabled(false);
//		}
//		pintar(tab2);
//		for(ActionListener ac : this.btnCancelar.getActionListeners()) {
//			this.btnCancelar.removeActionListener(ac);
//		}
//		this.agregarFuncionalidadOriginalBtnCancelar();
//		if(f instanceof Arquero) {
//			this.movsF++;
//		}
//		if(f instanceof Caballero) {
//			this.movsC++;
//		}
//		if(f instanceof Bárbaro) {
//			this.movsB++;
//		}
//		if(f instanceof Guerrero) {
//			this.movsG++;
//		}
//		if(f instanceof Lancero) {
//			this.movsL++;
////		}
//    }
    
    //el handler de mover, para ejercutarlo todo
    public void moverClick() {
    	this.botonesCasillasMover(false);
    	this.introducirOperacionEnCurso=true;
    	this.btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				botonesCasillasMover(true);
			}
    	});
    	
    	
    	
    }
    
    //Habilita solo los botones de las fichas que la faccion correspondiente que pueden mover.
    public void botonesCasillasMover(boolean estado) {
    	this.btnDisparar.setEnabled(estado);
    	this.btnEsperar.setEnabled(estado);
    	this.Mover.setEnabled(estado);
    	List<Integer> casAModificar = this.tab.quiénesNOPuedenMover(this.faccion);
    	for(Integer posicion : casAModificar) {
    		this.casillas[posicion].setEnabled(estado);
    	}
    	List<Integer> casFichas = this.tab.quiénesPuedenMover(this.faccion);
    	/**/ if(!estado)
    	for(final Integer posicion : casFichas) {
    		if(!this.botonFichaPuedeMover(posicion)) {
    				this.casillas[posicion].setEnabled(estado);
    		}else {
    	    	MouseListener eventoFicha = new MouseAdapter() {
        			@Override
        			public void mouseClicked(MouseEvent e) {
    					botonesCasillasAMover(tab.getNodo(posicion).getFicha(faccion),true);
    					deshabilitarTodoMenosI(posicion,false);
    					btnCancelar.addActionListener(new ActionListener() {
    						@Override
    						public void actionPerformed(ActionEvent arg0) {
    							deshabilitarTodoMenosI(posicion,true);
    							botonesCasillasAMover(tab.getNodo(posicion).getFicha(faccion),false);
    						}
    			    	});
    				}
    	    	};
    			if(!estado) { 
    				this.casillas[posicion].addMouseListener(eventoFicha);
    			}else {
//    				this.casillas[posicion].removeMouseListener(eventoFicha);
    			}
    		}
    	}
    	if(/*!*/estado) {
    		/**/ this.limpiarActionFichas(casFichas);
    		this.limpiarActionDeshacer();
    		/**/ this.pintar(tab);
    	}
    }
   
    //lo mismo que deshabilitar todo pero el boton de la posicion i no.
    public void deshabilitarTodoMenosI (int i,boolean estado) {
    	List<Integer> casFichas = this.tab.quiénesPuedenMover(this.faccion);
    	for(Integer pos: casFichas) {
    		if(pos!=i) {
    			this.casillas[pos].setEnabled(estado);
    			for(MouseListener ml :this.casillas[pos].getMouseListeners()) {
    				this.casillas[pos].removeMouseListener(ml);
    			}
    		}
    	}
    }
    
    //Todo es todos los botones del tablero.
    public void deshabilitarTodo (boolean estado) {
    	for(Integer pos = 0; pos < 45; pos++) {
    		this.casillas[pos].setEnabled(estado);
    		for(MouseListener ml :this.casillas[pos].getMouseListeners()) {
    			this.casillas[pos].removeMouseListener(ml);
    		}
    	}
    }
    
    //mira si la ficha que se encuentra en la posicion i puede mover, si sus movimientos se han agotado devolverá false.
    public boolean botonFichaPuedeMover(int i){
    	Ficha f = this.tab.getNodo(i).getFicha(faccion);
		if(f instanceof Arquero) {
			if(this.movsF == this.tab.getNodo(i).getFicha(faccion).getMovs())
				return false;
		}else if(f instanceof Caballero) {
			if(this.movsC == this.tab.getNodo(i).getFicha(faccion).getMovs())
				return false;
		}else if(f instanceof Bárbaro) {
			if(this.movsB == this.tab.getNodo(i).getFicha(faccion).getMovs())
				return false;
		}else if(f instanceof Guerrero) {
			if(this.movsG == this.tab.getNodo(i).getFicha(faccion).getMovs())
				return false;
		}else if(f instanceof Lancero) {
			if(this.movsL == this.tab.getNodo(i).getFicha(faccion).getMovs())
				return false;
		}
		return true;
    }
    
    //Pinta de blanco las casillas o no donde puede mover una ficha
    public void botonesCasillasAMover(Ficha f,boolean estado) {
    	List<Integer> posiciones = this.tab.dóndePuedeMover(f);
    	final Movimiento mov;
    	final Ficha fFinal = f;
    	final int posicionFicha = this.tab.dóndeEstá(f);
    	/**/ if(estado)
    	for(final Integer pos : posiciones) {
    		MouseListener eventoCasilla = new MouseAdapter() {
    			@Override
    			public void mouseClicked(MouseEvent e) {
    					añadirMov(fFinal,calcularDireccion(posicionFicha,pos)); 					
    				}
    			};
    		if(estado) {
	    		this.casillas[pos].setBackground(Color.white);
	    		for(MouseListener ml :this.casillas[pos].getMouseListeners()) {
	    			this.casillas[pos].removeMouseListener(ml);
	    		}
	    		this.casillas[pos].addMouseListener(eventoCasilla);
    		}else {
    			this.casillas[pos].setBackground(new Color(245, 245, 220));
    			this.casillas[pos].removeMouseListener(eventoCasilla);
    		}
    	}
    	if(!estado) {
//			this.limpiarActionFichas();
			/**/ this.limpiarActionFichas(posiciones);
			this.limpiarActionDeshacer();
			this.pintar(tab);
    	}
    }
    
    //añade el movimiento adecuado a la instrucción y restaura el tablero. 
    public void añadirMov (Ficha f, Dirección direccion) {
    	this.inst.add(new Movimiento(f,direccion));
    	this.Operaciones.setText("Ops.: "+this.inst.size()+"/6");
    	Tablero tabCopia = (Tablero)this.tab.clone();
    	tabCopia.moverFichaGraficamente(f, direccion);
    	this.tableros.add(tabCopia);
    	this.tab = tabCopia;
		if(this.inst.size()!=6) {
			this.btnDisparar.setEnabled(true);
    		this.btnEsperar.setEnabled(true);
    		this.Mover.setEnabled(true);
		}
    	this.limpiarActions();
		this.pintar(tabCopia);
		if(f instanceof Arquero) {
			this.movsF++;
		}else if(f instanceof Caballero) {
			this.movsC++;
		}else if(f instanceof Bárbaro) {
			this.movsB++;
		}else if(f instanceof Guerrero) {
			this.movsG++;
		}else if(f instanceof Lancero) {
			this.movsL++;
		}
		this.limpiarActionDeshacer();
		this.introducirOperacionEnCurso=false;
    }
    
    //En un principio fueron actions pero como no iban muy bien pues pasó a limpiar mouselisteners de las fichas de la facción correspondiente.
    public void limpiarActionFichas() {
		List<Integer> posFichas = this.tab.quiénesPuedenMover(this.faccion);
		for(Integer posficha : posFichas) {
			for(MouseListener al : this.casillas[posficha].getMouseListeners()) {
				this.casillas[posficha].removeMouseListener(al);
			}
		}
    }
    
    //Con el método anterior limpiabas la acción de las que pueden mover. Con este, a las que se puede mover (que es lo que deseamos).
    public void limpiarActionFichas(List<Integer> posiciones) {
		for(Integer posficha : posiciones) {
			for(MouseListener al : this.casillas[posficha].getMouseListeners()) {
				this.casillas[posficha].removeMouseListener(al);
			}
		}
    }
    
    //eso, limpia los actions del boton deshacer.
    public void limpiarActionDeshacer() {
		for(ActionListener al : this.btnCancelar.getActionListeners()) {
			this.btnCancelar.removeActionListener(al);
		}
		this.agregarFuncionalidadOriginalBtnCancelar();
    }
    
    //Aunque pone actions limpia mouse listeners.
    public void limpiarActions() {
    	for(int i = 0 ; i<45; i++) {
    		this.casillas[i].setEnabled(true);
    		for(MouseListener al : this.casillas[i].getMouseListeners()) {
    			this.casillas[i].removeMouseListener(al);
    		}
    	}
    }
    
    //Calcula la direaccion a partir de la posicion de la ficha y la posicion de la casilla a donde quiere moverse.
    public Dirección calcularDireccion(int posicionFicha, int posicionCasilla) {
    	System.out.println(posicionFicha+" "+posicionCasilla);
    	int resta =posicionCasilla-posicionFicha;
    	Dirección direccion = null;
		switch(resta) {
		case -9:{
			direccion = Dirección.norte;
			break;
		}
		case 9:{
			direccion = Dirección.sur;
			break;
		}
		case -1:{
			direccion = Dirección.oeste;
			break;
		}
		case 1:{
			direccion = Dirección.este;
			break;
		}
		case 8:{
			direccion = Dirección.suroeste;
			break;
		}
		case 10:{
			direccion = Dirección.sureste;
			break;
		}
		case -8:{
			direccion = Dirección.noreste;
			break;
		}
		case -10:{
			direccion = Dirección.noroeste;
			break;
		}
			
		}
		System.out.println(direccion.toString());
		return direccion;
    }
    
    
    //los peina y cepilla para no tener problemas luego al visualizar el tablero, muy util en muchas circunstancia.
    public void peinarEventos(int i) {
    	for(MouseListener ls : this.casillas[i].getMouseListeners()) {
    		this.casillas[i].removeMouseListener(ls);
    	}
    }
    
    //Manda al servidor un mensaje de que se ha rendido
    public void salir() {
    	try {
    		if(out!=null) {
    			out.writeBytes("AB-Me he rendido.\r\n");
    			out.flush();
    		}
    	}catch(IOException ex) {
    		ex.printStackTrace();
    	}
    }
    
    //Bloquea todos los botones excepto las catapultas disponibles.
    public void bloquearCasillasDisparo(boolean estado, final List<Integer> catapultas) {
    	this.btnDisparar.setEnabled(estado);
    	this.btnEsperar.setEnabled(estado);
    	this.Mover.setEnabled(estado);
    		if(catapultas.size()==2) {
    				for(int i=0; i<45; i++) {
    					final int x = i;
    					if(this.casillas[i].equals(this.casillas[catapultas.get(0)])||this.casillas[i].equals(this.casillas[catapultas.get(1)])){
    	    				this.casillas[i].addMouseListener( new MouseAdapter() {
    	    					@Override
    	    					public void mouseClicked(MouseEvent e) {
    	    						if(x==catapultas.get(0)) {
    	    							casillas[catapultas.get(1)].setEnabled(false);
    	    							peinarEventos(catapultas.get(1));
    	    						}else {
    	    							casillas[catapultas.get(0)].setEnabled(false);
    	    							peinarEventos(catapultas.get(0));
    	    						}
    	    						pintarCasillasDisparo(true,x);
    	    					}
    	    				});
    	    				this.btnCancelar.addActionListener( new ActionListener() {
    	    					public void actionPerformed(ActionEvent arg0) {
    	    						pintarCasillasDisparo(false,x);
    	    					}
    	    				});
    					}else {
    						this.casillas[i].setEnabled(estado);
    	    			}
    				}
    		}else {
    			for(int i=0; i<45; i++) {
    				final int x = i;
    				if(this.casillas[i].equals(this.casillas[catapultas.get(0)])) {
    					this.casillas[i].addMouseListener( new MouseAdapter() {
    						@Override
    						public void mouseClicked(MouseEvent e) {
    							pintarCasillasDisparo(true,x);
    						}
    					});
    					this.btnCancelar.addActionListener( new ActionListener() {
    						public void actionPerformed(ActionEvent arg0) {
    							pintarCasillasDisparo(false,x);
    						}
    					});
    				}else {
    					this.casillas[i].setEnabled(estado);
    				}
    			}
    		}
    		if(/*!*/estado) {
        		/**/ this.limpiarActions();
        		this.limpiarActionDeshacer();
        		/**/ this.pintar(tab);
        	}
    }
    
    //Pintar o no las casillas a las que se puede disparar con las catapultas.
    public void pintarCasillasDisparo(boolean estado, final Integer posicionCat) {
    	if(estado) {
	    		List<Integer> casillasCat1 =this.tab.dóndeDispararProyectiles((Catapulta)this.tab.getNodo(posicionCat).getCasilla());
	    		for(final Integer cat1 : casillasCat1) {
	    			this.casillas[cat1].setBackground(Color.white);
					for(MouseListener ac : casillas[cat1].getMouseListeners()) {
						casillas[cat1].removeMouseListener(ac);
					}
					final Ficha f = this.tab.getNodo(/*cat1*/ posicionCat).getFichaDefensora();
					final Integer x = cat1;
					final Catapulta cas = (Catapulta) this.tab.getNodo(posicionCat).getCasilla();
					this.casillas[cat1].addMouseListener( new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
//							System.out.println("1. f es null? " + f == null);
							disparar(f,cas,x,posicionCat);
						}
					});
	    		}
	//Devuelve a los botones a la situación original.
    }else {
//		for(int i=0; i<45;i++) {
//				casillas[i].setIcon(null);
//				for(MouseListener ac : casillas[i].getMouseListeners()) {
//					casillas[i].removeMouseListener(ac);
//				}
//				this.casillas[i].setBackground(new Color(240, 230, 140));
//				this.casillas[i].setEnabled(!estado);
//		}
//		pintar(tab);
    	
    	if(!estado) {
    		/**/ this.limpiarActions();
    		this.limpiarActionDeshacer();
    		/**/ this.pintar(tab);
    	}
    	
    }
    }
    
    //Dada una ficha y una catapulta ademas del lugar de disparo y la posicion de la cata pulta crea un objeto Disparo y lo añade a las instrucciones a mandar, ademas de restaurar el tablero a su forma anterior
    //quitando los eventos y los botones deshabilitados
    public void disparar(Ficha f, Catapulta cas, int lugarDisparo, int posicion) {
//    	System.out.println("2. f es null? " + f == null);
    	Disparo disp = new Disparo(f,cas,lugarDisparo);
		inst.add(disp);
		/**/ introducirOperacionEnCurso = false;
		if(inst.size()==6) {
    		btnDisparar.setEnabled(false);
	    	btnEsperar.setEnabled(false);
	    	Mover.setEnabled(false);
		}else {
    		btnDisparar.setEnabled(true);
	    	btnEsperar.setEnabled(true);
	    	Mover.setEnabled(true);
		}
		for(ActionListener ac : btnCancelar.getActionListeners()) {
			btnCancelar.removeActionListener(ac);
		}
		agregarFuncionalidadOriginalBtnCancelar();
		for(int i=0; i<45;i++) {
			casillas[i].setIcon(null);
			for(MouseListener ac : casillas[i].getMouseListeners()) {
				casillas[i].removeMouseListener(ac);
			}
		}
		if(posicion==24) {
			catR=false;
		}else {
			catA=false;
		}
		Tablero tab2 = (Tablero)tab.clone();
		Operaciones.setText("Ops.: "+inst.size()+"/6");
		tableros.add(tab2);
		pintar(tab2);
    }
    
    //Restaura la funcionalidad original (deshacer la última operación) del botón Deshacer.
    private void agregarFuncionalidadOriginalBtnCancelar() {
    	
    	/**/ btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(introducirOperacionEnCurso == false) {
					
					if(inst.size()>0) {
						Operación op = inst.get(inst.size()-1);
						if(op instanceof Movimiento) {
							Movimiento mov = (Movimiento)inst.get(inst.size()-1);
							Ficha f = mov.getFicha();
							if(f instanceof Arquero) {
								movsF--;
							}else if(f instanceof Caballero) {
								movsC--;
							}else if(f instanceof Bárbaro) {
								movsB--;
							}else if(f instanceof Guerrero) {
								movsG--;
							}else if(f instanceof Lancero) {
								movsL--;
							}
						}else if(op instanceof Disparo) {
							Disparo disp = (Disparo)inst.get(inst.size()-1);
							if(disp.getCatapulta().getIdentificador()==1) {
								catA=true;
							}else if(disp.getCatapulta().getIdentificador()==2){
								catR=true;
							}
						}
						if(inst.size()==6) {
				    		btnDisparar.setEnabled(true);
					    	btnEsperar.setEnabled(true);
					    	Mover.setEnabled(true);
						}
						inst.remove(inst.size()-1);
						tableros.remove(tableros.size()-1);
						tab = tableros.get(tableros.size()-1);
						limpiarActions();
						pintar(tableros.get(tableros.size()-1));
						Operaciones.setText("Ops.:" + inst.size() + "/6");
						
					}
					else {
						
						JOptionPane.showMessageDialog(null, "No hay ninguna operación por deshacer.", "Atención", JOptionPane.WARNING_MESSAGE);
						
					}
					
				}
				else {
					
					//ESTA ES UNA SOLUCIÓN PARA ELIMINAR TODOS LOS CAPTURADORES DE EVENTOS AL DESHACER:
					
//					for(int i=0; i<45;i++) {
//						for(MouseListener ac : casillas[i].getMouseListeners()) {
//							casillas[i].removeMouseListener(ac);
//						}
//					}
					
					introducirOperacionEnCurso = false;
					
				}
				
			}
		});
    	
    }
    
    
    //Resetea los movimientos de las fichas, para que al comenzar un turno vuelvan a estar frescas.
    public void resetearMovs() {
    	this.movsB=0;
    	this.movsF=0;
    	this.movsC=0;
    	this.movsG=0;
    	this.movsL=0;
    }
    
    //Con el booleano estado habilita o deshabilita los botones del menu
    public void casillasMenu(boolean estado) {
    	this.btnCancelar.setEnabled(estado);
    	this.btnDisparar.setEnabled(estado);
    	this.btnEsperar.setEnabled(estado);
    	this.Mover.setEnabled(estado);
    	this.Listo.setEnabled(estado);
    }
    
    
    //Resetea las catapultas para que en un turno nuevo vuelvan a estar funcionales
    public void resetearCatapultas() {
    	this.catA =true;
    	this.catR =true;
    }


    
    
    // -------------------------------------------------------------------------------------------------------------
    // -------------------------------------------------------------------------------------------------------------
    // --------------------------- RATÓN ENTRA O SALE DE CASILLAS (BRILLO EN LAS IMÁGENES) -------------------------
    // -------------------------------------------------------------------------------------------------------------
    // -------------------------------------------------------------------------------------------------------------
    
    //Básicamente añade fondo de color blanco y una imagen con o sin color a el botón correspondiente que puede representar una ficha o una casilla.
    
    public void ratonInCoronaAzul(int i) {
		this.casillas[i].setIcon(new ImageIcon("Recursos\\CoronaAzul.png"));
		this.casillas[i].setBackground(Color.white);
	}

	public void ratonOutCoronaAzul(int i) {
		this.casillas[i].setIcon(new ImageIcon("Recursos\\CoronaBC.png"));
		this.casillas[i].setBackground(new Color(245, 245, 220));
	}
	
	public void ratonInCoronaRojo(int i) {
		this.casillas[i].setIcon(new ImageIcon("Recursos\\CoronaRojo.png"));
		this.casillas[i].setBackground(Color.white);
	}

	public void ratonOutCoronaRojo(int i) {
		this.casillas[i].setIcon(new ImageIcon("Recursos\\CoronaBC.png"));
		this.casillas[i].setBackground(new Color(245, 245, 220));
	}
	
	public void ratonInCatapultaAzul(int i) {
		this.casillas[i].setIcon(new ImageIcon("Recursos\\CatapultaAzulC.png"));
		this.casillas[i].setBackground(Color.white);
	}

	public void ratonOutCatapultaAzul(int i) {
		this.casillas[i].setIcon(new ImageIcon("Recursos\\CatapultaAzul.png"));
		this.casillas[i].setBackground(new Color(245, 245, 220));
	}
	
	public void ratonInCatapultaRojo(int i) {
		this.casillas[i].setIcon(new ImageIcon("Recursos\\CatapultaRojaC.png"));
		this.casillas[i].setBackground(Color.white);
	}

	public void ratonOutCatapultaRojo(int i) {
		this.casillas[i].setIcon(new ImageIcon("Recursos\\CatapultaRoja.png"));
		this.casillas[i].setBackground(new Color(245, 245, 220));
	}
	
	public void ratonInColina(int i) {
		this.casillas[i].setIcon(new ImageIcon("Recursos\\ColinaC.png"));
		this.casillas[i].setBackground(Color.white);
	}

	public void ratonOutColina(int i) {
		this.casillas[i].setIcon(new ImageIcon("Recursos\\Colina.png"));
		this.casillas[i].setBackground(new Color(245, 245, 220));
	}
	
	public void ratonInCurarse(int i) {
		this.casillas[i].setIcon(new ImageIcon("Recursos\\CurarseC.png"));
		this.casillas[i].setBackground(Color.white);
	}

	public void ratonOutCurarse(int i) {
		this.casillas[i].setIcon(new ImageIcon("Recursos\\Curarse.png"));
		this.casillas[i].setBackground(new Color(245, 245, 220));
	}
	
	public void ratonInHacha(int i) {
		this.casillas[i].setIcon(new ImageIcon("Recursos\\HachaDivasonicaS.png"));
		this.casillas[i].setBackground(Color.white);
	}

	public void ratonOutHacha(int i) {
		this.casillas[i].setIcon(new ImageIcon("Recursos\\HachaDivasonica.png"));
		this.casillas[i].setBackground(new Color(245, 245, 220));
	}

	public void ratonInArqueroAzul(int i) {
		this.casillas[i].setIcon(new ImageIcon("Recursos\\ArqueroAzulC.png"));
		this.casillas[i].setBackground(Color.white);
	}

	public void ratonOutArqueroAzul(int i) {
		this.casillas[i].setIcon(new ImageIcon("Recursos\\ArqueroAzulBC.png"));
		this.casillas[i].setBackground(new Color(245, 245, 220));
	}

	public void ratonInArqueroRojo(int i) {
		this.casillas[i].setIcon(new ImageIcon("Recursos\\ArqueroRojoC.png"));
		this.casillas[i].setBackground(Color.white);
	}

	public void ratonOutArqueroRojo(int i) {
		this.casillas[i].setIcon(new ImageIcon("Recursos\\ArqueroRojoBC.png"));
		this.casillas[i].setBackground(new Color(245, 245, 220));
	}
	
	public void ratonInBarbaroAzul(int i) {
		this.casillas[i].setIcon(new ImageIcon("Recursos\\BarbaroAzulC.png"));
		this.casillas[i].setBackground(Color.white);
	}

	public void ratonOutBarbaroAzul(int i) {
		this.casillas[i].setIcon(new ImageIcon("Recursos\\BarbaroAzulBC.png"));
		this.casillas[i].setBackground(new Color(245, 245, 220));
	}

	public void ratonInBarbaroRojo(int i) {
		this.casillas[i].setIcon(new ImageIcon("Recursos\\BarbaroRojoC.png"));
		this.casillas[i].setBackground(Color.white);
	}

	public void ratonOutBarbaroRojo(int i) {
		this.casillas[i].setIcon(new ImageIcon("Recursos\\BarbaroRojoBC.png"));
		this.casillas[i].setBackground(new Color(245, 245, 220));
	}
	
	public void ratonInCaballeroAzul(int i) {
		this.casillas[i].setIcon(new ImageIcon("Recursos\\CaballeroAzulC.png"));
		this.casillas[i].setBackground(Color.white);
	}

	public void ratonOutCaballeroAzul(int i) {
		this.casillas[i].setIcon(new ImageIcon("Recursos\\CaballeroAzulBC.png"));
		this.casillas[i].setBackground(new Color(245, 245, 220));
	}

	public void ratonInCaballeroRojo(int i) {
		this.casillas[i].setIcon(new ImageIcon("Recursos\\CaballeroRojoC.png"));
		this.casillas[i].setBackground(Color.white);
	}

	public void ratonOutCaballeroRojo(int i) {
		this.casillas[i].setIcon(new ImageIcon("Recursos\\CaballeroRojoBC.png"));
		this.casillas[i].setBackground(new Color(245, 245, 220));
	}
	
	public void ratonInGuerreroAzul(int i) {
		this.casillas[i].setIcon(new ImageIcon("Recursos\\GuerreroAzulC.png"));
		this.casillas[i].setBackground(Color.white);
	}

	public void ratonOutGuerreroAzul(int i) {
		this.casillas[i].setIcon(new ImageIcon("Recursos\\GuerreroAzulBC.png"));
		this.casillas[i].setBackground(new Color(245, 245, 220));
	}

	public void ratonInGuerreroRojo(int i) {
		this.casillas[i].setIcon(new ImageIcon("Recursos\\GuerreroRojoC.png"));
		this.casillas[i].setBackground(Color.white);
	}

	public void ratonOutGuerreroRojo(int i) {
		this.casillas[i].setIcon(new ImageIcon("Recursos\\GuerreroRojoBC.png"));
		this.casillas[i].setBackground(new Color(245, 245, 220));
	}
	
	public void ratonInLanceroAzul(int i) {
		this.casillas[i].setIcon(new ImageIcon("Recursos\\LanceroAzulC.png"));
		this.casillas[i].setBackground(Color.white);
	}

	public void ratonOutLanceroAzul(int i) {
		this.casillas[i].setIcon(new ImageIcon("Recursos\\LanceroAzulBC.png"));
		this.casillas[i].setBackground(new Color(245, 245, 220));
	}

	public void ratonInLanceroRojo(int i) {
		this.casillas[i].setIcon(new ImageIcon("Recursos\\LanceroRojoC.png"));
		this.casillas[i].setBackground(Color.white);
	}

	public void ratonOutLanceroRojo(int i) {
		this.casillas[i].setIcon(new ImageIcon("Recursos\\LanceroRojoBC.png"));
		this.casillas[i].setBackground(new Color(245, 245, 220));
	}
	
	public void ratonInConflicto(int i, String color, String defensor, String atacante) {
		String todo = "Recursos\\"+defensor+"v"+atacante+color+"C.png";
		this.casillas[i].setIcon(new ImageIcon(todo));
		this.casillas[i].setBackground(Color.white);
	}
	
	public void ratonOutConflicto(int i, String color, String defensor, String atacante) {
		String todo = "Recursos\\"+defensor+"v"+atacante+color+"BC.png";
		this.casillas[i].setIcon(new ImageIcon(todo));
		this.casillas[i].setBackground(Color.white);
	}
	
	public void ratonInImpactoProyectil(int i) {
		this.casillas[i].setIcon(new ImageIcon("Recursos\\ImpactoProyectilC.png"));
		this.casillas[i].setBackground(Color.white);
	}

	public void ratonOutImpactoProyectil(int i) {
		this.casillas[i].setIcon(new ImageIcon("Recursos\\ImpactoProyectilBC.png"));
		this.casillas[i].setBackground(new Color(245, 245, 220));
	}
	
	//__________________________________________________________________________________________________________
	
	//Para conseguir los object Input y Output Streams.
	public void setIn(DataInputStream in) {
		try {
			this.in= new ObjectInputStream(in);
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public void setOut(DataOutputStream out) {
		try {
			this.out= new ObjectOutputStream(out);
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	
	//A través de un arbol DOM creamos un archivo XML a partir del primer tablero de la partida y lo guradamos en 'PARTIDAS GUARDADAS'
	public void guardarPartida() throws ParserConfigurationException, TransformerException {
    	
    	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    	DocumentBuilder db = dbf.newDocumentBuilder();
    	Document doc = db.newDocument();
    	
    	Element tableroElemento = this.tableros.get(0).getElemento(doc);
    	
    	tableroElemento.setAttribute("turno", this.turno + "");
    	
    	doc.appendChild(tableroElemento);
    	
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
    	SimpleDateFormat formato = new SimpleDateFormat("yyyy-MMMMM-dd_hh-mm-ss");
    	String fechaString = formato.format(Calendar.getInstance().getTime());
    	
    	StreamResult result = new StreamResult(new File("PartidasGuardadas//"+fechaString +"_"+ this.nombre +"VS" +this.oponente +".xml"));
    	t.transform(source , result);
    	
    }
	
	public void setTablero() {
		try {
			this.tab = (Tablero)in.readObject();
			this.tableros.clear();
			this.limpiarActions();
			this.pintar(tab);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void setTurno(int x) {
		this.turno= x;
		Turno.setText("Turno: "+turno);
	}
	
}
