package Presentación;

import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Font;

public class TableroGrafico extends JFrame {

	private JPanel contentPane;
	private JButton btnMenu;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;

	/**
	 * Create the frame.
	 */
	public TableroGrafico(final ClienteGUI main) {
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
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(763, 21, 274, 127);
		contentPane.add(lblNewLabel_1);
		
		JPanel panel = new JPanel();
		panel.setBounds(32, 206, 702, 390);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(5, 9, 0, 0));
		
		final JButton cas0 = new JButton("");
		cas0.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				cas0.setBackground(Color.white);
				cas0.setIcon(new ImageIcon("Recursos\\ArqueroAzulC.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				cas0.setBackground(new Color(245, 245, 220));
				cas0.setIcon(new ImageIcon("Recursos\\ArqueroAzulBN.png"));
			}
		});
		cas0.setBackground(new Color(245, 245, 220));
		cas0.setIcon(new ImageIcon("Recursos\\ArqueroAzulBN.png"));
		cas0.setBorder(null);
		panel.add(cas0);

		
		JButton cas1 = new JButton("");
		cas1.setBackground(new Color(245, 245, 220));
		panel.add(cas1);
		
		JButton cas2 = new JButton("");
		cas2.setIcon(new ImageIcon("Recursos\\Curarse.png"));
		cas2.setBackground(new Color(245, 245, 220));
		panel.add(cas2);
		
		JButton cas3 = new JButton("");
		cas3.setBackground(new Color(245, 245, 220));
		panel.add(cas3);
		
		JButton cas4 = new JButton("");
		cas4.setBackground(new Color(245, 245, 220));
		panel.add(cas4);
		
		JButton cas5 = new JButton("");
		cas5.setIcon(new ImageIcon("Recursos\\Colina.png"));
		cas5.setBackground(new Color(245, 245, 220));
		panel.add(cas5);
		
		JButton cas6 = new JButton("");
		cas6.setBackground(new Color(245, 245, 220));
		panel.add(cas6);
		
		JButton cas7 = new JButton("");
		cas7.setBackground(new Color(245, 245, 220));
		panel.add(cas7);
		
		final JButton cas8 = new JButton("");
		cas8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				cas8.setIcon(new ImageIcon("Recursos\\CaballeroRojoC.png"));
				cas8.setBackground(Color.white);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				cas8.setIcon(new ImageIcon("Recursos\\CaballeroRojoBC.png"));
				cas8.setBackground(new Color(245, 245, 220));
			}
		});
		cas8.setIcon(new ImageIcon("Recursos\\CaballeroRojoBC.png"));
		cas8.setBackground(new Color(245, 245, 220));
		cas8.setBorder(null);
		panel.add(cas8);
		
		final JButton cas17 = new JButton("");
		cas17.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				cas17.setIcon(new ImageIcon("Recursos\\LanzeroAzulC.png"));
				cas17.setBackground(Color.white);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				cas17.setBackground(new Color(245, 245, 220));
				cas17.setIcon(new ImageIcon("Recursos\\LanzeroAzulBN.png"));
			}
		});
		cas17.setBackground(new Color(245, 245, 220));
		cas17.setIcon(new ImageIcon("Recursos\\LanzeroAzulBN.png"));
		cas17.setBorder(null);
		panel.add(cas17);
		
		JButton cas9 = new JButton("");
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
		
		final JButton cas16 = new JButton("");
		cas16.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				cas16.setIcon(new ImageIcon("Recursos\\BarbaroRojoC.png"));
				cas16.setBackground(Color.white);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				cas16.setIcon(new ImageIcon("Recursos\\BarbaroRojoBC.png"));
				cas16.setBackground(new Color(245, 245, 220));
			}
		});
		cas16.setIcon(new ImageIcon("Recursos\\BarbaroRojoBC.png"));
		cas16.setBackground(new Color(245, 245, 220));
		cas16.setBorder(null);
		panel.add(cas16);
		
		final JButton cas18 = new JButton("");
		cas18.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				cas18.setIcon(new ImageIcon("Recursos\\CoronaAzul.png"));
				cas18.setBackground(Color.white);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				cas18.setIcon(new ImageIcon("Recursos\\CoronaBC.png"));
				cas18.setBackground(new Color(204, 204, 255));
			}
		});
		cas18.setIcon(new ImageIcon("Recursos\\CoronaBC.png"));
		cas18.setBorder(null);
		cas18.setBackground(new Color(204, 204, 255));
		panel.add(cas18);
		
		final JButton cas19 = new JButton("");
		cas19.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				cas19.setIcon(new ImageIcon("Recursos\\GuerreroAzulC.png"));
				cas19.setBackground(Color.white);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				cas19.setIcon(new ImageIcon("Recursos\\GuerreroAzulBC.png"));
				cas19.setBackground(new Color(245, 245, 220));
			}
		});
		cas19.setIcon(new ImageIcon("Recursos\\GuerreroAzulBC.png"));
		cas19.setBackground(new Color(245, 245, 220));
		cas19.setBorder(null);
		panel.add(cas19);
		
		JButton cas20 = new JButton("");
		cas20.setIcon(new ImageIcon("Recursos\\CatapultaAzul.png"));
		cas20.setBackground(new Color(245, 245, 220));
		panel.add(cas20);
		
		JButton cas21 = new JButton("");
		cas21.setBackground(new Color(245, 245, 220));
		panel.add(cas21);
		
		final JButton cas22 = new JButton("");
		cas22.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				cas22.setIcon(new ImageIcon("Recursos\\HachaDivasonicaS.png"));
				cas22.setBackground(Color.white);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				cas22.setIcon(new ImageIcon("Recursos\\HachaDivasonica.png"));
				cas22.setBackground(new Color(245, 245, 220));
			}
		});
		cas22.setIcon(new ImageIcon("Recursos\\HachaDivasonica.png"));
		cas22.setBackground(new Color(245, 245, 220));
		cas22.setBorder(null);
		panel.add(cas22);
		
		JButton cas23 = new JButton("");
		cas23.setBackground(new Color(245, 245, 220));
		cas23.setBorder(null);
		panel.add(cas23);
		
		JButton cas24 = new JButton("");
		cas24.setIcon(new ImageIcon("Recursos\\CatapultaRoja.png"));
		cas24.setBackground(new Color(245, 245, 220));
		panel.add(cas24);
		
		final JButton cas25 = new JButton("");
		cas25.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				cas25.setIcon(new ImageIcon("Recursos\\GuerreroRojoC.png"));
				cas25.setBackground(Color.white);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				cas25.setIcon(new ImageIcon("Recursos\\GuerreroRojoBC.png"));
				cas25.setBackground(new Color(245, 245, 220));
			}
		});
		cas25.setIcon(new ImageIcon("Recursos\\GuerreroRojoBC.png"));
		cas25.setBackground(new Color(245, 245, 220));
		cas25.setBorder(null);
		panel.add(cas25);
		
		final JButton cas26 = new JButton("");
		cas26.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				cas26.setIcon(new ImageIcon("Recursos\\CoronaRojo.png"));
				cas26.setBackground(Color.white);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				cas26.setIcon(new ImageIcon("Recursos\\CoronaBC.png"));
				cas26.setBackground(new Color(255, 204, 204));
			}
		});
		cas26.setIcon(new ImageIcon("Recursos\\CoronaBC.png"));
		cas26.setBackground(new Color(255, 204, 204));
		cas26.setBorder(null);
		panel.add(cas26);
		
		final JButton cas27 = new JButton("");
		cas27.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				cas27.setIcon(new ImageIcon("Recursos\\BarbaroAzulC.png"));
				cas27.setBackground(Color.white);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				cas27.setIcon(new ImageIcon("Recursos\\BarbaroAzulBC.png"));
				cas27.setBackground(new Color(245, 245, 220));
			}
		});
		cas27.setIcon(new ImageIcon("Recursos\\BarbaroAzulBC.png"));
		cas27.setBackground(new Color(245, 245, 220));
		cas27.setBorder(null);
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
		cas35.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				cas35.setBackground(Color.white);
				cas35.setIcon(new ImageIcon("Recursos\\LanzeroRojoC.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				cas35.setBackground(new Color(245, 245, 220));
				cas35.setIcon(new ImageIcon("Recursos\\LanzeroRojoBN.png"));
			}
		});
		cas35.setBackground(new Color(245, 245, 220));
		cas35.setIcon(new ImageIcon("Recursos\\LanzeroRojoBN.png"));
		cas35.setBorder(null);
		panel.add(cas35);
		
		final JButton cas36 = new JButton("");
		cas36.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				cas36.setIcon(new ImageIcon("Recursos\\CaballeroAzulC.png"));
				cas36.setBackground(Color.white);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				cas36.setIcon(new ImageIcon("Recursos\\CaballeroAzulBC.png"));
				cas36.setBackground(new Color(245, 245, 220));
			}
		});
		cas36.setIcon(new ImageIcon("Recursos\\CaballeroAzulBC.png"));
		cas36.setBackground(new Color(245, 245, 220));
		cas36.setBorder(null);
		panel.add(cas36);
		
		JButton cas37 = new JButton("");
		cas37.setBackground(new Color(245, 245, 220));
		panel.add(cas37);
		
		JButton cas38 = new JButton("");
		cas38.setBackground(new Color(245, 245, 220));
		panel.add(cas38);
		
		JButton cas39 = new JButton("");
		cas39.setIcon(new ImageIcon("Recursos\\Colina.png"));
		cas39.setBackground(new Color(245, 245, 220));
		panel.add(cas39);
		
		JButton cas40 = new JButton("");
		cas40.setBackground(new Color(245, 245, 220));
		panel.add(cas40);
		
		JButton cas41 = new JButton("");
		cas41.setBackground(new Color(245, 245, 220));
		panel.add(cas41);
		
		JButton cas42 = new JButton("");
		cas42.setIcon(new ImageIcon("Recursos\\Curarse.png"));
		cas42.setBackground(new Color(245, 245, 220));
		panel.add(cas42);
		
		JButton cas43 = new JButton("");
		cas43.setBackground(new Color(245, 245, 220));
		panel.add(cas43);
		
		final JButton cas44 = new JButton("");
		cas44.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				cas44.setBackground(Color.white);
				cas44.setIcon(new ImageIcon("Recursos\\ArqueroRojoC.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				cas44.setBackground(new Color(245, 245, 220));
				cas44.setIcon(new ImageIcon("Recursos\\ArqueroRojoBN.png"));
			}
		});
		cas44.setIcon(new ImageIcon("Recursos\\ArqueroRojoBN.png"));
		cas44.setBackground(new Color(245, 245, 220));
		cas44.setBorder(null);
		panel.add(cas44);
		
		
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
				main.setVisible(true);
				setVisible(false);
			}
		});
		btnMenu.setBackground(new Color(240, 230, 140));
		btnMenu.setIcon(new ImageIcon("Recursos\\Menu.png"));
		btnMenu.setBorder(null);
		btnMenu.setBounds(42, 615, 179, 47);
		contentPane.add(btnMenu);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setBounds(460, 633, 93, 29);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("New label");
		lblNewLabel_2_1.setBounds(617, 633, 93, 29);
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
}
