package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
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
 * La classe PanelTerrains permet d'afficher toutes les pi�ces du plateau : les terrains et les soldats
 */

@SuppressWarnings("serial")
public class PanelTerrains extends JLayeredPane {

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
	private PanelInfosJoueur panelInfosJoueur;
	private JLabel labelBonusDef;
	private Guide guide;
	private int indTourJoueur;

	public PanelTerrains(Joueur tourJoueur, SoldatVue soldatVue, PanelInfosSoldat panelInfosSoldat, PanelInfosJoueur panelInfosJoueur, Guide guide) {
		// D�finition des donn�es du panel
		this.setLayout(null);
		this.setVisible(true);
		this.setPreferredSize(new Dimension(0, 0));

		// Guide
		this.guide = guide;

		// R�cup�ration du joueur commencant la partie
		this.tourJoueur = tourJoueur;

		// Cr�ation du HashMap qui contiendra les couples identifiant des hexagones et le label correspondant
		this.labelsHexagones = new HashMap<Integer, JLabel>();

		// Cr�ation d'une liste de terrains
		this.terrains = new ArrayList<Terrain>();

		// Cr�ation d'un terrain for�t
		int xImage = 0, yImage = 0, x = 0, y = 0;
		Foret terrainForet = new Foret(x, y);
		this.terrains.add(terrainForet);

		// Cr�ation des hexagones du terrain for�t
		ArrayList<Hexagone> hexagonesForet;
		hexagonesForet = ajouterTerrain(12, 9, xImage, yImage, terrainForet, 0);
		terrainForet.ajouterHexagones(hexagonesForet);

		// Cr�ation d'un terrain glacier
		x = 624; y = 0;
		Glacier terrainGlacier = new Glacier(x, y);
		this.terrains.add(terrainGlacier);

		// Cr�ation des hexagones du terrain glacier
		ArrayList<Hexagone> hexagonesGlacier;
		hexagonesGlacier = ajouterTerrain(12, 9, xImage, yImage, terrainGlacier, 0);
		terrainGlacier.ajouterHexagones(hexagonesGlacier);

		// Cr�ation d'un terrain colline
		x = 0; y = 648;
		Colline terrainColline = new Colline(x, y);
		this.terrains.add(terrainColline);

		// Cr�ation des hexagones du colline
		ArrayList<Hexagone> hexagonesColline;
		hexagonesColline = ajouterTerrain(12, 9, xImage, yImage, terrainColline, 0);
		terrainColline.ajouterHexagones(hexagonesColline);

		// Cr�ation d'un terrain montagne
		x = 624; y = 648;
		Forteresse terrainMontagne = new Forteresse(x, y);
		this.terrains.add(terrainMontagne);	

		// Cr�ation des hexagones du terrain montagne
		ArrayList<Hexagone> hexagonesMontagne;
		hexagonesMontagne = ajouterTerrain(12, 9, xImage, yImage, terrainMontagne, 0);
		terrainMontagne.ajouterHexagones(hexagonesMontagne);

		// Cr�ation d'un terrain eau profonde
		x = 0; y = 1294;
		EauProfonde terrainEauProfonde = new EauProfonde(x, y);
		this.terrains.add(terrainEauProfonde);	

		// Cr�ation des hexagones du terrain eau profonde
		ArrayList<Hexagone> hexagonesEauProfonde;
		hexagonesEauProfonde = ajouterTerrain(24, 5, xImage, yImage, terrainEauProfonde, 0);
		terrainEauProfonde.ajouterHexagones(hexagonesEauProfonde);

		// R�cup�ration de la liste des soldats cr��s
		this.soldats = soldatVue.getSoldats();

		// R�cup�ration de la liste des labels correspondant aux soldats cr��s
		this.labelsSoldats = soldatVue.getLabelsSoldats();

		// R�cup�ration du panel permettant d'afficher les informations des soldats
		this.panelInfosSoldat = panelInfosSoldat;

		// R�cup�ration du panel permettant d'afficher les informations du joueur
		this.panelInfosJoueur = panelInfosJoueur;
		
		// Initialisation des soldats s�lectionn�s
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

		// Cr�ation du scroll pane contenant le panel
		this.scrollPane = new JScrollPane(this);
		this.scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0,0));
		this.scrollPane.getHorizontalScrollBar().setPreferredSize(new Dimension(0,0));
		this.scrollPane.setBounds(10,13,1080,545);
		this.scrollPane.setPreferredSize(this.getPreferredSize());
		this.scrollPane.getHorizontalScrollBar().setValue(1);
		this.scrollPane.getVerticalScrollBar().setValue(1);

		// Cr�ation d'une cam�ra
		this.camera = new Camera(0,0);

		// Centrer la cam�ra sur la position actuelle sur le joueur
		modifierCameraJoueur();

		// Mettre � jour l'image des hexagones sous chaque label des soldats
		mettreAjourHexagonesSoldats();
		
		//afficherBrouillard();
	}

	/*
	 * Cette fonction permet de mettre � jour l'affichage des soldats
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

						if (guide.isGuideActive() && !guide.aValideCompetence(1)) {
							guide.afficherIndicationsDeplacement();
						}
						SwingUtilities.updateComponentTreeUI(guide);

						if (soldat != soldatSelec) {
							ancienSoldatSelec = soldatSelec;
						}

						labelSoldatSelec = labelSoldat;
						soldatSelec = soldat;
						mettreAjourSoldatSelec();
					}
					else {
						if (guide.isGuideActive() && !guide.aValideCompetence(5)) {
							guide.afficherFinTuto();
						}
					}
				}
				
			});
		}
	}

	/*
	 * Cette fonction permet de retourner l'image "alli�" ou "ennemi"
	 * en fonction du joueur dont c'est le tour et du soldat en param�tres
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
	 * Cette fonction permet de mettre � jour le soldat
	 * s�lectionn� par le joueur
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
	 * Cette fonction permet d'afficher l'hexagone "s�lection" sur un hexagone
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
	 * Cette fonction permet d'effacer l'hexagone "s�lection" sur un hexagone
	 */

	public void effacerImageSelec(int x, int y) {
		Hexagone hexagone = getHexagone(x, y);
		boolean effacerHexSelec = true;
		if (hexagone != null) {
			ImageIcon bordure = new ImageIcon("images/hexagones/hexagone3.png");  
			JLabel label = getLabel(hexagone.getId());
			if (this.soldatSelec != null) {
				Hexagone hexagoneSoldat = getHexagone(this.soldatSelec);
				JLabel labelHexagoneSoldat = getLabel(hexagoneSoldat.getId());
				if (label == labelHexagoneSoldat) {
					effacerHexSelec = false;
				}
			}
			if (effacerHexSelec) label.setIcon(bordure);
		}
	}

	/*
	 * Cette fonction permet de mettre � jour l'image des hexagones
	 * sur lesquelles les soldats se d�placent (on remet leur image initiale)
	 */

	public void updateSoldatHexagone() {
		Hexagone ancienHexagone = getHexagone(this.soldatSelec);
		if (ancienHexagone != null) {
			ancienHexagone.removeFromHexagone(this.soldatSelec);
			ImageIcon ancienneBordure;
			ArrayList<Soldat> soldats = ancienHexagone.getUnits();
			if (soldats.isEmpty()) {
				ancienneBordure = new ImageIcon("images/hexagones/hexagone3.png");  
			}
			else {
				ancienneBordure = new ImageIcon("images/hexagones/hexagone4.png");
			}
			JLabel labelAncienneBordure = getLabel(ancienHexagone.getId());
			labelAncienneBordure.setIcon(ancienneBordure);
		}
		afficherImageSelec(true, this.soldatSelec.getAbscisse(), this.soldatSelec.getOrdonnees());
	}

	/*
	 * Cette fonction permet d'ajouter des hexagones � un terrain
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

				// On ajoute un tooltip � l'hexagone
				ImageIcon imageToolTip = new ImageIcon("images/button.png");
				Border matteborder = BorderFactory.createMatteBorder(1, 1, 3, 1, imageToolTip);
				String titre = "Type terrain : " + terrain.getTypeTerrain() + "\n Bonus d�fense : " + terrain.getBonusDefense() + "\n Point d�placement : " + terrain.getPointDeplacement();
				UIManager.put("ToolTip.background", Color.decode("#0B2161"));
				UIManager.put("ToolTip.border", new BorderUIResource(matteborder));
				UIManager.put("ToolTip.foreground", Color.decode("#B18904"));
				labelBordure.setToolTipText(titre);

				labelBordure.addMouseListener(new MouseHexagone(labelBordure));

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
	 * Cette fonction permet de r�cup�rer un hexagone � partir de cordoonn�es 
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
	 * Cette fonction permet de r�cup�rer un hexagone � partir d'un soldat
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
	 * Cette fonction permet de r�cup�rer un label correspondant � un hexagone
	 * � partir de son identifiant
	 */

	public JLabel getLabel(int id) {
		JLabel labelAretourner = this.labelsHexagones.get(id);
		return labelAretourner;
	}

	/*
	 * Cette fonction permet de modifier les cordoonn�es du scrollPane qui correspond � la cam�ra
	 */

	public void modifierCoordonneesCamera() {
		this.scrollPane.getHorizontalScrollBar().setValue(this.camera.getOffX());
		this.scrollPane.getVerticalScrollBar().setValue(this.camera.getOffY());
	}

	/*
	 * Replacer la cam�ra sur le joueur actif
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
		this.panelInfosJoueur.NomJoueur.setText(this.tourJoueur.getNomJoueur());
		this.panelInfosJoueur.score.setText(String.valueOf((Integer)this.tourJoueur.getScore()));
		this.panelInfosJoueur.nombreSoldat.setText(String.valueOf((Integer)this.tourJoueur.getSoldatList().size()));
	}

	/*
	 * R�cup�rer les points de d�placement d'un terrain � partir de son nom 
	 */

	public int getBonusDep(String nomTerrain) {
		List<Terrain> terrainsNom = this.terrains.stream().filter(x -> x.getTypeTerrain().equals(nomTerrain)).collect(Collectors.toList());
		return terrainsNom.get(0).getPointDeplacement();
	}

	/*
	 * R�cup�rer les bonus de d�fense d'un terrain � partir de son nom 
	 */

	public int getBonusDef(String nomTerrain) {
		List<Terrain> terrainsNom = this.terrains.stream().filter(x -> x.getTypeTerrain().equals(nomTerrain)).collect(Collectors.toList());
		return terrainsNom.get(0).getBonusDefense();
	}

	public Joueur getTourJoueur() {
		return tourJoueur;
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

	public int getIndTourJoueur() {
		return indTourJoueur;
	}

	public void setIndTourJoueur(int indTourJoueur) {
		this.indTourJoueur = indTourJoueur;
	}
	
	/*
	 * Cette fonction permet d'afficher le Brouillard sur un hexagone
	 */
	public void afficherImageFog(int x, int y) {
		Hexagone hexagone = getHexagone(x, y);
		if (hexagone != null) {
			ImageIcon fog = new ImageIcon("images/hexagones/hexagone1.png");  
			JLabel fogOfWar = getLabel(hexagone.getId());
			fogOfWar.setIcon(fog);
			retirerMouseListenerHexagone(fogOfWar);
			this.add(fogOfWar, JLayeredPane.MODAL_LAYER);
			
		}
	}
	
	/*
	 * Cette fonction permet d'afficher le brouillard sur tout le terrain
	 */

	public void afficherBrouillard()
	{ 
		int i, j;
		for(i=0;i<terrains.size();i++)
		{
			for (j=0; j<terrains.get(i).getHexagones().size(); j++) {
				Hexagone hexagone = terrains.get(i).getHexagones().get(j);
				if(hexagone.getUnits().isEmpty())
                {
					afficherImageFog(hexagone.getAbscisse(), hexagone.getOrdonnees());
                }
			}
		}
		
		return;
	}

	/*
	 * Cette fonction permet de retirer tous les mouse listener des labels hexagones
	 */
	
	public void retirerMouseListenerHexagones() {
		int i, j;
		for(i=0;i<terrains.size();i++)
		{
			for (j=0; j<terrains.get(i).getHexagones().size(); j++) {
				Hexagone hexagone = terrains.get(i).getHexagones().get(j);
				JLabel label = getLabel(hexagone.getId());
				retirerMouseListenerHexagone(label);
			}
		}
		for (i=0; i<this.labelsSoldats.size(); i++) {
			retirerMouseListenerHexagone(this.labelsSoldats.get(i));
		}
	}
	
	/*
	 * Cette fonction permet de retirer tous les mouse listener d'un label hexagone
	 */
	
	public void retirerMouseListenerHexagone(JLabel labelHexagone) {
		for (MouseListener mouseL : labelHexagone.getMouseListeners()) {
			labelHexagone.removeMouseListener(mouseL);
		}
	}
	
	/*
	 * Cette fonction permet d'ajouter tous les mouse listener des labels hexagones
	 */
	
	public void ajouterMouseListenerHexagones() {
		int i, j;
		for(i=0;i<terrains.size();i++)
		{
			for (j=0; j<terrains.get(i).getHexagones().size(); j++) {
				Hexagone hexagone = terrains.get(i).getHexagones().get(j);
				JLabel label = getLabel(hexagone.getId());
				ajouterMouseListenerHexagone(label);
			}
		}
	}
	
	/*
	 * Cette fonction permet d'ajouter tous les mouse listener d'un label hexagone
	 */
	
	public void ajouterMouseListenerHexagone(JLabel labelHexagone) {
		labelHexagone.addMouseListener(new MouseHexagone(labelHexagone));
	}
	
	public class MouseHexagone extends MouseAdapter {
		
		private JLabel labelBordure;
		
		public MouseHexagone(JLabel label) {
			this.labelBordure = label;
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			if (guide.isGuideActive() && !guide.aValideCompetence(3)) {
				guide.afficherIndicationsDeplacement3();
			}
			afficherImageSelec(false, labelBordure.getX(), labelBordure.getY());
			Hexagone hexagoneClique = getHexagone(labelBordure.getX(), labelBordure.getY());
			int bonusDefense = getBonusDef(hexagoneClique.getTypeTerrain());
			labelBonusDef = new JLabel(Integer.toString(bonusDefense) + "%");
			labelBonusDef.setFont(new Font("Arial", Font.BOLD, 20));
			labelBonusDef.setForeground(new Color(231, 206, 54));
			labelBonusDef.setHorizontalAlignment(SwingConstants.CENTER);
			labelBonusDef.setBounds(labelBordure.getBounds());
			add(labelBonusDef, JLayeredPane.PALETTE_LAYER);
		}

		@Override
		public void mouseExited(MouseEvent e) {
			effacerImageSelec(labelBordure.getX(), labelBordure.getY());
			remove(labelBonusDef);
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// On affiche la partie 2 du tutoriel
			if (guide.isGuideActive() && !guide.aValideCompetence(2)) {
				guide.afficherIndicationsDeplacement2();
			}
			remove(labelBonusDef);
			Hexagone hexagone = getHexagone(labelBordure.getX(), labelBordure.getY());
			if (soldatSelec != null && !hexagone.contientEnnemi(tourJoueur)) {
				int nbrHexagones, nouveauX, nouveauY, xClic, yClic;
				nouveauX = soldatSelec.getAbscisse();
				nouveauY = soldatSelec.getOrdonnees();

				nbrHexagones = 0;
				xClic = labelBordure.getX();
				yClic = labelBordure.getY();

				if (xClic  > soldatSelec.getAbscisse() && yClic > soldatSelec.getOrdonnees()) {
					String imageDroite = soldatSelec.getImage();
					ImageIcon image = new ImageIcon(imageDroite);
					labelSoldatSelec.setIcon(image);
					nouveauX = soldatSelec.deplacementDroit(xClic - soldatSelec.getAbscisse());
					nouveauY = soldatSelec.deplacementBas(yClic - soldatSelec.getOrdonnees());
					nbrHexagones = (nouveauX-soldatSelec.getAbscisse()) / labelBordure.getWidth();
				}
				else if (xClic  < soldatSelec.getAbscisse() && yClic > soldatSelec.getOrdonnees()) {
					String imageGauche= soldatSelec.getImagePivotee();
					ImageIcon image = new ImageIcon(imageGauche);
					labelSoldatSelec.setIcon(image);
					nouveauX = soldatSelec.deplacementGauche(soldatSelec.getAbscisse() - xClic);
					nouveauY = soldatSelec.deplacementBas(yClic - soldatSelec.getOrdonnees());
					nbrHexagones = (soldatSelec.getAbscisse()-nouveauX) / labelBordure.getWidth();
				}
				else if (xClic  > soldatSelec.getAbscisse() && yClic < soldatSelec.getOrdonnees()) {
					String imageDroite = soldatSelec.getImage();
					ImageIcon image = new ImageIcon(imageDroite);
					labelSoldatSelec.setIcon(image);
					nouveauX = soldatSelec.deplacementDroit(xClic - soldatSelec.getAbscisse());
					nouveauY = soldatSelec.deplacementHaut(soldatSelec.getOrdonnees() - yClic);
					nbrHexagones = (nouveauX-soldatSelec.getAbscisse()) / labelBordure.getWidth();
				}
				else if (xClic  < soldatSelec.getAbscisse() && yClic < soldatSelec.getOrdonnees()) {
					String imageGauche= soldatSelec.getImagePivotee();
					ImageIcon image = new ImageIcon(imageGauche);
					labelSoldatSelec.setIcon(image);
					nouveauX = soldatSelec.deplacementGauche(soldatSelec.getAbscisse() - xClic);
					nouveauY = soldatSelec.deplacementHaut(soldatSelec.getOrdonnees() - yClic);
					nbrHexagones = (soldatSelec.getAbscisse()-nouveauX) / labelBordure.getWidth();
				}
				else if (yClic < soldatSelec.getOrdonnees()) {
					nouveauY = soldatSelec.deplacementHaut(soldatSelec.getOrdonnees() - yClic);
					nbrHexagones = (soldatSelec.getOrdonnees()-nouveauY) / labelBordure.getHeight();
				}
				else if (yClic > soldatSelec.getOrdonnees()) {
					nouveauY = soldatSelec.deplacementBas(yClic - soldatSelec.getOrdonnees());
					nbrHexagones = (nouveauY-soldatSelec.getOrdonnees()) / labelBordure.getHeight();
				}

				nbrHexagones += 1;

				Hexagone hexagoneClique = getHexagone(labelBordure.getX(), labelBordure.getY());
				int bonusDeplacement = getBonusDep(hexagoneClique.getTypeTerrain());

				// On v�rifie si en se d�placant le soldat se retrouve � proximit� d'un ennemi
				List<Hexagone> listeHex = new ArrayList<Hexagone>();
				for (int ind=0; ind<terrains.size(); ind++) {
					Terrain terrain = terrains.get(ind);
					for (int ind2=0; ind2<terrain.getHexagones().size(); ind2++) {
						Hexagone hex = terrain.getHexagones().get(ind2);
						if (hex.getUnits().size() > 0 && !(tourJoueur.soldatExiste(hex.getUnits().get(0))) && (hex.getAbscisse()-hexagoneClique.getAbscisse()) <= 54 && (hex.getAbscisse()-hexagoneClique.getAbscisse()) >= -54 && (hex.getOrdonnees()-hexagoneClique.getOrdonnees()) <= 72 && (hex.getOrdonnees()-hexagoneClique.getOrdonnees()) >= -72) {
							listeHex.add(hex);
						}
						
					}
				}
				if (!listeHex.isEmpty()) {
					if (guide.isGuideActive() && !guide.aValideCompetence(4)) {
						guide.afficherIndicationsCombat();
					}
				}
				
				soldatSelec.deplacementPossible(0, getWidth() - 50, 0, getHeight() - 70, nouveauX, nouveauY, nbrHexagones, bonusDeplacement);
				labelSoldatSelec.setLocation(soldatSelec.getAbscisse(), soldatSelec.getOrdonnees());
				camera.update(soldatSelec.getAbscisse(), soldatSelec.getOrdonnees(), scrollPane);
				modifierCoordonneesCamera();
				updateSoldatHexagone();
			}
		}
	}
}
