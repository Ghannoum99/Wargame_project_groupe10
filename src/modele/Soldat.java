package modele;

public class Soldat extends Piece {
	private String typeSoldat;
	private int attaque;
	private int defense;
	private int deplacement;
	private int vision;
	private int pv;
	
	public Soldat(int abscisse, int ordonnees, String image, String typeSoldat, int attaque, int defense,
			int deplacement, int vision, int pv) {
		super(abscisse, ordonnees, image);
		this.typeSoldat = typeSoldat;
		this.attaque = attaque;
		this.defense = defense;
		this.deplacement = deplacement;
		this.vision = vision;
		this.pv = pv;
	}
	
	public String getTypeSoldat() {
		return typeSoldat;
	}
	public void setTypeSoldat(String typeSoldat) {
		this.typeSoldat = typeSoldat;
	}

	public int getAttaque() {
		return attaque;
	}
	public void setAttaque(int attaque) {
		this.attaque = attaque;
	}
	public int getDefense() {
		return defense;
	}
	public void setDefense(int defense) {
		this.defense = defense;
	}
	public int getDeplacement() {
		return deplacement;
	}
	public void setDeplacement(int deplacement) {
		this.deplacement = deplacement;
	}
	public int getVision() {
		return vision;
	}
	public void setVision(int vision) {
		this.vision = vision;
	}
	public int getPv() {
		return pv;
	}
	public void setPv(int pv) {
		this.pv = pv;
	}

}
