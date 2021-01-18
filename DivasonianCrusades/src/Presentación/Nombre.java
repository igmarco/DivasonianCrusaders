package Presentación;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;

public class Nombre extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblEstado;
	private JButton Aceptar;
	private JButton Cancelar; 
	/**/ private Socket s;
	
	private DataInputStream in;
	private DataOutputStream out;
	


	/**
	 * Create the frame.
	 */
	public Nombre(final ClienteGUI menu, final TableroGrafico tablero, final Socket s) {
		setTitle("Divasonian Crusaders"); 
		final Nombre main = this;
		/**/ this.s = s;
		setResizable(false);
		try {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 463, 272);
			contentPane = new JPanel();
			contentPane.setBackground(new Color(240, 230, 140));
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			JLabel lblNewLabel_1 = new JLabel("Introduzca su nombre:");	
			lblNewLabel_1.setVerticalAlignment(SwingConstants.BOTTOM);
			lblNewLabel_1.setFont(new Font("Consolas", Font.PLAIN, 20));
			lblNewLabel_1.setBounds(20, 101, 238, 28);
			contentPane.add(lblNewLabel_1);
			
			textField = new JTextField();
			textField.setFont(new Font("Consolas", Font.PLAIN, 20));
			textField.setBounds(268, 101, 166, 28);
			contentPane.add(textField);
			textField.setColumns(10);
			
			Aceptar = new JButton("");
			Aceptar.setBackground(new Color(240, 230, 140));
			Aceptar.setIcon(new ImageIcon("Recursos\\Conectar.png"));
//			Aceptar.addActionListener(new ActionListener() {
//			@Override
//				public void actionPerformed(ActionEvent arg0) {
//				final SwingWorker worker = new SwingWorker() {
//					@Override
//					protected Object doInBackground() throws Exception {
//						try {
////							Socket s = new Socket("localhost",58000);
//							boolean azul;
//							in = new DataInputStream( s.getInputStream());
//							out = new DataOutputStream(s.getOutputStream());
//							lblEstado.setText("Buscando oponente..."); //T.T
//							String linea = in.readLine();
//							//System.out.println("Llegué sii...");
//							if(linea.compareTo("OK1")==0) {
//								azul = true;
//								lblEstado.setText("Conexión establecida");
//								linea = in.readLine();
//							}else { 
//								azul=false;
//								lblEstado.setText("Conexión establecida");
//							}
//							out.writeBytes(textField.getText()+"\r\n");
//							out.flush();
//							String name = in.readLine();
//							menu.habilitarContinuar();
//							menu.habilitarRendirse();
//							/*/Esta puñetera orden vacia el buffer y parece que funciona/*/
//							in.skip(in.available());
//							
//							tablero.setNombre(textField.getText(), name,azul);
//							tablero.setIn(in);
//							tablero.setOut(out);
//							tablero.setVisible(true);
//							setVisible(false);
//						}catch(IOException ex) { 
//							lblEstado.setText("Error al conectar");
//							ex.printStackTrace();
//							menu.setVisible(true);
//							setVisible(false);
//						}
//						return null;
//					}
//				};
//					// TODO Auto-generated method stub
//					worker.execute();
//				}});
			Aceptar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					try {
//						Socket s = new Socket("localhost",58000);
						boolean azul;
						DataInputStream in = new DataInputStream( s.getInputStream());
						DataOutputStream out = new DataOutputStream(s.getOutputStream());
						lblEstado.setText("Buscando oponente..."); //T.T
						String linea = in.readLine();
						//System.out.println("Llegué sii...");
						if(linea.compareTo("OK1")==0) {
							out.writeBytes("NEW-Una nueva\r\n");
							out.flush();
							azul = true;
							lblEstado.setText("¡Oponente encontrado!");
							linea = in.readLine();
						}else { 
							azul=false;
							lblEstado.setText("¡Oponente encontrado!");
						}
						out.writeBytes(textField.getText()+"\r\n");
						out.flush();
						String name = in.readLine();
						String ok = in.readLine();
						System.out.println(ok);
						if(ok.equals("NEW")) {
							menu.habilitarContinuar();
							menu.habilitarRendirse();
							/*/Esta puñetera orden vacia el buffer y parece que funciona/*/
							in.skip(in.available());
							
							tablero.setNombre(textField.getText(), name,azul);
							tablero.setIn(in);
							tablero.setOut(out);
							tablero.setVisible(true);
							menu.salir(true);
							setVisible(false);
						}else {
						}
						
					}catch(IOException ex) { 
						lblEstado.setText("Error al conectar");
						ex.printStackTrace();
						menu.setVisible(true);
						setVisible(false);
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
			Aceptar.setBounds(237, 158, 175, 47);
			Aceptar.setBorder(null);
			contentPane.add(Aceptar);
			lblEstado = new JLabel("Creando la partida.");
			lblEstado.setHorizontalAlignment(SwingConstants.CENTER);
			lblEstado.setBounds(30, 38, 388, 28);
			lblEstado.setFont(new Font("Consolas", Font.PLAIN, 27));
			contentPane.add(lblEstado);
			
			Cancelar = new JButton("");
			Cancelar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					menu.setVisible(true);
					setVisible(false);
					
				}
				@Override
				public void mouseEntered(MouseEvent e) {
					Cancelar.setIcon(new ImageIcon("Recursos\\CancelarS.png"));
				}
				@Override
				public void mouseExited(MouseEvent e) {
					Cancelar.setIcon(new ImageIcon("Recursos\\Cancelar.png"));
				}
			});
			Cancelar.setIcon(new ImageIcon("Recursos\\Cancelar.png"));
			Cancelar.setBorder(null);
			Cancelar.setBackground(new Color(240, 230, 140));
			Cancelar.setBounds(30, 158, 175, 47);
			
			contentPane.add(Cancelar);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}

