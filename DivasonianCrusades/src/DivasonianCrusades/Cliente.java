package DivasonianCrusades;

import java.awt.EventQueue;
import java.util.Scanner;

import Presentación.ClienteGUI;

public class Cliente {

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

}
