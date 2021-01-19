package DivasonianCrusades;

import java.awt.EventQueue;
import java.util.Scanner;

import javax.swing.JOptionPane;

import Presentación.ClienteGUI;

public class Cliente {

	public static void main(String[] args) {
		
		final Object resultado = JOptionPane.showInputDialog(null, "Introduzca la dirección IP en la que se encuentra\r\nel servidor de Divasonian Crusaders.", "IP del servidor", JOptionPane.QUESTION_MESSAGE, null, null, "localhost");
		
		if(resultado == null) System.exit(0);
		else {
			
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						ClienteGUI frame = new ClienteGUI(/*true,null*/(String) resultado);
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			
		}
	}

}
