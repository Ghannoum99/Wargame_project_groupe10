package vue;


import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import java.util.ArrayList;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class PanelScenario extends JPanel {

	private JPanel panelPrincipal;
	public JScrollPane scrollPane;
	private JButton boutonImage;
	public ArrayList<JButton> listeBoutons = new ArrayList<JButton>();

	public PanelScenario() {
		this.setBounds(289, 155, 699, 381);
		this.setOpaque(true);
		setLayout(null);
		
		afficherScrollPane();
		
		/** Panel Principal **/
		panelPrincipal = new JPanel();
		panelPrincipal.setOpaque(false);
		scrollPane.setViewportView(panelPrincipal);
		
		/** AFFICHER LES 5 BOUTONS **/
		afficherBoutonScenario();

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
	
		
	/********************************************************************/
	/** AFFICHAGE DE 5 BOUTONS, CHAQUE BOUTON CORRESPOND A UN SCENRAIO **/
	/************************************** *****************************/	
	public void afficherBoutonScenario() {
		int i = 0;
		//String[] numbers= {"1", "2", "3", "4", "5"};
		String[] images = {"images/scenarios/infrantries-lourdes.jpg", "images/scenarios/match-de-4-mins.jpg", "images/scenarios/tuez-5-soldats-et-gagnez.jpg",  "images/scenarios/jeu-standard.jpg", "images/scenarios/gardez-un-soldat.jpg"};
		for(i=0; i<5; i++) {
			boutonImage = new JButton("");
			boutonImage.setIcon(new ImageIcon(images[i]));
			panelPrincipal.add(boutonImage);
			
			listeBoutons.add(boutonImage);
		}
	}
	
	
}
