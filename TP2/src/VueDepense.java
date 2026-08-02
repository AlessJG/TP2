import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VueDepense {

    private Stage primaryStage;
    private Controleur controleur;
    private VuePrincipale vueP;

    public VueDepense(Stage stage, Controleur controleur){
        this.primaryStage = stage;
        this.controleur = controleur;
    }

    public void setVueP(VuePrincipale vueP){
        this.vueP = vueP;
    }

    //============================
    // MENU
    //============================

    public void menuDepenses(){

        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);

        Button voiture = new Button("Ajouter une dépense véhicule");

        voiture.setOnAction(e->{
            ajouterDepenseVoiture();
        });

        Button autre = new Button("Ajouter une autre dépense");

        autre.setOnAction(e->{
            ajouterAutreDepense();
        });

        Button consulter = new Button("Consulter les dépenses");

        consulter.setOnAction(e->{
            controleur.consulterDepenses();
        });

        Button retour = new Button("Retour");

        retour.setOnAction(e->{
            vueP.menuPrincipal();
        });

        root.getChildren().addAll(
                voiture,
                autre,
                consulter,
                retour
        );

        primaryStage.setScene(new Scene(root,500,350));
        primaryStage.setTitle("Gestion des dépenses");
        primaryStage.show();
    }

    //============================
    // DEPENSE VOITURE
    //============================

    public void ajouterDepenseVoiture(){

        GridPane root = new GridPane();

        root.setHgap(10);
        root.setVgap(10);

        TextField plaque = new TextField();
        TextField description = new TextField();
        TextField montant = new TextField();

        ComboBox<String> categorie = new ComboBox<>();

        categorie.getItems().addAll(
                "R",
                "E",
                "C"
        );

        root.add(new Label("Plaque"),0,0);
        root.add(plaque,1,0);

        root.add(new Label("Catégorie"),0,1);
        root.add(categorie,1,1);

        root.add(new Label("Description"),0,2);
        root.add(description,1,2);

        root.add(new Label("Montant"),0,3);
        root.add(montant,1,3);

        Button ajouter = new Button("Ajouter");

        ajouter.setOnAction(e->{

            controleur.ajouterDepenseVoiture(
                    plaque.getText(),
                    categorie.getValue(),
                    description.getText(),
                    montant.getText()
            );

        });

        root.add(ajouter,1,4);

        primaryStage.setScene(new Scene(root,500,300));
    }

    //============================
    // AUTRE DEPENSE
    //============================

    public void ajouterAutreDepense(){

        GridPane root = new GridPane();

        root.setHgap(10);
        root.setVgap(10);

        TextField description = new TextField();
        TextField montant = new TextField();

        ComboBox<String> categorie = new ComboBox<>();

        categorie.getItems().addAll(
                "PUBLICITE",
                "BUREAU",
                "TELEPHONE",
                "INTERNET",
                "AUTRE"
        );

        root.add(new Label("Catégorie"),0,0);
        root.add(categorie,1,0);

        root.add(new Label("Description"),0,1);
        root.add(description,1,1);

        root.add(new Label("Montant"),0,2);
        root.add(montant,1,2);

        Button ajouter = new Button("Ajouter");

        ajouter.setOnAction(e->{

            controleur.ajouterAutreDepense(
                    categorie.getValue(),
                    description.getText(),
                    montant.getText()
            );

        });

        root.add(ajouter,1,3);

        primaryStage.setScene(new Scene(root,500,250));
    }

    //============================
    // CONSULTATION
    //============================

    public void afficherDepenses(ArrayList<String[]> depenses){

        ObservableList<String[]> liste =
                FXCollections.observableArrayList(depenses);

        TableView<String[]> table = new TableView<>();

        TableColumn<String[],String> categorie =
                new TableColumn<>("Catégorie");

        categorie.setCellValueFactory(cell->
                new SimpleStringProperty(cell.getValue()[0]));

        TableColumn<String[],String> description =
                new TableColumn<>("Description");

        description.setCellValueFactory(cell->
                new SimpleStringProperty(cell.getValue()[1]));

        TableColumn<String[],String> montant =
                new TableColumn<>("Montant");

        montant.setCellValueFactory(cell->
                new SimpleStringProperty(cell.getValue()[2]));

        table.getColumns().addAll(
                categorie,
                description,
                montant
        );

        table.setItems(liste);

        BorderPane root = new BorderPane();

        root.setCenter(table);

        Button retour = new Button("Retour");

        retour.setOnAction(e->{
            menuDepenses();
        });

        root.setBottom(retour);

        primaryStage.setScene(new Scene(root,700,400));
    }

}
