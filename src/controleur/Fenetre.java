package controleur;

import java.awt.event.*;

import javax.swing.*;

import modele.*;

public class Fenetre extends JFrame {

	private String titre;
	
	public Fenetre(String titre) {
		this.titre = titre;
	}
	
	public static void main(String[] args) {
		JFrame fenetre = new JFrame();
		
		Hexagone hexagone = new Hexagone("images/hex.png");
		Piece piece = new Piece(100, 200, "images/char.png");
		
		JLabel perso = transformeLabel(hexagone.getImage());
		fenetre.add(perso);
		perso.setLocation(piece.getAbscisse(), piece.getOrdonnees());
		
	    fenetre.pack();
	    fenetre.setVisible(true);
	    fenetre.setSize(800, 800);
	    fenetre.setLayout(null);
	}
	
	public static JLabel transformeLabel(String image) {
		ImageIcon icon = new ImageIcon(image);
	    JLabel label = new JLabel(icon);
		return label;
	}

}
