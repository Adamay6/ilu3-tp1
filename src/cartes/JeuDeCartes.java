package cartes;

public class JeuDeCartes {
	
	private Configuration[] typesDeCartes = new Configuration[19];
	
    public String affichageJeuDeCartes() {
        StringBuilder sb = new StringBuilder();
        sb.append("JEU :\n\n");
        
        int lignesMax = 7; 
        
        for (int i = 0; i < lignesMax; i++) {

            if (i < typesDeCartes.length && typesDeCartes[i] != null) {
                sb.append(String.format("%-25s", typesDeCartes[i].getNbExemplaires() + " " + typesDeCartes[i].getCarte()));
            }

            if (i + 7 < typesDeCartes.length && typesDeCartes[i + 7] != null) {
                sb.append(String.format("%-25s", typesDeCartes[i + 7].getNbExemplaires() + " " + typesDeCartes[i + 7].getCarte()));
            }

            if (i + 14 < typesDeCartes.length && typesDeCartes[i + 14] != null) {
                sb.append(typesDeCartes[i + 14].getNbExemplaires() + " " + typesDeCartes[i + 14].getCarte());
            }
            sb.append("\n");
        }
        
        return sb.toString();
    }

	
	private static class Configuration {
		private int nbExemplaires;
		private Carte carte;
		
		private Configuration(Carte carte, int nbExemplaires) {
			super();
			this.nbExemplaires = nbExemplaires;
			this.carte = carte;
		}

		public int getNbExemplaires() {
			return nbExemplaires;
		}

		public Carte getCarte() {
			return carte;
		}
			
	}
	
	public Carte[] donnerCartes() {
		int totalCartes = 0;
		
		for (Configuration conf : typesDeCartes) {
			if(conf != null)
				totalCartes+= conf.getNbExemplaires();
		}
		
		Carte[] toutesLesCartes = new Carte[totalCartes];
		int indice=0;
		
		for(Configuration conf : typesDeCartes) {
			if(conf != null) {
				for(int i=0;i<conf.getNbExemplaires();i++) {
					toutesLesCartes[indice]=conf.getCarte();
					indice++;
				}
			}
		}
		return toutesLesCartes;
	}
	
}
