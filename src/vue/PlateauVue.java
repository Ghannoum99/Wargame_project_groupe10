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

	private JLayeredPane plateau;
	private PanelTerrains panelTerrains;
	private SoldatVue soldatVue;
	private PanelInfosSoldat panelInfosSoldat;
	private PanelInfosJoueur infosJoueur;
	private MiniMap minimap;
	private Joueur tourJoueur;
	private ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
	private Guide guide;
	
	public PlateauVue(ArrayList<Joueur> joueurs) {  
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
		
		this.plateau = new JLayeredPane();
		this.plateau.setLayout(null);
		this.plateau.setBounds(0,0,1038, 790);
		this.plateau.setVisible(true);
		this.plateau.setOpaque(false);
		this.add(this.plateau);
		
		
		
		// Création du panel permettant d'afficher les infos du soldat
		this.panelInfosSoldat = new PanelInfosSoldat();
		this.plateau.add(this.panelInfosSoldat, JLayeredPane.DEFAULT_LAYER);
		
		this.infosJoueur = new PanelInfosJoueur(joueurs);
		this.plateau.add(this.infosJoueur, JLayeredPane.DEFAULT_LAYER);
		this.infosJoueur.NomJoueur.setText(joueurs.get(0).getNomJoueur());
		this.infosJoueur.score.setText(String.valueOf((Integer)joueurs.get(0).getScore()));
		this.infosJoueur.nombreSoldat.setText(String.valueOf((Integer)joueurs.get(0).getSoldatList().size()));

		// Création des joueurs (juste pour faire les tests)
		this.joueurs = joueurs;
		
		// Création de minimap
		
		this.minimap = new MiniMap(this.joueurs, this.tourJoueur);
		this.minimap.AfficherMiniSoldat();
		this.plateau.add(this.minimap, JLayeredPane.DEFAULT_LAYER);
		
		
		// Création des labels représentant les soldats
		this.soldatVue = new SoldatVue();
		this.soldatVue.creerSoldats(this.joueurs);
		
		// Choix aléatoire d'un joueur pour commencer le tour
		int ind =(int) (Math.random() * (this.joueurs.size() - 0));
		this.tourJoueur = this.joueurs.get(ind);
	
		// Tutoriel du jeu
		this.guide = new Guide();
		this.plateau.add(this.guide, JLayeredPane.DEFAULT_LAYER);
		this.guide.afficherIndicationsSelection();
		
		// Image du personnage expliquant les règles
		ImageIcon imageInterlo = new ImageIcon(new ImageIcon("images/profile/image7.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
		JLabel labelImageInterlo = new JLabel(imageInterlo);
		labelImageInterlo.setOpaque(false);
		labelImageInterlo.setBounds(10, 400, 300, 400);
		this.plateau.add(labelImageInterlo, JLayeredPane.DRAG_LAYER);
		
		// Création du panel permettant d'afficher les terrains et de positionner les soldats
		this.panelTerrains = new PanelTerrains(this.tourJoueur, this.soldatVue, this.panelInfosSoldat);
		this.plateau.add(this.panelTerrains.getScrollPane(), JLayeredPane.DEFAULT_LAYER);
		
		/** Panel Fin Baitaille **/
		//PanelFinBataille fin = new PanelFinBataille(joueurs);
		//this.plateau.add(fin, JLayeredPane.DRAG_LAYER);
		//PanelMenuInfos panelMenu = new PanelMenuInfos(155, 98, 544, 440);
		//this.plateau.add(panelMenu, JLayeredPane.DRAG_LAYER);
		
		
		
		SwingUtilities.updateComponentTreeUI(this.plateau);
	}
	

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
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

	public Guide getGuide() {
		return guide;
	}

	public void setGuide(Guide guide) {
		this.guide = guide;
	}


	public ArrayList<Joueur> getJoueurs() {
		return joueurs;
	}


	public void setJoueurs(ArrayList<Joueur> joueurs) {
		this.joueurs = joueurs;
	}
	
	
}
