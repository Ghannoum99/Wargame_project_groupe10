package vue;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	private PanelInfosJoueur infosJoueur;
	private PanelInfosSoldat panelInfosSoldat;
	private MiniMap minimap;
	private Joueur tourJoueur;
	private ArrayList<Joueur> joueurs;
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
		this.setBackground(Color.black);
		this.setTitle("WarGame");
		
		GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        if (device.isFullScreenSupported()) {
            device.setFullScreenWindow(this);
        } 

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

		// Création des joueurs 
		this.joueurs = joueurs;

		// Création des labels représentant les soldats
		this.soldatVue = new SoldatVue();
		this.soldatVue.creerSoldats(this.joueurs);

		// Choix aléatoire d'un joueur pour commencer le tour
		int ind =(int) (Math.random() * (this.joueurs.size() - 0));
		this.tourJoueur = this.joueurs.get(ind);

		/** Panel Pause **/
		PanelPause MenuPause = new PanelPause(this.joueurs);
		this.plateau.add(MenuPause, JLayeredPane.DRAG_LAYER);

		//Affichage de bouton pause
		ImageIcon imageIconPause = new ImageIcon("images/ornate_pause_30-active.png");
		JButton BoutonPause = new JButton(imageIconPause);
		BoutonPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuPause.setVisible(true);
			}
		});
		BoutonPause.setBounds(977, 610, imageIconPause.getIconWidth(), imageIconPause.getIconHeight());
		this.plateau.add(BoutonPause, JLayeredPane.DEFAULT_LAYER);

		// Création de minimap
		this.minimap = new MiniMap(this.joueurs, this.tourJoueur,this.soldatVue, this);
		this.plateau.add(this.minimap, JLayeredPane.DEFAULT_LAYER);

		// Tutoriel du jeu
		this.guide = new Guide(this.joueurs.size());
		this.plateau.add(this.guide, JLayeredPane.DEFAULT_LAYER);
		this.guide.afficherIndicationsSelection(ind);

		// Image du personnage expliquant les règles
		ImageIcon imageInterlo = new ImageIcon(new ImageIcon("images/profile/image7.png").getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT));
		JLabel labelImageInterlo = new JLabel(imageInterlo);
		labelImageInterlo.setBounds(0, 400, 300, 400);
		this.plateau.add(labelImageInterlo, JLayeredPane.DRAG_LAYER);

		// Création du panel permettant d'afficher les terrains et de positionner les soldats
		this.panelTerrains = new PanelTerrains(this.tourJoueur, this.soldatVue, this.panelInfosSoldat, this.guide);
		this.plateau.add(this.panelTerrains.getScrollPane(), JLayeredPane.DEFAULT_LAYER);

		setTourJoueur(tourJoueur, ind);

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
			int ind = 0;
			Joueur ancienJoueur, nouveauJoueur;
			ancienJoueur = this.panelTerrains.getTourJoueur();
			nouveauJoueur = ancienJoueur;
			while (nouveauJoueur == ancienJoueur) {
				ind = (int) (Math.random() * (joueurs.size() - 0));
				nouveauJoueur = joueurs.get(ind);
			}
			setTourJoueur(nouveauJoueur, ind);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	public Joueur getTourJoueur() {
		return tourJoueur;
	}

	public void setTourJoueur(Joueur tourJoueur, int ind) {
		this.tourJoueur = tourJoueur;
		this.panelTerrains.setTourJoueur(this.tourJoueur);
		this.panelTerrains.setIndTourJoueur(ind);
	}

	public Guide getGuide() {
		return guide;
	}

	public void setGuide(Guide guide) {
		this.guide = guide;
	}

}