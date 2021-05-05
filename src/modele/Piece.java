package modele;

public class Piece {
	private int abscisse;
	private int ordonnees;
	private String image;

	public Piece(int abscisse, int ordonnees, String image) {
		this.abscisse = abscisse;
		this.ordonnees = ordonnees;
		this.image = image;
	}
	
	public int getAbscisse() {
		return abscisse;
	}

	public void setAbscisse(int abscisse) {
		this.abscisse = abscisse;
	}

	public int getOrdonnees() {
		return ordonnees;
	}

	public void setOrdonnees(int ordonnees) {
		this.ordonnees = ordonnees;
	}
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void deplacementDroit(int cases) {
		this.abscisse += cases;
	}
	
	public void deplacementGauche(int cases) {
		this.abscisse -= cases;
	}
	
	public void deplacementHaut(int cases) {
		this.ordonnees += cases;
	}
	
	public void deplacementBas(int cases) {
		this.ordonnees -= cases;
	}

}
