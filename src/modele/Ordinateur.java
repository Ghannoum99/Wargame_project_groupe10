package modele;

import java.util.ArrayList;
import java.util.Random;
import modele.*;


public class Ordinateur  extends Joueur{

	private ArrayList<Hexagone> Hexagone= new ArrayList<Hexagone>();
	private ArrayList<Terrain> Terrain= new ArrayList<Terrain>();
	
	public Ordinateur(String nomJoueur, ArrayList<Soldat> soldats, int score, String image,
			ArrayList<Joueur> adversaires, ArrayList<modele.Hexagone> hexagone, ArrayList<Terrain> terrain) {
		super(nomJoueur, soldats, score, image, adversaires);
		Hexagone = hexagone;
		Terrain = terrain;
	}
	
	public ArrayList<Terrain> getTerrain() {
		return Terrain;
	}

	public void setTerrain(ArrayList<Terrain> terrain) {
		Terrain = terrain;
	}

	public ArrayList<Hexagone> getHexagone() {
		return Hexagone;
	}

	public void setHexagone(ArrayList<Hexagone> hexagone) {
		Hexagone = hexagone;
	}

	public int choisirSoldat(ArrayList<Soldat> soldats)
	{ 	
		int i;
		int min=0;
		int max=10;
		Random random = new Random();
		int numsoldat = random.nextInt(max+min)+min;
		
		return numsoldat ;
		
	}
	public void deplacerSoldat(ArrayList<Soldat> soldats,ArrayList<modele.Hexagone> hexagone)
	{
		int numSoldat,i;
		int x=0,y=0; // pour le decalage entre hexagone et soldat
		numSoldat=choisirSoldat(soldats);
		for(i=0;i<hexagone.size();i++)
		{  int Abscisse=0;
			int Ordonnees=0;
			if(hexagone.get(i).getUnits().isEmpty())//il faut quelle soit une case libre 
			{
				//il faut quelle soit dans l'intervalle de deplacement de soldat inferieur  ou égale a la vision de soldat
				if(((hexagone.get(i).getAbscisse()-soldats.get(numSoldat).getVision())<=soldats.get(numSoldat).getAbscisse())
				  &&
				  ((hexagone.get(i).getOrdonnees()-soldats.get(numSoldat).getVision())<=soldats.get(numSoldat).getOrdonnees()))
				Abscisse=hexagone.get(i).getAbscisse();
				soldats.get(numSoldat).setAbscisse(Abscisse+x);
				Ordonnees=hexagone.get(i).getOrdonnees();// il ya un decalage entre ab et or de hexagone et de soldat à vérifier 
				soldats.get(numSoldat).setOrdonnees(Ordonnees+y);
			}
		}
		
			
	}
	
	public void attacAdversaire(ArrayList<Soldat> soldats,ArrayList<Joueur> adversaires,ArrayList<Hexagone> hexagone)
	{
		int numSoldat;
		int i, j,k;
		numSoldat=choisirSoldat(getSoldats());
		for(i=0;i<adversaires.size();i++)
		{
			for(j=0;j<hexagone.size();j++)
			{
				if(hexagone.get(j).contientEnnemi(adversaires.get(i)))
				{  
					for(k=0;k<adversaires.get(i).getSoldatList().size();k++)
					
					if((soldats.get(numSoldat).getVision()<=(adversaires.get(i).getSoldatList().get(k).getDeplacement()+soldats.get(numSoldat).getVision())))
					if(soldats.get(numSoldat).getPv()>adversaires.get(i).getSoldatList().get(k).getPv())
					{
						// attacker degat de point de vie
					}
					
				}
				
				
			}
			
			
		}
			
		
		
	}
	/*************************************************************************************/
	/** FONCTION POUR RECHERCHE SI IL Y A UNE VILLAGE DISPONIBLE POUR SOIGNE LE SOLDAT **/
	/***********************************************************************************/
	public int rechercherVillage(ArrayList<Hexagone> hexagone,ArrayList<Soldat> soldats)
	{
		int etatVillage = 0,j,k;
		for(j=0;j<hexagone.size();j++)
		{
			if(hexagone.get(j).getTypeTerrain().equals("Village")&&hexagone.get(j).getUnits().isEmpty())
			{
				etatVillage=j;
				return j;//cette village est disponible 
			}
			else 
			{
				for(k=0;k<soldats.size();k++)
				{
					if(soldats.get(k).getAbscisse()==hexagone.get(j).getAbscisse()
						&&soldats.get(k).getOrdonnees()==hexagone.get(j).getOrdonnees()
						&&hexagone.get(j).getTypeTerrain().equals("Village"))
					{
						etatVillage=-1;
						return -1;// il y a aucun village disponible
					}
						
				}
				
			}
		}
		return etatVillage ;
	}
		
	
	
	
	

}
