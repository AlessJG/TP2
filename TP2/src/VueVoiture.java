import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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
			
    	// adding some sample data
    	ObservableList<Voiture> observableEleves = FXCollections.observableArrayList(voitures);
	  
    	// Creating a TableView
    	TableView<Voiture> tableView = new TableView<Voiture>();
	  
    	// Creating columns for the TableView
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
	  
	  	// Adding the columns to the TableView
	  	tableView.getColumns().addAll(colMarque, colPlaque, colAnnee, colPrix, colKmAchat, colEtat, colKm);
	  
	  	// Set the items of the TableView
	  	tableView.setItems(observableEleves);
	  
	  	// Create a BorderPane and set the TableView as its center
	  	BorderPane root = new BorderPane();
	  	root.setCenter(tableView);
	  
	  	// Create a Scene and set it on the Stage
	  	Scene scene = new Scene(root, 700, 300);
	  	this.primaryStage.setTitle("Table View in JavaFX");
	  	this.primaryStage.setScene(scene);
	  	this.primaryStage.show();
    }
    
    public void setVueP(VuePrincipale vueP) {
    	this.setVueP(vueP);
    }
}
