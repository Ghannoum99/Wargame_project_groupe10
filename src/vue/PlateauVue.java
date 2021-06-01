package vue;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

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
	private PanelInfosJoueur panelInfosJoueur;
	private PanelInfosSoldat panelInfosSoldat;
	private String scenario;
	private boolean termine = false;
	public Joueur gagnant = null;
	private int nombreTours = 0;
	private controleur.JsonController json;
	private MiniMap minimap;
	private Joueur tourJoueur;
	private ArrayList<Joueur> joueurs;
	private Guide guide;
	private boolean clicked = false;
	private JButton boutonQuitter;
	private JButton boutonReJouer;
	public JLabel labelInfo;
	private JPanel panelInfo;		
	private JLabel labelTitre;
	private JLabel labelScore;
	private JLabel labelNumJoueur;
	private JLabel labelNomJoueur;
	private PanelMenuInfos panelMenu;
	

	public PlateauVue(ArrayList<Joueur> joueurs, String scenario) { 
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

		int widthPlateau, heightPlateau, xPanelsInfos, yGuide, widthGuide, xCompteur, yCompteur, yMiniBoutons, heightBoutonFinirTour, yBoutonFinirTour;

		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		if (device.isFullScreenSupported() && size.getHeight() <= 720) {
			device.setFullScreenWindow(this);
			backgroundimage.setBounds(0, 0, 1267, 680);
			this.plateau.setBounds(0,0,1267, 680);
			backgroundimage.setIcon(new ImageIcon("images/plateau.png"));
			heightBoutonFinirTour = 22;
			yMiniBoutons = 600;
			yBoutonFinirTour = 640;
		} 
		else {
			backgroundimage.setBounds(0, 0, 1300, 781);
			this.plateau.setBounds(0,0,1300, 781);
			backgroundimage.setIcon(new ImageIcon("images/plateauV2.png"));
			heightBoutonFinirTour = 22;
			yMiniBoutons = 610;
			yBoutonFinirTour = 660;
		}

		widthPlateau = backgroundimage.getWidth()-187;
		heightPlateau = backgroundimage.getHeight()-135;
		xPanelsInfos = backgroundimage.getWidth()-157;
		yGuide = backgroundimage.getHeight()-210;
		widthGuide = backgroundimage.getWidth()-173;
		xCompteur = backgroundimage.getWidth()/2 - 150;
		yCompteur = backgroundimage.getHeight()-780;

		// Création du panel permettant d'afficher les infos du soldat
		this.panelInfosSoldat = new PanelInfosSoldat(xPanelsInfos);
		this.plateau.add(this.panelInfosSoldat, JLayeredPane.DEFAULT_LAYER);

		// Création du panel permettant d'afficher les infos du joueur
		this.panelInfosJoueur = new PanelInfosJoueur(joueurs, xPanelsInfos);
		this.plateau.add(this.panelInfosJoueur, JLayeredPane.DEFAULT_LAYER);

		// Création des joueurs 
		this.joueurs = joueurs;

		this.scenario = scenario;

		// Création des labels représentant les soldats
		this.soldatVue = new SoldatVue();
		this.soldatVue.creerSoldats(this.joueurs);

		// Choix aléatoire d'un joueur pour commencer le tour
		int ind =(int) (Math.random() * (this.joueurs.size() - 0));
		this.tourJoueur = this.joueurs.get(0);

		// Création de minimap
		this.minimap = new MiniMap(this.joueurs, this.tourJoueur,this.soldatVue, this, xPanelsInfos);
		this.plateau.add(this.minimap, JLayeredPane.DEFAULT_LAYER);

		// Tutoriel du jeu
		this.guide = new Guide(yGuide, widthGuide);
		this.plateau.add(this.guide, JLayeredPane.DRAG_LAYER);
		this.guide.afficherQuestion();

		// Création du panel permettant d'afficher les terrains et de positionner les soldats
		this.panelTerrains = new PanelTerrains(this.tourJoueur, this.soldatVue, this.panelInfosSoldat, this.panelInfosJoueur, this.guide, widthPlateau, heightPlateau);
		this.plateau.add(this.panelTerrains.getScrollPane(), JLayeredPane.DEFAULT_LAYER);

		/** Panel Pause **/
		PanelQuitter MenuPause = new PanelQuitter(this.joueurs);
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
			}
		});

		// Affichage de bouton pause
		ImageIcon imageIconPause = new ImageIcon("images/ornate_pause_30-active.png");
		JButton boutonPause = new JButton();
		boutonPause.setIcon(imageIconPause);
		boutonPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(!clicked) {
					ImageIcon imageIconPause = new ImageIcon("images/ornate_play_30-active.png");
					boutonPause.setIcon(imageIconPause);
					panelTerrains.retirerMouseListenerHexagones();
					clicked = true;
				}

				else {
					ImageIcon imageIconPause = new ImageIcon("images/ornate_pause_30-active.png");
					boutonPause.setIcon(imageIconPause);
					panelTerrains.ajouterMouseListenerHexagones();
					clicked = false;
				}
			}
		});
		boutonPause.setBackground(new Color(16, 22, 33));
		boutonPause.setBounds(xPanelsInfos+15, yMiniBoutons, imageIconPause.getIconWidth(), imageIconPause.getIconHeight());
		this.plateau.add(boutonPause, JLayeredPane.DEFAULT_LAYER);

		// Bouton pour lancer le tutoriel //
		JButton boutonAide = new JButton();
		boutonAide.setIcon(new ImageIcon("images/help_30.png"));
		boutonAide.setBackground(new Color(16, 22, 33));
		boutonAide.setHorizontalTextPosition(JButton.CENTER);
		boutonAide.setBounds(boutonPause.getX()+40, yMiniBoutons, 30, 30);
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
		boutonQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		boutonQuitter.setHorizontalTextPosition(JButton.CENTER);
		boutonQuitter.setBounds(boutonAide.getX()+40, yMiniBoutons, 30, 30);
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
				if (!verifGagnant(xCompteur, yCompteur)) {
					int ind = 0;
					Joueur ancienJoueur, nouveauJoueur;
					ancienJoueur = panelTerrains.getTourJoueur();
					nouveauJoueur = ancienJoueur;
					while (nouveauJoueur == ancienJoueur) {
						ind = (int) (Math.random() * (joueurs.size() - 0));
						nouveauJoueur = joueurs.get(ind);
					}
					setTourJoueur(nouveauJoueur, ind);
					nombreTours++;
				}
			}
		});

		boutonFinirTour.setBounds(xPanelsInfos, yBoutonFinirTour, 140, heightBoutonFinirTour);

		this.plateau.add(boutonFinirTour, JLayeredPane.DEFAULT_LAYER);
	
		SwingUtilities.updateComponentTreeUI(this.plateau);
	}

	public boolean verifGagnant(int xCompteur, int yCompteur) {
		switch(this.scenario) {
		case "scenarioStandard" :
			ScenarioStandard scenarioStandard = new ScenarioStandard(joueurs);
			if(scenarioStandard.appliquerScenario(getTourJoueur()) != null)
			{
				gagnant = scenarioStandard.appliquerScenario(getTourJoueur());
				termine = true;
			}
			break;

		case "scenarioTempsLimite" :
			ScenarioTempsLimite scenarioTempsLimite = new ScenarioTempsLimite(joueurs);
			PanelCompteur cmpt = new PanelCompteur(xCompteur, yCompteur);
			this.plateau.add(cmpt,JLayeredPane.DRAG_LAYER );
			/*if (cmpt.minute != 0 && cmpt.seconde != 0) {

					if(!clicked) {
						cmpt.compteur.stop();
					}
					else {
						cmpt.compteur.start();
					}
				}
				else {
					termine = true;
				}
			}*/
			break;

		case "scenarioTourLimite" :
			ScenarioTourLimite scenarioTourLimite = new ScenarioTourLimite(joueurs);
			if(scenarioTourLimite.appliquerScenarioTourLimite(getTourJoueur(), nombreTours, gagnant))
			{
				termine = true;
			}			
			break;	
		}

		if (termine) {
			afficherPanelFinBataille();
			panelTerrains.retirerMouseListenerHexagones(); 
		}

		return termine;
	}
	
	/********************************************************************/
	/** AFFICHAGE D'UN BOUTON QUI PERMET AUX JOUEURS DE QUITTER LE JEU **/
	/********************************************************************/		
	public void afficherBoutonQuitter() {
		boutonQuitter = new JButton(new ImageIcon("images/large-button-active.png"));
		boutonQuitter.setText("Quitter");
		boutonQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		boutonQuitter.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		boutonQuitter.setForeground(Color.white);
		boutonQuitter.setHorizontalTextPosition(JButton.CENTER);
		boutonQuitter.setBounds(296, 380, 172, 48);
		panelMenu.add(boutonQuitter);
	}		
	
	/*************************************************************************************************/
	/** AFFICHAGE D'UN BOUTON, QUI RE-AFFICHE LE MenuPrincipal POUR RE-COMMENCER UNE AUTRE BATAILLE **/
	/*************************************************************************************************/
	public void afficherBoutonRejouer() {
		boutonReJouer = new JButton();
		boutonReJouer.setText("Re-Jouer");
		boutonReJouer.setIcon(new ImageIcon("images/large-button-active.png"));
		boutonReJouer.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				MenuPrincipal menuPrincipal = new MenuPrincipal();
				menuPrincipal.show();
			}
		});
		boutonReJouer.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		boutonReJouer.setForeground(Color.white);
		boutonReJouer.setHorizontalTextPosition(JButton.CENTER);
		boutonReJouer.setBounds(96, 380, 172, 48);
		panelMenu.add(boutonReJouer);
	}	
	
	/****************************************************************************************/
	/** AFFICHAGE D'UN PANEL, QUI CONTIENT LES INFORMATIONS (NOM DES JOUEURS, LEUR SCORES) **/
	/****************************************************************************************/		
	public void afficherPanelInfo() {
		panelInfo = new JPanel();
		panelInfo.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Scores", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelInfo.setBounds(59, 84, 424, 263);
		this.add(panelInfo);
		panelInfo.setLayout(null);
	}
	
	/******************************************************************/
	/** AFFICHAGE DE 3 LABELS, CHAQUE LABEL CORRESPOND A UNE COLONEE **/
	/******************************************************************/
	public void afficherLabelInfo() {
		int i = 0;
		int x = 70;
		String[] text = {"#Num", "Joueur", "Score"};
		for(i=0; i<3; i++) {
			labelInfo = new JLabel(text[i]);
			labelInfo.setFont(new Font("Times new Roman", Font.BOLD, 17));
			labelInfo.setForeground(Color.GRAY);
			labelInfo.setBounds(x, 165, 70, 41);
			panelMenu.add(labelInfo);
			
			x += 190;
		}
	}
	
	public void afficherScore() {
		int y = 214;
		for(int i = 0; i < joueurs.size(); i++) {
			String score = String.valueOf((Integer) joueurs.get(i).getScore());
			labelScore = new JLabel();
			labelScore.setText(score);
			labelScore.setForeground(new Color(200, 173, 10));
			labelScore.setFont(new Font("Times new Roman", Font.PLAIN, 15));
			labelScore.setBounds(468, y, 77, 41);
			panelMenu.add(labelScore);
			
			y += 33;
		}
	}
	
	public void afficherNumJoueur() {
		int y = 214;
		for(int i = 0; i < joueurs.size(); i++) {
			String numJoueur = String.valueOf((Integer) i);
			labelNumJoueur = new JLabel();
			labelNumJoueur.setText(numJoueur);
			labelNumJoueur.setForeground(new Color(200, 173, 10));
			labelNumJoueur.setFont(new Font("Times new Roman", Font.PLAIN, 15));
			labelNumJoueur.setBounds(88, y, 77, 41);
			panelMenu.add(labelNumJoueur);
			
			y += 33;
		}
	}
	
	public void afficherNomJoueur() {
		int y = 214;
		for(int i = 0; i < joueurs.size(); i++) {
			labelNomJoueur = new JLabel();
			labelNomJoueur.setText(joueurs.get(i).getNomJoueur());
			labelNomJoueur.setForeground(new Color(200, 173, 10));
			labelNomJoueur.setFont(new Font("Times new Roman", Font.PLAIN, 16));
			labelNomJoueur.setBounds(259, y, 77, 41);
			panelMenu.add(labelNomJoueur);
			
			y += 33;
		}
	}
	
	
	public void afficherPanelFinBataille() {
		panelMenu = new PanelMenuInfos(290, 80, 596, 480);
		this.plateau.add(panelMenu, JLayeredPane.DRAG_LAYER);
		
		/** TITRE DU PANEL **/
		String felicitation = "F�licitations" + " " + gagnant.getNomJoueur();
		labelTitre = new JLabel(felicitation);
		labelTitre.setForeground(new Color(200, 173, 10));
		labelTitre.setFont(new Font("Times new Roman", Font.BOLD, 20));
		labelTitre.setBounds(215, 120, 209, 41);
		panelMenu.add(labelTitre);
		
		/** AFFICHAGE DU PANEL QUI CONTIENT LES INFORMATIONS (Numéro du joueur, Nom du Joueur et son score) **/
		//afficherPanelInfo();
		afficherLabelInfo();
		afficherNumJoueur();
		afficherNomJoueur();
		afficherScore();
		afficherBoutonRejouer();
		afficherBoutonQuitter();
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

	public String getScenario() {
		return scenario;
	}

	public void setScenario(String scenario) {
		this.scenario = scenario;
	}

	public ArrayList<Joueur> getJoueurs() {
		return joueurs;
	}

	public void setJoueurs(ArrayList<Joueur> joueurs) {
		this.joueurs = joueurs;
	}


}
