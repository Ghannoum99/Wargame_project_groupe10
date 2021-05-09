package vue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.UIManager;

public class MenuMultiJoueur {

	public JFrame frame;
	private JTextField pseudoJoueur1;
	private JTextField pseudoJoueur2;
	private JTextField pseudoJoueur3;
	private JTextField pseudoJoueur4;
	private JPanel panelMenu;
	
	
	public MenuMultiJoueur() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 1300, 781);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		panelMenu = new JPanel();
		panelMenu.setOpaque(false);
		panelMenu.setBackground(new Color(240, 240, 240));
		panelMenu.setBounds(342, 169, 596, 484);
		frame.getContentPane().add(panelMenu);
		panelMenu.setLayout(null);
		
		JLabel labelLogo = new JLabel("");
		labelLogo.setIcon(new ImageIcon("images/wesnoth-icon.png"));
		labelLogo.setBounds(228, 0, 132, 118);
		panelMenu.add(labelLogo);
		
		JLabel coinSupDroit = new JLabel("");
		coinSupDroit.setIcon(new ImageIcon("images/strong_opaque-border-topright.png"));
		coinSupDroit.setBounds(571, 0, 27, 26);
		panelMenu.add(coinSupDroit);
		
		JLabel coinSupGauche = new JLabel("");
		coinSupGauche.setIcon(new ImageIcon("images/strong_opaque-border-topleft.png"));
		coinSupGauche.setBounds(0, 0, 26, 29);
		panelMenu.add(coinSupGauche);
		
		JLabel coinInfGauche = new JLabel("");
		coinInfGauche.setIcon(new ImageIcon("images/strong_opaque-border-botleft.png"));
		coinInfGauche.setBounds(0, 455, 26, 29);
		panelMenu.add(coinInfGauche);
		
		JLabel coinInfDroit = new JLabel("\r\n");
		coinInfDroit.setIcon(new ImageIcon("images/strong_opaque-border-botright.png"));
		coinInfDroit.setBounds(571, 455, 27, 29);
		panelMenu.add(coinInfDroit);
		
		JLabel labelGauche1 = new JLabel("");
		labelGauche1.setIcon(new ImageIcon("images/strong_opaque-border-left.png"));
		labelGauche1.setBounds(0, 244, 26, 164);
		panelMenu.add(labelGauche1);
		
		JLabel labelGauche2 = new JLabel("");
		labelGauche2.setIcon(new ImageIcon("images/strong_opaque-border-left.png"));
		labelGauche2.setBounds(0, 105, 26, 154);
		panelMenu.add(labelGauche2);
		
		JLabel labelGauche3 = new JLabel("");
		labelGauche3.setIcon(new ImageIcon("images/strong_opaque-border-left.png"));
		labelGauche3.setBounds(0, 23, 26, 82);
		panelMenu.add(labelGauche3);
		
		JLabel labelGauche4 = new JLabel("");
		labelGauche4.setIcon(new ImageIcon("images/strong_opaque-border-left.png"));
		labelGauche4.setBounds(0, 402, 26, 55);
		panelMenu.add(labelGauche4);
		
		JLabel labelDroit1 = new JLabel("");
		labelDroit1.setIcon(new ImageIcon("images/strong_opaque-border-right.png"));
		labelDroit1.setBounds(571, 23, 26, 154);
		panelMenu.add(labelDroit1);
		
		JLabel labelDroit2 = new JLabel("");
		labelDroit2.setIcon(new ImageIcon("images/strong_opaque-border-right.png"));
		labelDroit2.setBounds(571, 175, 27, 154);
		panelMenu.add(labelDroit2);
		
		JLabel labelDroit3 = new JLabel("");
		labelDroit3.setIcon(new ImageIcon("images/strong_opaque-border-right.png"));
		labelDroit3.setBounds(571, 325, 26, 88);
		panelMenu.add(labelDroit3);
		
		JLabel labelDroit4 = new JLabel("");
		labelDroit4.setIcon(new ImageIcon("images/strong_opaque-border-right.png"));
		labelDroit4.setBounds(571, 412, 26, 45);
		panelMenu.add(labelDroit4);
		
		JLabel topLabel1 = new JLabel("");
		topLabel1.setIcon(new ImageIcon("images/strong_opaque-border-top.png"));
		topLabel1.setBounds(24, 0, 274, 26);
		panelMenu.add(topLabel1);
		
		JLabel topLabel2 = new JLabel("");
		topLabel2.setIcon(new ImageIcon("images/strong_opaque-border-top.png"));
		topLabel2.setBounds(298, 0, 281, 26);
		panelMenu.add(topLabel2);
		
		JLabel bottomLabel1 = new JLabel("");
		bottomLabel1.setIcon(new ImageIcon("images/strong_opaque-border_bottom.png"));
		bottomLabel1.setBounds(24, 455, 274, 29);
		panelMenu.add(bottomLabel1);
		
		JLabel bottomLabel2 = new JLabel("");
		bottomLabel2.setIcon(new ImageIcon("images/strong_opaque-border_bottom.png"));
		bottomLabel2.setBounds(298, 455, 274, 29);
		panelMenu.add(bottomLabel2);
		
		JLabel labelNombreJoueur = new JLabel("Nombre de joueur :");
		labelNombreJoueur.setBackground(Color.WHITE);
		labelNombreJoueur.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
		labelNombreJoueur.setForeground(Color.WHITE);
		labelNombreJoueur.setBounds(36, 144, 125, 29);
		panelMenu.add(labelNombreJoueur);
		
		JSpinner spinnerNombreJoueur = new JSpinner();
		spinnerNombreJoueur .setModel(new SpinnerNumberModel(2, 2, 4, 1));
		spinnerNombreJoueur .setBounds(170, 145, 68, 26);
		panelMenu.add(spinnerNombreJoueur);
		
		JButton boutonNombreJoueur = new JButton("");
		boutonNombreJoueur.setBorder(UIManager.getBorder("Button.border"));
		boutonNombreJoueur.setIcon(new ImageIcon("images/checkbox-active@2x.png"));
		boutonNombreJoueur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer s=(Integer)spinnerNombreJoueur.getValue();
				switch(s) {
				case 2: 
					afficherDeuxJoueurs();
					
					break;
		        case 3 :
		            afficherTroisJoueurs();
		            break;
		        case 4 :
		    	    afficherQuatreJoueurs();
		        	break;
				}
		        SwingUtilities.updateComponentTreeUI(panelMenu);
			}
		});
		
		boutonNombreJoueur.setBounds(278, 140, 40, 36);
		panelMenu.add(boutonNombreJoueur);
		
		JButton boutonValider = new JButton("");
		boutonValider.setBorder(UIManager.getBorder("Button.border"));
		boutonValider.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
		boutonValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Hello World");
			}
		});
		boutonValider.setIcon(new ImageIcon("images/menu_button_small_H18-active@2x-valider.jpg"));
		boutonValider.setBounds(443, 401, 109, 34);
		panelMenu.add(boutonValider);
		
		JLabel labelBackground1 = new JLabel("");
		labelBackground1.setBounds(26, 26, 274, 154);
		panelMenu.add(labelBackground1);
		labelBackground1.setIcon(new ImageIcon("images/strong_opaque-background.png"));
		
		JLabel labelBackground2 = new JLabel("");
		labelBackground2.setIcon(new ImageIcon("images/strong_opaque-background.png"));
		labelBackground2.setBounds(26, 180, 274, 154);
		panelMenu.add(labelBackground2);
		
		JLabel labelBackground3 = new JLabel("");
		labelBackground3.setIcon(new ImageIcon("images/strong_opaque-background.png"));
		labelBackground3.setBounds(297, 26, 274, 154);
		panelMenu.add(labelBackground3);
		
		JLabel labelBackground4 = new JLabel("");
		labelBackground4.setIcon(new ImageIcon("images/strong_opaque-background.png"));
		labelBackground4.setBounds(297, 180, 274, 154);
		panelMenu.add(labelBackground4);
		
		JLabel labelBackground5 = new JLabel("");
		labelBackground5.setIcon(new ImageIcon("images/strong_opaque-background.png"));
		labelBackground5.setBounds(26, 334, 274, 122);
		panelMenu.add(labelBackground5);
		
		JLabel labelBackground6 = new JLabel("");
		labelBackground6.setIcon(new ImageIcon("images/strong_opaque-background.png"));
		labelBackground6.setBounds(297, 334, 274, 122);
		panelMenu.add(labelBackground6);

		JLabel backgroundLabel = new JLabel("");
		backgroundLabel.setIcon(new ImageIcon("images/liberty.jpg"));
		backgroundLabel.setBounds(0, 0, 1296, 753);
		frame.getContentPane().add(backgroundLabel);
	}
	
	public void afficherPanelBackground() {
		int x = 26;
		int y = 26;
		int width = 274;
		int height = 154;
		
		for (int i = 1; i <= 3; i++) {
			x = 26;
			for (int j = 1; j <= 2; j++) {
			
				JLabel labelBackground = new JLabel("");
				labelBackground.setIcon(new ImageIcon("images/strong_opaque-background.png"));
				labelBackground.setBounds(x, y, width, height);
				panelMenu.add(labelBackground);
				
				x += 271;
			}
		}
	}
	
	public void afficherDeuxJoueurs() {
		JLabel labelJoueur1 = new JLabel("JOUEUR 1 :");
		labelJoueur1.setForeground(Color.WHITE);
		labelJoueur1.setVisible(true);
		labelJoueur1.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
		labelJoueur1.setBounds(36, 198, 111, 29);
		panelMenu.add(labelJoueur1);
		
		pseudoJoueur1 = new JTextField();
		pseudoJoueur1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 13));
		pseudoJoueur1.setBorder(null);
		pseudoJoueur1.setText("Pseudo1");
		pseudoJoueur1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				pseudoJoueur1.setText("");
				
			}
		});
		pseudoJoueur1.setBounds(170, 202, 187, 20);
		panelMenu.add(pseudoJoueur1);
		pseudoJoueur1.setColumns(10);
		
		JComboBox<String> cbjoueur1 = new JComboBox<String>();
		cbjoueur1.setBounds(387, 201, 58, 22);
		panelMenu.add(cbjoueur1);
		
		JLabel checkbox1 = new JLabel("New label");
		checkbox1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int checked=0;
				if (checked==0) {
					checkbox1.setIcon(new ImageIcon("images/checkbox-active-pressed.png"));
				checked=1;
				}
				if (checked==1) {
					checkbox1.setIcon(new ImageIcon("images/checkbox-active.png"));
					checked=0;
					}
				
				}
		});
		checkbox1.setIcon(new ImageIcon("images/checkbox-active.png"));
		checkbox1.setBounds(487, 198, 20, 26);
		panelMenu.add(checkbox1);
		
		JLabel labelJoueur2 = new JLabel("JOUEUR 2 :");
		labelJoueur2.setForeground(Color.WHITE);
		labelJoueur2.setVisible(true);
		labelJoueur2.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
		labelJoueur2.setBounds(36, 250, 111, 29);
		panelMenu.add(labelJoueur2);
		
		pseudoJoueur2 = new JTextField();
		pseudoJoueur2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 13));
		pseudoJoueur2.setText("Pseudo2");
		pseudoJoueur2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				pseudoJoueur2.setText("");
			}
		});
		pseudoJoueur2.setColumns(10);
		pseudoJoueur2.setBounds(170, 254, 187, 20);
		panelMenu.add(pseudoJoueur2);
		
		JComboBox<String> cbJoueur2 = new JComboBox<String>();
		cbJoueur2.setBounds(387, 253, 58, 22);
		panelMenu.add(cbJoueur2);
		
		JLabel checkbox2 = new JLabel("New label");
		checkbox2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				checkbox2.setIcon(new ImageIcon("images/checkbox-active-pressed.png"));
			}
		});
		checkbox2.setIcon(new ImageIcon("images/checkbox-active.png"));
		checkbox2.setBounds(487, 251, 20, 26);
		panelMenu.add(checkbox2);
	}
	
	
	public void afficherTroisJoueurs() {
		afficherDeuxJoueurs();
		
		JLabel labelJoueur3 = new JLabel("JOUEUR 3 :");
		labelJoueur3.setForeground(Color.WHITE);
		labelJoueur3.setVisible(true);
		labelJoueur3.setFont(new Font("Times New Roman", Font.BOLD, 12));
		labelJoueur3.setBounds(36, 300, 111, 29);
		panelMenu.add(labelJoueur3);
		pseudoJoueur3 = new JTextField();
		pseudoJoueur3.setText("Pseudo3");
		pseudoJoueur3.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				pseudoJoueur3.setText("");
			}
		});
		pseudoJoueur3.setColumns(10);
		pseudoJoueur3.setBounds(170, 306, 187, 20);
		panelMenu.add(pseudoJoueur3);
		JComboBox<String> cbJoueur3 = new JComboBox<String>();
		cbJoueur3.setBounds(387, 305, 58, 22);
		panelMenu.add(cbJoueur3);
		JLabel casecoche3 = new JLabel("New label");
		casecoche3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				casecoche3.setIcon(new ImageIcon("images/checkbox-active-pressed.png"));
			}
		});
		casecoche3.setIcon(new ImageIcon("images/checkbox-active.png"));
		casecoche3.setBounds(487, 301, 20, 26);
		panelMenu.add(casecoche3);	
	}
	
	public void afficherQuatreJoueurs() {
		afficherTroisJoueurs();
		JLabel labelJoueur4 = new JLabel("JOUEUR 4 :");
		labelJoueur4.setForeground(Color.WHITE);
		labelJoueur4.setVisible(true);
		labelJoueur4.setFont(new Font("Times New Roman", Font.BOLD, 12));
		labelJoueur4.setBounds(36, 345, 111, 29);
		panelMenu.add(labelJoueur4);
		pseudoJoueur4 = new JTextField();
		pseudoJoueur4.setText("Pseudo4");
		pseudoJoueur4.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				pseudoJoueur4.setText("");
			}
			
		});
		pseudoJoueur4.setColumns(10);
		pseudoJoueur4.setBounds(170, 358, 187, 20);
		panelMenu.add(pseudoJoueur4);
		JComboBox<String> cbJoueur4 = new JComboBox<String>();
		cbJoueur4.setBounds(387, 357, 58, 22);
		panelMenu.add(cbJoueur4);
		
		JLabel casecoche4 = new JLabel("New label");
		casecoche4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				casecoche4.setIcon(new ImageIcon("images/checkbox-active-pressed.png"));
			}
		});
		casecoche4.setIcon(new ImageIcon("images/checkbox-active.png"));
		casecoche4.setBounds(487, 345, 20, 26);
		panelMenu.add(casecoche4);
	}
}
