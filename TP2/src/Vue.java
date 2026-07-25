import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class Vue extends Application {
	 // Contrôleur de l'application
    private Controleur controleur;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		menuPrincipal(primaryStage);
	}
	
	/**
     * Fonction qui sert à gérer le menu principal de l'utilisateur
     */
    public void menuPrincipal(Stage primaryStage) {
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
    	
    	Button inscrire = new Button("Inscription");
    	inscrire.setFont(Font.font(20));
    	Button connectionEleve = new Button("Se connecter en tant qu'élève");
    	connectionEleve.setFont(Font.font(20));
    	Button connectionInstructeur = new Button("Se connecter en tant qu'instructeur");
    	connectionInstructeur.setFont(Font.font(20));
    	Button quitter = new Button("Quitter");
    	quitter.setFont(Font.font(20));
    	
    	root.getChildren().add(inscrire);
    	root.getChildren().add(connectionEleve);
    	root.getChildren().add(connectionInstructeur);
    	root.getChildren().add(quitter);
    	root.setAlignment(Pos.CENTER);
    	root.setSpacing(20);
    	
    	inscrire.setOnAction((event)-> {
    		this.inscriptionEleve(primaryStage);
    	});
    	connectionEleve.setOnAction((event)-> {
    		this.connectionEleve(primaryStage);
    	});
    	connectionInstructeur.setOnAction((event)-> {
    		this.connectionInstructeur(primaryStage);
    	});
    	quitter.setOnAction((event)-> {
    		this.quitter(primaryStage);
    	});
    	
    	primaryStage.setTitle("Menu principal");
    	primaryStage.setScene(scene);
    	primaryStage.show();
    	
    }
    
    public void inscriptionEleve(Stage primaryStage) {
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
    	primaryStage.setTitle("Inscription nouvel.le élève");
    	primaryStage.setScene(scene);
    	primaryStage.show();
    }
    
    public void connectionEleve(Stage primaryStage) {
    	
    }

	public void connectionInstructeur(Stage primaryStage) {
		
	}
	
	public void erreurEntree(Stage primaryStage) {
		VBox root = new VBox();
    	root.setSpacing(10);
    	Scene scene = new Scene(root, 400, 400);
    	
    	Text texte1 = new Text("Format incorrect");
    	texte1.setFont(Font.font(18));
    	root.getChildren().add(texte1);
    	
    	root.setAlignment(Pos.CENTER);
    	root.setSpacing(20);
    	
    	primaryStage.setTitle("Erreur");
    	primaryStage.setScene(scene);
    	primaryStage.show();
    	//revenir à un autre menu
	}
	
    public void quitter(Stage primaryStage) {
    	VBox root = new VBox();
    	root.setSpacing(10);
    	Scene scene = new Scene(root, 400, 400);
    	
    	Text texte1 = new Text("Au revoir!");
    	texte1.setFont(Font.font(18));
    	root.getChildren().add(texte1);
    	
    	root.setAlignment(Pos.CENTER);
    	root.setSpacing(20);
    	
    	primaryStage.setTitle("Quitter");
    	primaryStage.setScene(scene);
    	primaryStage.show();
    	System.exit(0);
    	
    }
}
