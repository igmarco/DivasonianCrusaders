package Presentación;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

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
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Ayuda.class.getResource("/Imagenes/iconoRefachero.png")));
		setTitle("Ayuda");
		setBounds(100, 100, 382, 358);
		contentPane = new JPanel();
		contentPane.setForeground(Color.BLACK);
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextPane txtpnLaAplicacinPermite = new JTextPane();
		txtpnLaAplicacinPermite.setEditable(false);
		txtpnLaAplicacinPermite.setBackground(Color.LIGHT_GRAY);
		txtpnLaAplicacinPermite.setForeground(Color.BLACK);
		txtpnLaAplicacinPermite.setText("La aplicaci\u00F3n permite calcular de forma concurrente ra\u00EDces reales de polinomios de coeficientes racionales. ");
		txtpnLaAplicacinPermite.setBounds(20, 41, 320, 48);
		contentPane.add(txtpnLaAplicacinPermite);
		
		JLabel lblNewLabel = new JLabel("Descripci\u00F3n de la aplicaci\u00F3n:");
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 17));
		lblNewLabel.setBounds(10, 11, 330, 24);
		contentPane.add(lblNewLabel);
		
		JTextPane txtpnParaElloEl = new JTextPane();
		txtpnParaElloEl.setEditable(false);
		txtpnParaElloEl.setText("Para ello, el usuario deber\u00E1 introducir el polinomio con la estructura correcta y pulsar el bot\u00F3n \"Calcular Ra\u00EDces\", y las ra\u00EDces del polinomio aparecer\u00E1n eventualmente en el cuadro de texto inferior. ");
		txtpnParaElloEl.setForeground(Color.BLACK);
		txtpnParaElloEl.setBackground(Color.LIGHT_GRAY);
		txtpnParaElloEl.setBounds(20, 86, 320, 67);
		contentPane.add(txtpnParaElloEl);
		
		JTextPane txtpnParaMsInformacin = new JTextPane();
		txtpnParaMsInformacin.setEditable(false);
		txtpnParaMsInformacin.setText("Para m\u00E1s informaci\u00F3n acerca del polinomio y del m\u00E9todo para hallarlas, el usuario puede hacer click en el bot\u00F3n \"Info.\"");
		txtpnParaMsInformacin.setForeground(Color.BLACK);
		txtpnParaMsInformacin.setBackground(Color.LIGHT_GRAY);
		txtpnParaMsInformacin.setBounds(20, 164, 320, 48);
		contentPane.add(txtpnParaMsInformacin);
		
		JTextPane txtpnEnElCaso = new JTextPane();
		txtpnEnElCaso.setEditable(false);
		txtpnEnElCaso.setText("En caso de obtener como resultado una pareja de ra\u00EDces id\u00E9nticas, se debe a la proximidad demasiado elevada de las mismas para el m\u00E9todo de Newton. Rogamos que nos disculpen.");
		txtpnEnElCaso.setForeground(Color.BLACK);
		txtpnEnElCaso.setBackground(Color.LIGHT_GRAY);
		txtpnEnElCaso.setBounds(20, 223, 320, 67);
		contentPane.add(txtpnEnElCaso);
	}
}
