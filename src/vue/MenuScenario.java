package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import modele.Joueur;
import modele.ScenarioStandard;
import modele.ScenarioTempsLimite;

@SuppressWarnings("serial")
public class MenuScenario extends JFrame {

	private JPanel panelPrincipal;
	private JPanel panelMenu;
	private JLabel backgroundimage;
	private JButton boutonScenarioTempsLimite;
	private JButton boutonScenarioStandard;
	private boolean choix;
	private ArrayList<Joueur> joueurs;


	/**
	 * Create the frame.
	 */
	public MenuScenario(boolean choix, ArrayList<Joueur> joueurs) {
		this.setTitle("WarGame");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 1300, 781);
		
		Dimension size = Toolkit. getDefaultToolkit().getScreenSize();
		GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		if (device.isFullScreenSupported() && size.getHeight() <= 720) {
			device.setFullScreenWindow(this);
		} 
		
		
		this.choix = choix;
		this.joueurs = joueurs;
		
		/** PANEL PRINCIPAL DE LA FENETRE **/
		panelPrincipal = new JPanel();
		panelPrincipal.setBounds(0, 0, 1296, 767);
		this.getContentPane().add(panelPrincipal);
		panelPrincipal.setLayout(null);
		
		
		/** PANEL QUI CONTIENT TOUS LES BOUTONS, ET LE LOGO DU JEU **/
		panelMenu = new PanelMenuInfos(490, 167, 292, 486);
		panelPrincipal.add(panelMenu);
		//panelMenu.setBackground(new Color(0,0,0,125));
		
		
		afficherBoutonScenarioStandard();
		afficherBoutonScenarioTempsLimite();
		
		
		/** BOUTON RETOUR **/
		
		
		/** BACKGROUND **/
		backgroundimage = new JLabel("");
		backgroundimage.setBounds(0, 0, 1296, 767);
		backgroundimage.setIcon(new ImageIcon("images/liberty.jpg"));
		panelPrincipal.add(backgroundimage);
		
	}
	
	

	public void afficherBoutonScenarioStandard() {
		boutonScenarioStandard = new JButton();
		boutonScenarioStandard.setText("Scenario Standard");
		boutonScenarioStandard.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				ScenarioStandard scenario = new ScenarioStandard("scenarioStandard", joueurs);
				PlateauVue plateau = new PlateauVue(joueurs,scenario);
				plateau.show();
				dispose();
			}
		});
		boutonScenarioStandard.setIcon(new ImageIcon("images/large-button-active.png"));
		boutonScenarioStandard.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		boutonScenarioStandard.setForeground(Color.white);
		boutonScenarioStandard.setHorizontalTextPosition(JButton.CENTER);
		boutonScenarioStandard.setBounds(60, 183, 172, 48);
		panelMenu.add(boutonScenarioStandard);
	}
	

	public void afficherBoutonScenarioTempsLimite() {
		boutonScenarioTempsLimite = new JButton();
		boutonScenarioTempsLimite.setText("Match de 4 Mins");
		boutonScenarioTempsLimite.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				ScenarioTempsLimite scenario = new ScenarioTempsLimite("scenarioStandard", joueurs);
				PlateauVue plateau = new PlateauVue(joueurs,scenario);
				plateau.show();
				dispose();
			}
		});
		boutonScenarioTempsLimite.setBorder(UIManager.getBorder("Button.border"));
		boutonScenarioTempsLimite.setIcon(new ImageIcon("images/large-button-active.png"));
		boutonScenarioTempsLimite.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		boutonScenarioTempsLimite.setForeground(Color.white);
		boutonScenarioTempsLimite.setHorizontalTextPosition(JButton.CENTER);
		panelMenu.setLayout(null);
		boutonScenarioTempsLimite.setBounds(60, 290, 172, 48);
		panelMenu.add(boutonScenarioTempsLimite);
		PanelBoutonRetour panelBouton = new PanelBoutonRetour();
		panelBouton.setBounds(34, 412, 52, 29);
		panelMenu.add(panelBouton);
		panelBouton.boutonRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				retourner();
			}
		});
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
