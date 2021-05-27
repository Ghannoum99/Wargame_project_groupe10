package vue;

import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import modele.Joueur;
import modele.Soldat;

@SuppressWarnings("serial")
public class MiniMap extends JLayeredPane {
	
	private ArrayList<Joueur> joueurs;
	private Joueur tourJoueur;
	
	public MiniMap(ArrayList<Joueur> joueurs, Joueur tourJoueur) {
		super();
		// Définition des données du panel
		this.setLayout(null);
		this.setVisible(true);
		this.setBounds(863, 35, 124, 165);
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
		
		//Récupération de la liste des joueurs
		this.joueurs = joueurs;
		
		//Récupération de tour de joueur
		this.tourJoueur = tourJoueur;

	}
	//initialisation des jlabel soldat...on a besoin d'un arraylist des jlabel soldat pour redifinir leur coordonnees et ainsi faire le rafraichissement des position des soldats dans le minimap
	public void AfficherMiniSoldat() {
		for(int i = 0; i < this.joueurs.size(); i++ ) {
			for(Soldat s : this.joueurs.get(i).getSoldatList()) {
				ImageIcon imageIconSoldat = new ImageIcon(ImageAafficher(s,this.tourJoueur));
				JLabel labelSoldat = new JLabel(imageIconSoldat);
				labelSoldat.setBounds(GetMiniX(s), GetMiniY(s), imageIconSoldat.getIconWidth(), imageIconSoldat.getIconHeight());
				this.add(labelSoldat, JLayeredPane.MODAL_LAYER);
				 
			}
		}
	}

	
	//Cette fonction permet de changer la couleur de pointeur de soldat sur le minimap selon le tour de joueur  
	public String ImageAafficher(Soldat soldat, Joueur tourJoueur) {
		String image;
		image = "images/minimap/position/enemy.png";
		
		if (tourJoueur.soldatExiste(soldat)) {
			image = "images/minimap/position/ally.png";
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


}


