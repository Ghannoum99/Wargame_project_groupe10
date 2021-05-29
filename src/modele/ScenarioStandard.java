package modele;

import java.util.ArrayList;

public class ScenarioStandard {
	private ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
	
	public ScenarioStandard(ArrayList<Joueur> joueurs) {
		this.joueurs = joueurs;
	}
	
	
	/** Scenario standard **/
	public boolean appliquerScenario(Joueur joueur) {
		return (appliquerScenario1(joueur) || appliquerScenario4(joueur) || appliquerScenario5());
	}
	
	public boolean appliquerScenario1(Joueur joueur) {
		//Joueur gagnant = new Joueur(" ", new ArrayList<Soldat>(), 0, " ", new ArrayList<Joueur>());
		boolean termine = false;
		for(Joueur j : joueurs) {
			if(joueur == j && joueur.getNombreSoldat() == 0)
			{
				termine = true;
			}
		}
		
		return termine;
	}
	
	public boolean appliquerScenario5() {
		boolean termine = false;
        for(Joueur joueur : joueurs) {
            for(Joueur joueurA : joueur.getAdversaires()) {
                if ( nombreInfanterieLourde(joueurA) == 2) {
                	termine = true;
                	//joueursGagnant.add(joueur);
                	break;
                }
            }
        }
       
        return termine;
    }
	
	public int nombreInfanterieLourde(Joueur joueur) {
		int c = 0;
	    for (Soldat soldat : joueur.getSoldatList()) {
	    	if (soldat.getTypeSoldat() == "infanterieLourde") {
	    		c ++;
	        }
	    }
	    return c;
	}
	
	public boolean appliquerScenario4(Joueur joueur) {
		boolean termine = false;
		if(aTueCinqSoldats(joueur)) {
			termine = true;
		}
		
		return termine;
	}
	
	public boolean aTueCinqSoldats(Joueur joueur) {
		int cmpt = 0;
		boolean gagne = false;
		
		for(int i = 0; i < joueurs.size(); i++)
		{
			if(joueur == joueurs.get(i))
			{
				for(Soldat soldat : joueur.getAdversaires().get(i).getSoldatList())
				{
					//tester si il a tué un soldat
					if(joueur.aTueUnSoldat(soldat));
					cmpt++;
				}
				
				break;
			}
			
			else {
				cmpt = 0;
			}	
		}
		
		if(cmpt == 5) {
			gagne = true;
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