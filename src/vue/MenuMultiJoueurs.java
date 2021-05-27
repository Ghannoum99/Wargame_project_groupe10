package vue;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import modele.*;

//x = 340;
//y = 157
//w = 596
//h = 480

@SuppressWarnings("serial")
public class MenuMultiJoueurs extends JFrame {
	protected boolean choix; //true:multi-joueurs, false :solo
	private JPanel contentPane;
	private JLabel backgroundImage;
	private boolean  checked = false;
	private ArrayList<Soldat> soldats;
	private JLabel labelJoueur;
	private JButton boutonValider;
	private JComboBox<String> imageJoueur;
	private ArrayList<String> images = new ArrayList<String>();
	protected boolean pressed = false;
	protected JLabel labelNombreJoueur;
	protected JButton boutonNombreJoueur;
	protected ArrayList<String> pseudos  = new ArrayList<String>();;
	protected ArrayList<JTextField> listeChampText = new ArrayList<JTextField>();
	protected ArrayList<JLabel> lblJoueur = new ArrayList<JLabel>();
	private ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
	protected ArrayList<JLabel> labelCheckBox = new ArrayList<JLabel>();
	protected ArrayList<JComboBox<String>> listeCombobox = new ArrayList<JComboBox<String>>();
	protected PanelMenuInfos panelMenu;
	protected JSpinner spinnerNombreJoueur;
	protected Integer nombreJoueur;
	protected JTextField champText;
	
	
	

	public  MenuMultiJoueurs() {
		this.setTitle("WarGame");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 1300, 781);
		
		choix = true;
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		/** CREATION D'UN PANEL DU MENU QUI CONTIENT LES CHAMPS DU TEXT, LES COMBOBOX, ET LES CHECKBUTTONS **/
		panelMenu = new PanelMenuInfos(340, 157, 596, 480);
		contentPane.add(panelMenu);
		
