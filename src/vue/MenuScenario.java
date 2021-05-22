package vue;


import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class MenuScenario extends JFrame {

	private JPanel contentPane;
	private JPanel panelPrincipal;
	private JPanel panelScenarioStandard;
	private JPanel panelScenario5;
	private JButton boutonScenarioStandard;
	private JLabel  backgroundLabel;
	private JButton boutonScenarioTempsLimite;
	private JButton boutonScenarioCinqSoldatSucc;
	private JButton boutonScenarioInfanteriesLourdes;
	private JButton boutonScenario5;
	private ArrayList<JButton> boutons = new ArrayList<JButton>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuScenario frame = new MenuScenario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public MenuScenario() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1300, 781);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panelPrincipal = new JPanel();
		panelPrincipal.setBounds(0, 0, 1290, 744);
		panelPrincipal.setLayout(null);
		contentPane.add(panelPrincipal);
		
		afficherScenarios();
		
		/** AFFICHAGE D'UN BACKGROUND POUR LE PANEL PRINCIPAL **/
		backgroundLabel = new JLabel("");
		backgroundLabel.setBounds(0, 0, 1290, 744);
		backgroundLabel.setIcon(new ImageIcon("images/EMATgxvW4AELz-Z.jpg"));
		panelPrincipal.add(backgroundLabel);
		
	}
	
	/*******************************/
	/** AFFICHAGE LES 5 SCENARIOS **/
	/*******************************/	
	public void afficherScenarios() {
		afficherBoutonScenarioStandard();
		afficherBoutonScenarioTempsLimite();
		afficherBoutonScenarioCinqSoldatSucc();
		afficherBoutonScenarioInfanteriesLourdes();
		afficherPanel();
		afficherChoixScenario5();
	}
	
	/****************************/
	/** AFFICHAGE DES 5 PANELS **/
	/****************************/
	public void afficherPanel() {
		int i = 0, j = 0;
		int cmptBoutons = 0;
		int x = 144;
		int y = 20;
		for (i=0; i<2; i++) {
			for(j=0; j<2; j++) {
				panelScenarioStandard = new JPanel();
				panelScenarioStandard.setOpaque(false);
				panelScenarioStandard.setBounds(x, y, 345, 223);
				panelPrincipal.add(panelScenarioStandard);
				panelScenarioStandard.setLayout(null);
				
				panelScenarioStandard.add(boutons.get(cmptBoutons));
				cmptBoutons += 1;
				x += 709;
			}
			x = 144;
			y = 496;
		}
	}
	
	
	/**********************************************/
	/** AFFICHAGE DU BOUTON 1: SCENARIO STANDARD **/
	/**********************************************/	
	public void afficherBoutonScenarioStandard() {
		boutonScenarioStandard = new JButton("");
		boutonScenarioStandard.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				PlateauVue plateauVue = new PlateauVue();
				plateauVue.show();
				dispose();
			}
		});
		boutonScenarioStandard.setBounds(10, 10, 325, 203);
		boutonScenarioStandard.setIcon(new ImageIcon("images/jeu_standard.jpg"));
		
		boutons.add(boutonScenarioStandard);
		
	}
	
	/*****************************************************/
	/** AFFICHAGE DU BOUTON 2: SCENARIO DU TEMPS LIMITE **/
	/*****************************************************/	
	public void afficherBoutonScenarioTempsLimite() {
		boutonScenarioTempsLimite = new JButton("");
		boutonScenarioTempsLimite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		boutonScenarioTempsLimite.setBounds(10, 10, 325, 203);
		boutonScenarioTempsLimite.setIcon(new ImageIcon("images/match_de_4_mins.jpg"));
		
		boutons.add(boutonScenarioTempsLimite);
	}
	
	/********************************************************************/
	/** AFFICHAGE DU BOUTON 3: SCENARIO DE CINQ SOLDATS SUCCESSIVEMENT **/
	/********************************************************************/	
	public void afficherBoutonScenarioCinqSoldatSucc() {
		boutonScenarioCinqSoldatSucc = new JButton("");
		boutonScenarioCinqSoldatSucc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		boutonScenarioCinqSoldatSucc.setBounds(10, 10, 325, 203);
		boutonScenarioCinqSoldatSucc.setIcon(new ImageIcon("images/cinq-soldats-succ.jpg"));
		
		boutons.add(boutonScenarioCinqSoldatSucc);
	}
	
	/************************************************************************************/
	/** AFFICHAGE DU BOUTON 4: SCENARIO CONSISTE A TUER TOUTES LES INFANTERIES LOURDES **/
	/************************************************************************************/	
	public void afficherBoutonScenarioInfanteriesLourdes() {
		boutonScenarioInfanteriesLourdes = new JButton("");
		boutonScenarioInfanteriesLourdes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		boutonScenarioInfanteriesLourdes.setBounds(10, 10, 325, 203);
		boutonScenarioInfanteriesLourdes.setIcon(new ImageIcon("images/cinq-infrantries-lourdes.jpg"));
		
		boutons.add(boutonScenarioInfanteriesLourdes);
	}
	
	
	/*********************************************/
	/** AFFICHAGE DU CHOIX 5: SCENARIO  **/
	/*********************************************/	
	
	public void afficherChoixScenario5() {
		panelScenario5 = new JPanel();
		panelScenario5.setOpaque(false);
		panelScenario5.setBounds(500, 258, 345, 223);
		panelPrincipal.add(panelScenario5);
		panelScenario5.setLayout(null);
		
		boutonScenario5 = new JButton("");
		boutonScenario5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		boutonScenario5.setBounds(10, 10, 325, 203);
		boutonScenario5.setIcon(new ImageIcon("images/review-battle-wesnoth-map.jpg"));
		panelScenario5.add(boutonScenario5);
	}
	
}
