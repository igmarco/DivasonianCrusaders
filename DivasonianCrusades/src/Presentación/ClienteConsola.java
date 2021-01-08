package Presentación;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class ClienteConsola extends JFrame {

	private JPanel contentPane;
	private final TableroConsola tablero;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClienteConsola frame = new ClienteConsola();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ClienteConsola() {
		final ClienteConsola main = this;
		tablero = new TableroConsola(main);
		setResizable(false);
		setBounds(100, 100, 395, 399);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 250, 205));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 230, 140));
		panel.setBounds(0, 0, 389, 370);
		contentPane.add(panel);
		panel.setLayout(null);
		
		final JButton btCargarPartida = new JButton("");
		btCargarPartida.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btCargarPartida.setIcon(new ImageIcon("Recursos\\CargarPartida2.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btCargarPartida.setIcon(new ImageIcon("Recursos\\CargarPartida.png"));
			}
		});
		btCargarPartida.setForeground(new Color(255, 250, 240));
		btCargarPartida.setFont(new Font("Matura MT Script Capitals", Font.PLAIN, 20));
		btCargarPartida.setBackground(new Color(240, 230, 140));
		btCargarPartida.setIcon(new ImageIcon("Recursos\\CargarPartida.png"));
		btCargarPartida.setBounds(104, 174, 175, 47);
		btCargarPartida.setHorizontalTextPosition(SwingConstants.CENTER);
		btCargarPartida.setBorder(null);
		panel.add(btCargarPartida);
		
		final JButton btGuardarPartida = new JButton("");
		btGuardarPartida.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btGuardarPartida.setIcon(new ImageIcon("Recursos\\GuardarPartida2.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btGuardarPartida.setIcon(new ImageIcon("Recursos\\GuardarPartida.png"));
			}
		});
		btGuardarPartida.setBackground(new Color(240, 230, 140));
		btGuardarPartida.setIcon(new ImageIcon("Recursos\\GuardarPartida.png"));
		btGuardarPartida.setBounds(104, 222, 175, 47);
		btGuardarPartida.setHorizontalTextPosition(SwingConstants.CENTER);
		btGuardarPartida.setBorder(null);
		panel.add(btGuardarPartida);
		
		final JButton btSalir = new JButton("");
		btSalir.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btSalir.setIcon(new ImageIcon("Recursos\\Salir2.1.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btSalir.setIcon(new ImageIcon("Recursos\\Salir2.png"));
			}
		});
		btSalir.setIcon(new ImageIcon("Recursos\\Salir2.png"));
		btSalir.setBackground(new Color(240, 230, 140));
		btSalir.setBounds(104, 295, 175, 47);
		btSalir.setBorder(null);
		panel.add(btSalir);
	
		final JButton btNuevaPartida = new JButton("");
		btNuevaPartida.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btNuevaPartida.setIcon(new ImageIcon("Recursos\\NuevaPartida2.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btNuevaPartida.setIcon(new ImageIcon("Recursos\\NuevaPartida.png"));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
						try {
							setVisible(false);
							
							Scanner sc = new Scanner(System.in);
							System.out.println("Introduzca el nombre:");
							String nom = sc.nextLine();
							
//							Nombre nom = new Nombre(main,tablero);
//							nom.setVisible(true);
							
							Socket s = new Socket("localhost",58000);
							DataInputStream in = new DataInputStream( s.getInputStream());
							DataOutputStream out = new DataOutputStream(s.getOutputStream());
							out.writeBytes("Conexion establecida\r\n");
							out.flush();
							String linea = in.readLine();
							if(linea.compareTo("OK1")==0) {
								linea = in.readLine();
								System.out.println("¡Oponente encontrado!");
							}else {
								System.out.println("¡Oponente encontrado!");
							}
							out.writeBytes(nom+"\r\n");
							out.flush();
							String name = in.readLine();
							tablero.setNombre(nom, name);
							tablero.setVisible(true);
							setVisible(false);
							
						} catch(IOException ex) {
							System.out.println("Error al conectar");
						} catch (Exception ex) {
							ex.printStackTrace();
						}
			}
		});
		btNuevaPartida.setBackground(new Color(240, 230, 140));
		btNuevaPartida.setFont(new Font("Matura MT Script Capitals", Font.PLAIN, 20));
		btNuevaPartida.setForeground(new Color(245, 245, 245));
		//btNuevaPartida.setBackground(new Color(240, 230, 140));
		btNuevaPartida.setIcon(new ImageIcon("Recursos\\NuevaPartida.png"));
		btNuevaPartida.setBounds(104, 125, 175, 47);
		btNuevaPartida.setHorizontalTextPosition(SwingConstants.CENTER);
		btNuevaPartida.setBorder(null);
		panel.add(btNuevaPartida);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("Recursos\\LogoD.png"));
		lblNewLabel.setBounds(48, 5, 303, 113);
		panel.add(lblNewLabel);
	}
}
