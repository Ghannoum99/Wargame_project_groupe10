package modele;

public class Terrain extends Piece {
	private String typeTerrain;
	private int pointDeplacement;
	private float bonusDefense;
	
	public Terrain (int abscisse, int ordonnees, String image, String typeTerrain, int pointDeplacement, float bonusDefense) {
		super(abscisse, ordonnees, image);
		this.typeTerrain = typeTerrain;
		this.pointDeplacement = pointDeplacement;
		this.bonusDefense = bonusDefense;
	}
	
	public String getTypeTerrain() {
		return typeTerrain;
	}
	
	public void setTypeTerrain(String typeTerrain) {
		this.typeTerrain = typeTerrain;
	}
	
	public int getPointDeplacement() {
		return pointDeplacement;
	}
	
	public void setPointDeplacement(int pointDeplacement) {
		this.pointDeplacement = pointDeplacement;
	}
	
	public float getBonusDefense() {
		return bonusDefense;
	}
	
	public void setBonusDefense(float bonusDefense) {
		this.bonusDefense = bonusDefense;
	}
	
}

