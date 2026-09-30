package cartes;

public abstract class Probleme extends Carte {
	private Type type;

	protected Probleme(Type type) {
		super();
		this.type = type;
	}

	public Type getType() {
		return type;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!super.equals(obj)) {
			return false;
		}
		Probleme probleme = (Probleme) obj;
		return type == probleme.type;
	}

	@Override
	public int hashCode() {
		return 31 * super.hashCode()
				+ (type == null ? 0 : type.hashCode());
	}
	
	
}
