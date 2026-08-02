import java.time.*;
import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class VueActivite {
	private Controleur controleur;
	private Stage primaryStage;
	private VuePrincipale vueP;
	
	public VueActivite (Stage stage, Controleur controleur) {
		this.primaryStage = stage;
		this.controleur = controleur;
	}
	
	public void trouverEleve() {
		VBox root = new VBox();
    	root.setSpacing(10);
    	Scene scene = new Scene(root, 600, 500);
    	
    	Text texte1 = new Text("Veuillez rentrer votre numéro SAAQ: ");
    	texte1.setFont(Font.font(18));
    	TextField input = new TextField();
    	root.getChildren().addAll(texte1, input);
    	
    	Button confirmer = new Button("Confirmer");
    	confirmer.setOnAction((event)-> {
    		this.controleur.demarrerGererActivite(input.getText());
    	});
    	
    	root.getChildren().add(confirmer);
    	root.setAlignment(Pos.CENTER);
    	root.setSpacing(20);
    	
    	this.primaryStage.setTitle("Gérer une activité");
    	this.primaryStage.setScene(scene);
    	this.primaryStage.show();
	}
	
	public void gererActivite(Eleve e) {
		VBox root = new VBox();
    	root.setSpacing(10);
    	Scene scene = new Scene(root, 600, 500);
    	
    	Text texte1 = new Text("Veuillez choisir une option:");
    	texte1.setFont(Font.font(18));
    	root.getChildren().add(texte1);
    	
    	HBox hbox = new HBox();
    	root.getChildren().add(hbox);
    	
    	Button planifier = new Button("Planifier une activité");
    	planifier.setOnAction((event)-> {
    		this.controleur.gestionActivite(true, e);
    	});
    	
    	Button annuler = new Button("Annuler une activité");
    	annuler.setOnAction((event)-> {
    		this.controleur.gestionActivite(false, e);
    	});
    	
    	hbox.getChildren().addAll(planifier, annuler);
    	
    	root.setAlignment(Pos.CENTER);
    	root.setSpacing(20);
    	
    	this.primaryStage.setTitle("Gérer une activité");
    	this.primaryStage.setScene(scene);
    	this.primaryStage.show();
	}
	
	public void annulerActivite(Eleve eleve) {
	    VBox root = new VBox();
	    Text texte = new Text("Votre prochaine activité est: " + eleve.getLecon() + "\nVoulez-vous l'annuler ?");
	    
	    Button oui = new Button("Oui");
	    oui.setOnAction(event -> {
	        this.controleur.confirmerAnnulation(eleve);
	    });
	    
	    Button non = new Button("Non");
	    non.setOnAction(event -> {
	        vueP.menuPrincipal();
	    });

	    root.getChildren().addAll(texte, oui, non);

	    Scene scene = new Scene(root, 600, 500);

	    this.primaryStage.setTitle("Annuler une activité");
	    this.primaryStage.setScene(scene);
	    this.primaryStage.show();
	}
	
	public void planifierActivite(Eleve eleve, Date[][] semaines) {

	    VBox root = new VBox(10);
	    GridPane grid = new GridPane();

	    String[] jours = {"DIM", "LUN", "MAR", "MER", "JEU", "VEN", "SAM"};

	    for (int c = 0; c < 7; c++) {
	        grid.add(new Label(jours[c]), c, 0);
	    }

	    for (int r = 0; r < semaines.length; r++) {
	        for (int c = 0; c < 7; c++) {
	            Date d = semaines[r][c];
	            if (d == null) {
	            	continue;
	            }

	            Button jourBtn = new Button(String.valueOf(d.getJour()));
	            jourBtn.setMinSize(60, 60);

	            if (d.getCreneauDispo().isEmpty()) {
	                jourBtn.setDisable(true);
	            }
	            else {
	                jourBtn.setOnAction(e -> {
	                    afficherCreneaux(eleve, d);
	                });
	            }

	            grid.add(jourBtn, c, r + 1);
	        }
	    }

	    root.getChildren().addAll(new Label("Choisissez une date pour " + eleve.getLecon()), grid);

	    this.primaryStage.setTitle("Planifier une activité");
	    this.primaryStage.setScene(new Scene(root, 600, 500));
	    this.primaryStage.show();
	}
	
	public void afficherCreneaux(Eleve eleve, Date date) {

	    VBox root = new VBox(10);
	    root.getChildren().add(new Label("Jour " + date.getJour()));

	    for (LocalTime h : date.getCreneauDispo().keySet()) {
	        Duration d = date.getCreneauDispo().get(h);
	        Button btn = new Button(h + " (" + d.toMinutes() + " min)");

	        btn.setOnAction(e -> {
	            this.controleur.selectionnerCreneau(eleve, date, h);
	        });

	        root.getChildren().add(btn);
	    }

	    this.primaryStage.setTitle("Planifier une activité");
	    this.primaryStage.setScene(new Scene(root, 600, 500));
	    this.primaryStage.show();
	}
	
	public void demanderVoiture(Eleve eleve, Date date, LocalTime heure) {

	    VBox root = new VBox(20);
	    root.setAlignment(Pos.CENTER);

	    Text texte = new Text("Utiliserez-vous la voiture de l'auto-école ?");
	    texte.setFont(Font.font(18));

	    Button oui = new Button("Oui");
	    Button non = new Button("Non");

	    oui.setOnAction(event -> {
	        // voiture de l'auto-école
	        this.controleur.confirmerPlanification(eleve, date, heure, true, null);
	    });

	    non.setOnAction(event -> {
	        // demander la plaque de l'utilisateur
	        demanderPlaque(eleve, date, heure);
	    });

	    HBox boutons = new HBox(20, oui, non);
	    boutons.setAlignment(Pos.CENTER);

	    root.getChildren().addAll(texte, boutons);

	    Scene scene = new Scene(root, 600, 500);
	    this.primaryStage.setTitle("Choix du véhicule");
	    this.primaryStage.setScene(scene);
	    this.primaryStage.show();
	}
	
	public void demanderPlaque(Eleve eleve, Date date, LocalTime heure) {

	    VBox root = new VBox(15);
	    root.setAlignment(Pos.CENTER);

	    Text texte = new Text("Veuillez entrer votre numéro d'immatriculation :");
	    texte.setFont(Font.font(16));

	    TextField plaqueField = new TextField();
	    plaqueField.setPromptText("ABC123");

	    Button confirmer = new Button("Confirmer");

	    confirmer.setOnAction(event -> {
	        this.controleur.confirmerPlanification(eleve, date, heure, false, plaqueField.getText().trim());
	    });

	    root.getChildren().addAll(texte, plaqueField, confirmer);

	    Scene scene = new Scene(root, 600, 500);
	    this.primaryStage.setTitle("Planifier une activité");
	    this.primaryStage.setScene(scene);
	    this.primaryStage.show();
	}
	
	public void afficherActivites(ArrayList<Activite> activites) {
		
		ObservableList<Activite> observableEleves = FXCollections.observableArrayList(activites);
	  
		TableView<Activite> tableView = new TableView<Activite>();
	  
		TableColumn<Activite, String> colType = new TableColumn<>("Type");
		colType.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType().name()));
	  
		TableColumn<Activite, String> colNumSAAQ = new TableColumn<>("Numéro SAAQ");
		colNumSAAQ.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNumSAAQ()));
	  
		TableColumn<Activite, String> colDate = new TableColumn<>("Date");
		colDate.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLocalDate().toString()));
	  
		TableColumn<Activite, String> colHeure = new TableColumn<>("Heure");
		colHeure.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getHeure()));
	  
		TableColumn<Activite, String> colDuree = new TableColumn<>("Durée");
		colDuree.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getDuree())));
	  
		TableColumn<Activite, String> colMontant = new TableColumn<>("Montant");
		colMontant.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getMontant())));
	  
		TableColumn<Activite, String> colStatut= new TableColumn<>("Statut");
		colStatut.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatut().name()));
	  
		tableView.getColumns().addAll(colType, colNumSAAQ, colDate, colHeure, colDuree, colMontant, colStatut);
	  
		tableView.setItems(observableEleves);
	  
		BorderPane root = new BorderPane();
		root.setCenter(tableView);
	  
		Scene scene = new Scene(root, 1000, 500);
		this.primaryStage.setTitle("Liste des activités");
		this.primaryStage.setScene(scene);
		this.primaryStage.show();
	}
	
	public void demanderSAAQActivite(int id) {

		VBox root = new VBox();
    	root.setSpacing(10);
    	Scene scene = new Scene(root, 600, 500);
    	
    	Text texte1 = new Text("Veuillez rentrer le numéro SAAQ de l'élève\n"
    						   + "qui a participé à l'activité: ");
    	texte1.setFont(Font.font(18));
    	TextField input = new TextField();
    	root.getChildren().addAll(texte1, input);
    	
    	Button confirmer = new Button("Confirmer");
    	confirmer.setOnAction((event)-> {
    		if(id == 1) {
    			this.controleur.afficherDetailsActivite(input.getText());
    		}
    		if(id == 2) {
    			this.controleur.mettreAJourStatutActivite(input.getText());
    		}
    		
    	});
    	
    	root.getChildren().add(confirmer);
    	root.setAlignment(Pos.CENTER);
    	root.setSpacing(20);
    	
    	this.primaryStage.setTitle("Numéro SAAQ d'une activité");
    	this.primaryStage.setScene(scene);
    	this.primaryStage.show();
	}
	
	public void setVueP(VuePrincipale vueP) {
		this.vueP = vueP;
	}

}
