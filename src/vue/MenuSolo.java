package vue;

/******************************************************************************************************************/                             
/* 				CLASSE HERITANT DE LA CLASSE MenuMultiJoueurs             	                  */
/* 				PAS BESOIN DE PRECISER LE NOMBRE DE JOUEURS: AUTOMATIQUEMENT 2                    */
/* 				ON A EFFACE TOUS LES WIDGET QUI SONT INUTILES                                     */
/******************************************************************************************************************/

@SuppressWarnings("serial")
public class MenuSolo extends MenuMultiJoueurs {
	public MenuSolo() {
		super();
		choix = false;
		
		effacerNombreJoueur();
		/** METTANT LE NOMBRE DE JOUEURS A 2 **/
		nombreJoueur = 2;
		pressed = true;
		afficherChampTextPseudo();
		afficherLabelNumJoueur();
		afficherCombobox();
		afficherCheckBox();
		effacerSpinner();
		effacerBoutonNombreJoueur();
		recupererPseudo();
		
		/** METTANT UN NOMBRE POUR L'ORDINATEUR **/
		listeChampText.get(1).setText("ORDINATEUR");
		listeChampText.get(1).setEditable(false);
	}
	
	/************************************/
	/** EFFACER LE LABEL NOMBRE JOUEUR **/
	/************************************/
	public void effacerNombreJoueur() {
		labelNombreJoueur.setVisible(false);
	}
	
	/********************************************************************/
	/** EFFACER LE SPINNER : AUTOMATIQUEMENT LE NOMBRE DE JOUEUR EST 2 **/
	/********************************************************************/
	public void effacerSpinner() {
		spinnerNombreJoueur.setVisible(false);
	}
	
	/*************************************/
	/** EFFACER LE BOUTON NOMBRE JOUEUR **/
	/*************************************/
	public void effacerBoutonNombreJoueur() {
		boutonNombreJoueur.setVisible(false);
	}

}
