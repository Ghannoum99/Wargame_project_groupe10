package vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import controleur.JsonController;
import modele.Joueur;

@SuppressWarnings("serial")
public class PanelPause extends JPanel {
	private JLabel labelTitre;
	private controleur.JsonController json;
	public JButton boutonContinuer;
	public JButton boutonMenuPrincipal;
	private ArrayList<Joueur> joueurs;

	public PanelPause(ArrayList<Joueur> joueurs) {
		this.setBounds(155, 98, 544, 440);
		this.setBackground(new Color(16, 22, 33));
		this.setOpaque(true);
		this.setLayout(null);
<<<<<<< HEAD
		this.setVisible(true);
		this.setPreferredSize(new Dimension(0, 0));

		// Guide
		this.guide = guide;

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

		// Récupération du panel permettant d'afficher les informations du joueur
		this.panelInfosJoueur = panelInfosJoueur;

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
		this.scrollPane.setBounds(10,13,widthPlateau,heightPlateau);
		this.scrollPane.setPreferredSize(this.getPreferredSize());
		this.scrollPane.getHorizontalScrollBar().setValue(1);
		this.scrollPane.getVerticalScrollBar().setValue(1);

		// Création d'une caméra
		this.camera = new Camera(0,0);

		// Centrer la caméra sur la position actuelle sur le joueur
		modifierCameraJoueur();

		// Mettre à jour l'image des hexagones sous chaque label des soldats
		mettreAjourHexagonesSoldats();

		//afficherBrouillard();
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

			for (MouseListener mouseL : labelSoldat.getMouseListeners()) {
				labelSoldat.removeMouseListener(mouseL);
			}

			labelSoldat.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					if (tourJoueur.soldatExiste(soldat)) {
						panelInfosSoldat.afficherInfosSoldats(soldat);

						// On affiche la partie 1 du tutoriel
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
						// On affiche la partie 5 du tutoriel
						if (guide.isGuideActive() && !guide.aValideCompetence(5)) {
							guide.afficherFinTuto();
						}
						boolean attaque = possibiliteAttaque(hexagoneSelected, hexagone);
						System.out.println(attaque);
						if(attaque)  {
							System.out.println("hex deb : " + hexagone);
							int degats = diminuerpointdeviesoldat(hexagoneSelected, hexagone);
							tuersoldat(hexagone, degats);
							System.out.println("hex fin : " + hexagone);
						}
					}
				}

			});
		}
	}

	/*
	 * Fonction pour vérfier les possiblités d'attaquer
	 * on peut attaquer seulement les 6 hexagones autour du soldat
	 */
	public boolean possibiliteAttaque(Hexagone amis, Hexagone ennemi) {
		boolean result = false;
		int intervalleAbscisse = 52;
		int intervalleOrdonnee1 = 36;
		int intervalleOrdonnee2 = 34;
		int abscisseAmis = amis.getAbscisse();
		int abscisseEnnemi = ennemi.getAbscisse();
		int ordonneeAmis = amis.getOrdonnees();
		int ordonneeEnnemi = ennemi.getOrdonnees();
		if(
				//Haut gauche
				(abscisseAmis - abscisseEnnemi == intervalleAbscisse && (ordonneeAmis - ordonneeEnnemi == intervalleOrdonnee1 || ordonneeAmis - ordonneeEnnemi == intervalleOrdonnee2))
				||
				//Bas gauche
				(abscisseAmis - abscisseEnnemi == intervalleAbscisse && (ordonneeAmis + intervalleOrdonnee1 == ordonneeEnnemi || ordonneeAmis + intervalleOrdonnee2 == ordonneeEnnemi))
				||
				//Haut
				(abscisseAmis == abscisseEnnemi && (ordonneeAmis - ordonneeEnnemi == intervalleOrdonnee1 + intervalleOrdonnee1 || ordonneeAmis - ordonneeEnnemi == intervalleOrdonnee2 + intervalleOrdonnee2 || ordonneeAmis - ordonneeEnnemi == intervalleOrdonnee1 + intervalleOrdonnee2))
				||
				//Bas
				(abscisseAmis == abscisseEnnemi && (ordonneeAmis + intervalleOrdonnee1 + intervalleOrdonnee1 == ordonneeEnnemi || ordonneeAmis + intervalleOrdonnee2 + intervalleOrdonnee2 == ordonneeEnnemi || ordonneeAmis + intervalleOrdonnee1 + intervalleOrdonnee2 == ordonneeEnnemi))
				||
				//Haut droite
				(abscisseAmis + intervalleAbscisse == abscisseEnnemi && (ordonneeAmis - ordonneeEnnemi == intervalleOrdonnee1 || ordonneeAmis - ordonneeEnnemi == intervalleOrdonnee2))
				||
				//Bas droite
				(abscisseAmis + intervalleAbscisse == abscisseEnnemi && (ordonneeAmis + intervalleOrdonnee1 == ordonneeEnnemi || ordonneeAmis + intervalleOrdonnee2 == ordonneeEnnemi))
				) {
			result = true;
		}
		return result;
	}

	/*
	 * Fonction permet d'afficher un feu pour le soldat ennemi
	 * Tuer un soldat
	 */
	public void tuersoldat(Hexagone hexagone, int degats) {
		if (hexagone != null) {
			ImageIcon feu = new ImageIcon(new ImageIcon("images/feux.gif").getImage().getScaledInstance(65, 65, Image.SCALE_DEFAULT));  
			JLabel labelSoldatEnnemi = getLabel(hexagone.getId());
			labelSoldatEnnemi.setIcon(feu);
			
			JLabel labelDegats = new JLabel(Integer.toString(degats));
			labelDegats.setBounds(labelSoldatEnnemi.getX()+25, labelSoldatEnnemi.getY()-60, 100, 100);
			labelDegats.setForeground(Color.red);
			labelDegats.setFont(new Font("Arial", Font.BOLD, 16));
			labelDegats.setVisible(true);
			this.add(labelDegats, JLayeredPane.DRAG_LAYER);
		
			Soldat tue = hexagone.getUnits().get(0);
			TimerTask task = new TimerTask() {
				public void run() {
					if(tue.getPv() <= 0)
					{
						tue.setKo(true);
						hexagone.removeFromHexagone(tue);
						labelSoldatEnnemi.setIcon(new ImageIcon("images/hexagone3.png"));
						JLabel lsoldat = chercherLabelSoldat(tue);
						lsoldat.setVisible(false);
						labelsSoldats.remove(lsoldat);
						soldats.remove(tue);
						revalidate();
						remove(lsoldat);
						labelDegats.setVisible(false);
						remove(labelDegats);
						tourJoueur.ajouterSoldatTue(tue);
						//incrementer le score
						tourJoueur.setScore(tourJoueur.getScore()+1);
						panelInfosJoueur.score.setText(String.valueOf((Integer)tourJoueur.getScore()));
					}
					else {
						labelSoldatEnnemi.setIcon(new ImageIcon(imageAafficher(tue)));
					}
					
				}
			};
			Timer timer = new Timer("Timer");
			long delay = 1000L;
			timer.schedule(task, delay);
		}
	}

	/*
	 * Cette fonction permet de diminuer les points de vie d'un soldat attaqué
	 */

	public int diminuerpointdeviesoldat(Hexagone selected, Hexagone ennemi) {
		Random random = new Random();
		int max = selected.getUnits().get(0).getAttaque() - ennemi.getUnits().get(0).getDefense();
		int value = ennemi.getUnits().get(0).getPv() - (max - (random.nextInt(max + 1) + 1));
		ennemi.getUnits().get(0).setPv(value);
		return value;
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
	 * Cette fonction permet de mettre à jour l'image des hexagones
	 * sur lesquelles les soldats se déplacent (on remet leur image initiale)
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
		this.panelInfosJoueur.NomJoueur.setText(this.tourJoueur.getNomJoueur());
		this.panelInfosJoueur.score.setText(String.valueOf((Integer)this.tourJoueur.getScore()));
		this.panelInfosJoueur.nombreSoldat.setText(String.valueOf((Integer)this.tourJoueur.getSoldatList().size()));
	}

	/*
	 * Récupérer les points de déplacement d'un terrain à partir de son nom 
	 */

	public int getBonusDep(String nomTerrain) {
		List<Terrain> terrainsNom = this.terrains.stream().filter(x -> x.getTypeTerrain().equals(nomTerrain)).collect(Collectors.toList());
		return terrainsNom.get(0).getPointDeplacement();
	}

	/*
	 * Récupérer les bonus de défense d'un terrain à partir de son nom 
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

	public JLabel chercherLabelSoldat(Soldat soldat) {
		List<JLabel> chercheLabel = new ArrayList<JLabel>();
		chercheLabel.addAll(this.labelsSoldats.stream().filter(x -> Integer.parseInt(x.getName()) == soldat.getId()).collect(Collectors.toList()));
		return chercheLabel.get(0);
	}

	public class MouseHexagone extends MouseAdapter {

		private JLabel labelBordure;

		public MouseHexagone(JLabel label) {
			this.labelBordure = label;
		}

		@Override
		public void mouseEntered(MouseEvent e) {
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
			TimerTask task = new TimerTask() {
				public void run() {
					if (guide.isGuideActive() && !guide.aValideCompetence(3)) {
						guide.afficherIndicationsDeplacement3();
					}
				}
			};
			Timer timer = new Timer("Timer");
			long delay = 1000L;
			timer.schedule(task, delay);
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
=======
		this.setVisible(false);
		
		//CrÃ©ation d'un jsonController pour l'enregistrement de la partie en cours
		this.json = new JsonController();
		
		//RÃ©cupÃ©ration de la liste des joueurs
		this.joueurs = joueurs;
		
		/** TITRE DU PANEL **/
		labelTitre = new JLabel("Partie en Pause");
		labelTitre.setForeground(new Color(255, 204, 0));
		labelTitre.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 20));
		labelTitre.setBounds(200, 10, 209, 41);
		this.add(labelTitre);
		
		afficherBoutonQuitter(this);
		AfficherBoutonMenuPrincipal();
		AfficherBoutonContinuer(this);
	}	
