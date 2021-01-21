package Presentación;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

public class CargarPartida extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblEstado;
	private JButton Aceptar;
	private JButton Cancelar; 
	
	TableroGrafico tablero;
	
	private DataInputStream in;
	private DataOutputStream out;
	private JScrollPane scrollPane;
	
	ClienteGUI menu;

	/**
	 * Create the frame.
	 */
	public CargarPartida(final ClienteGUI menu) {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("Recursos\\iconoRefachero2.png"));
		
		this.menu = menu;
		
		setTitle("Divasonian Crusaders"); 
		final CargarPartida main = this;
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
			setBounds(100, 100, 480, 559);
			contentPane = new JPanel();
			contentPane.setBackground(new Color(240, 230, 140));
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			JLabel lblNewLabel_1 = new JLabel("Introduzca su nombre:");	
			lblNewLabel_1.setVerticalAlignment(SwingConstants.BOTTOM);
			lblNewLabel_1.setFont(new Font("Consolas", Font.PLAIN, 20));
			lblNewLabel_1.setBounds(20, 327, 238, 28);
			contentPane.add(lblNewLabel_1);
			
			textField = new JTextField();
			textField.setFont(new Font("Consolas", Font.PLAIN, 20));
			textField.setBounds(273, 329, 166, 28);
			contentPane.add(textField);
			textField.setColumns(10);
			
			Aceptar = new JButton("");
			Aceptar.setBackground(new Color(240, 230, 140));
			Aceptar.setIcon(new ImageIcon("Recursos\\Conectar.png"));
			Aceptar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if(Azul.isSelected()||Rojo.isSelected()) {
						
						BufferedReader br = null;
						
						try {
							Socket s = new Socket(menu.getIp(),58000);
							boolean azul;
							ObjectInputStream in = new ObjectInputStream(new DataInputStream( s.getInputStream()));
							ObjectOutputStream out = new ObjectOutputStream(new DataOutputStream(s.getOutputStream()));
							lblEstado.setText("Buscando oponente..."); //T.T
							String linea = in.readLine();
							//System.out.println("Llegué sii...");
							if(linea.compareTo("OK1-primero")==0) {
								out.writeBytes("LOAD-Mandamos las partidiñas luego broski ;)\r\n");
								out.flush();
								linea = in.readLine();
								if(Azul.isSelected()) {
									azul = true;
								}else {
									azul = false;
								}
							
								out.writeBytes(textField.getText()+"\r\n");
								out.flush();
								String name = in.readLine();
								String ok = in.readLine();
								menu.habilitarContinuar();
								menu.habilitarRendirse();
								/*/Esta puñetera orden vacia el buffer y parece que funciona/*/
	//							in.skip(in.available());
								
								iniciarTableroGrafico(s, in, out);
								menu.setTableroGrafico(tablero); 
								
								tablero.setNombre(textField.getText(), name,azul);
								out.writeBytes("PartidasGuardadasServidor\\"+(String)list.getSelectedValue()+"\r\n");
								out.flush();
								
								
								//------------------- ENVÍO DE TABLERO.XML -------------------------
								
								String existeXMLEnServidor = in.readLine();
								
								if(existeXMLEnServidor.split("-")[0].equals("NEXISTS")) {
									
									br = new BufferedReader(new FileReader("PartidasGuardadas\\"+(String)list.getSelectedValue()));
									String lineaXML;
									while((lineaXML = br.readLine()) != null) {
										
										out.writeBytes(lineaXML+"\r\n");
										
									}
									out.flush();
									
									out.writeBytes("OK3-Terminado\r\n");
									out.flush();
									
								}
								
								//------------------------------------------------------------------
								
								
								if(azul)
									out.writeBytes("A\r\n");
								else
									out.writeBytes("R\r\n");
								out.flush();
								int turno = in.readInt();
								tablero.setTurno(turno);
	//							tablero.setIn(in);
								tablero.setTablero();
	//							tablero.setOut(out);
								tablero.setVisible(true);
								setVisible(false);
							
							}else /*if(linea.compareTo("OK1-segundo")==0)*/ {
								out.writeBytes("LOAD-Mandamos las partidiñas luego broski ;)\r\n");
								out.flush();
								azul = false;
								linea = in.readLine();
								
								out.writeBytes(textField.getText()+"\r\n");
								out.flush();
								String name = in.readLine();
								String ok = in.readLine();
								
//								if(azul) {
//									ok = in.readLine();
//								}
								
								menu.habilitarContinuar();
								menu.habilitarRendirse();
								
								iniciarTableroGrafico(s, in, out);
								menu.setTableroGrafico(tablero);
								
								if(ok.equals("NEW")/*|| azul*/) {
									
									if(Azul.isSelected()) {
										azul = true;
									}else {
										azul = false;
									}
									
									
									tablero.setNombre(textField.getText(), name,azul);
									out.writeBytes("PartidasGuardadasServidor\\"+(String)list.getSelectedValue()+"\r\n");
									out.flush();
									
									//------------------- ENVÍO DE TABLERO.XML -------------------------
									
									String existeXMLEnServidor = in.readLine();
									
									if(existeXMLEnServidor.split("-")[0].equals("NEXISTS")) {
										
										br = new BufferedReader(new FileReader("PartidasGuardadas\\"+(String)list.getSelectedValue()));
										String lineaXML;
										while((lineaXML = br.readLine()) != null) {
											
											out.writeBytes(lineaXML+"\r\n");
											
										}
										out.flush();
										
										out.writeBytes("OK3-Terminado\r\n");
										out.flush(); 
										
									}
									
									//------------------------------------------------------------------
									
									if(azul)
										out.writeBytes("A\r\n");
									else
										out.writeBytes("R\r\n");
									out.flush();
									int turno = in.readInt();
									tablero.setTurno(turno);
		//							tablero.setIn(in);
									tablero.setTablero();
		//							tablero.setOut(out);
									tablero.setVisible(true);
									setVisible(false);
								}else {
									menu.habilitarContinuar();
									menu.habilitarRendirse();
									String color = in.readLine();
									if(color.equals("A"))
										azul=true;
									else
										azul=false;
									
									int turno = in.readInt();
									/*/Esta puñetera orden vacia el buffer y parece que funciona/*/
//									in.skip(in.available());
									
									tablero.setNombre(textField.getText(), name,azul);
									tablero.setTurno(turno);
//									tablero.setIn(in);
									tablero.setTablero();
//									tablero.setOut(out);
									tablero.setVisible(true);
									menu.salir(true);
									setVisible(false);
									
								}
								
							}
							
						}catch(IOException ex) { 
							lblEstado.setText("Error al conectar");
							ex.printStackTrace();
							menu.restaurarMenu();
							menu.setVisible(true);
							setVisible(false);
						}finally {
							
							if(br != null) {
								
								try {
									br.close();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								
							}
							
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
			Aceptar.setBounds(264, 452, 175, 47);
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
			Cancelar.setBounds(51, 452, 175, 47);
			
			contentPane.add(Cancelar);
			
			JLabel lblNewLabel_1_1 = new JLabel("Seleccione la partida:");
			lblNewLabel_1_1.setVerticalAlignment(SwingConstants.BOTTOM);
			lblNewLabel_1_1.setFont(new Font("Consolas", Font.PLAIN, 20));
			lblNewLabel_1_1.setBounds(106, 77, 253, 28);
			contentPane.add(lblNewLabel_1_1);
			
			scrollPane = new JScrollPane();
			scrollPane.setBounds(20, 116, 419, 192);
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
			Azul.setBounds(62, 408, 109, 23);
			contentPane.add(Azul);
			
			
			Rojo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(Azul.isSelected())
						Azul.setSelected(false);
				}
			});
			Rojo.setFont(new Font("Consolas", Font.PLAIN, 15));
			Rojo.setBackground(new Color(240, 230, 140));
			Rojo.setBounds(173, 408, 109, 23);
			contentPane.add(Rojo);
			
			JLabel lblNewLabel_1_2 = new JLabel("Facci\u00F3n:");
			lblNewLabel_1_2.setVerticalAlignment(SwingConstants.BOTTOM);
			lblNewLabel_1_2.setFont(new Font("Consolas", Font.PLAIN, 20));
			lblNewLabel_1_2.setBounds(20, 373, 231, 28);
			contentPane.add(lblNewLabel_1_2);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void iniciarTableroGrafico(Socket s, ObjectInputStream in2, ObjectOutputStream out2) {
		
		this.tablero = new TableroGrafico(menu, s, in2, out2);
		
	}
}
