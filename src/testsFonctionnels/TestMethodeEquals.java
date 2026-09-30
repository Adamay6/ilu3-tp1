package testsFonctionnels;

import cartes.Attaque;
import cartes.Borne;
import cartes.Parade;
import cartes.Type;

public class TestMethodeEquals {

	public static void main(String[] args) {
		Borne borne1 = new Borne(25);
		Borne borne2 = new Borne(25);

		Attaque feuRouge1 = new Attaque(Type.FEU);
		Attaque feuRouge2 = new Attaque(Type.FEU);

		Parade feuVert = new Parade(Type.FEU);

		System.out.println("Deux cartes de 25km sont identiques ? "
				+ borne1.equals(borne2));
		System.out.println("Deux cartes de feux rouge sont identiques ? "
				+ feuRouge1.equals(feuRouge2));
		System.out.println("La carte feu rouge et la carte feu vert sont identiques ? "
				+ feuRouge1.equals(feuVert));
	}
}