		/** BOUTON DE RETOUR **/
		PanelBoutonRetour panelBouton = new PanelBoutonRetour();
		panelMenu.add(panelBouton);
		panelBouton.boutonRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				retourner();
			}
		});
		
		afficherLabelNombreJoueur();
		afficherSpinnerNombreJoueur();
		afficherBoutonNombreJoueur();
		afficherBoutonValider();
		
		
		
		
		/** BACKGROUND **/
		backgroundImage = new JLabel("");
		backgroundImage.setIcon(new ImageIcon("images/liberty.jpg"));
		backgroundImage.setBounds(0, 0, 1296, 753);
		contentPane.add(backgroundImage);
	}
	
	/*******************************************************************************************************/
	/** AFFICHAGE DU LABEL Nombre Joueur : QUI CONSISTE A INDIQUER QU'IL FAUT CHOISIR LE NOMBRE DE JOUEUR **/
	/*******************************************************************************************************/
	public void afficherLabelNombreJoueur() {
		labelNombreJoueur = new JLabel("Nombre de joueur :");
		labelNombreJoueur.setBackground(Color.WHITE);
		labelNombreJoueur.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
		labelNombreJoueur.setForeground(Color.WHITE);
		labelNombreJoueur.setBounds(36, 144, 125, 29);
		panelMenu.add(labelNombreJoueur);
	}
	
	/****************************************************************************/
	/** AFFICHAGE D'UN BOUTON QUI PERMET DE VALIDER LE NOMBRE DE JOUEUR CHOISI **/
	/****************************************************************************/
	public void afficherBoutonNombreJoueur() {
		boutonNombreJoueur = new JButton("");
		boutonNombreJoueur.setBorder(UIManager.getBorder("Button.border"));
		boutonNombreJoueur.setIcon(new ImageIcon("images/checkbox-active@2x.png"));
		boutonNombreJoueur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!pressed) {
					boutonNombreJoueur.setIcon(new ImageIcon("images/checkbox-active-pressed@2x.png"));
				}
				nombreJoueur = (Integer)spinnerNombreJoueur.getValue();
				SwingUtilities.updateComponentTreeUI(panelMenu);
				afficherLabelNumJoueur();
				afficherChampTextPseudo();
				afficherCombobox();
				afficherCheckBox();
				pressed = true;
			}
		});
		
		boutonNombreJoueur.setBounds(288, 140, 39, 36);
		panelMenu.add(boutonNombreJoueur);
	}
	
	/**************************************************************************************/
	/** AFFICHAGE D'UN SPINNER QUI PERMET A L'UTILISATEUR DE CHOISIR LE NOMBRE DE JOUEUR **/
	/**************************************************************************************/
	public void afficherSpinnerNombreJoueur() {
		spinnerNombreJoueur = new JSpinner();
		spinnerNombreJoueur .setModel(new SpinnerNumberModel(2, 2, 4, 1));
		spinnerNombreJoueur.setEditor(new JSpinner.DefaultEditor(spinnerNombreJoueur));
		spinnerNombreJoueur .setBounds(180, 145, 68, 26);
		panelMenu.add(spinnerNombreJoueur);
	}
	
	/**********************************************************************************************************/
	/** AFFICHAGE D'UN BOUTON QUI PERMET DE STOCKER LES PSEUDOS POUR CHAQUE JOUEUR ET PASSER AU MENU SUIVANT **/
	/**********************************************************************************************************/
	public void afficherBoutonValider() {
		boutonValider = new JButton("");
		boutonValider.setBorder(UIManager.getBorder("Button.border"));
		boutonValider.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
		boutonValider.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				
				/** SI L'UTILISATEUR A CHOISI LE NOMBRE DE JOUEUR, DONC IL PEUT PASSER AU MENU SUIVANT **/
				if (pressed) {
					/** RECUPERATION DES PSEUDOS **/
					recupererPseudo();
					recupererImage();
					
					for(int i=0; i<nombreJoueur; i++) {
						String photoProfile = "images/profile/" + images.get(i) +".png";
						Joueur joueur = new Joueur(pseudos.get(i), soldats ,0, photoProfile);
						joueurs.add(joueur);
						System.out.println(joueurs.get(i));
					}
					
					// faut ajouter des conditions si le check box est selectionné, donc c'est un ordinateur
					
					MenuScenario frame = new MenuScenario(choix, joueurs);
					frame.show();
					dispose();
				}	
			}
		});
		boutonValider.setIcon(new ImageIcon("images/menu_button_small_H18-active@2x-valider.jpg"));
		boutonValider.setBounds(443, 401, 109, 34);
		panelMenu.add(boutonValider);
	}
	
	/***********************************************************************/
	/** AFFICHAGE DES CHAMPS TEXT POUR INSERER LE PSEUDO DE CHAQUE JOUEUR **/
	/***********************************************************************/
	public void afficherChampTextPseudo() {
		int i;
		int y = 200;
		
		for(JTextField champText : listeChampText) {
			panelMenu.remove(champText);
		}
		
		listeChampText.removeAll(listeChampText);
		
		for(i=1; i<=nombreJoueur; i++) {
			champText = new JTextField();
			champText.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 13));
			champText.setBorder(null);
			champText.setText("Pseudo" + i);
			champText.setBounds(180, y, 187, 20);
			panelMenu.add(champText);
			champText.setColumns(10);
			
			listeChampText.add(champText);
			y += 52;
		}
	}
	
	/**************************************************************************************/
	/** AFFICHAGE D'UN LABEL POUR CHAQUE JOUEUR: CONSISTE A INDIQUER LE NOMBRE DE JOUEUR **/
	/**************************************************************************************/
	public void afficherLabelNumJoueur() {
		int y = 198;
		int i;
		
		for(JLabel label : lblJoueur) {
			panelMenu.remove(label);
		}
		
		lblJoueur.removeAll(lblJoueur);
		
		for(i = 1; i<=nombreJoueur; i++) {
			labelJoueur = new JLabel("JOUEUR " + i + " :");
			labelJoueur.setForeground(Color.WHITE);
			labelJoueur.setVisible(true);
			labelJoueur.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
			labelJoueur.setBounds(36, y, 111, 29);
			panelMenu.add(labelJoueur);
			
			lblJoueur.add(labelJoueur);
			
			y += 52;
		}
	}
	
	/*****************************************************************************************************/
	/** AFFICHAGE D'UN COMBOBOX POUR CHAQUE JOUEUR: PERMET AUX JOUEURS DE CHOISIR LEUR IMAGE DE PROFILE **/
	/*****************************************************************************************************/
	public void afficherCombobox() {
		int i = 0;
		int y = 201;
		
		for(JComboBox<String> combo : listeCombobox) {
			panelMenu.remove(combo);
		}
		
		listeCombobox.removeAll(listeCombobox);
		
		for(i=0; i<nombreJoueur; i++) {
			imageJoueur = new JComboBox<String>();
			imageJoueur.setBounds(397, y, 68, 22);
			imageJoueur.setModel(new DefaultComboBoxModel<String>(new String[] {"image2", "image3", "image4", "image5", "image6", "image7"}));
			panelMenu.add(imageJoueur);
			
			listeCombobox.add(imageJoueur);
			
			y += 52;
		}
	}
	
	/**********************************************************************************/
	/** AFFICHAGE DES CHECKBUTTONS POUR INDIQUER SI C'EST UN JOUEUR OU UN ORDINATEUR **/
	/**  				PRESSED : ORDINATEUR, OTHERWISE IT'S A HUMAIN                **/
	/**********************************************************************************/
	public void afficherCheckBox() {
		int y = 198;
		int i = 0;
		
		for(JLabel checkBox : labelCheckBox ) {
			panelMenu.remove(checkBox);
		}
		
		labelCheckBox.removeAll(labelCheckBox);
		
		for(i=0; i<nombreJoueur; i++) {
			JLabel checkbox = new JLabel(new ImageIcon("images/checkbox-active.png"));
			checkbox.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (!checked) {
						checkbox.setIcon(new ImageIcon("images/checkbox-active-pressed.png"));
						checked = true;
					}
					else {
						checkbox.setIcon(new ImageIcon("images/checkbox-active.png"));
						checked = false;
					}
				}
			});
			checkbox.setBounds(497, y, 20, 26);
			panelMenu.add(checkbox);
			
			labelCheckBox.add(checkbox);
			y += 52;
		}
		
	}
	
	/******************************************************************************/
	/** RECUPERATION DES PSEUDOS : STOCKER LES PSEUDOS DANS UN ARRAYLIST pseudos **/
	/******************************************************************************/
	public void recupererPseudo() {
		for(int i=0; i<listeChampText.size(); i++) {
			pseudos.add((String)listeChampText.get(i).getText());
		}
	}
	
	public void recupererImage() {
		for(int i = 0; i<listeCombobox.size(); i++) {
			images.add((String) listeCombobox.get(i).getSelectedItem());
		}
	}
	
	@SuppressWarnings("deprecation")
	public void retourner() {
		MenuPrincipal menuPrincipal = new MenuPrincipal();
		menuPrincipal.show();
		dispose();
	}
}
