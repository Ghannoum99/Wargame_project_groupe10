package vue;

import java.awt.Container;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import modele.Joueur;
import modele.Soldat;

@SuppressWarnings("serial")
public class MiniMap extends JLayeredPane {
	
	private ArrayList<Soldat> ListeSoldats;
	private ArrayList<JLabel> labelminiSoldats;
	private Joueur tourJoueur;
	private Soldat SoldatSelec;
	
	public MiniMap(ArrayList<Joueur> joueurs, Joueur tourJoueur, SoldatVue soldatVue, PlateauVue vue) {
		super();
		// Définition des données du panel
		this.setLayout(null);
		this.setVisible(true);
		this.setBounds(1110, 35, 124, 165);
		this.setOpaque(true);
		
		ImageIcon imageIconTerrain;
		JLabel label;
		
		//Affichage Mini terrain foret
		imageIconTerrain = new ImageIcon("images/minimap/miniterrains/miniforet.png");
		label = new JLabel(imageIconTerrain);
		label.setBounds(0, 0, imageIconTerrain.getIconWidth(), imageIconTerrain.getIconHeight());
		this.add(label, JLayeredPane.DEFAULT_LAYER);
		
		//Affichage Mini terrain glacier
		imageIconTerrain = new ImageIcon("images/minimap/miniterrains/miniglacier.png");
		label = new JLabel(imageIconTerrain);
		label.setBounds(62, 0, imageIconTerrain.getIconWidth(), imageIconTerrain.getIconHeight());
		this.add(label, JLayeredPane.DEFAULT_LAYER);
		
		//Affichage Mini terrain colline
		imageIconTerrain = new ImageIcon("images/minimap/miniterrains/minicolline.png");
		label = new JLabel(imageIconTerrain);
		label.setBounds(0, 65, imageIconTerrain.getIconWidth(), imageIconTerrain.getIconHeight());
		this.add(label, JLayeredPane.DEFAULT_LAYER);

		//Affichage Mini terrain forteresse
		imageIconTerrain = new ImageIcon("images/minimap/miniterrains/miniforteresse.png");
		label = new JLabel(imageIconTerrain);
		label.setBounds(62, 65, imageIconTerrain.getIconWidth(), imageIconTerrain.getIconHeight());
		this.add(label, JLayeredPane.DEFAULT_LAYER);	
		
		//Affichage Mini terrain eau profonde
		imageIconTerrain = new ImageIcon("images/minimap/miniterrains/minieauprofonde.png");
		label = new JLabel(imageIconTerrain);
		label.setBounds(0, 130, imageIconTerrain.getIconWidth(), imageIconTerrain.getIconHeight());
		this.add(label, JLayeredPane.DEFAULT_LAYER);
		
		//Initialistion de Soldat séléctioné
		this.SoldatSelec = null;
		
		//Récupération de tour de joueur
		this.tourJoueur = tourJoueur;
		
		////Récupération de la liste des soldats
		this.ListeSoldats = soldatVue.getSoldats();
		
		//Init de des jlabel soldat
		this.labelminiSoldats = new ArrayList<JLabel>() ;
		AfficherMiniSoldat();
		
	}
	

	public void AfficherMiniSoldat() {
		this.labelminiSoldats.clear();
		
			for(Soldat s : this.ListeSoldats) {
				ImageIcon imageIconSoldat = new ImageIcon(ImageAafficher(s,this.tourJoueur));
				JLabel labelSoldat = new JLabel(imageIconSoldat);
				labelSoldat.setBounds(GetMiniX(s), GetMiniY(s), imageIconSoldat.getIconWidth(), imageIconSoldat.getIconHeight());
				this.labelminiSoldats.add(labelSoldat);
				
				this.add(labelSoldat, JLayeredPane.MODAL_LAYER);	
			}
		
	}
	
	public void SupprimerMiniSoldat() {
		for (JLabel minisoldat : this.labelminiSoldats) {
			Container parent = minisoldat.getParent();
			parent.remove(minisoldat);
			parent.validate();
			parent.repaint();
		}
	}
	
	public void rafraichirMiniSoldats() {
		SupprimerMiniSoldat();
		AfficherMiniSoldat();
	}
	

	
	//Cette fonction permet de changer la couleur de pointeur de soldat sur le minimap selon le tour de joueur et le soldat selectionné  
	public String ImageAafficher(Soldat soldat, Joueur tourJoueur) {
		String image;
		
		image = "images/minimap/position/enemy.png";
		
		if (tourJoueur.soldatExiste(soldat)) {
			if (soldat == this.SoldatSelec){	
				
				image = "images/minimap/position/selected.png";
			}
			else{
				image = "images/minimap/position/ally.png";
			}
		}
		
		return image;
	}
	
	//Ces deux fonctions calcule et retourne des coordonnées réduit à partir des coordonnées réel d'un soldat
	private int GetMiniX(Soldat soldat) {
		int x = soldat.getAbscisse();
			return (x/10);
		
	}

	private int GetMiniY(Soldat soldat) {
		int y = soldat.getOrdonnees();
		return (y/10);
	}

	public void setSoldatSelec(Soldat soldatSelec) {
		SoldatSelec = soldatSelec;
	}
	public void setTourJoueur(Joueur tourJoueur) {
		this.tourJoueur = tourJoueur;
	}

	public void setListeSoldats(ArrayList<Soldat> listeSoldats) {
		ListeSoldats = listeSoldats;
	}

	
}


