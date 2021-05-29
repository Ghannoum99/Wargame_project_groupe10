package vue;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

@SuppressWarnings({ "deprecation", "serial" })
public class MenuPrincipal extends JFrame{
	private JPanel panelPrincipal;
	private JButton boutonQuitter;
	private JButton boutonContinuer;
	private JButton boutonMultiJoueurs;
	private JButton boutonSolo;
	private JLabel backgroundimage;
	private PanelMenuInfos panelMenu;

	public MenuPrincipal() {
		this.setTitle("WarGame");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 1300, 781);
		
		Dimension size = Toolkit. getDefaultToolkit().getScreenSize();
		GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		if (device.isFullScreenSupported() && size.getHeight() <= 720) {
			device.setFullScreenWindow(this);
		} 
        
		/** PANEL PRINCIPAL DE LA FENETRE **/
		panelPrincipal = new JPanel();
		panelPrincipal.setBounds(0, 0, 1296, 767);
		getContentPane().add(panelPrincipal);
		panelPrincipal.setLayout(null);
		
		/** PANEL QUI CONTIENT TOUS LES BOUTONS, ET LE LOGO DU JEU **/
		panelMenu = new PanelMenuInfos(114, 167, 292, 486);
		panelPrincipal.add(panelMenu);
		
		/** AFFICHER TOUS LES CONTENUS DU PanelMenu**/
		afficherPanelMenuContenu();
		
		/** BACKGROUND **/
		backgroundimage = new JLabel("");
		backgroundimage.setBounds(0, 0, 1296, 767);
		backgroundimage.setIcon(new ImageIcon("images/thumb-1920-646077.jpg"));
		panelPrincipal.add(backgroundimage);
		
		
	}
	
	/***************************************************************************************/
	/** AFFICHAGE DE TOUS LES CONTENUS DU PANEL MENU ( 4 BOUTONS, ET LES TRUCS DU DESIGN  **/
	/***************************************************************************************/
	public void afficherPanelMenuContenu() {
		SwingUtilities.updateComponentTreeUI(panelMenu);
		
		afficherBoutonMultiJoueurs();
		afficherBoutonSolo();
		afficherBoutonContinuer();
		afficherBoutonQuitter();
	}
	
	
	/*======================================  CHOISIR LE MODE DE JEU (Multi-players or Solo) ====================================== */
	
	/*****************************************************************************/
	/** AFFICHAGE DU BOUTON MULTI-JOUEURS : QUI VA AFFICHER LE MenuMultiJoueurs **/
	/*****************************************************************************/
	public void afficherBoutonMultiJoueurs() {
		boutonMultiJoueurs = new JButton("");
		boutonMultiJoueurs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playAudio("c:\\Windows\\media\\ding.wav");
				MenuMultiJoueurs menuMulti = new MenuMultiJoueurs();
				menuMulti.show();
				dispose();
			}
		});
		boutonMultiJoueurs.setIcon(new ImageIcon("images/large-button-active-multi-joueurs.jpg"));
		boutonMultiJoueurs.setBounds(60, 173, 172, 48);
		panelMenu.add(boutonMultiJoueurs);
	}
	
	/************************************************************/
	/** AFFICHAGE DU BOUTON SOLO : QUI VA AFFICHER LE MenuSolo **/
	/************************************************************/
	public void afficherBoutonSolo() {
		boutonSolo = new JButton("");
		boutonSolo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playAudio("c:\\Windows\\media\\ding.wav");
				MenuSolo menuSolo = new MenuSolo();
				menuSolo.show();
				dispose();
			}
		});
		boutonSolo.setBorder(UIManager.getBorder("Button.border"));
		boutonSolo.setIcon(new ImageIcon("images/large-button-active-solo.jpg"));
		panelMenu.setLayout(null);
		boutonSolo.setBounds(60, 231, 172, 48);
		panelMenu.add(boutonSolo);
	}
	
	
	/*====================================== CONTINUER UNE BATAILLE ====================================== */
	
	/**********************************************************************************************************/
	/** AFFICHAGE DU BOUTON CONTINUER : QUI PERMET AUX JOUEURS DE CONTINUER UNE BATAILLE STOCKEE DANS LA BDD **/
	/**********************************************************************************************************/
	public void afficherBoutonContinuer() {
		boutonContinuer = new JButton("");
		boutonContinuer.setBorder(UIManager.getBorder("Button.border"));
		boutonContinuer.setIcon(new ImageIcon("images/large-button-active-continuer.jpg"));
		panelMenu.setLayout(null);
		boutonContinuer.setBounds(60, 295, 172, 44);
		panelMenu.add(boutonContinuer);
	}
	
	
	/*====================================== QUITTER LE JEU ====================================== */
	
	/************************************************************************/
	/** AFFICHAGE DU BOUTON QUITTER : PERMET AUX JOUEURS DE QUITTER LE JEU **/
	/************************************************************************/
	public void afficherBoutonQuitter() {
		boutonQuitter = new JButton("");
		boutonQuitter.setBorder(UIManager.getBorder("Button.border"));
		boutonQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playAudio("c:\\Windows\\media\\ding.wav");
				System.exit(0);
			}
		});
		boutonQuitter.setIcon(new ImageIcon("images/large-button-active-quitter.jpg"));
		panelMenu.setLayout(null);
		boutonQuitter.setBounds(60, 358, 172, 44);
		panelMenu.add(boutonQuitter);
	}

	/*====================================== EFFETS SONORES ====================================== */
	
	/*****************************/
	/** AJOUTER UN EFFET SONORE **/
	/*****************************/
	public void playAudio(String audioFilePath){
		File wavFile = new File(audioFilePath);
		AudioClip sound;
		  try {
			  sound = Applet.newAudioClip(wavFile.toURL()); 
			  sound.play();
		  }catch(Exception e1) {
			  e1.printStackTrace();
		  }
	}
	
}
