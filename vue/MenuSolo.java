package vue;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

import java.awt.event.*;

@SuppressWarnings("serial")
public class MenuSolo extends JFrame {
	private JPanel contentPane;
	private JFrame frame;
	private JTextField pseudoJoueur;

	public  MenuSolo() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1300, 781);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelMenu = new JPanel();
		panelMenu.setOpaque(false);
		//panel.setForeground(new Color(16,22,36));
		panelMenu.setBackground(Color.WHITE);
		panelMenu.setBounds(364, 174, 503, 441);
		contentPane.add(panelMenu);
		panelMenu.setLayout(null);
		
		JLabel labelLogo = new JLabel("");
		labelLogo.setIcon(new ImageIcon("images/wesnoth-icon.png"));
		labelLogo.setBounds(188, 0, 128, 128);
		panelMenu.add(labelLogo);
		
		JLabel coinSupDroit = new JLabel("");
		coinSupDroit.setIcon(new ImageIcon("images/strong_opaque-border-topright.png"));
		coinSupDroit.setBounds(478, 0, 27, 26);
		panelMenu.add(coinSupDroit);
		
		JLabel coinSupGauche = new JLabel("");
		coinSupGauche.setIcon(new ImageIcon("images/strong_opaque-border-topleft.png"));
		coinSupGauche.setBounds(0, 0, 26, 29);
		panelMenu.add(coinSupGauche);
		
		JLabel coinInfGauche = new JLabel("");
		coinInfGauche.setIcon(new ImageIcon("images/strong_opaque-border-botleft.png"));
		coinInfGauche.setBounds(0, 412, 26, 29);
		panelMenu.add(coinInfGauche);
		
		JLabel coinInfDroit = new JLabel("");
		coinInfDroit.setIcon(new ImageIcon("images/strong_opaque-border-botright.png"));
		coinInfDroit.setBounds(478, 412, 27, 29);
		panelMenu.add(coinInfDroit);
		
		JLabel labelGauche1 = new JLabel("");
		labelGauche1.setIcon(new ImageIcon("images/strong_opaque-border-left.png"));
		labelGauche1.setBounds(0, 259, 26, 154);
		panelMenu.add(labelGauche1);
		
		JLabel labelGauche2 = new JLabel("");
		labelGauche2.setIcon(new ImageIcon("images/strong_opaque-border-left.png"));
		labelGauche2.setBounds(0, 105, 26, 154);
		panelMenu.add(labelGauche2);
		
		JLabel labelGauche3 = new JLabel("");
		labelGauche3.setIcon(new ImageIcon("images/strong_opaque-border-left.png"));
		labelGauche3.setBounds(0, 23, 26, 82);
		panelMenu.add(labelGauche3);
		
		JLabel labelDroit1 = new JLabel("");
		labelDroit1.setIcon(new ImageIcon("images/strong_opaque-border-right.png"));
		labelDroit1.setBounds(478, 23, 26, 154);
		panelMenu.add(labelDroit1);
		
		JLabel labelDroit2 = new JLabel("");
		labelDroit2.setIcon(new ImageIcon("images/strong_opaque-border-right.png"));
		labelDroit2.setBounds(478, 172, 27, 154);
		panelMenu.add(labelDroit2);
		
		JLabel labelDroit3 = new JLabel("");
		labelDroit3.setIcon(new ImageIcon("images/strong_opaque-border-right.png"));
		labelDroit3.setBounds(478, 325, 26, 88);
		panelMenu.add(labelDroit3);
		
		JLabel topLabel1 = new JLabel("");
		topLabel1.setIcon(new ImageIcon("images/strong_opaque-border-top.png"));
		topLabel1.setBounds(24, 0, 274, 26);
		panelMenu.add(topLabel1);
		
		JLabel topLabel2 = new JLabel("");
		topLabel2.setIcon(new ImageIcon("images/strong_opaque-border-top.png"));
		topLabel2.setBounds(298, 0, 180, 26);
		panelMenu.add(topLabel2);
		
		JLabel bottomLabel1 = new JLabel("");
		bottomLabel1.setIcon(new ImageIcon("images/strong_opaque-border_bottom.png"));
		bottomLabel1.setBounds(24, 412, 274, 29);
		panelMenu.add(bottomLabel1);
		
		JLabel bottomLabel2 = new JLabel("");
		bottomLabel2.setIcon(new ImageIcon("images/strong_opaque-border_bottom.png"));
		bottomLabel2.setBounds(298, 412, 180, 29);
		panelMenu.add(bottomLabel2);
		
		JLabel labelJoueur = new JLabel("Joueur :");
		labelJoueur.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		labelJoueur.setForeground(Color.WHITE);
		labelJoueur.setBounds(60, 165, 66, 26);
		panelMenu.add(labelJoueur);
		
		pseudoJoueur = new JTextField();
		pseudoJoueur.setBounds(152, 169, 167, 20);
		panelMenu.add(pseudoJoueur);
		pseudoJoueur.setColumns(10);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(351, 168, 30, 22);
		panelMenu.add(comboBox);
		
		JLabel lblNewLabel_7 = new JLabel("Ordinateur :");
		lblNewLabel_7.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblNewLabel_7.setForeground(Color.WHITE);
		lblNewLabel_7.setBounds(60, 237, 97, 34);
		panelMenu.add(lblNewLabel_7);
		
		JButton boutonValider = new JButton("");
		boutonValider.setIcon(new ImageIcon("images/btnvalider.png"));
		boutonValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		boutonValider.setBounds(291, 361, 167, 40);
		panelMenu.add(boutonValider);
		
		JButton boutonRetour = new JButton("");
		boutonRetour.setBackground(new Color(0, 0, 255));
		boutonRetour.setIcon(new ImageIcon("images/arrows_ornate_left_30-active.png"));
		boutonRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		boutonRetour.setBounds(70, 40, 21, 13);
		panelMenu.add(boutonRetour);
		
		JLabel backgroundLabel = new JLabel("");
		backgroundLabel.setIcon(new ImageIcon("images/liberty.jpg"));
		backgroundLabel.setBounds(0, 0, 1296, 772);
		frame.getContentPane().add(backgroundLabel);
	}
}
