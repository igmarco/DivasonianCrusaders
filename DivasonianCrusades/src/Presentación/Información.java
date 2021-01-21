package Presentación;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Información extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Información frame = new Información();
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
	public Información() {
//		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("Recursos\\iconoRefachero2.png")); 
		setResizable(false);
//		setIconImage(Toolkit.getDefaultToolkit().getImage(Información.class.getResource("/Imagenes/iconoRefachero.png")));
		setTitle("Informaci\u00F3n");
		setBounds(100, 100, 382, 278);
		contentPane = new JPanel();
		contentPane.setForeground(Color.BLACK);
		contentPane.setBackground(new Color(240, 230, 140));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("BlackLynx Studio");
		lblNewLabel.setFont(new Font("Consolas", Font.BOLD, 32));
		lblNewLabel.setBounds(23, 21, 343, 34);
		contentPane.add(lblNewLabel);
		
		JLabel lblIgnacioMarcoPrez = new JLabel("Ignacio Marco P\u00E9rez");
		lblIgnacioMarcoPrez.setFont(new Font("Consolas", Font.BOLD, 25));
		lblIgnacioMarcoPrez.setBounds(33, 154, 333, 26);
		contentPane.add(lblIgnacioMarcoPrez);
		
		JLabel lblNewLabel_1_1 = new JLabel("Pablo Ascorbe Fern\u00E1ndez");
		lblNewLabel_1_1.setFont(new Font("Consolas", Font.BOLD, 25));
		lblNewLabel_1_1.setBounds(33, 76, 333, 26);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("paascorb@unirioja.es");
		lblNewLabel_1_1_1.setFont(new Font("Consolas", Font.ITALIC, 21));
		lblNewLabel_1_1_1.setBounds(95, 113, 271, 26);
		contentPane.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("igmarco@unirioja.es");
		lblNewLabel_1_1_2.setFont(new Font("Consolas", Font.ITALIC, 21));
		lblNewLabel_1_1_2.setBounds(95, 191, 271, 26);
		contentPane.add(lblNewLabel_1_1_2);
	}
}
