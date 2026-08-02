import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class VuePaiement {

    private Controleur controleur;
    private Stage primaryStage;
    private VuePrincipale vueP;


    public VuePaiement(Stage stage, Controleur controleur){
        this.primaryStage = stage;
        this.controleur = controleur;
    }


    public void afficherPaiements(ArrayList<Paiement> paiements){

        ObservableList<Paiement> liste =
                FXCollections.observableArrayList(paiements);


        TableView<Paiement> table = new TableView<>();


        TableColumn<Paiement,String> numero =
                new TableColumn<>("Numéro facture");

        numero.setCellValueFactory(cell ->
            new SimpleStringProperty(
                cell.getValue().getNumeroUnique()
            )
        );


        TableColumn<Paiement,String> montant =
                new TableColumn<>("Montant");

        montant.setCellValueFactory(cell ->
            new SimpleStringProperty(
                String.valueOf(cell.getValue().getMontant())
            )
        );


        TableColumn<Paiement,String> date =
                new TableColumn<>("Date");

        date.setCellValueFactory(cell ->
            new SimpleStringProperty(
                cell.getValue().getDate().toString()
            )
        );


        TableColumn<Paiement,String> statut =
                new TableColumn<>("Statut");

        statut.setCellValueFactory(cell ->
            new SimpleStringProperty(
                cell.getValue().getStatut().toString()
            )
        );


        TableColumn<Paiement,String> methode =
                new TableColumn<>("Méthode");

        methode.setCellValueFactory(cell -> {

            if(cell.getValue().getMethode() == null)
                return new SimpleStringProperty("");

            return new SimpleStringProperty(
                cell.getValue().getMethode().toString()
            );
        });


        table.getColumns().addAll(
                numero,
                montant,
                date,
                statut,
                methode
        );


        table.setItems(liste);


        BorderPane root = new BorderPane();
        root.setCenter(table);

        Button retour = new Button("Retour");

        retour.setOnAction(e -> {
            vueP.menuPrincipal();
        });

        root.setBottom(retour);


        Scene scene = new Scene(root,700,400);


        primaryStage.setTitle("Gestion des paiements");
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    public void setVueP(VuePrincipale vueP){
        this.vueP = vueP;
    }

}
