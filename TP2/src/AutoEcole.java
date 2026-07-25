import java.time.LocalDate;
import java.util.ArrayList;

public class AutoEcole {
	private ArrayList<Eleve> eleves; //la liste de tous les eleves inscrit à l'auto-école
	private ArrayList<Activite> activites; //la liste de toutes les activités prévues pour cette années
	private ArrayList<Paiement> factures;
	private Personne utilisateur; //l'utilisateur du programme
	private Calendrier calendrier; //le calendrier lié à cette instance
	private Moniteur moniteur; //le moniteur de l'auto-école
	private Voiture voiture; //la voiture de l'auto-école
	
	private String fActivites; //le nom du fichier CSV des activites de cette année
	private String fCalendrier; //le nom du fichier CSV du calendrier de ce mois
	private String fVoiture; //le nom du fichier CSV des voitures de cette année
	private String fEleves; //le nom du fichier CSV des élèves de cette année
	private String fFactures;
	private String fAutresDepenses;
	private String fDepensesVoiture;
	private String fPaiements;
	
	/**
	 * Constructeur, initisalise les variables globales en récupérant les données 
	 * depuis les fichiers CSV correspondants
	 */
	public AutoEcole() {
		
		//fichiers CSV
		this.fEleves = ("./CSV/eleves/eleves" +
				 LocalDate.now().getYear() +
				 ".csv");
		this.eleves = GestionFichiers.elevesCSV(this.fEleves);
	
		this.fVoiture = ("./CSV/voitures/voiture" +
			 LocalDate.now().getYear() +
			 ".csv");
		
		this.voiture = GestionFichiers.voituresCSV(this.fVoiture);
	
		this.fActivites = ("./CSV/activites/activites" +
			 LocalDate.now().getYear() +
			 ".csv");
		this.activites = GestionFichiers.activitesCSV(this.fActivites, this.voiture);
	
		this.fCalendrier = ("./CSV/calendriers/calendrier" +
			 LocalDate.now().getMonthValue() +
			 ".csv");
	
		this.calendrier = new Calendrier();
	
		this.moniteur = new Moniteur();
		
		this.fFactures = "./CSV/factures/factures"
		        + LocalDate.now().getYear() + ".csv";

		this.factures = GestionFichiers.facturesCSV(
		        this.fFactures,
		        this.eleves,
		        this.activites);
		
		//fichiers textes
		
		this.fAutresDepenses = "./CSV/autres_depenses/autres_depenses"
		        + LocalDate.now().getYear() + ".txt";
		
		this.fDepensesVoiture = "./CSV/autres_depenses/depenses_voiture"
		        + LocalDate.now().getYear() + ".txt";
		
		this.fPaiements = "./CSV/autres_depenses/autres_depenses"
		        + LocalDate.now().getYear() + ".txt";
	}
	
	/**
	 * Fonction qui sert à gérer l'inscription d'un.e nouvel.le élève
	 * @return nouvelEleve, le nouvel élève (objet Eleve)
	 */
    public Eleve inscriptionEleve(String[] reponses) {
    		    
		//Un numéro de téléphone doit contenir 10 chiffres
		if(!(reponses[3].length() == 10)) {
			System.out.println("Format incorrect.");
			return null;			
		}
    			
		//Un numéro SAAQ doit contenir 9 chiffres
		if(!(reponses[4].length() == 9)) {
			System.out.println("Format incorrect.");
			return null;
		}
		
    	//Une fois que les informations sont correctes, on créé une nouvelle instance d'Eleve
    	Eleve nouvelEleve = new Eleve(LocalDate.now(), reponses[0], reponses[1], reponses[2],
    								  reponses[3], reponses[4], reponses[5]);
    	
    	//On l'enregistre dans notre base de données (elevesXXXX.csv)
    	String texte = (reponses[4] + "," + reponses[5] + "," + reponses[0] + "," + reponses[1] + "," +
    		    		reponses[2] + "," + reponses[3] + "," + LocalDate.now() + ", " + "\n");
    	
    	GestionFichiers.ajouterCSV(this.fEleves, texte);
    	
    	//On l'ajoute à la liste courante d'élève, 
    	//on laisse savoir à l'utilisateur que l'opération a été un succès
    	//et la nouvelle instance de l'Eleve est retournée
    	this.eleves.add(nouvelEleve);
    	System.out.println("Vos informations ont été enregistrées.");
    	return nouvelEleve;
    }
}
