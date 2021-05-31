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
		int min=0;
		int max=10;
		Random random = new Random();
		int numsoldat = random.nextInt(max+min)+min;
		return numsoldat ;
		
	}
	
	public int choisirHexagone(ArrayList<Hexagone> labelsHexagonesVisions,Soldat soldat)
	{ 	
		
		int nbhexagonesPossibles=labelsHexagonesVisions.size();
		int min=0;
		int max=nbhexagonesPossibles;
		Random random = new Random();
		int Numhexagone = random.nextInt(max+min)+min;
		int hexagone=getHexagone().get(Numhexagone).getId();
		return hexagone;
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
