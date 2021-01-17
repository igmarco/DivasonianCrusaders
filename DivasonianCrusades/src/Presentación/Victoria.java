package Presentación;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class Victoria extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public Victoria(String name, final ClienteGUI main, TableroGrafico tg) {
		setTitle("Divas\u00F3n estar\u00E1 orgulloso de ti");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 235);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(238, 232, 170));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel lblNewLabel = new JLabel("Enhorabuena "+name+", has ganado.");
		lblNewLabel.setFont(new Font("Consolas", Font.PLAIN, 22));
		lblNewLabel.setBounds(10, 37, 434, 49);
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
		btnNewButton.setBounds(147, 126, 155, 49);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("Recursos\\HachaDivasoniaI.png"));
		lblNewLabel_1.setBounds(328, 97, 66, 78);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("New label");
		lblNewLabel_1_1.setIcon(new ImageIcon("Recursos\\HachaDivasonia.png"));
		lblNewLabel_1_1.setBounds(52, 97, 66, 78);
		contentPane.add(lblNewLabel_1_1);
	}
}
