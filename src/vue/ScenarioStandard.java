package modele;

import java.util.ArrayList;

public class ScenarioStandard {
	private String nom;
	private String description;
	private ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
	
	public ScenarioStandard(String nom, String description, ArrayList<Joueur> joueurs) {
		this.nom = nom;
		this.description = description;
		
		this.joueurs = joueurs;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	/** Scenario standard **/
	public void appliquerScenario1() {
		while (joueurs.size() > 1) {
			for (Joueur joueur : joueurs) {
				if (joueur.getNombreSoldat() == 0) {
					joueurs.remove(joueur);
				}
			}
		}
		System.out.println("Fin du jeu");
		System.out.println("Félicitations, " + joueurs.get(0).getNomJoueur() + " a gagné");
	}
	
	public void appliquerScenario5() {
		for(int i = 0; i < joueurs.size(); i++) {
			for(int j = 0; j <joueurs.get(i).getAdversaires.)
		}
		
	}

	public ArrayList<Joueur> getJoueurs() {
		return joueurs;
	}

	public void setJoueurs(ArrayList<Joueur> joueurs) {
		this.joueurs = joueurs;
	}
	
	
}
