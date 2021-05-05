package vue;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuPrincipal{

	public JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipal window = new MenuPrincipal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MenuPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 1300, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setBounds(0, 0, 1296, 817);
		frame.getContentPane().add(panelPrincipal);
		panelPrincipal.setLayout(null);
		
		JPanel panelMenu = new JPanel();
		panelMenu.setBorder(null);
		panelMenu.setBounds(114, 167, 292, 486);
		panelPrincipal.add(panelMenu);
		
		JLabel labelBackground1 = new JLabel("");
		labelBackground1.setBounds(22, 21, 247, 146);
		labelBackground1.setIcon(new ImageIcon("C:\\Users\\Jihad\\Desktop\\ISTY\\IATIC3\\Semestre 2\\Projets Pédagogiques\\Projet Fin d'année\\Projet_JavaPOO_G10\\Projet_JavaPOO_G10\\images\\strong_opaque-background.png"));
		
		JLabel labelBackground2 = new JLabel("");
		labelBackground2.setBounds(22, 166, 248, 149);
		labelBackground2.setIcon(new ImageIcon("C:\\Users\\Jihad\\Desktop\\ISTY\\IATIC3\\Semestre 2\\Projets Pédagogiques\\Projet Fin d'année\\Projet_JavaPOO_G10\\Projet_JavaPOO_G10\\images\\strong_opaque-background.png"));
		
		JLabel labelBackground3 = new JLabel("");
		labelBackground3.setBounds(22, 311, 247, 154);
		labelBackground3.setIcon(new ImageIcon("C:\\Users\\Jihad\\Desktop\\ISTY\\IATIC3\\Semestre 2\\Projets Pédagogiques\\Projet Fin d'année\\Projet_JavaPOO_G10\\Projet_JavaPOO_G10\\images\\strong_opaque-background.png"));
		
		JLabel coinSupGauche = new JLabel("");
		coinSupGauche.setBounds(0, 0, 34, 22);
		coinSupGauche.setIcon(new ImageIcon("C:\\Users\\Jihad\\Desktop\\ISTY\\IATIC3\\Semestre 2\\Projets Pédagogiques\\Projet Fin d'année\\Projet_JavaPOO_G10\\Projet_JavaPOO_G10\\images\\strong_opaque-border-topleft.png"));
		
		JLabel coinSupDroit = new JLabel("");
		coinSupDroit.setBounds(266, 0, 27, 22);
		coinSupDroit.setIcon(new ImageIcon("C:\\Users\\Jihad\\Desktop\\ISTY\\IATIC3\\Semestre 2\\Projets Pédagogiques\\Projet Fin d'année\\Projet_JavaPOO_G10\\Projet_JavaPOO_G10\\images\\strong_opaque-border-topright.png"));
		
		JLabel coinInfDroit = new JLabel("");
		coinInfDroit.setBounds(267, 462, 27, 28);
		coinInfDroit.setIcon(new ImageIcon("C:\\Users\\Jihad\\Desktop\\ISTY\\IATIC3\\Semestre 2\\Projets Pédagogiques\\Projet Fin d'année\\Projet_JavaPOO_G10\\Projet_JavaPOO_G10\\images\\strong_opaque-border-botright.png"));
		
		JLabel labelGauche1 = new JLabel("");
		labelGauche1.setBorder(null);
		labelGauche1.setBounds(0, 20, 25, 146);
		labelGauche1.setIcon(new ImageIcon("C:\\Users\\Jihad\\Desktop\\ISTY\\IATIC3\\Semestre 2\\Projets Pédagogiques\\Projet Fin d'année\\Projet_JavaPOO_G10\\Projet_JavaPOO_G10\\images\\strong_opaque-border-left.png"));
		
		JLabel labelGauche2 = new JLabel("");
		labelGauche2.setBorder(null);
		labelGauche2.setBounds(0, 166, 25, 146);
		labelGauche2.setIcon(new ImageIcon("C:\\Users\\Jihad\\Desktop\\ISTY\\IATIC3\\Semestre 2\\Projets Pédagogiques\\Projet Fin d'année\\Projet_JavaPOO_G10\\Projet_JavaPOO_G10\\images\\strong_opaque-border-left.png"));
		
		JLabel labelGauche3 = new JLabel("");
		labelGauche3.setBounds(0, 311, 25, 154);
		labelGauche3.setIcon(new ImageIcon("C:\\Users\\Jihad\\Desktop\\ISTY\\IATIC3\\Semestre 2\\Projets Pédagogiques\\Projet Fin d'année\\Projet_JavaPOO_G10\\Projet_JavaPOO_G10\\images\\strong_opaque-border-left.png"));
		
		JLabel bottomLabel = new JLabel("");
		bottomLabel.setBounds(26, 465, 243, 22);
		bottomLabel.setIcon(new ImageIcon("C:\\Users\\Jihad\\Desktop\\ISTY\\IATIC3\\Semestre 2\\Projets Pédagogiques\\Projet Fin d'année\\Projet_JavaPOO_G10\\Projet_JavaPOO_G10\\images\\strong_opaque-border_bottom.png"));
		
		JLabel topLabel = new JLabel("");
		topLabel.setBounds(26, 1, 243, 20);
		topLabel.setIcon(new ImageIcon("C:\\Users\\Jihad\\Desktop\\ISTY\\IATIC3\\Semestre 2\\Projets Pédagogiques\\Projet Fin d'année\\Projet_JavaPOO_G10\\Projet_JavaPOO_G10\\images\\strong_opaque-border-top.png"));
		
		JLabel labelDroit1 = new JLabel("");
		labelDroit1.setBounds(267, 21, 27, 146);
		labelDroit1.setIcon(new ImageIcon("C:\\Users\\Jihad\\Desktop\\ISTY\\IATIC3\\Semestre 2\\Projets Pédagogiques\\Projet Fin d'année\\Projet_JavaPOO_G10\\Projet_JavaPOO_G10\\images\\strong_opaque-border-right.png"));
		
		JLabel labelDroit2 = new JLabel("");
		labelDroit2.setBounds(267, 160, 27, 160);
		labelDroit2.setIcon(new ImageIcon("C:\\Users\\Jihad\\Desktop\\ISTY\\IATIC3\\Semestre 2\\Projets Pédagogiques\\Projet Fin d'année\\Projet_JavaPOO_G10\\Projet_JavaPOO_G10\\images\\strong_opaque-border-right.png"));
		
		JLabel labelDroit3 = new JLabel("");
		labelDroit3.setBounds(267, 317, 27, 146);
		labelDroit3.setIcon(new ImageIcon("C:\\Users\\Jihad\\Desktop\\ISTY\\IATIC3\\Semestre 2\\Projets Pédagogiques\\Projet Fin d'année\\Projet_JavaPOO_G10\\Projet_JavaPOO_G10\\images\\strong_opaque-border-right.png"));
		panelMenu.setLayout(null);
		
		JButton boutonQuitter = new JButton("");
		boutonQuitter.setIcon(new ImageIcon("C:\\Users\\Jihad\\Desktop\\ISTY\\IATIC3\\Semestre 2\\Projets Pédagogiques\\Projet Fin d'année\\Projet_JavaPOO_G10\\Projet_JavaPOO_G10\\images\\large-button-active-quitter.jpg"));
		boutonQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
		boutonQuitter.setBounds(61, 352, 170, 42);
		panelMenu.add(boutonQuitter);
		
		JButton boutonContinuer = new JButton("");
		boutonContinuer.setIcon(new ImageIcon("C:\\Users\\Jihad\\Desktop\\ISTY\\IATIC3\\Semestre 2\\Projets Pédagogiques\\Projet Fin d'année\\Projet_JavaPOO_G10\\Projet_JavaPOO_G10\\images\\large-button-active-continuer.jpg"));
		boutonContinuer.setBounds(61, 286, 170, 43);
		panelMenu.add(boutonContinuer);
		
		JButton boutonMultiJoueurs = new JButton("");
		boutonMultiJoueurs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//MenuMultiJoueur menuMultiJoueur = new MenuMultiJoueur();
				//menuMultiJoueur.

			}
		});
		boutonMultiJoueurs.setIcon(new ImageIcon("C:\\Users\\Jihad\\Desktop\\ISTY\\IATIC3\\Semestre 2\\Projets Pédagogiques\\Projet Fin d'année\\Projet_JavaPOO_G10\\Projet_JavaPOO_G10\\images\\large-button-active-multi-joueurs.jpg"));
		boutonMultiJoueurs.setBounds(61, 160, 170, 42);
		panelMenu.add(boutonMultiJoueurs);
		
		JButton boutonSolo = new JButton("");
		boutonSolo.setIcon(new ImageIcon("C:\\Users\\Jihad\\Desktop\\ISTY\\IATIC3\\Semestre 2\\Projets Pédagogiques\\Projet Fin d'année\\Projet_JavaPOO_G10\\Projet_JavaPOO_G10\\images\\large-button-active-solo.jpg"));
		boutonSolo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//MenuSolo MenuSolo= new MenuSolo();
				//MenuSolo.main(null);

			}
		});
		boutonSolo.setBounds(61, 225, 170, 39);
		panelMenu.add(boutonSolo);
		
		JLabel coinInfGauche = new JLabel("");
		coinInfGauche.setBorder(null);
		coinInfGauche.setBounds(0, 462, 27, 28);
		coinInfGauche.setIcon(new ImageIcon("C:\\Users\\Jihad\\Desktop\\ISTY\\IATIC3\\Semestre 2\\Projets Pédagogiques\\Projet Fin d'année\\Projet_JavaPOO_G10\\Projet_JavaPOO_G10\\images\\strong_opaque-border-botleft.png"));
		panelMenu.add(coinInfGauche);
		
		JLabel labelLogo = new JLabel("");
		labelLogo.setIcon(new ImageIcon("C:\\Users\\Jihad\\Desktop\\ISTY\\IATIC3\\Semestre 2\\Projets Pédagogiques\\Projet Fin d'année\\Projet_JavaPOO_G10\\Projet_JavaPOO_G10\\images\\wesnoth-icon.png"));
		labelLogo.setBounds(81, 1, 122, 113);
		panelMenu.add(labelLogo);
		panelMenu.add(topLabel);
		panelMenu.add(labelGauche1);
		panelMenu.add(labelDroit1);
		panelMenu.add(coinInfDroit);
		panelMenu.add(labelGauche2);
		panelMenu.add(labelBackground1);
		panelMenu.add(labelBackground2);
		panelMenu.add(labelBackground3);
		panelMenu.add(bottomLabel);
		panelMenu.add(coinSupGauche);
		panelMenu.add(labelDroit2);
		panelMenu.add(labelGauche3);
		panelMenu.add(labelDroit3);
		panelMenu.add(coinSupDroit);
		
		JLabel backgroundimage = new JLabel("");
		backgroundimage.setBounds(0, 0, 1300, 767);
		panelPrincipal.add(backgroundimage);
		backgroundimage.setIcon(new ImageIcon("C:\\Users\\Jihad\\Desktop\\ISTY\\IATIC3\\Semestre 2\\Projets Pédagogiques\\Projet Fin d'année\\Projet_JavaPOO_G10\\Projet_JavaPOO_G10\\images\\thumb-1920-646077.jpg"));
	}
}
