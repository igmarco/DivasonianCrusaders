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

public class InfoCasillas extends JFrame {

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
	public InfoCasillas() {
//		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("Recursos\\iconoRefachero2.png"));
		setResizable(false);
//		setIconImage(Toolkit.getDefaultToolkit().getImage(InfoCasillas.class.getResource("/Imagenes/iconoRefachero.png")));
		setTitle("Informaci\u00F3n sobre las fichas");
		setBounds(100, 100, 729, 510);
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
		txtpnLaAplicacinPermite.setText("La catapulta es una casilla que solo puede ser utilizada si una de tus fichas est\u00E1 situada sobre ella. De este modo, podr\u00E1 acceder a la funcionalidad 'DISPARAR', donde, seleccionando una casilla que est\u00E9 en su rango de disparo, decidir\u00E1s d\u00F3nde caer\u00E1 el proximo proyectil. Cada catapulta solo puede ser usada una vez por turno y, una vez disparada, necesitar\u00E1 recargarse.");
		txtpnLaAplicacinPermite.setBounds(20, 41, 676, 53);
		contentPane.add(txtpnLaAplicacinPermite);
		
		JLabel lblNewLabel = new JLabel("Catapulta:");
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 17));
		lblNewLabel.setBounds(10, 11, 330, 24);
		contentPane.add(lblNewLabel);
		
		JTextPane txtpnACambioEl = new JTextPane();
		txtpnACambioEl.setText("Hay dos catapultas en el mapa, cada una dispuesta delante del ej\u00E9rcito de cada facci\u00F3n. Aun as\u00ED, ambas pueden ser utilizadas por cualquier jugador indistintamente.");
		txtpnACambioEl.setForeground(Color.BLACK);
		txtpnACambioEl.setEditable(false);
		txtpnACambioEl.setBackground(Color.LIGHT_GRAY);
		txtpnACambioEl.setBounds(20, 105, 676, 40);
		contentPane.add(txtpnACambioEl);
		
		JLabel lblBrbaro = new JLabel("Curaci\u00F3n:");
		lblBrbaro.setFont(new Font("Arial Black", Font.BOLD, 17));
		lblBrbaro.setBounds(10, 156, 330, 24);
		contentPane.add(lblBrbaro);
		
		JTextPane txtpnEstaCasillaEs = new JTextPane();
		txtpnEstaCasillaEs.setText("Esta casilla es la mejor aliada de tus fichas heridas y moribundas, en ella o en las casillas aleda\u00F1as a la misma, tus ejercitos podr\u00E1n reponerse y recuperarse para volver fuertes. Ya sabes, sin dejarse flush ni la concurrencia en su servidor.");
		txtpnEstaCasillaEs.setForeground(Color.BLACK);
		txtpnEstaCasillaEs.setEditable(false);
		txtpnEstaCasillaEs.setBackground(Color.LIGHT_GRAY);
		txtpnEstaCasillaEs.setBounds(20, 191, 676, 40);
		contentPane.add(txtpnEstaCasillaEs);
		
		JLabel lblColina = new JLabel("Colina:");
		lblColina.setFont(new Font("Arial Black", Font.BOLD, 17));
		lblColina.setBounds(10, 242, 330, 24);
		contentPane.add(lblColina);
		
		JTextPane txtpnSiUnaDe = new JTextPane();
		txtpnSiUnaDe.setText("Si una de tus fichas alcanza la cima de una colina se ver\u00E1 reforzada, de modo que podr\u00E1 ejecutar una mejor defensa con un mejor ataque. Si tus fichas reciben una carga estando en la colina, contar\u00E1n con una mayor potencia ofensiva.");
		txtpnSiUnaDe.setForeground(Color.BLACK);
		txtpnSiUnaDe.setEditable(false);
		txtpnSiUnaDe.setBackground(Color.LIGHT_GRAY);
		txtpnSiUnaDe.setBounds(20, 273, 676, 40);
		contentPane.add(txtpnSiUnaDe);
		
		JLabel lblCorona = new JLabel("Corona:");
		lblCorona.setFont(new Font("Arial Black", Font.BOLD, 17));
		lblCorona.setBounds(10, 329, 330, 24);
		contentPane.add(lblCorona);
		
		JTextPane txtpnLaCoronaEs = new JTextPane();
		txtpnLaCoronaEs.setText("Similar al rey en el ajedrez, solo que en su versi\u00F3n inm\u00F3vil. La corona representa tu aldea, asentamiento o clase de sistemas distribuidos. Si el jugador enemigo consigue arrasarla por completo, habr\u00E1s perdido.");
		//txtpnLaCoronaEs.setText("La corona es como tu TFG lo debes defender a toda costa, si cae, pierdes, es sencillo \u00BFno?. Pues no lo ser\u00E1 tanto teniendo solo 50 puntos de vida, que no la tumben esos Rojos.");
		txtpnLaCoronaEs.setForeground(Color.BLACK);
		txtpnLaCoronaEs.setEditable(false);
		txtpnLaCoronaEs.setBackground(Color.LIGHT_GRAY);
		txtpnLaCoronaEs.setBounds(20, 364, 676, 40);
		contentPane.add(txtpnLaCoronaEs);
		
		JTextPane txtpnAunqueEsPoco = new JTextPane();
		txtpnAunqueEsPoco.setText("Aunque es poco resistente, para da\u00F1arla es necesario tener una ficha de tu facci\u00F3n sobre la del enemigo (sin que est\u00E9 trabada en combate) al final del turno, por lo que puede ser complicado arrasarla si el rival todav\u00EDa tiene varias fichas sobre el tablero.");
		txtpnAunqueEsPoco.setForeground(Color.BLACK);
		txtpnAunqueEsPoco.setEditable(false);
		txtpnAunqueEsPoco.setBackground(Color.LIGHT_GRAY);
		txtpnAunqueEsPoco.setBounds(20, 415, 676, 40);
		contentPane.add(txtpnAunqueEsPoco);
	}
}
