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
import javax.swing.ImageIcon;

public class Nombre extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblNewLabel;
	private JButton Aceptar;


	/**
	 * Create the frame.
	 */
	public Nombre(final TableroGrafico tablero) {
		setResizable(false);
		try {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 450, 258);
			contentPane = new JPanel();
			contentPane.setBackground(new Color(240, 230, 140));
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			JLabel lblNewLabel_1 = new JLabel("Introduzca su nombre:");	
			lblNewLabel_1.setFont(new Font("Consolas", Font.PLAIN, 20));
			lblNewLabel_1.setBounds(10, 105, 251, 28);
			contentPane.add(lblNewLabel_1);
			
			textField = new JTextField();
			textField.setFont(new Font("Consolas", Font.PLAIN, 20));
			textField.setBounds(258, 101, 166, 28);
			contentPane.add(textField);
			textField.setColumns(10);
			
			Aceptar = new JButton("");
			Aceptar.setBackground(new Color(240, 230, 140));
			Aceptar.setIcon(new ImageIcon("Recursos\\Conectar.png"));
			Aceptar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					try {
						Socket s = new Socket("localhost",58000);
						boolean azul;
						DataInputStream in = new DataInputStream( s.getInputStream());
						DataOutputStream out = new DataOutputStream(s.getOutputStream());
						String linea = in.readLine();
						System.out.println("Llegué sii...");
						if(linea.compareTo("OK1")==0) {
							azul = true;
							lblNewLabel.setText("¡Oponente encontrado!");
							linea = in.readLine();
						}else {
							azul=false;
							lblNewLabel.setText("¡Oponente encontrado!");
						}
						Aceptar.setEnabled(false);
						out.writeBytes(textField.getText()+"\r\n");
						out.flush();
						String name = in.readLine();
						tablero.setNombre(textField.getText(), name,azul);
						tablero.setVisible(true);
						setVisible(false);
					}catch(IOException ex) {
						lblNewLabel.setText("Error al conectar");
					}
				}
				@Override
				public void mouseEntered(MouseEvent e) {
					Aceptar.setIcon(new ImageIcon("Recursos\\ConectarS.png"));
				}
				@Override
				public void mouseExited(MouseEvent e) {
					Aceptar.setIcon(new ImageIcon("Recursos\\Conectar.png"));
				}
			});
			Aceptar.setBounds(136, 148, 175, 47);
			Aceptar.setBorder(null);
			contentPane.add(Aceptar);
			lblNewLabel = new JLabel("Estableciendo Conexion...");
			lblNewLabel.setBounds(85, 35, 289, 28);
			lblNewLabel.setFont(new Font("Consolas", Font.PLAIN, 20));
			contentPane.add(lblNewLabel);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	
}
