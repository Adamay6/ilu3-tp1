package testsFonctionnels;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import cartes.Carte;
import cartes.JeuDeCartes;
import utils.GestionCartes;

import java.util.Arrays;
import java.util.Collections;

public class TestGestionCartes {
	public static <T> void testerListe(List<T> liste) {
		List<T> copie = new ArrayList<>(liste);
		List<T> melange = GestionCartes.melanger(copie);
		boolean occurrencesCorrectes = liste.size() == melange.size();

		for(T element : liste) {
			if(Collections.frequency(liste, element)
					!= Collections.frequency(melange, element)) {
				occurrencesCorrectes = false;
			}
		}

		System.out.println("\nListe initiale : " + liste);
		System.out.println("Occurrences conservees ? " + occurrencesCorrectes);
		System.out.println("Liste source du melange vide ? " + copie.isEmpty());
		System.out.println("Deja rassemblee ? "
				+ GestionCartes.verifierRassemblement(liste));

		List<T> rassemblement = GestionCartes.rassemberV2(melange);

		System.out.println("Liste rassemblee : " + rassemblement);
		System.out.println("Rassemblement correct ? "
				+ GestionCartes.verifierRassemblement(rassemblement));
		System.out.println("Contenu conserve apres rassemblement ? "
				+ GestionCartes.verifierMelange(liste, rassemblement));
	}
	
	public static void main(String args[]) {
		JeuDeCartes jeu = new JeuDeCartes();
		List<Carte> listeCarteNonMelangee = new LinkedList<>();
		for (Carte carte : jeu.donnerCartes()) {
			listeCarteNonMelangee.add(carte);
		}
		List<Carte> listeCartes = new ArrayList<>(listeCarteNonMelangee);
		System.out.println(listeCartes);
		listeCartes = GestionCartes.melanger(listeCartes);
		System.out.println(listeCartes);
		System.out.println(
				"liste mélangée sans erreur ? " + GestionCartes.verifierMelange(listeCarteNonMelangee, listeCartes));
		listeCartes = GestionCartes.rassemberV2(listeCartes);
		System.out.println(listeCartes);
		System.out.println("liste rassemblée sans erreur ? " + GestionCartes.verifierRassemblement(listeCartes));
		
		testerListe(new ArrayList<Integer>());
		testerListe(Arrays.asList(1, 1, 2, 1, 3));
		testerListe(Arrays.asList(1, 4, 3, 2));
		testerListe(Arrays.asList(1, 1, 2, 3, 1));
		testerListe(listeCarteNonMelangee);
	}

}