
package vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;

@SuppressWarnings("serial")
public class PanelFinBataille extends JPanel {
	private JButton boutonQuitter;
	private JButton boutonReJouer;
	public JLabel labelInfo;
	private JPanel panelInfo;		
	private JLabel labelTitre;

	public PanelFinBataille() {
		this.setBounds(155, 98, 544, 440);
		this.setBackground(new Color(16, 22, 33));
		this.setOpaque(true);
		this.setLayout(null);
		
		/** TITRE DU PANEL **/
		labelTitre = new JLabel("F\u00E9licitations (Player)");
		labelTitre.setForeground(new Color(255, 204, 0));
		labelTitre.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 20));
		labelTitre.setBounds(165, 10, 209, 41);
		this.add(labelTitre);
		
		/** AFFICHAGE DU PANEL QUI CONTIENT LES INFORMATIONS (Numéro du joueur, Nom du Joueur et son score) **/
		afficherPanelInfo();
		afficherLabelInfo();
		afficherBoutonRejouer();
		afficherBoutonQuitter();
	}		
			
	/********************************************************************/
	/** AFFICHAGE D'UN BOUTON QUI PERMET AUX JOUEURS DE QUITTER LE JEU **/
	/********************************************************************/		
	public void afficherBoutonQuitter() {
		boutonQuitter = new JButton(new ImageIcon("images/large-button-active-quitter.jpg"));
		boutonQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		boutonQuitter.setBounds(289, 370, 172, 48);
		this.add(boutonQuitter);
	}		
	
	/*************************************************************************************************/
	/** AFFICHAGE D'UN BOUTON, QUI RE-AFFICHE LE MenuPrincipal POUR RE-COMMENCER UNE AUTRE BATAILLE **/
	/*************************************************************************************************/
	public void afficherBoutonRejouer() {
		boutonReJouer = new JButton("");
		boutonReJouer.setIcon(new ImageIcon("images/large-button-active-rejouer.jpg"));
		boutonReJouer.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				MenuPrincipal menuPrincipal = new MenuPrincipal();
				menuPrincipal.show();
			}
		});
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
		int x = 40;
		String[] text = {"#Num", "Joueur", "Score"};
		for(i=0; i<3; i++) {
			labelInfo = new JLabel(text[i]);
			labelInfo.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 15));
			labelInfo.setBounds(x, 25, 70, 41);
			panelInfo.add(labelInfo);
			
			x += 150;
		}
	}
	
}		
		
		
		
