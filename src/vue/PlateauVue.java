package vue;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import java.util.*;
import modele.*;

/*
 * La classe PlateauVue permet d’afficher les différents éléments du plateau 
 * : les terrains, les soldats des joueurs de la partie et le cadre du plateau.
 */

@SuppressWarnings("serial")
public class PlateauVue extends JFrame {
	private JLayeredPane plateau;
	private PanelTerrains panelTerrains;
	private SoldatVue soldatVue;
	private PanelInfosJoueur infosJoueur;
	private PanelInfosSoldat panelInfosSoldat;
	private ScenarioStandard scenario;
	private Joueur joueurGagne;
	private MiniMap minimap;
	private Joueur tourJoueur;
	private ArrayList<Joueur> joueurs;
	private Guide guide;
	private boolean visible = true;
	private boolean clicked = false;

	public PlateauVue(ArrayList<Joueur> joueurs, ScenarioStandard scenario) {  
		// Définition des données de la fenêtre principale
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setBounds(0,0, 1310, 820);
		this.getContentPane().setBackground(Color.white);	
		getContentPane().setLayout(null);
		this.setVisible(true);
		this.setResizable(false);
		this.setBackground(Color.black);
		this.setTitle("WarGame");

		this.plateau = new JLayeredPane();
		this.plateau.setLayout(null);
		this.plateau.setVisible(true);
		this.plateau.setOpaque(false);
		this.add(this.plateau);

		// Image de fond
		JLabel backgroundimage = new JLabel("");
		this.add(backgroundimage);
		
		int widthPlateau, heightPlateau, xPanelsInfos, yGuide, widthGuide, xCompteur, yCompteur, xMiniBoutons, yBoutonFinirTour;
		
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		if (device.isFullScreenSupported() && size.getHeight() <= 720) {
			device.setFullScreenWindow(this);
			backgroundimage.setBounds(0, 0, 1267, 680);
			this.plateau.setBounds(0,0,1267, 680);
			backgroundimage.setIcon(new ImageIcon("images/plateau.png"));
			yBoutonFinirTour = 642;
			xMiniBoutons = 600;
		} 
		else {
			backgroundimage.setBounds(0, 0, 1300, 781);
			this.plateau.setBounds(0,0,1300, 781);
			backgroundimage.setIcon(new ImageIcon("images/plateauV2.png"));
			yBoutonFinirTour = 720;
			xMiniBoutons = 610;
		}
		
		widthPlateau = backgroundimage.getWidth()-187;
		heightPlateau = backgroundimage.getHeight()-135;
		xPanelsInfos = backgroundimage.getWidth()-157;
		yGuide = backgroundimage.getHeight()-210;
		widthGuide = backgroundimage.getWidth()-173;
		xCompteur = backgroundimage.getWidth()/2 - 150;
		yCompteur = backgroundimage.getHeight()-780;
		
		PanelCompteur cmpt = new PanelCompteur(xCompteur, yCompteur);
		this.plateau.add(cmpt,JLayeredPane.DRAG_LAYER );
		
		// Création du panel permettant d'afficher les infos du soldat
		this.panelInfosSoldat = new PanelInfosSoldat(xPanelsInfos);
		this.plateau.add(this.panelInfosSoldat, JLayeredPane.DEFAULT_LAYER);

		// Création du panel permettant d'afficher les infos du joueur
		this.infosJoueur = new PanelInfosJoueur(joueurs, xPanelsInfos);
		this.plateau.add(this.infosJoueur, JLayeredPane.DEFAULT_LAYER);
	
		// Création des joueurs 
		this.joueurs = joueurs;
		
		this.scenario = scenario;

		// Création des labels représentant les soldats
		this.soldatVue = new SoldatVue();
		this.soldatVue.creerSoldats(this.joueurs);

		// Choix aléatoire d'un joueur pour commencer le tour
		int ind =(int) (Math.random() * (this.joueurs.size() - 0));
		this.tourJoueur = this.joueurs.get(ind);

		this.visible = true;
		
		// Création de minimap
		this.minimap = new MiniMap(this.joueurs, this.tourJoueur,this.soldatVue, this, xPanelsInfos);
		this.plateau.add(this.minimap, JLayeredPane.DEFAULT_LAYER);

		// Tutoriel du jeu
		this.guide = new Guide(yGuide, widthGuide);
		this.plateau.add(this.guide, JLayeredPane.DRAG_LAYER);
		this.guide.afficherQuestion();

		// Création du panel permettant d'afficher les terrains et de positionner les soldats
		this.panelTerrains = new PanelTerrains(this.tourJoueur, this.soldatVue, this.panelInfosSoldat, this.infosJoueur, this.guide, widthPlateau, heightPlateau);
		this.plateau.add(this.panelTerrains.getScrollPane(), JLayeredPane.DEFAULT_LAYER);
		
		scenario.appliquerScenario(this.panelTerrains.getTourJoueur());

		//scenario.appliquerScenario(this.panelTerrains.getTourJoueur());
	
		setTourJoueur(tourJoueur, ind);

		/** Panel Pause **/
		PanelPause MenuPause = new PanelPause(this.joueurs);
		this.plateau.add(MenuPause, JLayeredPane.DRAG_LAYER);
		
		MenuPause.boutonMenuPrincipal.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				MenuPrincipal menuPrincipal = new MenuPrincipal();
				menuPrincipal.show();
				dispose();
			}
		});
		
		MenuPause.boutonContinuer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuPause.setVisible(false);
				cmpt.compteur.start();
			}
		});
		
		// Affichage de bouton pause
		ImageIcon imageIconPause = new ImageIcon("images/ornate_pause_30-active.png");
		JButton boutonPause = new JButton();
		boutonPause.setIcon(imageIconPause);
		boutonPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!clicked) {
					cmpt.compteur.stop();
					ImageIcon imageIconPause = new ImageIcon("images/ornate_play_30-active.png");
					boutonPause.setIcon(imageIconPause);
					panelTerrains.retirerMouseListenerHexagones();
					clicked = true;
				}
				
				else {
					cmpt.compteur.start();
					ImageIcon imageIconPause = new ImageIcon("images/ornate_pause_30-active.png");
					boutonPause.setIcon(imageIconPause);
					panelTerrains.ajouterMouseListenerHexagones();
					clicked = false;
				}
				/*
				ornate_play_30-active
				if (visible) {
					
					visible = false;
				}
				else {
					
					visible = true;
				}
				
				*/
			}
		});
		boutonPause.setBackground(new Color(16, 22, 33));
		boutonPause.setBounds(xPanelsInfos+15, xMiniBoutons, imageIconPause.getIconWidth(), imageIconPause.getIconHeight());
		this.plateau.add(boutonPause, JLayeredPane.DEFAULT_LAYER);

    	// Bouton pour lancer le tutoriel //
        JButton boutonAide = new JButton();
        boutonAide.setIcon(new ImageIcon("images/help_30.png"));
        boutonAide.setBackground(new Color(16, 22, 33));
        boutonAide.setHorizontalTextPosition(JButton.CENTER);
        boutonAide.setBounds(boutonPause.getX()+40, xMiniBoutons, 30, 30);
        boutonAide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guide.afficherQuestion();
			}
        });
		this.plateau.add(boutonAide,  JLayeredPane.DEFAULT_LAYER);
		
		// BOUTON POUR QUITTER //
        JButton boutonQuitter = new JButton();
        boutonQuitter.setIcon(new ImageIcon("images/icons8-close-window-30.png"));
        boutonQuitter.setBackground(new Color(16, 22, 33));
        boutonQuitter.setHorizontalTextPosition(JButton.CENTER);
        boutonQuitter.setBounds(boutonAide.getX()+40, xMiniBoutons, 30, 30);
        this.plateau.add(boutonQuitter,  JLayeredPane.DEFAULT_LAYER);

		// Finir le tour
		JButton boutonFinirTour = new JButton("Finir tour");
		boutonFinirTour.setBorder(UIManager.getBorder("Button.border"));
		boutonFinirTour.setIcon(new ImageIcon("images/large-button-active.png"));
		boutonFinirTour.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		boutonFinirTour.setForeground(Color.white);
		boutonFinirTour.setHorizontalTextPosition(JButton.CENTER);
		boutonFinirTour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ind = 0;
				Joueur ancienJoueur, nouveauJoueur;
				ancienJoueur = panelTerrains.getTourJoueur();
				nouveauJoueur = ancienJoueur;
				while (nouveauJoueur == ancienJoueur) {
					ind = (int) (Math.random() * (joueurs.size() - 0));
					nouveauJoueur = joueurs.get(ind);
				}
				setTourJoueur(nouveauJoueur, ind);
			}
		});
		
		boutonFinirTour.setBounds(xPanelsInfos, yBoutonFinirTour, 140, 22);
        
		this.plateau.add(boutonFinirTour, JLayeredPane.DEFAULT_LAYER);
		
		/** Panel Fin Baitaille **/
		//PanelFinBataille fin = new PanelFinBataille(joueurs, joueurGagne);
		//this.plateau.add(fin, JLayeredPane.DRAG_LAYER);
		//PanelMenuInfos panelMenu = new PanelMenuInfos(155, 98, 544, 440);
		//this.plateau.add(panelMenu, JLayeredPane.DRAG_LAYER);
		//this.panelTerrains.retirerMouseListenerHexagones();
        
		SwingUtilities.updateComponentTreeUI(this.plateau);
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

	public ScenarioStandard getScenario() {
		return scenario;
	}

	public void setScenario(ScenarioStandard scenario) {
		this.scenario = scenario;
	}
}
