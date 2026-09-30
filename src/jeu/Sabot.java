package jeu;

import cartes.Carte;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

public class Sabot implements Iterable<Carte> {
    private Carte[] cartes;
    private int nbCartes;
    private int nbModifications = 0;

    public Sabot(Carte[] cartes) {
        this.cartes = cartes;
        this.nbCartes = cartes.length;
    }

    public boolean estVide() {
        return nbCartes == 0;
    }

    public void ajouterCarte(Carte carte) {
        if (nbCartes == cartes.length) {
            throw new IllegalStateException("Le sabot est plein");
        }

        cartes[nbCartes] = carte;
        nbCartes++;
        nbModifications++;
    }
    
    public Carte piocher() {
        Iterator<Carte> iterateur = iterator();
        Carte carte = iterateur.next();
        iterateur.remove();
        return carte;
    }
    
    @Override
    public Iterator<Carte> iterator() {
        return new IterateurSabot();
    }

    private class IterateurSabot implements Iterator<Carte> {
        private int indice = 0;
        private int derniereCarte = -1;
        private int modificationsAttendues = nbModifications;

        private void verifierModification() {
            if (modificationsAttendues != nbModifications) {
                throw new ConcurrentModificationException();
            }
        }

        @Override
        public boolean hasNext() {
            verifierModification();
            return indice < nbCartes;
        }

        @Override
        public Carte next() {
            verifierModification();

            if (indice >= nbCartes) {
                throw new NoSuchElementException();
            }

            derniereCarte = indice;
            Carte carte = cartes[indice];
            indice++;
            return carte;
        }

        @Override
        public void remove() {
            verifierModification();

            if (derniereCarte == -1) {
                throw new IllegalStateException();
            }

            for (int i = derniereCarte; i < nbCartes - 1; i++) {
                cartes[i] = cartes[i + 1];
            }

            nbCartes--;
            cartes[nbCartes] = null;

            indice = derniereCarte;
            derniereCarte = -1;

            nbModifications++;
            modificationsAttendues = nbModifications;
        }
    }
}