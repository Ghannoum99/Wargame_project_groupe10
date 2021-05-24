package vue;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

import java.util.*;
import modele.*;

/*
 * La classe PlateauVue permet d’afficher les différents éléments du plateau 
 * : les terrains, les soldats des joueurs de la partie et le cadre du plateau.
 */

@SuppressWarnings("serial")
public class PlateauVue extends JFrame implements KeyListener {

	private PanelTerrains panelTerrains;
	private SoldatVue soldatVue;
	private PanelInfosSoldat panelInfosSoldat;
	private Joueur tourJoueur;
	private ArrayList<Joueur> joueurs;
	
	public PlateauVue() {  
		// Définition des données de la fenêtre principale
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setBounds(0,0,1038, 790);
		this.getContentPane().setBackground(Color.white);	
		getContentPane().setLayout(null);
		this.setVisible(true);
		this.addKeyListener(this);
		this.setResizable(false);
		this.setTitle("WarGame");

		// Image de fond
		JPanel panTmp = new JPanel();
		panTmp.setBounds(0, 0, 1030, 765);
		panTmp.setOpaque(false);
		JLabel contentPane = new JLabel();
		contentPane.setIcon( new ImageIcon("images/plateau.png"));
		contentPane.setBounds(0, 50, 1024, 770);
		this.setContentPane(contentPane);
		this.getContentPane().add(panTmp);
		panTmp.setLayout(null);
		
		/** Panel Fin Baitaille **/
		//PanelFinBataille fin = new PanelFinBataille();
		//this.add(fin);
		
		// Création du panel permettant d'afficher les infos du soldat
		this.panelInfosSoldat = new PanelInfosSoldat();
		getContentPane().add(this.panelInfosSoldat);
		
		// Création des joueurs (juste pour faire les tests)
		this.joueurs = new ArrayList<Joueur>();
		this.joueurs.add(new Joueur("bobby", new ArrayList<Soldat>(), 0, ""));
		this.joueurs.add(new Joueur("bobby", new ArrayList<Soldat>(), 0, ""));
		this.joueurs.add(new Joueur("bobby", new ArrayList<Soldat>(), 0, ""));
		this.joueurs.add(new Joueur("bobby", new ArrayList<Soldat>(), 0, ""));
		
		// Création des labels représentant les soldats
		this.soldatVue = new SoldatVue();
		this.soldatVue.creerSoldats(this.joueurs);
		
		// Choix aléatoire d'un joueur pour commencer le tour
		int ind =(int) (Math.random() * (this.joueurs.size() - 0));
		this.tourJoueur = this.joueurs.get(ind);
	
		// Création du panel permettant d'afficher les terrains et de positionner les soldats
		this.panelTerrains = new PanelTerrains(this.tourJoueur, this.soldatVue, this.panelInfosSoldat);
		getContentPane().add(this.panelTerrains.getScrollPane());
		
		
		
		SwingUtilities.updateComponentTreeUI(this);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			new PlateauVue();
		});
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		this.panelTerrains.keyPressed(e);
		// Pour l'instant il faut appuyer sur la touche espace pour changer de joueur (juste pour les tests)
		if(e.getKeyCode()==KeyEvent.VK_SPACE){
			System.out.println("Changement de joueur");
			int ind;
			Joueur ancienJoueur, nouveauJoueur;
			ancienJoueur = this.panelTerrains.getTourJoueur();
			nouveauJoueur = ancienJoueur;
			while (nouveauJoueur == ancienJoueur) {
				ind = (int) (Math.random() * (joueurs.size() - 0));
				nouveauJoueur = joueurs.get(ind);
			}
			setTourJoueur(nouveauJoueur);
       }
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	public Joueur getTourJoueur() {
		return tourJoueur;
	}

	public void setTourJoueur(Joueur tourJoueur) {
		this.tourJoueur = tourJoueur;
		this.panelTerrains.setTourJoueur(this.tourJoueur);
	}
}