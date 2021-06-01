package modele;

import java.util.ArrayList;
import java.util.Iterator;

public class ScenarioStandard {
	protected ArrayList<Joueur> joueurs = new ArrayList<Joueur>();

	public ScenarioStandard(ArrayList<Joueur> joueurs) {
		this.joueurs = joueurs;
	}

	/** Scenario standard **/
	public Joueur appliquerScenario(Joueur joueur) {
		Joueur gagnant = null;

		if(appliquerScenario1() != null) {
			System.out.println("scenario1");
			gagnant = appliquerScenario1();
		}

		else if(appliquerScenario2() != null) {
			System.out.println("scenario2");
			gagnant = appliquerScenario2();
		}

		else if(appliquerScenario3(joueur) != null) {
			System.out.println("scenario3");
			gagnant = appliquerScenario3(joueur);
		}

		return gagnant;
	}

	public Joueur appliquerScenario1() {
		ArrayList<Joueur> joueurs_copie = new ArrayList<Joueur>();
		Joueur gagnant = null;
		for(Joueur j : joueurs) {
			joueurs_copie.add(j);
		}

		Iterator<Joueur> i = joueurs_copie.iterator();
		while (i.hasNext()) {
			Joueur joueur = (Joueur) i.next();
			if(joueur.getNombreSoldat() == 0)
			{
				i.remove();
			}
		}
		if(joueurs_copie.size() == 1) {
			gagnant = joueurs_copie.get(0);
		}

		return gagnant;
	}

	/** TUEZ TOUTES LES INFANTERIES LOURDES DE TOUS LES ADVERSAIRES **/
	public Joueur appliquerScenario2() {
		Joueur gagnant = null;
		int atueToutesLesInfanteries = 0;
		for(Joueur joueur : joueurs) {
			for(Joueur joueurA : joueur.getAdversaires()) {
				if (joueur.nombreSoldatsTuesType(joueurA, "infanterieLourde") == 2) {
					atueToutesLesInfanteries++;
				}   
			} 
			if (joueur.getAdversaires().size() > 1) {
				if(atueToutesLesInfanteries == joueur.getAdversaires().size()*2) {
					gagnant = joueur;
					break;
				}
			}
			else {
				if(atueToutesLesInfanteries == joueur.getAdversaires().size()) {
					gagnant = joueur;
					break;
				}
			}
		}
		return gagnant;
	}

	public Joueur appliquerScenario3(Joueur joueur) {
		Joueur gagnant = null;
		if(aTueCinqSoldats(joueur)) {
			//System.out.println(joueur.getNomJoueur());
			gagnant = joueur;
			//System.out.println(gagnant.getNomJoueur());
		}

		return gagnant;
	}


	public boolean aTueCinqSoldats(Joueur joueur) {
		int cmpt = 0;
		int c = 0;
		boolean gagne = false;

		for(int i = 0; i < joueurs.size(); i++)
		{
			if(joueur == joueurs.get(i))
			{
				for(int j = 0; j < joueur.getAdversaires().size(); j++) {

					for(Soldat soldat : joueur.getAdversaires().get(j).getSoldatList())
					{
						//tester si il a tué un soldat
						if(joueur.aTueUnSoldat(soldat)) {
							cmpt++;
						}
						c += cmpt;
						if(c >= 5) {
							gagne = true;
							break;
						}
					}				
				}
			}		
		}	
		return gagne;
	}

	public ArrayList<Joueur> getJoueurs() {
		return joueurs;
	}

	public void setJoueurs(ArrayList<Joueur> joueurs) {
		this.joueurs = joueurs;
	}

}
