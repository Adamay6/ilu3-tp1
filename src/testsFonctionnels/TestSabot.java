package testsFonctionnels;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import cartes.Botte;
import cartes.Carte;
import cartes.JeuDeCartes;
import cartes.Type;
import jeu.Sabot;

public class TestSabot {

	public static void main(String[] args) {
		JeuDeCartes jeu = new JeuDeCartes();

		System.out.println("--- Test piocher ---");
		Sabot sabot = new Sabot(jeu.donnerCartes());
		int compteur = 0;

		while(!sabot.estVide()) {
			System.out.println("je pioche " + sabot.piocher());
			compteur++;
		}

		System.out.println("Cartes piochees : " + compteur);
		System.out.println("Sabot vide : " + sabot.estVide());

		System.out.println("\n--- Test iterateur ---");
		sabot = new Sabot(jeu.donnerCartes());
		Iterator<Carte> iterateur = sabot.iterator();
		compteur = 0;

		while(iterateur.hasNext()) {
			System.out.println("je pioche " + iterateur.next());
			iterateur.remove();
			compteur++;
		}

		System.out.println("Cartes retirees : " + compteur);
		System.out.println("Sabot vide : " + sabot.estVide());

		System.out.println("\n--- Test modification par piocher ---");
		sabot = new Sabot(jeu.donnerCartes());
		iterateur = sabot.iterator();

		try {
			while(iterateur.hasNext()) {
				iterateur.next();
				iterateur.remove();
				sabot.piocher();
			}
			System.out.println("ECHEC : aucune exception");
		} catch (ConcurrentModificationException e) {
			System.out.println("OK : modification detectee");
		}

		System.out.println("\n--- Test modification par ajout ---");
		sabot = new Sabot(jeu.donnerCartes());
		sabot.piocher();
		iterateur = sabot.iterator();

		try {
			while(iterateur.hasNext()) {
				iterateur.next();
				iterateur.remove();
				sabot.ajouterCarte(new Botte(Type.ACCIDENT));
			}
			System.out.println("ECHEC : aucune exception");
		} catch (ConcurrentModificationException e) {
			System.out.println("OK : modification detectee");
		}

		System.out.println("\n--- Test remove avant next ---");
		sabot = new Sabot(jeu.donnerCartes());
		iterateur = sabot.iterator();

		try {
			iterateur.remove();
			System.out.println("ECHEC : aucune exception");
		} catch (IllegalStateException e) {
			System.out.println("OK : suppression interdite");
		}

		System.out.println("\n--- Test double remove ---");
		iterateur.next();
		iterateur.remove();

		try {
			iterateur.remove();
			System.out.println("ECHEC : aucune exception");
		} catch (IllegalStateException e) {
			System.out.println("OK : deuxieme suppression interdite");
		}

		System.out.println("\n--- Test sabot plein ---");
		sabot = new Sabot(jeu.donnerCartes());

		try {
			sabot.ajouterCarte(new Botte(Type.ACCIDENT));
			System.out.println("ECHEC : aucune exception");
		} catch (IllegalStateException e) {
			System.out.println("OK : ajout interdit");
		}

		System.out.println("\n--- Test sabot vide ---");
		sabot = new Sabot(new Carte[0]);

		try {
			sabot.piocher();
			System.out.println("ECHEC : aucune exception");
		} catch (NoSuchElementException e) {
			System.out.println("OK : pioche impossible");
		}
	}
}