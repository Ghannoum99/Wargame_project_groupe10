package modele;

import java.util.ArrayList;

public class ScenarioStandard {
	protected ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
	
	public ScenarioStandard(ArrayList<Joueur> joueurs) {
		this.joueurs = joueurs;
	}
	
	/** Scenario standard **/
	public Joueur appliquerScenario(Joueur joueur) {
		Joueur gagnant = null;
		
		if(appliquerScenario1() != null) {
			gagnant = appliquerScenario1();
		}
		
		else if(appliquerScenario2() != null) {
			gagnant = appliquerScenario2();
		}
		
		else if(appliquerScenario3(joueur) != null) {
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
		
		if(joueurs_copie.size() == 1) {
			gagnant = joueurs_copie.get(0);

		}
		else {
			for(Joueur joueur : joueurs_copie) {
				if(joueur.getNombreSoldat() == 0)
				{
					joueurs_copie.remove(joueur);
				}
			}
		}

		return gagnant;
	}
	
	/** TUEZ TOUTES LES INFANTERIES LOURDES DE TOUS LES ADVERSAIRES **/
	public Joueur appliquerScenario2() {
		Joueur gagnant = null;
		boolean atueToutesLesInfanteries = true;
        for(Joueur joueur : joueurs) {
            for(Joueur joueurA : joueur.getAdversaires()) {
                if (nombreInfanterieLourde(joueurA) == 2) {
                	atueToutesLesInfanteries = false;
                }
              
            }
            
            if(atueToutesLesInfanteries) {
            	gagnant = joueur;
            	break;
            }
        }

        return gagnant;
    }
	
	/** PERMET DE SAVOIR LE NOMBRE D'INFANTERIES LOURDES TUER PAR LE JOUEUR **/
	public int nombreInfanterieLourde(Joueur joueur) {
		int c = 0;
	    for (Soldat soldat : joueur.getSoldatList()) {
	    	if (soldat.getTypeSoldat() == "infanterieLourde") {
	    		c ++;
	        }
	    }
	    return c;
	}
	
	
	public Joueur appliquerScenario3(Joueur joueur) {
		Joueur gagnant = null;
		if(aTueCinqSoldats(joueur)) {
			gagnant = joueur;
		}
		
		return gagnant;
	}
	
	
	public boolean aTueCinqSoldats(Joueur joueur) {
		int cmpt = 0;
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
					}				
				}
				if(cmpt >= 5) {
					gagne = true;
					break;
				}
			}		
			else {
				cmpt = 0;
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
