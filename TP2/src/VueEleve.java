import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class VueEleve {
	 // Contrôleur de l'application
    private Controleur controleur;
    private Stage primaryStage;
    private VuePrincipale vueP;

    public VueEleve (Stage stage, Controleur controleur) {
		this.primaryStage = stage;
		this.controleur = controleur;
	}
	
    public void inscriptionEleve() {
    	VBox root = new VBox();
    	root.setSpacing(10);
    	Scene scene = new Scene(root, 800, 550);
    	
    	String[] questions = {"Nom:", "Prénom:",
				  "Adresse (au format - sans les crochets [] :\n[numéro de rue] [nom de la rue] [ville] [province] [code postal]):", 
				  "Numéro de téléphone (au format : 5141234567):",
				  "Numéro SAAQ (au format : 123456789):",
				  "Mot de passe que vous désiré:"};
    	TextField[] inputs = new TextField[6];
    	
    	Text texte1 = new Text("Veuillez rentrer vos informations");
    	texte1.setFont(Font.font(18));
    	root.getChildren().add(texte1);
    	
    	for (int i = 0; i<6; i++) {
    		Text texte = new Text(questions[i]);
    		texte.setFont(Font.font(18));
    		inputs[i] = new TextField();
    		HBox hbox = new HBox();
    		hbox.getChildren().addAll(texte, inputs[i]);
    		hbox.setSpacing(10);
    		root.getChildren().add(hbox);
    		root.getChildren().add(new Separator());
    	}
    	
    	Button confirmer = new Button("Confirmer");
    	confirmer.setFont(Font.font(18));
    	confirmer.setOnAction((event)->{
    		this.controleur.confirmerInscription(inputs);
    	});
    	
    	root.getChildren().add(confirmer);
    	
    	root.setSpacing(20);
    	this.primaryStage.setTitle("Inscription nouvel.le élève");
    	this.primaryStage.setScene(scene);
    	this.primaryStage.show();
    }
    
	public void afficherEleves(ArrayList<Eleve> eleves) {
		
		// adding some sample data
	      ObservableList<Eleve> observableEleves = FXCollections.observableArrayList(eleves);
	      
	      // Creating a TableView
	      TableView<Eleve> tableView = new TableView<Eleve>();
	      
	      // Creating columns for the TableView
	      TableColumn<Eleve, String> colPrenom = new TableColumn<>("Prénom");
	      colPrenom.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPrenom()));
	      
	      TableColumn<Eleve, String> colNom = new TableColumn<>("Nom");
	      colNom.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNom()));
	      
	      TableColumn<Eleve, String> colAdresse = new TableColumn<>("Adresse");
	      colAdresse.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAdresse()));
	      
	      TableColumn<Eleve, String> colTelephone = new TableColumn<>("Numéro de téléphone");
	      colTelephone.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNumTelephone()));
	      
	      TableColumn<Eleve, String> colNumSAAQ = new TableColumn<>("Numéro SAAQ");
	      colNumSAAQ.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNumSAAQ()));
	      
	      TableColumn<Eleve, String> colActivite = new TableColumn<>("Prochaine activité");
	      colActivite.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLecon().toString()));
	      
	      // Adding the columns to the TableView
	      tableView.getColumns().addAll(colPrenom, colNom, colAdresse, colTelephone, colNumSAAQ, colActivite);
	      
	      // Set the items of the TableView
	      tableView.setItems(observableEleves);
	      
	      // Create a BorderPane and set the TableView as its center
	      BorderPane root = new BorderPane();
	      root.setCenter(tableView);
	      
	      // Create a Scene and set it on the Stage
	      Scene scene = new Scene(root, 500, 300);
	      this.primaryStage.setTitle("Table View in JavaFX");
	      this.primaryStage.setScene(scene);
	      this.primaryStage.show();
	}
	
	public void rechercherElevePar() {
		VBox root = new VBox();
    	root.setSpacing(10);
    	Scene scene = new Scene(root, 400, 400);
    	
    	Text texte1 = new Text("Veuillez choisir une option:\nRecherche par;");
    	texte1.setFont(Font.font(18));
    	texte1.setTextAlignment(TextAlignment.CENTER);
    	root.getChildren().add(texte1);
    	
    	HBox hbox = new HBox();
    	root.getChildren().add(hbox);
    	
    	Button numSAAQ = new Button("Numéro SAAQ");
    	numSAAQ.setOnAction((event)-> {
    		this.barDeRechercheEleve("numSAAQ");
    	});
    	
    	Button nom = new Button("Nom");
    	nom.setOnAction((event)-> {
    		this.barDeRechercheEleve("nom");
    	});
    	
    	Button prenom = new Button("Prénom");
    	prenom.setOnAction((event)-> {
    		this.barDeRechercheEleve("prenom");
    	});
    	
    	hbox.getChildren().addAll(numSAAQ, nom, prenom);
    	hbox.setSpacing(20);
    	hbox.setAlignment(Pos.CENTER);
    	
    	Button quitter = new Button("Quitter");
    	quitter.setOnAction((event)-> {
    		this.vueP.quitter();
    	});
    	
    	root.getChildren().add(quitter);
    	root.setAlignment(Pos.CENTER);
    	root.setSpacing(40);
    	
    	this.primaryStage.setTitle("Recherche d'un élève");
    	this.primaryStage.setScene(scene);
    	this.primaryStage.show();
	}
	
	public void barDeRechercheEleve(String type) {
		VBox root = new VBox();
    	root.setSpacing(10);
    	Scene scene = new Scene(root, 400, 400);
    	
    	Text texte1 = new Text("Veuillez rentrer le " + type +" de l'élève à rechercher:");
    	texte1.setFont(Font.font(18));
    	texte1.setTextAlignment(TextAlignment.CENTER);
    	
		TextField input = new TextField();
		Button confirmer = new Button("Rechercher");
		confirmer.setOnAction((event)-> {
    		this.controleur.rechercherEleve(type, input.getText().trim());
    	});
		
		root.getChildren().addAll(texte1, input, confirmer);
		root.setSpacing(20);
		
		this.primaryStage.setTitle("Recherche d'un élève");
    	this.primaryStage.setScene(scene);
    	this.primaryStage.show();
	}
	
	public void barDeRechercheSupprimerEleve() {
		VBox root = new VBox();
    	root.setSpacing(10);
    	Scene scene = new Scene(root, 400, 400);
    	
    	Text texte1 = new Text("Veuillez rentrer le numéro SAAQ de l'élève à rechercher:");
    	texte1.setFont(Font.font(18));
    	texte1.setTextAlignment(TextAlignment.CENTER);
    	
		TextField input = new TextField();
		Button confirmer = new Button("Rechercher");
		confirmer.setOnAction((event)-> {
    		this.controleur.supprimeurEleves(input.getText().trim());
    	});
		
		root.getChildren().addAll(texte1, input, confirmer);
		root.setSpacing(20);
		
		this.primaryStage.setTitle("Recherche d'un élève");
    	this.primaryStage.setScene(scene);
    	this.primaryStage.show();
	}
	
	public void barDeRechercheModifierEleve() {
		VBox root = new VBox();
    	root.setSpacing(10);
    	Scene scene = new Scene(root, 400, 400);
    	
    	Text texte1 = new Text("Veuillez rentrer le numéro SAAQ de l'élève à rechercher:");
    	texte1.setFont(Font.font(18));
    	texte1.setTextAlignment(TextAlignment.CENTER);
    	
		TextField input = new TextField();
		Button confirmer = new Button("Rechercher");
		confirmer.setOnAction((event)-> {
    		this.controleur.modifierEleves(input.getText().trim());
    	});
		
		root.getChildren().addAll(texte1, input, confirmer);
		root.setSpacing(20);
		
		this.primaryStage.setTitle("Recherche d'un élève");
    	this.primaryStage.setScene(scene);
    	this.primaryStage.show();
	}
	
	public void supprimerEleve(Eleve eleve) {
		VBox root = new VBox();
    	root.setSpacing(10);
    	Scene scene = new Scene(root, 400, 400);
    	
    	Text texte1 = new Text("Confirmer la désinscription de:\n" + 
    							eleve.getPrenom() + " " + eleve.getNom() +
    							"\nNuméro SAAQ: " + eleve.getNumSAAQ() + 
    							"\nTéléphone: " + eleve.getNumTelephone() +
    							"\nAdresse: " + eleve.getAdresse() +
    							"\nInscrit(e) le " + eleve.getDateInscription() + "?");
    	texte1.setFont(Font.font(18));
    	root.getChildren().add(texte1);
    	
    	HBox hbox = new HBox();
    	root.getChildren().add(hbox);
    	
    	Button quitter = new Button("Quitter");
    	quitter.setOnAction((event)-> {
    		this.vueP.quitter();
    	});
    	
    	Button nextMenu = new Button("Confirmer");
    	nextMenu.setOnAction((event)-> {
    		this.controleur.confirmerDesinscription();
    	});
    	
    	hbox.getChildren().add(quitter);
    	hbox.getChildren().add(nextMenu);
    	hbox.setSpacing(20);
    	
    	root.setAlignment(Pos.CENTER);
    	root.setSpacing(30);
    	
    	this.primaryStage.setTitle("Supprimer un élève");
    	this.primaryStage.setScene(scene);
    	this.primaryStage.show();
		
	}
	
	public void modifierInfosEleve(Eleve e) {
		VBox root = new VBox();
    	root.setSpacing(10);
    	Scene scene = new Scene(root, 800, 550);
    	
    	String[] questions = {"Nom:", "Prénom:",
				  "Adresse (au format - sans les crochets [] :\n[numéro de rue] [nom de la rue] [ville] [province] [code postal]):", 
				  "Numéro de téléphone (au format : 5141234567):",
				  "Numéro SAAQ (au format : 123456789):",
				  "Mot de passe que vous désiré:"};
    	TextField[] inputs = new TextField[6];
    	
    	inputs[0] = new TextField(e.getNom());
    	inputs[1] = new TextField(e.getPrenom());
    	inputs[2] = new TextField(e.getAdresse());
    	inputs[3] = new TextField(e.getNumTelephone());
    	inputs[4] = new TextField(e.getNumSAAQ());
    	inputs[5] = new TextField(e.getMotDePasse());
    	
    	Text texte1 = new Text("Veuillez rentrer vos informations");
    	texte1.setFont(Font.font(18));
    	root.getChildren().add(texte1);
    	
    	for (int i = 0; i<6; i++) {
    		Text texte = new Text(questions[i]);
    		texte.setFont(Font.font(18));
    		HBox hbox = new HBox();
    		hbox.getChildren().addAll(texte, inputs[i]);
    		hbox.setSpacing(10);
    		root.getChildren().add(hbox);
    		root.getChildren().add(new Separator());
    	}
    	
    	Button confirmer = new Button("Confirmer");
    	confirmer.setFont(Font.font(18));
    	confirmer.setOnAction((event)->{
    		this.controleur.confirmerModifier(inputs, e);
    	});
    	
    	root.getChildren().add(confirmer);
    	
    	root.setSpacing(20);
    	this.primaryStage.setTitle("Inscription nouvel.le élève");
    	this.primaryStage.setScene(scene);
    	this.primaryStage.show();
	}
	
	public void echecDesinscription() {
		VBox root = new VBox();
    	root.setSpacing(10);
    	Scene scene = new Scene(root, 400, 400);
    	
    	Text texte1 = new Text("Élève non existant");
    	texte1.setFont(Font.font(18));
    	root.getChildren().add(texte1);
    	
    	HBox hbox = new HBox();
    	root.getChildren().add(hbox);
    	
    	Button quitter = new Button("Quitter");
    	quitter.setOnAction((event)-> {
    		this.vueP.quitter();
    	});
    	
    	Button nextMenu = new Button("Menu principal");
    	nextMenu.setOnAction((event)-> {
    		this.vueP.menuPrincipal();
    	});
    	
    	hbox.getChildren().add(quitter);
    	hbox.getChildren().add(nextMenu);
    	
    	root.setAlignment(Pos.CENTER);
    	root.setSpacing(20);
    	
    	this.primaryStage.setTitle("Erreur");
    	this.primaryStage.setScene(scene);
    	this.primaryStage.show();
	}
	
	public void setVueP(VuePrincipale vueP) {
		this.vueP = vueP;
	}
}
