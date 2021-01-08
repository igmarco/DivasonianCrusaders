package Presentación;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Nombre extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblNewLabel;
	private JButton Aceptar;


	/**
	 * Create the frame.
	 */
	public Nombre(final ClienteGUI main, final TableroGrafico tablero) {
		try {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 450, 300);
			contentPane = new JPanel();
			contentPane.setBackground(new Color(240, 230, 140));
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			JLabel lblNewLabel_1 = new JLabel("Introduzca su nombre:");	
			lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 19));
			lblNewLabel_1.setBounds(27, 108, 197, 14);
			contentPane.add(lblNewLabel_1);
			
			textField = new JTextField();
			textField.setBounds(240, 108, 111, 20);
			contentPane.add(textField);
			textField.setColumns(10);
			
			Aceptar = new JButton("Conectar");
			Aceptar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					try {
						Socket s = new Socket("localhost",58000);
						DataInputStream in = new DataInputStream( s.getInputStream());
						DataOutputStream out = new DataOutputStream(s.getOutputStream());
						out.writeBytes("Conexion establecida\r\n");
						out.flush();
						String linea = in.readLine();
						System.out.println("Llegué sii...");
						if(linea.compareTo("OK1")==0) {
							linea = in.readLine();
							lblNewLabel.setText("¡Oponente encontrado!");
						}else {
							lblNewLabel.setText("¡Oponente encontrado!");
						}
						Aceptar.setEnabled(false);
						out.writeBytes(textField.getText()+"\r\n");
						out.flush();
						String name = in.readLine();
						tablero.setNombre(textField.getText(), name);
						tablero.setVisible(true);
						setVisible(false);
					}catch(IOException ex) {
						lblNewLabel.setText("Error al conectar");
					}
				}
			});
			Aceptar.setBounds(240, 154, 89, 23);
			contentPane.add(Aceptar);
			lblNewLabel = new JLabel("Estableciendo Conexion...");
			lblNewLabel.setBounds(122, 35, 197, 14);
			lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 19));
			contentPane.add(lblNewLabel);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	
}
