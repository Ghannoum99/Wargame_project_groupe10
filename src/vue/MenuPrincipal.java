package vue;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

public class MenuPrincipal{

	public JFrame frame;
	private JPanel panelPrincipal;
	private JPanel panelMenu ;
	private JLabel labelBackground;
	private JLabel coinSupGauche;
	private JLabel coinSupDroit;
	private JLabel coinInfDroit;
	private JLabel coinInfGauche;
	private JLabel labelGauche;
	private JLabel labelBottom;
	private JLabel labelTop;
	private JLabel labelDroit;
	private JButton boutonQuitter;
	private JButton boutonContinuer;
	private JButton boutonMultiJoueurs;
	private JButton boutonSolo;
	private JLabel labelLogo;
	private JLabel backgroundimage;
	
	
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
		frame.setBounds(100, 100, 1297, 795);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		panelPrincipal = new JPanel();
		panelPrincipal.setBounds(0, 0, 1296, 767);
		frame.getContentPane().add(panelPrincipal);
		panelPrincipal.setLayout(null);
		
		panelMenu = new JPanel();
		panelMenu.setOpaque(false);
		panelMenu.setBorder(null);
		panelMenu.setBounds(114, 167, 292, 486);
		panelMenu.setLayout(null);
		panelPrincipal.add(panelMenu);

		afficherPanelMenuContenu();
		

