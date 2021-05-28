package vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

@SuppressWarnings("serial")
public class Guide extends JPanel {

	private JLabel labelTitre;
	private JLabel labelIndications;
	private JLabel labelIndications2;
	private JLabel labelIndications3;
	private JButton boutonValider;
	private JLabel labelIcon;
	private boolean[][] competencesAcquisesJoueurs;

	public Guide(int nbrJoueurs) {
		this.setLayout(null);
		this.setVisible(true);
		this.setBounds(250, 570, 840, 120);
		this.setOpaque(false);	

		competencesAcquisesJoueurs = new boolean[nbrJoueurs][4];
		for (int i=0; i<nbrJoueurs; i++) {
			for (int j=0; j<4; j++) {
				competencesAcquisesJoueurs[i][j] = false;
			}
		}

		this.labelTitre = new JLabel();
		this.labelIndications = new JLabel();
		this.labelIndications2 = new JLabel();
		this.labelIndications3 = new JLabel();
		this.boutonValider = new JButton();

		ImageIcon imageIcon = new ImageIcon(new ImageIcon("images/fleches/fleche_haut.gif").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
		labelIcon = new JLabel(imageIcon);
		labelIcon.setBounds(600, -70, 200, 200);

		this.add(labelTitre);
		this.add(labelIndications);
		this.add(labelIndications2);
		this.add(labelIndications3);
		this.add(boutonValider);
	}

	public void afficherIndicationsDeplacement(int numJoueur) {
		if (aValideCompetence(numJoueur, 0)) {
			labelIcon.setBounds(520, -70, 200, 200);

			labelTitre.setText("Indications");
			labelTitre.setFont(new Font("Times New Roman", Font.PLAIN, 18));
			labelTitre.setForeground(new Color(200, 173, 10));
			labelTitre.setBounds(10, -10, 700, 50);

			labelIndications2.setVisible(false);
			labelIndications.setVisible(true);
			labelIndications.setText("<html>Pour pouvoir déplacer un soldat, vous devez cliquer "
					+ "sur l'hexagone sur lequel vous voulez qu'il se rende.</html>");
			labelIndications.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			labelIndications.setForeground(Color.white);
			labelIndications.setBounds(10, 20, 700, 50);

			boutonValider.setVisible(false);
			validerCompetence(numJoueur, 1);
		}
	}

	public void afficherIndicationsDeplacement2(int numJoueur) {
		if (aValideCompetence(numJoueur, 1)) {
			labelIcon.setVisible(false);
			labelIndications.setBounds(10, 20, 680, 50);	
			labelIndications.setText("<html>ATTENTION : un soldat ne peut se déplacer que si ses points de déplacements "
					+ "additioné au bonus de déplacement du terrain sur lequel il se trouve le permettent.</html>");
	
			boutonValider.setVisible(true);
			for (ActionListener actionL : boutonValider.getActionListeners()) {
				boutonValider.removeActionListener(actionL);
			}
	
			labelIndications2.setText("<html>Pour avoir des informations sur un terrain, il suffit de passer la souris sur l'un de ses hexagones.</html>");
			
			boutonValider.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					labelIcon.setVisible(true);
					labelIcon.setBounds(480, -70, 200, 200);
					labelIndications2.setVisible(true);
					labelIndications.setVisible(false);
					add(labelIcon);
					boutonValider.setVisible(false);
					validerCompetence(numJoueur, 2);
				}
			});
		}
	}

	public void afficherIndicationsDeplacement3(int numJoueur) {
		if (aValideCompetence(numJoueur, 2)) {
			labelIcon.setBounds(330, -70, 200, 200);
			
			labelIndications2.setText("<html>Vous trouverez toutes les informations du soldat sélectionné à droite.</html>");
			ImageIcon imageIcon = new ImageIcon(new ImageIcon("images/fleches/fleche_droite.gif").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
			labelIcon.setIcon(imageIcon);
			for (ActionListener actionL : boutonValider.getActionListeners()) {
				boutonValider.removeActionListener(actionL);
			}
			boutonValider.setVisible(true);
			ImageIcon imageFond = new ImageIcon("images/button_small_copper_H22-active.png");
			boutonValider.setIcon(imageFond);
			boutonValider.setBounds(750, 65, 50, 20);
			boutonValider.setFont(new Font("Times New Roman", Font.PLAIN, 11));
			boutonValider.setText("OK");
			boutonValider.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					labelIndications.setVisible(false);
					labelIndications2.setVisible(false);
					labelIcon.setVisible(false);
					validerCompetence(numJoueur, 3);
					boutonValider.setVisible(false);
				}
			});
		}
	}

	public void afficherIndicationsSelection(int numJoueur) {
		labelTitre.setText("Bienvenue sur WarGame !");
		labelTitre.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		labelTitre.setForeground(new Color(200, 173, 10));
		labelTitre.setBounds(10, -10, 700, 50);

		labelIndications.setText("<html>Le jeu est composé de cinq terrains distincts et des soldats des différents joueurs."
				+ " Tous les joueurs disposent de 10 soldats au lancement du jeu. </html>");
		labelIndications.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		labelIndications.setForeground(Color.white);
		labelIndications.setBounds(10, 20, 690, 50);	

		labelIndications2.setText("<html>Pour gagner la partie, il suffit de répondre aux critères du scénario que vous venez de choisir, avant les autres joueurs.</html>");
		labelIndications2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		labelIndications2.setForeground(Color.white);
		labelIndications2.setBounds(10, 20, 690, 50);
		labelIndications2.setVisible(false);

		boutonValider.setText("Suivant");
		boutonValider.setBorder(UIManager.getBorder("Button.border"));
		boutonValider.setIcon(new ImageIcon("images/large-button-active.png"));
		boutonValider.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		boutonValider.setForeground(Color.white);
		boutonValider.setBounds(700,65, 100, 22);
		boutonValider.setHorizontalTextPosition(JButton.CENTER);
		boutonValider.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				labelIndications.setVisible(false);
				labelIndications2.setVisible(true);
				boutonValider.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						labelIndications2.setText("<html>Pour pouvoir réaliser une action avec l'un de vos soldats, vous devez tout "
								+ "d'abord le sélectionner en cliquant dessus.</html>");
						boutonValider.setVisible(false);
						add(labelIcon);
						validerCompetence(numJoueur, 0);
					}
				});
			}
		});

	}

	public void validerCompetence(int numJoueur, int numCompetence) {
		competencesAcquisesJoueurs[numJoueur][numCompetence] = true;
	}

	public boolean aValideCompetence(int numJoueur, int numCompetence) {
		return competencesAcquisesJoueurs[numJoueur][numCompetence];
	}
}
