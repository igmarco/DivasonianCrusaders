package Presentación;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClienteGUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClienteGUI frame = new ClienteGUI();
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
	public ClienteGUI() {
		setResizable(false);
		setBounds(100, 100, 395, 399);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 250, 205));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 230, 140));
		panel.setBounds(0, 0, 389, 370);
		contentPane.add(panel);
		panel.setLayout(null);
		
		final JButton btCargarPartida = new JButton("");
		btCargarPartida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btCargarPartida.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btCargarPartida.setIcon(new ImageIcon("Rercursos\\CargarPartida2.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btCargarPartida.setIcon(new ImageIcon("Rercursos\\CargarPartida.png"));
			}
		});
		btCargarPartida.setForeground(new Color(255, 250, 240));
		btCargarPartida.setFont(new Font("Matura MT Script Capitals", Font.PLAIN, 20));
		btCargarPartida.setBackground(new Color(240, 230, 140));
		btCargarPartida.setIcon(new ImageIcon("Rercursos\\CargarPartida.png"));
		btCargarPartida.setBounds(104, 174, 175, 47);
		btCargarPartida.setHorizontalTextPosition(SwingConstants.CENTER);
		btCargarPartida.setBorder(null);
		panel.add(btCargarPartida);
		
		final JButton btGuardarPartida = new JButton("");
		btGuardarPartida.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btGuardarPartida.setIcon(new ImageIcon("Rercursos\\GuardarPartida2.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btGuardarPartida.setIcon(new ImageIcon("Rercursos\\GuardarPartida.png"));
			}
		});
		btGuardarPartida.setBackground(new Color(240, 230, 140));
		btGuardarPartida.setIcon(new ImageIcon("Rercursos\\GuardarPartida.png"));
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
				btSalir.setIcon(new ImageIcon("Rercursos\\Salir2.1.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btSalir.setIcon(new ImageIcon("Rercursos\\Salir2.png"));
			}
		});
		btSalir.setIcon(new ImageIcon("Rercursos\\Salir2.png"));
		btSalir.setBackground(new Color(240, 230, 140));
		btSalir.setBounds(104, 295, 175, 47);
		btSalir.setBorder(null);
		panel.add(btSalir);
	
		final JButton btNuevaPartida = new JButton("");
		btNuevaPartida.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btNuevaPartida.setIcon(new ImageIcon("Rercursos\\NuevaPartida2.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btNuevaPartida.setIcon(new ImageIcon("Rercursos\\NuevaPartida.png"));
			}
		});
		btNuevaPartida.setBackground(new Color(240, 230, 140));
		btNuevaPartida.setFont(new Font("Matura MT Script Capitals", Font.PLAIN, 20));
		btNuevaPartida.setForeground(new Color(245, 245, 245));
		//btNuevaPartida.setBackground(new Color(240, 230, 140));
		btNuevaPartida.setIcon(new ImageIcon("Rercursos\\NuevaPartida.png"));
		btNuevaPartida.setBounds(104, 125, 175, 47);
		btNuevaPartida.setHorizontalTextPosition(SwingConstants.CENTER);
		btNuevaPartida.setBorder(null);
		panel.add(btNuevaPartida);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\pablo\\OneDrive\\Escritorio\\BlackLynx-Studio\\DivasonianCrusades\\Rercursos\\LogoD.png"));
		lblNewLabel.setBounds(48, 5, 303, 113);
		panel.add(lblNewLabel);
	}
}