>>>>>>> 1191b05e4c04e417c820d3026ce83e6f3fedbfa6
			
	/********************************************************************/
	/** AFFICHAGE D'UN BOUTON QUI PERMET AUX JOUEURS DE QUITTER LE JEU **/
	/********************************************************************/		
	public void afficherBoutonQuitter(PanelPause MenuPause) {
		JButton boutonQuitter = new JButton("Quitter");
		boutonQuitter.setBorder(UIManager.getBorder("Button.border"));
		boutonQuitter.setIcon(new ImageIcon("images/large-button-active.png"));
		boutonQuitter. setFont(new Font("Times New Roman", Font.PLAIN, 20));
		boutonQuitter. setForeground (Color.white);
		boutonQuitter.setBounds (100, 100, 172, 44);
		boutonQuitter.setHorizontalTextPosition(JButton.CENTER);
		boutonQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuPause.json.sauvegarde_file_json(MenuPause.joueurs);
				System.exit(0);
			}
		});
		boutonQuitter.setBounds(190, 370, 172, 48);
		this.add(boutonQuitter);
	}		
	
	
	/*************************************************************************************************/
	/** AFFICHAGE D'UN BOUTON, QUI RETOURNE AU MenuPrincipal                                        **/
	/*************************************************************************************************/
	public void AfficherBoutonMenuPrincipal() {
		boutonMenuPrincipal = new JButton("Menu Principal");
		boutonMenuPrincipal.setBorder(UIManager.getBorder("Button.border"));
		boutonMenuPrincipal.setIcon(new ImageIcon("images/large-button-active.png"));
		boutonMenuPrincipal. setFont(new Font("Times New Roman", Font.PLAIN, 20));
		boutonMenuPrincipal. setForeground (Color.white);
		boutonMenuPrincipal.setBounds (100, 100, 172, 44);
		boutonMenuPrincipal.setHorizontalTextPosition(JButton.CENTER);
		boutonMenuPrincipal.setBounds(190, 320, 172, 48);
		this.add(boutonMenuPrincipal);
	}
	
	/*************************************************************************/
	/** AFFICHAGE D'UN BOUTON QUI PERMET AUX JOUEURS DE CONTINUER LA PARTIE **/
	/*************************************************************************/		
	public void AfficherBoutonContinuer(PanelPause MenuPause) {
		boutonContinuer = new JButton("Continuer");
		boutonContinuer.setBorder(UIManager.getBorder("Button.border"));
		boutonContinuer.setIcon(new ImageIcon("images/large-button-active.png"));
		boutonContinuer. setFont(new Font("Times New Roman", Font.PLAIN, 20));
		boutonContinuer. setForeground (Color.white);
		boutonContinuer.setBounds (100, 100, 172, 44);
		boutonContinuer.setHorizontalTextPosition(JButton.CENTER);
		boutonContinuer.setBounds(190, 270, 172, 48);
		this.add(boutonContinuer);
	}


	public void setJoueurs(ArrayList<Joueur> joueurs) {
		this.joueurs = joueurs;
	}		
}	
