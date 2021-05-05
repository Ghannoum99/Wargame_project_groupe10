package controleur;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controleur.Fenetre;

public class Touches {

	public class Touches extends JFrame implements KeyListener {
		 Fenetre ardoise = new Fenetre("hello");
		 JLabel labelR = new JLabel(" touche r : ovale rouge");
		 JLabel labelB = new JLabel(" touche b : ovale bleu");
		 JLabel labelV = new JLabel(" touche v : ovale vert");
		 JLabel labelE = new JLabel(" touche e : effacer");
		 JPanel indications = new JPanel();
		 
		 Touches() {
			 indications.setLayout(new GridLayout(4, 1));
			 indications.add(labelR);
			 indications.add(labelB);
			 indications.add(labelV);
			 indications.add(labelE);
			 add(indications, BorderLayout.NORTH);
			 add(ardoise, BorderLayout.CENTER);
			 addKeyListener (this);
			 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			 addWindowListener(new WindowAdapter() {
				public void windowOpened(WindowEvent e) { 
				requestFocus();	
				}
				});
			 pack();
		 setVisible(true);
		 }

	}
	
	public void keyPressed(KeyEvent evt){}

	public void keyReleased(KeyEvent evt){} 

	public void keyTyped(KeyEvent evt) {
			 if (evt.getKeyChar() == 'r')
				ardoise.setForeground(Color.RED);
			 else if (evt.getKeyChar() == 'b')
				ardoise.setForeground(Color.BLUE);
			 else if (evt.getKeyChar() == 'v')
				ardoise.setForeground(Color.GREEN);
			 else if (evt.getKeyChar() == 'e')
				ardoise.setForeground(ardoise.getBackground());
			repaint();
		}	  
}
