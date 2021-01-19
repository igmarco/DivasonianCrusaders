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

public class InfoFichas extends JFrame {

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
	public InfoFichas() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(InfoFichas.class.getResource("/Imagenes/iconoRefachero.png")));
		setTitle("Informaci\u00F3n sobre las fichas");
		setBounds(100, 100, 729, 702);
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
		txtpnLaAplicacinPermite.setText("El arquero es una ficha d\u00E9bil, que no destaca ni por su potencia en el combate cuerpo a curerpo ni por su resistencia. De hecho, es con total seguridad la ficha m\u00E1s susceptible a disparos de catapultas y a cargas de caballer\u00EDa, por lo que trata de protegerla cuidadosamente con fichas con mayor resistencia.");
		txtpnLaAplicacinPermite.setBounds(20, 41, 676, 53);
		contentPane.add(txtpnLaAplicacinPermite);
		
		JLabel lblNewLabel = new JLabel("Arquero:");
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 17));
		lblNewLabel.setBounds(10, 11, 330, 24);
		contentPane.add(lblNewLabel);
		
		JTextPane txtpnACambioEl = new JTextPane();
		txtpnACambioEl.setText("A cambio, el arquero es la \u00FAnica ficha de Divasonian Crusaders con un efecto pasivo sobre el juego. Al finalizar cada turno, disparar\u00E1 autom\u00E1ticamente a todas las fichas del rival que tenga a rango. Esto es: Dos casillas hacia delante y una hacia detr\u00E1s y los lados.");
		txtpnACambioEl.setForeground(Color.BLACK);
		txtpnACambioEl.setEditable(false);
		txtpnACambioEl.setBackground(Color.LIGHT_GRAY);
		txtpnACambioEl.setBounds(20, 105, 676, 40);
		contentPane.add(txtpnACambioEl);
		
		JLabel lblBrbaro = new JLabel("B\u00E1rbaro");
		lblBrbaro.setFont(new Font("Arial Black", Font.BOLD, 17));
		lblBrbaro.setBounds(10, 156, 330, 24);
		contentPane.add(lblBrbaro);
	}
}
