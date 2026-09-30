package utils;

import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import java.util.ArrayList;
import java.util.Collections;

import java.util.Objects;

public class GestionCartes {

	private static final Random random = new Random();

	public static <T> T extraire(List<T> liste) {
		int indice = random.nextInt(liste.size());
		return liste.remove(indice);
	}

	public static <T> T extraireV2(List<T> liste) {
		int indice = random.nextInt(liste.size());
		ListIterator<T> iterateur = liste.listIterator();

		for(int i=0;i<indice;i++) {
			iterateur.next();
		}

		T element = iterateur.next();
		iterateur.remove();
		return element;
	}
	
	public static <T> List<T> melanger(List<T> liste) {
		List<T> resultat = new ArrayList<>();

		while(!liste.isEmpty()) {
			resultat.add(extraireV2(liste));
		}

		return resultat;
	}

	public static <T> boolean verifierMelange(List<T> liste1, List<T> liste2) {
		if(liste1.size() != liste2.size()) {
			return false;
		}

		for(T element : liste1) {
			if(Collections.frequency(liste1, element)
					!= Collections.frequency(liste2, element)) {
				return false;
			}
		}

		return true;
	}
	
	public static <T> List<T> rassembler(List<T> liste) {
		List<T> restantes = new ArrayList<>(liste);
		List<T> resultat = new ArrayList<>();

		while(!restantes.isEmpty()) {
			T element = restantes.remove(0);
			resultat.add(element);

			ListIterator<T> iterateur = restantes.listIterator();

			while(iterateur.hasNext()) {
				T suivant = iterateur.next();

				if(Objects.equals(element, suivant)) {
					resultat.add(suivant);
					iterateur.remove();
				}
			}
		}

		return resultat;
	}

	public static <T> List<T> rassemberV2(List<T> liste) {
		return rassembler(liste);
	}

	public static <T> boolean verifierRassemblement(List<T> liste) {
		ListIterator<T> iterateur = liste.listIterator();

		if(!iterateur.hasNext()) {
			return true;
		}

		T precedent = iterateur.next();

		while(iterateur.hasNext()) {
			T courant = iterateur.next();

			if(!Objects.equals(precedent, courant)) {
				ListIterator<T> recherche =
						liste.listIterator(iterateur.nextIndex());

				while(recherche.hasNext()) {
					if(Objects.equals(precedent, recherche.next())) {
						return false;
					}
				}
			}

			precedent = courant;
		}

		return true;
	}
}