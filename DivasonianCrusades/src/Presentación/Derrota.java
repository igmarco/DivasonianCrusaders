package Presentación;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Derrota extends JFrame {

	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	public Derrota(String name, final ClienteGUI main, TableroGrafico tg) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("Recursos\\iconoRefachero2.png"));
		setTitle("Divas\u00F3n estar\u00E1 decepcionado");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 281);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 230, 140));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel lblNewLabel = new JLabel(name);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Consolas", Font.PLAIN, 22));
		lblNewLabel.setBounds(10, 54, 424, 49);
		contentPane.add(lblNewLabel);
		JButton btnNewButton = new JButton("Vale");
		btnNewButton.setBackground(SystemColor.info);
		btnNewButton.setForeground(SystemColor.menuText);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				main.deshabilitarRendirse();
				main.deshabilitarContinuar();
				main.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Consolas", Font.PLAIN, 20));
		btnNewButton.setBounds(147, 174, 155, 49);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("Recursos\\HachaDivasoniaI.png"));
		lblNewLabel_1.setBounds(328, 145, 66, 78);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("New label");
		lblNewLabel_1_1.setIcon(new ImageIcon("Recursos\\HachaDivasonia.png"));
		lblNewLabel_1_1.setBounds(52, 145, 66, 78);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblEnhorabuena = new JLabel("Vaya...");
		lblEnhorabuena.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnhorabuena.setFont(new Font("Consolas", Font.PLAIN, 31));
		lblEnhorabuena.setBounds(10, 11, 424, 49);
		contentPane.add(lblEnhorabuena);
		
		JLabel lblHasGanado = new JLabel("has comido hacha");
		lblHasGanado.setHorizontalAlignment(SwingConstants.CENTER);
		lblHasGanado.setFont(new Font("Consolas", Font.PLAIN, 27));
		lblHasGanado.setBounds(10, 98, 424, 49);
		contentPane.add(lblHasGanado);
		
		JLabel lblNewLabel_2 = new JLabel("\u00BFOlvidaste usar el flush()?");
		lblNewLabel_2.setFont(new Font("Consolas", Font.PLAIN, 12));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(10, 145, 424, 14);
		contentPane.add(lblNewLabel_2);
	}

}
