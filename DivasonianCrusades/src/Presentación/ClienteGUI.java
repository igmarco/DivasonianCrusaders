package Presentación;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClienteGUI extends JFrame {

	private JPanel contentPane;
	private /*final*/ TableroGrafico tablero;
	private final JButton btContinuarPartida;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClienteGUI frame = new ClienteGUI(/*true,null*/);
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
	public ClienteGUI(/*boolean nuevo, final TableroGrafico Tablero*/) {
		setTitle("Divasonian Crusaders");
		final ClienteGUI main = this;
//		tablero = new TableroGrafico();
		setResizable(false);
		setBounds(100, 100, 395, 444);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 250, 205));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 230, 140));
		panel.setBounds(0, 0, 389, 417);
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
		btCargarPartida.setBounds(104, 221, 175, 47);
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
		btGuardarPartida.setBounds(104, 269, 175, 47);
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
		btSalir.setBounds(104, 342, 175, 47);
		btSalir.setBorder(null);
		panel.add(btSalir);
	
//		if(nuevo) {
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
								Socket s = new Socket("localhost",58000);
								//Deshabilitamos el botón continuar partida, ya que rompemos la partida que teníamos. Luego lo habilitamos al meter el nombre.
								btContinuarPartida.setEnabled(false);
								//OJO, en caso de que el jugador se eche para atrás no tiene sentido haber creado el TableroGrafico, no? eso hay que revisarlo.
								//Ya está revisado
								tablero = new TableroGrafico(main, s);
								Nombre nom = new Nombre(main, tablero, s);
								nom.setVisible(true);
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
//		}
//		else {
			btContinuarPartida = new JButton("");
			btContinuarPartida.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						setVisible(false); 
						/*T*/ tablero.setVisible(true);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			});
			btContinuarPartida.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					btContinuarPartida.setIcon(new ImageIcon("Recursos\\ContinuarBotonS.png"));
				}
				@Override
				public void mouseExited(MouseEvent e) {
					btContinuarPartida.setIcon(new ImageIcon("Recursos\\ContinuarBoton.png"));
				}
			});
			btContinuarPartida.setBackground(new Color(240, 230, 140));
			btContinuarPartida.setFont(new Font("Matura MT Script Capitals", Font.PLAIN, 20));
			btContinuarPartida.setForeground(new Color(245, 245, 245));
			//btNuevaPartida.setBackground(new Color(240, 230, 140));
			btContinuarPartida.setIcon(new ImageIcon("Recursos\\ContinuarBoton.png"));
			btContinuarPartida.setBounds(104, 173, 175, 47);
			btContinuarPartida.setHorizontalTextPosition(SwingConstants.CENTER);
			btContinuarPartida.setBorder(null);
			/**/ btContinuarPartida.setEnabled(false);
			panel.add(btContinuarPartida);
//		}
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("Recursos\\LogoD.png"));
		lblNewLabel.setBounds(48, 5, 303, 113);
		panel.add(lblNewLabel);
	}
	
	public void habilitarContinuar() {
		
		btContinuarPartida.setEnabled(true); 
		
	}
}
