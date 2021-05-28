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

import modele.Joueur;

@SuppressWarnings("serial")
public class PanelPause extends JPanel {
	private JLabel labelTitre;
	private controleur.JsonController json;
	private ArrayList<Joueur> joueurs;

	public PanelPause(ArrayList<Joueur> joueurs) {
		this.setBounds(155, 98, 544, 440);
		this.setBackground(new Color(16, 22, 33));
		this.setOpaque(true);
		this.setLayout(null);
		this.setVisible(false);
		
		//Création d'un jsonController pour l'enregistrement de la partie en cours
		this.json = new controleur.JsonController();
		
		//Récupération de la liste des joueurs
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
		JButton boutonMenuPrincipal = new JButton("Menu Principal");
		boutonMenuPrincipal.setBorder(UIManager.getBorder("Button.border"));
		boutonMenuPrincipal.setIcon(new ImageIcon("images/large-button-active.png"));
		boutonMenuPrincipal. setFont(new Font("Times New Roman", Font.PLAIN, 20));
		boutonMenuPrincipal. setForeground (Color.white);
		boutonMenuPrincipal.setBounds (100, 100, 172, 44);
		boutonMenuPrincipal.setHorizontalTextPosition(JButton.CENTER);
		boutonMenuPrincipal.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				MenuPrincipal menuPrincipal = new MenuPrincipal();
				menuPrincipal.show();
			}
		});
		boutonMenuPrincipal.setBounds(190, 320, 172, 48);
		this.add(boutonMenuPrincipal);
	}
	
	/*************************************************************************/
	/** AFFICHAGE D'UN BOUTON QUI PERMET AUX JOUEURS DE CONTINUER LA PARTIE **/
	/*************************************************************************/		
	public void AfficherBoutonContinuer(PanelPause MenuPause) {
		JButton boutonContinuer = new JButton("Continuer");
		boutonContinuer.setBorder(UIManager.getBorder("Button.border"));
		boutonContinuer.setIcon(new ImageIcon("images/large-button-active.png"));
		boutonContinuer. setFont(new Font("Times New Roman", Font.PLAIN, 20));
		boutonContinuer. setForeground (Color.white);
		boutonContinuer.setBounds (100, 100, 172, 44);
		boutonContinuer.setHorizontalTextPosition(JButton.CENTER);
		boutonContinuer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuPause.setVisible(false);
			}
		});
		boutonContinuer.setBounds(190, 270, 172, 48);
		this.add(boutonContinuer);
	}


	public void setJoueurs(ArrayList<Joueur> joueurs) {
		this.joueurs = joueurs;
	}		
}	
