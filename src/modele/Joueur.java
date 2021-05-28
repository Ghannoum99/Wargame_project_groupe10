package modele;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Joueur {
	private String nomJoueur;
	private ArrayList<Soldat> soldats = new ArrayList<Soldat>(10);
	private int score;
	private String image;
	private int[] KO = new int[10];
	
	public Joueur(String nomJoueur, ArrayList<Soldat> soldats, int score, String image) {
		this.nomJoueur = nomJoueur;
		this.soldats = soldats;
		this.score = score;
		this.image = image;
	}
	
	public void initialiserKO() {
		for (int i = 0; i < KO.length; i++) {
			KO[i] = 0;
		}
	}
	
	public String getNomJoueur() {
		return nomJoueur;
	}
	
	public void setNomJoueur(String nomJoueur) {
		this.nomJoueur = nomJoueur;
	}
	
	public List<Soldat> getSoldatList() {
		return soldats;
	}
	
	public void setSoldatList(ArrayList<Soldat> soldats) {
		this.soldats = soldats;
	}
	 
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	public int getNombreSoldat() {
		return soldats.size();
	} 
	
	public void ajouterSoldat(Soldat soldat) {
		this.soldats.add(soldat);
	}
	
	public void retirerSoldat() {
		int i = 0;
		for (Soldat soldat:soldats) {
			if (soldat.getPv() == 0) {
				KO[i] = 1;
			}
			
			i += 1;
		}
	}

	public boolean soldatExiste(Soldat soldat) {
		List<Soldat> chercheSoldat = this.soldats.stream().filter(x -> x.getId() == soldat.getId()).collect(Collectors.toList());
		return chercheSoldat.size() > 0;
	}
	
	@Override
	public String toString() {
		return "Joueur [nomJoueur=" + nomJoueur + ", soldats=" + soldats + ", score=" + score + ", image=" + image
				+ ", KO=" + Arrays.toString(KO) + "]";
	}
	
}
