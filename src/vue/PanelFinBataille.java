
package vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.TitledBorder;

import modele.Joueur;

import javax.swing.border.EtchedBorder;
import java.awt.Color;

@SuppressWarnings("serial")
public class PanelFinBataille extends JPanel {
	private JButton boutonQuitter;
	private JButton boutonReJouer;
	private ArrayList<Joueur> joueurs;
	public JLabel labelInfo;
	private JPanel panelInfo;		
	private JLabel labelTitre;
	private JLabel labelScore;
	private JLabel labelNumJoueur;
	private JLabel labelNomJoueur;

	public PanelFinBataille(ArrayList<Joueur> joueurs) {
		this.setBounds(155, 98, 544, 440);
		this.setBackground(new Color(16, 22, 33));
		this.setOpaque(true);
		this.setLayout(null);
		
		this.joueurs = joueurs;
		
		/** TITRE DU PANEL **/
		labelTitre = new JLabel("F\u00E9licitations (Player)");
		labelTitre.setForeground(new Color(200, 173, 10));
		labelTitre.setFont(new Font("Times new Roman", Font.BOLD, 20));
		labelTitre.setBounds(168, 10, 209, 41);
		this.add(labelTitre);
		
		/** AFFICHAGE DU PANEL QUI CONTIENT LES INFORMATIONS (Numéro du joueur, Nom du Joueur et son score) **/
		//afficherPanelInfo();
		afficherLabelInfo();
		afficherNumJoueur();
		afficherNomJoueur();
		afficherScore();
		afficherBoutonRejouer();
		afficherBoutonQuitter();
		
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
		boutonQuitter.setBounds(289, 370, 172, 48);
		this.add(boutonQuitter);
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
		boutonReJouer.setBounds(79, 370, 172, 48);
		this.add(boutonReJouer);
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
		int x = 50;
		String[] text = {"#Num", "Joueur", "Score"};
		for(i=0; i<3; i++) {
			labelInfo = new JLabel(text[i]);
			labelInfo.setFont(new Font("Times new Roman", Font.BOLD, 17));
			labelInfo.setForeground(Color.GRAY);
			labelInfo.setBounds(x, 105, 70, 41);
			this.add(labelInfo);
			
			x += 190;
		}
	}
	
	public void afficherScore() {
		int y = 154;
		for(int i = 0; i < joueurs.size(); i++) {
			String score = String.valueOf((Integer) joueurs.get(i).getScore());
			labelScore = new JLabel();
			labelScore.setText(score);
			labelScore.setForeground(new Color(200, 173, 10));
			labelScore.setFont(new Font("Times new Roman", Font.PLAIN, 15));
			labelScore.setBounds(448, y, 77, 41);
			this.add(labelScore);
			
			y += 33;
		}
	}
	
	public void afficherNumJoueur() {
		int y = 154;
		for(int i = 0; i < joueurs.size(); i++) {
			String numJoueur = String.valueOf((Integer) i);
			labelNumJoueur = new JLabel();
			labelNumJoueur.setText(numJoueur);
			labelNumJoueur.setForeground(new Color(200, 173, 10));
			labelNumJoueur.setFont(new Font("Times new Roman", Font.PLAIN, 15));
			labelNumJoueur.setBounds(68, y, 77, 41);
			this.add(labelNumJoueur);
			
			y += 33;
		}
	}
	
	public void afficherNomJoueur() {
		int y = 154;
		for(int i = 0; i < joueurs.size(); i++) {
			labelNomJoueur = new JLabel();
			labelNomJoueur.setText(joueurs.get(i).getNomJoueur());
			labelNomJoueur.setForeground(new Color(200, 173, 10));
			labelNomJoueur.setFont(new Font("Times new Roman", Font.PLAIN, 16));
			labelNomJoueur.setBounds(239, y, 77, 41);
			this.add(labelNomJoueur);
			
			y += 33;
		}
	}

	public ArrayList<Joueur> getJoueurs() {
		return joueurs;
	}

	public void setJoueurs(ArrayList<Joueur> joueurs) {
		this.joueurs = joueurs;
	}
	
	
	
}		
		
		
