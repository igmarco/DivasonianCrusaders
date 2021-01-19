package Presentación;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import LN.Tablero;

public class CargarPartida extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblEstado;
	private JButton Aceptar;
	private JButton Cancelar; 
	/**/ private Socket s;
	
	private DataInputStream in;
	private DataOutputStream out;
	private JScrollPane scrollPane;
	


	/**
	 * Create the frame.
	 */
	public CargarPartida(final ClienteGUI menu, final TableroGrafico tablero, final Socket s) {
		setTitle("Divasonian Crusaders"); 
		final CargarPartida main = this;
		/**/ this.s = s;
		setResizable(false);
		try {
			final JRadioButton Azul = new JRadioButton("Azul");
			final JRadioButton Rojo = new JRadioButton("Rojo");
			
			List<String> partidas = new ArrayList();
			File file = new File("PartidasGuardadas");
			for(File fichero : file.listFiles()) {
				if(!fichero.isDirectory()) {
					partidas.add(fichero.getName());
				}
			}
			
			final JList list = new JList(partidas.toArray());

			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 480, 460);
			contentPane = new JPanel();
			contentPane.setBackground(new Color(240, 230, 140));
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			JLabel lblNewLabel_1 = new JLabel("Introduzca su nombre:");	
			lblNewLabel_1.setVerticalAlignment(SwingConstants.BOTTOM);
			lblNewLabel_1.setFont(new Font("Consolas", Font.PLAIN, 20));
			lblNewLabel_1.setBounds(20, 235, 238, 28);
			contentPane.add(lblNewLabel_1);
			
			textField = new JTextField();
			textField.setFont(new Font("Consolas", Font.PLAIN, 20));
			textField.setBounds(273, 237, 166, 28);
			contentPane.add(textField);
			textField.setColumns(10);
			
			Aceptar = new JButton("");
			Aceptar.setBackground(new Color(240, 230, 140));
			Aceptar.setIcon(new ImageIcon("Recursos\\Conectar.png"));
			Aceptar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if(Azul.isSelected()||Rojo.isSelected()) {
						try {
	//						Socket s = new Socket("localhost",58000);
							boolean azul;
							DataInputStream in = new DataInputStream( s.getInputStream());
							DataOutputStream out = new DataOutputStream(s.getOutputStream());
							lblEstado.setText("Buscando oponente..."); //T.T
							String linea = in.readLine();
							//System.out.println("Llegué sii...");
							if(linea.compareTo("OK1")==0) {
								out.writeBytes("LOAD-Mandamos las partidiñas luego broski ;)\r\n");
								linea = in.readLine();
							}
							out.writeBytes(textField.getText()+"\r\n");
							out.flush();
							String name = in.readLine();
							menu.habilitarContinuar();
							menu.habilitarRendirse();
							if(Azul.isSelected()) {
								azul = true;
							}else {
								azul = true;
							}
							/*/Esta puñetera orden vacia el buffer y parece que funciona/*/
							in.skip(in.available());
							tablero.setNombre(textField.getText(), name,azul);
							out.writeBytes("PartidasGuardadas\\"+(String)list.getSelectedValue()+"\r\n");
							out.flush();
							if(azul)
								out.writeBytes("A\r\n");
							else
								out.writeBytes("R\r\n");
							out.flush();
							int turno = in.readInt();
							tablero.setTurno(turno);
							tablero.setIn(in);
							tablero.setTablero();
							tablero.setOut(out);
							tablero.setVisible(true);
							setVisible(false);
						}catch(IOException ex) { 
							lblEstado.setText("Error al conectar");
							ex.printStackTrace();
							menu.setVisible(true);
							setVisible(false);
						}
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
			Aceptar.setBounds(264, 360, 175, 47);
			Aceptar.setBorder(null);
			contentPane.add(Aceptar);
			lblEstado = new JLabel("Cargando la partida.");
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
			Cancelar.setBounds(51, 360, 175, 47);
			
			contentPane.add(Cancelar);
			
			JLabel lblNewLabel_1_1 = new JLabel("Seleccione la partida:");
			lblNewLabel_1_1.setVerticalAlignment(SwingConstants.BOTTOM);
			lblNewLabel_1_1.setFont(new Font("Consolas", Font.PLAIN, 20));
			lblNewLabel_1_1.setBounds(106, 77, 253, 28);
			contentPane.add(lblNewLabel_1_1);
			
			scrollPane = new JScrollPane();
			scrollPane.setBounds(20, 116, 419, 108);
			contentPane.add(scrollPane);
			scrollPane.setViewportView(list);
			

			Azul.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(Rojo.isSelected())
						Rojo.setSelected(false);
				}
			});
			Azul.setFont(new Font("Consolas", Font.PLAIN, 15));
			Azul.setBackground(new Color(240, 230, 140));
			Azul.setBounds(62, 316, 109, 23);
			contentPane.add(Azul);
			
			
			Rojo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(Azul.isSelected())
						Azul.setSelected(false);
				}
			});
			Rojo.setFont(new Font("Consolas", Font.PLAIN, 15));
			Rojo.setBackground(new Color(240, 230, 140));
			Rojo.setBounds(173, 316, 109, 23);
			contentPane.add(Rojo);
			
			JLabel lblNewLabel_1_2 = new JLabel("Facci\u00F3n:");
			lblNewLabel_1_2.setVerticalAlignment(SwingConstants.BOTTOM);
			lblNewLabel_1_2.setFont(new Font("Consolas", Font.PLAIN, 20));
			lblNewLabel_1_2.setBounds(20, 281, 231, 28);
			contentPane.add(lblNewLabel_1_2);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
