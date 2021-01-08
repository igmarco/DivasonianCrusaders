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
import MD_Tablero.Nodo;
import Utilidades.Facción;

public class TableroGrafico extends JFrame {

	private JPanel contentPane;
	private JButton btnMenu;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private Tablero tab;
	private final JButton[] casillas = new JButton[45];

	/**
	 * Create the frame.
	 */
	public TableroGrafico() {
		tab = new Tablero();
		setResizable(false);
		final TableroGrafico tablero= this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1080, 720);
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
		
		JButton cas20 = new JButton("");;
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
		
		Nodo[] nodos = tab.nodos;
		for(int i=0 ; i<45; i++) {
			final Integer x =i;
			Casilla cas = nodos[i].getCasilla();
			Ficha f = nodos[i].getFichaDefensora();
			Ficha fat = nodos[i].getFichaDefensora();
			if(cas instanceof Catapulta) {
				if(((Catapulta) cas).getIdentificador()==1) {
					if(f == null)
						this.casillas[i].setIcon(new ImageIcon("Recursos\\CatapultaAzul.png"));
					else {
						
					}
				}else {
					if(f==null) {
						this.casillas[i].setIcon(new ImageIcon("Recursos\\CatapultaRoja.png"));
					}else {
						
					}
				}
			}else if(cas instanceof Colina) {
				this.casillas[i].setIcon(new ImageIcon("Recursos\\Colina.png"));
			}else if(cas instanceof Curación) {
				this.casillas[i].setIcon(new ImageIcon("Recursos\\Curarse.png"));
			}else if(cas instanceof Copa) {
				if(((Copa) cas).getFacción().equals(Facción.Facción1)) {
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
				}else {
					this.casillas[i].setIcon(new ImageIcon("Recursos\\CoronaBC.png"));
				}
				
			}else {
				if(f instanceof Arquero) {
					if(f.getFacción().equals(Facción.Facción1))
						this.casillas[i].setIcon(new ImageIcon("Recursos\\ArqueroAzulBC.png"));
					else{
						this.casillas[i].setIcon(new ImageIcon("Recursos\\ArqueroRojoBC.png"));
					}
				}else if(f instanceof Lancero) {
					if(f.getFacción().equals(Facción.Facción1))
						this.casillas[i].setIcon(new ImageIcon("Recursos\\LanceroAzulBC.png"));
					else{
						this.casillas[i].setIcon(new ImageIcon("Recursos\\LanceroRojoBC.png"));
					}
				}else if (f instanceof Guerrero) {
					if(f.getFacción().equals(Facción.Facción1))
						this.casillas[i].setIcon(new ImageIcon("Recursos\\GuerreroAzulBC.png"));
					else{
						this.casillas[i].setIcon(new ImageIcon("Recursos\\GuerreroRojoBC.png"));
					}
				}else if(f instanceof Bárbaro) {
					if(f.getFacción().equals(Facción.Facción1))
						this.casillas[i].setIcon(new ImageIcon("Recursos\\BarbaroAzulBC.png"));
					else{
						this.casillas[i].setIcon(new ImageIcon("Recursos\\BarbaroRojoBC.png"));
					}
				}else if(f instanceof Caballero) {
					if(f.getFacción().equals(Facción.Facción1))
						this.casillas[i].setIcon(new ImageIcon("Recursos\\CaballeroAzulBC.png"));
					else{
						this.casillas[i].setIcon(new ImageIcon("Recursos\\CaballeroRojoBC.png"));
					}
				}
			}
		}
		
		
		
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

				ClienteGUI frame = new ClienteGUI(false,tablero);
				setVisible(false);
				frame.setVisible(true);
			}
		});
		btnMenu.setBackground(new Color(240, 230, 140));
		btnMenu.setIcon(new ImageIcon("Recursos\\Menu.png"));
		btnMenu.setBorder(null);
		btnMenu.setBounds(42, 615, 179, 47);
		contentPane.add(btnMenu);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setBounds(374, 633, 93, 29);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("New label");
		lblNewLabel_2_1.setBounds(605, 633, 93, 29);
		contentPane.add(lblNewLabel_2_1);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(751, 206, 9, 390);
		contentPane.add(separator);
		
		lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(42, 170, 191, 14);
		contentPane.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(249, 170, 196, 14);
		contentPane.add(lblNewLabel_4);
	}
	
	public void setNombre(String nombre1, String nombre2,boolean azul) {
		if(azul) {
			lblNewLabel_3.setText("Tú: "+nombre1+" (Azul)");
			lblNewLabel_4.setText("Oponente: "+nombre2+" (Rojo)");
		}else {
			lblNewLabel_3.setText("Tú: "+nombre1+" (Rojo)");
			lblNewLabel_4.setText("Oponente: "+nombre2+" (Azul)");
		}
	}

	public void ratonInCoronaAzul(int i) {
		this.casillas[i].setIcon(new ImageIcon("Recursos\\CoronaAzul.png"));
		this.casillas[i].setBackground(Color.white);
	}
	
	public void ratonOutCoronaAzul(int i) {
		this.casillas[i].setIcon(new ImageIcon("Recursos\\CoronaBC.png"));
		this.casillas[i].setBackground(new Color(204, 204, 255));
	}
	
}
