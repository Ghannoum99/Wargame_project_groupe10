package vue;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import java.util.*;
import modele.*;

/*
 * La classe PlateauVue permet d�afficher les diff�rents �l�ments du plateau 
 * : les terrains, les soldats des joueurs de la partie et le cadre du plateau.
 */

@SuppressWarnings("serial")
public class PlateauVue extends JFrame {
	private JLayeredPane plateau;
	private PanelTerrains panelTerrains;
	private SoldatVue soldatVue;
	private PanelInfosJoueur infosJoueur;
	private PanelInfosSoldat panelInfosSoldat;
	private MiniMap minimap;
	private Joueur tourJoueur;
	private ArrayList<Joueur> joueurs;
	private Guide guide;
	private boolean visible;

	public PlateauVue(ArrayList<Joueur> joueurs) {  
		// D�finition des donn�es de la fen�tre principale
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setBounds(0,0,1267,680);
		this.setSize(new Dimension(1267, 680));
		this.getContentPane().setBackground(Color.white);	
		getContentPane().setLayout(null);
		this.setVisible(true);
		this.setResizable(false);
		this.setBackground(Color.black);
		this.setTitle("WarGame");

		Dimension size = Toolkit. getDefaultToolkit(). getScreenSize();
		GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		if (device.isFullScreenSupported() && size.getHeight() <= 680) {
			device.setFullScreenWindow(this);
		} 

		this.plateau = new JLayeredPane();
		this.plateau.setLayout(null);
		this.plateau.setBounds(0,0,1267, 680);
		this.plateau.setVisible(true);
		this.plateau.setOpaque(false);
		this.add(this.plateau);

		// Image de fond
		JLabel backgroundimage = new JLabel("");
		backgroundimage.setBounds(0, 0, 1267, 680);
		backgroundimage.setIcon(new ImageIcon("images/plateau.png"));
		this.add(backgroundimage);

		// Cr�ation du panel permettant d'afficher les infos du soldat
		this.panelInfosSoldat = new PanelInfosSoldat();
		this.plateau.add(this.panelInfosSoldat, JLayeredPane.DEFAULT_LAYER);

		// Cr�ation du panel permettant d'afficher les infos du joueur
		this.infosJoueur = new PanelInfosJoueur(joueurs);
		this.plateau.add(this.infosJoueur, JLayeredPane.DEFAULT_LAYER);
	
		// Cr�ation des joueurs 
		this.joueurs = joueurs;

		// Cr�ation des labels repr�sentant les soldats
		this.soldatVue = new SoldatVue();
		this.soldatVue.creerSoldats(this.joueurs);

		// Choix al�atoire d'un joueur pour commencer le tour
		int ind =(int) (Math.random() * (this.joueurs.size() - 0));
		this.tourJoueur = this.joueurs.get(ind);

		this.visible = true;
		
		// Cr�ation de minimap
		this.minimap = new MiniMap(this.joueurs, this.tourJoueur,this.soldatVue, this);
		this.plateau.add(this.minimap, JLayeredPane.DEFAULT_LAYER);

		// Tutoriel du jeu
		this.guide = new Guide();
		this.plateau.add(this.guide, JLayeredPane.DEFAULT_LAYER);
		this.guide.afficherQuestion();

		// Cr�ation du panel permettant d'afficher les terrains et de positionner les soldats
		this.panelTerrains = new PanelTerrains(this.tourJoueur, this.soldatVue, this.panelInfosSoldat, this.infosJoueur, this.guide);
		this.plateau.add(this.panelTerrains.getScrollPane(), JLayeredPane.DEFAULT_LAYER);

		// Finir le tour
		JButton bouton = new JButton("Finir tour");
		bouton.setBorder(UIManager.getBorder("Button.border"));
		bouton.setIcon(new ImageIcon("images/large-button-active.png"));
		bouton.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		bouton.setForeground(Color.white);
		bouton.setHorizontalTextPosition(JButton.CENTER);
		bouton.addActionListener(new ActionListener() {
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
		bouton.setBounds(1150, 610, 100, 22);

		this.plateau.add(bouton, JLayeredPane.DEFAULT_LAYER);

		setTourJoueur(tourJoueur, ind);

		/** Panel Pause **/
		PanelPause MenuPause = new PanelPause(this.joueurs);
		this.plateau.add(MenuPause, JLayeredPane.DRAG_LAYER);

		// Affichage de bouton pause
		ImageIcon imageIconPause = new ImageIcon("images/ornate_pause_30-active.png");
		JButton BoutonPause = new JButton(imageIconPause);
		BoutonPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuPause.setVisible(visible);
				if (visible) {
					panelTerrains.retirerMouseListenerHexagones();
					visible = false;
				}
				else {
					panelTerrains.ajouterMouseListenerHexagones();
					visible = true;
				}
			}
		});
		BoutonPause.setBounds(1110, 610, imageIconPause.getIconWidth(), imageIconPause.getIconHeight());
		this.plateau.add(BoutonPause, JLayeredPane.DEFAULT_LAYER);

		/** Panel Fin Baitaille **/
		//PanelFinBataille fin = new PanelFinBataille(joueurs);
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

}