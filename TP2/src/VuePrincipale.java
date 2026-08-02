import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class VuePrincipale extends Application {
	private Controleur controleur;
	private Stage primaryStage;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		this.controleur = new Controleur(this);
		this.menuPrincipal();
	}
	
	/**
     * Fonction qui sert à gérer le menu principal de l'utilisateur
     */
    public void menuPrincipal() {
    	VBox root = new VBox();
    	root.setSpacing(10);
    	Scene scene = new Scene(root, 500, 800);
    	
    	Text texte1 = new Text("Bonjour!");
    	texte1.setFont(Font.font(18));
    	Text texte2 = new Text("Veuillez choisir une option parmi celles suivantes");
    	texte2.setFont(Font.font(18));
    	root.getChildren().add(texte1);
    	root.getChildren().add(texte2);
    	
    	root.getChildren().add(new Separator());
    	
    	Button activite = new Button("Activités");
    	activite.setFont(Font.font(20));
    	activite.setOnAction((event)-> {
    		this.menuActivites();
    	});
    	
    	Button eleve = new Button("Élèves");
    	eleve.setFont(Font.font(20));
    	eleve.setOnAction((event)-> {
    		this.menuEleves();
    	});
    	
    	Button vehicule = new Button("Véhicules");
    	vehicule.setFont(Font.font(20));
    	vehicule.setOnAction((event)-> {
    		this.menuVehicules();
    	});

		Button paiement = new Button("Paiements");

		paiement.setFont(Font.font(20));

		paiement.setOnAction((event)-> {
    		this.controleur.demarrerPaiements();
		});

		Button depense = new Button("Dépenses");

		depense.setFont(Font.font(20));

		depense.setOnAction((event)-> {
    		this.controleur.demarrerDepenses();
		});

		Button rapport = new Button("Rapports");

		rapport.setFont(Font.font(20));

		rapport.setOnAction((event)-> {
    		this.controleur.demarrerRapports();
		});
    	
    	Button quitter = new Button("Quitter");
    	quitter.setFont(Font.font(20));
    	quitter.setOnAction((event)-> {
    		this.quitter();
    	});
    	
    	root.getChildren().addAll(activite, eleve, vehicule, paiement, rapport, depense, quitter);
    	root.setAlignment(Pos.CENTER);
    	root.setSpacing(20);
    	
    	this.primaryStage.setTitle("Menu principal");
    	this.primaryStage.setScene(scene);
    	this.primaryStage.show();
    	
    }
    
    public void menuActivites() {
    	
    	VBox root = new VBox();
    	root.setSpacing(10);
    	Scene scene = new Scene(root, 500, 600);
    	
    	Text texte2 = new Text("Veuillez choisir une option parmi celles suivantes");
    	texte2.setFont(Font.font(18));
    	root.getChildren().add(texte2);
    	
    	root.getChildren().add(new Separator());
    	
    	Button gerer = new Button("Gérer une activité");
    	gerer.setFont(Font.font(20));
    	gerer.setOnAction((event)-> {
    		this.controleur.demarrerTrouverEleve();
    	});
    	
    	Button lister = new Button("Afficher la liste des activités");
    	lister.setFont(Font.font(20));
    	lister.setOnAction((event)-> {
    		this.controleur.demarrerAfficherActivites();
    	});
    	
    	Button miseAJour = new Button("Mettre à jour le statut d'une activité");
    	miseAJour.setFont(Font.font(20));
    	miseAJour.setOnAction((event)-> {
    		this.controleur.demarrerMettreAJour();
    	});
    	    	
    	Button details = new Button("Afficher les détails d'une activité");
    	details.setFont(Font.font(20));
    	details.setOnAction((event)-> {
    		this.controleur.demarrerAfficherDetails();
    	});
    	
    	Button quitter = new Button("Quitter");
    	quitter.setFont(Font.font(20));
    	quitter.setOnAction((event)-> {
    		this.quitter();
    	});
    	
    	root.getChildren().addAll(gerer, lister, miseAJour, details, quitter);
    	root.setAlignment(Pos.CENTER);
    	root.setSpacing(20);
    	
    	this.primaryStage.setTitle("Menu activités");
    	this.primaryStage.setScene(scene);
    	this.primaryStage.show();
    	
    }
    
    public void menuEleves() {
    	VBox root = new VBox();
    	root.setSpacing(10);
    	Scene scene = new Scene(root, 500, 600);
    	
    	Text texte2 = new Text("Veuillez choisir une option parmi celles suivantes");
    	texte2.setFont(Font.font(18));
    	root.getChildren().add(texte2);
    	
    	root.getChildren().add(new Separator());
    	
    	Button lister = new Button("Afficher la liste complète des élèves");
    	lister.setFont(Font.font(20));
    	lister.setOnAction((event)-> {
    		this.controleur.demarrerAfficherEleves();
    	});
    	
    	Button inscrire = new Button("Ajouter un nouvel élève");
    	inscrire.setFont(Font.font(20));
    	inscrire.setOnAction((event)-> {
    		this.controleur.demarrerInscription();
    	});
    	
    	Button rechercher = new Button("Rechercher un élève");
    	rechercher.setFont(Font.font(20));
    	rechercher.setOnAction((event)-> {
    		this.controleur.demarrerRechercherEleve();
    	});
    	
    	Button modifier = new Button("Modifier les informations d'un élève");
    	modifier.setFont(Font.font(20));
    	modifier.setOnAction((event)-> {
    		this.controleur.demarrerModifierEleve();
    	});
    	
    	Button supprimer = new Button("Supprimer un élève");
    	supprimer.setFont(Font.font(20));
    	supprimer.setOnAction((event)-> {
    		this.controleur.demarrerSupprimerEleve();
    	});
    	
    	Button quitter = new Button("Quitter");
    	quitter.setFont(Font.font(20));
    	quitter.setOnAction((event)-> {
    		this.quitter();
    	});
    	
    	root.getChildren().addAll(lister, inscrire, rechercher, modifier, supprimer, quitter);
    	root.setAlignment(Pos.CENTER);
    	root.setSpacing(20);
    	
    	this.primaryStage.setTitle("Menu élèves");
    	this.primaryStage.setScene(scene);
    	this.primaryStage.show();
    	
    }
    
    public void menuVehicules() {
    	
    	VBox root = new VBox();
    	root.setSpacing(10);
    	Scene scene = new Scene(root, 500, 560);
    	
    	Text texte2 = new Text("Veuillez choisir une option parmi celles suivantes");
    	texte2.setFont(Font.font(18));
    	root.getChildren().add(texte2);
    	
    	root.getChildren().add(new Separator());
    	
    	Button lister = new Button("Afficher la liste des véhicules");
    	lister.setFont(Font.font(20));
    	lister.setOnAction((event)-> {
    		this.controleur.demarrerAfficherVehicules();
    	});
    	
    	Button ajouter = new Button("Ajouter un véhicule");
    	ajouter.setFont(Font.font(20));
    	ajouter.setOnAction((event)-> {
    		this.controleur.demarrerInscriptionVehicule();
    	});
    	
    	Button modifier = new Button("Modifier un véhicule");
    	modifier.setFont(Font.font(20));
    	modifier.setOnAction((event)-> {
    		this.controleur.demarrerModifierVehicule();
    	});
    	
    	Button supprimer = new Button("Supprimer un véhicule");
    	supprimer.setFont(Font.font(20));
    	supprimer.setOnAction((event)-> {
    		this.controleur.demarrerVendreVoiture();
    	});
    	
    	Button suivre = new Button("Suivre le kilométrage et l'état du véhicule");
    	suivre.setFont(Font.font(20));
    	suivre.setOnAction((event)-> {
    		this.controleur.demarrerAfficherVehicule();
    	});
    	
    	Button quitter = new Button("Quitter");
    	quitter.setFont(Font.font(20));
    	quitter.setOnAction((event)-> {
    		this.quitter();
    	});
    	
    	root.getChildren().addAll(lister, ajouter, modifier, supprimer, suivre, quitter);
    	root.setAlignment(Pos.CENTER);
    	root.setSpacing(20);
    	
    	this.primaryStage.setTitle("Menu véhicules");
    	this.primaryStage.setScene(scene);
    	this.primaryStage.show();
    	
    }
    
    public void quitter() {
    	VBox root = new VBox();
    	root.setSpacing(10);
    	Scene scene = new Scene(root, 400, 400);
    	
    	Text texte1 = new Text("Au revoir!");
    	texte1.setFont(Font.font(18));
    	root.getChildren().add(texte1);
    	
    	root.setAlignment(Pos.CENTER);
    	root.setSpacing(20);
    	
    	this.primaryStage.setTitle("Quitter");
    	this.primaryStage.setScene(scene);
    	this.primaryStage.show();
    	System.exit(0);
    	
    }
    
    public Button erreur(String texte) {
		VBox root = new VBox();
    	root.setSpacing(10);
    	Scene scene = new Scene(root, 400, 400);
    	
    	Text texte1 = new Text(texte);
    	texte1.setFont(Font.font(18));
    	texte1.setWrappingWidth(300);
    	root.getChildren().add(texte1);
    	
    	HBox hbox = new HBox();
    	root.getChildren().add(hbox);
    	
    	Button quitter = new Button("Quitter");
    	quitter.setOnAction((event)-> {
    		this.quitter();
    	});
    	
    	Button nextMenu = new Button("Menu principal");
    	nextMenu.setOnAction((event)-> {
    		this.menuPrincipal();
    	});
    	
    	hbox.getChildren().add(quitter);
    	hbox.getChildren().add(nextMenu);
    	
    	root.setAlignment(Pos.CENTER);
    	root.setSpacing(20);
    	
    	this.primaryStage.setTitle("Erreur");
    	this.primaryStage.setScene(scene);
    	this.primaryStage.show();
    	
    	return nextMenu;
	}
    
    public void confirmation() {
		VBox root = new VBox();
    	root.setSpacing(10);
    	Scene scene = new Scene(root, 400, 400);
    	
    	Text texte1 = new Text("Confirmé!");
    	texte1.setFont(Font.font(18));
    	root.getChildren().add(texte1);
    	
    	HBox hbox = new HBox();
    	root.getChildren().add(hbox);
    	
    	Button quitter = new Button("Quitter");
    	quitter.setOnAction((event)-> {
    		this.quitter();
    	});
    	
    	Button nextMenu = new Button("Menu principal");
    	nextMenu.setOnAction((event)-> {
    		this.menuPrincipal();
    	});
    	
    	hbox.getChildren().add(quitter);
    	hbox.getChildren().add(nextMenu);
    	
    	root.setAlignment(Pos.CENTER);
    	root.setSpacing(20);
    	
    	this.primaryStage.setTitle("Succès");
    	this.primaryStage.setScene(scene);
    	this.primaryStage.show();
	}
    
    public Stage getPrimaryStage() {
    	return this.primaryStage;
    }
}
