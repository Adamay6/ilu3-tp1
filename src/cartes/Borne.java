package cartes;

public class Borne extends Carte {
	private int km;

	public Borne(int km) {
		super();
		this.km = km;
	}

	@Override
	public String toString() {
		return km + "KM";
	}

	public int getKm() {
		return km;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!super.equals(obj)) {
			return false;
		}
		Borne borne = (Borne) obj;
		return km == borne.km;
	}

	@Override
	public int hashCode() {
		return 31 * super.hashCode() + km;
	}
}
