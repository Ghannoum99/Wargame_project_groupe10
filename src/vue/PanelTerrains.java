package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.plaf.BorderUIResource;
import modele.Camera;
import modele.Colline;
import modele.EauProfonde;
import modele.Foret;
import modele.Glacier;
import modele.Hexagone;
import modele.Joueur;
import modele.Forteresse;
import modele.Soldat;
import modele.Terrain;

/*
 * La classe PanelTerrains permet d'afficher toutes les pièces du plateau : les terrains et les soldats
 */

@SuppressWarnings("serial")
public class PanelTerrains extends JLayeredPane implements KeyListener {

	private Soldat soldatSelec, ancienSoldatSelec;
	private JLabel labelSoldatSelec;
	private ArrayList<JLabel> labelsSoldats;
	private Camera camera;
	private JScrollPane scrollPane;
	private ArrayList<Terrain> terrains;
	private ArrayList<Soldat> soldats;
	private Map<Integer, JLabel> labelsHexagones;
	private Joueur tourJoueur;
	private PanelInfosSoldat panelInfosSoldat;

	public PanelTerrains(Joueur tourJoueur, SoldatVue soldatVue, PanelInfosSoldat panelInfosSoldat) {
		super();
		// Définition des données du panel
		this.setLayout(null);
		this.setVisible(true);
		this.setPreferredSize(new Dimension(0, 0));
		this.addKeyListener(this);

		// Récupération du joueur commencant la partie
		this.tourJoueur = tourJoueur;

		// Création du HashMap qui contiendra les couples identifiant des hexagones et le label correspondant
		this.labelsHexagones = new HashMap<Integer, JLabel>();

		// Création d'une liste de terrains
		this.terrains = new ArrayList<Terrain>();

		// Création d'un terrain forêt
		int xImage = 0, yImage = 0, x = 0, y = 0;
		Foret terrainForet = new Foret(x, y);
		this.terrains.add(terrainForet);

		// Création des hexagones du terrain forêt
		ArrayList<Hexagone> hexagonesForet;
		hexagonesForet = ajouterTerrain(12, 9, xImage, yImage, terrainForet, 0);
		terrainForet.ajouterHexagones(hexagonesForet);

		// Création d'un terrain glacier
		x = 624; y = 0;
		Glacier terrainGlacier = new Glacier(x, y);
		this.terrains.add(terrainGlacier);

		// Création des hexagones du terrain glacier
		ArrayList<Hexagone> hexagonesGlacier;
		hexagonesGlacier = ajouterTerrain(12, 9, xImage, yImage, terrainGlacier, 0);
		terrainGlacier.ajouterHexagones(hexagonesGlacier);

		// Création d'un terrain colline
		x = 0; y = 648;
		Colline terrainColline = new Colline(x, y);
		this.terrains.add(terrainColline);

		// Création des hexagones du colline
		ArrayList<Hexagone> hexagonesColline;
		hexagonesColline = ajouterTerrain(12, 9, xImage, yImage, terrainColline, 0);
		terrainColline.ajouterHexagones(hexagonesColline);

		// Création d'un terrain montagne
		x = 624; y = 648;
		Forteresse terrainMontagne = new Forteresse(x, y);
		this.terrains.add(terrainMontagne);	

		// Création des hexagones du terrain montagne
		ArrayList<Hexagone> hexagonesMontagne;
		hexagonesMontagne = ajouterTerrain(12, 9, xImage, yImage, terrainMontagne, 0);
		terrainMontagne.ajouterHexagones(hexagonesMontagne);

		// Création d'un terrain eau profonde
		x = 0; y = 1294;
		EauProfonde terrainEauProfonde = new EauProfonde(x, y);
		this.terrains.add(terrainEauProfonde);	

		// Création des hexagones du terrain eau profonde
		ArrayList<Hexagone> hexagonesEauProfonde;
		hexagonesEauProfonde = ajouterTerrain(24, 5, xImage, yImage, terrainEauProfonde, 0);
		terrainEauProfonde.ajouterHexagones(hexagonesEauProfonde);

		// Récupération de la liste des soldats créés
		this.soldats = soldatVue.getSoldats();

		// Récupération de la liste des labels correspondant aux soldats créés
		this.labelsSoldats = soldatVue.getLabelsSoldats();

		// Récupération du panel permettant d'afficher les informations des soldats
		this.panelInfosSoldat = panelInfosSoldat;

		// Initialisation des soldats sélectionnés
		this.labelSoldatSelec = null;
		this.soldatSelec = null;

		// Ajout des labels soldat au panel
		for (int i = 0; i<this.labelsSoldats.size(); i++) {
			JLabel labelSoldat = this.labelsSoldats.get(i);
			Soldat soldat = this.soldats.get(i);
			this.add(labelSoldat, JLayeredPane.MODAL_LAYER);
			Hexagone hexagone = getHexagone(soldat.getAbscisse(), soldat.getOrdonnees());
			hexagone.addInHexagone(soldat);
		}

		// Création du scroll pane contenant le panel
		this.scrollPane = new JScrollPane(this);
		this.scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0,0));
		this.scrollPane.getHorizontalScrollBar().setPreferredSize(new Dimension(0,0));
		this.scrollPane.setBounds(10,34,835,570);
		this.scrollPane.setPreferredSize(this.getPreferredSize());
		this.scrollPane.getHorizontalScrollBar().setValue(1);
		this.scrollPane.getVerticalScrollBar().setValue(1);

		// Création d'une caméra
		this.camera = new Camera(0,0);

		// Centrer la caméra sur la position actuelle sur le joueur
		modifierCameraJoueur();
		
		// Mettre à jour l'image des hexagones sous chaque label des soldats
		mettreAjourHexagonesSoldats();
	}

	/*
	 * Cette fonction permet de mettre à jour l'affichage des soldats
	 * sur le plateau, en fonction du joueur donc c'est le tour
	 */
	
	public void mettreAjourHexagonesSoldats() {
		for (int i=0; i<this.soldats.size(); i++) {
			JLabel labelSoldat = this.labelsSoldats.get(i);
			Soldat soldat = this.soldats.get(i);

			Hexagone hexagone = getHexagone(soldat);
			ImageIcon bordure = new ImageIcon(imageAafficher(soldat));  
			JLabel label = getLabel(hexagone.getId());
			label.setIcon(bordure);

			labelSoldat.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					if (tourJoueur.soldatExiste(soldat)) {
						panelInfosSoldat.afficherInfosSoldats(soldat);

						if (soldat != soldatSelec) {
							ancienSoldatSelec = soldatSelec;
						}

						labelSoldatSelec = labelSoldat;
						soldatSelec = soldat;
						mettreAjourSoldatSelec();
					}
				}
			});
		}
	}

	/*
	 * Cette fonction permet de retourner l'image "allié" ou "ennemi"
	 * en fonction du joueur dont c'est le tour et du soldat en paramètres
	 */
	
	public String imageAafficher(Soldat soldat) {
		String image;
		image = "images/hexagones/hexagone2.png";
		
		if (tourJoueur.soldatExiste(soldat)) {
			image = "images/hexagones/hexagone4.png";
		}
		
		return image;
	}

	/*
	 * Cette fonction permet de mettre à jour le soldat
	 * sélectionné par le joueur
	 */
	
	public void mettreAjourSoldatSelec() {
		if (this.ancienSoldatSelec != null) {
			Hexagone hexagoneAncienSoldat = getHexagone(this.ancienSoldatSelec);
			ImageIcon bordure = new ImageIcon(imageAafficher(this.ancienSoldatSelec));  
			JLabel label = getLabel(hexagoneAncienSoldat.getId());
			label.setIcon(bordure);
			this.ancienSoldatSelec = null;
		}
		afficherImageSelec(false, this.soldatSelec.getAbscisse(), this.soldatSelec.getOrdonnees());
	}

	/*
	 * Cette fonction permet d'afficher l'hexagone "sélection" sur un hexagone
	 */
	
	public void afficherImageSelec(boolean deplacement, int x, int y) {
		Hexagone hexagone = getHexagone(x, y);
		if (hexagone != null) {
			ImageIcon bordure = new ImageIcon("images/hexagones/hexagone6.png");  
			JLabel label = getLabel(hexagone.getId());
			label.setIcon(bordure);
			if (deplacement) {
				this.soldatSelec.setAbscisse(hexagone.getAbscisse());
				this.soldatSelec.setOrdonnees(hexagone.getOrdonnees());
				hexagone.addInHexagone(this.soldatSelec);
			}
		}
	}
	
	/*
	 * Cette fonction permet d'effacer l'hexagone "sélection" sur un hexagone
	 */
	
	public void effacerImageSelec(int x, int y) {
		Hexagone hexagone = getHexagone(x, y);
		if (hexagone != null) {
			ImageIcon bordure = new ImageIcon("images/hexagones/hexagone3.png");  
			JLabel label = getLabel(hexagone.getId());
			label.setIcon(bordure);
		}
	}
	
	/*
	 * Cette fonction permet de mettre à jour l'image des hexagones
	 * sur lesquelles les soldats se déplacent (on remet leur image initiale)
	 */

	public void updateSoldatHexagone() {
		Hexagone ancienHexagone = getHexagone(this.soldatSelec);
		if (ancienHexagone != null) {
			ancienHexagone.removeFromHexagone(this.soldatSelec);
			ImageIcon ancienneBordure;
			if (ancienHexagone.getUnits().size() == 0) {
				ancienneBordure = new ImageIcon("images/hexagones/hexagone3.png");  
			}
			else ancienneBordure = new ImageIcon("images/hexagones/hexagone4.png");  
			JLabel labelAncienneBordure = getLabel(ancienHexagone.getId());
			labelAncienneBordure.setIcon(ancienneBordure);
		}
		afficherImageSelec(true, this.soldatSelec.getAbscisse(), this.soldatSelec.getOrdonnees());
	}

	/*
	 * Cette fonction permet d'ajouter des hexagones à un terrain
	 */
	
	public ArrayList<Hexagone> ajouterTerrain(int nbrHexagoneX, int nbrHexagoneY, int xImage, int yImage, Terrain terrain, int ordre) {
		int x, y, xBordure, yBordure, oldY;
		int i = 0, j = 0;

		x = terrain.getAbscisse();
		y = terrain.getOrdonnees();
		oldY = y;

		// Affichage de l'image du terrain
		ImageIcon imageIconTerrain = new ImageIcon(terrain.getImage());
		JLabel label = new JLabel(imageIconTerrain);
		label.setBounds(x, y, imageIconTerrain.getIconWidth(), imageIconTerrain.getIconHeight());
		this.add(label, JLayeredPane.DEFAULT_LAYER);

		ArrayList<Hexagone> hexagones = new ArrayList<Hexagone>();

		for (i=0; i<nbrHexagoneX; i++) {
			yImage = 0;
			y = oldY;

			xBordure = x;
			yBordure = y;


			if (ordre%2 != 0) {
				yBordure += 36;
			}

			for (j=0; j<nbrHexagoneY; j++) {
				Hexagone hexagone = new Hexagone(xBordure, yBordure, terrain.getTypeTerrain());
				hexagones.add(hexagone);

				// On affiche le contour de l'hexagone
				ImageIcon imageBordure = new ImageIcon("images/hexagones/hexagone3.png");
				JLabel labelBordure = new JLabel(imageBordure);
				labelBordure.setBounds(xBordure, yBordure, imageBordure.getIconWidth(), imageBordure.getIconHeight());

				// On ajoute un tooltip à l'hexagone
				ImageIcon imageToolTip = new ImageIcon("images/button.png");
				Border matteborder = BorderFactory.createMatteBorder(1, 1, 3, 1, imageToolTip);
				String titre = "Type terrain : " + terrain.getTypeTerrain() + "\n Bonus défense : " + terrain.getBonusDefense() + "\n Point déplacement : " + terrain.getPointDeplacement();
				UIManager.put("ToolTip.background", Color.decode("#0B2161"));
				UIManager.put("ToolTip.border", new BorderUIResource(matteborder));
				UIManager.put("ToolTip.foreground", Color.decode("#B18904"));
				labelBordure.setToolTipText(titre);

				labelBordure.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						afficherImageSelec(false, labelBordure.getX(), labelBordure.getY());
					}
					
					@Override
					public void mouseExited(MouseEvent e) {
						effacerImageSelec(labelBordure.getX(), labelBordure.getY());
					}
					
					@Override
					public void mouseClicked(MouseEvent e) {
						
					}
				});
				
				this.add(labelBordure, JLayeredPane.PALETTE_LAYER);

				labelsHexagones.put(hexagone.getId(), labelBordure);

				yImage += 73;
				y += 72;
				yBordure += 72;
			}
			xImage += 52;
			x += 52;
			ordre++;
		}
		this.setPreferredSize(new Dimension(this.getWidth() + (53 * nbrHexagoneX), this.getHeight() + (329 * nbrHexagoneY)));
		return hexagones;
	}

	/*
	 * Cette fonction permet de récupérer un hexagone à partir de cordoonnées 
	 */
	
	public Hexagone getHexagone(int x, int y) {
		Hexagone hexagone = null;
		for (Terrain terrain : this.terrains) {
			if (terrain.getHexagone(x, y).size() > 0) {
				hexagone = terrain.getHexagone(x, y).get(0);
			}
		}
		return hexagone;
	}

	/*
	 * Cette fonction permet de récupérer un hexagone à partir d'un soldat
	 */
	
	public Hexagone getHexagone(Soldat soldat) {
		Hexagone hexagone = null;
		for (Terrain terrain : this.terrains) {
			if (terrain.getHexagone(soldat).size() > 0) {
				hexagone = terrain.getHexagone(soldat).get(0);
			}
		}
		return hexagone;
	}

	/*
	 * Cette fonction permet de récupérer un label correspondant à un hexagone
	 * à partir de son identifiant
	 */
	
	public JLabel getLabel(int id) {
		JLabel labelAretourner = this.labelsHexagones.get(id);
		return labelAretourner;
	}
	
	/*
	 * Cette fonction permet de gérer les déplacements des soldats
	 */

	@Override
	public void keyPressed(KeyEvent event) {
		if (this.soldatSelec != null) {
			int x, y;
			x = this.soldatSelec.getAbscisse();
			y = this.soldatSelec.getOrdonnees();

			if (event.getKeyCode() == KeyEvent.VK_UP) {
				y = this.soldatSelec.deplacementHaut(70);
			}
			if (event.getKeyCode() == KeyEvent.VK_DOWN) {
				y = this.soldatSelec.deplacementBas(74);
			}
			if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
				x = this.soldatSelec.deplacementDroit(52);
				y = this.soldatSelec.deplacementBas(36);
			}
			if (event.getKeyCode() == KeyEvent.VK_LEFT) {
				x = this.soldatSelec.deplacementGauche(52);
				y = this.soldatSelec.deplacementBas(36);
			}
			this.soldatSelec.deplacementPossible(0, this.getWidth() - 50, 0, this.getHeight() - 70, x, y);
			this.labelSoldatSelec.setLocation(this.soldatSelec.getAbscisse(), this.soldatSelec.getOrdonnees());
			this.camera.update(this.soldatSelec.getAbscisse(), this.soldatSelec.getOrdonnees(), this.scrollPane);
			modifierCoordonneesCamera();
			updateSoldatHexagone();
		}
	}
	
	/*
	 * Cette fonction permet de modifier les cordoonnées du scrollPane qui correspond à la caméra
	 */

	public void modifierCoordonneesCamera() {
		this.scrollPane.getHorizontalScrollBar().setValue(this.camera.getOffX());
		this.scrollPane.getVerticalScrollBar().setValue(this.camera.getOffY());
	}
	
	/*
	 * Replacer la caméra sur le joueur actif
	 */

	public void modifierCameraJoueur() {
		int ind = (int) (Math.random() * (this.tourJoueur.getSoldatList().size() - 0));
		Soldat soldat = this.tourJoueur.getSoldatList().get(ind);
		this.camera.update(soldat.getAbscisse(), soldat.getOrdonnees(), this.scrollPane);
		modifierCoordonneesCamera();
	}

	/*
	 * Cette fonction permet de modifier le tour du joueur
	 */
	
	public void setTourJoueur(Joueur tourJoueur) {
		this.tourJoueur = tourJoueur;
		modifierCameraJoueur();
		mettreAjourHexagonesSoldats();
		this.soldatSelec = null;
		this.labelSoldatSelec = null;
	}
	
	public Joueur getTourJoueur() {
		return tourJoueur;
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	public Soldat getSoldatSelec() {
		return soldatSelec;
	}

	public void setSoldatSelec(Soldat soldatSelec) {
		this.soldatSelec = soldatSelec;
	}

	public JLabel getLabelSoldatSelec() {
		return labelSoldatSelec;
	}

	public void setLabelSoldatSelec(JLabel labelSoldatSelec) {
		this.labelSoldatSelec = labelSoldatSelec;
	}

	public Camera getCamera() {
		return camera;
	}

	public void setCamera(Camera camera) {
		this.camera = camera;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}

	public ArrayList<Terrain> getTerrains() {
		return terrains;
	}

	public void setTerrains(ArrayList<Terrain> terrains) {
		this.terrains = terrains;
	}

	public ArrayList<Soldat> getSoldats() {
		return soldats;
	}

	public void setSoldats(ArrayList<Soldat> soldats) {
		this.soldats = soldats;
	}

}
