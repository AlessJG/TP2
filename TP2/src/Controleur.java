import java.time.LocalTime;
import java.util.ArrayList;
import java.time.LocalDate;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controleur {
	
	private VuePrincipale vueP;
	private VueEleve vueE;
	private VueActivite vueA;
	private VueVoiture vueV;
	private VuePaiement vuePaiement;
	private VueDepense vueD;
	private VueRapport vueRapport;
	private AutoEcole autoEcole;
	
	public Controleur(VuePrincipale vueP) {
		this.vueP = vueP;
		this.vueA = new VueActivite(vueP.getPrimaryStage(), this);
		this.vueE = new VueEleve(vueP.getPrimaryStage(), this);
		this.vueV = new VueVoiture(vueP.getPrimaryStage(), this);
		
		this.vuePaiement = new VuePaiement(vueP.getPrimaryStage(), this);
		this.vuePaiement.setVueP(vueP);
		this.vueD = new VueDepense(vueP.getPrimaryStage(), this);
		this.vueD.setVueP(vueP);
		this.vueRapport = new VueRapport(vueP.getPrimaryStage(), this);
		this.vueRapport.setVueP(vueP);
		this.autoEcole = new AutoEcole();
	}

	
	//Fonctions pour démarrer des actions et des menus de vues secondaires
	
	//élèves
	public void demarrerInscription() {
		this.vueE.inscriptionEleve();
	}
	
	public void demarrerTrouverEleve() {
		this.vueA.trouverEleve();
	}
	
	public void demarrerAfficherEleves() {
		this.vueE.afficherEleves(this.autoEcole.getEleves());
	}
	
	public void demarrerRechercherEleve() {
		this.vueE.rechercherElevePar();
	}
	
	public void demarrerSupprimerEleve() {
		this.vueE.barDeRechercheSupprimerEleve();
	}
	
	public void demarrerModifierEleve() {
		this.vueE.barDeRechercheModifierEleve();
	}
	
	
	//activités
	public void demarrerAfficherActivites() {
		this.vueA.afficherActivites(this.autoEcole.getActivites());
	}
	
	public void demarrerGererActivite(String numSAAQ) {
		Eleve e = this.autoEcole.rechercheEleveNumSAAQ(numSAAQ).get(0);
		this.vueA.gererActivite(e);
	}
	
	public void demarrerAfficherDetails() {
		this.vueA.demanderSAAQActivite(1);
	}
	
	public void demarrerMettreAJour() {
		this.vueA.demanderSAAQActivite(2);
	}
	
	
	//vehicules
	public void demarrerInscriptionVehicule() {
		this.vueV.inscrireNouveauVehicule();
	}
	
	public void demarrerAfficherVehicules() {
		this.vueV.afficherVoitures(this.autoEcole.getVoitures());
	}
	
	public void demarrerModifierVehicule() {
		this.vueV.barDeRechercheModifierVehicule();
	}
	
	public void demarrerVendreVoiture() {
		this.vueV.barDeRechercheSupprimerVehicule();
	}
	
	public void demarrerAfficherVehicule() {
		ArrayList<Voiture> v = new ArrayList<Voiture>();
		v.add(this.autoEcole.getVoiture());
		this.vueV.afficherVoitures(v);
	}
	
	
	//paiements
	public void demarrerPaiements(){
	    this.vuePaiement.afficherPaiements(
	        this.autoEcole.getFactures()
	    );
	}
	//dépenses

	public void demarrerDepenses() {
    	this.vueD.menuDepenses();
	}

	public void ajouterDepenseVoiture(TextField[] inputs) {

    	try {

        	DepenseVoiture d = new DepenseVoiture(
                	this.autoEcole.getDepensesVoiture().size() + 1,
                	inputs[0].getText(), // plaque
                	LocalDate.parse(inputs[1].getText()), // date
                	DepenseVoiture.Categorie.valueOf(inputs[2].getText()), // categorie
                	inputs[3].getText(), // description
                	Double.parseDouble(inputs[4].getText()) // montant
        	);

        	this.autoEcole.ajouterDepenseVoiture(d);
        	this.vueP.confirmation();

    	} catch(Exception e) {
        	this.vueP.erreur("Impossible d'ajouter la dépense.");
    	}
	}

	public void ajouterAutreDepense(TextField[] inputs) {

    	try {

        	AutreDepense d = new AutreDepense(
                	this.autoEcole.getAutresDepenses().size() + 1,
                	LocalDate.parse(inputs[0].getText()),
                	AutreDepense.Categorie.valueOf(inputs[1].getText()),
                	inputs[2].getText(),
                	Double.parseDouble(inputs[3].getText())
        	);

        	this.autoEcole.ajouterAutreDepense(d);
        	this.vueP.confirmation();

    	} catch(Exception e) {
        	this.vueP.erreur("Impossible d'ajouter la dépense.");
    	}
	}

	public void afficherDepensesVoiture() {
    	this.vueD.afficherDepensesVoiture(this.autoEcole.getDepensesVoiture());
	}

	public void afficherAutresDepenses() {
    	this.vueD.afficherAutresDepenses(this.autoEcole.getAutresDepenses());
	}

	public void afficherDepensesCategorie(String categorie) {
    	this.vueD.afficherDepensesCategorie(
        	this.autoEcole.rechercherDepensesCategorie(categorie)
    	);
	}


	// rapports

	public void demarrerRapports() {
    	this.vueRapport.menuRapports();
	}


	public void genererRapports(LocalDate debut, LocalDate fin, String dossier) {

    	Rapport.rapportEleves(
            	"./CSV/eleves/eleves" + LocalDate.now().getYear() + ".csv",
            	debut,
            	fin,
            	dossier);

    	Rapport.rapportRevenus(
            	"./CSV/paiements/factures" + LocalDate.now().getYear() + ".csv",
            	debut,
            	fin,
            	dossier);

    	Rapport.rapportDepensesVoiture(
            	"./CSV/depenses/depensesVoiture.csv",
            	debut,
            	fin,
            	dossier);

    	Rapport.rapportAutresDepenses(
            	"./CSV/depenses/autresDepenses.csv",
            	debut,
            	fin,
            	dossier);
		this.vueP.confirmation();

    	
	}
	
	//Fonctions pour le menu élèves
	public void confirmerInscription(TextField[] inputs) {
		String[] reponses = new String[inputs.length];
		for(int i = 0; i<inputs.length; i++) {
			reponses[i] = inputs[i].getText();
		}
		
		if(this.autoEcole.inscriptionEleve(reponses) == null) {
			Button bouton = this.vueP.erreur("Erreur de format");
			bouton.setOnAction((event)-> {
				this.vueE.inscriptionEleve();
			});
		}
		else {
			this.vueP.confirmation();
		}
	}	
	
	public void rechercherEleve(String type, String input) {
		switch(type) {
			case "numSAAQ":
				this.vueE.afficherEleves(this.autoEcole.rechercheEleveNumSAAQ(input));
				break;
			case "nom":
				this.vueE.afficherEleves(this.autoEcole.rechercheEleveNom(input));
				break;
			case "prenom":
				this.vueE.afficherEleves(this.autoEcole.rechercheElevePrenom(input));
				break;
		}
	}
	
	public void supprimeurEleves(String input) {
		this.autoEcole.rechercheEleveNumSAAQ(input);
		if(this.autoEcole.getElevesRecherches() != null && 
				!(this.autoEcole.getElevesRecherches().isEmpty())) {
			this.vueE.supprimerEleve(this.autoEcole.getElevesRecherches().get(0));
		}
		else {
			this.vueP.erreur("Erreur");
		}
		
	}
	
	public void confirmerDesinscription() {
		this.autoEcole.retirerEleve(this.autoEcole.getElevesRecherches().get(0));
	}
	
	public void modifierEleves(String input) {
		this.autoEcole.rechercheEleveNumSAAQ(input);
		if(this.autoEcole.getElevesRecherches() != null && 
				!(this.autoEcole.getElevesRecherches().isEmpty())) {
			this.vueE.modifierInfosEleve(this.autoEcole.getElevesRecherches().get(0));
		}
		else {
			this.vueP.erreur("Erreur");
		}
		
	}
	
	public void confirmerModifier(TextField[] inputs, Eleve e) {
		String[] reponses = new String[inputs.length];
		for(int i = 0; i<inputs.length; i++) {
			reponses[i] = inputs[i].getText();
		}
		
		if(this.autoEcole.modifierEleve(reponses, e) == null) {
			this.vueP.erreur("Erreur");
		}
		else {
			this.vueP.confirmation();
		}
	}
	
	//Fonctions pour le menu des activités
	public void gestionActivite(boolean cas, Eleve e) {
		int resultat = this.autoEcole.gestionActivite(cas, e);
		
		switch (resultat) {
		case 0:
			if(cas) {
				this.ouvrirPlanification(e);
			}
			else {
				this.vueA.annulerActivite(e);
			}
			break;
		case 1:
			if(cas) {
				 this.vueP.erreur("Vous ne pouvez pas planifier plus "
						+ "d'une activité à l'avance.");
			}
			else {
				this.vueP.erreur("Vous ne pouvez pas annuler une activité si vous "
						+ "n'avez déjà aucune activité prévue.");
			}
			break;
		case 2:
			if(!cas) {
				this.vueP.erreur("Vous ne pouvez pas planifier une activité si vous "
									   + "n'avez pas fini de payer pour celle précédente.");
			}
			break;
		}
	}
	
	public void confirmerAnnulation(Eleve eleve) {

	    boolean succes = this.autoEcole.annulerActivite(eleve);

	    if (succes) {
	        this.vueP.confirmation();
	    }
	    else {
	        this.vueP.erreur("Erreur");
	    }
	}
	
	public void ouvrirPlanification(Eleve eleve) {
	    this.vueA.planifierActivite(eleve, this.autoEcole.getCalendrier().getSemainesMois());
	}

	public void selectionnerCreneau(Eleve eleve, Date date, LocalTime heure) {
	    this.vueA.demanderVoiture(eleve, date, heure);
	}
	
	public void confirmerPlanification(Eleve eleve, Date date, LocalTime heure, boolean voitureAutoEcole, String plaqueUtilisateur) {

		String plaque = null;
		boolean succes = false;
		
		if (voitureAutoEcole) {
			if(this.autoEcole.getVoiture() != null) {
				plaque = this.autoEcole.getVoiture().getPlaque();
			}
			else {
				succes = false;
			}
		}
		else {
			plaque = plaqueUtilisateur;
		}
		
		succes = this.autoEcole.reserverActivite(eleve, date, heure, voitureAutoEcole, plaque);
		
		if (succes) {
			this.vueP.confirmation();
		}
		else {
			this.vueP.erreur("Erreur");
		}
	}
	
	public void afficherDetailsActivite(String numSAAQ) {
		
	    ArrayList<Activite> activites = this.autoEcole.trouverActivites(numSAAQ);
	    this.vueA.afficherActivites(activites);
	}
	
	public void mettreAJourStatutActivite(String numSAAQ) {
		  
    	ArrayList<Activite> activites = this.autoEcole.trouverActivites(numSAAQ);
    	
    	ArrayList<Activite> activitesNC = new ArrayList<Activite>();
    	for(Activite a : activites) {
    		if(a.getStatut().equals(Activite.Statut.NC)) {
    			activitesNC.add(a);
    		}
    	}
	    boolean succes = this.autoEcole.mettreAJourStatutActivite(activitesNC, Activite.Statut.C);
	    if (succes) {
	        this.vueP.confirmation();;
	    }
	    else {
	        this.vueP.erreur("Activité introuvable");
	    }
	}
	
	//Fonctions pour le menu des véhicules
	public void confirmerInscriptionVehicule(TextField[] inputs) {
		String[] reponses = new String[inputs.length];
		for(int i = 0; i<inputs.length; i++) {
			reponses[i] = inputs[i].getText();
		}
		
		if(this.autoEcole.inscriptionVehicule(reponses) == null) {
			this.vueP.erreur("Erreur");
		}
		else {
			this.vueP.confirmation();
		}
	}
	
	public void modifierVehicule(String plaque) {
		Voiture v = this.autoEcole.trouverVoiture(plaque);
		if(v != null) {
			this.vueV.modifierInfosVoiture(v);
		}
		else {
			this.vueP.erreur("Erreur");
		}
	}
	
	public void confirmerModifierVehicule(TextField[] inputs, Voiture v) {
		String[] reponses = new String[inputs.length];
		for(int i = 0; i<inputs.length; i++) {
			reponses[i] = inputs[i].getText();
		}
		
		if(this.autoEcole.modifierVoiture(v.getPlaque(), reponses[0], reponses[1]) == false) {
			this.vueP.erreur("Erreur");
		}
		else {
			this.vueP.confirmation();
		}
	}
	
	public void vendreVoiture(String plaque) {

	    boolean succes = this.autoEcole.vendreVoiture(plaque);

	    if(succes) {
	    	this.vueP.confirmation();
	    }
	    else {
	    	this.vueP.erreur("Véhicule introuvable");
	    }
	}
	
	//Getters et setters
	public void setVueE(VueEleve vueE) {
	    this.vueE = vueE;
	    this.vueE.setVueP(this.vueP);
	}

	public void setVueA(VueActivite vueA) {
	    this.vueA = vueA;
	    this.vueA.setVueP(this.vueP);
	}

	public void setVueV(VueVoiture vueV) {
	    this.vueV = vueV;
	    this.vueV.setVueP(this.vueP);
	}
	public void setVueD(VueDepense vueD) {
    	this.vueD = vueD;
    	this.vueD.setVueP(this.vueP);
	}

}