		backgroundimage = new JLabel("");
		backgroundimage.setBounds(0, 0, 1296, 767);
		panelPrincipal.add(backgroundimage);
		backgroundimage.setIcon(new ImageIcon("images/thumb-1920-646077.jpg"));
	}
	
	public void afficherPanelMenuContenu() {
		afficherLabelLogo();
		afficherBackgroundPanelMenu();
		afficherCoinSup();
		afficherCoinInf();
		afficherLabelGauche();
		afficherLabelDroit();
		afficherLabelTop();
		afficherLabelBottom();
		afficherBoutonMultiJoueurs();
		afficherBoutonSolo();
		afficherBoutonContinuer();
		afficherBoutonQuitter();
		
		SwingUtilities.updateComponentTreeUI(panelMenu);
	}
	
	public void afficherBackgroundPanelMenu() {
		int y = 21;
		
		for (int i=0;i<3;i++) {
			labelBackground = new JLabel("");
			labelBackground.setBounds(22, y, 247, 154);
			labelBackground.setIcon(new ImageIcon("images/strong_opaque-background.png"));
			panelMenu.setLayout(null);
			panelMenu.add(labelBackground);
			
			y += 145;
		}
		
	}
	
	public void afficherBoutonMultiJoueurs() {
		boutonMultiJoueurs = new JButton("");
		boutonMultiJoueurs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//MenuMultiJoueur menuMultiJoueur = new MenuMultiJoueur();
				//menuMultiJoueur.

			}
		});
		boutonMultiJoueurs.setIcon(new ImageIcon("images/large-button-active-multi-joueurs.jpg"));
		panelMenu.setLayout(null);
		boutonMultiJoueurs.setBounds(60, 173, 172, 48);
		panelMenu.add(boutonMultiJoueurs);
		
	}
	
	public void afficherBoutonSolo() {
		boutonSolo = new JButton("");
		boutonSolo.setBorder(UIManager.getBorder("Button.border"));
		
		boutonSolo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//MenuSolo MenuSolo= new MenuSolo();
				//MenuSolo.main(null);

			}
		});
		boutonSolo.setIcon(new ImageIcon("images/large-button-active-solo.jpg"));
		panelMenu.setLayout(null);
		boutonSolo.setBounds(60, 231, 172, 48);
		panelMenu.add(boutonSolo);
		
	}
	
	public void afficherBoutonContinuer() {
		boutonContinuer = new JButton("");
		boutonContinuer.setBorder(UIManager.getBorder("Button.border"));
		boutonContinuer.setIcon(new ImageIcon("images/large-button-active-continuer.jpg"));
		panelMenu.setLayout(null);
		boutonContinuer.setBounds(60, 295, 172, 44);
		panelMenu.add(boutonContinuer);
		
	}
	
	public void afficherBoutonQuitter() {
		boutonQuitter = new JButton("");
		boutonQuitter.setBorder(UIManager.getBorder("Button.border"));
		boutonQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
		boutonQuitter.setIcon(new ImageIcon("images/large-button-active-quitter.jpg"));
		panelMenu.setLayout(null);
		boutonQuitter.setBounds(60, 358, 172, 44);
		panelMenu.add(boutonQuitter);
		
	}
	
	public void afficherCoinSup() {
		coinSupGauche = new JLabel("");
		coinSupGauche.setBorder(null);
		coinSupGauche.setBounds(0, 0, 26, 25);
		coinSupGauche.setIcon(new ImageIcon("images/strong_opaque-border-topleft.png"));
		panelMenu.setLayout(null);
		panelMenu.add(coinSupGauche);
		
		coinSupDroit = new JLabel("");
		coinSupDroit.setBorder(null);
		coinSupDroit.setBounds(267, 0, 25, 25);
		panelMenu.setLayout(null);
		coinSupDroit.setIcon(new ImageIcon("images/strong_opaque-border-topright.png"));
		panelMenu.add(coinSupDroit);
		
	}
	
	public void afficherCoinInf() {
		coinInfDroit = new JLabel("");
		coinInfDroit.setBorder(null);
		coinInfDroit.setBounds(267, 462, 25, 26);
		coinInfDroit.setIcon(new ImageIcon("images/strong_opaque-border-botright.png"));
		panelMenu.setLayout(null);
		panelMenu.add(coinInfDroit);
		
		coinInfGauche = new JLabel("");
		coinInfGauche.setBorder(null);
		coinInfGauche.setBounds(0, 462, 27, 26);
		coinInfGauche.setIcon(new ImageIcon("images/strong_opaque-border-botleft.png"));
		panelMenu.setLayout(null);
		panelMenu.add(coinInfGauche);
		
	}
	
	public void afficherLabelDroit() {
		int y = 20;
		
		for(int i=0; i<3; i++) {
			labelDroit = new JLabel("");
			labelDroit.setBounds(267, y, 25, 150);
			labelDroit.setIcon(new ImageIcon("images/strong_opaque-border-right.png"));
			panelMenu.setLayout(null);
			panelMenu.add(labelDroit);
			
			y += 146;
		}
	}
	
	public void afficherLabelGauche() {
		int y = 20;
		
		for(int i=0; i<3; i++) {
			labelGauche = new JLabel("");
			labelGauche.setBounds(0, y, 25, 150);
			labelGauche.setIcon(new ImageIcon("images/strong_opaque-border-left.png"));
			panelMenu.setLayout(null);
			panelMenu.add(labelGauche);
			
			y += 146;
		}
	}
	
	public void afficherLabelLogo() {
		labelLogo = new JLabel("");
		labelLogo.setIcon(new ImageIcon("images/wesnoth-icon.png"));
		panelMenu.setLayout(null);
		labelLogo.setBounds(83, 0, 128, 128);
		panelMenu.add(labelLogo);
		
	}
	public void afficherLabelBottom() {
		labelBottom = new JLabel("");
		labelBottom.setBorder(null);
		labelBottom.setBounds(10, 462, 274, 26);
		labelBottom.setIcon(new ImageIcon("images/strong_opaque-border_bottom.png"));
		panelMenu.setLayout(null);
		panelMenu.add(labelBottom);	
	}
	
	public void afficherLabelTop() {
		labelTop = new JLabel("");
		labelTop.setBorder(null);
		labelTop.setBounds(9, 0, 274, 25);
		labelTop.setIcon(new ImageIcon("images/strong_opaque-border-top.png"));
		panelMenu.setLayout(null);
		panelMenu.add(labelTop);
	}
	
}
