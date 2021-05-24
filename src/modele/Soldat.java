package modele;

public class Soldat extends Piece {
	private String typeSoldat;
	private int attaque;
	private int defense;
	private int deplacement;
	private int vision;
	private int pv;
	private boolean ko;
	
	public Soldat(int abscisse, int ordonnees, String image, String typeSoldat, int attaque, int defense,
			int deplacement, int vision, int pv,boolean ko) {
		super(abscisse, ordonnees, image);
		this.typeSoldat = typeSoldat;
		this.attaque = attaque;
		this.defense = defense;
		this.deplacement = deplacement;
		this.vision = vision;
		this.pv = pv;
		this.ko = ko;
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
	public boolean isKo() {
		return ko;
	}
	public void setKo(boolean ko) {
		this.ko = ko;
	}

	public void majSoldat(Soldat s)
	{
		if(pv==0)
		{
			s.setKo(true);
		}
	}

	@Override
	public String toString() {
		return "Soldat [typeSoldat=" + typeSoldat + ", attaque=" + attaque + ", defense=" + defense + ", deplacement="
				+ deplacement + ", vision=" + vision + ", pv=" + pv + ", ko=" + ko + "]";
	}

}
