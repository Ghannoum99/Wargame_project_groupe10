package controleur;
import vue.*;

import java.awt.EventQueue;

public class Tester {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuMultiJoueur window = new MenuMultiJoueur();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
