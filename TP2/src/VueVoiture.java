import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class VueVoiture {
	private Controleur controleur;
    private Stage primaryStage;
    
    public VueVoiture (Stage stage, Controleur controleur) {
		this.primaryStage = stage;
		this.controleur = controleur;
	}
	
    public void inscrireNouveauVehicule() {
    	VBox root = new VBox();
    	root.setSpacing(10);
    	Scene scene = new Scene(root, 800, 550);
    	
    	//Marque,Plaque,Annee,Prix,KmAchat,Etat,Kms
    	String[] questions = {"Marque: ", "Plaque: ", "Année: ", "Prix: ", 
			    			  "Kilométrage à l'achat: ", "État (R-En réparation, V-Vendu, D-Disponible): ", 
			    			  "Kilométrage actuel: "};
    	TextField[] inputs = new TextField[7];
    	
    	Text texte1 = new Text("Veuillez rentrer les informations du véhicule");
    	texte1.setFont(Font.font(18));
    	root.getChildren().add(texte1);
    	
    	for (int i = 0; i<7; i++) {
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
    		this.controleur.confirmerInscriptionVehicule(inputs);
    	});
    	
    	root.getChildren().add(confirmer);
    	
    	root.setSpacing(20);
    	this.primaryStage.setTitle("Inscription nouveau véhicule");
    	this.primaryStage.setScene(scene);
    	this.primaryStage.show();
	}
	 
    public void afficherVoitures(ArrayList<Voiture> voitures) {
    	
    	ObservableList<Voiture> observableEleves = FXCollections.observableArrayList(voitures);
	  
    	TableView<Voiture> tableView = new TableView<Voiture>();
	  
    	TableColumn<Voiture, String> colMarque = new TableColumn<>("Marque");
    	colMarque.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMarque()));
	  
    	TableColumn<Voiture, String> colPlaque = new TableColumn<>("Plaque");
    	colPlaque.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPlaque()));
	  
    	TableColumn<Voiture, String> colAnnee = new TableColumn<>("Annee");
    	colAnnee.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getAnnee())));
	  
    	TableColumn<Voiture, String> colPrix = new TableColumn<>("Prix à l'achat");
    	colPrix.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPrixAchat())));
	  
    	TableColumn<Voiture, String> colKmAchat = new TableColumn<>("Kilométrage à l'achat");
    	colKmAchat.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getKmAchat())));
	  
    	TableColumn<Voiture, String> colEtat = new TableColumn<>("État");
	  	colEtat.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEtat().name()));
	  
	  	TableColumn<Voiture, String> colKm = new TableColumn<>("Kilométrage");
	  	colKm.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getKilometrage())));
	  
	  	tableView.getColumns().addAll(colMarque, colPlaque, colAnnee, colPrix, colKmAchat, colEtat, colKm);
	  
	  	tableView.setItems(observableEleves);
	  
	  	BorderPane root = new BorderPane();
	  	root.setCenter(tableView);
	  
	  	Scene scene = new Scene(root, 700, 300);
	  	this.primaryStage.setTitle("Liste des véhicules");
	  	this.primaryStage.setScene(scene);
	  	this.primaryStage.show();
    }
    
    public void barDeRechercheModifierVehicule() {
		VBox root = new VBox();
    	root.setSpacing(10);
    	Scene scene = new Scene(root, 500, 600);
    	
    	Text texte1 = new Text("Veuillez rentrer la plaque de la voiture à modifier:");
    	texte1.setFont(Font.font(18));
    	texte1.setTextAlignment(TextAlignment.CENTER);
    	
		TextField input = new TextField();
		Button confirmer = new Button("Rechercher");
		confirmer.setOnAction((event)-> {
    		this.controleur.modifierVehicule(input.getText().trim());
    	});
		
		root.getChildren().addAll(texte1, input, confirmer);
		root.setSpacing(20);
		
		this.primaryStage.setTitle("Recherche d'un véhicule");
    	this.primaryStage.setScene(scene);
    	this.primaryStage.show();
	}
    
    public void barDeRechercheSupprimerVehicule() {
		VBox root = new VBox();
    	root.setSpacing(10);
    	Scene scene = new Scene(root, 400, 400);
    	
    	Text texte1 = new Text("Veuillez rentrer la plaque de la voiture à vendre:");
    	texte1.setFont(Font.font(18));
    	texte1.setTextAlignment(TextAlignment.CENTER);
    	
		TextField input = new TextField();
		Button confirmer = new Button("Rechercher");
		confirmer.setOnAction((event)-> {
    		this.controleur.vendreVoiture(input.getText().trim());
    	});
		
		root.getChildren().addAll(texte1, input, confirmer);
		root.setSpacing(20);
		
		this.primaryStage.setTitle("Recherche d'un véhicule");
    	this.primaryStage.setScene(scene);
    	this.primaryStage.show();
	}
    
    public void modifierInfosVoiture(Voiture v) {
		VBox root = new VBox();
    	root.setSpacing(10);
    	Scene scene = new Scene(root, 800, 550);
    	
    	String[] questions = {"Kilometrage: ", "État: "};
    	TextField[] inputs = new TextField[6];
    	
    	inputs[0] = new TextField(String.valueOf(v.getKilometrage()));
    	inputs[1] = new TextField(v.getEtat().name());
    	
    	Text texte1 = new Text("Veuillez rentrer les informations");
    	texte1.setFont(Font.font(18));
    	root.getChildren().add(texte1);
    	
    	for (int i = 0; i<2; i++) {
    		Text texte = new Text(questions[i]);
    		texte.setFont(Font.font(18));
    		HBox hbox = new HBox();
    		hbox.getChildren().addAll(texte, inputs[i]);
    		hbox.setSpacing(10);
    		root.getChildren().addAll(hbox, new Separator());
    	}
    	
    	Button confirmer = new Button("Confirmer");
    	confirmer.setFont(Font.font(18));
    	confirmer.setOnAction((event)->{
    		this.controleur.confirmerModifierVehicule(inputs, v);
    	});
    	
    	root.getChildren().add(confirmer);
    	
    	root.setSpacing(20);
    	this.primaryStage.setTitle("Modifier les informations d'un véhicule");
    	this.primaryStage.setScene(scene);
    	this.primaryStage.show();
	}
    
    public void setVueP(VuePrincipale vueP) {
    	this.setVueP(vueP);
    }
}
