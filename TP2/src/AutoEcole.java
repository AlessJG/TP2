import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class AutoEcole {
	private ArrayList<Eleve> eleves; //la liste de tous les eleves inscrit à l'auto-école
	private ArrayList<Eleve> elevesRecherches;
	private ArrayList<Activite> activites; //la liste de toutes les activités prévues pour cette années
	private ArrayList<Paiement> factures;
	private ArrayList<Voiture> voitures;
	private ArrayList<DepenseVoiture> depensesVoiture;
	private ArrayList<AutreDepense> autresDepenses;
	
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
		
		this.voitures = GestionFichiers.voituresCSV(this.fVoiture);
		this.voiture = this.voitures.getLast();
	
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
		
		this.fDepensesVoiture = "./CSV/depenses_voiture/depenses_voiture"
        		+ LocalDate.now().getYear() + ".txt";
		
		this.fPaiements = "./CSV/autres_depenses/autres_depenses"
		        + LocalDate.now().getYear() + ".txt";
		
		this.voitures = GestionFichiers.voituresCSV(this.fVoiture);

		this.depensesVoiture = GestionFichiers.depensesVoitureCSV(fDepensesVoiture);

		this.autresDepenses = GestionFichiers.autresDepensesCSV(fAutresDepenses);
		
	}
	
	/**
	 * Fonction qui sert à gérer l'inscription d'un.e nouvel.le élève
	 * @return nouvelEleve, le nouvel élève (objet Eleve)
	 */
    public Eleve inscriptionEleve(String[] reponses) {
    		    
		//Un numéro de téléphone doit contenir 10 chiffres
		if(!(reponses[3].length() == 10)) {
			return null;			
		}
    			
		//Un numéro SAAQ doit contenir 9 chiffres
		if(!(reponses[4].length() == 9)) {
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
    	return nouvelEleve;
    }
    
    public Voiture inscriptionVehicule(String[] reponses) {
    	Voiture v;
    	try {
    		v = new Voiture(reponses[0], Integer.parseInt(reponses[2]), reponses[1], 
        			Double.parseDouble(reponses[3]), Integer.parseInt(reponses[4]), 
        			Integer.parseInt(reponses[6]), Voiture.Etat.valueOf(reponses[5]));
    	}
    	catch(Exception e) {
    		return null;
    	}
    	
    	
    	String texte = (reponses[4] + "," + reponses[5] + "," + reponses[0] + "," + reponses[1] + "," +
    		    		reponses[2] + "," + reponses[3] + "," + LocalDate.now() + ", " + "\n");
    	
    	GestionFichiers.ajouterCSV(fVoiture, texte);
    	
    	this.voiture = v;
    	return v;
    }
    
    /**
	 * Fonction qui sert à modifier les informations d'un.e élève
	 * @return nouvelEleve, le.a nouvel.le élève (objet Eleve)
	 */
    public Eleve modifierEleve(String[] reponses, Eleve e) {
    		    
		//Un numéro de téléphone doit contenir 10 chiffres
		if(!(reponses[3].length() == 10)) {
			return null;			
		}
    			
		//Un numéro SAAQ doit contenir 9 chiffres
		if(!(reponses[4].length() == 9)) {
			return null;
		}
		
		e.setNom(reponses[0]);
	    e.setPrenom(reponses[1]);
	    e.setAdresse(reponses[2]);
	    e.setNumTelephone(reponses[3]);
	    e.setNumeroSAAQ(reponses[4]);
	    e.setMotDePasse(reponses[5]);

	    GestionFichiers.modifierEleveCSV(e, this.fEleves);
	    return e;
    	
    }
    
    public ArrayList<Eleve> rechercheEleveNumSAAQ(String numSAAQ) {
    	this.elevesRecherches = new ArrayList<Eleve>();
        for (Eleve e : this.eleves) {
            if (e.getNumSAAQ().equalsIgnoreCase(numSAAQ)) {
                this.elevesRecherches.add(e);
            }
        }
        return this.elevesRecherches;
    }
    public ArrayList<Eleve> rechercheElevePrenom(String prenom) {
    	this.elevesRecherches = new ArrayList<Eleve>();
        for (Eleve e : this.eleves) {
            if (e.getPrenom().equalsIgnoreCase(prenom)) {
            	this.elevesRecherches.add(e);
            }
        }
        
        return this.elevesRecherches;
    }
    public ArrayList<Eleve> rechercheEleveNom(String nom) {
    	this.elevesRecherches = new ArrayList<Eleve>();
        for (Eleve e : this.eleves) {
            if (e.getNom().equalsIgnoreCase(nom)) {
                this.elevesRecherches.add(e);
            }
        }
        return this.elevesRecherches;
    }
    
    public ArrayList<Eleve> getElevesRecherches(){
    	return this.elevesRecherches;
    }
    
    public Activite trouverActivites(String numSAAQ) {

    	ArrayList<Activite> activites = new ArrayList<Activite>();
        for (Activite activite : this.activites) {

            if (activite.getNumSAAQ().equals(numSAAQ)) {
                activites.add(activite);
            }
        }
        
    	for(Activite a : activites) {
    		if(a.getStatut().equals(Activite.Statut.NC)) {
    			return a;
    		}
    	}
        return null;
    }
    
    public boolean mettreAJourStatutActivite(String numSAAQ, Activite.Statut nouveauStatut) {

    	Activite a = trouverActivites(numSAAQ);

    	if (a == null) {
    		return false;
    	}

    	a.setStatut(nouveauStatut);

    	// Sauvegarder toutes les activités dans le CSV
    	GestionFichiers.modifierActiviteCSV(a, this.fActivites);

    	return true;
    }
    
    public void retirerEleve(Eleve e) {

        e.setDateFin(LocalDate.now());

        this.eleves.removeIf(el ->
            el.getNumSAAQ().equals(e.getNumSAAQ()));

        if (this.elevesRecherches != null) {
            this.elevesRecherches.removeIf(el ->
                el.getNumSAAQ().equals(e.getNumSAAQ()));
        }

        GestionFichiers.modifierEleveCSV(e, this.fEleves);
    }
    
    public Voiture rechercherVoiture(String plaque) {
        for (Voiture v : this.voitures) {
            if (v.getPlaque().equalsIgnoreCase(plaque)) {
                return v;
            }
        }
        return null;
    }
    
    /**
     * Fonction qui sert à gérer le menu de planification d'une activité (annulation et ajout)
     * Note: il est impossible de planifier plus d'une activité à l'avance et de planifier
     * une activité si celle précédente n'a pas encore été complètement payée 
     */
    public int gestionActivite(boolean cas, Eleve e) {
    	
    	if(cas) {
    		if(e.getActivitePrevue()) {
    			return 1; //"Vous ne pouvez pas planifier plus d'une activité à l'avance."
        		
        	}
    		else if(e.impaye()) {
        		return 2; // ("Vous ne pouvez pas planifier une activité si vous n'avez pas fini de payer pour celle précédente.")
    		}
    		else {
    			return 0;
    			//this.planifierActivite();
    		}
    	}
    	else {
    		if(e.getActivitePrevue()) {
    			return 0;//this.annulerActivite();
    		}
    		else {
    			return 1; //("Vous ne pouvez pas annuler une activité si vous n'avez déjà aucune activité prévue.");
    		}
    	}
    }
    
    public boolean annulerActivite(Eleve eleve) {

        Activite activite = eleve.getActivite();

        if (activite == null) {
            return false;
        }

        // Libérer le créneau
        for (Date d : this.calendrier.getDates()) {
            if (d.getJour() == activite.getDate()) {
                GestionFichiers.modifierCalendrierCSV(d, this.fCalendrier);
                break;
            }
        }

        GestionFichiers.retirerActiviteCSV(eleve.getNumSAAQ(), this.fActivites);

        this.activites.remove(activite);

        eleve.setActivite(null);
        eleve.setActivitePrevue(false);
        eleve.setLecon(eleve.getLecon().previous());

        this.calendrier = new Calendrier();

        return true;
    }
    
    /**
     * Fonction qui sert à planifier une activité.
     */
    public boolean reserverActivite(Eleve eleve, Date dateChoisie, LocalTime heure, boolean voitureAutoEcole, String plaque) {

		long duree = eleve.getTempsLecon();
		
		if (!dateChoisie.ajouterIndispo(heure, Duration.ofMinutes(duree))) {
			return false;
		}
		
		GestionFichiers.modifierCalendrierCSV(dateChoisie, this.fCalendrier);
		
		this.calendrier = new Calendrier();
		
		LocalDate date = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), dateChoisie.getJour());
		
		Activite activite = new Activite(date, heure, Duration.ofMinutes(duree), eleve.getNumSAAQ(),
										 this.moniteur,	voitureAutoEcole, eleve.getTypeActivite(!voitureAutoEcole),
										 Activite.Statut.NC);
		
		eleve.setActivite(activite);
		eleve.setActivitePrevue(true);
		eleve.setLecon(eleve.getLecon().next());
		
		GestionFichiers.modifierEleveCSV(eleve, this.fEleves);
		
		this.activites.add(activite);

		String numero = "F-" + LocalDate.now().getYear() + "-" 
              + String.format("%05d", this.factures.size() + 1);

		Paiement paiement = new Paiement(numero, activite.getMontant(), LocalDate.now(),
        								 eleve,  activite, Paiement.Statut.P, Paiement.Methode.E);

		this.factures.add(paiement);
		
		GestionFichiers.ajouterFactureCSV(paiement,	this.fFactures);
		
		//On enregistre l'activité dans la base de données dans activitesXXXX.csv
        String ligne = this.activites.size() + "," +
		                activite.getType() + "," +
		                activite.getNumSAAQ() + "," +
		                date + "," +
		                activite.getHeure() + "," +
		                activite.getDuree() + "," +
		                activite.getMontant() + "," +
		                activite.getStatut() + "," +
		                plaque + "\n";

        GestionFichiers.ajouterCSV(this.fActivites, ligne);
        
		
		return true;
	}
    
    public boolean mettreAJourStatutActivite(int id, boolean completee) {
        if (id < 0 || id >= this.activites.size()) {
            return false;
        }

        Activite a = this.activites.get(id);
        
        if(completee) {
        	a.setStatut(Activite.Statut.C);
        }
        else {
        	a.setStatut(Activite.Statut.NC);
        }

        GestionFichiers.modifierActiviteCSV(a, fActivites);
        return true;
    }

	public void ajouterDepenseVoiture(DepenseVoiture d){

    	depensesVoiture.add(d);

    	GestionFichiers.ajouterDepenseVoitureCSV(
            	d,
            	fDepensesVoiture
    	);
	}
	public void ajouterAutreDepense(AutreDepense d){

    	autresDepenses.add(d);

    	GestionFichiers.ajouterAutreDepenseCSV(
            	d,
            	fAutresDepenses
    	);
	}

	public ArrayList<AutreDepense> rechercherDepensesCategorie(String categorie) {

    	ArrayList<AutreDepense> resultat = new ArrayList<>();

    	for (AutreDepense d : autresDepenses) {
        	if (d.getCategorie().name().equalsIgnoreCase(categorie)) {
            	resultat.add(d);
        	}
    	}

    	return resultat;
	}
    
    public Calendrier getCalendrier() {
        return this.calendrier;
    }
    
    public Voiture getVoiture() {
        return this.voiture;
    }
    
    public ArrayList<Voiture> getVoitures() {
        return this.voitures;
    }
    
    public ArrayList<Eleve> getEleves(){
    	return this.eleves;
    }
    
    public ArrayList<Activite> getActivites(){
    	return this.activites;
    }

	public ArrayList<Paiement> getFactures() {
    	return this.factures;
	}


	public void ajouterPaiement(Paiement paiement) {
    	this.factures.add(paiement);
	}

	public ArrayList<DepenseVoiture> getDepensesVoiture(){
    	return depensesVoiture;
	}

	public ArrayList<AutreDepense> getAutresDepenses(){
    	return autresDepenses;
	}
    
}

