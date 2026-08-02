import java.time.LocalTime;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controleur {
	
	private VuePrincipale vueP;
	private VueEleve vueE;
	private VueActivite vueA;
	private VueVoiture vueV;
	private VuePaiement vuePaiement;
	private VueDepense vueD;
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

    	String[] reponses = new String[inputs.length];

    	for (int i = 0; i < inputs.length; i++) {
        	reponses[i] = inputs[i].getText();
    	}

    	boolean succes = this.autoEcole.ajouterAutreDepense(reponses);

    	if (succes) {
        	this.vueP.confirmation();
    	} else {
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
	
	//Fonctions pour le menu élèves
	public void confirmerInscription(TextField[] inputs) {
		String[] reponses = new String[inputs.length];
		for(int i = 0; i<inputs.length; i++) {
			reponses[i] = inputs[i].getText();
		}
		
		if(this.autoEcole.inscriptionEleve(reponses) == null) {
			Button bouton = this.vueP.erreurEntree("Retourner à l'inscription");
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
			this.vueE.echecDesinscription();
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
			this.vueE.echecDesinscription();
		}
		
	}
	
	public void confirmerModifier(TextField[] inputs, Eleve e) {
		String[] reponses = new String[inputs.length];
		for(int i = 0; i<inputs.length; i++) {
			reponses[i] = inputs[i].getText();
		}
		
		if(this.autoEcole.modifierEleve(reponses, e) == null) {
			Button bouton = this.vueP.erreurEntree("Retourner à la modification des informations");
			bouton.setOnAction((event)-> {
				this.vueE.modifierInfosEleve(this.autoEcole.getElevesRecherches().get(0));
			});
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
	        this.vueP.erreurEntree("Retour");
	    }
	}
	
	public void ouvrirPlanification(Eleve eleve) {
	    this.vueA.planifierActivite(eleve, this.autoEcole.getCalendrier().getSemainesMois());
	}

	public void selectionnerCreneau(Eleve eleve, Date date, LocalTime heure) {
	    this.vueA.demanderVoiture(eleve, date, heure);
	}
	
	public void confirmerPlanification(Eleve eleve, Date date, LocalTime heure, boolean voitureAutoEcole, String plaqueUtilisateur) {

		String plaque;
		
		if (voitureAutoEcole) {
			plaque = this.autoEcole.getVoiture().getPlaque();
		}
		else {
			plaque = plaqueUtilisateur;
		}
		
		boolean succes = this.autoEcole.reserverActivite(eleve, date, heure,	voitureAutoEcole, plaque);
		
		if (succes) {
			this.vueP.confirmation();
		}
		else {
			this.vueP.erreurEntree("Retour");
		}
	}
	
	public void afficherDetailsActivite() {

	    String numSAAQ = this.vueA.demanderNumSAAQActivite();

	    String details = this.autoEcole.afficherDetailsActivite(numSAAQ);

	    this.vueA.afficherMessage(details);
	}
	
	public void mettreAJourStatutActivite() {

	    String numSAAQ = this.vueA.demanderIdActivite();

	    Activite.Statut statut = this.vueA.demanderNouveauStatutActivite();

	    boolean succes = this.autoEcole.mettreAJourStatutActivite(numSAAQ, statut);

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
			Button bouton = this.vueP.erreurEntree("Retourner à l'inscription");
			bouton.setOnAction((event)-> {
				this.vueV.inscrireNouveauVehicule();
			});
		}
		else {
			this.vueP.confirmation();
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
