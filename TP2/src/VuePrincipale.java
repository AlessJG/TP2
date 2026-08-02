import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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
    	Scene scene = new Scene(root, 400, 400);
    	
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
    	
    	Button quitter = new Button("Quitter");
    	quitter.setFont(Font.font(20));
    	quitter.setOnAction((event)-> {
    		this.quitter();
    	});
    	
    	root.getChildren().addAll(activite, eleve, vehicule, quitter);
    	root.setAlignment(Pos.CENTER);
    	root.setSpacing(20);
    	
    	this.primaryStage.setTitle("Menu principal");
    	this.primaryStage.setScene(scene);
    	this.primaryStage.show();
    	
    }
    
    public void menuActivites() {
    	
    	VBox root = new VBox();
    	root.setSpacing(10);
    	Scene scene = new Scene(root, 400, 500);
    	
    	Text texte2 = new Text("Veuillez choisir une option parmi celles suivantes");
    	texte2.setFont(Font.font(18));
    	root.getChildren().add(texte2);
    	
    	root.getChildren().add(new Separator());
    	
    	Button planifier = new Button("Planifier une nouvelle activité");
    	planifier.setFont(Font.font(20));
    	planifier.setOnAction((event)-> {
    		this.controleur.demarrerTrouverEleve();
    	});
    	
    	Button lister = new Button("Afficher la liste des activités");
    	lister.setFont(Font.font(20));
    	lister.setOnAction((event)-> {
    		//
    	});
    	
    	Button miseAJour = new Button("Mettre à jour le statut d'une activité");
    	miseAJour.setFont(Font.font(20));
    	miseAJour.setOnAction((event)-> {
    		//
    	});
    	
    	Button annuler = new Button("Annuler une activité");
    	annuler.setFont(Font.font(20));
    	annuler.setOnAction((event)-> {
    		//
    	});
    	
    	Button details = new Button("Afficher les détails d'une activité");
    	details.setFont(Font.font(20));
    	details.setOnAction((event)-> {
    		//
    	});
    	
    	Button quitter = new Button("Quitter");
    	quitter.setFont(Font.font(20));
    	quitter.setOnAction((event)-> {
    		this.quitter();
    	});
    	
    	root.getChildren().addAll(planifier, lister, miseAJour, annuler, details, quitter);
    	root.setAlignment(Pos.CENTER);
    	root.setSpacing(20);
    	
    	this.primaryStage.setTitle("Menu activités");
    	this.primaryStage.setScene(scene);
    	this.primaryStage.show();
    	
    }
    
    public void menuEleves() {
    	VBox root = new VBox();
    	root.setSpacing(10);
    	Scene scene = new Scene(root, 400, 500);
    	
    	Text texte2 = new Text("Veuillez choisir une option parmi celles suivantes");
    	texte2.setFont(Font.font(18));
    	root.getChildren().add(texte2);
    	
    	root.getChildren().add(new Separator());
    	
    	Button lister = new Button("Afficher la liste complète des élèves");
    	lister.setFont(Font.font(20));
    	lister.setOnAction((event)-> {
    		//
    	});
    	
    	Button inscrire = new Button("Ajouter un nouvel élève");
    	inscrire.setFont(Font.font(20));
    	inscrire.setOnAction((event)-> {
    		//
    	});
    	
    	Button rechercher = new Button("Rechercher un élève");
    	rechercher.setFont(Font.font(20));
    	rechercher.setOnAction((event)-> {
    		//
    	});
    	
    	Button modifier = new Button("Modifier les informations d'un élève");
    	modifier.setFont(Font.font(20));
    	modifier.setOnAction((event)-> {
    		//
    	});
    	
    	Button supprimer = new Button("Supprimer un élève");
    	supprimer.setFont(Font.font(20));
    	supprimer.setOnAction((event)-> {
    		//
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
    	Scene scene = new Scene(root, 400, 500);
    	
    	Text texte2 = new Text("Veuillez choisir une option parmi celles suivantes");
    	texte2.setFont(Font.font(18));
    	root.getChildren().add(texte2);
    	
    	root.getChildren().add(new Separator());
    	
    	Button lister = new Button("Afficher la liste des véhicules");
    	lister.setFont(Font.font(20));
    	lister.setOnAction((event)-> {
    		//
    	});
    	
    	Button ajouter = new Button("Ajouter un véhicule");
    	ajouter.setFont(Font.font(20));
    	ajouter.setOnAction((event)-> {
    		//
    	});
    	
    	Button modifier = new Button("Modifier un véhicule");
    	modifier.setFont(Font.font(20));
    	modifier.setOnAction((event)-> {
    		//
    	});
    	
    	Button supprimer = new Button("Supprimer un véhicule");
    	supprimer.setFont(Font.font(20));
    	supprimer.setOnAction((event)-> {
    		//
    	});
    	
    	Button suivre = new Button("Suivre le kilométrage et l'état du véhicule");
    	suivre.setFont(Font.font(20));
    	suivre.setOnAction((event)-> {
    		//
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
    
    public Button erreurEntree(String buttonText) {
		VBox root = new VBox();
    	root.setSpacing(10);
    	Scene scene = new Scene(root, 400, 400);
    	
    	Text texte1 = new Text("Format incorrect");
    	texte1.setFont(Font.font(18));
    	root.getChildren().add(texte1);
    	
    	HBox hbox = new HBox();
    	root.getChildren().add(hbox);
    	
    	Button quitter = new Button("Quitter");
    	quitter.setOnAction((event)-> {
    		this.quitter();
    	});
    	
    	Button nextMenu = new Button(buttonText);
    	
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
