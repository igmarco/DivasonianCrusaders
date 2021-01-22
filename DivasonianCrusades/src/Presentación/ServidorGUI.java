package Presentación;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DivasonianCrusades.Servidor;
import Utilidades.CerradorServidor;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Toolkit;

public class ServidorGUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServidorGUI frame = new ServidorGUI();
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
	public ServidorGUI() {
		
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("Recursos\\iconoRefachero2.png"));
		setTitle("Divasonian Crusaders: Servidor");
		final Timer timer = new Timer();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 449, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 230, 140));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 433, 28);
		contentPane.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("Herramientas");
		menuBar.add(mnNewMenu);
		
		final JMenuItem mntmNewMenuItem = new JMenuItem("Programar desconexión del servidor");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Object resultado = JOptionPane.showInputDialog(null, "Introduzca los minutos que quiere que pasen hasta la desconexión programada.", "Tiempo para la desconexión", JOptionPane.QUESTION_MESSAGE, null, null, 60);
				
				if(resultado != null && esEntero((String) resultado)) {
					
					timer.schedule(new CerradorServidor(), Integer.parseInt((String) resultado)*60000);
					
				}
				else if (!esEntero((String) resultado)) {
					
					JOptionPane.showMessageDialog(null, "Error al introducir los minutos", "Advertencia", JOptionPane.WARNING_MESSAGE);
					
				}
				
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Ayuda");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// Al pulsar esto se muestra la descripción básica de la aplicación y su uso.
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							Ayuda frameAyuda = new Ayuda();
							frameAyuda.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				
			}
		});
		mntmNewMenuItem_1.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("M\u00E1s informaci\u00F3n");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// Al pulsar esto se muestra la información de nosotros
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							Información frameInformación = new Información();
							frameInformación.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				
			}
		});
		mntmNewMenuItem_2.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(mntmNewMenuItem_2);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(63, 39, 303, 113);
		contentPane.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon("Recursos\\LogoD.png"));
		
		final JButton btSalir = new JButton("");
		
		btSalir.addMouseListener(new MouseAdapter() { 
			public void mouseClicked(MouseEvent e) {
				int resp = JOptionPane.showConfirmDialog(null, "Salir detendrá las partidas en curso. ¿Está seguro?", "Atención", JOptionPane.YES_NO_OPTION);
				
				if(resp == JOptionPane.YES_OPTION) {
					
					System.exit(0);
					
				}
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
		
		btSalir.setBorder(null);
		btSalir.setBackground(new Color(240, 230, 140));
		btSalir.setBounds(123, 176, 175, 47);
		contentPane.add(btSalir);
		
		
	}
	
	public boolean esEntero(String i) {
		
		try {
			Integer.parseInt(i);
			return true;
		}catch(NumberFormatException e) {
			return false;
		}
		
	}
}
