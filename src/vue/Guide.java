package vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

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
		this.setBounds(250, 610, 840, 80);
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
		labelIcon.setBounds(400, -70, 200, 200);

		this.add(labelTitre);
		this.add(labelIndications);
		this.add(labelIndications2);
		this.add(labelIndications3);
		this.add(boutonValider);
	}

	public void afficherIndicationsDeplacement(int numJoueur) {
		if (aValideCompetence(numJoueur, 0)) {
			if (Arrays.stream(this.getComponents()).anyMatch(this.labelIcon::equals)) {
				this.remove(labelIcon);
			}

			labelTitre.setText("Indications");
			labelTitre.setFont(new Font("Times New Roman", Font.PLAIN, 18));
			labelTitre.setForeground(new Color(200, 173, 10));
			labelTitre.setBounds(10, -10, 700, 50);

			labelIndications2.setVisible(false);
			labelIndications.setVisible(true);
			labelIndications.setText("<html>Pour pouvoir d�placer un soldat, vous devez cliquer "
					+ "sur l'hexagone sur lequel <br>vous voulez qu'il se rende.</html>");
			labelIndications.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			labelIndications.setForeground(Color.white);
			labelIndications.setBounds(10, 20, 700, 50);

			boutonValider.setVisible(false);
			validerCompetence(numJoueur, 1);
		}
	}

	public void afficherIndicationsDeplacement2(int numJoueur) {
		if (aValideCompetence(numJoueur, 1)) {
			labelIndications.setText("<html>ATTENTION : un soldat ne peut se d�placer que si ses points de d�placements <br>"
					+ "addition� au bonus de d�placement du terrain sur lequel il se trouve le permettent.</html>");
	
			boutonValider.setVisible(true);
			for (ActionListener actionL : boutonValider.getActionListeners()) {
				boutonValider.removeActionListener(actionL);
			}
	
			labelIndications2.setText("<html>Pour avoir des informations sur un terrain, il suffit de passer la souris sur <br>l'un de ses hexagones.</html>");
			
			boutonValider.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
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
			labelIndications2.setText("<html>Vous trouverez toutes les informations du soldat s�lectionn� � droite.</html>");
			ImageIcon imageIcon = new ImageIcon(new ImageIcon("images/fleches/fleche_droite.gif").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
			labelIcon.setIcon(imageIcon);
			for (ActionListener actionL : boutonValider.getActionListeners()) {
				boutonValider.removeActionListener(actionL);
			}
			boutonValider.setVisible(true);
			ImageIcon imageFond = new ImageIcon("images/button_small_copper_H22-active.png");
			boutonValider.setIcon(imageFond);
			boutonValider.setBounds(410, 65, 50, 20);
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

		labelIndications.setText("<html>Le jeu est compos� de cinq terrains distincts et des soldats des diff�rents joueurs.<br>"
				+ "Tous les joueurs disposent de 10 soldats au lancement du jeu. </html>");
		labelIndications.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		labelIndications.setForeground(Color.white);
		labelIndications.setBounds(10, 20, 500, 50);	

		labelIndications2.setText("<html>Pour gagner la partie, il suffit de r�pondre aux crit�res du sc�nario que vous <br>venez de choisir, avant les autres joueurs.</html>");
		labelIndications2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		labelIndications2.setForeground(Color.white);
		labelIndications2.setBounds(10, 20, 500, 50);
		labelIndications2.setVisible(false);

		boutonValider.setText("Suivant");
		boutonValider.setBorder(UIManager.getBorder("Button.border"));
		boutonValider.setIcon(new ImageIcon("images/large-button-active.png"));
		boutonValider.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		boutonValider.setForeground(Color.white);
		boutonValider.setBounds(470,65, 100, 22);
		boutonValider.setHorizontalTextPosition(JButton.CENTER);
		boutonValider.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				labelIndications.setVisible(false);
				labelIndications2.setVisible(true);
				boutonValider.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						labelIndications2.setText("<html>Pour pouvoir r�aliser une action avec l'un de vos soldats, vous devez tout "
								+ "d'abord <br>le s�lectionner en cliquant dessus.</html>");
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
