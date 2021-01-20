package Presentación;

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
		setBounds(100, 100, 729, 714);
		contentPane = new JPanel();
		contentPane.setForeground(Color.BLACK);
		contentPane.setBackground(new Color(240, 230, 140));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextPane txtpnLaAplicacinPermite = new JTextPane();
		txtpnLaAplicacinPermite.setEditable(false);
		txtpnLaAplicacinPermite.setBackground(Color.LIGHT_GRAY);
		txtpnLaAplicacinPermite.setForeground(Color.BLACK);
		txtpnLaAplicacinPermite.setText("Divasonian Crusaders es un juego de mesa sim\u00E9trico que trata de armonizar juegos de tablero cl\u00E1sicos, como el ajedrez, con videojuegos actuales de estrategia en tiempo real.");
		txtpnLaAplicacinPermite.setBounds(20, 41, 676, 39);
		contentPane.add(txtpnLaAplicacinPermite);
		
		JLabel lblNewLabel = new JLabel("Instrucciones del juego:");
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 17));
		lblNewLabel.setBounds(10, 11, 330, 24);
		contentPane.add(lblNewLabel);
		
		JTextPane txtpnElEsqueletoDel = new JTextPane();
		txtpnElEsqueletoDel.setText("El esqueleto del juego lo componen un tablero con 45 casillas, algunas de ellas con propiedades especiales;  junto con un total de 10 fichas, 5 por cada jugador. Estas fichas, adem\u00E1s, poseen cualidades y habilidades especiales que ser\u00E1 necesario explotar con cuidado.");
		txtpnElEsqueletoDel.setForeground(Color.BLACK);
		txtpnElEsqueletoDel.setEditable(false);
		txtpnElEsqueletoDel.setBackground(Color.LIGHT_GRAY);
		txtpnElEsqueletoDel.setBounds(20, 91, 676, 39);
		contentPane.add(txtpnElEsqueletoDel);
		
		JTextPane txtpnLasPartidasDe = new JTextPane();
		txtpnLasPartidasDe.setText("Las partidas de Divasonian Crusaders se suceden por turnos, compuesto cada uno por una serie de 6 maniobras. Al comienzo de este, cada jugador deber\u00E1 introducir en la aplicaci\u00F3n lo que llamamos una instrucci\u00F3n, formada por 6 operaciones. Una vez introducidas, la partida pondr\u00E1 en com\u00FAn las operaciones de los jugadoras, ejecutando secuencialmente cada par y produciendo las maniobras resultantes. Tras procesar las 6 operaciones, la partida resolver\u00E1 el turno: Las fichas enfrentadas combatir\u00E1n.");
		txtpnLasPartidasDe.setForeground(Color.BLACK);
		txtpnLasPartidasDe.setEditable(false);
		txtpnLasPartidasDe.setBackground(Color.LIGHT_GRAY);
		txtpnLasPartidasDe.setBounds(20, 141, 676, 67);
		contentPane.add(txtpnLasPartidasDe);
		
		JLabel lblOperaciones = new JLabel("Operaciones:");
		lblOperaciones.setFont(new Font("Arial Black", Font.BOLD, 17));
		lblOperaciones.setBounds(10, 219, 330, 24);
		contentPane.add(lblOperaciones);
		
		JTextPane txtpnLlasOperacionesQue = new JTextPane();
		txtpnLlasOperacionesQue.setText("Las operaciones que deseemos que se lleven a cabo en el turno (y que suceder\u00E1n secuencialmente al mismo tiempo que las del rival) son 6, y pueden ser de 3 tipos diferentes:");
		txtpnLlasOperacionesQue.setForeground(Color.BLACK);
		txtpnLlasOperacionesQue.setEditable(false);
		txtpnLlasOperacionesQue.setBackground(Color.LIGHT_GRAY);
		txtpnLlasOperacionesQue.setBounds(20, 254, 676, 39);
		contentPane.add(txtpnLlasOperacionesQue);
		
		JTextPane txtpnMovimientoPodemos = new JTextPane();
		txtpnMovimientoPodemos.setText("- Movimiento: Podemos indicar a una ficha que se desplace a una casilla adyacente. En caso de que en esa maniobra se trabe en combate con una ficha del rival, ejecutar\u00E1 una carga, y no se mover\u00E1n en el resto del turno (aunque en turnos posteriores podr\u00E1 huir del combate, sufriendo el da\u00F1o por persecuci\u00F3n del rival), ni esa ficha ni la del rival. Cada ficha puede moverse en un m\u00E1ximo de dos operaciones, salvo el Caballero, que puede mover tres.");
		txtpnMovimientoPodemos.setForeground(Color.BLACK);
		txtpnMovimientoPodemos.setEditable(false);
		txtpnMovimientoPodemos.setBackground(Color.LIGHT_GRAY);
		txtpnMovimientoPodemos.setBounds(20, 304, 676, 67);
		contentPane.add(txtpnMovimientoPodemos);
		
		JTextPane txtpnDisparoEn = new JTextPane();
		txtpnDisparoEn.setText("- Disparo: En caso de que una de tus fichas est\u00E9 situada (sin estar trabada en combate) sobre una casilla de Catapulta, podr\u00E1 emplearla para disparar un proyectil a una casilla dentro del rango. Esta operaci\u00F3n consumir\u00E1 (como cualquier otra) toda la maniobra, y todas las fichas que se encuentren en la casilla indicada, sufrir\u00E1n el da\u00F1o que corresponda al proyectil.");
		txtpnDisparoEn.setForeground(Color.BLACK);
		txtpnDisparoEn.setEditable(false);
		txtpnDisparoEn.setBackground(Color.LIGHT_GRAY);
		txtpnDisparoEn.setBounds(20, 382, 676, 54);
		contentPane.add(txtpnDisparoEn);
		
		JTextPane txtpnEsperaEn = new JTextPane();
		txtpnEsperaEn.setText("- Espera: En esa maniobra, ninguna ficha bajo tu control llevar\u00E1 a cabo ninguna operaci\u00F3n.");
		txtpnEsperaEn.setForeground(Color.BLACK);
		txtpnEsperaEn.setEditable(false);
		txtpnEsperaEn.setBackground(Color.LIGHT_GRAY);
		txtpnEsperaEn.setBounds(20, 447, 676, 24);
		contentPane.add(txtpnEsperaEn);
		
		JLabel lblHachaDivasnica = new JLabel("Hacha divas\u00F3nica:");
		lblHachaDivasnica.setFont(new Font("Arial Black", Font.BOLD, 17));
		lblHachaDivasnica.setBounds(10, 482, 330, 24);
		contentPane.add(lblHachaDivasnica);
		
		JTextPane txtpnLaVerdaderaProtagonista = new JTextPane();
		txtpnLaVerdaderaProtagonista.setText("La verdadera protagonista de Divasonian Crusaders: El hacha divas\u00F3nica. Si al final del turno una ficha bajo tu control est\u00E1 situada sobre la casilla en la que se encuentra el hacha divas\u00F3nica, la tomar\u00E1. El portador de esta arma obtendr\u00E1 un gran poder, y ver\u00E1 su potencia de ataque aumentada considerablemente, pero la corrupci\u00F3n se apoderar\u00E1 de ella, y, por el consiguiente, en la resoluci\u00F3n de cada turno perder\u00E1 una cantidad variable de puntos de vida. Una ficha que porte el hacha divas\u00F3nica no podr\u00E1 soltarla hasta su muerte, as\u00ED que valora con cuidado si deseas portar tal arma de destrucci\u00F3n.");
		txtpnLaVerdaderaProtagonista.setForeground(Color.BLACK);
		txtpnLaVerdaderaProtagonista.setEditable(false);
		txtpnLaVerdaderaProtagonista.setBackground(Color.LIGHT_GRAY);
		txtpnLaVerdaderaProtagonista.setBounds(20, 517, 676, 85);
		contentPane.add(txtpnLaVerdaderaProtagonista);
		
		JButton btnNewButton = new JButton("Ver m\u00E1s informaci\u00F3n sobre las casillas");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// Al pulsar esto se muestra la descripción básica de la aplicación y su uso.
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
		btnNewButton.setBounds(89, 627, 230, 39);
		contentPane.add(btnNewButton);
		
		JButton btnVerMsInformacin = new JButton("Ver m\u00E1s informaci\u00F3n sobre las fichas");
		btnVerMsInformacin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// Al pulsar esto se muestra la descripción básica de la aplicación y su uso.
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
		btnVerMsInformacin.setBounds(373, 627, 230, 39);
		contentPane.add(btnVerMsInformacin);
	}
}
