package Presentación;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import LN.Tablero;
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
import MD_Tablero.Normal;
import Utilidades.Facción;

public class TableroGrafico extends JFrame {

	private JPanel contentPane;
	private JButton btnMenu;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private Tablero tab;
	private String nombre;
	private final JButton[] casillas = new JButton[45];
	private boolean azul;
	private JTextArea txtCasilla;
	private JTextArea txtFichaDef;
	private JTextArea txtFichaAt;

	/**
	 * Create the frame.
	 */
	public TableroGrafico(final ClienteGUI menu) {
		tab = new Tablero();
		setResizable(false);
		final TableroGrafico tablero = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1080, 735);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 230, 140));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(42, 21, 618, 127);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("Recursos\\LogobienV2.png"));
		lblNewLabel_1.setBounds(816, 21, 216, 102);
		contentPane.add(lblNewLabel_1);

		JPanel panel = new JPanel();
		panel.setBounds(32, 206, 702, 390);
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
		cas8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
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
		cas18.setBackground(new Color(204, 204, 255));
		panel.add(cas18);

		final JButton cas19 = new JButton("");
		cas19.setBackground(new Color(245, 245, 220));
		panel.add(cas19);

		JButton cas20 = new JButton("");
		;
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
		cas26.setBackground(new Color(255, 204, 204));
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
		btnMenu.setBounds(42, 633, 179, 47);
		contentPane.add(btnMenu);

		JLabel lblNewLabel_2 = new JLabel("Turno: 2");
		lblNewLabel_2.setBounds(249, 633, 64, 29);
		contentPane.add(lblNewLabel_2);

		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(751, 206, 9, 474);
		contentPane.add(separator);

		lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(42, 170, 191, 14);
		contentPane.add(lblNewLabel_3);

		lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(249, 170, 196, 14);
		contentPane.add(lblNewLabel_4);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(770, 478, 278, 14);
		contentPane.add(separator_1);

		JLabel lb_Casilla = new JLabel("Casilla:");
		lb_Casilla.setBounds(770, 514, 46, 14);
		contentPane.add(lb_Casilla);

		JLabel lb_Info = new JLabel("Informaci\u00F3n:");
		lb_Info.setBounds(770, 489, 89, 14);
		contentPane.add(lb_Info);

		JLabel lb_Ficha = new JLabel("Ficha:");
		lb_Ficha.setBounds(770, 612, 46, 14);
		contentPane.add(lb_Ficha);

		txtCasilla = new JTextArea();
		txtCasilla.setFont(new Font("Monospaced", Font.PLAIN, 11));
		txtCasilla.setEditable(false);
		txtCasilla.setBounds(825, 514, 223, 82);
		contentPane.add(txtCasilla);

		txtFichaDef = new JTextArea();
		txtFichaDef.setFont(new Font("Monospaced", Font.PLAIN, 11));
		txtFichaDef.setBounds(825, 609, 142, 71);
		contentPane.add(txtFichaDef);

		JButton Mover = new JButton("");

		Mover.setBackground(new Color(240, 230, 140));
		Mover.setIcon(new ImageIcon("Recursos\\Mover.png"));
		Mover.setBorder(null);
		Mover.setBounds(823, 206, 179, 47);
		contentPane.add(Mover);

		JButton btnDisparar = new JButton("");
		btnDisparar.setBackground(new Color(240, 230, 140));
		btnDisparar.setIcon(new ImageIcon("Recursos\\Disparar.png"));
		btnDisparar.setBounds(822, 264, 179, 47);
		btnDisparar.setBorder(null);
		contentPane.add(btnDisparar);

		JButton btnEsperar = new JButton("");

		btnEsperar.setIcon(new ImageIcon("Recursos\\Esperar.png"));
		btnEsperar.setBackground(new Color(240, 230, 140));
		btnEsperar.setBorder(null);
		btnEsperar.setBounds(822, 322, 179, 47);
		contentPane.add(btnEsperar);

		JButton btnCancelar = new JButton("");
		btnCancelar.setBackground(new Color(240, 230, 140));
		btnCancelar.setIcon(new ImageIcon("Recursos\\Deshacer.png"));
		btnCancelar.setBounds(822, 380, 179, 47);
		btnCancelar.setBorder(null);
		contentPane.add(btnCancelar);

		JButton Rendirse = new JButton("");
		Rendirse.setIcon(new ImageIcon("Recursos\\Rendirse.png"));
		Rendirse.setBackground(new Color(240, 230, 140));
		Rendirse.setBounds(900, 438, 100, 29);
		Rendirse.setBorder(null);
		contentPane.add(Rendirse);

		JLabel lb_Casilla_1 = new JLabel("Movs: 4/6");
		lb_Casilla_1.setFont(new Font("Consolas", Font.PLAIN, 13));
		lb_Casilla_1.setBounds(825, 438, 77, 29);
		contentPane.add(lb_Casilla_1);

		JButton btnSiguienteMovimiento = new JButton("Siguiente");
		btnSiguienteMovimiento.setBounds(555, 633, 179, 47);
		contentPane.add(btnSiguienteMovimiento);

		JLabel lblNewLabel_2_1 = new JLabel("Movimiento: 0");
		lblNewLabel_2_1.setBounds(249, 653, 89, 29);
		contentPane.add(lblNewLabel_2_1);

		txtFichaAt = new JTextArea();
		txtFichaAt.setFont(new Font("Monospaced", Font.PLAIN, 11));
		txtFichaAt.setBounds(977, 607, 71, 73);
		contentPane.add(txtFichaAt);

		JButton btnAnteriorMovimiento = new JButton("Anterior");
		btnAnteriorMovimiento.setBounds(366, 633, 179, 47);
		contentPane.add(btnAnteriorMovimiento);
	}

	public void setNombre(String nombre1, String nombre2, boolean azul) {
		if (azul) {
			lblNewLabel_3.setText("Tú: " + nombre1 + " (Azul)");
			lblNewLabel_4.setText("Oponente: " + nombre2 + " (Rojo)");
		} else {
			lblNewLabel_3.setText("Tú: " + nombre1 + " (Rojo)");
			lblNewLabel_4.setText("Oponente: " + nombre2 + " (Azul)");
		}
		this.azul = azul;
		System.out.println(this.azul);
		this.nombre = nombre1;
	}

	public void ratonInCoronaAzul(int i) {
		this.casillas[i].setIcon(new ImageIcon("Recursos\\CoronaAzul.png"));
		this.casillas[i].setBackground(Color.white);
	}

	public void ratonOutCoronaAzul(int i) {
		this.casillas[i].setIcon(new ImageIcon("Recursos\\CoronaBC.png"));
		this.casillas[i].setBackground(new Color(204, 204, 255));
	}
	
	public void ratonInCoronaRojo(int i) {
		this.casillas[i].setIcon(new ImageIcon("Recursos\\CoronaRojo.png"));
		this.casillas[i].setBackground(Color.white);
	}

	public void ratonOutCoronaRojo(int i) {
		this.casillas[i].setIcon(new ImageIcon("Recursos\\CoronaBC.png"));
		this.casillas[i].setBackground(new Color(255, 204, 204));
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
	
	public void ratonInConflicto(int i, String color, String atacante, String defensor) {
		String todo = "Recursos\\"+defensor+"v"+atacante+color+"C.png";
		this.casillas[i].setIcon(new ImageIcon(todo));
		this.casillas[i].setBackground(Color.white);
	}
	
	public void ratonOutConflicto(int i, String color, String atacante, String defensor) {
		String todo = "Recursos\\"+defensor+"v"+atacante+color+"BC.png";
		this.casillas[i].setIcon(new ImageIcon(todo));
		this.casillas[i].setBackground(Color.white);
	}
	
	
	
	

	public void ratonInNodo(int i) {

		String casillaInfo = "";
		String fichaDefInfo = "";
		String fichaAtInfo = "";

		Casilla casilla = this.tab.getNodo(i).getCasilla();
		Ficha fichaDef = this.tab.getNodo(i).getFichaDefensora();
		Ficha fichaAt = this.tab.getNodo(i).getFichaAtacante();

		// Info. de la casilla

		casillaInfo += casilla.getClass().getCanonicalName().split("\\.")[casilla.getClass().getCanonicalName().split("\\.").length-1] + "\r\n";

		if (casilla instanceof Copa) {

			casillaInfo += "Facción: " + ((Copa) casilla).getFacción() + "\r\n";
			casillaInfo += "Vida: " + ((Copa) casilla).getVida() + "\r\n";

		} else if (casilla instanceof Curación) {

			casillaInfo += "Identificador: " + ((Curación) casilla).getIdentificador() + "\r\n";
			casillaInfo += "Curación: " + ((Curación) casilla).getCuración() + "\r\n";

		} else if (casilla instanceof Catapulta) {

			casillaInfo += "Identificador: " + ((Catapulta) casilla).getIdentificador() + "\r\n";
			casillaInfo += "Uso: ";

			if (fichaDef != null && fichaAt == null) {

				casillaInfo += fichaDef.getFacción() + "\r\n";

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

			fichaDefInfo += "Facción: " + fichaDef.getFacción() + "\r\n";
			if (fichaAt != null)
				fichaAtInfo += fichaAt.getFacción() + "\r\n";

			fichaDefInfo += "Vida: " + fichaDef.getVida() + "\r\n";
			if (fichaAt != null)
				fichaAtInfo += fichaAt.getVida() + "\r\n";

			if (fichaDef.tieneHacha()) {

				fichaDefInfo += "Hacha Divasónica: Sí" + "\r\n";

			} else {

				fichaDefInfo += "Hacha Divasónica: No" + "\r\n";

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

    public void pintar(Tablero tab) {
    	
    	//Representar gráficamente el tablero tab
    	for (int i = 0; i < 45; i++) {
    		
			final Integer x = i;
			Casilla cas = this.tab.getNodo(i).getCasilla();
			Ficha f = this.tab.getNodo(i).getFichaDefensora();
			Ficha fat = this.tab.getNodo(i).getFichaAtacante();
			if (cas instanceof Catapulta) {
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
			}
		}
    }
    
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
			if (f2.getFacción().equals(Facción.Facción1))
				if(f1 instanceof Arquero) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\BvFABC.png"));
				}else if(f1 instanceof Bárbaro) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\BvBABC.png"));
				}else if(f1 instanceof Caballero) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\BvCABC.png"));
				}else if(f1 instanceof Guerrero) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\BvGABC.png"));
				}else if(f1 instanceof Lancero) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\BvLABC.png"));
				}
			else {
				if(f1 instanceof Arquero) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\BvFRBC.png"));
				}else if(f1 instanceof Bárbaro) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\BvBRBC.png"));
				}else if(f1 instanceof Caballero) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\BvCRBC.png"));
				}else if(f1 instanceof Guerrero) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\BvGRBC.png"));
				}else if(f1 instanceof Lancero) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\BvLRBC.png"));
				}
			}
		} else if (f2 instanceof Guerrero) {
			if (f2.getFacción().equals(Facción.Facción1))
				if(f1 instanceof Arquero) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\GvFABC.png"));
				}else if(f1 instanceof Bárbaro) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\GvBABC.png"));
				}else if(f1 instanceof Caballero) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\GvCABC.png"));
				}else if(f1 instanceof Guerrero) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\GvGABC.png"));
				}else if(f1 instanceof Lancero) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\GvLABC.png"));
				}
			else {
				if(f1 instanceof Arquero) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\GvFRBC.png"));
				}else if(f1 instanceof Bárbaro) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\GvBRBC.png"));
				}else if(f1 instanceof Caballero) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\GvCRBC.png"));
				}else if(f1 instanceof Guerrero) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\GvGRBC.png"));
				}else if(f1 instanceof Lancero) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\gvLRBC.png"));
				}
			}
		} else if (f2 instanceof Lancero) {
			if (f2.getFacción().equals(Facción.Facción1))
				if(f1 instanceof Arquero) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\LvFABC.png"));
				}else if(f1 instanceof Bárbaro) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\LvBABC.png"));
				}else if(f1 instanceof Caballero) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\LvCABC.png"));
				}else if(f1 instanceof Guerrero) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\LvGaBC.png"));
				}else if(f1 instanceof Lancero) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\LvLABC.png"));
				}
			else {
				if(f1 instanceof Arquero) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\LvFRBC.png"));
				}else if(f1 instanceof Bárbaro) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\LvBRBC.png"));
				}else if(f1 instanceof Caballero) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\LvCRBC.png"));
				}else if(f1 instanceof Guerrero) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\LvGRBC.png"));
				}else if(f1 instanceof Lancero) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\LvLRBC.png"));
				}
			}
		} else if (f2 instanceof Caballero) {
			if (f2.getFacción().equals(Facción.Facción1))
				if(f1 instanceof Arquero) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\CvFABC.png"));
				}else if(f1 instanceof Bárbaro) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\CvBABC.png"));
				}else if(f1 instanceof Caballero) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\CvCABC.png"));
				}else if(f1 instanceof Guerrero) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\CvGaBC.png"));
				}else if(f1 instanceof Lancero) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\CvLABC.png"));
				}
			else {
				if(f1 instanceof Arquero) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\CvFRBC.png"));
				}else if(f1 instanceof Bárbaro) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\CvBRBC.png"));
				}else if(f1 instanceof Caballero) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\CvCRBC.png"));
				}else if(f1 instanceof Guerrero) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\CvGRBC.png"));
				}else if(f1 instanceof Lancero) {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\CvLRBC.png"));
				}
			}
		}
    }
}
