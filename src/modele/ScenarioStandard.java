package modele;

import java.util.List;

public class ScenarioStandard {
	private String nom;
	private String description;
	
	public ScenarioStandard(String nom, String description) {
		this.nom = nom;
		this.description = description;
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
	
	protected void appliquerScenarioStandard(List<Joueur> joueurs) {
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
}
