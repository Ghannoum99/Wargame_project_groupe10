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
			//gagnant = null;
			System.out.println("scenario1");
			gagnant = appliquerScenario1();
		}
		else if(appliquerScenario2(joueur) != null) {
			gagnant = null;
			System.out.println("scenario2");
			gagnant = appliquerScenario2(joueur);
		}
		
		else if(appliquerScenario3(joueur) != null) {
			gagnant = null;
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

        for(Joueur joueur : joueurs_copie) {
            if(joueur.getNombreSoldat() == 0)
            {
                joueurs_copie.remove(joueur);
                System.out.println(joueur);
            }
            
            if(joueurs_copie.size() == 1) {
                gagnant = joueurs_copie.get(0);
                System.out.println(gagnant);
            }
        }
        

        return gagnant;
    }
	
	/** TUEZ TOUTES LES INFANTERIES LOURDES DE TOUS LES ADVERSAIRES **/
	public Joueur appliquerScenario2(Joueur joueur) {
		Joueur gagnant = null;
		int atueToutesLesInfanteries = 0;
		for(int i = 0; i<joueurs.size(); i++)
		{
			if(joueur.getNomJoueur().equals(joueurs.get(i).getNomJoueur()))
			{
	        	for(Joueur joueurA : joueur.getAdversaires()) {
	            	if (joueur.nombreSoldatsTuesType(joueurA, "infanterieLourde") == 2) {
	                	atueToutesLesInfanteries++;
	                }   
	            } 
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
			System.out.println(joueur.getNomJoueur());
			gagnant = joueur;
			//System.out.println(gagnant.getNomJoueur());
		}
		
		return gagnant;
	}
	
	
	public boolean aTueCinqSoldats(Joueur joueur) {
		int cmpt = 0;
		boolean gagne = false;
		
		for(int i = 0; i < joueurs.size(); i++)
		{
			if(joueur.getNomJoueur().equals(joueurs.get(i).getNomJoueur()))
			{
				for(int j = 0; j < joueur.getAdversaires().size(); j++) {
					
					for(Soldat soldat : joueur.getAdversaires().get(j).getSoldatList())
					{
						//tester si il a tué un soldat
						if(joueur.aTueUnSoldat(soldat)) {
							cmpt++;
						}
						if(cmpt >= 5) {
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
