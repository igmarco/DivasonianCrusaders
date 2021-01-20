package Presentaci�n;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Ayuda extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Ayuda frame = new Ayuda();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public Ayuda() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("Recursos\\iconoRefachero2.png"));
		setResizable(false);
//		setIconImage(Toolkit.getDefaultToolkit().getImage(Ayuda.class.getResource("/Recursos/iconoRefachero.png")));
		setTitle("Ayuda");
		setBounds(100, 100, 1087, 825);
		contentPane = new JPanel();
		contentPane.setForeground(Color.BLACK);
		contentPane.setBackground(new Color(240, 230, 140));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextPane txtpnLaAplicacinPermite = new JTextPane();
		txtpnLaAplicacinPermite.setFont(new Font("Consolas", Font.PLAIN, 14));
		txtpnLaAplicacinPermite.setEditable(false);
		txtpnLaAplicacinPermite.setBackground(new Color(245, 245, 220));
		txtpnLaAplicacinPermite.setForeground(Color.BLACK);
		txtpnLaAplicacinPermite.setText("Divasonian Crusaders es un juego de mesa sim\u00E9trico que trata de armonizar juegos de tablero cl\u00E1sicos, como el ajedrez, con videojuegos actuales de estrategia en tiempo real.");
		txtpnLaAplicacinPermite.setBounds(20, 41, 1023, 39);
		contentPane.add(txtpnLaAplicacinPermite);
		
		JLabel lblNewLabel = new JLabel("Instrucciones del juego:");
		lblNewLabel.setFont(new Font("Consolas", Font.BOLD, 23));
		lblNewLabel.setBounds(10, 11, 330, 24);
		contentPane.add(lblNewLabel);
		
		JTextPane txtpnElEsqueletoDel = new JTextPane();
		txtpnElEsqueletoDel.setFont(new Font("Consolas", Font.PLAIN, 14));
		txtpnElEsqueletoDel.setText("El esqueleto del juego lo componen un tablero con 45 casillas, algunas de ellas con propiedades especiales;  junto con un total de 10 fichas, 5 por cada jugador. Estas fichas, adem\u00E1s, poseen cualidades y habilidades especiales que ser\u00E1 necesario explotar con cuidado.");
		txtpnElEsqueletoDel.setForeground(Color.BLACK);
		txtpnElEsqueletoDel.setEditable(false);
		txtpnElEsqueletoDel.setBackground(new Color(245, 245, 220));
		txtpnElEsqueletoDel.setBounds(20, 91, 1023, 54);
		contentPane.add(txtpnElEsqueletoDel);
		
		JTextPane txtpnLasPartidasDe = new JTextPane();
		txtpnLasPartidasDe.setFont(new Font("Consolas", Font.PLAIN, 14));
		txtpnLasPartidasDe.setText("Las partidas de Divasonian Crusaders se suceden por turnos, compuesto cada uno por una serie de 6 maniobras. Al comienzo de este, cada jugador deber\u00E1 introducir en la aplicaci\u00F3n lo que llamamos una instrucci\u00F3n, formada por 6 operaciones. Una vez introducidas, la partida pondr\u00E1 en com\u00FAn las operaciones de los jugadoras, ejecutando secuencialmente cada par y produciendo las maniobras resultantes. Tras procesar las 6 operaciones, la partida resolver\u00E1 el turno: Las fichas enfrentadas combatir\u00E1n.");
		txtpnLasPartidasDe.setForeground(Color.BLACK);
		txtpnLasPartidasDe.setEditable(false);
		txtpnLasPartidasDe.setBackground(new Color(245, 245, 220));
		txtpnLasPartidasDe.setBounds(20, 155, 1023, 86);
		contentPane.add(txtpnLasPartidasDe);
		
		JLabel lblOperaciones = new JLabel("Operaciones:");
		lblOperaciones.setFont(new Font("Consolas", Font.BOLD, 23));
		lblOperaciones.setBounds(10, 252, 330, 24);
		contentPane.add(lblOperaciones);
		
		JTextPane txtpnLlasOperacionesQue = new JTextPane();
		txtpnLlasOperacionesQue.setFont(new Font("Consolas", Font.PLAIN, 14));
		txtpnLlasOperacionesQue.setText("Las operaciones que deseemos que se lleven a cabo en el turno (y que suceder\u00E1n secuencialmente al mismo tiempo que las del rival) son 6, y pueden ser de 3 tipos diferentes:");
		txtpnLlasOperacionesQue.setForeground(Color.BLACK);
		txtpnLlasOperacionesQue.setEditable(false);
		txtpnLlasOperacionesQue.setBackground(new Color(245, 245, 220));
		txtpnLlasOperacionesQue.setBounds(20, 287, 1023, 44);
		contentPane.add(txtpnLlasOperacionesQue);
		
		JTextPane txtpnMovimientoPodemos = new JTextPane();
		txtpnMovimientoPodemos.setFont(new Font("Consolas", Font.PLAIN, 14));
		txtpnMovimientoPodemos.setText("- Movimiento: Podemos indicar a una ficha que se desplace a una casilla adyacente. En caso de que en esa maniobra se trabe en combate con una ficha del rival, ejecutar\u00E1 una carga, y no se mover\u00E1n en el resto del turno (aunque en turnos posteriores podr\u00E1 huir del combate, sufriendo el da\u00F1o por persecuci\u00F3n del rival), ni esa ficha ni la del rival. Cada ficha puede moverse en un m\u00E1ximo de dos operaciones, salvo el Caballero, que puede mover tres.");
		txtpnMovimientoPodemos.setForeground(Color.BLACK);
		txtpnMovimientoPodemos.setEditable(false);
		txtpnMovimientoPodemos.setBackground(new Color(245, 245, 220));
		txtpnMovimientoPodemos.setBounds(20, 342, 1023, 73);
		contentPane.add(txtpnMovimientoPodemos);
		
		JTextPane txtpnDisparoEn = new JTextPane();
		txtpnDisparoEn.setFont(new Font("Consolas", Font.PLAIN, 14));
		txtpnDisparoEn.setText("- Disparo: En caso de que una de tus fichas est\u00E9 situada (sin estar trabada en combate) sobre una casilla de Catapulta, podr\u00E1 emplearla para disparar un proyectil a una casilla dentro del rango. Esta operaci\u00F3n consumir\u00E1 (como cualquier otra) toda la maniobra, y todas las fichas que se encuentren en la casilla indicada, sufrir\u00E1n el da\u00F1o que corresponda al proyectil.");
		txtpnDisparoEn.setForeground(Color.BLACK);
		txtpnDisparoEn.setEditable(false);
		txtpnDisparoEn.setBackground(new Color(245, 245, 220));
		txtpnDisparoEn.setBounds(20, 426, 1023, 73);
		contentPane.add(txtpnDisparoEn);
		
		JTextPane txtpnEsperaEn = new JTextPane();
		txtpnEsperaEn.setFont(new Font("Consolas", Font.PLAIN, 14));
		txtpnEsperaEn.setText("- Espera: En esa maniobra, ninguna ficha bajo tu control llevar\u00E1 a cabo ninguna operaci\u00F3n.");
		txtpnEsperaEn.setForeground(Color.BLACK);
		txtpnEsperaEn.setEditable(false);
		txtpnEsperaEn.setBackground(new Color(245, 245, 220));
		txtpnEsperaEn.setBounds(20, 510, 1023, 27);
		contentPane.add(txtpnEsperaEn);
		
		JLabel lblHachaDivasnica = new JLabel("Hacha divas\u00F3nica:");
		lblHachaDivasnica.setFont(new Font("Consolas", Font.BOLD, 23));
		lblHachaDivasnica.setBounds(10, 548, 330, 24);
		contentPane.add(lblHachaDivasnica);
		
		JTextPane txtpnLaVerdaderaProtagonista = new JTextPane();
		txtpnLaVerdaderaProtagonista.setFont(new Font("Consolas", Font.PLAIN, 14));
		txtpnLaVerdaderaProtagonista.setText("La verdadera protagonista de Divasonian Crusaders: El hacha divas\u00F3nica. Si al final del turno una ficha bajo tu control est\u00E1 situada sobre la casilla en la que se encuentra el hacha divas\u00F3nica, la tomar\u00E1. El portador de esta arma obtendr\u00E1 un gran poder, y ver\u00E1 su potencia de ataque aumentada considerablemente, pero la corrupci\u00F3n se apoderar\u00E1 de ella, y, por el consiguiente, en la resoluci\u00F3n de cada turno perder\u00E1 una cantidad variable de puntos de vida. Una ficha que porte el hacha divas\u00F3nica no podr\u00E1 soltarla hasta su muerte, as\u00ED que valora con cuidado si deseas portar tal arma de destrucci\u00F3n.");
		txtpnLaVerdaderaProtagonista.setForeground(Color.BLACK);
		txtpnLaVerdaderaProtagonista.setEditable(false);
		txtpnLaVerdaderaProtagonista.setBackground(new Color(245, 245, 220));
		txtpnLaVerdaderaProtagonista.setBounds(20, 583, 1023, 114);
		contentPane.add(txtpnLaVerdaderaProtagonista);
		
		final JButton btnNewButton = new JButton("");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewButton.setIcon(new ImageIcon("Recursos\\CasillasS.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton.setIcon(new ImageIcon("Recursos\\Casillas.png"));
			}
		});
		btnNewButton.setBackground(new Color(240, 230, 140));
		btnNewButton.setBorder(null);
		btnNewButton.setIcon(new ImageIcon("Recursos\\Casillas.png"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// Al pulsar esto se muestra la descripci�n b�sica de la aplicaci�n y su uso.
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							InfoCasillas frameInfoCasillas = new InfoCasillas();
							frameInfoCasillas.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				
			}
		});
		btnNewButton.setBounds(334, 723, 175, 47);
		contentPane.add(btnNewButton);
		
		final JButton btnVerMsInformacin = new JButton("");
		btnVerMsInformacin.setBackground(new Color(240, 230, 140));
		btnVerMsInformacin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnVerMsInformacin.setIcon(new ImageIcon("Recursos\\FichasS.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnVerMsInformacin.setIcon(new ImageIcon("Recursos\\Fichas.png"));
			}
		});
		btnVerMsInformacin.setIcon(new ImageIcon("Recursos\\Fichas.png"));
		btnVerMsInformacin.setBorder(null);
		btnVerMsInformacin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// Al pulsar esto se muestra la descripci�n b�sica de la aplicaci�n y su uso.
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							InfoFichas frameInfoFichas = new InfoFichas();
							frameInfoFichas.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				
			}
		});
		btnVerMsInformacin.setBounds(566, 723, 175, 47);
		contentPane.add(btnVerMsInformacin);
	}
}
