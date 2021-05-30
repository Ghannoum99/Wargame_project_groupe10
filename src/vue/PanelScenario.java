package vue;


import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class PanelScenario extends JPanel {
	private JPanel panelPrincipal;
	public JScrollPane scrollPane;
	public JButton boutonScenarioStandard;
	public JButton boutonScenarioTempsLimite;

	public PanelScenario() {
		this.setBounds(289, 155, 699, 381);
		this.setOpaque(true);
		setLayout(null);
		
		afficherScrollPane();
		
		/** Panel Principal **/
		panelPrincipal = new JPanel();
		panelPrincipal.setOpaque(false);
		scrollPane.setViewportView(panelPrincipal);
		
		/** AFFICHER 2 BOUTONS POUR CHOISIR LE SCENARIO DU JEU **/
		afficherBoutonScenarioStandard();
		afficherBoutonScenarioTempsLimite();
	}
	
	
	/*********************************************************************************/
	/** AFFICHAGE D'UN SCROLLPANE POUR PRESENTER LES DIFFERENTS SCENARIOS POSSIBLES **/
	/*********************************************************************************/
	public void afficherScrollPane() {
		scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setOpaque(false);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(0, 0, 699, 381);
		this.add(scrollPane);
	}
	
		
	/*====================================== BOUTONS QUI PERMETTENT AUX JOUEURS DE CHOISIR LE SCENARIO ====================================== */
	
	/*****************************************************************************************************************************/
	/**         AFFICHAGE DU boutonScenarioStandard : QUI REGROUPE 4 SCENARIO                                                   **/
	/**         1. Le jeu se terminera lorsqu'un seul joueur aura au moins un soldat                                            **/
	/**         2. Si l’un des joueurs a tué successivement cinq soldats appartenant au joueur adverse alors il gagne la partie **/
	/**         3. faut atteindre le numéro de tour numéro cinq avec au moins un soldat encore en liste                         **/
	/**         4. Si l’un des joueurs tue toutes les infanteries lourdes de son adversaire, il gagne la partie                 **/
	/************************************** **************************************************************************************/	
	public void afficherBoutonScenarioStandard() {
		boutonScenarioStandard = new JButton();
		boutonScenarioStandard.setIcon(new ImageIcon("images/scenarios/jeu-standard.jpg"));
		panelPrincipal.add(boutonScenarioStandard);
	}
	
	/*********************************************************************************/
	/** AFFICHAGE DU boutonScenarioTempsLimite : PERMET DE JOUER UN MATCH DE 4 MINS **/
	/*********************************************************************************/
	public void afficherBoutonScenarioTempsLimite() {
		boutonScenarioTempsLimite = new JButton();
		boutonScenarioTempsLimite.setIcon(new ImageIcon("images/scenarios/match-de-4-mins.jpg"));
		panelPrincipal.add(boutonScenarioTempsLimite);
	}
	
	
}
