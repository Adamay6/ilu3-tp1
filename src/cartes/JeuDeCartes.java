package cartes;

public class JeuDeCartes {
	
	private Configuration[] typesDeCartes = new Configuration[19];
	
    public String affichageJeuDeCartes() {
        StringBuilder sb = new StringBuilder();
        sb.append("JEU :\n");
        for (Configuration conf : typesDeCartes) {
            if (conf != null) {
                sb.append(conf.getNbExemplaires()).append(" ").append(conf.getCarte()).append("\n");
            }
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
