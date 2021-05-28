package vue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.event.ActionEvent;
import modele.Joueur;


@SuppressWarnings("serial")
public class MenuScenario extends JFrame {

	private JPanel contentPane;
	private JPanel panelPrincipal ;
	private JButton boutonLeft;
	private JButton boutonRight;
	private JLabel backgroundimage;
	private PanelScenario panelScroll;
	private boolean choix;
	private ArrayList<Joueur> joueurs;

	
	public MenuScenario(boolean choix, ArrayList<Joueur> joueurs) {
		setTitle("WarGame");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 1300, 781);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		this.choix = choix;
		this.joueurs = joueurs;
		
		afficherPanelPrincipal();
		
		/** Creation d'un panel Scénario qui contient tous les images des scenarios possibles **/
		panelScroll = new PanelScenario();
		panelPrincipal.add(panelScroll);
		
		for(int i = 0; i < panelScroll.listeBoutons.size(); i++) {
			panelScroll.listeBoutons.get(i).addActionListener(new ActionListener() {
				@SuppressWarnings("deprecation")
				public void actionPerformed(ActionEvent e) {
					PlateauVue plateau = new PlateauVue(joueurs);
					plateau.show();
					dispose();
				}
			});
		}
		
		
		/** BOUTON RETOUR **/
		PanelBoutonRetour panelBouton = new PanelBoutonRetour();
		panelPrincipal.add(panelBouton);
		panelBouton.boutonRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				retourner();
			}
		});
		
		afficherBoutonGoLeft();
		afficherBoutonGoRight();
		
		/** BACKGROUND **/
		backgroundimage = new JLabel("");
		backgroundimage.setBounds(0, 0, 1296, 767);
		panelPrincipal.add(backgroundimage);
		backgroundimage.setIcon(new ImageIcon("images/thumb-1920-646077.jpg"));
		
	}
	
	/**********************************************************************/
	/** AFFICHER LE PANEL PRINCIPAL QUI CONTIENT TOUS LES QUTRES WIDGETS **/
	/************************************** ******************************/
	public void afficherPanelPrincipal() {
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(null);
		panelPrincipal.setBounds(0, 0, 1296, 753);
		panelPrincipal.setLayout(null);
		contentPane.add(panelPrincipal);
	}
	
	

	/*====================================== BOUTONS POUR BASCULER ENTRE LES SCENARIOS ====================================== */
	
	/**********************************************************/
	/** AFFICHER UN BOUTON POUR PASSER AU SCENARIO PRECEDENT **/
	/**********************************************************/
	public void afficherBoutonGoLeft() {
		
		boutonLeft = new JButton("Go Left");
		boutonLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(panelScroll.scrollPane.isEnabled()) {
					panelScroll.scrollPane.getHorizontalScrollBar().setValue(panelScroll.scrollPane.getHorizontalScrollBar().getValue()- 855);
				}
			}
		});
		boutonLeft.setBounds(101, 155, 97, 381);
		boutonLeft.setBackground(new Color(16, 22, 33));
		panelPrincipal.add(boutonLeft);
	}
	
	/********************************************************/
	/** AFFICHER UN BOUTON POUR PASSER AU SCENARIO SUIVANT **/
	/********************************************************/
	private void afficherBoutonGoRight() {
		boutonRight = new JButton("Go Right");
		boutonRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(panelScroll.scrollPane.isEnabled()) {
					panelScroll.scrollPane.getHorizontalScrollBar().setValue(panelScroll.scrollPane.getHorizontalScrollBar().getValue() + 855);
				}	
			}
		});
		boutonRight.setBounds(1084, 155, 97, 381);
		boutonRight.setBackground(new Color(16, 22, 33));
		panelPrincipal.add(boutonRight);
	}
	
	
	/*====================================== BOUTONS POUR RETOURNER ====================================== */
	@SuppressWarnings("deprecation")
	public void retourner() {
		if(choix)
		{
			MenuMultiJoueurs menuMulti = new MenuMultiJoueurs();
			menuMulti.show();
		}
		
		else {
			MenuSolo menuSolo = new MenuSolo();
			menuSolo.show();
		}
		dispose();
	}

	

	public boolean isChoix() {
		return choix;
	}

	public void setChoix(boolean choix) {
		this.choix = choix;
	}

	public ArrayList<Joueur> getJoueurs() {
		return joueurs;
	}

	public void setJoueurs(ArrayList<Joueur> joueurs) {
		this.joueurs = joueurs;
	}
	
	
	
}
